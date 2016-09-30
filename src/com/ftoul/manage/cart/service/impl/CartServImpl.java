package com.ftoul.manage.cart.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.DateStr;
import com.ftoul.common.DateUtil;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.cart.service.CartServ;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.ShopCar;
import com.ftoul.po.User;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.vo.ShopCarVO;

@Service("CartServImpl")
public class CartServImpl implements CartServ {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public Result getShopCartByUserId(Parameter param) throws Exception {
		String sql = "select sc.id,sc.number,gs.pic_src,gs.title,gp.param_name,sc.goods_param_id,gs.id as goodsId,ge.id as geId,gej.event_price as gejPrice,ge.event_price as gePrice,\n" +
		"					ge.discount,gp.price,ge.event_begen,ge.event_end,ge.state as geState,gp.stock,gej.quantity\n" +
		"					from shop_car sc  left join goods_param gp on sc.goods_param_id = gp.id and gp.state =1 \n" +
		"					left join goods gs on gp.goods_id=gs.id and gs.state = 1 and sc.state = 1\n" +
		"					left join goods_event_join gej on gs.id = gej.goods_id and gej.state = 1  and sc.state = 1\n" +
		"					left join goods_event ge  on ge.id=gej.event_id and ge.state = 1 and sc.state = 1\n" +
		"					where sc.user_id='" + param.getUserToken().getUser().getId() +"' and sc.state = '1' and gs.state = 1 order by sc.create_time desc";
//		String sql = "select sc.id,sc.number,gs.pic_src,gs.title,gp.param_name,sc.goods_param_id,gs.id as goodsId,ge.id as geId,gej.event_price as gejPrice,ge.event_price as gePrice,"
//					+" ge.discount,gp.price,ge.event_begen,ge.event_end,ge.state,gp.stock,gej.quantity"
//					+" from shop_car sc left join goods_param gp on sc.goods_param_id = gp.id and gp.state =1 left join goods gs on gp.goods_id=gs.id and gs.state = 1 "
//					+" left join goods_event_join gej on gs.id = gej.goods_id and gej.state = 1 left join goods_event ge  on ge.id=gej.event_id and ge.state = 1"
//					+" where sc.user_id='" + param.getUserToken().getUser().getId() +"' and sc.state = '1' order by sc.create_time desc";
		List<Object[]> shopCarList = hibernateUtil.sql(sql);
		List<ShopCarVO> voList = new ArrayList<ShopCarVO>();
		Date currentTime = DateUtil.stringFormatToDate(
				DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss"),
				"yyyy/MM/dd HH:mm:ss");
		for (int i = 0; i < shopCarList.size(); i++) {
			ShopCarVO shopCarVO = new ShopCarVO();
			shopCarVO.setId(shopCarList.get(i)[0].toString());
			shopCarVO.setNumber(shopCarList.get(i)[1].toString());
			if(null!=shopCarList.get(i)[2]){
				shopCarVO.setPicSrc(shopCarList.get(i)[2].toString());
			}
			shopCarVO.setTitle(shopCarList.get(i)[3].toString());
			shopCarVO.setParamName(shopCarList.get(i)[4].toString());
			shopCarVO.setGoodsParamId(shopCarList.get(i)[5].toString());
			shopCarVO.setGoodsId(shopCarList.get(i)[6].toString());
			if(null!= shopCarList.get(i)[7]){
				Date begin = DateUtil.stringFormatToDate(shopCarList.get(i)[12].toString(),
						"yyyy/MM/dd HH:mm:ss");
				Date end = DateUtil.stringFormatToDate(shopCarList.get(i)[13].toString(),
						"yyyy/MM/dd HH:mm:ss");
				if (currentTime.after(begin) && currentTime.before(end)
						&& "1".equals(shopCarList.get(i)[14].toString())){
					if(null!=shopCarList.get(i)[8]){
						shopCarVO.setEventPrice(Float.parseFloat(shopCarList.get(i)[8].toString()));
					}else{
						if(null!=shopCarList.get(i)[9]){
							shopCarVO.setEventPrice(Float.parseFloat(shopCarList.get(i)[9].toString()));
						}else{
							if(null!=shopCarList.get(i)[10] && null!=shopCarList.get(i)[11]){
								Float f = Float.parseFloat(shopCarList.get(i)[10].toString())*Float.parseFloat(shopCarList.get(i)[11].toString());
								shopCarVO.setEventPrice((float)Math.round(f*100)/100);
								shopCarVO.setDiscount(shopCarList.get(i)[10].toString());
							}
						}
					}
				}
			}
			if(null!=shopCarList.get(i)[16]){
				shopCarVO.setStock(shopCarList.get(i)[16].toString());
			}else{
				if(null!=shopCarList.get(i)[15]){
					shopCarVO.setStock(shopCarList.get(i)[15].toString());
				}else{
					shopCarVO.setStock("");
				}
			}
			shopCarVO.setPrice(shopCarList.get(i)[11].toString());
			voList.add(shopCarVO);
		}
		return ObjectToResult.getResult(voList);
	}

	@Override
	public Result saveShopCart(Parameter param) throws Exception {
		
		ShopCarVO shopCarVO = (ShopCarVO) JSONObject.toBean((JSONObject) param.getObj(),ShopCarVO.class);
		Object o = hibernateUtil.hqlFirst("from ShopCar sc where state = '1' and sc.user.id='" +param.getUserToken().getUser().getId()+"'"+" and sc.goodsParam.id ='"+shopCarVO.getGoodsParamId()+"'");
		Object res;
		if(o ==null){
			User user = new User();
			user.setId(param.getUserToken().getUser().getId());
			ShopCar shopCar = new ShopCar();
			shopCar.setCreateTime(new DateStr().toString());
			shopCar.setNumber(shopCarVO.getNumber());
			shopCar.setState("1");
			shopCar.setUser(user);
			GoodsParam goodsParam = new GoodsParam();
			goodsParam.setId(shopCarVO.getGoodsParamId());
			shopCar.setGoodsParam(goodsParam);
			res = hibernateUtil.save(shopCar);
		}else{
			ShopCar car = (ShopCar) o;
			Integer num = Integer.parseInt(shopCarVO.getNumber())+Integer.parseInt(car.getNumber());
			car.setNumber(num.toString());
			res = hibernateUtil.update(car);
		}
		
		return ObjectToResult.getResult(res);
	}
	@Override
	public Result updateShopCart(Parameter param) throws Exception {
		ShopCar shopCar = (ShopCar) JSONObject.toBean((JSONObject) param.getObj(),ShopCar.class);
		ShopCar car = (ShopCar) hibernateUtil.find(ShopCar.class, shopCar.getId());
		Object res;
		car.setNumber(shopCar.getNumber());
		res = hibernateUtil.update(car);
		return ObjectToResult.getResult(res);
	}

	@Override
	public Result delShopCart(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update ShopCar set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}

	@Override
	public Result getGoodsList(Parameter param) throws Exception {
		String hql = "from Goods where state = '1' order by createTime desc ";
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), 6);
		return ObjectToResult.getResult(page);
	}

	@Override
	public Result clearShopCart(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update ShopCar set state = '0' where user.id='" +param.getUserToken().getUser().getId()+"'");
		return ObjectToResult.getResult(num);
	}
	
}

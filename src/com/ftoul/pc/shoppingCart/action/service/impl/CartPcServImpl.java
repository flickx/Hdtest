package com.ftoul.pc.shoppingCart.action.service.impl;

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
import com.ftoul.pc.shoppingCart.action.service.CartPcServ;
import com.ftoul.po.BusinessStore;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.ShopCar;
import com.ftoul.po.User;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.vo.ShopCarVO;

@Service("CartPcServImpl")
public class CartPcServImpl implements CartPcServ {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public Result getShopCartByUserId(Parameter param) throws Exception {
		String goodsSql = "select sc.id,sc.number,gs.pic_src,gs.title,gp.param_name,sc.goods_param_id,gs.id as goodsId,gp.stock,gp.price,gs.shop_id"
					+" from shop_car sc  join goods_param gp on sc.goods_param_id = gp.id and gp.state =1 and sc.state = 1 "
					+" join goods gs on gp.goods_id=gs.id and gs.state = 1 and sc.state = 1"
					+" where sc.user_id='" + param.getUserToken().getUser().getId() +"' and sc.state = 1 and gs.state = 1 order by gs.shop_id";
		List<Object[]> shopCarList = hibernateUtil.sql(goodsSql);
		List<ShopCarVO> voList = new ArrayList<ShopCarVO>();
		for (int i = 0; i < shopCarList.size(); i++) {
			 String eventSql = "select gej.event_price as gejPrice,ge.event_price as gePrice,ge.discount,gp.price,gej.quantity,ge.type_name "
			 				+" from goods gs join goods_param gp"
							+" on gs.id = gp.goods_id and gp.state = 1 and gs.state = 1"
							+" join goods_event_join gej on gej.goods_id = gs.id and gej.state =1 and gs.state =1"
							+" join goods_event ge on ge.id = gej.event_id and ge.state =1 and gej.state = 1"
							+" and ge.type_name != '满减' and gp.id = '"+shopCarList.get(i)[5].toString()+"' and ge.event_begen <NOW() and ge.event_end>NOW()";
			List<Object[]> eventList = hibernateUtil.sql(eventSql);
			BusinessStore bs = (BusinessStore) hibernateUtil.find(BusinessStore.class,shopCarList.get(i)[9].toString());
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
			shopCarVO.setPrice(shopCarList.get(i)[8].toString());
			shopCarVO.setShopId(bs.getId());
			shopCarVO.setShopName(bs.getStoreName());
			if(eventList.size()>0){
				if(null!=eventList.get(0)[0]){
					shopCarVO.setEventPrice(Float.parseFloat(eventList.get(0)[0].toString()));
				}else{
					if(null!=eventList.get(0)[1]){
						shopCarVO.setEventPrice(Float.parseFloat(eventList.get(0)[1].toString()));
					}else{
						if(null!=eventList.get(0)[2] && null!=eventList.get(0)[3] &&!"1".equals(eventList.get(0)[2])){
							Float f = Float.parseFloat(eventList.get(0)[2].toString())*Float.parseFloat(eventList.get(0)[3].toString());
							shopCarVO.setEventPrice((float)Math.round(f*100)/100);
							shopCarVO.setDiscount(eventList.get(0)[2].toString());
						}
					}
				}
				shopCarVO.setStock(eventList.get(0)[4].toString());
				shopCarVO.setTypeName(eventList.get(0)[5].toString());
			}else{
				if(null!=shopCarList.get(i)[7]){
					shopCarVO.setStock(shopCarList.get(i)[7].toString());
				}else{
					shopCarVO.setStock("0");
				}
			}
			voList.add(shopCarVO);
		}
		return ObjectToResult.getResult(voList);
	}

	@Override
	public Result saveShopCart(Parameter param) throws Exception {
		
		ShopCarVO shopCarVO = (ShopCarVO) JSONObject.toBean((JSONObject) param.getObj(),ShopCarVO.class);
		
		String sql = "select ge.event_begen,ge.event_end from shop_car sc" 
				+" left join goods_param gp on sc.goods_param_id = gp.id and gp.state =1"
				+" left join goods gs on gp.goods_id=gs.id and gs.state = 1 and sc.state = 1"
				+" left join goods_event_join gej on gs.id = gej.goods_id and gej.state = 1  and sc.state = 1"
				+" left join goods_event ge  on ge.id=gej.event_id and ge.state = 1 and sc.state = 1"
				+" where sc.state = '1' and gs.state = 1"
				+" and gs.id = '"+shopCarVO.getGoodsId()+"'"
				+" and sc.user_id = '" +param.getUserToken().getUser().getId()+"'"
				+" and ge.type_name = '秒杀'";
		List<Object[]> shopCarList = hibernateUtil.sql(sql);
		Date currentTime = DateUtil.stringFormatToDate(DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss"),"yyyy/MM/dd HH:mm:ss");
		Result result = new Result();
		if(shopCarList.size()>0){
			Date begin = DateUtil.stringFormatToDate(shopCarList.get(0)[0].toString(),
					"yyyy/MM/dd HH:mm:ss");
			Date end = DateUtil.stringFormatToDate(shopCarList.get(0)[1].toString(),
					"yyyy/MM/dd HH:mm:ss");
			if (currentTime.after(begin) && currentTime.before(end)){
				result.setResult(3);
				result.setMessage("秒杀商品只能购买一件，该商品在购物车已存在");
				return result;
			}
		}
		Object o = hibernateUtil.hqlFirst("from ShopCar sc where state = '1' and sc.user.id='" +param.getUserToken().getUser().getId()+"'"+" and sc.goodsParam.id ='"+shopCarVO.getGoodsParamId()+"'");
		Object res;
		GoodsParam goodsParam = (GoodsParam) this.hibernateUtil.find(GoodsParam.class, shopCarVO.getGoodsParamId()+"");
		User user = (User) this.hibernateUtil.find(User.class, param.getUserToken().getUser().getId()+"");
		if(o == null){
			ShopCar shopCar = new ShopCar();
			shopCar.setNumber(shopCarVO.getNumber());
			shopCar.setState("1");
			shopCar.setUser(user);
			shopCar.setGoodsParam(goodsParam);
			shopCar.setCreateTime(new DateStr().toString());
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
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), 6);
		return ObjectToResult.getResult(page);
	}

	@Override
	public Result clearShopCart(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update ShopCar set state = '0' where user.id='" +param.getUserToken().getUser().getId()+"'");
		return ObjectToResult.getResult(num);
	}

	@Override
	public Result clearNoStock(Parameter param) throws Exception {
		Result ret = this.getShopCartByUserId(param);
		List<ShopCarVO> shopCarList = (List<ShopCarVO>)ret.getObj();
		Integer num = 0 ;
		for (int i = 0; i < shopCarList.size(); i++) {
			ShopCarVO shopCarVO = shopCarList.get(i);
			if("0".equals(shopCarVO.getStock())){
				num = hibernateUtil.execHql("update ShopCar set state = '0' where id='" +shopCarVO.getId()+"'");
				num++;
			}
		}
		return ObjectToResult.getResult(num);
	}
	
}

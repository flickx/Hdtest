
package com.ftoul.app.action.cart.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.app.action.cart.service.CartAppServ;
import com.ftoul.app.vo.GoodsWebVo;
import com.ftoul.app.vo.ShopCarAppVo;
import com.ftoul.common.DateStr;
import com.ftoul.common.DateUtil;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.cart.service.CartServ;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.ShopCar;
import com.ftoul.po.User;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.vo.ShopCarVO;

@Service("CartAppServImpl")
public class CartAppServImpl implements CartAppServ {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	
	@Autowired
	private CartServ cartServ;
	@Override
	public Result getShopCartByUserId(Parameter param) throws Exception {
		Result ret = cartServ.getShopCartByUserId(param);
		List<ShopCarVO> shopCarList = (List<ShopCarVO>)ret.getObj();
		List<ShopCarAppVo> shopCarAppList = new ArrayList<ShopCarAppVo>();
		for (int i = 0; i < shopCarList.size(); i++) {
			ShopCarVO shopCarVO = shopCarList.get(i);
			ShopCarAppVo shopCarAppVo = new ShopCarAppVo();
			shopCarAppVo.setId(shopCarVO.getId());
			shopCarAppVo.setUserId(shopCarVO.getUserId());
			shopCarAppVo.setGoodsParamId(shopCarVO.getGoodsParamId());
			shopCarAppVo.setNumber(shopCarVO.getNumber());
			shopCarAppVo.setPicSrc(shopCarVO.getPicSrc());
			shopCarAppVo.setTitle(shopCarVO.getTitle());
			shopCarAppVo.setPrice(shopCarVO.getPrice());
			shopCarAppVo.setParamName(shopCarVO.getParamName());
			shopCarAppVo.setGoodsId(shopCarVO.getGoodsId());
			shopCarAppVo.setEventPrice(shopCarVO.getEventPrice());
			shopCarAppVo.setStock(shopCarVO.getStock());
			shopCarAppVo.setTypeName(shopCarVO.getTypeName());
			shopCarAppVo.setShopId(shopCarVO.getShopId());
			shopCarAppVo.setShopName(shopCarVO.getShopName());
			shopCarAppList.add(shopCarAppVo);
		}
		return ObjectToResult.getResult(shopCarAppList);
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
		Date currentTime = DateUtil.stringFormatToDate(
				DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss"),
				"yyyy/MM/dd HH:mm:ss");
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
		if(o == null){
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
		Result result = cartServ.getGoodsList(param);
		List<Goods> goodsList = (List<Goods>)result.getObj();
		List<GoodsWebVo> goodsWebVoList = new ArrayList<GoodsWebVo>();
		for (int i = 0; i < goodsList.size(); i++) {
			GoodsWebVo goodsWebVo = new GoodsWebVo();
			goodsWebVo.setId(goodsList.get(i).getId());
			goodsWebVo.setTitle(goodsList.get(i).getTitle());
			goodsWebVo.setGoodsPic(goodsList.get(i).getPicSrc());
			goodsWebVo.setPrice(goodsList.get(i).getPrice());
			goodsWebVoList.add(goodsWebVo);
		}
		return ObjectToResult.getResult(goodsWebVoList);        
	}

	@Override
	public Result clearShopCart(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update ShopCar set state = '0' where user.id='" +param.getUserToken().getUser().getId()+"'");
		return ObjectToResult.getResult(num);
	}

	@Override
	public Result clearNoStock(Parameter param) throws Exception {
		Result ret = cartServ.getShopCartByUserId(param);
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

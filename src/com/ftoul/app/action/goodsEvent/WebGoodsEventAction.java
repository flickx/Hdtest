/**
 * 
 */
package com.ftoul.app.action.goodsEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.app.vo.AppLimitGoods;
import com.ftoul.app.vo.AppLimitGoodsVo;
import com.ftoul.app.vo.IndexGoodsAppVo;
import com.ftoul.common.Common;
import com.ftoul.common.DateUtil;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goodsEvent.service.GoodsEventServ;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsEvent;
import com.ftoul.po.GoodsEventJoin;

/**
 * 活动操作相关类
 * @author
 *
 */
@Controller("AppEvent")
@RequestMapping(value = "/app/goodsEvent")
public class WebGoodsEventAction {
	
	@Autowired
	private GoodsEventServ goodsEventServ;
	
	/**
	 * 通过ID获取活动对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsEventById")  
	public @ResponseBody Result getGoodsEventById(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getGoodsEventById(parameter);
	}
	
	/**
	 * 通过代码获取活动对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsEventByCode")  
	public @ResponseBody Result getGoodsEventByCode(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getGoodsEventByCode(parameter);
	}
	
	/**
	 * 通过ID获取活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getGoodsByEventId")  
	public @ResponseBody Result getGoodsByEventId(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getGoodsByEventId(parameter);
	}
	/**
	 * 获取app所有限时抢商品列表
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getTimeLimitGoods")  
	public @ResponseBody Result getTimeLimitGoods(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result re1 = goodsEventServ.getLimitEventList(parameter);
		List<GoodsEvent> goodsEventList = (List<GoodsEvent>)re1.getObj();
		List<AppLimitGoodsVo> goodsAppVoList = new ArrayList<AppLimitGoodsVo>();
		for (GoodsEvent goodsEvent : goodsEventList) {
			String eventId = goodsEvent.getId();
			Result re =  goodsEventServ.getTimeLimitGoods(eventId);
			List<GoodsEventJoin> goodsEventJoinList = (List<GoodsEventJoin>)re.getObj();
			List<AppLimitGoods> goodsList = new ArrayList<AppLimitGoods>();
			for (GoodsEventJoin goodsEventJoin : goodsEventJoinList) {
				AppLimitGoods appLimitGoods = new AppLimitGoods();
				appLimitGoods.setGoodsId(goodsEventJoin.getGoods().getId());
				appLimitGoods.setImgUrl(goodsEventJoin.getGoods().getPicSrc());
				appLimitGoods.setName(goodsEventJoin.getGoods().getTitle());
				appLimitGoods.setNum(goodsEventJoin.getQuantity());
				appLimitGoods.setOriginalPrice(goodsEventJoin.getGoods().getPrice());
				appLimitGoods.setPresentPrice(goodsEventJoin.getEventPrice());
				goodsList.add(appLimitGoods);
			}
			
			AppLimitGoodsVo i  =new AppLimitGoodsVo();
			String begin = goodsEvent.getEventBegen().toString().substring(11,16);
			String end = goodsEvent.getEventEnd().toString();
			long beginTime = DateUtil.stringFormatToDate(goodsEvent.getEventBegen().toString(), "yyyy/MM/dd HH:mm:ss").getTime();
			long endTime = DateUtil.stringFormatToDate(end, "yyyy/MM/dd HH:mm:ss").getTime();
			long now = new Date().getTime();
			if (now > beginTime) {
				i.setEndTime((endTime- now)/1000);
				i.setHasBegin("1");
			}else{
				i.setEndTime((beginTime - now)/1000);
				i.setHasBegin("0");
			}
			i.setStartTime(begin);
			
			i.setAppLimitGoodsList(goodsList);
			goodsAppVoList.add(i);
		}
		return ObjectToResult.getResult(goodsAppVoList);
	}
	/**
	 * 通过活动代码获取所有活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getGoodsByEventCode")  
	public @ResponseBody Result getGoodsByEventCode(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result re =  goodsEventServ.getAppGoodsByEventCode(parameter);
		List<GoodsEventJoin> goodsAppVos = (List<GoodsEventJoin>)re.getObj();
		List<IndexGoodsAppVo> goodsAppVoList = new ArrayList<IndexGoodsAppVo>();
		for (GoodsEventJoin goodsAppVo : goodsAppVos) {
			IndexGoodsAppVo i  =new IndexGoodsAppVo();
			i.setGoodsId(goodsAppVo.getGoods().getId());
			i.setPicSrc(goodsAppVo.getGoods().getPicSrc());
			i.setPrice(goodsAppVo.getGoods().getPrice());
			i.setTitle(goodsAppVo.getGoods().getTitle());
			goodsAppVoList.add(i);
		}
		re.setObj(goodsAppVoList);
		return re;
	}
	/**
	 * 获取每日上新商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getNewestGoodsList")  
	public @ResponseBody Result getNewestGoodsList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result re =  goodsEventServ.getAppNewestGoodsList(parameter);
		List<Goods> goodsList = (List<Goods>)re.getObj();
		List<IndexGoodsAppVo> goodsAppVoList = new ArrayList<IndexGoodsAppVo>();
		for (Goods goods : goodsList) {
			IndexGoodsAppVo i  =new IndexGoodsAppVo();
			i.setGoodsId(goods.getId());
			i.setPicSrc(goods.getPicSrc());
			i.setPrice(goods.getPrice());
			i.setTitle(goods.getTitle());
			goodsAppVoList.add(i);
		}
		return ObjectToResult.getResult(goodsAppVoList);
	}
	
	/**
	 * 获取"为你推荐"商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getCommendGoodsList")  
	public @ResponseBody Result getCommendGoodsList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result re =  goodsEventServ.getAllGoods(parameter);
		List<Object> goodsAppVos = (List<Object>)re.getObj();
		List<IndexGoodsAppVo> goodsAppVoList = new ArrayList<IndexGoodsAppVo>();
		for (Object o : goodsAppVos) {
			Object[] index = (Object[])o;
			IndexGoodsAppVo i  =new IndexGoodsAppVo();
			i.setGoodsId(index[0].toString());
			i.setTitle(index[1].toString());
			i.setPicSrc(index[2].toString());
			i.setPrice(new Double(index[3].toString()));
			goodsAppVoList.add(i);
		}
		re.setObj(goodsAppVoList);
		return re;
	}
}

package com.ftoul.pc.interfaces.action;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.app.vo.AppLimitGoodsVo;
import com.ftoul.app.vo.PcLimitGoods;
import com.ftoul.app.vo.PcLimitGoodsVo;
import com.ftoul.common.Common;
import com.ftoul.common.DateUtil;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goodsEvent.service.GoodsEventServ;
import com.ftoul.po.GoodsEvent;
import com.ftoul.po.GoodsEventJoin;

/**
 * pc限时抢接口
 * @author LiDing
 * 2017-02-17
 */
@Controller
@RequestMapping(value = "/pcInterface/limitGoods/")
public class LimitGoodsAction {

	@Autowired
	private GoodsEventServ goodsEventServ; 
	
	/**
	 * 获取pc端首页正在限时抢商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getPcLimitGoods")  
	public @ResponseBody Result getPcLimitGoods(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result re1 = goodsEventServ.getPcLimitEvent(parameter);
		List<GoodsEvent> goodsEventList = (List<GoodsEvent>)re1.getObj();
		List<PcLimitGoodsVo> goodsPcVoList = new ArrayList<PcLimitGoodsVo>();
		for (GoodsEvent goodsEvent : goodsEventList) {
			String eventId = goodsEvent.getId();
			Result re =  goodsEventServ.getTimeLimitGoods(eventId);
			List<GoodsEventJoin> goodsEventJoinList = (List<GoodsEventJoin>)re.getObj();
			List<PcLimitGoods> goodsList = new ArrayList<PcLimitGoods>();
			for (GoodsEventJoin goodsEventJoin : goodsEventJoinList) {
				PcLimitGoods pcLimitGoods = new PcLimitGoods();
				pcLimitGoods.setGoodsId(goodsEventJoin.getGoods().getId());
				pcLimitGoods.setImgUrl(goodsEventJoin.getGoods().getPicSrc());
				pcLimitGoods.setName(goodsEventJoin.getGoods().getTitle());
				pcLimitGoods.setSubName(goodsEventJoin.getGoods().getSubtitle());
		        NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
		        format.setMinimumFractionDigits(0);// 设置小数位
		        pcLimitGoods.setNum(format.format(goodsEventJoin.getQuantity()*1.0/goodsEventJoin.getDefaultQuantity()));
		        pcLimitGoods.setOriginalPrice(goodsEventJoin.getGoods().getPrice());
		        pcLimitGoods.setPresentPrice(goodsEventJoin.getEventPrice());
				goodsList.add(pcLimitGoods);
			}
			
			PcLimitGoodsVo i  =new PcLimitGoodsVo();
			String begen = goodsEvent.getEventBegen().toString().substring(11,16);
			
			String end = goodsEvent.getEventEnd().toString();
			long last = DateUtil.stringFormatToDate(end, "yyyy/MM/dd HH:mm:ss").getTime();
			long now = new Date().getTime();
			long distance = last - now;
			long endTime = distance/1000;
			i.setStartTime(begen);
			i.setEndTime(endTime);
			i.setPcLimitGoodsList(goodsList);
			goodsPcVoList.add(i);
		}
		return ObjectToResult.getResult(goodsPcVoList);
	}
	
	/**
	 * 获取pc端限时抢购页面限时抢商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getPcLimitGoodsList")  
	public @ResponseBody Result getPcLimitGoodsList(String param) throws Exception{

		Parameter parameter = Common.jsonToParam(param);
		Result re1 = goodsEventServ.getPcLimitEventList(parameter);
		List<GoodsEvent> goodsEventList = (List<GoodsEvent>)re1.getObj();
		List<PcLimitGoodsVo> goodsPcVoList = new ArrayList<PcLimitGoodsVo>();
		for (GoodsEvent goodsEvent : goodsEventList) {
			String eventId = goodsEvent.getId();
			Result re =  goodsEventServ.getTimeLimitGoods(eventId);
			List<GoodsEventJoin> goodsEventJoinList = (List<GoodsEventJoin>)re.getObj();
			List<PcLimitGoods> goodsList = new ArrayList<PcLimitGoods>();
			for (GoodsEventJoin goodsEventJoin : goodsEventJoinList) {
				PcLimitGoods pcLimitGoods = new PcLimitGoods();
				pcLimitGoods.setGoodsId(goodsEventJoin.getGoods().getId());
				pcLimitGoods.setImgUrl(goodsEventJoin.getGoods().getPicSrc());
				pcLimitGoods.setName(goodsEventJoin.getGoods().getTitle());
				pcLimitGoods.setSubName(goodsEventJoin.getGoods().getSubtitle());
		        NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
		        format.setMinimumFractionDigits(2);// 设置小数位
		        pcLimitGoods.setNum(format.format(goodsEventJoin.getQuantity()*1.0/goodsEventJoin.getDefaultQuantity()));
		        pcLimitGoods.setQunatity(goodsEventJoin.getQuantity());
		        pcLimitGoods.setOriginalPrice(goodsEventJoin.getGoods().getPrice());
		        pcLimitGoods.setPresentPrice(goodsEventJoin.getEventPrice());
				goodsList.add(pcLimitGoods);
			}
			
			PcLimitGoodsVo i  =new PcLimitGoodsVo();
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
			i.setPcLimitGoodsList(goodsList);
			goodsPcVoList.add(i);
		}
		return ObjectToResult.getResult(goodsPcVoList);
	}
}

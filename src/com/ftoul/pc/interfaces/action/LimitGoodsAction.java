package com.ftoul.pc.interfaces.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goodsEvent.service.GoodsEventServ;

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
		return goodsEventServ.getPcLimitEvent(parameter);
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
		return goodsEventServ.getPcLimitEventList(parameter);
	}
}

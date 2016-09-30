package com.ftoul.manage.goodsEvent.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goodsEvent.service.GoodsEventServ;

/**
 * 活动操作相关类
 * @author liding
 *
 */
@Controller
@RequestMapping(value = "/manage/goodsEvent")
public class GoodsEventAction {
	
	@Autowired
	private GoodsEventServ goodsEventServ;
	/**
	 * 获取活动类型（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getEventTypeListPage")  
	public @ResponseBody Result getEventTypeListPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getEventTypeListPage(parameter);
	}
	/**
	 * 保存/更新活动类型对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveEventType")  
	public @ResponseBody Result saveEventType(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.saveEventType(parameter);
	}
	/**
	 * 删除活动类型
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "delEventType")  
	public @ResponseBody Result delEventType(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.delEventType(parameter);
	}
	/**
	 * 根据类型ID获取活动类型
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getEventTypeById")  
	public @ResponseBody Result getEventTypeById(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getEventTypeById(parameter);
	}
	/**
	 * 获取活动列表（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsEventListPage")  
	public @ResponseBody Result getGoodsEventListPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getGoodsEventListPage(parameter);
	}
	/**
	 * 保存/更新活动对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveGoodsEvent")  
	public @ResponseBody Result saveGoodsEvent(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.saveGoodsEvent(parameter);
	}
	
	/**
	 * 删除活动
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "delGoodsEvent")  
	public @ResponseBody Result delGoodsEvent(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.delGoodsEvent(parameter);
	}
	
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
	 * 通过ID删除活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "delGoodsByEventId")  
	public @ResponseBody Result getGoodsByEventID(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.delGoodsByEventId(parameter);
	}
	/**
	 * 获取所有上架商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getAllGoods")  
	public @ResponseBody Result getAllGoods(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getAllGoods(parameter);
	}
	/**
	 * 保存活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "saveGoodsEventJoinList")  
	public @ResponseBody Result saveGoodsEventJoinList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.saveGoodsEventJoinList(parameter);
	}
	/**
	 * 获取活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getGoodsEventJoin")  
	public @ResponseBody Result getGoodsEventJoin(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getGoodsEventJoin(parameter);
	}
	/**
	 * 修改活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "updateGoodsEventJoin")  
	public @ResponseBody Result updateGoodsEventJoin(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.updateGoodsEventJoin(parameter);
	}
	/**
	 * 通过活动代码获取所有活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getGoodsByEventCode")  
	public @ResponseBody Result getGoodsByEventCode(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getGoodsByEventCode(parameter);
	}
	/**
	 * 获取每日上新商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getNewestGoodsList")  
	public @ResponseBody Result getNewestGoodsList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getNewestGoodsList(parameter);
	}
	/**
	 * 满减规则列表
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getFullCutRuleList")  
	public @ResponseBody Result getFullCutRuleList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getFullCutRuleList(parameter);
	}
	/**
	 * 新增满减规则
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "saveFullCutRule")  
	public @ResponseBody Result saveFullCutRule(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.saveFullCutRule(parameter);
	}
	/**
	 * 删除满减规则
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "delFullCutRule")  
	public @ResponseBody Result delFullCutRule(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.delFullCutRule(parameter);
	}
	/**
	 * 商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsListPage")  
	public @ResponseBody Result getGoodsListPage(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getGoodsListPage(parameter);
	}
}

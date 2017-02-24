package com.ftoul.manage.goodsEvent.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 用户地址处理的业务接口
 * @author flick
 * 2016-07-12
 */
public interface GoodsEventServ {
	
	
	/**
	 * 获取活动列表（带分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getGoodsEventListPage(Parameter param) throws Exception;
	/**
	 * 获取所有"限时抢"
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getLimitEventList(Parameter param) throws Exception; 
	/**
	 * 根据活动ID获取单个活动对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getGoodsEventById(Parameter param) throws Exception;
	/**
	 * 根据活动代码获取单个活动对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getGoodsEventByCode(Parameter param) throws Exception;
	/**
	 * 保存/更新活动对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveGoodsEvent(Parameter param) throws Exception;
	/**
	 * 删除活动
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result delGoodsEvent(Parameter param) throws Exception;
	/**
	 * 获取活动类型（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	Result getEventTypeListPage(Parameter param) throws Exception;
	/**
	 * 保存/更新活动类型对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveEventType(Parameter param) throws Exception;
	/**
	 * 删除活动
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result delEventType(Parameter param) throws Exception;
	/**
	 * 获取所有上架商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result getAllGoods(Parameter param) throws Exception;
	/**
	 * 通过ID获取活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result getGoodsByEventId(Parameter param) throws Exception;
	/**
	 * 根据类型ID获取活动类型
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getEventTypeById(Parameter param) throws Exception;
	/**
	 * 通过ID删除活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result delGoodsByEventId(Parameter param) throws Exception;
	/**
	 * 保存活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result saveGoodsEventJoinList(Parameter param) throws Exception;
	/**
	 * 获取活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result getGoodsEventJoin(Parameter param) throws Exception;
	/**
	 * 保存活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result updateGoodsEventJoin(Parameter param) throws Exception;
	/**
	 * 通过活动名称获取所有活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result getGoodsByEventCode(Parameter param) throws Exception;
	/**
	 * 获取每日上新商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result getNewestGoodsList(Parameter param) throws Exception;
	
	/**
	 * 满减规则列表
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result getFullCutRuleList(Parameter param) throws Exception;
	/**
	 * 新增满减规则
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result saveFullCutRule(Parameter param) throws Exception;
	/**
	 * 删除满减规则
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result delFullCutRule(Parameter param) throws Exception;
	/**
	 * 
	 * 得到商品列表（带分页）
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result getGoodsListPage(Parameter param) throws Exception;
	/**
	 * 
	 * 减少活动商品数量，用于订单调用
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result reduceEventGoodsSum(Parameter param) throws Exception;
	/**
	 * 获取app所有限时抢商品列表
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result getTimeLimitGoods(String id) throws Exception;	
	/**
	 * 通过活动代码获取app端首页活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result getAppGoodsByEventCode(Parameter param) throws Exception;	
	/**
	 * 获取app首页每日上新商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result getAppNewestGoodsList(Parameter param) throws Exception;
	/**
	 * 活动排除品类下所有商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result saveGoodsEventJoin(Parameter param) throws Exception;
	/**
	 * 通过活动ID获取此活动排除的商品品类
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result getGoodsTypeByEventId(Parameter param) throws Exception;
	/**
	 * 
	 *  查找第一级商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result getGoodsTypeLevel1List(Parameter param) throws Exception;
	/**
	 * 
	 *  通过id查找
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result getByPid(Parameter parameter) throws Exception;
	/**
	 * 获取pc端首页正在限时抢活动
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	Result getPcLimitEvent(Parameter param) throws Exception;
	/**
	 * 获取pc端限时抢页面限时抢活动商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	Result getPcLimitEventList(Parameter param) throws Exception;
	/**
	 * 获取省钱趴价格
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getSqpPrice(Parameter param) throws Exception;
}


package com.ftoul.web.goods.service;

import org.springframework.stereotype.Service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 
*
* 类描述：
* @author: yw
* @date： 日期：2016年8月8日 时间：上午9:58:52
* @version 1.0
*
*/


@Service("WebGoodsImpl")
public interface GoodsServ{

	/**
	 * 保存/更新商品对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveGoods(Parameter param) throws Exception;

	/**
	 * 
	 * 得到商品列表
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result getGoodsList(Parameter parameter) throws Exception;

	/**
	 * 
	 * 得到商品列表（带分页）
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result getGoodsListPage(Parameter parameter) throws Exception;
	
	/**
	 * 
	 * 保存商品第二步
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result getGoodsDetail(Parameter parameter) throws Exception;

	Result getGoodsListByKeyWord(Parameter parameter) throws Exception;
	
	/**
	 * 查询商品的活动价
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result getGoodsEventPrice(Parameter parameter) throws Exception;
	
	/**
	 * 
	 * 查找跨境商品
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result getGoodsListPageByCross(Parameter parameter)throws Exception;
	/**
	 * app首页模糊搜索
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getAppGoodsListByKeyWord(Parameter param) throws Exception; 
	/**
	 * pc首页“每日上新”接口
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getPcNewGoods(Parameter param) throws Exception; 
	/**
	 * pc“每日上新”详情页面接口
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getPcNewGoodsList(String typeId) throws Exception; 
}

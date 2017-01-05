package com.ftoul.app.action.goods.service;

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
public interface GoodsAppServ{
	/**
	 * 查询商品详情
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result getGoodsDetail(Parameter parameter) throws Exception;
	/**
	 * 获得所有收藏商品.
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getUserCollectionList(Parameter param) throws Exception;
	

	/**
	 * 获得所有收藏商品.
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getUserCollectionList(Parameter param) throws Exception;
}

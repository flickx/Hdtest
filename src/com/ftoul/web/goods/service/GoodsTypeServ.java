package com.ftoul.web.goods.service;

import org.springframework.stereotype.Service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 
* 类描述：
* @author: yw
* @date： 日期：2016年7月20日 时间：上午10:24:27
* @version 1.0
* 
*/


@Service("WebGoodsTypeServImpl")
public interface GoodsTypeServ{

	/**
	 * 保存/更新商品类别对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveGoodsType(Parameter param) throws Exception;
	
	/**
	 * 
	 *  查找下一级商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result getNextGoodsTypes(Parameter param) throws Exception;
	
	/**
	 * 
	 *  查找第一级商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result getGoodsTypeLevel1List(Parameter param) throws Exception;
	
	
	/**
	 * 
	 * 删除商品类别对象
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result delGoodsTypes(Parameter param) throws Exception;
	
	/**
	 * 通过商品类别一级得到二三级商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result getGoodsTypeLevel23from1List (Parameter param) throws Exception;
	/**
	 * 
	 * 得到第三级商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */

	Result getLevel3GoodsType(Parameter parameter);

	/**
	 * 
	 * 得到第一级商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result getLevel1GoodsType(Parameter parameter);
	
	/**
	 * 
	 *  通过id查找
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result getByid(Parameter parameter);
	/**
	 * pc端接口：通过商品一级类别得到二三级商品类别
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	Result getGoodsTypeList (Parameter param) throws Exception;
}

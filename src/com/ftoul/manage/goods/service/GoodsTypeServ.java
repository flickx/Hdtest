package com.ftoul.manage.goods.service;

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


@Service("GoodsTypeServImpl")
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
	 *  查找第二级商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result getGoodsTypeLevel2List(Parameter param) throws Exception;
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
	 * 得到第三级商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result getLevel3GoodsTypeByParentId(Parameter parameter);
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
	 * 
	 *  通过pid查找
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result getByPid(Parameter parameter);
	
	/**
	 * 
	 *  通过分类id查找分类
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result getGoodsById(Parameter parameter);
	
	/**
	 * 通过分类id查找分类
	 * @param parameter
	 * @param type (分类级别)
	 * @return
	 */
	Result getGoodsByType(Parameter parameter,String type);
}

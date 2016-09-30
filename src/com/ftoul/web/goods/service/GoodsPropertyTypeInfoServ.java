package com.ftoul.web.goods.service;

import org.springframework.stereotype.Service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 
*
* 类描述：商品属性详情对象
* @author: yw
* @date： 日期：2016年7月26日 时间：下午3:50:01
* @version 1.0
*
*/


@Service("WebGoodsPropertyTypeInfoServImpl")
public interface GoodsPropertyTypeInfoServ{
	/**
	 * 保存/更新用户对象
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result saveGoodsPropertyTypeInfo(Parameter param) throws Exception;
	
	
	/**
	 * 
	 * 删除商品属性详情对象
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result delGoodsPropertyTypeInfo(Parameter param) throws Exception;


	/**
	 * 
	 * 通过大类id得到商品属性详情
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result getGoodsPropInfoTypeListByTypeId(Parameter param);
	
}

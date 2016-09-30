package com.ftoul.manage.goods.service;

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


@Service("GoodsPropertyTypeInfoServImpl")
public interface GoodsPropertyTypeInfoServ{
	/**
	 * 保存商品属性
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result saveGoodsPropertyTypeInfo(Parameter param) throws Exception;
	
	/**
	 * 修改商品属性
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result updateGoodsPropertyTypeInfo(Parameter param) throws Exception;
	
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

	/**
	 * 获取属性内容
	 * @param param
	 * @return
	 */
	Result getPropTypeContent(Parameter param);
}

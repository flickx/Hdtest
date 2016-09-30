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


@Service("WebGoodsPropTypeServImpl")
public interface GoodsPropTypeServ{

	/**
	 * 保存/更新商品属性分类对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveGoodsPropType(Parameter param) throws Exception;
	
	/**
	 * 通过id查找商品属性
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getById(Parameter param) throws Exception;
	
	

	/**
	 * 
	 * 删除商品属性分类对象
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result delGoodsPropType(Parameter param) throws Exception;

	/**
	 * 
	 * 得到所有商品属性大类
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result getGoodsPropType(Parameter param);
}

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


@Service("GoodsPropServImpl")
public interface GoodsPropServ{

	/**
	 * 通过goodsid得到属性列表
	 */
	Result getGoodsProp(Parameter param) throws Exception;

	/**
	 * 保存商品属性
	 */
	Result saveGoodsProp(Parameter parameter) throws Exception;
	
}

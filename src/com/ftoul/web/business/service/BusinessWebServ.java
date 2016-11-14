package com.ftoul.web.business.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 
 * @author wenyujie
 *
 */
public interface BusinessWebServ {
	/**
	 * 
	 * 得到店铺商铺列表（带分页）
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	public Result getStoreGoodsPage(Parameter param) throws Exception;
	/**
	 * 
	 * 获取店铺详情以及商品统计
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */ 
	public Result getBusinessStorePage(Parameter param) throws Exception;
}

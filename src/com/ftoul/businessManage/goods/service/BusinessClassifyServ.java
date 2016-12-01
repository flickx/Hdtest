package com.ftoul.businessManage.goods.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 店铺分类
 * @author yzw
 *
 */
public interface BusinessClassifyServ {

	/**
	 * 添加分类名称
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result saveBusinessClassify(Parameter param) throws Exception;

	
	/**
	 * 商城分类名称
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result delBusinessClassify(Parameter param) throws Exception;

	/**
	 * 
	 * 获取所有分类名称
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result getBusinessClassify(Parameter param) throws Exception;
	
	
	/**
	 * 根据Id获取分类名称
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getBusinessClassifyById(Parameter param) throws Exception;
	
	/**
	 * 根据店铺Id获取分类名称
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getBusinessClassifyByShopId(Parameter param) throws Exception;
	
}

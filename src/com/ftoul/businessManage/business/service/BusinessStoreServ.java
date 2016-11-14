package com.ftoul.businessManage.business.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
/**
 * 
 * @author wenyujie
 * 商家
 *
 */
public interface BusinessStoreServ {
	/**
	 * 查询商家信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result getBusinessPage(Parameter param) throws Exception;
}

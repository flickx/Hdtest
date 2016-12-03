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
	/**
	 * 保存店铺详情
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveBusinessStoreSummary(Parameter param) throws Exception;
	/**
	 * 保存店铺logo
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveBusinessStorePic(Parameter param) throws Exception;
}

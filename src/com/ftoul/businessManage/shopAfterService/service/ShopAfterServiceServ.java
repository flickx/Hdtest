package com.ftoul.businessManage.shopAfterService.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 售后申请单处理的业务接口
 * @author HuDong
 * 2016-07-18
 */
public interface ShopAfterServiceServ {
	
	Result getAfterListPage(Parameter parameter) throws Exception;

	Result getAfterScheduleDetail(Parameter parameter) throws Exception;

	Result auditAfterService(Parameter parameter) throws Exception;

	Result modifyClearCustom(Parameter parameter) throws Exception;

	Result saveAfterServiceLogisticsCompany(Parameter parameter) throws Exception;

	Result saveScheduleStatic(Parameter parameter) throws Exception;
	
}

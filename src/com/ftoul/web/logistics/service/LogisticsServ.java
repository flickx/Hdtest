package com.ftoul.web.logistics.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 物流处理的业务接口
 * @author HuDong
 * 2016-07-18
 */
public interface LogisticsServ {

	Result getLogisticsCompany(Parameter parameter) throws Exception;

	Result getLogisticsCompanyPage(Parameter parameter) throws Exception;
	
	Result saveLogisticsCompanyJoinList(Parameter parameter) throws Exception;
	
}

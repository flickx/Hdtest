package com.ftoul.web.afterService.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.vo.OrderPriceVo;


/**
 * 订单处理的业务接口
 * @author HuDong
 * 2016-07-18
 */
public interface AfterServiceServ {
	
	Result getOrderAfterSchedulePage(Parameter parameter) throws Exception;

	Result saveAfter(Parameter parameter) throws Exception;

	Result getAfterSchedule(Parameter parameter) throws Exception;
	
}

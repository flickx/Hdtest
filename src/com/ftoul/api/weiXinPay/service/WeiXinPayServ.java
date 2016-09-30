package com.ftoul.api.weiXinPay.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ftoul.api.chinaPay.vo.PayResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface WeiXinPayServ {

	/**
	 * 支付订单
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result payOrders(Parameter param) throws Exception;
	/**
	 * 支付前台返回处理
	 * @param request
	 * @return
	 * @throws Exception
	 */
	PayResult payResult(HttpServletRequest request) throws Exception;
	
	/**
	 * 支付后台返回处理
	 * @param request
	 * @return
	 * @throws Exception
	 */
	void payReturn(Map<Object, Object> resultMap) throws Exception;
}

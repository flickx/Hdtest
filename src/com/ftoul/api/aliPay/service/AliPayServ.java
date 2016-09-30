package com.ftoul.api.aliPay.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ftoul.api.chinaPay.vo.PayResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface AliPayServ {

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
	 * @param response 
	 * @return
	 * @throws Exception
	 */
	void payReturn(HttpServletRequest request, HttpServletResponse response) throws Exception;
	/**
	 * 支付后台返回处理(APP)
	 * @param request
	 * @param response 
	 * @return
	 * @throws Exception
	 */
	void payAppReturn(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

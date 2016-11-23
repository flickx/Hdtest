package com.ftoul.web.afterService.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.afterService.service.AfterServiceServ;
import com.ftoul.web.orders.service.OrdersServ;

/**
 * 申请售后单管理
 * @author HuDong
 *
 */
@Controller("WebAfterServiceAction")
@RequestMapping(value = "/web/afterService")
public class AfterServiceAction {

	@Autowired
	private AfterServiceServ afterServiceServ;
	
	/**
	 * 获取售后进度列表
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrderAfterSchedulePage")  
	public @ResponseBody Result getOrderAfterSchedulePage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.getOrderAfterSchedulePage(parameter);
	}
	
	/**
	 * 保存售后申请
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveAfter")  
	public @ResponseBody Result saveAfter(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.saveAfter(parameter);
	}
	

	/**
	 * 获取售后申请
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAfterSchedule")  
	public @ResponseBody Result getAfterSchedule(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.getAfterSchedule(parameter);
	}
}

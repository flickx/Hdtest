package com.ftoul.manage.login.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.login.service.LoginServ;

/**
 * 管理员操作相关类
 * @author flick
 *
 */
@Controller
@RequestMapping(value = "/manage/login")
public class LoginAction {

	@Autowired
	private LoginServ loginServ;
	/**
	 * 管理员登陆
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "login")  
	public @ResponseBody Result login(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return loginServ.login(parameter);
	}
	/**
	 * 管理员注销
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "logout")  
	public @ResponseBody Result logout(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return loginServ.logout(parameter);
	}
	
}

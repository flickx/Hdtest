package com.ftoul.manage.login.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 管理员处理的业务接口
 * @author flick
 * 2016-07-12
 */
public interface LoginServ {
	
	/**
	 * 管理员登陆
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result login(Parameter param) throws Exception;
	
	/**
	 * 管理员注销
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result logout(Parameter param) throws Exception;

	
}

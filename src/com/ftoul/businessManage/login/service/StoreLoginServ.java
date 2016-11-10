package com.ftoul.businessManage.login.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface StoreLoginServ {
	/**
	 * 商家登陆
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result login(Parameter param) throws Exception;
	
	/**
	 * 商家登录注销
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result logout(Parameter param) throws Exception;
}

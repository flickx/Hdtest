package com.ftoul.pc.user.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface PcUserServ {
	/**
	 * 登录，校验用户名，密码
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result login(Parameter param) throws Exception;
	/**
	 * 发送短信验证码
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result sendSmsCode(Parameter param)throws Exception;
	/**
	 * 找回密码 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result forgetPassword(Parameter param)throws Exception;
}

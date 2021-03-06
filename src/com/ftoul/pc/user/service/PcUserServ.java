package com.ftoul.pc.user.service;

import javax.servlet.http.HttpServletRequest;

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
	
	/**
	 * 获取用户个人信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getUserInfo(Parameter param)throws Exception;
	
	/**
	 * 保存用户信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result saveUser(Parameter param)throws Exception;
	
	/**
	 * 头像上传
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result picUpload(Parameter param,HttpServletRequest request)throws Exception;
	
	/**
	 * 修改登陆密码
	 * @return
	 * @throws Exception
	 */
	Result updatePassword(Parameter param)throws Exception;
	
	/**
	 * 判断邮箱是否存在
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getEmailByName(Parameter param)throws Exception;
	
	/**
	 * 发送邮件
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result sendEmail(Parameter param,HttpServletRequest request)throws Exception;
	/**
	 * 验证邮箱激活码
	 * @param email
	 * @param code
	 * @return
	 * @throws Exception
	 */
	Result activeEmail(String userId,String code)throws Exception;
	
	
	/**
	 * 验证短信验证码是否正确
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result validteSmsCode(Parameter param)throws Exception;
	
	
	/**
	 * 查询用户头像 ，优惠券，蜂币
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getUser(Parameter param)throws Exception;
}

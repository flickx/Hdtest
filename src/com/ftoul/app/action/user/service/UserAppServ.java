/**
 * 
 */
package com.ftoul.app.action.user.service;

import javax.servlet.http.HttpServletRequest;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.vo.UsersVO;

/**
 * @author 李丁
 * @date:2016年7月20日 下午4:44:27
 * @类说明 :
 */

public interface UserAppServ {
	/**
	 * 注册 新增用户对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result register(Parameter param) throws Exception;
	/**
	 * 校验用户名是否存在
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result checkUserExists(Parameter param) throws Exception;
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
	 * app端发送短信验证码
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result sendAppSmsCode(Parameter param)throws Exception;
	/**
	 * 找回密码 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result doResetPassword(Parameter param)throws Exception;
	
	/**
	 * 重置密码 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result resetPassword(Parameter param)throws Exception;
	
	Result getAddressBook(Parameter param) throws Exception;
	
	/**
	 * 根据用户ID获取单个用户对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	
	Result getUserById(Parameter param) throws Exception;
//	/**
//	 * 找回密码 “下一步”
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	Result toResetPassword(Parameter param)throws Exception;
	
	/**
	 * 保存/更新用户对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveUser(Parameter param) throws Exception;
	
	/**
	 * 头像上传
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	Result picUpload(Parameter param,HttpServletRequest request) throws Exception;
}

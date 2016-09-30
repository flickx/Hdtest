package com.ftoul.manage.admin.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 管理员处理的业务接口
 * @author flick
 * 2016-07-12
 */
public interface AdminServ {
	
	/**
	 * 获取用户列表（带分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getAdminList(Parameter param) throws Exception;
	
	/**
	 * 根据用户ID获取单个用户对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getAdminById(Parameter param) throws Exception;

	/**
	 * 保存/更新用户对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveAdmin(Parameter param) throws Exception;
	/**
	 * 删除用户信息
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result delAdmin(Parameter param) throws Exception;
	/**
	 * 操作日志
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result getLogList(Parameter parameter) throws Exception;
	
}

package com.ftoul.manage.user.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 乐币管理接口
 * @author yzw
 *
 */
public interface LecoinServ {

	/**
	 * 保存/更新乐币管理
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveLecoin(Parameter param) throws Exception;
	
	/**
	 * 查找乐币
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getLecoin(Parameter param)throws Exception;
	
}

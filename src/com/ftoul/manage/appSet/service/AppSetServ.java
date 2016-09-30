package com.ftoul.manage.appSet.service;

import org.springframework.stereotype.Service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 
* 类描述：
* @author: yw
* @date： 日期：2016年7月20日 时间：上午10:24:27
* @version 1.0
* 
*/


public interface AppSetServ{

	/**
	 * 保存/更新系统设置对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveAppSet(Parameter param) throws Exception;
	
	/**
	 * 得到系统设置对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getAppSet(Parameter param) throws Exception;
	
	
}

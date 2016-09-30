package com.ftoul.manage.systemset.service;

import java.util.List;

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


@Service("SystemSetImpl")
public interface SystemSetServ{

	/**
	 * 保存/更新系统设置对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveSystemSet(Parameter param) throws Exception;
	
	/**
	 * 得到系统设置对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getSystemSet(Parameter param) throws Exception;
	
	
}

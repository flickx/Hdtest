/**
 * 
 */
package com.ftoul.manage.systemset.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.systemset.service.SystemSetServ;

/**
 *
 * 类描述  系统设置
 * 
 * @author: yw
 * @date： 日期：2016年7月20日 时间：上午11:11:05
 * @version 1.0
 *
 */
@Controller
@RequestMapping(value = "/manage/systemSet")
public class SystemSetAction {
	@Autowired
	private SystemSetServ systemSetServ;
	
	/**
	 * 更新系统设置
	 * @param param 当前级别参数
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveSystemSet")  
	public @ResponseBody Result saveSystemSet(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return systemSetServ.saveSystemSet(parameter);
	}
	
	/**
	 * 得到当前的系统配置
	 * @param param 当前级别参数
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getSystemSet")  
	public @ResponseBody Result getSystemSet(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return systemSetServ.getSystemSet(parameter);
	}
}

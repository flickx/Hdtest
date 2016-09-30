/**
 * 
 */
package com.ftoul.web.appSet.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.appSet.service.AppSetServ;

/**
 *
 * 类描述  系统设置
 * 
 * @author: yw
 * @date： 日期：2016年7月20日 时间：上午11:11:05
 * @version 1.0
 *
 */
@Controller("AppSetWebAction")
@RequestMapping(value = "/web/appSet")
public class AppSetAction {
	@Autowired
	private AppSetServ appSetServ;
	
	/**
	 * 更新app设置
	 * @param param 当前级别参数
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveAppSet")  
	public @ResponseBody Result saveAppSet(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return appSetServ.saveAppSet(parameter);
	}
	
	/**
	 * 得到当前的APP配置
	 * @param param 当前级别参数
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAppSet")  
	public @ResponseBody Result getAppSet(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return appSetServ.getAppSet(parameter);
	}
}

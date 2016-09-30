package com.ftoul.manage.admin.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.admin.service.AdminServ;

/**
 * 管理员操作相关类
 * @author flick
 *
 */
@Controller
@RequestMapping(value = "/manage/admin")
public class AdminAction {

	@Autowired
	private AdminServ adminServ;
	/**
	 * 获取管理员列表（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAdminList")  
	public @ResponseBody Result getAdminList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return adminServ.getAdminList(parameter);
	}
	/**
	 * 根据用户ID获取单个管理员对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAdminById")  
	public @ResponseBody Result getAdminById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		if(parameter.getPageNum() == null){
			parameter.setPageNum(1);
		}
		return adminServ.getAdminById(parameter);
	}
	/**
	 * 修改密码
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveAdmin")  
	public @ResponseBody Result saveAdmin(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return adminServ.saveAdmin(parameter);
	}
	/**
	 * 删除管理员
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "delAdmin")  
	public @ResponseBody Result delAdmin(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return adminServ.delAdmin(parameter);
	}
	/**
	 * 操作日志
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getLogList")  
	public @ResponseBody Result getLogList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return adminServ.getLogList(parameter);
	}
	
}

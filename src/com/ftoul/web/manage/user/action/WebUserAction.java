/**
 * 
 */
package com.ftoul.web.manage.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.manage.user.service.WebUserServ;
import com.ftoul.web.vo.UsersVO;

/**
 * @author 李丁
 * @date:2016年7月20日 下午4:35:39
 * @类说明 : 前台用户注册，登录
 */
@Controller
@RequestMapping(value = "/web/manage/user")
public class WebUserAction {
	@Autowired
	private WebUserServ webUserServ;
	/**
	 * 注册
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "register") 
	public @ResponseBody Result register(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return webUserServ.register(parameter);
	}
	/**
	 * 校验手机号码
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "checkUserExists") 
	public @ResponseBody Result checkUserExists(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return webUserServ.checkUserExists(parameter);
	}
	/**
	 * 登录
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "login") 
	public @ResponseBody Result login(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return webUserServ.login(parameter);
	}
	/**
	 * 获取短信验证码
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "sendSmsCode",method=RequestMethod.POST) 
	public Result sendSmsCode(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return webUserServ.sendSmsCode(parameter);
	}
	/**
	 * 找回密码
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "doResetPassword") 
	public @ResponseBody Result doResetPassword(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return webUserServ.doResetPassword(parameter);
	}
	
	/**
	 * 重置密码
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "resetPassword") 
	public @ResponseBody Result resetPassword(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return webUserServ.resetPassword(parameter);
	}
	
	
	
	/**
	 * 根据用户ID获取单个用户对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getUserById")  
	public @ResponseBody Result getUserById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		if(parameter.getPageNum() == null){
			parameter.setPageNum(1);
		}
		return webUserServ.getUserById(parameter);
	}
	
	/**
	 * 获取该用户通讯录信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getAddressBook") 
	public @ResponseBody Result getAddressBook(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return webUserServ.getAddressBook(parameter);
	}
	
	/**
	 * 保存/更新用户对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveUser")  
	public @ResponseBody Result saveUser(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return webUserServ.saveUser(parameter);
	}
	
	//	/**
//	 * 找回密码:下一步，进入重置密码页面
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "toResetPassword") 
//	public @ResponseBody Result toResetPassword(String param) throws Exception{
//		Parameter parameter = Common.jsonToParam(param);
//		return webUserServ.toResetPassword(parameter);
//	}
}

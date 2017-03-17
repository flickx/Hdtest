package com.ftoul.pc.user.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.user.service.PcUserServ;
import com.ftoul.web.manage.user.service.WebUserServ;

@Controller
@RequestMapping("pc/user")
public class PcUserAction {
	@Autowired
	private PcUserServ pcUserServ;
	@Autowired
	private WebUserServ webUserServ;
	/**
	 * 登录
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "login") 
	public @ResponseBody Result login(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return pcUserServ.login(parameter);
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
	 * 获取短信验证码
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "sendSmsCode",method=RequestMethod.POST) 
	public Result sendSmsCode(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return pcUserServ.sendSmsCode(parameter);
	}
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
	 * 找回密码
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "forgetPassword") 
	public @ResponseBody Result forgetPassword(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return pcUserServ.forgetPassword(parameter);
	}
	/**
	 * 获取用户个人信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getUserInfo") 
	public @ResponseBody Result getUserInfo(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return pcUserServ.getUserInfo(parameter);
	}
	/**
	 * 保存/修改用户个人信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveUser") 
	public @ResponseBody Result saveUser(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return pcUserServ.saveUser(parameter);
	}
	/**
	 * 头像上传
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "picUpload")
	public @ResponseBody Result picUpload(String param, HttpServletRequest request)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return pcUserServ.picUpload(parameter, request);
	}
	/**
	 * 修改登陆密码
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updatePassword")
	public @ResponseBody Result updatePassword(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return pcUserServ.updatePassword(parameter);
	}
	/**
	 * 判断邮箱是否存在
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getEmailByName")
	public @ResponseBody Result getEmailByName(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return pcUserServ.getEmailByName(parameter);
	}
	/**
	 * 发送邮件
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sendEmail")
	public @ResponseBody Result sendEmail(String param,HttpServletRequest request)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return pcUserServ.sendEmail(parameter, request);
	}
	/**
	 * 验证邮箱激活码
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "activeEmail")
	public @ResponseBody Result activeEmail(String param,HttpServletRequest request)throws Exception{
		String userId = request.getParameter("userId");
		String code = request.getParameter("code");
		return pcUserServ.activeEmail(userId,code);
	}
}

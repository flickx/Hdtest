package com.ftoul.businessManage.login.Action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.businessManage.login.service.StoreLoginServ;
import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 商家登录操作相关类
 * @author wenyujie
 *
 */
@Controller
@RequestMapping(value = "/businessManage/login")
public class StoreLoginAction {
	@Autowired
	private StoreLoginServ storeLoginServ;
	/**
	 * 商家登陆
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "loginBusinessStoreLogin")  
	public @ResponseBody Result loginBusinessStoreLogin(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return storeLoginServ.login(parameter);
	}
	/**
	 * 商家登录注销
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "logoutBusinessStoreLogin")  
	public @ResponseBody Result logoutBusinessStoreLogin(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return storeLoginServ.logout(parameter);
	}
}

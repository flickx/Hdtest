package com.ftoul.test.rest.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.User;
import com.ftoul.web.address.service.AddressServ;

/**
 * 用户地址操作相关类
 * @author hud
 *
 */
@Controller
@RequestMapping(value = "/test/rest")
public class RestAction {
	
	/**
	 *  
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public @ResponseBody Result getUser(String param) throws Exception {
		User user = new User();
		user.setName("abc");
		user.setId("1");
		return ObjectToResult.getResult(user);
	}
	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public @ResponseBody Result addUser(String param) throws Exception {
		User user = new User();
		user.setName("aaa");
		user.setId("aaa");
		return ObjectToResult.getResult("save success");
	}
	
	@RequestMapping(value="/user",method=RequestMethod.PUT)
	public @ResponseBody Result uploadUser(String param) throws Exception {
		User user = new User();
		user.setName("aaa");
		user.setId("aaa");
		return ObjectToResult.getResult("upload success");
	}
	
	
}

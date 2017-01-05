package com.ftoul.pc.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.user.service.PcUserServ;

@Controller
@RequestMapping("pc/user")
public class PcUserAction {
	@Autowired
	private PcUserServ pcUserServ;
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
}

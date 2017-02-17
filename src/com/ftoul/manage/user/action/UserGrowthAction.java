package com.ftoul.manage.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.user.service.UserGrowthServ;

/**
 * 会员成长值action
 * @author yzw
 *
 */
@Controller
@RequestMapping(value = "/manage/userGrowth")
public class UserGrowthAction {

	@Autowired
	private UserGrowthServ userGrowthServ;
	
	/**
	 * 添加会员成长值
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveUserGrowth")  
	public @ResponseBody Result saveUserGrowth(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userGrowthServ.saveUserGrowth(parameter);
	}
	/**
	 * 查询会员成长值
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getUserGrowth")  
	public @ResponseBody Result getUserGrowth(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result result = userGrowthServ.getUserGrowth(parameter);
		return result;
	}
	
}

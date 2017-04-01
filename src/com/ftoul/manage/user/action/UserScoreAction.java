package com.ftoul.manage.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.user.service.UserScoreServ;

@Controller
@RequestMapping(value = "/manage/userScore")
public class UserScoreAction {
	@Autowired
	private UserScoreServ userScoreServ;
	/**
	 * 获取用户积分列表（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getScorePage")  
	public @ResponseBody Result getScorePage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userScoreServ.getScorePage(parameter);
	}
}

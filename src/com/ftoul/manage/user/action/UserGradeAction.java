package com.ftoul.manage.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.user.service.UserGradeServ;

/**
 * 会员等级
 * @author yzw
 *
 */
@Controller
@RequestMapping(value = "/manage/userGrade")
public class UserGradeAction {

	@Autowired
	private UserGradeServ userGradeServ;
	/**
	 * 获取会员等级列表（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getUserGradeListPage")  
	public @ResponseBody Result getUserGradeListPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userGradeServ.getUserGradeListPage(parameter);
	}
	/**
	 * 添加会员等级
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveUserGrade")  
	public @ResponseBody Result saveUserGrade(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userGradeServ.saveUserGrade(parameter);
	}
	
	/**
	 * 根据id查询会员等级
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getUserGradeById")  
	public @ResponseBody Result getUserGradeById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userGradeServ.getUserGradeById(parameter);
	}
	/**
	 * 删除会员等级
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delUserGrade")  
	public @ResponseBody Result delUserGrade(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userGradeServ.delUserGrade(parameter);
	}
	/**
	 * 删除会员等级图标
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delUserGradePic")  
	public @ResponseBody Result delUserGradePic(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userGradeServ.delUserGradePic(parameter);
	}
}

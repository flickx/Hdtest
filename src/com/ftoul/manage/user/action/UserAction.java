package com.ftoul.manage.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.user.service.UserServ;

/**
 * 用户操作相关类（demo用）
 * @author flick
 *
 */
@Controller
@RequestMapping(value = "/manage/user")
public class UserAction {

	@Autowired
	private UserServ userServ;
	/**
	 * 获取用户列表（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getUserListPage")  
	public @ResponseBody Result getUserListPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userServ.getUserListPage(parameter);
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
		return userServ.getUserById(parameter);
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
		return userServ.saveUser(parameter);
	}
	/**
	 * 删除用户信息
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "delUser")  
	public @ResponseBody Result delUser(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userServ.delUser(parameter);
	}
	/**
	 * 根据ID获取订单详情
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersDetailById")  
	public @ResponseBody Result getOrdersDetailById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userServ.getOrdersDetailById(parameter);
	}
	/**
	 * 获取用户列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getUserList")  
	public @ResponseBody Result getUserList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userServ.getUserList(parameter);
	}
	/**
	 * 获取用户浏览记录
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getUserBrowseList")  
	public @ResponseBody Result getUserBrowseList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
//		return userServ.getUserBrowseList(parameter);
		return userServ.getUserBrowseMongoList(parameter);
	}
	/**
	 * 保存/修改用户浏览记录
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveUserBrowse")  
	public @ResponseBody Result saveUserBrowse(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userServ.saveUserBrowse(parameter);
	}
	/**
	 * 删除用户浏览记录
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "delUserBrowse")  
	public @ResponseBody Result delUserBrowse(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userServ.delUserBrowse(parameter);
	}
	
	/**
	 * 停用用户
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "stopUser")  
	public @ResponseBody Result stopUser(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userServ.stopUser(parameter);
	}
	/**
	 * 重置密码
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "doResetPassword")  
	public @ResponseBody Result doResetPassword(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userServ.doResetPassword(parameter);
	}
	
	/**
	 * 获取待办事项
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDeals")  
	public @ResponseBody Result getDeals(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userServ.getDeals(parameter);
	}
	/**
	 * 获取商品销售情况
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsSales")  
	public @ResponseBody Result getGoodsSales(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userServ.getGoodsSales(parameter);
	}
	/**
	 * 获取本月注册用户
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getRegisterUser")  
	public @ResponseBody Result getRegisterUser(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userServ.getRegisterUser(parameter);
	}
	
	/**
	 * 获取畅销前20名商品
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getPopularGoods")  
	public @ResponseBody Result s(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userServ.getPopularGoods(parameter);
	}
}

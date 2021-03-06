package com.ftoul.manage.user.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 用户处理的业务接口
 * @author flick
 * 2016-07-12
 */
public interface UserServ {
	
	/**
	 * 获取用户列表（带分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getUserListPage(Parameter param) throws Exception;
	
	/**
	 * 根据用户ID获取单个用户对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getUserById(Parameter param) throws Exception;

	/**
	 * 保存/更新用户对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveUser(Parameter param) throws Exception;
	/**
	 * 删除用户信息
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result delUser(Parameter param) throws Exception;
	/**
	 * 根据用户ID获取订单详情
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	public Result getOrdersDetailById(Parameter param) throws Exception ;
	/**
	 * 获取用户列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getUserList(Parameter param) throws Exception ;
	/**
	 * 获取用户浏览记录
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getUserBrowseList(Parameter parameter) throws Exception ;
	/**
	 * 获取用户浏览记录(mongo)
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getUserBrowseMongoList(Parameter param) throws Exception;
	/**
	 * 保存/修改用户浏览记录
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveUserBrowse(Parameter parameter) throws Exception ;
	
	/**
	 * 删除用户浏览记录
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result delUserBrowse(Parameter parameter) throws Exception ;
	
	
	/**
	 * 停用用户
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result stopUser(Parameter parameter) throws Exception ;
	
	/**
	 * 重置密码
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result doResetPassword(Parameter parameter) throws Exception ;
	
	/**
	 * 获取待办事项
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result getDeals(Parameter parameter) throws Exception;
	
	
	/**
	 * 获取商品销量情况
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result getGoodsSales(Parameter parameter) throws Exception;
	
	/**
	 * 获取本周商品销量情况
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result getGoodsSalesByWeeks(Parameter parameter) throws Exception;
	
	/**
	 * 获取本月注册用户数
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result getRegisterUser(Parameter parameter) throws Exception;
	
	/**
	 * 获取畅销商品前20名
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result getPopularGoods(Parameter parameter) throws Exception;
}

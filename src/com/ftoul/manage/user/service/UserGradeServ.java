package com.ftoul.manage.user.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 会员等级serv
 * @author yzw
 *
 */
public interface UserGradeServ {
	
	/**
	 * 获取会员等级列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getUserGradeListPage(Parameter param) throws Exception;
	/**
	 * 保存/更新会员等级
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveUserGrade(Parameter param) throws Exception;
	
	/**
	 * 根据id查询会员等级
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getUserGradeById(Parameter param) throws Exception;
	/**
	 * 删除会员等级
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result delUserGrade(Parameter param) throws Exception;
	
	/**
	 * 删除会员等级图标
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	Result delUserGradePic(Parameter param) throws Exception;
	
	
}

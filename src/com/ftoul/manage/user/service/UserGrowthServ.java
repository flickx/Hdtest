package com.ftoul.manage.user.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 会员成长值接口
 * @author yzw
 *
 */
public interface UserGrowthServ {

	/**
	 * 保存/更新会员成长值
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveUserGrowth(Parameter param) throws Exception;
	
	/**
	 * 查找会员成长值
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getUserGrowth(Parameter param)throws Exception;
	
}

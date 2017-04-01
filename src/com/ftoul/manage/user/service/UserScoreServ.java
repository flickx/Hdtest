package com.ftoul.manage.user.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface UserScoreServ {
	/**
	 * 获取用户积分列表（带分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getScorePage(Parameter param) throws Exception;
}

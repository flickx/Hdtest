package com.ftoul.pc.interfaces.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface PcAdvertService {
	/**
	 * 获取pc指定位置广告对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	public Result getAdvert(Parameter param) throws Exception;
}

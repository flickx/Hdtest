package com.ftoul.web.open.demo.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 获取快递信息的业务接口
 * @author flick
 * 2016-07-12
 */
public interface KDServ {
	
	/**
	 * 获取单个快递公司
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 * @author flick
	 */
	Result getKDInfo(Parameter param) throws Exception;
	
	/**
	 * 获取快递列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 * @author flick
	 */
	Result getKDInfoList(Parameter param) throws Exception;

	/**
	 * 获取快递列表（带分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 * @author flick
	 */
	Result getKDInfoListPage(Parameter param) throws Exception;
	/**
	 * 获取快递当前状态
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 * @author flick
	 */
	Result getKdStatic(Parameter param) throws Exception;
}

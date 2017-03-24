package com.ftoul.manage.area.service;

import org.springframework.stereotype.Service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

@Service("AreaServImpl")
public interface AreaServ {

	/**
	 * 获取省份列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getProvices(Parameter param) throws Exception;
	/**
	 * 保存省份
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveProvince(Parameter param) throws Exception;
	/**
	 * 保存城市
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveCity(Parameter param) throws Exception;
	/**
	 * 获取省份下级城市列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getCitys(Parameter param) throws Exception;

	/**
	 * 获取区县列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getCountys(Parameter param) throws Exception;
	/**
	 * 获取乡镇列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getTowns(Parameter param) throws Exception;
	/**
	 * 获取村庄列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getVillages(Parameter param) throws Exception;
	
	Result saveCounty(Parameter param) throws Exception;
	
	Result saveTown(Parameter param) throws Exception;
}

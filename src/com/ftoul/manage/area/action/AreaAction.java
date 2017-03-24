package com.ftoul.manage.area.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.area.service.AreaServ;

/**
 * 后台行政地区设置
 * @author lid
 * 2017-3-22
 */
@Controller
@RequestMapping(value = "/manage/area")
public class AreaAction {
	@Autowired
	private AreaServ areaServ;
	
	/**
	 * 获取区县列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="getCountys")
	public @ResponseBody Result getCountys(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return areaServ.getCountys(parameter);
	}
	/**
	 * 获取省份列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="getProvices")
	public @ResponseBody Result getProvices(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return areaServ.getProvices(parameter);
	}
	/**
	 * 保存省份
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="saveProvince")
	public @ResponseBody Result saveProvince(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return areaServ.saveProvince(parameter);
	}
	/**
	 * 保存城市
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="saveCity")
	public @ResponseBody Result saveCity(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return areaServ.saveCity(parameter);
	}
	
	/**
	 * 保存区县
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="saveCounty")
	public @ResponseBody Result saveCounty(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return areaServ.saveCounty(parameter);
	}
	/**
	 * 保存乡镇
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="saveTown")
	public @ResponseBody Result saveTown(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return areaServ.saveTown(parameter);
	}
	
	/**
	 * 获取省份下级城市列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="getCitys")
	public @ResponseBody Result getCitys(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return areaServ.getCitys(parameter);
	}

	/**
	 * 获取街道列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="getTowns")
	public @ResponseBody Result getTowns(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return areaServ.getTowns(parameter);
	}
	/**
	 * 获取村庄列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="getVillages")
	public @ResponseBody Result getVillages(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return areaServ.getVillages(parameter);
	}
}

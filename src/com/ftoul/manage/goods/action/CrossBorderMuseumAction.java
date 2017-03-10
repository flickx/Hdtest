package com.ftoul.manage.goods.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.goods.service.CrossBorderMuseumServ;

/**
 * 跨境馆管理
 * @author lid
 *
 */
@Controller
@RequestMapping(value = "/manage/crossBorderMuseum")
public class CrossBorderMuseumAction {
	@Autowired
	private CrossBorderMuseumServ crossBorderMuseumServ;
	
	/**
	 * 获取跨境馆列表（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getCrossBorderMuseumList")  
	public @ResponseBody Result getCrossBorderMuseumList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return crossBorderMuseumServ.getCrossBorderMuseumList(parameter);
	}
	/**
	 * 保存/更新跨境馆对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveCrossBorderMuseum")  
	public @ResponseBody Result saveCrossBorderMuseum(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return crossBorderMuseumServ.saveCrossBorderMuseum(parameter);
	}
	/**
	 * 获取跨境馆对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getCrossBorderMuseumById")  
	public @ResponseBody Result getCrossBorderMuseumById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return crossBorderMuseumServ.getCrossBorderMuseumById(parameter);
	}
	
	/**
	 * 删除图片
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "deletePic")  
	public @ResponseBody Result deletePic(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return crossBorderMuseumServ.deletePic(parameter);
	}
	/**
	 * 删除跨境馆
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "deleteCrossBorderMuseum")  
	public @ResponseBody Result deleteCrossBorderMuseum(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return crossBorderMuseumServ.deleteCrossBorderMuseum(parameter);
	}
}

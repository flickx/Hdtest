package com.ftoul.web.goods.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface CrossBorderMuseumServ {
	/**
	 * 获取跨境馆列表（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	Result getCrossBorderMuseumList(Parameter param) throws Exception;
	/**
	 * 保存/更新跨境馆对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	Result saveCrossBorderMuseum(Parameter param) throws Exception;
	/**
	 * 获取跨境馆对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	Result getCrossBorderMuseumById(Parameter param) throws Exception;
	/**
	 * 删除图片
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	Result deletePic(Parameter param) throws Exception;
	/**
	 * 删除首页轮播图（全部删除，包括图片）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	Result deleteCrossBorderMuseum(Parameter param) throws Exception;
}

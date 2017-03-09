package com.ftoul.pc.interfaces.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface CountryService {
	/**
	 * pc端国家馆二级页面获取所有国家接口
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	public Result getCountryList(Parameter param) throws Exception;
	/**
	 * pc端国家馆二级页面获取所有有进口商品的商品分类接口
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */  
	public Result getCountryTypeList(Parameter param) throws Exception;
	/**
	 * pc端国家馆二级页面通过国家ID，国家ID,一级分类获取商品接口
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	public Result getCountryGoodsList(Parameter param) throws Exception;
}

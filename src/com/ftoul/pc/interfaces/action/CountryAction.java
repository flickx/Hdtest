package com.ftoul.pc.interfaces.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.interfaces.service.CountryService;
import com.ftoul.web.goods.service.CrossBorderMuseumServ;

/**
 * pc端国家馆二级页面接口
 * @author lid
 *
 */
@Controller
@RequestMapping(value = "/pcInterface/country/")
public class CountryAction {
	@Autowired
	private CountryService countryService;
	@Autowired
	private CrossBorderMuseumServ crossBorderMuseumServ;
	/**
	 * pc端国家馆二级页面获取所有国家接口
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getCountryList")  
	public @ResponseBody Result getCountryList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return  crossBorderMuseumServ.getCrossBorderMuseumList(parameter);
	}
	/**
	 * pc端国家馆二级页面获取所有有进口商品的商品分类接口
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getCountryTypeList")  
	public @ResponseBody Result getCountryTypeList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return  countryService.getCountryTypeList(parameter);
	}
	/**
	 * pc端国家馆二级页面通过国家ID，国家ID,一级分类获取商品接口
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getCountryGoodsList")  
	public @ResponseBody Result getCountryGoodsList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return  countryService.getCountryGoodsList(parameter);
	}
}

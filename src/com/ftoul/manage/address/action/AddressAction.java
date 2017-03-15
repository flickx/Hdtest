package com.ftoul.manage.address.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.address.service.AddressServ;

/**
 * 用户地址操作相关类
 * @author hud
 *
 */
@Controller
@RequestMapping(value = "/manage/address")
public class AddressAction {
	
	@Autowired
	private AddressServ addressServ;
	
	/**
	 * 保存/更新用户对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveUserAddress")  
	public @ResponseBody Result saveUserAddress(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return addressServ.saveUserAddress(parameter);
	}
	
	/**
	 * 设置默认地址
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveDefaultUserAddress")  
	public @ResponseBody Result saveDefaultUserAddress(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return addressServ.saveDefaultUserAddress(parameter);
	}
	
	/**
	 * 删除地址
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "delUserAddress")  
	public @ResponseBody Result delUserAddress(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return addressServ.delUserAddress(parameter);
	}
	
	/**
	 * 获取地址
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getUserAddressById")  
	public @ResponseBody Result getUserAddressById(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return addressServ.getUserAddressById(parameter);
	}
	
	/**
	 * 获取用户地址
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getUserAddressListByUserId")  
	public @ResponseBody Result getUserAddressListByUserId(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return addressServ.getUserAddressListByUserId(parameter);
	}
	
	/**
	 * 获取省
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAllProvice")  
	public @ResponseBody Result getAllProvice(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return addressServ.getAllProvice(parameter);
	}
	
	/**
	 * 获取市
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getCityByProviceId")  
	public @ResponseBody Result getCityByProviceId(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return addressServ.getCityByProviceId(parameter);
	}
	
	/**
	 * 获取地区
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getCountyByCityId")  
	public @ResponseBody Result getCountyByCityId(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return addressServ.getCountyByCityId(parameter);
	}
	
	/**
	 * 获取街道
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getTownByCityId")  
	public @ResponseBody Result getTownByCityId(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return addressServ.getTownByCityId(parameter);
	}
	
	/**
	 * 根据用户id获取用户地址
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getUserAddressByUserId")  
	public @ResponseBody Result getUserAddressByUserId(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return addressServ.getUserAddressByUserId(parameter);
	}
	
}

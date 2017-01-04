package com.ftoul.app.action.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.address.service.AddressServ;

/**
 * 用户地址操作相关类
 * @author hud
 *
 */
@Controller("AddressAppAction")
@RequestMapping(value = "/app/address")
public class AddressAppAction {
	
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
	 * 根据主键获取地址
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
	 * 获取默认地址
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getUserDefaultAddressById")  
	public @ResponseBody Result getUserDefaultAddressById(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return addressServ.getUserDefaultAddressById(parameter);
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
	 * 获取地址列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAddress")  
	public @ResponseBody Result getAddress(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return addressServ.getAddress(parameter);
	}
	
	
	
}

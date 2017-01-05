package com.ftoul.app.action.address.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 用户地址处理的业务接口
 * @author flick
 * 2016-07-12
 */
public interface AddressAppServ {
	
	public Result saveUserAddress(Parameter param) throws Exception;

	Result saveDefaultUserAddress(Parameter param) throws Exception;

	Result delUserAddress(Parameter param) throws Exception;

	Result getUserAddressListByUserId(Parameter param) throws Exception;

	public Result getUserAddressById(Parameter param) throws Exception ;

	public Result getUserDefaultAddressById(Parameter parameter) throws Exception;
	
	public Result getAddress(Parameter param) throws Exception;
	
}

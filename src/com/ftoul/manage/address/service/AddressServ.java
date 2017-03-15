package com.ftoul.manage.address.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 用户地址处理的业务接口
 * @author flick
 * 2016-07-12
 */
public interface AddressServ {
	
	public Result saveUserAddress(Parameter param) throws Exception;

	Result saveDefaultUserAddress(Parameter param) throws Exception;

	Result delUserAddress(Parameter param) throws Exception;

	Result getUserAddressListByUserId(Parameter param) throws Exception;

	Result getAllProvice(Parameter param) throws Exception;

	Result getCityByProviceId(Parameter param) throws Exception;

	Result getCountyByCityId(Parameter param) throws Exception;

	Result getTownByCityId(Parameter param) throws Exception;

	public Result getUserAddressById(Parameter param) throws Exception ;
	
	/**
	 * 根据用户id获取用户地址
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getUserAddressByUserId(Parameter param) throws Exception;
	
}

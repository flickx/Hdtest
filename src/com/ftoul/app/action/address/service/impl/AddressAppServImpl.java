package com.ftoul.app.action.address.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.app.action.address.service.AddressAppServ;
import com.ftoul.app.vo.AddressAppVo;
import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.User;
import com.ftoul.po.UserAddress;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.address.service.AddressServ;

@Service("AddressAppServImpl")
public class AddressAppServImpl implements AddressAppServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private AddressServ addressServ;
	/**
	 * 设置默认用户地址
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveDefaultUserAddress(Parameter param) throws Exception {
		return addressServ.saveDefaultUserAddress(param);
		
	}
	
	/**
	 * 删除用户地址信息
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result delUserAddress(Parameter param) throws Exception {
		return addressServ.delUserAddress(param);
	}
	
	/**
	 * 获取用户地址列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getUserAddressListByUserId(Parameter param) throws Exception {
		Result result = addressServ.getUserAddressListByUserId(param);
		List<UserAddress> list = (List<UserAddress>)result.getObj();
		List<AddressAppVo> addressList = new ArrayList<AddressAppVo>();
		for (int i = 0; i < list.size(); i++) {
			UserAddress userAddress = list.get(i);
			AddressAppVo addressVo = new AddressAppVo();
			addressVo.setId(userAddress.getId());
			addressVo.setProvince(userAddress.getCounty());
			addressVo.setCity(userAddress.getCity());
			addressVo.setCounty(userAddress.getCounty());
			addressVo.setAddress(userAddress.getAddress());
			addressVo.setDefulat(userAddress.getDefulat());
			addressVo.setName(userAddress.getName());
			addressVo.setConsignee(userAddress.getConsignee());
			addressVo.setTel(userAddress.getTel());
			addressVo.setCreateTime(userAddress.getCreateTime());
			addressList.add(addressVo);
		}
		return ObjectToResult.getResult(addressList);
	}
	
	/**
	 * 获取地址列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getAddress(Parameter param) throws Exception {
		return addressServ.getAddress(param);
	}
	
	/**
	 * 通过主键获取地址
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getUserAddressById(Parameter param) throws Exception {
		return addressServ.getUserAddressById(param);
	}

	/**
	 * 获取用户的默认地址
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getUserDefaultAddressById(Parameter param)
			throws Exception {
		Result result = addressServ.getUserDefaultAddressById(param);
		UserAddress userAddress = (UserAddress)result.getObj();
		AddressAppVo appVo = new AddressAppVo();
		appVo.setId(userAddress.getId());
		appVo.setName(userAddress.getName());
		appVo.setProvince(userAddress.getProvince());
		appVo.setCounty(userAddress.getCounty());
		appVo.setCity(userAddress.getCity());
		appVo.setTel(userAddress.getTel());
		appVo.setConsignee(userAddress.getConsignee());
		appVo.setDefulat(userAddress.getDefulat());
		appVo.setAddress(userAddress.getAddress());
		return ObjectToResult.getResult(appVo);
	}
	
	/**
	 * 保存/更新用户地址对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveUserAddress(Parameter param) throws Exception {
		UserAddress userAddress = (UserAddress) JSONObject.toBean((JSONObject) param.getObj(),UserAddress.class);
		userAddress.setAddress(new String(userAddress.getAddress().getBytes("ISO-8859-1"),"UTF-8"));
		userAddress.setName(new String(userAddress.getName().getBytes("ISO-8859-1"),"UTF-8"));
		userAddress.setConsignee(new String(userAddress.getConsignee().getBytes("ISO-8859-1"),"UTF-8"));
		userAddress.setDefulat(param.getKey());
		User user = param.getUserToken().getUser();
		userAddress.setUser(user);
		Object res;
		String defaultFlag = userAddress.getDefulat();
		if("true".equals(defaultFlag)){
			hibernateUtil.execSql("update user_address set defulat='false',modify_time='"+ new DateStr().toString() +"' where user_id='"+user.getId()+"' and state='1'");
		}
		
		if(Common.isNull(userAddress.getId())){
			userAddress.setUser(user);
			userAddress.setCreateTime(new DateStr().toString());
			userAddress.setState("1");
			res = hibernateUtil.save(userAddress);
		}else{
			userAddress.setModifyTime(new DateStr().toString());
			userAddress.setState("1");
			res = hibernateUtil.update(userAddress);
		}
		return ObjectToResult.getResult(res);
	}

}

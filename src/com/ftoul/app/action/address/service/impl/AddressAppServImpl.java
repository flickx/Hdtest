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
import com.ftoul.web.manage.user.service.WebUserServ;

@Service("AddressAppServImpl")
public class AddressAppServImpl implements AddressAppServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private AddressServ addressServ;
	
	@Autowired
	private WebUserServ webUserServ;
	/**
	 * 设置默认用户地址
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveDefaultUserAddress(Parameter param) throws Exception {
		String hql = "from UserAddress where state = '1' and user.id = '" + param.getUserToken().getUser().getId()+"'" ;
		List<Object> list = hibernateUtil.hql(hql);
		UserAddress userAddress = new UserAddress();
		for (int i = 0; i < list.size(); i++) {
			userAddress = (UserAddress) list.get(i);
			userAddress.setModifyTime(new DateStr().toString());
			userAddress.setDefulat("false");
			hibernateUtil.update(userAddress);
		}
		UserAddress defaultUserAddress = (UserAddress) hibernateUtil.find(UserAddress.class, param.getId()+"");
		defaultUserAddress.setDefulat("true");
		defaultUserAddress.setModifyTime(new DateStr().toString());
		Object res = hibernateUtil.update(defaultUserAddress);
		return ObjectToResult.getResult(res);
		
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
		if(null!=userAddress){
			appVo.setId(userAddress.getId());
			appVo.setName(userAddress.getName());
			appVo.setProvince(userAddress.getProvince());
			appVo.setCounty(userAddress.getCounty());
			appVo.setCity(userAddress.getCity());
			appVo.setTel(userAddress.getTel());
			appVo.setConsignee(userAddress.getConsignee());
			appVo.setDefulat(userAddress.getDefulat());
			appVo.setAddress(userAddress.getAddress());
		}
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
		if(null!=userAddress.getAddress()){
			userAddress.setAddress(userAddress.getAddress());	
		}
		if(null!=userAddress.getName()){
			userAddress.setName(userAddress.getName());
		}
		if(null!=userAddress.getConsignee()){
			userAddress.setConsignee(userAddress.getConsignee());
		}
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

	@Override
	public Result getUserInfoById(Parameter param) throws Exception {
		Result result = addressServ.getUserDefaultAddressById(param);
		UserAddress userAddress = (UserAddress)result.getObj();
		Result res = webUserServ.getUserById(param);
		User user = (User)res.getObj();
		AddressAppVo appVo = new AddressAppVo();
		if(null!=userAddress){
			appVo.setId(userAddress.getId());
			appVo.setName(userAddress.getName());
			appVo.setProvince(userAddress.getProvince());
			appVo.setCounty(userAddress.getCounty());
			appVo.setCity(userAddress.getCity());
			appVo.setTel(userAddress.getTel());
			appVo.setConsignee(userAddress.getConsignee());
			appVo.setDefulat(userAddress.getDefulat());
			appVo.setAddress(userAddress.getAddress());
		}
		if(null!=user){
			appVo.setUsername(user.getUsername());
			appVo.setNickname(user.getNickname());
			appVo.setHeadImg(user.getPic());
			appVo.setCardId(user.getCardId());
			appVo.setBirthday(user.getBirth());
			appVo.setEmail(user.getEmail());
			appVo.setRealName(user.getName());
			appVo.setSex(user.getSex());
		}
		return ObjectToResult.getResult(appVo);
	}

}

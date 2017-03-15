package com.ftoul.manage.address.service.impl;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.address.service.AddressServ;
import com.ftoul.po.UserAddress;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("AddressServImpl")
public class AddressServImpl implements AddressServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 保存/更新用户地址对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveUserAddress(Parameter param) throws Exception {
		UserAddress userAddress = (UserAddress) JSONObject.toBean((JSONObject) param.getObj(),UserAddress.class);
		Object res;
		if(Common.isNull(userAddress.getId())){
			userAddress.setCreateTime(new DateStr().toString());
			if("true".equals(userAddress.getDefulat())){
				userAddress.setDefulat("1");
			}else{
				userAddress.setDefulat("0");
			}
			userAddress.setState("1");
			res = hibernateUtil.save(userAddress);
		}else{
			userAddress.setModifyTime(new DateStr().toString());
			res = hibernateUtil.update(userAddress);
		}
		return ObjectToResult.getResult(res);
	}
	
	/**
	 * 设置默认用户地址
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveDefaultUserAddress(Parameter param) throws Exception {
		String hql = "from UserAddress where state = '1' and user.id = '" + param.getId()+"'" ;
		List<Object> list = hibernateUtil.hql(hql);
		UserAddress userAddress = new UserAddress();
		for (int i = 0; i < list.size(); i++) {
			userAddress = (UserAddress) list.get(i);
			userAddress.setModifyTime(new DateStr().toString());
			userAddress.setDefulat("0");
			hibernateUtil.update(userAddress);
		}
		
		UserAddress defaultUserAddress = (UserAddress) hibernateUtil.find(UserAddress.class, param.getObj()+"");
		defaultUserAddress.setDefulat("1");
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
		Integer num = hibernateUtil.execHql("update UserAddress set state = '0' where id in (" + StrUtil.getIds(param.getId()) + ")");
		return ObjectToResult.getResult(num);
	}
	
	/**
	 * 获取用户地址列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getUserAddressListByUserId(Parameter param) throws Exception {
		String hql = "from UserAddress where state = '1'" ;
		List<Object> list = hibernateUtil.hql(hql);
		for (int i = 0; i < list.size(); i++) {
			UserAddress userAddress = (UserAddress) list.get(i);
			if("1".equals(userAddress.getDefulat())){
				userAddress.setDefulat("true");
			}else{
				userAddress.setDefulat("false");
			}
		}
		return ObjectToResult.getResult(list);
	}
	
	/**
	 * 获取省列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getAllProvice(Parameter param) throws Exception {
		String hql = "from JPositionProvice";
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}
	
	/**
	 * 获取市列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getCityByProviceId(Parameter param) throws Exception {
		String hql = "from JPositionCity where provinceId ='"+param.getId()+"'";
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}
	
	/**
	 * 获取区列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getCountyByCityId(Parameter param) throws Exception {
		String hql = "from JPositionCounty where cityId ='"+param.getId()+"'";
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}
	
	/**
	 * 获取街道列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getTownByCityId(Parameter param) throws Exception {
		String hql = "from JPositionTown where countyId ='"+param.getId()+"'";
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}

	/**
	 * 获取地址
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getUserAddressById(Parameter param) throws Exception {
		UserAddress userAddress = (UserAddress) hibernateUtil.find(UserAddress.class, param.getId() + "");
		if ("1".equals(userAddress.getDefulat())) {
			userAddress.setDefulat("true");
		} else {
			userAddress.setDefulat("false");
		}
		return ObjectToResult.getResult(userAddress);
	}

	@Override
	public Result getUserAddressByUserId(Parameter param) throws Exception {
		String hql = "from UserAddress where state = '1' and user.id = '" + param.getId()+"'" ;
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}

}

package com.ftoul.web.address.service.impl;

import java.util.ArrayList;
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
import com.ftoul.web.address.service.AddressServ;
import com.ftoul.web.vo.AddressVo;
import com.ftoul.po.JPositionCity;
import com.ftoul.po.JPositionCounty;
import com.ftoul.po.JPositionProvice;
import com.ftoul.po.User;
import com.ftoul.po.UserAddress;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("AddressWebServImpl")
public class AddressServImpl implements AddressServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
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
		Integer num = hibernateUtil.execHql("update UserAddress set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}
	
	/**
	 * 获取用户地址列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getUserAddressListByUserId(Parameter param) throws Exception {
		String hql = "from UserAddress where state = '1' and user.id='"+param.getUserToken().getUser().getId()+"'" ;
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}
	
	/**
	 * 获取地址列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getAddress(Parameter param) throws Exception {
		List<AddressVo> result = new ArrayList<AddressVo>();
		String hql = "from JPositionProvice";
		List<Object> proviceList = hibernateUtil.hql(hql);
		for (int i = 0; i < proviceList.size(); i++) {
			AddressVo proviceVo = new AddressVo();
			JPositionProvice provice = (JPositionProvice) proviceList.get(i);
			proviceVo.setText(provice.getProviceName());
			proviceVo.setValue(provice.getProviceId());
			hql = "from JPositionCity where provinceId ='"+provice.getProviceId()+"'";
			List<Object> cityList = hibernateUtil.hql(hql);
			List<AddressVo> cityResult = new ArrayList<AddressVo>();
			for (int j = 0; j < cityList.size(); j++) {
				AddressVo cityVo = new AddressVo();
				JPositionCity city = (JPositionCity) cityList.get(j);
				cityVo.setText(city.getCityName());
				cityVo.setValue(city.getCityId());
				hql = "from JPositionCounty where cityId ='"+city.getCityId()+"'";
				List<Object> countryList = hibernateUtil.hql(hql);
				List<AddressVo> countryResult = new ArrayList<AddressVo>();
				for (int k = 0; k < countryList.size(); k++) {
					AddressVo countryVo = new AddressVo();
					JPositionCounty country = (JPositionCounty) countryList.get(k);
					countryVo.setText(country.getCountyName());
					countryVo.setValue(country.getCountyId());
					countryResult.add(countryVo);
				}
				cityVo.setChildren(countryResult);
				cityResult.add(cityVo);
			}
			proviceVo.setChildren(cityResult);
			result.add(proviceVo);
		}
		return ObjectToResult.getResult(result);
	}
	
	/**
	 * 通过主键获取地址
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getUserAddressById(Parameter param) throws Exception {
		UserAddress userAddress = (UserAddress) hibernateUtil.find(UserAddress.class, param.getId() + "");
		return ObjectToResult.getResult(userAddress);
	}

	/**
	 * 获取用户的默认地址
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getUserDefaultAddressById(Parameter param)
			throws Exception {
		UserAddress address = (UserAddress) hibernateUtil.hqlFirst("from UserAddress where defulat='true' and state= '1' and user.id='"+param.getUserToken().getUser().getId()+"'");
		return ObjectToResult.getResult(address);
	}
	
	/**
	 * 保存/更新用户地址对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveUserAddress(Parameter param) throws Exception {
		UserAddress userAddress = (UserAddress) JSONObject.toBean((JSONObject) param.getObj(),UserAddress.class);
		userAddress.setDefulat(param.getKey());
		User user = param.getUserToken().getUser();
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
			res = hibernateUtil.update(userAddress);
		}
		return ObjectToResult.getResult(res);
	}

}

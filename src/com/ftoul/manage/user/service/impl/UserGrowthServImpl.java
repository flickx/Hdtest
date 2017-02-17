package com.ftoul.manage.user.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.user.service.UserGrowthServ;
import com.ftoul.po.UserGrowth;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("UserGrowthServImpl")
public class UserGrowthServImpl implements UserGrowthServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public Result saveUserGrowth(Parameter param) throws Exception {
		UserGrowth userGrowth = (UserGrowth) JSONObject.toBean((JSONObject) param.getObj(),UserGrowth.class);
		String hql = "from UserGrowth";
		List<Object> list = hibernateUtil.hql(hql);
		Object res;
		if(list.size()<=0){
			res = hibernateUtil.save(userGrowth);
		}else{
			res = hibernateUtil.update(userGrowth);
		}
		return ObjectToResult.getResult(res);
	}

	@Override
	public Result getUserGrowth(Parameter param) throws Exception {
		String hql = "from UserGrowth";
		UserGrowth userGrowth = (UserGrowth)hibernateUtil.hqlFirst(hql);
		return ObjectToResult.getResult(userGrowth);
	}

}

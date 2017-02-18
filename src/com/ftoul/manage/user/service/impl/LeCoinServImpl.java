package com.ftoul.manage.user.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.user.service.LecoinServ;
import com.ftoul.po.Lecoin;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("LeCoinServImpl")
public class LeCoinServImpl implements LecoinServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public Result saveLecoin(Parameter param) throws Exception {
		Lecoin lecoin = (Lecoin) JSONObject.toBean((JSONObject) param.getObj(),Lecoin.class);
		String hql = "from Lecoin";
		List<Object> list = hibernateUtil.hql(hql);
		Object res;
		if(list.size()<=0){
			lecoin.setCreateTime(new DateStr().toString());
			res = hibernateUtil.save(lecoin);
		}else{
			lecoin.setModifyTime(new DateStr().toString());
			res = hibernateUtil.update(lecoin);
		}
		return ObjectToResult.getResult(res);
	}

	@Override
	public Result getLecoin(Parameter param) throws Exception {
		String hql = "from Lecoin";
		Lecoin lecoin = (Lecoin)hibernateUtil.hqlFirst(hql);
		return ObjectToResult.getResult(lecoin);
	}

}

package com.ftoul.manage.user.service.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.user.service.UserGradeServ;
import com.ftoul.po.UserGrade;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("UserGradeServImpl")
public class UserGradeServImpl implements UserGradeServ{
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public Result getUserGradeListPage(Parameter param) throws Exception {
		String hql = "from UserGrade where state = '1'";
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}

	@Override
	public Result saveUserGrade(Parameter param) throws Exception {
		UserGrade userGrade = (UserGrade) JSONObject.toBean((JSONObject) param.getObj(),UserGrade.class);
		Object res;
		if(Common.isNull(userGrade.getId())){
			userGrade.setCreateTime(new DateStr().toString());
			userGrade.setState("1");
			res = hibernateUtil.save(userGrade);
		}else{
			UserGrade newUserGrade = (UserGrade) hibernateUtil.find(UserGrade.class, userGrade.getId());
			newUserGrade.setName(userGrade.getName());
			newUserGrade.setPic(userGrade.getPic());
			newUserGrade.setRangeStart(userGrade.getRangeStart());
			newUserGrade.setRangeEnd(userGrade.getRangeEnd());
			newUserGrade.setDiscountRate(userGrade.getDiscountRate());
			newUserGrade.setDescription(userGrade.getDescription());
			newUserGrade.setModifyTime(new DateStr().toString());
			res = hibernateUtil.update(newUserGrade);
		}
		return ObjectToResult.getResult(res);
	}

	@Override
	public Result getUserGradeById(Parameter param) throws Exception {
		UserGrade userGrade = (UserGrade) hibernateUtil.find(UserGrade.class, param.getId() + "");
		return ObjectToResult.getResult(userGrade);
	}

	@Override
	public Result delUserGrade(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update UserGrade set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}

	@Override
	public Result delUserGradePic(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update UserGrade set pic = '' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}

}

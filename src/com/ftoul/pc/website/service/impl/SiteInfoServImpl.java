package com.ftoul.pc.website.service.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.ftoul.common.Common;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.website.service.SiteInfoServ;
import com.ftoul.po.SiteInfo;
import com.ftoul.util.hibernate.HibernateUtil;

public class SiteInfoServImpl implements SiteInfoServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public Result getSiteInfo(Parameter parameter) throws Exception {
		String hql = "from SiteInfo where 1 = 1";
		Object o = hibernateUtil.hqlFirst(hql);
		return ObjectToResult.getResult(o);
	}

	@Override
	public Result saveSiteInfo(Parameter param) throws Exception {
		Object res;
		SiteInfo siteInfo = (SiteInfo) JSONObject.toBean((JSONObject) param.getObj(),SiteInfo.class);
		String hql = "from SiteInfo where 1 = 1";
		Object o = hibernateUtil.hqlFirst(hql);
		if (o==null) {
			res = hibernateUtil.save(siteInfo);
		}else{
			res = hibernateUtil.update(siteInfo);
		}
		return ObjectToResult.getResult(res);
	}

}

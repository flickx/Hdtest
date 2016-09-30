package com.ftoul.manage.kdNiaoCode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.kdNiaoCode.service.KdniaoCodeServ;
import com.ftoul.util.hibernate.HibernateUtil;
@Service("KdniaoCodeServiceImpl")
public class KdniaoCodeServImpl implements KdniaoCodeServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * 后台分页查看快递鸟
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getKdniaoCodeList(Parameter parameter) throws Exception {
		String hql = " from KdniaoCode where 1=1 " +parameter.getWhereStr();
		Page page = hibernateUtil.hqlPage(hql, parameter.getPageNum(), parameter.getPageSize());
		return ObjectToResult.getResult(page);
	}
	@Override
	public Result getkdniaoCodeListPage(Parameter parameter) throws Exception {
		String hql = " from KdniaoCode where 1=1 and nam like '%" +parameter.getWhereStr() + "%'";
		Page page = hibernateUtil.hqlPage(hql, parameter.getPageNum(), parameter.getPageSize());
		return ObjectToResult.getResult(page);
	}

}

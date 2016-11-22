package com.ftoul.web.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.logistics.service.LogisticsServ;
import com.ftoul.po.KdniaoCode;
import com.ftoul.po.LogisticsCompany;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("WebLogisticsServImpl")
public class LogisticsServImpl implements LogisticsServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 后台查看物流公司
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getLogisticsCompany(Parameter parameter) throws Exception {
		String hql = " from LogisticsCompany where 1 = 1 ";
		List<Object> companyList = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(companyList);
	}
	
	/**
	 * 后台分页查看物流公司
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getLogisticsCompanyPage(Parameter param) throws Exception {
		String hql = " from LogisticsCompany where 1 = 1 "+param.getWhereStr();
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 后台保存物流公司
	 * @param param Parameter对象
	 * @return 返回结果
	 */
	@Override
	public Result saveLogisticsCompanyJoinList(Parameter parameter)throws Exception {
		Object res="数据已存在";
		String paramIds = parameter.getId()+"";
		String[] Ids =  paramIds.substring(1, paramIds.length()-1).split(",");
		for(String id : Ids){
			LogisticsCompany logisticsCompanyObj = (LogisticsCompany)hibernateUtil.find(LogisticsCompany.class, id.trim());
			if(logisticsCompanyObj == null){
				KdniaoCode kdniaoCode = (KdniaoCode)hibernateUtil.find(KdniaoCode.class, Integer.parseInt(id.trim()));
				LogisticsCompany logisticsCompany = new LogisticsCompany();
				logisticsCompany.setId(kdniaoCode.getId().toString());
				logisticsCompany.setCode(kdniaoCode.getCode());
				logisticsCompany.setName(kdniaoCode.getNam());
				logisticsCompany.setKdType(kdniaoCode.getKdType());
				res=hibernateUtil.save(logisticsCompany);
			}
			
		}
		return ObjectToResult.getResult(res);
	}
	
}

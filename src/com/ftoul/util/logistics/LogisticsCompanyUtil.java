package com.ftoul.util.logistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.util.hibernate.HibernateUtil;

@Component
public class LogisticsCompanyUtil {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 后台查看物流公司
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	public Result getLogisticsCompany(Parameter parameter) throws Exception {
		String hql = " from LogisticsCompany where 1 = 1 ";
		List<Object> companyList = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(companyList);
	}
	

}

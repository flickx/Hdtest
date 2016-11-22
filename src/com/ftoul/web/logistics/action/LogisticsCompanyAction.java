package com.ftoul.web.logistics.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.util.logistics.LogisticsCompanyUtil;

/**
 * 物流管理
 * @author HuDong
 *
 */
@Controller
@RequestMapping(value = "/web/logistics")
public class LogisticsCompanyAction {

	@Autowired
	private LogisticsCompanyUtil logisticsCompanyUtil; 
	
	/**
	 * 获取物流列表
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getLogisticsCompany")  
	public @ResponseBody Result getLogisticsCompany(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return logisticsCompanyUtil.getLogisticsCompany(parameter);
	}
	
	
}

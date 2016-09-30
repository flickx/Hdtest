package com.ftoul.manage.logistics.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.logistics.service.LogisticsServ;

/**
 * 物流管理
 * @author HuDong
 *
 */
@Controller
@RequestMapping(value = "/manage/logistics")
public class LogisticsAction {

	@Autowired
	private LogisticsServ logisticsServ; 
	
	/**
	 * 获取物流列表
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getLogisticsCompany")  
	public @ResponseBody Result getLogisticsCompany(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return logisticsServ.getLogisticsCompany(parameter);
	}
	
	/**
	 * 获取物流分页列表
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getLogisticsCompanyPage")  
	public @ResponseBody Result getLogisticsCompanyPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return logisticsServ.getLogisticsCompanyPage(parameter);
	}
	/**
	 * 保存物流信息
	 * @author wenyujie
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="saveLogisticsCompanyJoinList")
	public @ResponseBody Result saveLogisticsCompanyJoinList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return logisticsServ.saveLogisticsCompanyJoinList(parameter);
	}
}

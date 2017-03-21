package com.ftoul.businessManage.shopAfterService.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.businessManage.shopAfterService.service.ShopAfterServiceServ;
import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 售后申请单管理
 * @author HuDong
 *
 */
@Controller
@RequestMapping(value = "/businessManage/shopAfterService")
public class ShopAfterServiceAction {

	@Autowired
	private ShopAfterServiceServ afterServiceServ;
	
	/**
	 * 获取售后申请列表（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAfterListPage")  
	public @ResponseBody Result getAfterListPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.getAfterListPage(parameter);
	}
	
	/**
	 * 获取售后申请列表（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAfterScheduleDetail")  
	public @ResponseBody Result getAfterScheduleDetail(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.getAfterScheduleDetail(parameter);
	}
	
	/**
	 * 审核售后申请
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "auditAfterService")  
	public @ResponseBody Result auditAfterService(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.auditAfterService(parameter);
	}
	
	
	/**
	 * 设置清关状态
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "modifyClearCustom")  
	public @ResponseBody Result modifyClearCustom(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.modifyClearCustom(parameter);
	}
	
	/**
	 * 订单一键发货
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveAfterServiceLogisticsCompany")  
	public @ResponseBody Result saveAfterServiceLogisticsCompany(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.saveAfterServiceLogisticsCompany(parameter);
	}
	
	/**
	 * 修改申请售后状态
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveScheduleStatic")  
	public @ResponseBody Result saveScheduleStatic(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.saveScheduleStatic(parameter);
	}

}

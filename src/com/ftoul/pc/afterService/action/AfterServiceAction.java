package com.ftoul.pc.afterService.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.afterService.service.AfterServiceServ;
import com.ftoul.util.logistics.LogisticsUtil;

/**
 * 申请售后单管理
 * @author HuDong
 *
 */
@Controller("PcAfterServiceAction")
@RequestMapping(value = "/pc/afterService")
public class AfterServiceAction {

	@Autowired
	private AfterServiceServ afterServiceServ;
	@Autowired
	private LogisticsUtil logisticsUtil; 
	/**
	 * 获取需要申请售后列表
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAfterListByUserId")  
	public @ResponseBody Result getAfterListByUserId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.getAfterListByUserId(parameter);
	}
	
	/**
	 * 获取售后进度列表
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAfterSchedulePage")  
	public @ResponseBody Result getAfterSchedulePage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.getAfterSchedulePage(parameter);
	}
	
	/**
	 * 保存售后申请
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveAfter")  
	public @ResponseBody Result saveAfter(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.saveAfter(parameter);
	}
	

	/**
	 * 获取售后申请
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAfterSchedule")  
	public @ResponseBody Result getAfterSchedule(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.getAfterSchedule(parameter);
	}
	
	/**
	 * 申请售后凭证上传
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception
	 */
	@RequestMapping(value = "afterServicePicUpload")
	public @ResponseBody Result afterServicePicUpload(String param, HttpServletRequest request)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.afterServicePicUpload(parameter, request);
	}
	
	/**
	 * 申请售后用户发货
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception
	 */
	@RequestMapping(value = "saveSendGoods")
	public @ResponseBody Result saveSendGoods(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.saveSendGoods(parameter);
	}
	
	/**
	 * 获取商家发货的物流信息
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception
	 */
	@RequestMapping(value = "getAfterLogistics")
	public @ResponseBody Result getAfterLogistics(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.getAfterLogistics(parameter);
	}
	
	/**
	 * 更新状态
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception
	 */
	@RequestMapping(value = "updateAfterScheduleStatic")
	public @ResponseBody Result updateAfterScheduleStatic(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.updateAfterScheduleStatic(parameter);
	}
	
	/**
	 * 进入售后页面获取商品信息
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception
	 */
	@RequestMapping(value = "getAfterGoods")
	public @ResponseBody Result getAfterGoods(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.getAfterGoods(parameter);
	}
	
	/**
	 * 获取物流列表
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getLogisticsCompany")  
	public @ResponseBody Result getLogisticsCompany(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return logisticsUtil.getLogisticsCompany(parameter);
	}
	
	/**
	 * 取消售后申请
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception
	 */
	@RequestMapping(value = "cancelAfter")
	public @ResponseBody Result cancelAfter(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return afterServiceServ.cancelAfter(parameter);
	}
	
}

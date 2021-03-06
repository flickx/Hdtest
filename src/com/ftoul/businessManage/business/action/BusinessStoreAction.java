package com.ftoul.businessManage.business.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.businessManage.business.service.BusinessStoreServ;
import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 
 * @author wenyujie
 * 商家
 *
 */
@Controller
@RequestMapping(value="/businessManage/businessStore")
public class BusinessStoreAction {
	@Autowired BusinessStoreServ businessStoreServ;
	/**
	 * 获取商家资料数据
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getBusinessPage")
	public @ResponseBody Result getBusinessPage(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessStoreServ.getBusinessPage(parameter);
	}
	/**
	 * 保存店铺详情
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="saveBusinessStoreSummary")
	public @ResponseBody Result saveBusinessStoreSummary(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessStoreServ.saveBusinessStoreSummary(parameter);
	}
	/**
	 * 保存店铺logo
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="saveBusinessStorePic")
	public @ResponseBody Result saveBusinessStorePic(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessStoreServ.saveBusinessStorePic(parameter);
	}
}

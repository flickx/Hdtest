package com.ftoul.web.open.demo.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.open.demo.service.KDServ;

/**
 * 快递公司相关类（demo用）
 * @author flick
 *
 */
@Controller
@RequestMapping(value = "/web/open/demo")
public class KDAction {

	@Autowired
	private KDServ kDServ;
	/**
	 * 获取快递公司信息
	 * @return
	 */
	@RequestMapping(value = "getKDInfo")  
	public @ResponseBody Result getKDInfo(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return kDServ.getKDInfo(parameter);
	}
	/**
	 * 获取快递公司列表
	 * @return
	 */
	@RequestMapping(value = "getKDInfoList")  
	public @ResponseBody Result getKDInfoList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return kDServ.getKDInfoList(parameter);
	}
	/**
	 * 获取快递公司列表（带分页）
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getKDInfoListPage")  
	public @ResponseBody Result getKDInfoListPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		if(parameter.getPageNum() == null){
			parameter.setPageNum(1);
		}
//		throw new Exception("abaaa");
//		int i = 0/0;
		return kDServ.getKDInfoListPage(parameter);
	}
	/**
	 * 获取物流信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getKdStatic")  
	public @ResponseBody Result getKdStatic(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return kDServ.getKdStatic(parameter);
	}
}

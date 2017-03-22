package com.ftoul.pc.website.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.website.service.SiteInfoServ;

@Controller
@RequestMapping("/pc/siteInfo/")
public class SiteInfoAction {
	@Autowired
	private SiteInfoServ siteInfoServ; 
	/**
	 * 保存站点信息
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("saveSiteInfo")
	public @ResponseBody Result saveSiteInfo(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return siteInfoServ.saveSiteInfo(parameter);
	}
	/**
	 * 获取站点信息
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("getSiteInfo")
	public @ResponseBody Result getSiteInfo(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return siteInfoServ.getSiteInfo(parameter);
	}
	
	/**
	 * 删除logo图片
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping("deleteLogoPic")
	public @ResponseBody Result deleteLogoPic(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return siteInfoServ.deleteLogoPic(parameter);
	}
	/**
	 * 删除头部背景图片
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping("deleteTopBgPic")
	public @ResponseBody Result deleteTopBgPic(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return siteInfoServ.deleteTopBgPic(parameter);
	}
}

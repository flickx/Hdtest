package com.ftoul.pc.website.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 网站站点业务接口
 * @author LiDing
 * 2017-03-21
 */
public interface SiteInfoServ {
	/**
	 * 获取站点信息
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	Result getSiteInfo(Parameter parameter) throws Exception;
	
	/**
	 * 保存站点信息
	 * @author LiDing
	 * @return
	 * @throws Exception
	 */
	Result saveSiteInfo(Parameter parameter) throws Exception;
}

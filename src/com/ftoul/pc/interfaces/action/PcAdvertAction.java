package com.ftoul.pc.interfaces.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.interfaces.service.PcAdvertService;

/**
 * pc广告接口
 * @author LiDing
 * 2017-02-16
 */
@Controller
@RequestMapping(value = "/pcInterface/advert/")
public class PcAdvertAction {

	@Autowired
	private PcAdvertService advertServ; 
	
	/**
	 * 获取指定位置广告信息
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAdvert")  
	public @ResponseBody Result getAdvert(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return advertServ.getAdvert(parameter);
	}
}

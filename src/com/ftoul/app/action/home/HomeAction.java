/**
 * 
 */
package com.ftoul.app.action.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.home.service.HomeServ;

/**
 * @author 李丁
 * @date:2016年8月15日 下午3:42:16
 * @类说明 : 前台首页
 */
@Controller("AppHomeAction")
@RequestMapping(value = "/app/home")
public class HomeAction {
	@Autowired
	private HomeServ homeServ;
	
	/**
	 * 获取首页轮播图列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getIndexCarouselList") 
	public @ResponseBody Result getIndexCarouselList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return homeServ.getIndexCarouselList(parameter);
	}
	/**
	 * 获取server time
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getServerTime") 
	public @ResponseBody Result getServerTime(String param) throws Exception{
		Long resTime = System.currentTimeMillis();
		Result result = ObjectToResult.getResult(resTime);
		return result;
	}
	/**
	 * 插入用户短信记录
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "insertSmsInfo") 
	public @ResponseBody Result insertSmsInfo(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return homeServ.insertSmsInfo(parameter);
	}
}

package com.ftoul.manage.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.user.service.LecoinServ;

/**
 * 乐币管理action
 * @author yzw
 *
 */
@Controller
@RequestMapping(value = "/manage/lecoin")
public class LeCoinAction {

	@Autowired
	private LecoinServ lecoinServ;
	
	/**
	 * 添加乐币参数
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveLecoin")  
	public @ResponseBody Result saveLecoin(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return lecoinServ.saveLecoin(parameter);
	}
	/**
	 * 查询乐币
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getLecoin")  
	public @ResponseBody Result getLecoin(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return lecoinServ.getLecoin(parameter);
	}
	
}

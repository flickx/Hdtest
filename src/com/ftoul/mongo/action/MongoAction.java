package com.ftoul.mongo.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.mongo.service.MongoDbServ;
import com.ftoul.web.address.service.AddressServ;

/**
 * mongoDb测试类
 * @author flick
 *
 */
@Controller
@RequestMapping(value = "/mongoDb")
public class MongoAction {
	
	@Autowired
	private MongoDbServ mongoDbServ;
	
	/**
	 * 新增学生
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveStudent")  
	public @ResponseBody Result saveStudent(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return mongoDbServ.saveStudent(parameter);
	}
	/**
	 * 根据ID获取学生
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getStudent")  
	public @ResponseBody Result getStudent(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return mongoDbServ.getStudent(parameter);
	}
	/**
	 * 获取所有学生
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getStudentList")  
	public @ResponseBody Result getStudentList(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return mongoDbServ.getStudentList(parameter);
	}
	
}

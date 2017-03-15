package com.ftoul.mongo.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * mongodb测试接口
 * @author flick
 * 2016-07-12
 */
public interface MongoDbServ {
	/**
	 * 新增学生
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Result saveStudent(Parameter param) throws Exception;
	/**
	 * 根据ID获取学生
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Result getStudent(Parameter param) throws Exception;
	/**
	 * 获取所有学生
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Result getStudentList(Parameter param) throws Exception;
	
}

package com.ftoul.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.mongo.po.Student;
import com.ftoul.mongo.service.MongoDbServ;
import com.ftoul.util.mongodb.MongoDbUtil;
@Service("MongoDbServImpl")
public class MongoDbServImpl implements MongoDbServ {

	@Autowired
	MongoDbUtil<?> mongoDbUtil;
	/**
	 * 新增学生
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result saveStudent(Parameter param) throws Exception {
		Student student = (Student) Common.jsonToBean(param.getObj().toString(), Student.class);
		mongoDbUtil.insert(student);
		return ObjectToResult.getResult(student);
	}
	/**
	 * 根据ID获取学生
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result getStudent(Parameter param) throws Exception {
		Student student = (Student) mongoDbUtil.find(param.getId(), Student.class);
		System.out.println("id:"+student.getId()+",name:"+student.getName()+",age:"+student.getAge());
		return ObjectToResult.getResult(student);
	}
	/**
	 * 获取所有学生
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result getStudentList(Parameter param) throws Exception {
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) mongoDbUtil.getList(null,Student.class);
		for (Student student : list) {
			System.out.println("id:"+student.getId()+",name:"+student.getName()+",age:"+student.getAge());
		}
		return ObjectToResult.getResult(list);
	}
	

}

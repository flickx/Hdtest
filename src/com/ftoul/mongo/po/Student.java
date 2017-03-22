package com.ftoul.mongo.po;

import java.io.Serializable;

import com.ftoul.util.redis.RedisId;

@SuppressWarnings("serial")
public class Student implements Serializable{
	
	@RedisId
	private String id;
	private String name;
	private Integer age;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	

}

package com.ftoul.util.mongodb.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.ftoul.common.StrUtil;
import com.ftoul.util.mongodb.MongoDbUtil;
@Service("MongoDbUtilImpl")
public class MongoDbUtilImpl implements MongoDbUtil<T> {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void insert(Object object) {
		String collectionName = StrUtil.firstToLower(object.getClass().getSimpleName());
		mongoTemplate.insert(object, collectionName);  
	}

	@Override
	public Object find(Object id,Class<?> c) {
		String collectionName = StrUtil.firstToLower(c.getSimpleName());
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), c,collectionName);
	}

	@Override
	public List<?> getList(Map<String, Object> params,Class<?> c) {
		String collectionName = StrUtil.firstToLower(c.getSimpleName());
		return mongoTemplate.findAll(c, collectionName);
	}

	@Override
	public void update(Map<String, Object> params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createCollection(String collectionName) {
		mongoTemplate.createCollection(collectionName);
		
	}

	@Override
	public void del(Object id,Class<?> c) {
		String collectionName = StrUtil.firstToLower(c.getSimpleName());
		mongoTemplate.remove(new Query(Criteria.where("id").is(id)),c,collectionName);
	}

}

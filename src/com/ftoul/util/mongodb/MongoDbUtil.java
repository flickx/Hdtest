package com.ftoul.util.mongodb;

import java.util.List;
import java.util.Map;

import com.ftoul.common.Filters;
import com.ftoul.common.Page;

public interface MongoDbUtil{
	//添加  
    public void insert(Object object);    
    //根据条件查找  
    public Object find(Object id,Class<?> c);   
    //查找所有
    public List<?> getList(Map<String, Object> params,Class<?> c);
    //查找所有  
    public Page getListPage(Class<?> c,Integer pageNum, Integer pageSize,String sidx,String sord,Filters... filters);  
    //修改  
    public void update(Map<String,Object> params);   
    //创建集合  
    public void createCollection(String collectionName);  
    //根据条件删除  
    public void del(Object id,Class<?> c);  
}

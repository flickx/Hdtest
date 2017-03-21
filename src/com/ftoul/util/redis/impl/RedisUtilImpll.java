package com.ftoul.util.redis.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftoul.util.redis.RedisUtil;

@Repository("RedisUtilImpll")
public class RedisUtilImpll implements RedisUtil {
//	public class RedisUtilImpll {
	@Autowired
    protected RedisTemplate<String, String> redisTemplate;
//	/**
@SuppressWarnings("unused")
	//	 * 保存对象
//	 * @param o 需要保存的对象
//	 * @return ID
//	 */
	@Override
	public String save(Object obj) {
		String id = null;
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			HashOperations<String, String,String> hashOper = redisTemplate.opsForHash();  
			Class<?> objClass = obj.getClass();
			Field[] fields = objClass.getDeclaredFields();
			Field idField = null;
			Boolean isHaveId = false;
			for (Field field : fields) {
				if(field.isAnnotationPresent(com.ftoul.util.redis.RedisId.class)){
					idField = field;
					isHaveId = true;
					break;
				}
			}
			if(!isHaveId){
				if(fields.length>0){
					Field field = fields[0];
					idField = field;
				}
			}
			idField.setAccessible(true);
			id = idField.get(obj)+"";
			if(id == null)
				idField.set(obj, uuid);
			ObjectMapper mapper = new ObjectMapper();
			hashOper.put(obj.getClass().getSimpleName(),id, mapper.writeValueAsString(obj));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
//
//	/**
//	 * 更新对象
//	 * @param o 需要更新的对象
//	 * @return 更新后的对象
//	 */
//	@Override
//	public Object update(final String id,final Object o) {
//		return redisTemplate.execute(new RedisCallback<Object>() {  
//            public Object doInRedis(RedisConnection connection)  
//                    throws DataAccessException {  
//            	byte[] key  = id.getBytes();
//            	byte[] name = SerializeUtil.serialize(o);
//                connection.set(key, name);
//                return o;
//            }  
//        });  
//	}
//
	/**
	 * 通过ID查询对象
	 * @param id 检索ID
	 * @return 查出的对象
	 */
	@Override
	public Object get(String id ,Class<?> c) {
		HashOperations<String, String,String> hashOper = redisTemplate.opsForHash();
		ObjectMapper mapper = new ObjectMapper();
		Object returnObj = null;
		try {
			returnObj = mapper.readValue(hashOper.get(c.getSimpleName(),id), c);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return returnObj;
//         c.cast(valueOper.get(id)); 
	}
	
	/**
	 * 获取列表
	 * @param c 对象
	 * @return 查出的对象
	 */
    public List<Object> getList(Class<?> c){
    	HashOperations<String, String,String> hashOper = redisTemplate.opsForHash();
    	Set<String> set = hashOper.keys(c.getSimpleName());
    	List<Object> returnList = new ArrayList<Object>();
    	try {
	    	for (String string : set) {
	//			System.out.println(string);
	    		ObjectMapper mapper = new ObjectMapper();
	    		Object returnObj = mapper.readValue(hashOper.get(c.getSimpleName(),string), c);
	    		returnList.add(returnObj);
    		}
    	} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return returnList;
    }
	/**
	 * 通过ID删除对象
	 * @param id 检索ID
	 * @return 删除的对象
	 */
	@Override
	public void delete(String id,Class<?> c) {
		HashOperations<String, String,String> hashOper = redisTemplate.opsForHash();
		if(hashOper.hasKey(c.getSimpleName(), id)){
			hashOper.delete(c.getSimpleName(), id);
		}
	}
	/**
	 * 通过ID删除对象
	 * @param id 检索ID
	 * @return 删除的对象
	 */
	@Override
	public void drop(Class<?> c) {
		HashOperations<String, String,String> hashOper = redisTemplate.opsForHash();  
        RedisOperations<String, ?>  redisOperations  = hashOper.getOperations();  
        redisOperations.delete(c.getSimpleName());
	}
	/**
	 * 获取对象总数
	 * @param id 检索ID
	 * @return 删除的对象
	 */
	public Long count(Class<?> c){
		HashOperations<String, String,String> hashOper = redisTemplate.opsForHash();
		return hashOper.size(c.getSimpleName());
	}

}

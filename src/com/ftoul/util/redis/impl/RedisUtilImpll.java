package com.ftoul.util.redis.impl;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.ftoul.util.redis.RedisUtil;
import com.ftoul.util.redis.SerializeUtil;

@Repository("RedisUtilImpll")
//public class RedisUtilImpll implements RedisUtil {
	public class RedisUtilImpll {
//	@Autowired
//    protected RedisTemplate<Serializable, Serializable> redisTemplate;
//	/**
//	 * 保存对象
//	 * @param o 需要保存的对象
//	 * @return ID
//	 */
//	@Override
//	public String save(final String id,final Object o) {
//		return redisTemplate.execute(new RedisCallback<String>() {  
//            public String doInRedis(RedisConnection connection)  
//                    throws DataAccessException {  
//            	byte[] key  = id.getBytes();
//            	byte[] name = SerializeUtil.serialize(o);
//            	connection.setNX(key, name);
//            	return id;
//            }  
//        });  
//	}
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
//	/**
//	 * 通过ID查询对象
//	 * @param id 检索ID
//	 * @return 查出的对象
//	 */
//	@Override
//	public Object get(final String id ,final Class c) {
//		return redisTemplate.execute(new RedisCallback<Object>() {  
//            public Object doInRedis(RedisConnection connection)  
//                    throws DataAccessException { 
//            	byte[] key = id.getBytes();
//            	if (connection.exists(key)) {
//            		byte[] value = connection.get(key);
//            		return SerializeUtil.unserialize(value,c);
//            	}
//            	return null; 
//            }  
//        });  
//	}
//	/**
//	 * 通过ID删除对象
//	 * @param id 检索ID
//	 * @return 删除的对象
//	 */
//	@Override
//	public Serializable delete(final String id) {
//		return redisTemplate.execute(new RedisCallback<Serializable>() {  
//	        public Serializable doInRedis(RedisConnection connection) {  
//	            connection.del(id.getBytes());  
//	            return id;  
//	        }  
//	    });  
//	}

}

package com.ftoul.util.redis;

import java.util.List;

public interface RedisUtil
{

	/**
	 * 保存对象
	 * @param o 需要保存的对象
	 * @return ID
	 */
    public String save(Object o);
    
    /**
	 * 通过ID查询对象
	 * @param id 检索ID
	 * @return 查出的对象
	 */
    public Object get(String id ,Class<?> c);
    
    /**
	 * 获取列表
	 * @param c 对象
	 * @return 查出的对象
	 */
    public List<Object> getList(Class<?> c);
    /**
	 * 通过ID删除对象
	 * @param id 检索ID
	 * @return 删除的对象
	 */
    public void delete(String id,Class<?> c);
    /**
	 * 删除对象集合
	 * @param id 检索ID
	 * @return 删除的对象
	 */
	public void drop(Class<?> c);
	 /**
	 * 获取对象总数
	 * @param id 检索ID
	 * @return 删除的对象
	 */
	public Long count(Class<?> c);
   

    
}
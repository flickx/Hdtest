package com.ftoul.util.redis;

import java.io.Serializable;

public interface RedisUtil
{

	/**
	 * 保存对象
	 * @param o 需要保存的对象
	 * @return ID
	 */
    public Serializable save(String id, Object o);
    
    /**
	 * 更新对象
	 * @param o 需要更新的对象
	 * @return 更新后的对象
	 */
    public Object update(final String id,final Object o);
    
    /**
	 * 通过ID查询对象
	 * @param id 检索ID
	 * @return 查出的对象
	 */
    public Object get(String id,Class c);
    /**
	 * 通过ID删除对象
	 * @param id 检索ID
	 * @return 删除的对象
	 */
    public Serializable delete(String id);
   

    
}
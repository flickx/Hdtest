package com.ftoul.util.hibernate;

import java.io.Serializable;
import java.util.List;

import com.ftoul.common.Page;

public interface HibernateUtil
{

	/**
	 * 保存对象
	 * @param o 需要保存的对象
	 * @return ID
	 */
    public Serializable save(Object o);
    
    /**
	 * 更新对象
	 * @param o 需要更新的对象
	 * @return 更新后的对象
	 */
    public Object update(Object o);
    
    /**
	 * 通过ID查询对象
	 * @param c 对象类名.class
	 * @param id 检索ID
	 * @return 查出的对象
	 */
    public Object find(Class<?> c , Serializable id);
    
    /**
	 * 通过HQL执行查询
	 * @param hql HQL语句
	 * @return 查出的结果集
	 */
    public List<Object> hql(String hql);
    /**
	 * 通过HQL执行查询指定记录条数
	 * @param hql HQL语句
	 * @param begin 起点
	 * @param limit 最大记录数	
	 * @return 查出的结果集
	 */
    public List<Object> hqlLimit(String hql,int begin,int limit);
    /**
	 * 通过HQL执行查询第一条对象
	 * @param hql HQL语句
	 * @return 查出的结果集
	 */
    public Object hqlFirst(String hql);

    /**
	 * 通过HQL执行查询
	 * @param hqla HQL语句
	 * @return 查出的结果集
	 */
    public List<Object[]> hqla(String hql);
    
    /**
	 * 通过HQL执行查询(分页)
	 * @param hql HQL语句
	 * @param pageNum 第几页
	 * @param pageSize 多少数据一页
	 * @return 查出的结果集
	 */
    public Page hqlPage(String countSql,String hql, Integer pageNum, Integer pageSize);
    
    /**
	 * 通过SQL执行查询
	 * @param sql SQL语句
	 * @return 查出的结果集
	 */
    public List<Object[]> sql(String sql);
    
    /**
	 * 通过SQL执行查询（分页）
	 * @param sql HQL语句
	 * @param pageNum 第几页
	 * @param pageSize 多少数据一页
	 * @return 查出的结果集
	 */
    public Page sqlPage(String countSql,String sql, Integer pageNum, Integer pageSize);
    
    /**
	 * 通过SQL执行查询
	 * @param Sql SQL语句
	 * @return 查出的结果集转换成实体对象
	 */
    public List<Object> sqlObject(String sql, Class<?> c);
    /**
	 * 通过SQL执行DDL
	 * @param sql SQL语句
	 * @return 执行成功条数
	 */
    public int execSql(String sql);
    /**
	 * 通过HQL执行DDL
	 * @param hql SQL语句
	 * @return 执行成功条数
	 */
    public int execHql(String hql);

    
}
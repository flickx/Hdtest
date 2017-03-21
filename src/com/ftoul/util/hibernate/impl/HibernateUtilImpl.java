package com.ftoul.util.hibernate.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ftoul.common.Page;
import com.ftoul.util.hibernate.HibernateUtil;

@Repository("HibernateUtilImpl")
public class HibernateUtilImpl implements HibernateUtil
{
	@Autowired
	SessionFactory sessionFactory;
	/**
	 * 保存对象
	 * @param o 需要保存的对象
	 * @return ID
	 */
	@Override
    public Serializable save(Object o){
    	return sessionFactory.getCurrentSession().save(o);
    }
    
	/**
	 * 更新对象
	 * @param o 需要更新的对象
	 * @return 更新后的对象
	 */
	@Override
    public Object update(Object o){
		return sessionFactory.getCurrentSession().merge(o);
    }
    
	/**
	 * 通过ID查询对象
	 * @param c 对象类名.class
	 * @param id 检索ID
	 * @return 查出的对象
	 */
	@Override
    public Object find(Class<?> c , Serializable id){
    	return sessionFactory.getCurrentSession().get(c, id);
    }
    
	/**
	 * 通过HQL执行查询
	 * @param hql HQL语句
	 * @return 查出的结果集
	 */
	@SuppressWarnings("unchecked")
	@Override
    public List<Object> hql(String hql){
		return sessionFactory.getCurrentSession().createQuery(hql).setCacheable(true).list();
    }
	
	/**
	 * 通过HQL执行查询第一条对象
	 * @param hql HQL语句
	 * @return 查出的结果集
	 */
	@Override
    public Object hqlFirst(String hql){
		@SuppressWarnings("unchecked")
		List<Object> resList = sessionFactory.getCurrentSession().createQuery(hql).list();
		if(resList == null ||resList.size() == 0)
			return null;
		else
			return resList.get(0);
    }
	
	/**
	 * 通过HQL执行查询
	 * @param hqla HQL语句
	 * @return 查出的结果集
	 */
	@Override
    public List<Object[]> hqla(String hql){
    	Session session = null;
		session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Object[]> objList = session.createQuery(hql).setCacheable(true).list();
		session.close();
		session = null;
    	return objList;
    }
	
	/**
	 * 通过HQL执行查询(分页)
	 * @param hql HQL语句
	 * @param pageNum 第几页
	 * @param pageSize 多少数据一页
	 * @return 查出的结果集
	 */
	@Override
	public Page hqlPage(String countSql,String hql, Integer pageNum, Integer pageSize){
		Long count = 0l;
		Page page = new Page();
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		if(countSql == null){
			countSql = "select count(*) " + hql;
			count = ((Long) sessionFactory.getCurrentSession().createQuery(countSql).uniqueResult());
		}else{
			count = ((Long) sessionFactory.getCurrentSession().createSQLQuery(countSql).uniqueResult());
		}
		
		page.setCount(count);
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setCacheable(true);
		//设置每页显示多少个，设置多大结果。  
        query.setMaxResults(pageSize);  
        //设置起点  
        query.setFirstResult((pageNum-1)*pageSize); 
//        if(query.list()!=null)
//			count = query.list().size();
		page.setMaxPage((int)(Math.ceil(((double)count)/pageSize)));
        @SuppressWarnings("unchecked")
		List<Object> objList = query.list();
        page.setObjList(objList);
    	return page;
    }
	
	/**
	 * 通过SQL执行查询
	 * @param Sql SQL语句
	 * @return 查出的结果集
	 */
	@SuppressWarnings("unchecked")
	@Override
    public List<Object[]> sql(String sql){
    	return sessionFactory.getCurrentSession().createSQLQuery(sql).list();
    }
    /**
	 * 通过SQL执行查询（分页）
	 * @param sql HQL语句
	 * @param pageNum 第几页
	 * @param pageSize 多少数据一页
	 * @return 查出的结果集
	 */
	@Override
    public Page sqlPage(String countSql,String sql, Integer pageNum, Integer pageSize){
		Page page = new Page();
		Long count = 0l;
		if(countSql == null){
			countSql = "select count(*) " + sql.substring(sql.indexOf("from"));
			count = ((BigInteger)sessionFactory.getCurrentSession().createSQLQuery(countSql).uniqueResult()).longValue();
		}else{
			count = ((BigInteger)sessionFactory.getCurrentSession().createSQLQuery(countSql).uniqueResult()).longValue();
		}
//		Integer count = ((Number) sessionFactory.getCurrentSession().createSQLQuery(countSql).uniqueResult()).intValue();
		page.setCount(count);
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
//		Integer count = 0;
//		if(query.list()!=null)
//			count = query.list().size();
		page.setCount(count);
		page.setMaxPage((int)(Math.ceil(((double)count)/pageSize)));
		//设置每页显示多少个，设置多大结果。  
        query.setMaxResults(pageSize);  
        //设置起点  
        query.setFirstResult((pageNum-1)*pageSize); 
        @SuppressWarnings("unchecked")
		List<Object> objList = query.list();
        page.setObjList(objList);
    	return page;
    }
	
	/**
	 * 通过SQL执行查询
	 * @param Sql SQL语句
	 * @return 查出的结果集转换成实体对象
	 */
    @SuppressWarnings("unchecked")
	public List<Object> sqlObject(String sql, Class<?> c){
    	return sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(c).list();
    }
    
    /**
	 * 通过SQL执行DDL
	 * @param sql SQL语句
	 * @return 查出的结果集转换成实体对象
	 */
    public int execSql(String sql){
    	return sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
    }
    
    /**
	 * 通过HQL执行DDL
	 * @param hql SQL语句
	 * @return 查出的结果集转换成实体对象
	 */
    public int execHql(String hql){
    	return sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
    }

}
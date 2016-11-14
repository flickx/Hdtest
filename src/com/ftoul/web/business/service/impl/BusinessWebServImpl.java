package com.ftoul.web.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.business.service.BusinessWebServ;
/**
 * 
 * @author wenyujie
 *
 */
@Service("BusinessWebServImpl")
public class BusinessWebServImpl implements BusinessWebServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * 
	 * 得到店铺商品列表（带分页）
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getStoreGoodsPage(Parameter param) throws Exception {
		    String hql = "from Goods where state = '1' and grounding = '1' and id in (select gp.goods.id from GoodsParam gp where gp.state='1') and BusinessStore.id='"+param.getId()+"' "+param.getOrderBy();
			Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
			return ObjectToResult.getResult(page);
			
	}
	/**
	 * 
	 * 获取店铺详情以及商品统计
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getBusinessStorePage(Parameter param) throws Exception {
		    String hql = "from Goods where state = '1' and grounding = '1' and id in (select gp.goods.id from GoodsParam gp where gp.state='1') and BusinessStore.id='"+param.getId()+"' "+param.getOrderBy();
			Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
			return ObjectToResult.getResult(page);
			
	}
}

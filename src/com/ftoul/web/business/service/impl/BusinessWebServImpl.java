package com.ftoul.web.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.BusinessStoreSummary;
import com.ftoul.po.Goods;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.business.service.BusinessWebServ;
import com.ftoul.web.business.vo.BusinessVo;
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
		    String hql = "from BusinessStoreSummary where state = '1' BusinessStore.id='"+param.getId()+"' ";
		    BusinessStoreSummary businessStoreSummary = (BusinessStoreSummary) hibernateUtil.hql(hql);
		    //店铺商品总计
		    String goodsHql = "from Goods where BusinessStore.id ='"+param.getId()+"' ";
		    List<Object> ObjList =(List<Object>) hibernateUtil.hql(goodsHql);
		    //店铺最近一个月上新商品总计
		    Date endDate = new Date();
		    Calendar cl = Calendar.getInstance();
		    cl.setTime(endDate);
		    cl.add(Calendar.MONTH, -1);
		    Date startDate = cl.getTime();
		    SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		    String start = dd.format(startDate);
		    String end = dd.format(endDate);
		    String goodsMonthHql = "from Goods where BusinessStore.id ='"+param.getId()+"' and createTime >= '"+start+"' and createTime <= '"+end+"'";
		    List<Object> ObjMonthList =(List<Object>) hibernateUtil.hql(goodsMonthHql);
		    //店铺商品销量总计
		    String goodsSaleHql="select sum(saleNumber) from GoodsParam where BusinessStore.id = '"+param.getId()+"'";
		    Integer saleNumber=hibernateUtil.execSql(goodsSaleHql);
		    
		    BusinessVo businessVo=new BusinessVo();
		    businessVo.setGoodsNum(ObjList.size());
		    businessVo.setGoodsMonthNum(ObjMonthList.size());
		    businessVo.setGoodsSaleNum(saleNumber);
			businessVo.setBusinessStoreId(businessStoreSummary.getBusinessStore().getId());
			businessVo.setStoreName(businessStoreSummary.getBusinessStore().getStoreName());
			businessVo.setPic(businessStoreSummary.getBusinessStore().getPic());
			businessVo.setVerifyTime(businessStoreSummary.getBusinessStore().getVerifyTime());
			businessVo.setSummary(businessStoreSummary.getSummary());
			return ObjectToResult.getResult(businessVo);
	}
}

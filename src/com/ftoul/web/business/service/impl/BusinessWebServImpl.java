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
import com.ftoul.po.BusinessStore;
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
		    String hql = "from Goods where state = '1' and grounding = '1' and id in (select gp.goods.id from GoodsParam gp where gp.state='1') and shopId='"+param.getId()+"' "+param.getOrderBy();
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
			//店铺详情
		    BusinessStore businessStore = (BusinessStore) hibernateUtil.find(BusinessStore.class, param.getId().toString());
		    String summaryHql = "select bss.summary from business_store_summary as bss where bss.state = '1' and bss.store_id='"+param.getId()+"' ";
		    List<Object[]> businessStoreSummaryList =hibernateUtil.sql(summaryHql);
		    //店铺商品总计
		    String goodsHql = "from Goods where state = '1' and grounding = '1' and shopId ='"+param.getId()+"' ";
		    List<Object> ObjList =(List<Object>) hibernateUtil.hql(goodsHql);
		    //店铺最近一个月上新商品总计
		    Date endDate = new Date();
		    Calendar cl = Calendar.getInstance();
		    cl.setTime(endDate);
		    cl.add(Calendar.MONTH, -1);
		    Date startDate = cl.getTime();
		    SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String start = dd.format(startDate);
		    String end = dd.format(endDate);
		    String goodsMonthHql = "from Goods where state = '1' and grounding = '1' and shopId ='"+param.getId()+"' and createTime >= '"+start+"' and createTime <= '"+end+"'";
		    List<Object> ObjMonthList =(List<Object>) hibernateUtil.hql(goodsMonthHql);
		    //店铺商品销量总计
		    String goodsSaleHql="select sum(gp.sale_number) as number from goods_param as gp left join goods as g on g.id=gp.goods_id where g.shop_id = '"+param.getId()+"'";
		    List<Object[]> saleNumber=hibernateUtil.sql(goodsSaleHql);
		    //装载前台视图对象
		    BusinessVo businessVo=new BusinessVo();
		    businessVo.setGoodsNum(ObjList.size());
		    businessVo.setGoodsMonthNum(ObjMonthList.size());
		    if(saleNumber.get(0)!=null){
		    	businessVo.setGoodsSaleNum(Integer.parseInt(saleNumber.get(0)+""));
		    }
		    if(businessStore!=null){
		    	businessVo.setBusinessStoreId(businessStore.getId());
		    	businessVo.setStoreId(businessStore.getStoreId());
				businessVo.setStoreName(businessStore.getStoreName());
				businessVo.setLinkmanNumber(businessStore.getBusiness().getBusinessBase().getLinkmanNumber());
				businessVo.setPic(businessStore.getPic());
				businessVo.setVerifyTime(businessStore.getVerifyTime());
		    }
			if(businessStoreSummaryList.size()!=0){
				businessVo.setSummary(businessStoreSummaryList.get(0)+"");
		    }
			
			return ObjectToResult.getResult(businessVo);
	}
	/**
	 * 
	 * 根据商品ID获取店铺详情以及商品统计
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getBusinessStorePageByGoodsId(Parameter param) throws Exception {
			//获取店铺ID
			Goods goods=(Goods) hibernateUtil.find(Goods.class, param.getId().toString());
			//店铺详情
		    BusinessStore businessStore = (BusinessStore) hibernateUtil.find(BusinessStore.class,goods.getShopId());
//		    String summaryHql = "select bss.summary from business_store_summary as bss where bss.state = '1' and bss.store_id='"+goods.getShopId()+"' ";
//		    List<Object[]> businessStoreSummaryList =hibernateUtil.sql(summaryHql);
		    //店铺商品总计
		    String goodsHql = "from Goods where state = '1' and grounding = '1' and shopId ='"+businessStore.getId()+"' ";
		    List<Object> ObjList =(List<Object>) hibernateUtil.hql(goodsHql);
		    //店铺最近一个月上新商品总计
		    Date endDate = new Date();
		    Calendar cl = Calendar.getInstance();
		    cl.setTime(endDate);
		    cl.add(Calendar.MONTH, -1);
		    Date startDate = cl.getTime();
		    SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String start = dd.format(startDate);
		    String end = dd.format(endDate);
		    String goodsMonthHql = "from Goods where state = '1' and grounding = '1' and shopId ='"+businessStore.getId()+"' and createTime >= '"+start+"' and createTime <= '"+end+"'";
		    List<Object> ObjMonthList =(List<Object>) hibernateUtil.hql(goodsMonthHql);
		    //店铺商品销量总计
		    String goodsSaleHql="select sum(gp.sale_number) as number from goods_param as gp left join goods as g on g.id=gp.goods_id where g.state = 1 and gp.state = 1 and g.shop_id = '"+goods.getShopId()+"'";
		    List<Object[]> saleNumber=hibernateUtil.sql(goodsSaleHql);
		    //装载前台视图对象
		    BusinessVo businessVo=new BusinessVo();
		    businessVo.setGoodsNum(ObjList.size());
		    businessVo.setGoodsMonthNum(ObjMonthList.size());
		    if(saleNumber.get(0)!=null){
		    	businessVo.setGoodsSaleNum(Integer.parseInt(saleNumber.get(0)+""));
		    }
		    if(businessStore!=null){
		    	businessVo.setBusinessStoreId(businessStore.getId());
		    	businessVo.setStoreId(businessStore.getStoreId());
				businessVo.setStoreName(businessStore.getStoreName());
				businessVo.setPic(businessStore.getPic());
				businessVo.setLinkmanNumber(businessStore.getBusiness().getBusinessBase().getLinkmanNumber());
				businessVo.setVerifyTime(businessStore.getVerifyTime());
		    }
//			if(businessStoreSummaryList.size()!=0){
//				businessVo.setSummary(businessStoreSummaryList.get(0)+"");
//			}
			return ObjectToResult.getResult(businessVo);
	}
	/**
	 * 
	 * 根据商品分类得到店铺商品列表（带分页）
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getStoreGoodsPagebyStoreClassify(Parameter param) throws Exception {
		    String hql = "from Goods where state = '1' and grounding = '1' and id in (select gp.goods.id from GoodsParam gp where gp.state='1') and shopId='"+param.getId()+"' and businessClassifyId= '"+param.getParentId()+"'"+param.getOrderBy();
			Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
			return ObjectToResult.getResult(page);
			
	}
}

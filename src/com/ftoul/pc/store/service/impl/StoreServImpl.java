package com.ftoul.pc.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.store.service.StoreServ;
import com.ftoul.pc.store.vo.StoreVo;
import com.ftoul.po.BusinessClassify;
import com.ftoul.util.hibernate.HibernateUtil;
@Service("StoreServImpl")
public class StoreServImpl implements StoreServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * 
	 * 得到店铺商铺列表
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getStoreGoodsPage(Parameter param) throws Exception {
		String hql = "from BusinessClassify where state =1 and shopId = '"+param.getId()+"'";
		List<Object> list = hibernateUtil.hql(hql);
		List<StoreVo> storeVoList=new ArrayList<StoreVo>();
		for(Object object : list){
			BusinessClassify businessClassify=(BusinessClassify) object;
			StoreVo storeVo=new StoreVo();
			storeVo.setId(businessClassify.getId());
			storeVo.setName(businessClassify.getName());
			String goodsHql = "from Goods where state = '1' and grounding = '1' and id in (select gp.goods.id from GoodsParam gp where gp.state='1') and shopId='"+param.getId()+"' "+" and businessClassifyId='"+businessClassify.getId()+"'";
			Page page = hibernateUtil.hqlPage(null,goodsHql,param.getPageNum(),8);
			storeVo.setObjList(page.getObjList());
			storeVoList.add(storeVo);
		}
		return ObjectToResult.getResult(storeVoList);
	}
	/**
	 * 
	 * 根据商铺分类得到店铺商铺列表
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getStoreGoodsPageByBusinessClassify(Parameter param)
			throws Exception {
		BusinessClassify businessClassify=(BusinessClassify) hibernateUtil.find(BusinessClassify.class, param.getParentId().toString());
		StoreVo storeVo=new StoreVo();
		storeVo.setId(businessClassify.getId());
		storeVo.setName(businessClassify.getName());
		String goodsHql = "from Goods where state = '1' and grounding = '1' and id in (select gp.goods.id from GoodsParam gp where gp.state='1') and shopId='"+param.getId()+"' "+" and businessClassifyId='"+businessClassify.getId()+"'";
		Page page = hibernateUtil.hqlPage(null,goodsHql,param.getPageNum(),param.getPageSize());
		storeVo.setCount(page.getCount());
		storeVo.setPageNum(page.getPageNum());
		storeVo.setMaxPage(page.getMaxPage());
		storeVo.setPageSize(page.getPageSize());
		storeVo.setObjList(page.getObjList());
		return ObjectToResult.getResult(storeVo);
	}

}

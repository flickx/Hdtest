package com.ftoul.businessManage.goods.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.businessManage.goods.service.BusinessClassifyServ;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.po.BusinessClassify;
import com.ftoul.po.GoodsBrand;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("BusinessClassifyServImpl")
public class BusinessClassifyServImpl implements BusinessClassifyServ{

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public Result saveBusinessClassify(Parameter param) throws Exception {
		BusinessClassify businessClassify = (BusinessClassify) JSONObject.toBean((JSONObject) param.getObj(),BusinessClassify.class);
		Object res;
		if(businessClassify.getId()!=null){
			
			BusinessClassify bc = (BusinessClassify) hibernateUtil.find(BusinessClassify.class, businessClassify.getId());
			//重复性判断
			List<Object> list = hibernateUtil.hql("from BusinessClassify where state = '1' and id != '"+businessClassify.getId()+"'"+" and name = '"+businessClassify.getName()+"'"+" and shopId = '"+param.getManageToken().getBusinessStoreLogin().getBusinessStore().getId()+"'");
			if(list.size()>0){
				throw new Exception("店铺分类名称已存在，保存失败");
			}else{
				bc.setId(businessClassify.getId());
				bc.setOperateTime(new DateStr().toString());
				bc.setName(businessClassify.getName());
				res = this.hibernateUtil.update(businessClassify);
			}
		}else{
			//重复性判断
			List<Object> list = hibernateUtil.hql("from BusinessClassify where state = '1' and name = '"+businessClassify.getName()+"'"+" and shopId = '"+param.getManageToken().getBusinessStoreLogin().getBusinessStore().getId()+"'");
			if(list.size()>0){
				throw new Exception("店铺分类名称已存在，保存失败");
			}
			String hql = "from BusinessClassify where state = 1 and shopId = '"+param.getManageToken().getBusinessStoreLogin().getBusinessStore().getId()+"'";
			List<Object> classList = hibernateUtil.hql(hql);
			if(classList.size()>=5){
				throw new Exception("店铺分类最多添加5个");
			}else{
				businessClassify.setCreateTime(new DateStr().toString());
				businessClassify.setShopId(param.getManageToken().getBusinessStoreLogin().getBusinessStore().getId());
				businessClassify.setState("1");
				res = this.hibernateUtil.save(businessClassify);
			}
		} 
		return ObjectToResult.getResult(res);
	}

	@Override
	public Result delBusinessClassify(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update BusinessClassify set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}

	@Override
	public Result getBusinessClassify(Parameter param) throws Exception {
		String hql = "from BusinessClassify where state =1 and shopId = '"+param.getManageToken().getBusinessStoreLogin().getBusinessStore().getId()+"' order by createTime desc";
		Page page = hibernateUtil.hqlPage(null, hql,param.getPageNum(),param.getPageSize());
		return ObjectToResult.getResult(page);
	}

	@Override
	public Result getBusinessClassifyById(Parameter param) throws Exception {
		BusinessClassify businessClassify = (BusinessClassify) this.hibernateUtil.find(BusinessClassify.class, param.getId()+"");
		return ObjectToResult.getResult(businessClassify);
	}

	@Override
	public Result getBusinessClassifyByShopId(Parameter param) throws Exception {
		String hql = "from BusinessClassify where state =1 and shopId = '"+param.getId()+"'";
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}

}

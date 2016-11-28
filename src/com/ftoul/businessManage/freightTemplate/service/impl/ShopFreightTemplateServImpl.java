package com.ftoul.businessManage.freightTemplate.service.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.businessManage.freightTemplate.service.ShopFreightTemplateServ;
import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.po.AreaFreightTemplate;
import com.ftoul.po.ShopFreightTemplate;
import com.ftoul.util.hibernate.HibernateUtil;
/**
 * 
 * @author liding
 *
 */
@Service("ShopFreightTemplateServImpl")
public class ShopFreightTemplateServImpl implements ShopFreightTemplateServ {
	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public Result getShopFreightTemplatePage(Parameter param) throws Exception {
		String hql = "from ShopFreightTemplate where state = '1'" + param.getWhereStr() + param.getOrderBy() ;
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}

	@Override
	public Result getAreaFreightTemplateById(Parameter param) throws Exception {
		String hql = "from AreaFreightTemplate where state='1' and shopFreightTemplate.id = '" + param.getId() +"' " + param.getWhereStr() + param.getOrderBy() ;
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}

	@Override
	public Result saveShopFreightTemplate(Parameter param) throws Exception {
		Object res;
		if(param.getObj() == null){
			ShopFreightTemplate shopFreightTemplate =  new ShopFreightTemplate();
			shopFreightTemplate.setCreateTime(new DateStr().toString());
			shopFreightTemplate.setCreatePerson(param.getUserToken().getUser().getUsername());
			hibernateUtil.save(shopFreightTemplate);
			res = shopFreightTemplate;
		}else{
			ShopFreightTemplate shopFreightTemplate=(ShopFreightTemplate) JSONObject.toBean((JSONObject) param.getObj(),ShopFreightTemplate.class);			
			if(Common.isNull(shopFreightTemplate.getId())){
				shopFreightTemplate.setCreateTime(new DateStr().toString());
				shopFreightTemplate.setState("1");
				shopFreightTemplate.setShopAddress("未被配置的区域自动执行默认运费");
				shopFreightTemplate.setCreatePerson(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
				res = hibernateUtil.save(shopFreightTemplate);
			}else{
				shopFreightTemplate.setModifyPerson(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
				shopFreightTemplate.setModifyTime(new DateStr().toString());
				shopFreightTemplate.setState("1");
				res = hibernateUtil.update(shopFreightTemplate);
			}
		}
		return ObjectToResult.getResult(res);
	}

	@Override
	public Result delShopFreightTemplate(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update ShopFreightTemplate set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}

	@Override
	public Result saveShopAreaFreightTemplate(Parameter param)
			throws Exception {
		AreaFreightTemplate areaFreightTemplate=(AreaFreightTemplate) JSONObject.toBean((JSONObject) param.getObj(),AreaFreightTemplate.class);
		ShopFreightTemplate shopFreightTemplate = (ShopFreightTemplate) hibernateUtil.find(ShopFreightTemplate.class, param.getId()+"");
		if (shopFreightTemplate!=null) {
			areaFreightTemplate.setShopFreightTemplate(shopFreightTemplate);
		}
		Object res;
		if(areaFreightTemplate.getId().length()<3){
			areaFreightTemplate.setState("1");
			areaFreightTemplate.setCreateTime(new DateStr().toString());
			areaFreightTemplate.setCreatePerson(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
			res = hibernateUtil.save(areaFreightTemplate);
		}else{
			areaFreightTemplate.setModifyPerson(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
			areaFreightTemplate.setModifyTime(new DateStr().toString());
			areaFreightTemplate.setState("1");
			res=hibernateUtil.update(areaFreightTemplate);
		}
		return ObjectToResult.getResult(res);
	}

	@Override
	public Result delAreaFreightTemplateById(Parameter param)
			throws Exception {
		Integer num = hibernateUtil.execHql("update AreaFreightTemplate set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}

	@Override
	public Result getShopFreightTemplateById(Parameter param) throws Exception {
		String hql = "from ShopFreightTemplate where state='1' and id = '" + param.getId() +"' " + param.getWhereStr() + param.getOrderBy() ;
		Object o = hibernateUtil.hqlFirst(hql);
		return ObjectToResult.getResult(o);
	}

	@Override
	public Result getTemplateByArea(Parameter param) throws Exception {
		String hql = "from AreaFreightTemplate where state='1' and shopFreightTemplate.id = '" + param.getId() +"' and freightArea like '%" +param.getWhereStr() +"%'" + param.getOrderBy() ;
		Object o = hibernateUtil.hqlFirst(hql);
		return ObjectToResult.getResult(o);
	}
}

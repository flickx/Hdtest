package com.ftoul.manage.freight.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.freight.service.FreightTemplateServ;
import com.ftoul.po.AreaFreightTemplate;
import com.ftoul.po.JPositionProvice;
import com.ftoul.po.ShopFreightTemplate;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.vo.AddressVo;
/**
 * 
 * @author liding
 * 
 */
@Service("FreightTemplateServImpl")
public class FreightTemplateServImpl implements FreightTemplateServ {
	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public Result getShopFreightTemplatePage(Parameter param) throws Exception {
		String hql = "from ShopFreightTemplate where state = '1' and shopId = '"
				+ param.getId()
				+ "' "
				+ param.getWhereStr()
				+ param.getOrderBy();
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(),
				param.getPageSize());
		return ObjectToResult.getResult(page);
	}

	@Override
	public Result getAreaFreightTemplateById(Parameter param) throws Exception {
		String hql = "from AreaFreightTemplate where state='1' and shopFreightTemplate.id = '"
				+ param.getId()
				+ "' "
				+ param.getWhereStr()
				+ param.getOrderBy();
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(),
				param.getPageSize());
		return ObjectToResult.getResult(page);
	}

	@Override
	public Result saveShopFreightTemplate(Parameter param) throws Exception {
		Object res;
		if (param.getObj() == null) {
			ShopFreightTemplate s = new ShopFreightTemplate();
			s.setShopId("1");
			s.setShopAddress("未被配置的区域自动执行默认运费");
			s.setCreateTime(new DateStr().toString());
			hibernateUtil.save(s);
			res = s;
		} else {
			ShopFreightTemplate shopFreightTemplate = (ShopFreightTemplate) JSONObject
					.toBean((JSONObject) param.getObj(),
							ShopFreightTemplate.class);
			if (Common.isNull(shopFreightTemplate.getId())) {
				shopFreightTemplate.setShopId("1");
				shopFreightTemplate.setCreateTime(new DateStr().toString());
				shopFreightTemplate.setShopAddress("未被配置的区域自动执行默认运费");
				shopFreightTemplate.setState("1");
				String activety = shopFreightTemplate.getActivety();
				if ("是".equals(activety)) {
					hibernateUtil
							.execHql("update ShopFreightTemplate set activety = '否' where shopId ='1'");
				}
				res = hibernateUtil.save(shopFreightTemplate);
			} else {
				String activety = shopFreightTemplate.getActivety();
				if ("是".equals(activety)) {
					hibernateUtil
							.execHql("update ShopFreightTemplate set activety = '否' where shopId ='1'");
				}
				shopFreightTemplate.setModifyTime(new DateStr().toString());
				shopFreightTemplate.setState("1");
				res = hibernateUtil.update(shopFreightTemplate);
			}
		}
		return ObjectToResult.getResult(res);
	}

	@Override
	public Result delShopFreightTemplate(Parameter param) throws Exception {
		Integer num = hibernateUtil
				.execHql("update ShopFreightTemplate set state = '0' where id in ("
						+ StrUtil.getIds(param.getId()) + ")");
		return ObjectToResult.getResult(num);
	}

	@Override
	public Result saveShopAreaFreightTemplate(Parameter param) throws Exception {
		AreaFreightTemplate areaFreightTemplate = (AreaFreightTemplate) JSONObject
				.toBean((JSONObject) param.getObj(), AreaFreightTemplate.class);
		ShopFreightTemplate shopFreightTemplate = (ShopFreightTemplate) hibernateUtil
				.find(ShopFreightTemplate.class, param.getId() + "");
		if (shopFreightTemplate != null) {
			areaFreightTemplate.setShopFreightTemplate(shopFreightTemplate);
		}
		Object res;
		if (areaFreightTemplate.getId().length() < 3) {
			areaFreightTemplate.setState("1");
			areaFreightTemplate.setCreateTime(new DateStr().toString());
			res = hibernateUtil.save(areaFreightTemplate);
		} else {
			// AreaFreightTemplate area = (AreaFreightTemplate)
			// hibernateUtil.find(AreaFreightTemplate.class,
			// areaFreightTemplate.getId());
			areaFreightTemplate.setModifyTime(new DateStr().toString());
			areaFreightTemplate.setState("1");
			res = hibernateUtil.update(areaFreightTemplate);
		}
		return ObjectToResult.getResult(res);
	}

	@Override
	public Result delAreaFreightTemplateById(Parameter param) throws Exception {
		Integer num = hibernateUtil
				.execHql("update AreaFreightTemplate set state = '0' where id in ("
						+ StrUtil.getIds(param.getId()) + ")");
		return ObjectToResult.getResult(num);
	}

	@Override
	public Result getShopFreightTemplateById(Parameter param) throws Exception {
		String hql = "from ShopFreightTemplate where state='1' and id = '"
				+ param.getId() + "' " + param.getWhereStr()
				+ param.getOrderBy();
		Object o = hibernateUtil.hqlFirst(hql);
		return ObjectToResult.getResult(o);
	}

	@Override
	public Result getTemplateByArea(Parameter param) throws Exception {
		String hql = "from AreaFreightTemplate where state='1' and shopFreightTemplate.id = '"
				+ param.getId()
				+ "'"
				+ param.getWhereStr()
				+ param.getOrderBy();
		Object o = hibernateUtil.hqlFirst(hql);
		return ObjectToResult.getResult(o);
	}

	@Override
	public Result getProvinces(Parameter param) throws Exception {
		String hql = "from JPositionProvice";
		List<Object> proviceList = hibernateUtil.hql(hql);
		for (int i = 0; i < proviceList.size(); i++) {
			AddressVo proviceVo = new AddressVo();
			JPositionProvice provice = (JPositionProvice) proviceList.get(i);
			proviceVo.setText(provice.getProviceName());
			proviceVo.setValue(provice.getProviceId());
		}
		return ObjectToResult.getResult(proviceList);
	}

}

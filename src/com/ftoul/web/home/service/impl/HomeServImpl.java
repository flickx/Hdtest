/**
 * 
 */
package com.ftoul.web.home.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.IndexCarouselPic;
import com.ftoul.po.SmsInfo;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.home.service.HomeServ;
import com.ftoul.web.vo.UsersVO;

/**
 * @author 李丁
 * @date:2016年8月15日 下午3:43:38
 * @类说明 : 前台首页实现类
 */
@Service("HomeServImpl")
public class HomeServImpl implements HomeServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * 获取首页轮播图列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result getIndexCarouselList(Parameter param) throws Exception {
		IndexCarouselPic index = (IndexCarouselPic)JSONObject.toBean((JSONObject)param.getObj(),IndexCarouselPic.class);
		String hql = "from IndexCarouselPic where state = '1' and carouselType = '" +index.getCarouselType()+"' "+param.getOrderBy() ;
		List<Object> indexList = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(indexList);
	}
	/**
	 * 插入用户短信记录
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Result insertSmsInfo(Parameter param) throws Exception{
		Object res = null;
		if(param.getObj() == null){
			List<SmsInfo> smsInfoList = (List<SmsInfo>) JSONObject.toBean((JSONObject) param.getObj(),SmsInfo.class);
			for (SmsInfo smsInfo : smsInfoList) {
				res = hibernateUtil.save(smsInfo);
			}
		}
		return ObjectToResult.getResult(res);
	}
	
	/**
	 * app首页一级分类栏目(美妆个护，环球美食，家居生活)
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Result getAppGoodsByGoodsType(Parameter param) throws Exception{
		String typeName = param.getId().toString();
		if ("mzgh".equals(typeName)) {
			typeName = "美妆个护";
		}
		if ("hqms".equals(typeName)) {
			typeName = "环球美食";
		}
		if ("jjsh".equals(typeName)) {
			typeName = "家居生活";
		}
		String hql1 = "from Goods where state = '1' and grounding = '1' and id in (select gp.goods.id from GoodsParam gp where gp.state='1') and goodsType1.name='"+typeName+"'  order by rand()";
		Page page = hibernateUtil.hqlPage(null,hql1, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * ios首页轮播图列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result getIosIndexCarouselList(Parameter param) throws Exception {
		String hql = "from IndexCarouselPic where state = '1' and carouselType = '" +param.getId()+"' "+param.getOrderBy() ;
		List<Object> indexList = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(indexList);
	}

}

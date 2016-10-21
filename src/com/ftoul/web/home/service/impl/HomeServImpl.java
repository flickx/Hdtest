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
import com.ftoul.po.SmsInfo;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.home.service.HomeServ;

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
		String hql = "from IndexCarouselPic where state = '1' " +param.getWhereStr() + param.getOrderBy() ;
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
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
}

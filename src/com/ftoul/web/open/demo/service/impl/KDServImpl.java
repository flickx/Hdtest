package com.ftoul.web.open.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.api.KdniaoTrackQueryAPI;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.KdniaoCode;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.open.demo.service.KDServ;

@Service("KDServImpl")
public class KDServImpl implements KDServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 获取单个快递公司
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 * @author flick
	 */
	@Override
	public Result getKDInfo(Parameter param) throws Exception {
		KdniaoCode kdniaoCode = (KdniaoCode) hibernateUtil.find(KdniaoCode.class, 1);
		return ObjectToResult.getResult(kdniaoCode);
	}
	
	/**
	 * 获取快递列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 * @author flick
	 */
	@Override
	public Result getKDInfoList(Parameter param) throws Exception {
		String hql = "from KdniaoCode where id<=5";
		List<Object> list = hibernateUtil.hql(hql);
//		System.out.println(0/0);
		return ObjectToResult.getResult(list);
	}
	
	/**
	 * 获取快递列表（带分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 * @author flick
	 */
	@Override
	public Result getKDInfoListPage(Parameter param) throws Exception {
		String hql = "from KdniaoCode";
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), 20);
		return ObjectToResult.getResult(page);
	}

	/**
	 * 获取快递当前状态
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 * @throws Exception 
	 */
	@Override
	public Result getKdStatic(Parameter param) throws Exception {
		// TODO Auto-generated method stub
		KdniaoTrackQueryAPI kdniaoTrackQueryAPI = new KdniaoTrackQueryAPI();
		String res = kdniaoTrackQueryAPI.getOrderTracesByJson("SF", "606102226173");
		return ObjectToResult.getResult(res);
	}

}

package com.ftoul.pc.interfaces.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.interfaces.service.PcAdvertService;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("PcAdvertServiceImpl")
public class PcAdvertServiceImpl implements PcAdvertService{
	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * 获取pc指定位置广告对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	public Result getAdvert(Parameter param) throws Exception{
		String hql = "from IndexCarouselPic where state = '1' and active = '启用' and carouselType = '" +param.getId()+"' ";
		List<Object> indexList = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(indexList);
	}
}

package com.ftoul.util.goodsEvent;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.DateUtil;
import com.ftoul.util.hibernate.HibernateUtil;

@Component
public class GoodsEventUtil {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 根据店铺ID和活动类型获取该店是否有该类型的全场活动
	 * @param param
	 * @param eventType
	 */
	public Object getUniversalEventByShopId(String shopId,String eventType){
		String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
		return hibernateUtil.hqlFirst("from GoodsEvent where state='1' and universal ='1' and shopId ='"+shopId+"' and typeName = '"+eventType+"' and  eventBegen<='"+current+"' and eventEnd>='"+current+"'");
	}

}

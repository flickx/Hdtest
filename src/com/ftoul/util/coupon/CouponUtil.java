package com.ftoul.util.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.util.hibernate.HibernateUtil;

@Component
public class CouponUtil {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	public String getCouponType(String param){
		String name = null;
		if("1".equals(param)){
			name = "直降券";
		}else if("2".equals(param)){
			name = "满减券";
		}
		return name;
	}

}

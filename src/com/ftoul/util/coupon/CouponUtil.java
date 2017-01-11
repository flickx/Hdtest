package com.ftoul.util.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.util.hibernate.HibernateUtil;

@Component
public class CouponUtil {
	
	@Autowired
	private HibernateUtil hibernateUtil;

}

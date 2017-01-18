package com.ftoul.util.coupon;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.DateStr;
import com.ftoul.po.Coupon;
import com.ftoul.po.UserCoupon;
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
	
	/**
	 * 自动设置优惠券是否已过期
	 * @param param
	 * @throws Exception 
	 */
	public void autoSetCouponState() throws Exception{
		String currentTime = new DateStr().toString();
		List<Object> objList = hibernateUtil.hql("from UserCoupon where state='1'");
		for (Object object : objList) {
			UserCoupon userCoupon = (UserCoupon) object;
			Coupon coupon = (Coupon) hibernateUtil.find(Coupon.class, userCoupon.getCouponId());
			if(new DateStr().compareDate(currentTime, coupon.getValidEndTime())>0){
				userCoupon.setIsUsed("3");
				userCoupon.setState("0");
				userCoupon.setModifyPerson("SYSTEM");
				userCoupon.setModifyTime(new DateStr().toString());
				hibernateUtil.update(userCoupon);
			}
		}
	}

}

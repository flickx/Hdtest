package com.ftoul.util.coupon;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.DateStr;
import com.ftoul.po.Coupon;
import com.ftoul.po.UserCoupon;
import com.ftoul.util.goodsparam.GoodsParamUtil;
import com.ftoul.util.hibernate.HibernateUtil;

@Component
public class CouponUtil {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private GoodsParamUtil paramUtil;
	
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
	
	/**
	 * 获取该店铺目前所有通用优惠券
	 * @param shopId
	 */
	public List<Object> getCurrencyCouponByShopId(String shopId){
		String currentTime = new DateStr().toString();
		List<Object> objList = hibernateUtil.hql("from Coupon where state='1' and and useType='1' and validStartTime>='"+currentTime+"' and validEndTime<='"+currentTime+"'");
		return objList;
	}
	
	public void isHasCouponByGoodsParamIdAndShopId(List<Object> param,String shopId){
		//List<String> typeList = paramUtil.getGoodsTypeByGoodsParamId(paramId);
	}
	
	/**
	 * 查询此用户目前有效未使用的优惠券
	 * @param param
	 * @return
	 */
	public List<Object> getCouponByUserId(String param){
		return hibernateUtil.hql("from UserCoupon where state='1' and and isUsed='1' and userId='"+param+"'");
	}

}

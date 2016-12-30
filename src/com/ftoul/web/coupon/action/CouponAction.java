package com.ftoul.web.coupon.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.coupon.service.CouponService;

/**
 * 优惠券
 * @author hud
 *
 */
@Controller("CouponAction")
@RequestMapping(value = "/web/coupon")
public class CouponAction {
	
	@Autowired
	private CouponService couponService;
	
	/**
	 * 领取优惠券
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getCoupon")  
	public @ResponseBody Result getCoupon(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return couponService.getCoupon(parameter);
	}
	
	/**
	 * 查询拥有的优惠券
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "queryCoupon")  
	public @ResponseBody Result queryCoupon(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return couponService.queryCoupon(parameter);
	}

}

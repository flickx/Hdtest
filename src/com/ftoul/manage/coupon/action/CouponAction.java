package com.ftoul.manage.coupon.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.coupon.service.CouponService;

/**
 * 大后台优惠券
 * @author hud
 *
 */
@Controller
@RequestMapping(value = "/manage/coupon")
public class CouponAction {
	
	@Autowired
	private CouponService couponService;
	
	/**
	 * 创建优惠券
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveCoupon")  
	public @ResponseBody Result saveCoupon(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return couponService.saveCoupon(parameter);
	}
	
	/**
	 * 查询优惠券
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
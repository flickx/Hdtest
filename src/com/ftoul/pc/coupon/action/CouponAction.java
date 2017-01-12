package com.ftoul.pc.coupon.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.coupon.service.CouponService;

/**
 * PC前台优惠券
 * @author hud
 *
 */
@Controller("PcCouponAction")
@RequestMapping(value = "/pc/coupon")
public class CouponAction {
	
	@Autowired
	private CouponService couponService;
	
	/**
	 * 查询优惠券列表
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "queryCouponPage")  
	public @ResponseBody Result queryCouponPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return couponService.queryCouponPage(parameter);
	}
	
	/**
	 * 查询各种状态的优惠券列表
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "queryCouponList")  
	public @ResponseBody Result queryCouponList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return couponService.queryCouponList(parameter);
	}
	
	/**
	 * 优惠券领取
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
	 * 优惠券统计
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "queryCouponCount")  
	public @ResponseBody Result queryCouponCount(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return couponService.queryCouponCount(parameter);
	}
	
}
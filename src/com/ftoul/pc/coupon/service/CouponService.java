package com.ftoul.pc.coupon.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface CouponService {


	Result queryCouponPage(Parameter parameter) throws Exception;

	Result queryCouponCount(Parameter parameter) throws Exception;

	Result queryCouponList(Parameter parameter) throws Exception;

	Result getCoupon(Parameter parameter) throws Exception;

}

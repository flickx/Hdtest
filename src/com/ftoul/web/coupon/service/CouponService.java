package com.ftoul.web.coupon.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface CouponService {

	Result getCoupon(Parameter parameter) throws Exception;

	Result queryCoupon(Parameter parameter) throws Exception;

}

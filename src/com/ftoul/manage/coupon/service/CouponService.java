package com.ftoul.manage.coupon.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface CouponService {

	Result saveCoupon(Parameter parameter) throws Exception;

	Result queryCoupon(Parameter parameter) throws Exception;

}

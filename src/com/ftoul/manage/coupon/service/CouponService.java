package com.ftoul.manage.coupon.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface CouponService {

	Result saveCoupon(Parameter parameter) throws Exception;

	Result queryCouponPage(Parameter parameter) throws Exception;

	Result queryCouponCount(Parameter parameter) throws Exception;

	Result deleteCoupon(Parameter parameter) throws Exception;

	Result queryCouponDetail(Parameter parameter) throws Exception;

	Result isHasCouponByGoodsTypeId(Parameter parameter) throws Exception;

}

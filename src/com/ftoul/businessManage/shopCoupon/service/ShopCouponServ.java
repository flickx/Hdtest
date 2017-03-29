package com.ftoul.businessManage.shopCoupon.service;

import javax.servlet.http.HttpServletRequest;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface ShopCouponServ {

	Result saveCoupon(Parameter parameter) throws Exception;

	Result queryCouponPage(Parameter parameter) throws Exception;

	Result queryCouponCount(Parameter parameter) throws Exception;

	Result deleteCoupon(Parameter parameter) throws Exception;

	Result queryCouponDetail(Parameter parameter) throws Exception;

	Result isHasCouponByGoodsTypeId(Parameter parameter) throws Exception;

	Result isHasCouponInArrsByGoodsTypeId(Parameter parameter)throws Exception;

	Result fileUpload(Parameter parameter,HttpServletRequest request) throws Exception;

	Result sendCouponToAppointUser(Parameter parameter) throws Exception;

	Result queryUserCouponPageByCouponId(Parameter parameter) throws Exception;

}

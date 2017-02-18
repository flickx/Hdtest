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
@Controller("ManageCouponAction")
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
	 * 查询优惠券明细
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "queryCouponDetail")  
	public @ResponseBody Result queryCouponDetail(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return couponService.queryCouponDetail(parameter);
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
	
	/**
	 * 删除优惠券
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "deleteCoupon")  
	public @ResponseBody Result deleteCoupon(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return couponService.deleteCoupon(parameter);
	}
	
	/**
	 * 检测此商品分类是否已有有效优惠券
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "isHasCouponByGoodsTypeId")  
	public @ResponseBody Result isHasCouponByGoodsTypeId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return couponService.isHasCouponByGoodsTypeId(parameter);
	}
	
	


}
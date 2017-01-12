package com.ftoul.pc.coupon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.coupon.vo.CouponCount;
import com.ftoul.pc.coupon.service.CouponService;
import com.ftoul.pc.coupon.vo.CouponVo;
import com.ftoul.po.Coupon;
import com.ftoul.po.UserCoupon;
import com.ftoul.util.coupon.CouponUtil;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("PcCouponServiceImpl")
public class CouponServiceImpl implements CouponService {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private CouponUtil couponUtil;

	/**
	 * 查询所有的优惠券列表
	 */
	@Override
	public Result queryCouponPage(Parameter param) throws Exception {
		String whereStr = param.getWhereStr();
		String hql = "from Coupon where state='1' "+whereStr+"' order by creatTime desc";
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		List<Object> objList = page.getObjList();
		List<Object> voList = new ArrayList<Object>();
		for (Object object : objList) {
			Coupon coupon = (Coupon) object;
			CouponVo vo = new CouponVo();
			vo.setId(coupon.getId());
			vo.setFaceValue(coupon.getFaceValue().toString());
			vo.setName(coupon.getName());
			vo.setType(couponUtil.getCouponType(coupon.getCouponType()));
			vo.setValidEndTime(coupon.getValidEndTime());
			vo.setTargetValue(coupon.getTargetValue().toString());
			UserCoupon obj = (UserCoupon) hibernateUtil.hqlFirst("from UserCoupon where couponId='"+coupon.getId()+"' and userId='"+param.getUserToken().getUser().getId()+"' and state='1'");
			if(obj!=null){
				vo.setIsUsed("1");//已领取
			}else{
				vo.setIsUsed("0");//未领取
			}
			voList.add(vo);
		}
		page.setObjList(null);
		page.setObjList(voList);
		return ObjectToResult.getResult(page);
	}

	/**
	 * 优惠券统计
	 */
	@Override
	public Result queryCouponCount(Parameter param) throws Exception {
		String currentTime = new DateStr().toString();
		List<Object> list1 = hibernateUtil.hql("from Coupon where state='1' and couponType='1' and businessStore.id='"+param.getManageToken().getBusinessStoreLogin().getBusinessStore().getId()+"' and validStartTime>='"+currentTime+"' and validEndTime<='"+currentTime+"'");
		List<Object> list2 = hibernateUtil.hql("from Coupon where state='1' and couponType='2' and businessStore.id='"+param.getManageToken().getBusinessStoreLogin().getBusinessStore().getId()+"' and validStartTime>='"+currentTime+"' and validEndTime<='"+currentTime+"'");
		CouponCount count = new CouponCount();
		count.setCount1(list1.size());
		count.setCount2(list2.size());
		return ObjectToResult.getResult(count);
	}

	/**
	 * 查询各种状态的优惠券列表
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@Override
	public Result queryCouponList(Parameter param) throws Exception {
		couponUtil.autoSetCouponState();//设置过期的优惠券
		List<Object> objList = hibernateUtil.hql("from UserCoupon where state='1' and user_id='"+param.getUserToken().getUser().getId()+"' and isUsed='"+param.getKey()+"'");
		List<Object> voList = new ArrayList<Object>();
		for (Object object : objList) {
			UserCoupon userCoupon = (UserCoupon) object;
			Coupon coupon = (Coupon) hibernateUtil.find(Coupon.class, userCoupon.getCouponId());
			CouponVo vo = new CouponVo();
			vo.setId(coupon.getId());
			vo.setFaceValue(coupon.getFaceValue().toString());
			vo.setName(coupon.getName());
			vo.setType(couponUtil.getCouponType(coupon.getCouponType()));
			vo.setValidEndTime(coupon.getValidEndTime());
			vo.setTargetValue(coupon.getTargetValue().toString());
			vo.setIsUsed(userCoupon.getIsUsed());
			voList.add(vo);
		}
		return ObjectToResult.getResult(voList);
	}

	/**
	 * 优惠券领取
	 */
	@Override
	public Result getCoupon(Parameter param) throws Exception {
		UserCoupon userCoupon = (UserCoupon) Common.jsonToBean(param.getObj().toString(), UserCoupon.class);
		userCoupon.setUserId(param.getUserToken().getUser().getId());
		userCoupon.setIsUsed("1");
		userCoupon.setState("1");
		userCoupon.setCreatePerson(param.getUserToken().getUser().getUsername());
		userCoupon.setCreateTime(new DateStr().toString());
		hibernateUtil.save(userCoupon);
		return ObjectToResult.getResult(userCoupon);
	}

}

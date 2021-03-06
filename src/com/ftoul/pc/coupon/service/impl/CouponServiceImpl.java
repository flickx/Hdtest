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
import com.ftoul.pc.coupon.service.CouponServ;
import com.ftoul.pc.coupon.vo.CouponVo;
import com.ftoul.po.BusinessStore;
import com.ftoul.po.Coupon;
import com.ftoul.po.UserCoupon;
import com.ftoul.util.coupon.CouponUtil;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("PcCouponServiceImpl")
public class CouponServiceImpl implements CouponServ {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private CouponUtil couponUtil;
	@Autowired
	private DateStr dateStr;

	/**
	 * 查询所有的优惠券列表
	 */
	@Override
	public Result queryCouponPage(Parameter param) throws Exception {
		String currentTime = new DateStr().toString();
		String whereStr = param.getWhereStr();
		if(whereStr==null){
			whereStr = "";
		}
		String hql = "from Coupon where state='1' "+whereStr+" order by createTime desc";
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		List<?> objList = page.getObjList();
		List<Object> voList = new ArrayList<Object>();
		boolean flag = false;
		for (Object object : objList) {
			Coupon coupon = (Coupon) object;
			CouponVo vo = new CouponVo();
			vo.setId(coupon.getId());
			vo.setFaceValue((String.valueOf(coupon.getFaceValue())).substring(0,(String.valueOf(coupon.getFaceValue())).indexOf(".")));
			vo.setName(coupon.getName());
			vo.setType(couponUtil.getCouponType(coupon.getCouponType()));
			vo.setValidEndTime(coupon.getValidEndTime());
			if(dateStr.compareDate(currentTime, coupon.getValidEndTime())>0){
				flag = true;
			}
			BusinessStore store = coupon.getBusinessStore();
			if(store!=null){
				vo.setShopName(store.getStoreName());
			}
			if(coupon.getTargetValue()!=null){
				vo.setTargetValue(coupon.getTargetValue().toString());
			}
			UserCoupon obj = (UserCoupon) hibernateUtil.hqlFirst("from UserCoupon where couponId='"+coupon.getId()+"' and userId='"+param.getUserToken().getUser().getId()+"' and state='1'");
			if(flag){
				vo.setIsUsed("1");//已失效
				flag = false;
			}else{
				if(obj!=null){
					vo.setIsUsed("1");//已领取
				}else{
					vo.setIsUsed("0");//未领取
				}
			}
			voList.add(vo);
		}
		page.setObjList(null);
		page.setObjList(voList);
		return ObjectToResult.getResult(page);
	}

	/**
	 * 优惠券分类统计
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
		List<Object> objList = hibernateUtil.hql("from UserCoupon where state='1' and userId='"+param.getUserToken().getUser().getId()+"' and isUsed='"+param.getKey()+"'");
		List<Object> voList = new ArrayList<Object>();
		for (Object object : objList) {
			UserCoupon userCoupon = (UserCoupon) object;
			Coupon coupon = userCoupon.getCouponId();
			CouponVo vo = new CouponVo();
			vo.setId(coupon.getId());
			vo.setFaceValue((String.valueOf(coupon.getFaceValue())).substring(0,(String.valueOf(coupon.getFaceValue())).indexOf(".")));
			vo.setName(coupon.getName());
			vo.setType(couponUtil.getCouponType(coupon.getCouponType()));
			vo.setValidEndTime(coupon.getValidEndTime());
			vo.setShopName(coupon.getBusinessStore().getStoreName());
			if(coupon.getTargetValue()!=null){
				vo.setTargetValue(coupon.getTargetValue().toString());
			}
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

	/**
	 * 优惠券状态统计
	 */
	@Override
	public Result queryCouponStateCount(Parameter param) throws Exception {
		List<Object> list1 = hibernateUtil.hql("from UserCoupon where state='1' and isUsed='1' and userId='"+param.getUserToken().getUser().getId()+"'");
		List<Object> list2 = hibernateUtil.hql("from UserCoupon where state='1' and isUsed='2' and userId='"+param.getUserToken().getUser().getId()+"'");
		List<Object> list3 = hibernateUtil.hql("from UserCoupon where state='1' and isUsed='3' and userId='"+param.getUserToken().getUser().getId()+"'");
		CouponCount count = new CouponCount();
		count.setCount1(list1.size());
		count.setCount2(list2.size());
		count.setCount3(list3.size());
		return ObjectToResult.getResult(count);
	}

}

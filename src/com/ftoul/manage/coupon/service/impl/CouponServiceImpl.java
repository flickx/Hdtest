package com.ftoul.manage.coupon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.coupon.service.CouponService;
import com.ftoul.manage.coupon.vo.GoodsTypeVo;
import com.ftoul.po.Coupon;
import com.ftoul.po.GoodsTypeEventJoin;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("ManageCouponServiceImpl")
public class CouponServiceImpl implements CouponService {
	
	@Autowired
	private HibernateUtil hibernateUtil;

	/**
	 * 创建优惠券
	 */
	@Override
	public Result saveCoupon(Parameter param) throws Exception {
		Coupon coupon = (Coupon) Common.jsonToBean(param.getObj().toString(), Coupon.class);
		coupon.setBusinessStore(param.getManageToken().getBusinessStoreLogin().getBusinessStore());
		coupon.setState("1");
		coupon.setCreatePerson(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
		coupon.setCreateTime(new DateStr().toString());
		Object obj = hibernateUtil.save(coupon);
		List<Object> list = param.getObjList();
		if(list!=null&&list.size()>0){//如果有分类就关联分类
			for (Object object : list) {
				GoodsTypeVo vo = (GoodsTypeVo) object;
				GoodsTypeEventJoin join = new GoodsTypeEventJoin();
				join.setBusinessStore(param.getManageToken().getBusinessStoreLogin().getBusinessStore());
				join.setCreatePerson(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
				join.setCreateTime(new DateStr().toString());
				join.setState("1");
				join.setEventId(coupon.getId());
				join.setGoodsType1(vo.getType1());
				join.setGoodsType2(vo.getType2());
				join.setGoodsType3(vo.getType3());
				join.setLevel(vo.getLevel());
				hibernateUtil.save(join);
			}
		}
		return ObjectToResult.getResult(obj);
	}

	@Override
	public Result queryCoupon(Parameter param) throws Exception {
		
		return null;
	}

}

package com.ftoul.manage.coupon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.coupon.service.CouponService;
import com.ftoul.manage.coupon.vo.CouponCount;
import com.ftoul.manage.coupon.vo.CouponVo;
import com.ftoul.manage.coupon.vo.GoodsTypeVo;
import com.ftoul.po.BusinessStore;
import com.ftoul.po.Coupon;
import com.ftoul.po.GoodsTypeEventJoin;
import com.ftoul.util.coupon.CouponUtil;
import com.ftoul.util.goodsparam.GoodsParamUtil;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("ManageCouponServiceImpl")
public class CouponServiceImpl implements CouponService {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private CouponUtil couponUtil;
	@Autowired
	private GoodsParamUtil paramUtil;
	/**
	 * 创建优惠券
	 */
	@Override
	public Result saveCoupon(Parameter param) throws Exception {
		Coupon coupon = (Coupon) Common.jsonToBean(param.getObj().toString(), Coupon.class);
		BusinessStore store = (BusinessStore) hibernateUtil.find(BusinessStore.class, "1");
		coupon.setBusinessStore(store);
		coupon.setState("1");
		coupon.setCreatePerson(param.getManageToken().getLoginUser().getLoginName());
		coupon.setCreateTime(new DateStr().toString());
		Object obj = hibernateUtil.save(coupon);
		List<Object> list = param.getObjList();
		if(list!=null&&list.size()>0){//如果有分类就关联分类
			for (Object object : list) {
				HashMap map = (HashMap) object;
				GoodsTypeEventJoin join = new GoodsTypeEventJoin();
				join.setBusinessStore(store);
				join.setCreatePerson(param.getManageToken().getLoginUser().getLoginName());
				join.setCreateTime(new DateStr().toString());
				join.setState("1");
				join.setEventId(coupon.getId());
				join.setGoodsType((String)map.get("id"));
				join.setLevel((String)map.get("level"));
				hibernateUtil.save(join);
			}
		}
		return ObjectToResult.getResult(obj);
	}

	/**
	 * 查询该店的优惠券列表
	 */
	@Override
	public Result queryCouponPage(Parameter param) throws Exception {
		String whereStr = param.getWhereStr();
		String hql = "from Coupon where state='1' "+whereStr+" and businessStore.id = '"+param.getManageToken().getBusinessStoreLogin().getBusinessStore().getId()+"' order by creatTime desc";
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		List<Object> objList = page.getObjList();
		List<Object> voList = new ArrayList<Object>();
		for (Object object : objList) {
			Coupon coupon = (Coupon) object;
			CouponVo vo = new CouponVo();
			vo.setId(coupon.getId());
			vo.setCreateTime(coupon.getCreateTime());
			vo.setFaceValue(coupon.getFaceValue().toString());
			vo.setGiveNumber(coupon.getGiveoutNum().toString());
			vo.setName(coupon.getName());
			vo.setReceiveNumber(coupon.getReceiveNum().toString());
			vo.setType(couponUtil.getCouponType(coupon.getCouponType()));
			vo.setValidEndTime(coupon.getValidEndTime());
			vo.setValidStartTime(coupon.getValidStartTime());
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
	 * 删除优惠券
	 */
	@Override
	public Result deleteCoupon(Parameter param) throws Exception {
		Coupon coupon = (Coupon) hibernateUtil.find(Coupon.class, param.getId().toString());
		if(coupon!=null){
			coupon.setState("0");
			coupon.setModifyPerson(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
			coupon.setModifyTime(new DateStr().toString());
			List<Object> typeList = hibernateUtil.hql("from GoodsTypeEventJoin where state='1' and eventId='"+coupon.getId()+"'");
			for (Object object : typeList) {
				GoodsTypeEventJoin join = (GoodsTypeEventJoin) object;
				join.setState("0");
				join.setModifyPerson(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
				join.setModifyTime(new DateStr().toString());
				hibernateUtil.update(join);
			}
		}
		hibernateUtil.update(coupon);
		return ObjectToResult.getResult(coupon);
	}

	/**
	 * 查询优惠券明细
	 */
	@Override
	public Result queryCouponDetail(Parameter param) throws Exception {
		Coupon coupon = (Coupon) hibernateUtil.find(Coupon.class, param.getId().toString());
		CouponVo vo = new CouponVo();
		if(coupon!=null){
			vo.setCreateTime(coupon.getCreateTime());
			vo.setFaceValue(coupon.getFaceValue().toString());
			vo.setCode(coupon.getCode());
			vo.setName(coupon.getName());
			vo.setType(couponUtil.getCouponType(coupon.getCouponType()));
			vo.setGiveNumber(coupon.getGiveoutNum().toString());
			vo.setValidStartTime(coupon.getValidStartTime());
			vo.setValidEndTime(coupon.getValidEndTime());
			vo.setTargetValue(coupon.getTargetValue().toString());
			List<Object> typeList = hibernateUtil.hql("from GoodsTypeEventJoin where state='1' and eventId='"+coupon.getId()+"'");
			List<Object> typeVoList = new ArrayList<Object>();
			for (Object object : typeList) {
				GoodsTypeEventJoin join = (GoodsTypeEventJoin) object;
				GoodsTypeVo typeVo = new GoodsTypeVo();
				typeVo.setLevel(join.getLevel());
				typeVoList.add(typeVo);
			}
			vo.setTypeList(typeVoList);
		}
		return ObjectToResult.getResult(vo);
	}

	/**
	 * 检测此商品分类是否已有有效优惠券
	 */
	@Override
	public Result isHasCouponByGoodsTypeId(Parameter param)
			throws Exception {
		Object vo = new Object();
		vo = "3";
		List<String> types = paramUtil.getThirdType(param.getId().toString(), param.getKey());//查询此分类下所有的第三级
		List<String> upperTypes = paramUtil.getUpperType(param.getId().toString(), param.getKey());
		List<Object> typeList = new ArrayList<Object>();
		types.add(param.getId().toString());
		upperTypes.add(param.getId().toString());
		for (String str : types) {
			typeList = hibernateUtil.hql("from GoodsTypeEventJoin where state='1' and goodsType='"+str+"'");
			if(typeList.size()>0){
				vo = "1";
				break;
			}
		}
		for (String str : upperTypes) {
			typeList = hibernateUtil.hql("from GoodsTypeEventJoin where state='1' and goodsType='"+str+"'");
			if(typeList.size()>0){
				vo = "2";
				break;
			}
		}
		return ObjectToResult.getResult(vo);
	}

}

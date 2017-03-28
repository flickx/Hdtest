package com.ftoul.businessManage.shopCoupon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ftoul.businessManage.shopCoupon.service.ShopCouponServ;
import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ExcelTools;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.businessManage.shopCoupon.vo.CouponCount;
import com.ftoul.businessManage.shopCoupon.vo.CouponVo;
import com.ftoul.businessManage.shopCoupon.vo.GoodsTypeVo;
import com.ftoul.businessManage.shopCoupon.vo.UserCouponVo;
import com.ftoul.po.BusinessStore;
import com.ftoul.po.Coupon;
import com.ftoul.po.GoodsType;
import com.ftoul.po.GoodsTypeEventJoin;
import com.ftoul.po.User;
import com.ftoul.po.UserCoupon;
import com.ftoul.util.coupon.CouponUtil;
import com.ftoul.util.goodsparam.GoodsParamUtil;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("ShopCouponServiceImpl")
public class ShopCouponServiceImpl implements ShopCouponServ {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private CouponUtil couponUtil;
	@Autowired
	private GoodsParamUtil paramUtil;
	@Autowired
	private ExcelTools excelTools;
	/**
	 * 创建优惠券
	 */
	@Override
	public Result saveCoupon(Parameter param) throws Exception {
		Coupon coupon = (Coupon) Common.jsonToBean(param.getObj().toString(), Coupon.class);
		BusinessStore store = param.getManageToken().getBusinessStoreLogin().getBusinessStore();
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
		if("2".equals(coupon.getGiveoutType())){//系统自动发放优惠券给用户
			couponUtil.autoSendCouponToUser(coupon.getId());
		}
		return ObjectToResult.getResult(obj);
	}

	/**
	 * 查询该店的优惠券列表
	 */
	@Override
	public Result queryCouponPage(Parameter param) throws Exception {
		String whereStr = param.getWhereStr();
		if(whereStr==null){
			whereStr="";
		}
		String hql = "from Coupon where state='1' "+whereStr+" and businessStore.id = '"+param.getManageToken().getBusinessStoreLogin().getBusinessStore().getId()+"' order by createTime desc";
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		List<?> objList = page.getObjList();
		List<Object> voList = new ArrayList<Object>();
		for (Object object : objList) {
			Coupon coupon = (Coupon) object;
			CouponVo vo = new CouponVo();
			vo.setId(coupon.getId());
			vo.setCreateTime(coupon.getCreateTime());
			vo.setFaceValue(coupon.getFaceValue().toString());
			vo.setGiveNumber(coupon.getGiveoutNum().toString());
			vo.setName(coupon.getName());
			if(coupon.getReceiveNum()!=null){
				vo.setReceiveNumber(coupon.getReceiveNum().toString());
			}else{
				vo.setReceiveNumber("0");
			}
			vo.setType(couponUtil.getCouponType(coupon.getCouponType()));
			vo.setValidEndTime(coupon.getValidEndTime());
			vo.setValidStartTime(coupon.getValidStartTime());
			vo.setGiveoutType(coupon.getGiveoutType());
			voList.add(vo);
		}
		page.setObjList(null);
		page.setObjList(voList);
		return ObjectToResult.getResult(page);
	}

	/**
	 * 有效直降券和满减券统计
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
			coupon.setModifyPerson(param.getManageToken().getLoginUser().getLoginName());
			coupon.setModifyTime(new DateStr().toString());
			List<Object> typeList = hibernateUtil.hql("from GoodsTypeEventJoin where state='1' and eventId='"+coupon.getId()+"'");
			for (Object object : typeList) {//先删除关联的商品分类
				GoodsTypeEventJoin join = (GoodsTypeEventJoin) object;
				join.setState("0");
				join.setModifyPerson(param.getManageToken().getLoginUser().getLoginName());
				join.setModifyTime(new DateStr().toString());
				hibernateUtil.update(join);
			}
			List<Object> userCouponList = hibernateUtil.hql("from UserCoupon where state='1' and couponId.id='"+coupon.getId()+"'");
			for (Object object : userCouponList) {//删除关联的用户
				UserCoupon userCoupon = (UserCoupon) object;
				userCoupon.setState("0");
				userCoupon.setModifyPerson(param.getManageToken().getLoginUser().getLoginName());
				userCoupon.setModifyTime(new DateStr().toString());
				hibernateUtil.update(userCoupon);
			}
		}
		hibernateUtil.update(coupon);//删除优惠券
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
			vo.setId(coupon.getId());
			vo.setCreateTime(coupon.getCreateTime());
			vo.setFaceValue(coupon.getFaceValue().toString());
			vo.setCode(coupon.getCode());
			vo.setName(coupon.getName());
			vo.setType(couponUtil.getCouponType(coupon.getCouponType()));
			vo.setGiveNumber(coupon.getGiveoutNum().toString());
			vo.setValidStartTime(coupon.getValidStartTime());
			vo.setValidEndTime(coupon.getValidEndTime());
			if(coupon.getTargetValue()!=null){
				vo.setTargetValue(coupon.getTargetValue().toString());
			}
			vo.setUseType(couponUtil.getCouponUseType(coupon.getUseType()));
			List<Object> typeList = hibernateUtil.hql("from GoodsTypeEventJoin where state='1' and eventId='"+coupon.getId()+"'");
			List<Object> typeVoList = new ArrayList<Object>();
			for (Object object : typeList) {
				GoodsTypeEventJoin join = (GoodsTypeEventJoin) object;
				GoodsTypeVo typeVo = new GoodsTypeVo();
				typeVo.setLevel(join.getLevel());
				GoodsType type = (GoodsType) hibernateUtil.find(GoodsType.class, join.getGoodsType());
				typeVo.setName(type.getName());
				typeVoList.add(typeVo);
			}
			vo.setTypeList(typeVoList);
		}
		return ObjectToResult.getResult(vo);
	}

	/**
	 * 检测此商品分类在数据库中是否已有有效优惠券
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
				vo = "1";//此分类下已经有优惠券了
				break;
			}
		}
		for (String str : upperTypes) {
			typeList = hibernateUtil.hql("from GoodsTypeEventJoin where state='1' and goodsType='"+str+"'");
			if(typeList.size()>0){
				vo = "2";//此分类上已经有优惠券了
				break;
			}
		}
		return ObjectToResult.getResult(vo);
	}
	
	/**
	 * 检测此商品分类在数据库中是否已有有效优惠券
	 */
	@Override
	public Result isHasCouponInArrsByGoodsTypeId(Parameter param)
			throws Exception {
		Object vo = new Object();
		vo = "3";
		List<String> types = paramUtil.getThirdType(param.getId().toString(), param.getKey());//查询此分类下所有的第三级
		List<String> upperTypes = paramUtil.getUpperType(param.getId().toString(), param.getKey());
		List<Object> typeList = new ArrayList<Object>();
		types.add(param.getId().toString());
		upperTypes.add(param.getId().toString());
		List<Object> list = param.getObjList();
		for (String str : types) {
			for (Object object : list) {
				HashMap map = (HashMap) object;
				String id = (String)map.get("id");
				if(str.equals(id)){
					vo = "1";//此分类下已经有设置优惠券了
					break;
				}
			}
			
		}
		for (String str : upperTypes) {
			for (Object object : list) {
				HashMap map = (HashMap) object;
				String id = (String)map.get("id");
				if(str.equals(id)){
					vo = "2";//此分类上已经有设置优惠券了
					break;
				}
			}
		}
		return ObjectToResult.getResult(vo);
	}
	
	/**
	 * 上传用户附件
	 */
	@Override
	public Result fileUpload(Parameter parameter, HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		List<MultipartFile> fileList = multipartRequest.getFiles("file_data");
		//图片文件夹名称
		List list = null;
		Map<String ,Object> map = new HashMap<String ,Object>();
		if (fileList.size()>0) {
			for (MultipartFile multipartFile : fileList) {
				list = excelTools.readXlsx(multipartFile);
			}
		}
		return ObjectToResult.getResult(list);
	}

	/**
	 * 发送优惠券给指定用户
	 */
	@Override
	public Result sendCouponToAppointUser(Parameter param) throws Exception {
		Coupon coupon = (Coupon) hibernateUtil.find(Coupon.class, param.getId().toString());
		List<Object> list = param.getObjList();
		List<String> str = (List<String>) list.get(0);
		for (String string : str) {
			User user = (User) hibernateUtil.hqlFirst("from User where state='1' and static='1' and username='"+string+"'" );
			UserCoupon userCoupon = new UserCoupon();
			userCoupon.setCouponId(coupon);
			userCoupon.setIsUsed("1");
			userCoupon.setUserId(user.getId());
			userCoupon.setCreatePerson(param.getManageToken().getLoginUser().getLoginName());
			userCoupon.setCreateTime(new DateStr().toString());
			hibernateUtil.save(userCoupon);
		}
		return ObjectToResult.getResult(coupon);
	}
	
	/**
	 * 查询优惠券下的所有用户
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@Override
	public Result queryUserCouponPageByCouponId(Parameter param)
			throws Exception {
		Page page = hibernateUtil.hqlPage(null, "from UserCoupon where state='1' and couponId.id='"+param.getId().toString()+"'", param.getPageNum(), param.getPageSize());
		List<?> objList = page.getObjList();
		List<Object> voList = new ArrayList<Object>();
		for (Object object : objList) {
			UserCoupon userCoupon = (UserCoupon) object;
			User user = (User) hibernateUtil.find(User.class, userCoupon.getUserId());
			UserCouponVo vo = new UserCouponVo();
			vo.setId(userCoupon.getId());
			vo.setTime(userCoupon.getCreateTime());
			vo.setUserName(user.getUsername());
			vo.setUseState(couponUtil.getCouponUseState(userCoupon.getIsUsed()));
			voList.add(vo);
		}
		page.setObjList(null);
		page.setObjList(voList);
		return ObjectToResult.getResult(page);
	}
	
}

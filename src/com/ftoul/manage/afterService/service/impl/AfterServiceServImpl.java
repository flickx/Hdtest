package com.ftoul.manage.afterService.service.impl;

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
import com.ftoul.manage.afterService.service.AfterServiceServ;
import com.ftoul.manage.logistics.vo.LogisticsCompanyVo;
import com.ftoul.manage.orders.vo.AfterScheduleVo;
import com.ftoul.po.AfterOpLog;
import com.ftoul.po.AfterSchedule;
import com.ftoul.po.Orders;
import com.ftoul.po.OrdersDetail;
import com.ftoul.util.afterService.AfterServiceUtil;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.orders.OrdersUtil;

@Service("AfterServiceServImpl")
public class AfterServiceServImpl implements AfterServiceServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	OrdersUtil ordersUtil;
	@Autowired
	AfterServiceUtil afterServiceUtil;

	/**
	 * 获取售后申请列表
	 */
	@Override
	public Result getAfterListPage(Parameter param) throws Exception {
		String queryStr = param.getWhereStr();
		String hql = "";
		if(queryStr!=null){
			hql = " from AfterSchedule where state='1' "+queryStr+" order by createTime desc";
		}else{
			hql = " from AfterSchedule where state='1' order by createTime desc";
		}
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		List<Object> afterList = page.getObjList();
		List<Object> voList = new ArrayList<Object>();
		for (Object object : afterList) {
			AfterSchedule after = (AfterSchedule) object;
			Orders orders = after.getOrdersDetail().getOrders();
			AfterScheduleVo afterVo = new AfterScheduleVo();
			afterVo.setId(after.getId());
			afterVo.setServiceCode(after.getServiceCode());
			afterVo.setOrderNumber(orders.getOrderNumber());
			afterVo.setOrderTime(orders.getOrderTime());
			afterVo.setScheduleStatic(afterServiceUtil.getAfterState(after.getScheduleStatic()));
			afterVo.setType(afterServiceUtil.getAfterType(after.getType()));
			afterVo.setOrderStatic(ordersUtil.getState(orders.getOrderStatic()));
			afterVo.setBackPrice(after.getBackPrice());
			afterVo.setNum(after.getNum());
			afterVo.setCreateTime(after.getCreateTime());
			if(orders.getShopId()!=null){
				afterVo.setShopName(orders.getShopId().getStoreName());
			}
			
			voList.add(afterVo);
		}
		page.getObjList().clear();
		page.setObjList(voList);
		
		return ObjectToResult.getResult(page);
	}

	@Override
	public Result getAfterScheduleDetail(Parameter param) throws Exception {
		AfterSchedule schedule = (AfterSchedule) hibernateUtil.find(AfterSchedule.class, param.getId()+"");
		Orders orders = schedule.getOrdersDetail().getOrders();
		OrdersDetail od = schedule.getOrdersDetail();
		AfterScheduleVo afterVo = new AfterScheduleVo();
		afterVo.setId(schedule.getId());
		afterVo.setServiceCode(schedule.getServiceCode());
		afterVo.setCreateTime(schedule.getCreateTime());
		afterVo.setReason(schedule.getReason());
		afterVo.setNum(schedule.getNum());
		afterVo.setBackPrice(schedule.getBackPrice());
		afterVo.setScheduleStatic(schedule.getScheduleStatic());
		afterVo.setType(schedule.getType());
		List<AfterSchedule> picSrcs = new ArrayList<AfterSchedule>();
		if(schedule.getPicSrcs()!=null){
			String[] picSrc = schedule.getPicSrcs().split(";");
			for (String src : picSrc) {
				AfterSchedule afterSchedule = new AfterSchedule();
				afterSchedule.setPicSrcs(src);
				picSrcs.add(afterSchedule);
			}
		}
		afterVo.setList(picSrcs);
		afterVo.setOrderNumber(orders.getOrderNumber());
		afterVo.setOrderTime(orders.getOrderTime());
		afterVo.setOrderStatic(ordersUtil.getState(orders.getOrderStatic()));
		
		if(orders.getOdd()!=null){
			afterVo.setOdd(orders.getOdd());
		}else{
			afterVo.setOdd("暂未发货");
		}
		
		if(orders.getLogisticsCompany()!=null){
			afterVo.setCompany(orders.getLogisticsCompany().getName());
		}else{
			afterVo.setCompany("暂未发货");
		}
		afterVo.setAddress(orders.getAddress());
		afterVo.setConsignee(orders.getConsignee());
		afterVo.setConsigneeTel(orders.getConsigneeTel());
		if(orders.getFreight()!=null){
			afterVo.setFreight(orders.getFreight().toString());
		}
		
		afterVo.setGoodsName(od.getGoodsParam().getGoods().getTitle());
		afterVo.setGoodsPicSrcs(od.getGoodsParam().getGoods().getPicSrc());
		afterVo.setSku(od.getGoodsParam().getGoods().getSkuCode());
		afterVo.setSalePrice(od.getPrice());
		afterVo.setNumber(od.getNumber());
		afterVo.setParam(od.getGoodsParam().getParamName());
		List<Object> list = hibernateUtil.hql("from AfterOpLog where state='1' and afterSchedule.id = '"+schedule.getId()+"' order by createTime desc");
		afterVo.setLogList(list);
//		double totalPrice = Double.parseDouble(od.getPrice())*Integer.parseInt(od.getNumber());
//		afterVo.setTotalPrice(String.valueOf(totalPrice));
		return ObjectToResult.getResult(afterVo);
	}

	/**
	 * 后台管理人员审核售后申请
	 */
	@Override
	public Result auditAfterService(Parameter param) throws Exception {
		AfterSchedule after = (AfterSchedule) hibernateUtil.find(AfterSchedule.class, param.getId()+"");
		AfterSchedule schedule = (AfterSchedule) Common.jsonToBean(param.getObj().toString(), AfterSchedule.class);
		after.setBackPrice(schedule.getBackPrice());
		after.setReason(schedule.getReason());
		after.setScheduleStatic(schedule.getScheduleStatic());
		after.setMsg(schedule.getMsg());
		after.setModifyTime(new DateStr().toString());
		after.setModifyPerson(param.getManageToken().getLoginUser().getId());
		Object o = hibernateUtil.update(after);
		afterServiceUtil.saveAfterOpLog(param,"【卖家】"+afterServiceUtil.getAfterState(schedule.getScheduleStatic()));
		return ObjectToResult.getResult(o);
	}
	
	/**
	 * 设置清关状态
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result modifyClearCustom(Parameter param)
			throws Exception {
		String hql = "update AfterSchedule set customsClearanceStatic ='"+param.getKey()+"' where id = '"+param.getId().toString()+"'";
		int result = hibernateUtil.execHql(hql);
		String msg;
		if("1".equals(param.getKey())){
			msg = "清关中";
		}else{
			msg = "已清关";
		}
		afterServiceUtil.saveAfterOpLog(param,"【卖家】设置清关状态为 "+msg);
		return ObjectToResult.getResult(result);
	}

	/**
	 * 一键发货
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveAfterServiceLogisticsCompany(Parameter param)
			throws Exception {
		Object obj = param.getObj();
		LogisticsCompanyVo logisticsCompanyVo = (LogisticsCompanyVo) Common.jsonToBean(obj.toString(), LogisticsCompanyVo.class);
		String hql = "update AfterSchedule set buyerLogCompany.id = '"+logisticsCompanyVo.getLogisticsCompanyID()+"', buyerLogOdd = '"+logisticsCompanyVo.getOdd()+"', buyerLogInfo = '"+logisticsCompanyVo.getLogInfo()+"', scheduleStatic = '10' where id = '"+logisticsCompanyVo.getId()+"'";
		int result = hibernateUtil.execHql(hql);
		afterServiceUtil.saveAfterOpLog(param,"【卖家】已发货");
		return ObjectToResult.getResult(result);
	}
	
	/**
	 * 修改申请售后状态
	 */
	@Override
	public Result saveScheduleStatic(Parameter param) throws Exception {
		AfterSchedule after = (AfterSchedule) hibernateUtil.find(AfterSchedule.class, param.getId()+"");
		after.setScheduleStatic(param.getKey());
		after.setModifyPerson(param.getManageToken().getLoginUser().getLoginName());
		after.setModifyTime(new DateStr().toString());
		hibernateUtil.update(after);
		afterServiceUtil.saveAfterOpLog(param,"【卖家】修改售后状态为"+afterServiceUtil.getAfterState(param.getKey()));
		return ObjectToResult.getResult(after);
	}

	
	
	
}

package com.ftoul.web.afterService.service.impl;

import java.io.Serializable;
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
import com.ftoul.po.AfterOpLog;
import com.ftoul.po.AfterSchedule;
import com.ftoul.po.OrdersDetail;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.orders.OrdersUtil;
import com.ftoul.web.afterService.service.AfterServiceServ;
import com.ftoul.web.vo.AfterScheduleVo;

@Service("WebAfterServiceServImpl")
public class AfterServiceServImpl implements AfterServiceServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired  
	OrdersUtil ordersUtil;
	
	/**
	 * 获取售后进度列表
	 */
	@Override
	public Result getOrderAfterSchedulePage(Parameter param) throws Exception {
		Page page = hibernateUtil.hqlPage("from AfterSchedule where state='1' and user.id='"+param.getUserToken().getUser().getId()+"' order by createTime desc",param.getPageNum(),param.getPageSize());
		List list = page.getObjList();
		List<AfterScheduleVo> voList = new ArrayList<AfterScheduleVo>();
		for (int i = 0; i < list.size(); i++) {
			AfterScheduleVo vo = new AfterScheduleVo();
			AfterSchedule schedule = (AfterSchedule) list.get(i);
			vo.setId(schedule.getId());
			vo.setGoodsName(schedule.getOrdersDetail().getGoodsParam().getGoods().getTitle());
			vo.setLogCompany(schedule.getLogCompany().getName());
			vo.setLogOdd(schedule.getLogOdd());
			vo.setOrderId(schedule.getOrdersDetail().getOrders().getId());
			vo.setOrderStatic(schedule.getOrdersDetail().getOrders().getOrderStatic());
			vo.setOrderTime(schedule.getOrdersDetail().getOrders().getOrderTime());
			vo.setPic(schedule.getOrdersDetail().getGoodsParam().getGoods().getPicSrc());
			vo.setBackPrice(schedule.getBackPrice());
			vo.setNum(schedule.getNum());
			vo.setScheduleStatic(schedule.getScheduleStatic());
			vo.setServiceCode(schedule.getServiceCode());
			vo.setTel(schedule.getTel());
			vo.setUserId(schedule.getUser().getId());
			voList.add(vo);
		}
		page.getObjList().clear();
		page.getObjList().addAll(voList);
		return ObjectToResult.getResult(page);
	}

	/**
	 * 保存售后申请信息
	 */
	@Override
	public Result saveAfter(Parameter param) throws Exception {
		Serializable s = null;
		AfterSchedule schedule = (AfterSchedule) Common.jsonToBean(param.getObj().toString(), AfterSchedule.class);
		AfterOpLog log = new AfterOpLog();
		if(Common.isNull(schedule.getId())){
			OrdersDetail od = (OrdersDetail) hibernateUtil.find(OrdersDetail.class, param.getId()+"");
			schedule.setOrdersDetail(od);
			schedule.setUser(param.getUserToken().getUser());
			schedule.setServiceCode(ordersUtil.getAfterServiceCode());
			schedule.setScheduleStatic("1");//用户申请售后
			schedule.setState("1");
			schedule.setCreatePerson(param.getUserId());
			schedule.setCreateTime(new DateStr().toString());
			s = hibernateUtil.save(schedule);
			log.setMsg("【用户】申请售后,售后类型为:"+ordersUtil.getAfterType(schedule.getType()));
			log.setCreatePerson(param.getManageToken().getLoginUser().getLoginName());
			log.setCreateTime(new DateStr().toString());
			log.setState("1");
		}else{
			hibernateUtil.update(schedule);
			log.setMsg("修改了申请售后内容");
		}
		log.setAfterSchedule(schedule);
		log.setUserId(param.getUserToken().getUser().getName());
		hibernateUtil.save(log);
		return ObjectToResult.getResult(s);
	}

	/**
	 * 根据主键获取售后申请
	 */
	@Override
	public Result getAfterSchedule(Parameter param) throws Exception {
		AfterSchedule after = (AfterSchedule) hibernateUtil.find(AfterSchedule.class, param.getId()+"");
		return ObjectToResult.getResult(after);
	}
	
}

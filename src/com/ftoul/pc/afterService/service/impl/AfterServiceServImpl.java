package com.ftoul.pc.afterService.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ftoul.api.KdniaoTrackQueryAPI;
import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.afterService.service.AfterServiceServ;
import com.ftoul.pc.afterService.vo.AfterScheduleVo;
import com.ftoul.pc.afterService.vo.GoodsVo;
import com.ftoul.pc.orders.vo.PcOrderVo;
import com.ftoul.po.AfterOpLog;
import com.ftoul.po.AfterSchedule;
import com.ftoul.po.BusinessStore;
import com.ftoul.po.Orders;
import com.ftoul.po.OrdersDetail;
import com.ftoul.util.afterService.AfterServiceUtil;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.orders.OrdersUtil;
import com.ftoul.web.vo.AfterLogisticsVo;
import com.ftoul.web.vo.AfterScheduleLogisticsVo;

@Service("PcAfterServiceServImpl")
public class AfterServiceServImpl implements AfterServiceServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired  
	OrdersUtil ordersUtil;
	@Autowired  
	AfterServiceUtil afterServiceUtil;
	
	/**
	 * 根据用户ID获取售后列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getAfterListByUserId(Parameter param) throws Exception {
		ordersUtil.autoCancelOrders(param);
		List<Object> ordersList = new ArrayList<Object>();
		Page page = new Page();
		page =  hibernateUtil.hqlPage(null,"from Orders where isHasChild='0' and orderStatic not in ('0','1','7','8') and user.id='"+param.getUserToken().getUser().getId()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		ordersList = page.getObjList();
		List<Object> ordersDetailList = new ArrayList<Object>();
		PcOrderVo vo;
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < ordersList.size(); i++) {
			Orders order = (Orders) ordersList.get(i);
			ordersDetailList = hibernateUtil.hql("from OrdersDetail where orders.id='"+order.getId()+"'");
			vo = ordersUtil.transformOrder(order,ordersDetailList);
			list.add(vo);
		}
		page.setObjList(null);
		page.setObjList(list);
		return ObjectToResult.getResult(page);
	}
	
	/**
	 * 获取售后进度列表
	 */
	@Override
	public Result getAfterSchedulePage(Parameter param) throws Exception {
		Page page = hibernateUtil.hqlPage(null,"from AfterSchedule where state='1' and user.id='"+param.getUserToken().getUser().getId()+"' order by createTime desc",param.getPageNum(),param.getPageSize());
//		List list = page.getObjList();
//		List<AfterScheduleVo> voList = new ArrayList<AfterScheduleVo>();
//		for (int i = 0; i < list.size(); i++) {
//			AfterScheduleVo vo = new AfterScheduleVo();
//			AfterSchedule schedule = (AfterSchedule) list.get(i);
//			vo.setId(schedule.getId());
//			vo.setGoodsTitle(schedule.getOrdersDetail().getGoodsParam().getGoods().getTitle());
//			if(schedule.getSellerLogCompany()!=null){
//				vo.setLogCompany(schedule.getSellerLogCompany().getName());
//			}
//			vo.setLogOdd(schedule.getSellerLogOdd());
//			vo.setOrderId(schedule.getOrdersDetail().getOrders().getId());
//			vo.setOrderStatic(schedule.getOrdersDetail().getOrders().getOrderStatic());
//			vo.setOrderTime(schedule.getOrdersDetail().getOrders().getOrderTime());
//			vo.setPic(schedule.getOrdersDetail().getPicSrc());
//			vo.setBackPrice(schedule.getBackPrice());
//			vo.setNum(schedule.getNum());
//			vo.setScheduleStatic(schedule.getScheduleStatic());
//			vo.setServiceCode(schedule.getServiceCode());
//			vo.setTel(schedule.getSellerTel());
//			vo.setUserId(schedule.getUser().getId());
//			vo.setSalePrice(schedule.getOrdersDetail().getPrice());
//			voList.add(vo);
//		}
//		page.getObjList().clear();
//		page.getObjList().addAll(voList);
		return ObjectToResult.getResult(page);
	}

	/**
	 * 保存售后申请信息
	 */
	@Override
	public Result saveAfter(Parameter param) throws Exception {
		AfterSchedule schedule = (AfterSchedule) Common.jsonToBean(param.getObj().toString(), AfterSchedule.class);
		AfterOpLog log = new AfterOpLog();
		if(Common.isNull(schedule.getId())){
			OrdersDetail od = (OrdersDetail) hibernateUtil.find(OrdersDetail.class, param.getId()+"");
			od.setIsAfter("1");
			schedule.setOrdersDetail(od);
			schedule.setUser(param.getUserToken().getUser());
			schedule.setServiceCode(ordersUtil.getAfterServiceCode());
			schedule.setScheduleStatic("1");//用户申请售后
			schedule.setState("1");
			schedule.setCreatePerson(param.getUserToken().getUser().getUsername());
			schedule.setCreateTime(new DateStr().toString());
			hibernateUtil.save(schedule);
			log.setScheduleStatic(schedule.getScheduleStatic());
			log.setMsg("【买家】申请售后,售后类型为:"+afterServiceUtil.getAfterType(schedule.getType()));
			log.setCreatePerson(param.getUserToken().getUser().getUsername());
			log.setCreateTime(new DateStr().toString());
			log.setState("1");
		}else{
			hibernateUtil.update(schedule);
			log.setMsg("修改了申请售后内容");
		}
		log.setAfterSchedule(schedule);
		log.setUserId(param.getUserToken().getUser().getUsername());
		hibernateUtil.save(log);
		return ObjectToResult.getResult(schedule);
	}

	/**
	 * 根据主键获取售后申请
	 */
	@Override
	public Result getAfterSchedule(Parameter param) throws Exception {
		OrdersDetail detail = (OrdersDetail) hibernateUtil.find(OrdersDetail.class, param.getId().toString());
		AfterSchedule after = (AfterSchedule) hibernateUtil.hqlFirst("from AfterSchedule where state='1' and ordersDetail.id='"+detail.getId()+"'");
		AfterScheduleVo vo = new AfterScheduleVo();
		if(after!=null){
			vo.setBackPrice(after.getBackPrice());
			vo.setCoinsigee(detail.getOrders().getConsignee());
			if(detail.getTotalPrice()!=null){
				vo.setGoodsPrice(detail.getTotalPrice().toString());
			}
			vo.setGoodsTitle(detail.getGoodsTitle());
			if(after.getBuyerLogCompany()!=null){
				vo.setLogCompany(after.getBuyerLogCompany().getName());
			}
			vo.setLogOdd(after.getBuyerLogOdd());
			vo.setNum(detail.getNumber());
			vo.setOrderNumber(detail.getOrders().getOrderNumber());
			vo.setOrderTime(after.getOrdersDetail().getOrders().getOrderTime());
			vo.setPicSrc(detail.getPicSrc());
			vo.setReason(after.getReason());
			vo.setScheduleStatic(afterServiceUtil.getAfterState(after.getScheduleStatic()));
			vo.setServiceCode(after.getServiceCode());
			vo.setServicePicSrc(after.getPicSrcs());
			vo.setServiceType(afterServiceUtil.getAfterType(after.getType()));
			vo.setServiceNum(after.getNum());
			vo.setServiceId(after.getId());
			BusinessStore store = (BusinessStore) hibernateUtil.find(BusinessStore.class, detail.getShopId());
			if(store!=null){
				vo.setShopName(store.getStoreName());
			}
			vo.setTel(after.getBuyerTel());
		}
		return ObjectToResult.getResult(vo);
	}

	@Override
	public Result afterServicePicUpload(Parameter parameter,
			HttpServletRequest request) throws Exception {
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		System.out.println("contenxtType类型："+request.getContentType());
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		Map<String, MultipartFile> multiValuemap = multipartRequest.getFileMap();
		Set set = multiValuemap.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext()){
			Entry entry = (Entry) it.next();
			entry.getValue();
			fileList.add((MultipartFile) entry.getValue());
		}
		String scheduleId = request.getParameter("scheduleId");
		AfterSchedule afterSchedule = (AfterSchedule) hibernateUtil.find(AfterSchedule.class, scheduleId+"");
		StringBuffer srcs = new StringBuffer();
		System.out.println(scheduleId);
		String path = request.getSession().getServletContext().getRealPath("/upload/img/after/");
		String picPath = "/upload/img/after/";
		int count = 0;
		if (fileList.size()>0) {
			for (MultipartFile multipartFile : fileList) {
				count++;
				String picName = UUID.randomUUID()+"."+multipartFile.getOriginalFilename().split("\\.")[1];
			    String picAddress = picPath+ picName;
			    srcs.append(picAddress);
			    if(count!=fileList.size()){
			    	srcs.append(";");
			    }
				File targetFile = new File(path, picName);  
		        if(!targetFile.exists()){  
		            targetFile.mkdirs();  
		        } 
		        multipartFile.transferTo(targetFile);
			}
		}
		afterSchedule.setPicSrcs(srcs.toString());
		hibernateUtil.update(afterSchedule);
		return ObjectToResult.getResult(afterSchedule);
	}
	
	/**
	 * 用户发货
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveSendGoods(Parameter param)
			throws Exception {
		Object obj = param.getObj();
		AfterScheduleLogisticsVo logisticsVo = (AfterScheduleLogisticsVo) Common.jsonToBean(obj.toString(), AfterScheduleLogisticsVo.class);
		String hql = "update AfterSchedule set buyerLogCompany.id = '"+logisticsVo.getLogisticsCompanyID()+"', buyerLogOdd = '"+logisticsVo.getOdd()+"', buyerLogInfo = '"+logisticsVo.getLogInfo()+"', buyerAddress = '"+logisticsVo.getAddress()+"', buyerTel = '"+logisticsVo.getTel()+"',scheduleStatic='8' where id = '"+logisticsVo.getId()+"'";
		int result = hibernateUtil.execHql(hql);
		afterServiceUtil.saveWebAfterOpLog(param, "【买家】已发货");
		return ObjectToResult.getResult(result);
	}
	
	/**
	 * 获取商家发货的物流信息
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getAfterLogistics(Parameter param) throws Exception {
		AfterSchedule after = (AfterSchedule) hibernateUtil.find(AfterSchedule.class, param.getId()+"");
		KdniaoTrackQueryAPI kdniaoTrackQueryAPI = new KdniaoTrackQueryAPI();
		String res = kdniaoTrackQueryAPI.getOrderTracesByJson(after.getBuyerLogCompany().getCode(), after.getBuyerLogOdd());
		AfterLogisticsVo vo = new AfterLogisticsVo();
		vo.setServiceCode(after.getServiceCode());
		if(after.getBuyerLogCompany()!=null){
			vo.setLogisticeCompanyName(after.getBuyerLogCompany().getName());
		}
		vo.setLogisticeInfo(res);
		vo.setOdd(after.getBuyerLogOdd());
		vo.setCreateTime(after.getCreateTime());
		if(!"null".equals(after.getBuyerLogInfo())){
			vo.setLogInfo(after.getBuyerLogInfo());
		}
		return ObjectToResult.getResult(vo);
	}

	@Override
	public Result updateAfterScheduleStatic(Parameter param)
			throws Exception {
		AfterSchedule after = (AfterSchedule) hibernateUtil.find(AfterSchedule.class, param.getId()+"");
		if(after!=null){
			after.setScheduleStatic(param.getKey());
			hibernateUtil.update(after);
			if("11".equals(param.getKey())){
				afterServiceUtil.saveWebAfterOpLog(param, "【买家】已收货");
			}else if("13".equals(param.getKey())){
				afterServiceUtil.saveWebAfterOpLog(param, "【买家】取消售后申请");
			}
		}
		return ObjectToResult.getResult(after);
	}

	/**
	 * 进入售后页面获取商品信息
	 */
	@Override
	public Result getAfterGoods(Parameter param) throws Exception {
		OrdersDetail ordersDetail = (OrdersDetail) hibernateUtil.find(OrdersDetail.class, param.getId().toString());
		GoodsVo vo = new GoodsVo();
		vo.setConsigee(ordersDetail.getOrders().getConsignee());
		vo.setDetailId(ordersDetail.getId());
		vo.setGoodsTitle(ordersDetail.getGoodsTitle());
		vo.setNum(ordersDetail.getNumber());
		vo.setOrderNumber(ordersDetail.getOrders().getOrderNumber());
		vo.setOrderTime(ordersDetail.getOrders().getOrderTime());
		vo.setPicSrc(ordersDetail.getPicSrc());
		vo.setPrice(ordersDetail.getPrice());
		BusinessStore store = (BusinessStore) hibernateUtil.find(BusinessStore.class, ordersDetail.getShopId());
		if(store!=null){
			vo.setShopName(store.getStoreName());
		}
		return ObjectToResult.getResult(vo);
	}

	/**
	 * 取消售后申请
	 */
	@Override
	public Result cancelAfter(Parameter param) throws Exception {
		OrdersDetail detail = (OrdersDetail) hibernateUtil.find(OrdersDetail.class, param.getId().toString());
		AfterSchedule after = (AfterSchedule) hibernateUtil.hqlFirst("from AfterSchedule where state='1' and ordersDetail.id='"+detail.getId()+"'");
		detail.setIsAfter("3");//取消申请
		after.setScheduleStatic("13");//取消申请
		hibernateUtil.update(detail);
		hibernateUtil.update(after);
		param.setId(after.getId());
		afterServiceUtil.saveWebAfterOpLog(param, "【买家】取消售后申请");
		return ObjectToResult.getResult(after);
	}
	
	
}

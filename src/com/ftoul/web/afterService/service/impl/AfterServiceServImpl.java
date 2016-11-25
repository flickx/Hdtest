package com.ftoul.web.afterService.service.impl;

import java.io.File;
import java.io.Serializable;
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

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.AfterOpLog;
import com.ftoul.po.AfterSchedule;
import com.ftoul.po.Orders;
import com.ftoul.po.OrdersDetail;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.orders.OrdersUtil;
import com.ftoul.web.afterService.service.AfterServiceServ;
import com.ftoul.web.vo.AfterScheduleVo;
import com.ftoul.web.vo.ManyVsOneVo;

@Service("WebAfterServiceServImpl")
public class AfterServiceServImpl implements AfterServiceServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired  
	OrdersUtil ordersUtil;
	
	
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
		page =  hibernateUtil.hqlPage("from Orders where orderStatic not in ('0','1','7','8') and user.id='"+param.getUserToken().getUser().getId()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		ordersList = page.getObjList();
		List<Object> ordersDetailList = new ArrayList<Object>();
		ManyVsOneVo vo;
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < ordersList.size(); i++) {
			Orders order = (Orders) ordersList.get(i);
			ordersDetailList = hibernateUtil.hql("from OrdersDetail where orders.id='"+order.getId()+"'");
			vo = ordersUtil.transformObject(order,ordersDetailList);
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
	public Result getOrderAfterSchedulePage(Parameter param) throws Exception {
		Page page = hibernateUtil.hqlPage("from AfterSchedule where state='1' and user.id='"+param.getUserToken().getUser().getId()+"' order by createTime desc",param.getPageNum(),param.getPageSize());
		List list = page.getObjList();
		List<AfterScheduleVo> voList = new ArrayList<AfterScheduleVo>();
		for (int i = 0; i < list.size(); i++) {
			AfterScheduleVo vo = new AfterScheduleVo();
			AfterSchedule schedule = (AfterSchedule) list.get(i);
			vo.setId(schedule.getId());
			vo.setGoodsName(schedule.getOrdersDetail().getGoodsParam().getGoods().getTitle());
			if(schedule.getLogCompany()!=null){
				vo.setLogCompany(schedule.getLogCompany().getName());
			}
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
			vo.setSalePrice(schedule.getOrdersDetail().getPrice());
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
			od.setIsAfter("1");
			schedule.setOrdersDetail(od);
			schedule.setUser(param.getUserToken().getUser());
			schedule.setServiceCode(ordersUtil.getAfterServiceCode());
			schedule.setScheduleStatic("1");//用户申请售后
			schedule.setState("1");
			schedule.setCreatePerson(param.getUserId());
			schedule.setCreateTime(new DateStr().toString());
			s = hibernateUtil.save(schedule);
			log.setMsg("【用户】申请售后,售后类型为:"+ordersUtil.getAfterType(schedule.getType()));
			log.setCreatePerson(param.getUserToken().getUser().getUsername());
			log.setCreateTime(new DateStr().toString());
			log.setState("1");
		}else{
			hibernateUtil.update(schedule);
			log.setMsg("修改了申请售后内容");
		}
		log.setAfterSchedule(schedule);
		log.setUserId(param.getUserToken().getUser().getName());
		hibernateUtil.save(log);
		return ObjectToResult.getResult(schedule);
	}

	/**
	 * 根据主键获取售后申请
	 */
	@Override
	public Result getAfterSchedule(Parameter param) throws Exception {
		AfterSchedule after = (AfterSchedule) hibernateUtil.find(AfterSchedule.class, param.getId()+"");
		return ObjectToResult.getResult(after);
	}

	@Override
	public Result afterServicePicUpload(Parameter parameter,
			HttpServletRequest request) throws Exception {
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//		MultiValueMap<String, MultipartFile> multiValuemap= multipartRequest.getMultiFileMap();
		Map<String, MultipartFile> multiValuemap = multipartRequest.getFileMap();
		Set set = multiValuemap.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext()){
			Entry entry = (Entry) it.next();
			System.out.println(entry.getKey());
			entry.getValue();
			fileList.add((MultipartFile) entry.getValue());
		}
		//List<MultipartFile> fileList = multipartRequest.getFiles("1");
		String scheduleId = request.getParameter("scheduleId");
		AfterSchedule afterSchedule = (AfterSchedule) hibernateUtil.find(AfterSchedule.class, scheduleId+"");
		StringBuffer srcs = new StringBuffer();
		System.out.println(scheduleId);
		String path = request.getSession().getServletContext().getRealPath("upload/img/after/");
		String picPath = "upload/img/after/";
		//Map<String ,Object> map = new HashMap<String ,Object>();
		int count = 0;
		int state = 0;
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
//				map.put("folderName", "after");
//				map.put("picAddress", picAddress );
//				map.put("picName", picName );
//				map.put("hasUpload", true );
		        //state = 200;
			}
		}
		afterSchedule.setPicSrcs(srcs.toString());
		hibernateUtil.update(afterSchedule);
		return ObjectToResult.getResult(afterSchedule);
	}
	
}

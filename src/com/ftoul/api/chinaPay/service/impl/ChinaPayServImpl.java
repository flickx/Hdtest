package com.ftoul.api.chinaPay.service.impl;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.api.chinaPay.service.ChinaPayServ;
import com.ftoul.api.chinaPay.util.ChinaPayUtil;
import com.ftoul.api.chinaPay.util.SignUtil;
import com.ftoul.api.chinaPay.vo.PayResult;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.Orders;
import com.ftoul.po.OrdersPay;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("ChinaPayServImpl")
public class ChinaPayServImpl implements ChinaPayServ{

	@Autowired
	ChinaPayUtil chinaPayUtil;
	@Autowired
	HibernateUtil hibernateUtil;
	
	/**
	 * 支付订单
	 */
	@Override
	public Result payOrders(Parameter param) throws Exception {
		Orders orders = new Orders();
		Long randomNum = (long) (10000+Math.random()*(99999-10000+1));
		orders.setOrderNumber("2016072900"+randomNum);
		orders.setOrderPrice("1");
//		orders.setOrderStatic("等待支付");
		orders.setPayType("chinaPay");
		orders.setOrderStatic("0");
		orders.setCreateTime(new DateStr().toString());
		hibernateUtil.save(orders);
		return ObjectToResult.getResult(chinaPayUtil.payByOrders(orders));
	}
	
	/**
	 * 支付前台返回处理
	 */
	@Override
	public PayResult payResult(HttpServletRequest request) throws Exception {
		Enumeration<String> requestNames = request.getParameterNames();
		Map<String, String> resultMap = new HashMap<String, String>();
		while(requestNames.hasMoreElements()){
			String name = requestNames.nextElement();
			String value = request.getParameter(name);
//			value = URLDecoder.decode(value, "UTF-8");
			resultMap.put(name, value);
			System.out.println("前台验证："+name + ":" + value);
		}
		PayResult payResult = new PayResult();
		String merOrderNo = resultMap.get("MerOrderNo");
		String orderStatus = resultMap.get("OrderStatus");
		payResult.setOrdersNum(merOrderNo);
		//验证签名
		if(SignUtil.verify(resultMap)){
			if("0000".equals(orderStatus)){
				payResult.setResult(true);
			}else{
				payResult.setResult(false);
			}
		}else{
			payResult.setResult(false);
		}
		return payResult;
	}
	/**
	 * 支付后台返回处理
	 */
	@Override
	public void payReturn(HttpServletRequest request) throws Exception {
		System.out.println("进入银联支付后台返回处理");
		Enumeration<String> requestNames = request.getParameterNames();
		Map<String, String> resultMap = new HashMap<String, String>();
		while(requestNames.hasMoreElements()){
			String name = requestNames.nextElement();
			String value = request.getParameter(name);
			value = URLDecoder.decode(value, "UTF-8");
			resultMap.put(name, value);
			System.out.println("后台验证："+ name + ":" + value);
		}
		String resObj = "";
		//验证签名
		String merOrderNo = resultMap.get("MerOrderNo");
		Orders orders = (Orders) hibernateUtil.hqlFirst("from Orders where orderNumber = '" + merOrderNo + "'");
		orders.setPayType("1");
		
		OrdersPay ordersPay = new OrdersPay();
		
		if(SignUtil.verify(resultMap)){
			orders.setModifyTime(new DateStr().toString());
			orders.setOrderStatic("2");
			orders.setPayStatic("1");
			orders.setPayTime(new DateStr().toString());
			if("1".equals(orders.getIsHasChild())){//如果有子订单，将子订单的付款信息也填充满
				List<Object> childList = hibernateUtil.hql("from Orders where state='1' and parentOrdersId='"+orders.getId()+"'");
				for (Object object : childList) {
					Orders child = (Orders) object;
					child.setModifyTime(new DateStr().toString());
					child.setOrderStatic("2");
					child.setPayStatic("1");
					child.setPayType("1");
					child.setPayTime(new DateStr().toString());
					hibernateUtil.update(child);
				}
			}
			
			ordersPay.setOrders(orders);
			ordersPay.setCreateTime(new DateStr().toString());
			ordersPay.setPayPrice(orders.getOrderPrice());
			ordersPay.setPayStatic("1");
			ordersPay.setPayType("1");
			ordersPay.setSerialNumber(resultMap.get("AcqSeqId"));
			ordersPay.setPayCard(resultMap.get("BankInstNo"));
			ordersPay.setState("1");
			resObj = "success";
			
		}else{
			ordersPay.setOrders(orders);
			ordersPay.setCreateTime(new DateStr().toString());
			ordersPay.setPayPrice(orders.getOrderPrice());
			ordersPay.setPayStatic("0");
			ordersPay.setPayType("1");
			ordersPay.setSerialNumber(resultMap.get("AcqSeqId"));
			ordersPay.setPayCard(resultMap.get("BankInstNo"));
			ordersPay.setState("1");
			resObj = "fail";
		}
		hibernateUtil.update(orders);
		hibernateUtil.save(ordersPay);
		System.out.println(resObj);
	}

}

package com.ftoul.api.weiXinPay.service.impl;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import com.ftoul.api.aliPay.util.httpClient.HttpRequest;
import com.ftoul.api.chinaPay.util.SignUtil;
import com.ftoul.api.chinaPay.vo.PayResult;
import com.ftoul.api.weiXinPay.service.WeiXinPayServ;
import com.ftoul.api.weiXinPay.util.WeiXinPayUtil;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.Orders;
import com.ftoul.po.OrdersPay;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.websocket.WxWebSocketHandler;

@Service("WeiXinPayServImpl")
public class WeiXinPayServImpl implements WeiXinPayServ{

	@Autowired
	WeiXinPayUtil weiXinPayUtil;
	@Autowired
	HibernateUtil hibernateUtil;
	@Autowired  
	private  HttpServletRequest req;
	
	@Bean
	public WxWebSocketHandler wxWebSocketHandler(){
		return new WxWebSocketHandler();
	}
	
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
		orders.setPayType("wxPay");
		orders.setOrderStatic("0");
		orders.setCreateTime(new DateStr().toString());
		hibernateUtil.save(orders);
		return ObjectToResult.getResult(weiXinPayUtil.payByOrders(orders,req.getRemoteAddr()));
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
		String merOrderNo = resultMap.get("out_trade_no");
		String orderStatus = resultMap.get("result_code");
		payResult.setOrdersNum(merOrderNo);
		if("SUCCESS".equals(orderStatus)){
			payResult.setResult(true);
		}else{
			payResult.setResult(false);
		}
		return payResult;
	}
	/**
	 * 支付后台返回处理
	 */
	@Override
	public void payReturn(Map<Object, Object> resultMap) throws Exception {
		String merOrderNo = (String) resultMap.get("out_trade_no");
		Orders orders = (Orders) hibernateUtil.hqlFirst("from Orders where orderNumber = '" + merOrderNo + "'");
		if("1".equals(orders.getOrderStatic())){
			orders.setPayType("3");
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
					child.setPayType("3");
					child.setPayTime(new DateStr().toString());
					hibernateUtil.update(child);
				}
			}
			
			OrdersPay ordersPay = new OrdersPay();
			ordersPay.setOrders(orders);
			ordersPay.setCreateTime(new DateStr().toString());
			ordersPay.setPayPrice(orders.getOrderPrice());
			ordersPay.setPayStatic("1");
			ordersPay.setPayType("3");
			ordersPay.setSerialNumber((String)resultMap.get("transaction_id"));
			//ordersPay.setPayCard(resultMap.get("BankInstNo"));
			ordersPay.setState("1");
			
			hibernateUtil.update(orders);
			hibernateUtil.save(ordersPay);
		}
		sendMessageToPage(orders.getUser().getId(),orders.getOrderNumber(),hibernateUtil);
		
	}
	
	public void sendMessageToPage(String userId,String orderNumber,HibernateUtil hibernateUtil) throws Exception{
		wxWebSocketHandler().sendMessageToPage(userId, orderNumber,hibernateUtil);
	}

}

package com.ftoul.api.aliPay.service.impl;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.api.aliPay.service.AliPayServ;
import com.ftoul.api.aliPay.util.AliPayUtil;
import com.ftoul.api.aliPay.util.AlipayNotify;
import com.ftoul.api.aliPay.util.rsa.AlipayNotifyRSA;
import com.ftoul.api.chinaPay.vo.PayResult;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.Orders;
import com.ftoul.po.OrdersPay;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("AliPayServImpl")
public class AliPayServImpl implements AliPayServ{

	@Autowired
	AliPayUtil aliPayUtil;
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
		return ObjectToResult.getResult(aliPayUtil.payByOrders(orders));
	}
	
	/**
	 * 支付前台返回处理
	 */
	@Override
	public PayResult payResult(HttpServletRequest request) throws Exception {
		//获取支付宝GET过来反馈信息
//		Map<String,String> params = new HashMap<String,String>();
//		Map requestParams = request.getParameterMap();
//		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//			String name = (String) iter.next();
//			String[] values = (String[]) requestParams.get(name);
//			String valueStr = "";
//			for (int i = 0; i < values.length; i++) {
//				valueStr = (i == values.length - 1) ? valueStr + values[i]
//						: valueStr + values[i] + ",";
//			}
//			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//			params.put(name, valueStr);
//		}
//		
//		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
//		//商户订单号
//
//		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//
//		//支付宝交易号
//
//		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//
//		//交易状态
//		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
//
//		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//		
//		//计算得出通知验证结果
//		boolean verify_result = AlipayNotify.verify(params);
//		PayResult payResult = new PayResult();
//		payResult.setOrdersNum(out_trade_no);
//		if(verify_result){//验证成功
//			//////////////////////////////////////////////////////////////////////////////////////////
//			//请在这里加上商户的业务逻辑程序代码
//
//			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
//			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
//				//判断该笔订单是否在商户网站中已经做过处理
//					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//					//如果有做过处理，不执行商户的业务程序
//			}
//			payResult.setResult(true);
//			//该页面可做页面美工编辑
////			out.println("验证成功<br />");
//			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//
//			//////////////////////////////////////////////////////////////////////////////////////////
//		}else{
//			//该页面可做页面美工编辑
//			payResult.setResult(false);
////			out.println("验证失败");
//		}
//		
//		
		Enumeration<String> requestNames = request.getParameterNames();
		Map<String, String> resultMap = new HashMap<String, String>();
		while(requestNames.hasMoreElements()){
			String name = requestNames.nextElement();
			String value = request.getParameter(name);
//			value = URLDecoder.decode(value, "UTF-8");
			resultMap.put(name, value);
			System.out.println("alipay前台验证："+name + ":" + value);
		}
		PayResult payResult = new PayResult();
		String merOrderNo = resultMap.get("out_trade_no");
		String orderStatus = resultMap.get("trade_status");
		payResult.setOrdersNum(merOrderNo);
		//验证签名
		if(AlipayNotify.verify(resultMap)){
			if("TRADE_SUCCESS".equals(orderStatus)){
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
	public void payReturn(HttpServletRequest request ,HttpServletResponse response) throws Exception {
//		//获取支付宝POST过来反馈信息
//		Map<String,String> params = new HashMap<String,String>();
//		Map requestParams = request.getParameterMap();
//		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//			String name = (String) iter.next();
//			String[] values = (String[]) requestParams.get(name);
//			String valueStr = "";
//			for (int i = 0; i < values.length; i++) {
//				valueStr = (i == values.length - 1) ? valueStr + values[i]
//						: valueStr + values[i] + ",";
//			}
//			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
//			params.put(name, valueStr);
//		}
//		
//		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
//		//商户订单号
//
//		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//
//		//支付宝交易号
//
//		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//
//		//交易状态
//		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
//
//		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//
//		if(AlipayNotify.verify(params)){//验证成功
//			//////////////////////////////////////////////////////////////////////////////////////////
//			//请在这里加上商户的业务逻辑程序代码
//
//			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
//			
//			if(trade_status.equals("TRADE_FINISHED")){
//				//判断该笔订单是否在商户网站中已经做过处理
//					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
//					//如果有做过处理，不执行商户的业务程序
//					
//				//注意：
//				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
//			} else if (trade_status.equals("TRADE_SUCCESS")){
//				//判断该笔订单是否在商户网站中已经做过处理
//					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
//					//如果有做过处理，不执行商户的业务程序
//					
//				//注意：
//				//付款完成后，支付宝系统发送该交易状态通知
//			}
//
//			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//				
////			out.print("success");	//请不要修改或删除
//			System.out.println("后台支付成功");
//			//////////////////////////////////////////////////////////////////////////////////////////
//		}else{//验证失败
////			out.print("fail");
//			System.out.println("后台支付失败");
//		}
		
		System.out.println("进入alipay网页版了");
		Enumeration<String> requestNames = request.getParameterNames();
		Map<String, String> resultMap = new HashMap<String, String>();
		while(requestNames.hasMoreElements()){
			String name = requestNames.nextElement();
			String value = request.getParameter(name);
			value = URLDecoder.decode(value, "UTF-8");
			resultMap.put(name, value);
			System.out.println("alipay后台验证："+ name + ":" + value);
		}
		String resObj = "";
		//验证签名
		String merOrderNo = resultMap.get("out_trade_no");
		
		String hql = "from Orders where state = '1' and orderNumber = '" + merOrderNo + "'";
		Orders orders = (Orders) hibernateUtil.hqlFirst(hql);
		
		String payHql = "from OrdersPay where state = '1' and orders.id = '"+orders.getId()+"'";
		OrdersPay ordersPay = (OrdersPay) hibernateUtil.hqlFirst(payHql);
		if(ordersPay == null){
			ordersPay = new OrdersPay();
		}
		
		if(AlipayNotify.verify(resultMap)){
			orders.setModifyTime(new DateStr().toString());
			orders.setOrderStatic("2");
			orders.setPayStatic("1");
			orders.setPayType("2");
			orders.setPayTime(new DateStr().toString());
			
			ordersPay.setOrders(orders);
			ordersPay.setCreateTime(new DateStr().toString());
			ordersPay.setPayPrice(orders.getOrderPrice());
			ordersPay.setPayStatic("1");
			ordersPay.setPayType("2");
			ordersPay.setState("1");
			ordersPay.setSerialNumber(resultMap.get("trade_no"));
			ordersPay.setPayCard(resultMap.get("buyer_logon_id"));//买家支付宝账号
			resObj = "success";
		}else{
			orders.setPayTime(new DateStr().toString());
			ordersPay.setOrders(orders);
			ordersPay.setCreateTime(new DateStr().toString());
			ordersPay.setPayPrice(orders.getOrderPrice());
			ordersPay.setPayStatic("0");
			ordersPay.setPayType("2");
			ordersPay.setState("1");
			ordersPay.setSerialNumber(resultMap.get("trade_no"));
			ordersPay.setPayCard(resultMap.get("buyer_logon_id"));
			
			resObj = "success";
		}
		hibernateUtil.update(orders);
		hibernateUtil.update(ordersPay);
		response.getWriter().write(resObj);
	}

	/**
	 * 支付后台返回处理(APP)
	 */
	@Override
	public void payAppReturn(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		System.out.println("进入alipay APP了");
		Enumeration<String> requestNames = request.getParameterNames();
		Map<String, String> resultMap = new HashMap<String, String>();
		System.out.println("request"+request);
		while(requestNames.hasMoreElements()){
			String name = requestNames.nextElement();
			String value = request.getParameter(name);
			System.out.println("alipay验证前："+ name + ":" + value);
			//value = URLDecoder.decode(value, "UTF-8");
			resultMap.put(name, value);
			System.out.println("alipay后台验证："+ name + ":" + value);
		}
		String resObj = "";
		//验证签名
		String merOrderNo = resultMap.get("out_trade_no");
		
		String hql = "from Orders where state = '1' and orderNumber = '" + merOrderNo + "'";
		Orders orders = (Orders) hibernateUtil.hqlFirst(hql);
		
		String payHql = "from OrdersPay where state = '1' and orders.id = '"+orders.getId()+"'";
		OrdersPay ordersPay = (OrdersPay) hibernateUtil.hqlFirst(payHql);
		if(ordersPay == null){
			ordersPay = new OrdersPay();
		}
		
		if(AlipayNotifyRSA.verify(resultMap)){
			orders.setModifyTime(new DateStr().toString());
			orders.setOrderStatic("2");
			orders.setPayStatic("1");
			orders.setPayType("2");
			orders.setPayTime(new DateStr().toString());
			
			ordersPay.setOrders(orders);
			ordersPay.setCreateTime(new DateStr().toString());
			ordersPay.setPayPrice(orders.getOrderPrice());
			ordersPay.setPayStatic("1");
			ordersPay.setPayType("2");
			ordersPay.setState("1");
			ordersPay.setSerialNumber(resultMap.get("trade_no"));
			ordersPay.setPayCard(resultMap.get("buyer_logon_id"));//买家支付宝账号
			resObj = "success";
		}else{
			orders.setPayTime(new DateStr().toString());
			ordersPay.setOrders(orders);
			ordersPay.setCreateTime(new DateStr().toString());
			ordersPay.setPayPrice(orders.getOrderPrice());
			ordersPay.setPayStatic("0");
			ordersPay.setPayType("2");
			ordersPay.setState("1");
			ordersPay.setSerialNumber(resultMap.get("trade_no"));
			ordersPay.setPayCard(resultMap.get("buyer_logon_id"));
			
			resObj = "success";
		}
		hibernateUtil.update(orders);
		hibernateUtil.update(ordersPay);
		response.getWriter().write(resObj);
	}
}

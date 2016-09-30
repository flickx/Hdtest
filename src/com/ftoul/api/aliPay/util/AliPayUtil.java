package com.ftoul.api.aliPay.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.jdom.JDOMException;
import org.springframework.stereotype.Component;

import com.ftoul.api.aliPay.util.rsa.AlipayConfigRSA;
import com.ftoul.api.aliPay.util.rsa.AlipayCoreRSA;
import com.ftoul.po.Orders;

/**
 * 银联支付中心工具包
 * @author flick
 *
 */
@Component
public class AliPayUtil {

	/**
	 * 根据订单进行支付操作 
	 * @param orders
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public Map<String, String> payByOrders(Orders orders) throws Exception{
		Map<String, String> sendMap = new HashMap<String,String>();
		Map<String, String> sPara = new HashMap<String,String>();
		sendMap.put("service",AlipayConfig.service);
		sendMap.put("partner", AlipayConfig.partner);
		sendMap.put("seller_id", AlipayConfig.seller_id);
		sendMap.put("_input_charset", AlipayConfig.input_charset);
		sendMap.put("payment_type", AlipayConfig.payment_type);
		sendMap.put("notify_url", AlipayConfig.notify_url);
		sendMap.put("return_url", AlipayConfig.return_url);
		sendMap.put("out_trade_no", orders.getOrderNumber());
		sendMap.put("subject", orders.getOrderNumber());
		sendMap.put("total_fee", orders.getOrderPrice());
		//订单的URL
		sendMap.put("show_url", AlipayConfig.show_url 
				+ "?orderNumber=" + orders.getOrderNumber()
				+ "&orderPrice=" + orders.getOrderPrice());
		//增加签名信息
		sPara = AlipaySubmit.buildRequestPara(sendMap);
		return sPara;
	}
	
	public String payByOrdersApp(Orders orders) throws UnsupportedEncodingException{
		String total_fee = orders.getOrderPrice();
		String orderInfo = AlipayCoreRSA.getOrderInfo("他她乐购物订单",
				"他她乐购物订单", total_fee, orders.getOrderNumber());
		String sign = AlipayCoreRSA.sign(orderInfo, AlipayConfigRSA.private_key);
		// 仅需对sign 做URL编码
		sign = URLEncoder.encode(sign, "UTF-8");
		String linkString = orderInfo + "&sign=\"" + sign + "\"&"
				+ AlipayCoreRSA.getSignType();
		return linkString;
	}
	
    public static void main(String[] args) throws Exception{
//    	WeiXinPayUtil weiXinPayUtil = new WeiXinPayUtil();
//    	weiXinPayUtil.payByOrders(null);
	}
}

package com.ftoul.api.chinaPay.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ftoul.po.Orders;

/**
 * 银联支付中心工具包
 * @author flick
 *
 */
@Component
public class ChinaPayUtil {

	//商户号
//	private final static String MerId = "531111608150007";
//	private final static String MerId = "531111608190001";
	private final static String MerId = PayPropUtil.getValue("MerId");
	/**
	 * 根据订单进行支付操作 
	 * @param orders
	 */
	public Map<String, Object> payByOrders(Orders orders){
		Map<String, Object> returnMap = paySign(orders);
		return returnMap;
	}
	
	
	
	/**
	 * 签入方法
	 * @param orders
	 */
	private Map<String, Object> paySign(Orders orders){
		Map<String, Object> sendMap = new HashMap<String,Object>();
		sendMap.put("MerId", MerId);
		sendMap.put("MerOrderNo", orders.getOrderNumber());
		BigDecimal price = new BigDecimal(orders.getOrderPrice());
		BigDecimal base = new BigDecimal(100);
		String money = price.multiply(base).toString();
		sendMap.put("OrderAmt",money.substring(0,money.indexOf(".")));
//		sendMap.put("OrderAmt", (int)(Float.parseFloat(orders.getOrderPrice())*100)+"");
		sendMap.put("TranDate", StringUtil.getRelevantDate(Calendar.getInstance().getTime()));
		sendMap.put("TranTime", StringUtil.getRelevantTime(Calendar.getInstance().getTime()));
		sendMap.put("TranType", PayPropUtil.getValue("TranType"));
		sendMap.put("BusiType", PayPropUtil.getValue("BusiType"));
		sendMap.put("Version", PayPropUtil.getValue("Version"));
		sendMap.put("CurryNo", PayPropUtil.getValue("CurryNo"));
		sendMap.put("AccessType", PayPropUtil.getValue("AccessType"));
		sendMap.put("AcqCode", PayPropUtil.getValue("AcqCode"));
		sendMap.put("MerBgUrl", PayPropUtil.getValue("MerBgUrl"));
		sendMap.put("MerPageUrl", PayPropUtil.getValue("MerPageUrl"));
		//商户签名
		String signature = SignUtil.sign(sendMap);
		sendMap.put("Signature", signature);
		for (String key : sendMap.keySet()) {
			System.out.println("key= "+ key + " and value= " + sendMap.get(key));
		}
//		System.out.println(signature);
		return sendMap;
	}
}

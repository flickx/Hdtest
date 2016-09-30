package com.ftoul.api.weiXinPay.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jdom.JDOMException;
import org.springframework.stereotype.Component;

import com.ftoul.po.Orders;

/**
 * 银联支付中心工具包
 * @author flick
 *
 */
@Component
public class WeiXinPayUtil {

	/**
	 * 根据订单进行支付操作 
	 * @param orders
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public String payByOrders(Orders orders,String ipAddress) throws Exception{
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		parameters.put("appid", ConfigUtil.APPID);
		parameters.put("mch_id", ConfigUtil.MCH_ID);
		String nonceStr = PayCommonUtil.CreateNoncestr();
		parameters.put("nonce_str", nonceStr);
		parameters.put("body", "他她乐订单支付");
		parameters.put("out_trade_no", orders.getOrderNumber());
		BigDecimal price = new BigDecimal(orders.getOrderPrice());
		BigDecimal base = new BigDecimal(100);
		String money = price.multiply(base).toString();
		parameters.put("total_fee",money.substring(0,money.indexOf(".")));
		//parameters.put("total_fee", ((int)(Float.parseFloat(orders.getOrderPrice())*100))+"");
		parameters.put("spbill_create_ip",ipAddress);
		parameters.put("notify_url", ConfigUtil.NOTIFY_URL);
//		parameters.put("timeStamp", new Date().getTime()+"");
		parameters.put("trade_type", "APP");
		String sign = PayCommonUtil.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		String requestXML = PayCommonUtil.getRequestXml(parameters);
		System.out.println(requestXML);
		
		String result =CommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
		System.out.println(result);
		
		Map<String, Object> map = XMLUtil.doXMLParse(result);
		String prepayId = (String) map.get("prepay_id");
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		String s="appid="+ConfigUtil.APPID+"&noncestr="+nonceStr+"&package=Sign=WXPay"+"&partnerid="+
				ConfigUtil.MCH_ID+"&prepayid="+prepayId+"&timestamp="+timestamp+"&key=" + ConfigUtil.API_KEY;
		System.out.println(s);
	    String newSign = MD5Util.MD5Encode(s, "UTF-8").toUpperCase();
		
		
//		System.out.println(map);
//		return map;
//	    Map<String,Object> resMap = new HashMap<String,Object>();
//	    resMap.put("appid", ConfigUtil.APPID);
//	    resMap.put("noncestr", nonceStr);
//	    resMap.put("package", "Sign=WXPay");
//	    resMap.put("partnerid", ConfigUtil.MCH_ID);
//	    resMap.put("prepayid", prepayId);
//	    resMap.put("timestamp", Integer.parseInt(timestamp));
//	    resMap.put("sign", newSign);
	    
	    StringBuffer json = new StringBuffer();
	    json.append("{\"appid\":\"");
	    json.append(ConfigUtil.APPID);
	    json.append("\",\"noncestr\":\"");
	    json.append(nonceStr);
	    json.append("\",\"package\":\"");
	    json.append("Sign=WXPay");
	    json.append("\",\"partnerid\":\"");
	    json.append(ConfigUtil.MCH_ID);
	    json.append("\",\"prepayid\":\"");
	    json.append(prepayId);
	    json.append("\",\"timestamp\":");
	    json.append(timestamp);
	    json.append(",\"sign\":\"");
	    json.append(newSign);
	    json.append("\"}");
	    System.out.println(json.toString());
	    return json.toString();
//		MobilWxPayVo mobilWxPayVo = new MobilWxPayVo();
//		mobilWxPayVo.setAppid(ConfigUtil.APPID);
//		mobilWxPayVo.setNoncestr(nonceStr);
//		mobilWxPayVo.setPartnerid(ConfigUtil.MCH_ID);
//		mobilWxPayVo.setPrepayid(prepayId);
//		mobilWxPayVo.setTimestamp(timestamp);
//		mobilWxPayVo.setSign(newSign);
//		return mobilWxPayVo;
	}
	
	
    public static void main(String[] args) throws Exception{
//    	WeiXinPayUtil weiXinPayUtil = new WeiXinPayUtil();
//    	Orders orders = new Orders();
//    	Long randomNum = (long) (10000+Math.random()*(99999-10000+1));
//		orders.setOrderNumber("2016072900"+randomNum);
//		orders.setOrderPrice("1");
//    	MobilWxPayVo mobilWxPayVo = weiXinPayUtil.payByOrders(orders,"120.26.223.18");
//    	System.out.println(mobilWxPayVo);
//    	System.out.println((int)(Float.parseFloat("0.01")*100));
    	System.out.println(MD5Util.MD5Encode("admin&ad的", "UTF-8"));
    	System.out.println(WXMD5.MD5Encode("admin&ad的"));
    	System.out.println(new Date().getTime());
	}
}

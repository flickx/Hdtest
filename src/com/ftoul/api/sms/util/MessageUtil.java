package com.ftoul.api.sms.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.ftoul.common.CustomizedPropertyConfigurer;

public class MessageUtil {

	public static String sn = CustomizedPropertyConfigurer.getProperty("message.sn");// 软件序列号,请通过亿美销售人员获取
	public static String key = CustomizedPropertyConfigurer.getProperty("message.key");// 序列号首次激活时自己设定
	public static String password = CustomizedPropertyConfigurer.getProperty("message.password");// 密码,请通过亿美销售人员获取
	public static String baseUrl = CustomizedPropertyConfigurer.getProperty("message.baseUrl");
	
	/**
	 * 
	 * 发送短信
	 * @param   mobile :手机号,content:发送内容
	 * @return  null
	 * @throws UnsupportedEncodingException 
	 */
	public static String send(String mobile,String content) throws UnsupportedEncodingException{
		content = URLEncoder.encode(content, "UTF-8");
		String code = "888";
		long seqId = System.currentTimeMillis();
		String param = "cdkey=" + sn + "&password=" + key + "&phone=" + mobile + "&message=" + content + "&addserial=" + code + "&seqid=" + seqId;
		String url = baseUrl + "sendsms.action";
		String ret = SDKHttpClient.sendSMS(url, param);
		return ret;
	}
	
	public static void StartMenu() {
		int i = 1;
		System.out.println(i + "、激活序列号,初次使用、已注销后再次使用时调用该方法.");
		i += 1;
		System.out.println(i + "、余额查询");
		i += 1;
		System.out.println(i + "、发送带有信息ID的短信,可供查询状态报告");
		i += 1;
		System.out.println(i + "、获取上行短信");
		i += 1;
		System.out.println(i + "、获得下行短信状态报告");
		i += 1;
		System.out.println(i + "、注销序列号");
		i += 1;
		System.out.println(i + "、关闭程序");
	}
	
	public static void main(String[] args) {
		System.out.println(123);
	}
}

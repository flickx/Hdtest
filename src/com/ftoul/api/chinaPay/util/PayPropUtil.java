package com.ftoul.api.chinaPay.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 支付配置文件（pay.propties）获取支付参数
 * @author flick
 *
 */
public class PayPropUtil {
	private static Properties properties = null;
	static{
		try {
			InputStream is = PayPropUtil.class.getResourceAsStream("/config/pay.properties");
			properties = new Properties();
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据key值取得对应的value值
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return properties.getProperty(key);
	}
	
	public static void main(String[] args) {
		System.out.println(PayPropUtil.getValue("MerId"));
	}
}

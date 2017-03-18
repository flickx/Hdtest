package com.ftoul.api.aliPay.util;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088421453179888";
	
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	// MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String key = "n7ahaip5h4al9hvvd5ehnsqqykveszkj";
    
    // RSA密钥，安全检验码，由数字和字母组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
//    public static String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyVR4tb7SdWgLxH+zzK6C5gK3vySimNwich+1tSooB1q3dg1hDRw2On6b9a5901FVewHnAfX+I/dbdIlNqUDakYu0S36G5b4n6hulbjKBhVpIQ/OkNJGJrf+F8rRljKiOuuwc/uXB+SNJ1jGuMC1WyiViBRl/d/Izugv1gH/XgIQIDAQAB";
    public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANCpeZDXIJLzmoFcbjnRwrg88ThlbM1r285sMcnpEw3WgVvFdFig2vwiiiQ17r2zfAo1+2MXyOg1ylURNd3LyfbiXbOAJpJ1RCb04LIsA2kffaEIkbAyfvRC82+gKmAxiF8zgFwJHQFYKl1esIUz6vwdkx4oMB/uNn1D9UbajjhDAgMBAAECgYAyxfpRtzcy6tnevmoH4bW69QFRUsS924/507PtyKwdQuPpO8vKwSFoFtN8ai80s9br+SS5gREfB++68VwlIzB6VKvdaG15c0FlOfoti418a88wVAyjnDTOVzN9QwW9nnxMz/sKKdmtFspkyhkq+779fg+F17o/ENrxbNcMQd6LAQJBAP6K/QpfS5gMxkQrVsLmUCvuIJJ2MlT7vaJDX137F8ai55yHxyPNLMjWmLWubpIXrvN5+sE2Eb8/QeMYzq2aMIECQQDR20Byz3tg3vL1+Kz7Kjq4+QRh7deGVk3K1H8tghdGcN4+yxWo+HQvZtibPDc0lX4sibygNHXOGco5FwOI2EbDAkEA4GdGF8vViBHTBSw0DBUhnukLXDPJwbRCBXhh7ie8WUkq6YumW4C5t8HA1YMXRcKNYWcrUwTI9pLdEkm/g7sIgQJAWpMlB+ihObhAaQWBs44QdlGUIKhFA4gwX0DD+2JQpWiFo9iHaryBeQwMpUOiGCIFqTdxoDvENMZQz78tGV0vfQJAaaQelUiyyIUNv2usRHr4dtV/PVfyfeAkLwhzKATguUmY4zNakqARPc0T0Up3IWp/bdDgXDX6hBuaCMFQIVH7jA==";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://api2.tatll.com/FtShop/api/aliPay/payReturn.action";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://api2.tatll.com/FtShop/api/aliPay/payResult.action";
	
	//点击返回回到页面
	public static String show_url = "http://m.tatll.com/pages/Order/OrderList.html?type=waitPayment";

	// 签名方式
	public static String sign_type = "MD5";
	public static String rsa_sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
	// 字符编码格式 目前支持utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// WAP调用的接口名，无需修改
	public static String service = "alipay.wap.create.direct.pay.by.user";
	// PC调用的接口名，无需修改
	public static String pc_service = "create_direct_pay_by_user";
	
	// APP调用的接口名，无需修改
//	public static String appService = "mobile.securitypay.pay";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}


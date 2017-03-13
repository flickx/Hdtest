package com.ftoul.api.aliPay.util.rsa;

import com.ftoul.api.aliPay.util.AlipayConfig;

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

public class AlipayConfigRSA {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = AlipayConfig.partner;
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANCpeZDXIJLzmoFcbjnRwrg88ThlbM1r285sMcnpEw3WgVvFdFig2vwiiiQ17r2zfAo1+2MXyOg1ylURNd3LyfbiXbOAJpJ1RCb04LIsA2kffaEIkbAyfvRC82+gKmAxiF8zgFwJHQFYKl1esIUz6vwdkx4oMB/uNn1D9UbajjhDAgMBAAECgYAyxfpRtzcy6tnevmoH4bW69QFRUsS924/507PtyKwdQuPpO8vKwSFoFtN8ai80s9br+SS5gREfB++68VwlIzB6VKvdaG15c0FlOfoti418a88wVAyjnDTOVzN9QwW9nnxMz/sKKdmtFspkyhkq+779fg+F17o/ENrxbNcMQd6LAQJBAP6K/QpfS5gMxkQrVsLmUCvuIJJ2MlT7vaJDX137F8ai55yHxyPNLMjWmLWubpIXrvN5+sE2Eb8/QeMYzq2aMIECQQDR20Byz3tg3vL1+Kz7Kjq4+QRh7deGVk3K1H8tghdGcN4+yxWo+HQvZtibPDc0lX4sibygNHXOGco5FwOI2EbDAkEA4GdGF8vViBHTBSw0DBUhnukLXDPJwbRCBXhh7ie8WUkq6YumW4C5t8HA1YMXRcKNYWcrUwTI9pLdEkm/g7sIgQJAWpMlB+ihObhAaQWBs44QdlGUIKhFA4gwX0DD+2JQpWiFo9iHaryBeQwMpUOiGCIFqTdxoDvENMZQz78tGV0vfQJAaaQelUiyyIUNv2usRHr4dtV/PVfyfeAkLwhzKATguUmY4zNakqARPc0T0Up3IWp/bdDgXDX6hBuaCMFQIVH7jA==";
	
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://api.tatll.com/FtShop/api/aliPay/payAppReturn.action";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = AlipayConfig.return_url;

	// 签名方式
	public static String sign_type = "RSA";
	
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
	// 字符编码格式 目前支持utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
//	public static String service = "alipay.wap.create.direct.pay.by.user";
	public static String service = "mobile.securitypay.pay";
	public static String pc_service = "create_direct_pay_by_user";

	// 设置未付款交易的超时时间
	// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
	// 取值范围：1m～15d。
	// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
	// 该参数数值不接受小数点，如1.5h，可转换为90m。
	public static String it_b_pay = "1d";
//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}


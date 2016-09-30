package com.ftoul.api.weiXinPay.util;

public class ConfigUtil {
	/**
	 * 服务号相关信息
	 */
	 public final static String APPID = "wx52cccaf318767b4c";//服务号的应用号
//	 public final static String APP_SECRECT = "04368f6940e5b5a35d89e6409be82e76";//APP密匙
//	 public final static String TOKEN = "token";//服务号的配置token
	 public final static String MCH_ID = "1382884802";//商户号
	 public final static String API_KEY = "96e79218965eb72c92a549dd5a330112";//API密钥
	 
//	 public final static String SIGN_TYPE = "MD5";//签名加密方式
	//微信支付统一接口的回调action
	 public final static String NOTIFY_URL = "http://test.tatll.com/FtShop/api/weiXinPay/payReturn.action";
	//微信支付成功支付后跳转的地址
	 public final static String SUCCESS_URL = "http://test.tatll.com/FtShop/api/weiXinPay/payResult.action";
	 //oauth2授权时回调action
	 public final static String REDIRECT_URI = "http://test.tatll.com/FtShop/api/weiXinPay/payResult.action";
	/**
	 * 微信支付接口地址
	 */
	//微信支付统一接口(POST)
	public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//微信退款接口(POST)
	public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	//订单查询接口(POST)
	public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	//关闭订单接口(POST)
	public final static String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	//退款查询接口(POST)
	public final static String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	//对账单接口(POST)
	public final static String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	//短链接转换接口(POST)
	public final static String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	//接口调用上报接口(POST)
	public final static String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
}

package com.ftoul.common;

/**
 * 订单状态常量
 * @author hud
 *
 */
	
public class OrdersConstant {
	/**待付款 1*/
	public static final String NOT_PAY = "waitPayment";
	/**已付款 2*/
	public static final String YES_PAY = "2";
	/**待发货 3*/
	public static final String NOT_DELIVER = "waitShipments";
	/**已发货 4*/
	public static final String YES_DELIVER = "4";
	/**待收货 5*/
	public static final String NOT_TASK_DELIVER = "waitReceipt";
	/**已完成 6*/
	public static final String COMPLETE = "6";
	/**未评价 7*/
	public static final String NOT_EVALUATE = "7";
	/**售后 10*/
	public static final String AFTER = "10";
	
	/**银联支付*/
	public static final String CHINAPAYTEST = "0";
	/**银联支付*/
	public static final String CHINAPAY = "1";
	/**支付宝支付*/
	public static final String ALIPAY = "2";
	/**微信支付*/
	public static final String WXPAY = "3";
	/**支付宝钱包支付*/
	public static final String ALIQBPAY = "4";
	

}

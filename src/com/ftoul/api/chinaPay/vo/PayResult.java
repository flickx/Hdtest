package com.ftoul.api.chinaPay.vo;
/**
 * 支付结果返回类
 * @author flick
 *
 */
public class PayResult {

	//支付结果
	private Boolean result;
	//订单号
	private String ordersNum;
	
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getOrdersNum() {
		return ordersNum;
	}
	public void setOrdersNum(String ordersNum) {
		this.ordersNum = ordersNum;
	}
	
}

package com.ftoul.app.vo;

public class OrderAppVo {
	
	private String orderNumber;//订单号
	private String orderStatic;//订单状态
	private String price;//订单价格
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderStatic() {
		return orderStatic;
	}

	public void setOrderStatic(String orderStatic) {
		this.orderStatic = orderStatic;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}

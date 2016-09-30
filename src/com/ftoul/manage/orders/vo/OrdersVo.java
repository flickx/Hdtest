package com.ftoul.manage.orders.vo;

import java.util.List;

public class OrdersVo {
	
	private String id;
	private String orderNumber;
	private List<String> goodPicSrcs;
	private String payable;//总价
	private String goodsTotal;
	private String userName;
	private String conginee;
	private String tel;
	private String orderTime;
	private String orderStatic;
	private String orderPrice;//实付金额
	private String shopName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public List<String> getGoodPicSrcs() {
		return goodPicSrcs;
	}
	public void setGoodPicSrcs(List<String> goodPicSrcs) {
		this.goodPicSrcs = goodPicSrcs;
	}
	public String getPayable() {
		return payable;
	}
	public void setPayable(String payable) {
		this.payable = payable;
	}
	public String getGoodsTotal() {
		return goodsTotal;
	}
	public void setGoodsTotal(String goodsTotal) {
		this.goodsTotal = goodsTotal;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getConginee() {
		return conginee;
	}
	public void setConginee(String conginee) {
		this.conginee = conginee;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderStatic() {
		return orderStatic;
	}
	public void setOrderStatic(String orderStatic) {
		this.orderStatic = orderStatic;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	

}

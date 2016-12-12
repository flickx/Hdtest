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
	private String address;
	private String orderTime;
	private String orderStatic;
	private String orderPrice;//实付金额
	private String shopName;
	private String isHasChild;
	private String parentOrderNumber;
	private String freight;
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
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
	public String getIsHasChild() {
		return isHasChild;
	}
	public void setIsHasChild(String isHasChild) {
		this.isHasChild = isHasChild;
	}
	public String getParentOrderNumber() {
		return parentOrderNumber;
	}
	public void setParentOrderNumber(String parentOrderNumber) {
		this.parentOrderNumber = parentOrderNumber;
	}
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}

}

package com.ftoul.web.vo;

import java.util.List;

public class OrderPriceVo {
	
	private String payable;
	private String orderPrice;
	private String benPrice;
	private String orderNumber;
	private String isCard;
	private String msg;
	private int coinNumber;
	private int totalCoinNumber;
	private double coinPrice;
	private String flag;
	private String shopName;
	private String eventName;
	private List<Object> voList;
//	private List<ShopGoodsParamVo> shopGoodsParamList;
	
	public String getBenPrice() {
		return benPrice;
	}
	public void setBenPrice(String benPrice) {
		this.benPrice = benPrice;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getPayable() {
		return payable;
	}
	public void setPayable(String payable) {
		this.payable = payable;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getIsCard() {
		return isCard;
	}
	public void setIsCard(String isCard) {
		this.isCard = isCard;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCoinNumber() {
		return coinNumber;
	}
	public void setCoinNumber(int coinNumber) {
		this.coinNumber = coinNumber;
	}
	public double getCoinPrice() {
		return coinPrice;
	}
	public void setCoinPrice(double coinPrice) {
		this.coinPrice = coinPrice;
	}
	public int getTotalCoinNumber() {
		return totalCoinNumber;
	}
	public void setTotalCoinNumber(int totalCoinNumber) {
		this.totalCoinNumber = totalCoinNumber;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
//	public List<ShopGoodsParamVo> getShopGoodsParamList() {
//		return shopGoodsParamList;
//	}
//	public void setShopGoodsParamList(List<ShopGoodsParamVo> shopGoodsParamList) {
//		this.shopGoodsParamList = shopGoodsParamList;
//	}
	public List<Object> getVoList() {
		return voList;
	}
	public void setVoList(List<Object> voList) {
		this.voList = voList;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
}

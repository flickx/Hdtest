package com.ftoul.web.vo;

import java.math.BigDecimal;
import java.util.List;

public class OrderPriceVo {
	
	private String payable;//原价
	private String orderPrice;//订单价格包含运费
	private double goodsTotalPrice;//商品总价格（不包含运费）
	private String benPrice;//优惠金额
	private String orderNumber;//订单号
	private String isCard;//是否有身份证信息
	private String msg;//存一些提示信息
	private int coinNumber;//蜂币数量
	private int totalCoinNumber;//总蜂币数量
	private double coinPrice;//蜂币兑换价格
	private String flag;//
	private String shopName;//店铺名
	private String shopId;//店铺ID
	private String eventName;//参加活动名称
	private int goodsNum;//购买商品数量
	private double freight;//运费
	private List<Object> voList;
	private List<Object> couponList;//优惠券列表
	private List<Object> mjList;//满减活动列表
	
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
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public int getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}
	public double getFreight() {
		return freight;
	}
	public void setFreight(double freight) {
		this.freight = freight;
	}
	public double getGoodsTotalPrice() {
		return goodsTotalPrice;
	}
	public void setGoodsTotalPrice(double goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}
	public List<Object> getCouponList() {
		return couponList;
	}
	public void setCouponList(List<Object> couponList) {
		this.couponList = couponList;
	}
	public List<Object> getMjList() {
		return mjList;
	}
	public void setMjList(List<Object> mjList) {
		this.mjList = mjList;
	}
	
}

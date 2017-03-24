package com.ftoul.manage.user.vo;

public class UserManageVo {
	
	private String username;//用户名
	private String lastLoginTime;//上次登陆时间
	private String lastLoginIp;//上次登陆ip
	private String userAmount;//用户总数
	private String goodsAmount;//商品总数
	private String shopAmount;//店铺总数
	private String orderAmount;//订单总金额
	
	private String deliveryOrder;//待发货订单
	private String goodsWarn;//商品库存预警
	private String auditActivity;//待审核促销
	private String expireActivity;//即将到期促销
	private String confirmOrder;//待确认订单
	private String payOrder;//待支付订单
	
	private String refundOrder;//退款单
	private String exchangeOrder;//换货单
	private String answer;//待回答咨询
	private String complain;//待处理投诉
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getUserAmount() {
		return userAmount;
	}
	public void setUserAmount(String userAmount) {
		this.userAmount = userAmount;
	}

	public String getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(String goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	public String getShopAmount() {
		return shopAmount;
	}
	public void setShopAmount(String shopAmount) {
		this.shopAmount = shopAmount;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getDeliveryOrder() {
		return deliveryOrder;
	}
	public void setDeliveryOrder(String deliveryOrder) {
		this.deliveryOrder = deliveryOrder;
	}
	public String getGoodsWarn() {
		return goodsWarn;
	}
	public void setGoodsWarn(String goodsWarn) {
		this.goodsWarn = goodsWarn;
	}
	public String getAuditActivity() {
		return auditActivity;
	}
	public void setAuditActivity(String auditActivity) {
		this.auditActivity = auditActivity;
	}
	public String getExpireActivity() {
		return expireActivity;
	}
	public void setExpireActivity(String expireActivity) {
		this.expireActivity = expireActivity;
	}
	public String getConfirmOrder() {
		return confirmOrder;
	}
	public void setConfirmOrder(String confirmOrder) {
		this.confirmOrder = confirmOrder;
	}
	public String getPayOrder() {
		return payOrder;
	}
	public void setPayOrder(String payOrder) {
		this.payOrder = payOrder;
	}
	public String getRefundOrder() {
		return refundOrder;
	}
	public void setRefundOrder(String refundOrder) {
		this.refundOrder = refundOrder;
	}
	public String getExchangeOrder() {
		return exchangeOrder;
	}
	public void setExchangeOrder(String exchangeOrder) {
		this.exchangeOrder = exchangeOrder;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getComplain() {
		return complain;
	}
	public void setComplain(String complain) {
		this.complain = complain;
	}
	
	
}

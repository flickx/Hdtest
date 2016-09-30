package com.ftoul.web.vo;

public class OrderVo {
	private String orderNumber;
	private String addressId;
	private String payable;
	private String goodsParameter;
	private String invoiceType;
	private String invoiceHead;
	private String invoiceContent;
	private String payType;
	private String feedBack;
	private String card;
	private int coinNumber;
	private Boolean coinFlag;
	
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getPayable() {
		return payable;
	}
	public void setPayable(String payable) {
		this.payable = payable;
	}
	public String getGoodsParameter() {
		return goodsParameter;
	}
	public void setGoodsParameter(String goodsParameter) {
		this.goodsParameter = goodsParameter;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getInvoiceHead() {
		return invoiceHead;
	}
	public void setInvoiceHead(String invoiceHead) {
		this.invoiceHead = invoiceHead;
	}
	public String getInvoiceContent() {
		return invoiceContent;
	}
	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getFeedBack() {
		return feedBack;
	}
	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public Boolean getCoinFlag() {
		return coinFlag;
	}
	public void setCoinFlag(Boolean coinFlag) {
		this.coinFlag = coinFlag;
	}
	public int getCoinNumber() {
		return coinNumber;
	}
	public void setCoinNumber(int coinNumber) {
		this.coinNumber = coinNumber;
	}
	
}

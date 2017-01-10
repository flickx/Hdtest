package com.ftoul.pc.orders.vo;

import java.util.List;

public class PcOrderVo {
	private String id;//主键
	private String orderNumber;//订单号
	private String shopName;//店铺名称
	private String orderPrice;//支付金额
	private String goodsTotalPrice;//商品总金额
	private String benPrice;//优惠金额
	private String freight;//运费
	private String payType;//支付方式
	private String orderTime;//下单时间
	private String payTime;//付款时间
	private String orderStatic;//订单状态
	private String odd;//物流单号
	private String logistInfo;//物流信息
	private String logCompany;//物流公司
	private String consigeeName;//收货人
	private String tel;//联系电话
	private String address;//地址
	
	
	private List<Object> detailVoList;
	
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
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getConsigeeName() {
		return consigeeName;
	}
	public void setConsigeeName(String consigeeName) {
		this.consigeeName = consigeeName;
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
	public List<Object> getDetailVoList() {
		return detailVoList;
	}
	public void setDetailVoList(List<Object> detailVoList) {
		this.detailVoList = detailVoList;
	}
	public String getGoodsTotalPrice() {
		return goodsTotalPrice;
	}
	public void setGoodsTotalPrice(String goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}
	public String getBenPrice() {
		return benPrice;
	}
	public void setBenPrice(String benPrice) {
		this.benPrice = benPrice;
	}
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getOdd() {
		return odd;
	}
	public void setOdd(String odd) {
		this.odd = odd;
	}
	public String getLogCompany() {
		return logCompany;
	}
	public void setLogCompany(String logCompany) {
		this.logCompany = logCompany;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLogistInfo() {
		return logistInfo;
	}
	public void setLogistInfo(String logistInfo) {
		this.logistInfo = logistInfo;
	}

}

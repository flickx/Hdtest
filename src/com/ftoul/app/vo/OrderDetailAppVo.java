package com.ftoul.app.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单详情vo
 * @author yzw
 *
 */
public class OrderDetailAppVo {
	private String id;//订单详情id
	private String orderNumber;//订单号
	private String storeName;//店铺名
	private String orderTime;//下单时间
	private String orderPrice;//支付金额(实付款金额)
	private String orderStatic;//订单状态
	private String consignee;//收货人
	private String consigneeTel;//收货人电话
	private String address;//收货地址
	private String feedback;//订单备注
	private String beeCoins;//蜂币抵扣数量
	private String coinPrice;//蜂币抵扣金额;
	private String payable;//商品金额
	private BigDecimal freight;//运费
	private String benefitPrice;//满减优惠金额
	private List<OrderListDetailAppVo> detailList;//商品信息集合
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
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderStatic() {
		return orderStatic;
	}
	public void setOrderStatic(String orderStatic) {
		this.orderStatic = orderStatic;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getConsigneeTel() {
		return consigneeTel;
	}
	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getBeeCoins() {
		return beeCoins;
	}
	public void setBeeCoins(String beeCoins) {
		this.beeCoins = beeCoins;
	}
	public String getCoinPrice() {
		return coinPrice;
	}
	public void setCoinPrice(String coinPrice) {
		this.coinPrice = coinPrice;
	}
	public String getPayable() {
		return payable;
	}
	public void setPayable(String payable) {
		this.payable = payable;
	}
	
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public String getBenefitPrice() {
		return benefitPrice;
	}
	public void setBenefitPrice(String benefitPrice) {
		this.benefitPrice = benefitPrice;
	}
	public List<OrderListDetailAppVo> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<OrderListDetailAppVo> detailList) {
		this.detailList = detailList;
	}
	
}

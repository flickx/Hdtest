package com.ftoul.app.vo;

import java.util.List;

public class OrderListAppVo {
	private String id;//id
	private String orderNumber;//订单号
	private String storeName;//店铺名
	private String goodsTotal;//商品总件数
	private String orderTime;//下单时间
	private String orderPrice;//支付金额
	private String orderStatic;//订单状态
	private List<OrderListDetailAppVo> detailList;//订单详情集合
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
	public String getGoodsTotal() {
		return goodsTotal;
	}
	public void setGoodsTotal(String goodsTotal) {
		this.goodsTotal = goodsTotal;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<OrderListDetailAppVo> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<OrderListDetailAppVo> detailList) {
		this.detailList = detailList;
	}
	
}

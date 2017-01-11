package com.ftoul.app.vo;

/**
 * 订单列表订单详情vo
 * @author yzw
 *
 */
public class OrderListDetailAppVo {
	private String id;//商品ID
	private String title;//商品标题
	private String picSrc;//商品图片
	private String paramName;//商品参数
	private Double price;//价格
	private String number;//数量
	private String isAfter;//是否已申请售后;1:已申请售后 ,null未申请售后
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicSrc() {
		return picSrc;
	}
	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getIsAfter() {
		return isAfter;
	}
	public void setIsAfter(String isAfter) {
		this.isAfter = isAfter;
	}
	
}

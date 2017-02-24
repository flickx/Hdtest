package com.ftoul.app.vo;

import java.math.BigDecimal;

public class IndexGoodsAppVo {
	
	private String goodsId;//ID
	private String title;//商品名称
	private double price;//商品价格
	private BigDecimal eventPrice;//活动价格
	private String picSrc;//商品图片 主图
	
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPicSrc() {
		return picSrc;
	}

	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	public BigDecimal getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(BigDecimal eventPrice) {
		this.eventPrice = eventPrice;
	}
}

package com.ftoul.app.vo;

import java.math.BigDecimal;

public class PcLimitGoods {
	private String goodsId;
	private String name;
	private String model;
	private BigDecimal presentPrice;
	private double originalPrice;
	private String imgUrl;
	private String num;//抢购商品剩余百分比
	/**
	 * @return the goodsId
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the presentPrice
	 */
	public BigDecimal getPresentPrice() {
		return presentPrice;
	}
	/**
	 * @param presentPrice the presentPrice to set
	 */
	public void setPresentPrice(BigDecimal presentPrice) {
		this.presentPrice = presentPrice;
	}
	/**
	 * @return the originalPrice
	 */
	public double getOriginalPrice() {
		return originalPrice;
	}
	/**
	 * @param originalPrice the originalPrice to set
	 */
	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}
	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}
	
	
}

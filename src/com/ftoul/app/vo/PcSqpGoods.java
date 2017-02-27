package com.ftoul.app.vo;


public class PcSqpGoods {
	private String goodsId;
	private String title;
	private String subTitle;
	private String model;
	private double price;
	private double eventPrice;
	private String picSrc;
	private String qunatity;//折扣
	private Integer saleSum;
	/**
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}
	/**
	 * @param subTitle the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * @return the eventPrice
	 */
	public double getEventPrice() {
		return eventPrice;
	}
	/**
	 * @param eventPrice the eventPrice to set
	 */
	public void setEventPrice(double eventPrice) {
		this.eventPrice = eventPrice;
	}
	/**
	 * @return the picSrc
	 */
	public String getPicSrc() {
		return picSrc;
	}
	/**
	 * @param picSrc the picSrc to set
	 */
	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}
	/**
	 * @return the qunatity
	 */
	public String getQunatity() {
		return qunatity;
	}
	/**
	 * @param qunatity the qunatity to set
	 */
	public void setQunatity(String qunatity) {
		this.qunatity = qunatity;
	}
	/**
	 * @return the saleSum
	 */
	public Integer getSaleSum() {
		return saleSum;
	}
	/**
	 * @param saleSum the saleSum to set
	 */
	public void setSaleSum(Integer saleSum) {
		this.saleSum = saleSum;
	}
	
}

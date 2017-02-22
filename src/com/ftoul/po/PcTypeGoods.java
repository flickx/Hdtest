package com.ftoul.po;


public class PcTypeGoods {
	private String goodsId;//商品id
	private String title;//主标题
	private String subTitle;//副标题
	private double price;//现价
	private double marketPrice;//市场价格
	private String picSrc;//商品主图片
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
	 * @return the marketPrice
	 */
	public double getMarketPrice() {
		return marketPrice;
	}
	/**
	 * @param marketPrice the marketPrice to set
	 */
	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
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
}

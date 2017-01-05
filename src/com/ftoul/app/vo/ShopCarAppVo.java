package com.ftoul.app.vo;


/**
 * 购物车VO.
 * @author yzw
 *
 */
public class ShopCarAppVo {
	private String id;//id
	private String userId;//用户ID
	private String goodsParamId;//商品参数Id
	private String number;//商品数量
	private String picSrc;//商品图片
	private String title;//商品名称
	private String price;//商品价格
	private String paramName;//商品参数名称
	private String goodsId;//商品ID
	private Float eventPrice;//活动价
	private String stock;//商品库存
	private String typeName;//活动类型
	private String shopId;//店铺Id
	private String shopName;//店铺名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGoodsParamId() {
		return goodsParamId;
	}
	public void setGoodsParamId(String goodsParamId) {
		this.goodsParamId = goodsParamId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPicSrc() {
		return picSrc;
	}
	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public Float getEventPrice() {
		return eventPrice;
	}
	public void setEventPrice(Float eventPrice) {
		this.eventPrice = eventPrice;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}

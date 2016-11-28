package com.ftoul.web.vo;


/**
 * 购物车VO.
 * @author yzw
 *
 */
public class ShopCarVO {
	private String id;
	private String userId;
	private String goodsParamId;
	private String number;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	private String state;
	private String picSrc;
	private String title;
	private String price;
	private String paramName;
	private String goodsId;
	private String discount;
	private Float eventPrice;//活动价
	private String stock;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyPerson() {
		return modifyPerson;
	}
	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
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

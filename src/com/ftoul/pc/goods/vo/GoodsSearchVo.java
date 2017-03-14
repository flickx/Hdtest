package com.ftoul.pc.goods.vo;

import java.util.List;


public class GoodsSearchVo {
	private String id;//ID
	private String title;//商品名称
	private String price;//商品价格
	private String picSrc;//商品图片 主图
	private String shopId;//店铺Id
	private String comment;//评论数
	private List<SearchVo> goodsBrandList;//品牌集合
	private List<SearchVo> countryList;//国家地区集合
	private List<SearchVo> goodsTypeList;//分类集合
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPicSrc() {
		return picSrc;
	}
	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public List<SearchVo> getGoodsBrandList() {
		return goodsBrandList;
	}
	public void setGoodsBrandList(List<SearchVo> goodsBrandList) {
		this.goodsBrandList = goodsBrandList;
	}
	public List<SearchVo> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<SearchVo> countryList) {
		this.countryList = countryList;
	}
	public List<SearchVo> getGoodsTypeList() {
		return goodsTypeList;
	}
	public void setGoodsTypeList(List<SearchVo> goodsTypeList) {
		this.goodsTypeList = goodsTypeList;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}

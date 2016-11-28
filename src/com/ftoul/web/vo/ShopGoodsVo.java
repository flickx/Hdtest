package com.ftoul.web.vo;

import java.util.List;

public class ShopGoodsVo {
	
	private String shopId;
	private String shopName;
	private List<Object> goodsVoList;
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
	public List<Object> getGoodsVoList() {
		return goodsVoList;
	}
	public void setGoodsVoList(List<Object> goodsVoList) {
		this.goodsVoList = goodsVoList;
	}
	
}

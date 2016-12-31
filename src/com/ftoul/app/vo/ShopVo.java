package com.ftoul.app.vo;

public class ShopVo {
	
	private String storeId;//店铺id
	private String storePic;//店铺图标
	private String storeName;//店铺名
	private Integer goodsSaleNum;//销售量
	private Integer goodsNum;//全部商品数量
	private Integer goodsMonthNum;//上新商品
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Integer getGoodsSaleNum() {
		return goodsSaleNum;
	}
	public void setGoodsSaleNum(Integer goodsSaleNum) {
		this.goodsSaleNum = goodsSaleNum;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public Integer getGoodsMonthNum() {
		return goodsMonthNum;
	}
	public void setGoodsMonthNum(Integer goodsMonthNum) {
		this.goodsMonthNum = goodsMonthNum;
	}
	public String getStorePic() {
		return storePic;
	}
	public void setStorePic(String storePic) {
		this.storePic = storePic;
	}
	
	
}

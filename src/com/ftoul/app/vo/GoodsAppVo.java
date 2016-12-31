package com.ftoul.app.vo;

import java.util.List;


public class GoodsAppVo {
	
	private String id;//ID
	private String title;//商品名称
	private String price;//商品价格
	private String picSrc;//商品图片 主图
	private String quantity;//参加活动时 活动数量
	private String goodsBrandId;//商品品牌Id
	private Integer stock;//库存
	private String typeName;//活动类型
	private String shopId;//店铺Id
	private String businessClassifyId;//店铺分类Id
	private String goodsLabel;//商品标签
	private String subTitle;//副标题
	private String sumStock;//没有参加活动时 总库存
	private double freight;//运费
	private String grounding;//是否上架 1:上架 0：下架
	private String goodsEventName;//活动名称
	private String eventPrice;//活动价格
	private String storePic;//店铺图片
	private Integer goodsSaleNum;//销售量
	private Integer goodsNum;//全部商品数量
	private Integer goodsMonthNum;//上新商品
	
	private List<GoodsPropAppVo> goodsPropList;//商品规格
	
	private List<GoodsParamAppVo> goodsParamList;//商品参数
	
	
	private List<GoodsPicAppVo> goodsPicList;//前台商品图
	
	private List<GoodsPicAppVo> goodsPicInfoList;//前台商品详情图
	
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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getGoodsBrandId() {
		return goodsBrandId;
	}

	public void setGoodsBrandId(String goodsBrandId) {
		this.goodsBrandId = goodsBrandId;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getBusinessClassifyId() {
		return businessClassifyId;
	}

	public void setBusinessClassifyId(String businessClassifyId) {
		this.businessClassifyId = businessClassifyId;
	}

	public String getGoodsLabel() {
		return goodsLabel;
	}

	public void setGoodsLabel(String goodsLabel) {
		this.goodsLabel = goodsLabel;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getSumStock() {
		return sumStock;
	}

	public void setSumStock(String sumStock) {
		this.sumStock = sumStock;
	}

	public double getFreight() {
		return freight;
	}

	public void setFreight(double freight) {
		this.freight = freight;
	}

	public String getGrounding() {
		return grounding;
	}

	public void setGrounding(String grounding) {
		this.grounding = grounding;
	}

	public String getGoodsEventName() {
		return goodsEventName;
	}

	public void setGoodsEventName(String goodsEventName) {
		this.goodsEventName = goodsEventName;
	}

	public String getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(String eventPrice) {
		this.eventPrice = eventPrice;
	}

	
	public List<GoodsPropAppVo> getGoodsPropList() {
		return goodsPropList;
	}

	public void setGoodsPropList(List<GoodsPropAppVo> goodsPropList) {
		this.goodsPropList = goodsPropList;
	}

	public List<GoodsParamAppVo> getGoodsParamList() {
		return goodsParamList;
	}

	public void setGoodsParamList(List<GoodsParamAppVo> goodsParamList) {
		this.goodsParamList = goodsParamList;
	}

	public List<GoodsPicAppVo> getGoodsPicList() {
		return goodsPicList;
	}

	public void setGoodsPicList(List<GoodsPicAppVo> goodsPicList) {
		this.goodsPicList = goodsPicList;
	}

	public List<GoodsPicAppVo> getGoodsPicInfoList() {
		return goodsPicInfoList;
	}

	public void setGoodsPicInfoList(List<GoodsPicAppVo> goodsPicInfoList) {
		this.goodsPicInfoList = goodsPicInfoList;
	}

	public String getStorePic() {
		return storePic;
	}

	public void setStorePic(String storePic) {
		this.storePic = storePic;
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

	

}

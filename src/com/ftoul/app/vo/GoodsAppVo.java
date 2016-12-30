package com.ftoul.app.vo;

import java.util.List;

import com.ftoul.po.GoodsParam;
import com.ftoul.po.GoodsProp;
import com.ftoul.po.GoodsUploadpic;


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
	
	private List<GoodsProp> goodsPropList;//商品规格
	
	private List<GoodsParam> goodsParamList;//商品参数
	
	
	private List<GoodsUploadpic> goodsPicList;//前台商品图
	
	private List<GoodsUploadpic> goodsPicInfoList;//前台商品详情图
	
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

	public List<GoodsProp> getGoodsPropList() {
		return goodsPropList;
	}

	public void setGoodsPropList(List<GoodsProp> goodsPropList) {
		this.goodsPropList = goodsPropList;
	}

	public List<GoodsParam> getGoodsParamList() {
		return goodsParamList;
	}

	public void setGoodsParamList(List<GoodsParam> goodsParamList) {
		this.goodsParamList = goodsParamList;
	}

	public List<GoodsUploadpic> getGoodsPicList() {
		return goodsPicList;
	}

	public void setGoodsPicList(List<GoodsUploadpic> goodsPicList) {
		this.goodsPicList = goodsPicList;
	}

	public List<GoodsUploadpic> getGoodsPicInfoList() {
		return goodsPicInfoList;
	}

	public void setGoodsPicInfoList(List<GoodsUploadpic> goodsPicInfoList) {
		this.goodsPicInfoList = goodsPicInfoList;
	}
	
}

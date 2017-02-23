package com.ftoul.manage.goods.vo;

import java.util.List;

import com.ftoul.manage.advert.vo.UploadPicVo;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.GoodsProp;
import com.ftoul.po.GoodsUploadpic;


public class GoodsVo {
	
	private String id;
	private String goodsType1;
	private String goodsType2;
	private String goodsType3;
	private String goodsPropTypeId;
	private String goodsBrandId;
	private String code;
	private String grounding;
	private String title;
	private String subtitle;
	private String price;
	private String batchprice;
	private String costprice;
	private String deductionrate;
	private String crossborder;
	private String skuCode;
	private String pcInfo;
	private String picSrc;
	private String giveScore;
	private String giveLevel;
	private String mobilInfo;
	private String groundingTime;
	private String goodscanalId;
	private List<GoodsProp> goodsPropList;
	private List<GoodsParam> goodsParamList;
	private String saleSum;
	private String goodsEventName;//促销名称
	private String quantity;//活动数量
	private String eventPrice;//活动价格
	private String discount;//活动折扣
	private String createTime;
//	private Goods goods;
	private List<UploadPicVo> uploadPicMainList;//后台上传商品主图
	private List<UploadPicVo> uploadPicList;//后台上传商品图
	private List<UploadPicVo> uploadPicInfoList;//后台上传商品详情图
	private List<GoodsUploadpic> goodsPicList;//前台商品图
	private List<GoodsUploadpic> goodsPicInfoList;//前台商品详情图

	private Integer stock;//库存
	
	private String typeName;//活动类型
	private String homeChannel;
	
	private String shopId;
	
	private String businessClassifyId;//店铺分类Id
	
	private String goodsLabel;//商品标签
	
	private String subTitle;//副标题
	
	private String sumStock;//总库存
	
	private String place;//场地
	private double freight;//运费
	private String weight;//重量
	private String packingList;//包装清单
	private String afterService;//售后服务
	private String packingLength;//包装长
	private String packingWidth;//包装宽
	private String packingHeight;//包装高
	
	private String goodsTypeNameOne;//一级分类名称
	private String goodsTypeNameTwo;//二级分类名称
	private String goodsTypeNameThree;//三级分类名称
	private String fullCutName;//满减名称
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
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the uploadPicMainList
	 */
	public List<UploadPicVo> getUploadPicMainList() {
		return uploadPicMainList;
	}

	/**
	 * @param uploadPicMainList the uploadPicMainList to set
	 */
	public void setUploadPicMainList(List<UploadPicVo> uploadPicMainList) {
		this.uploadPicMainList = uploadPicMainList;
	}

	/**
	 * @return the uploadPicList
	 */
	public List<UploadPicVo> getUploadPicList() {
		return uploadPicList;
	}

	/**
	 * @param uploadPicList the uploadPicList to set
	 */
	public void setUploadPicList(List<UploadPicVo> uploadPicList) {
		this.uploadPicList = uploadPicList;
	}
	
	/**
	 * @return the uploadPicInfoList
	 */
	public List<UploadPicVo> getUploadPicInfoList() {
		return uploadPicInfoList;
	}

	/**
	 * @param uploadPicInfoList the uploadPicInfoList to set
	 */
	public void setUploadPicInfoList(List<UploadPicVo> uploadPicInfoList) {
		this.uploadPicInfoList = uploadPicInfoList;
	}
	
	/**
	 * @return the goodsPicList
	 */
	public List<GoodsUploadpic> getGoodsPicList() {
		return goodsPicList;
	}

	/**
	 * @param goodsPicList the goodsPicList to set
	 */
	public void setGoodsPicList(List<GoodsUploadpic> goodsPicList) {
		this.goodsPicList = goodsPicList;
	}

	/**
	 * @return the goodsPicInfoList
	 */
	public List<GoodsUploadpic> getGoodsPicInfoList() {
		return goodsPicInfoList;
	}

	/**
	 * @param goodsPicInfoList the goodsPicInfoList to set
	 */
	public void setGoodsPicInfoList(List<GoodsUploadpic> goodsPicInfoList) {
		this.goodsPicInfoList = goodsPicInfoList;
	}

	public String getId() {
		return id;
	}

	
	public String getBatchprice() {
		return batchprice;
	}

	public void setBatchprice(String batchprice) {
		this.batchprice = batchprice;
	}

	public String getCostprice() {
		return costprice;
	}

	public void setCostprice(String costprice) {
		this.costprice = costprice;
	}

	public String getDeductionrate() {
		return deductionrate;
	}

	public void setDeductionrate(String deductionrate) {
		this.deductionrate = deductionrate;
	}

	public String getCrossborder() {
		return crossborder;
	}

	public void setCrossborder(String crossborder) {
		this.crossborder = crossborder;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getGoodsType1() {
		return goodsType1;
	}

	public void setGoodsType1(String goodsType1) {
		this.goodsType1 = goodsType1;
	}

	public String getGoodsType2() {
		return goodsType2;
	}

	public void setGoodsType2(String goodsType2) {
		this.goodsType2 = goodsType2;
	}

	public String getGoodsType3() {
		return goodsType3;
	}

	public void setGoodsType3(String goodsType3) {
		this.goodsType3 = goodsType3;
	}

	public String getGoodsPropTypeId() {
		return goodsPropTypeId;
	}

	public void setGoodsPropTypeId(String goodsPropTypeId) {
		this.goodsPropTypeId = goodsPropTypeId;
	}

	public String getGoodsBrandId() {
		return goodsBrandId;
	}

	public void setGoodsBrandId(String goodsBrandId) {
		this.goodsBrandId = goodsBrandId;
	}

	public String getGrounding() {
		return grounding;
	}

	public void setGrounding(String grounding) {
		this.grounding = grounding;
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

	public String getPcInfo() {
		return pcInfo;
	}

	public void setPcInfo(String pcInfo) {
		this.pcInfo = pcInfo;
	}

	public String getMobilInfo() {
		return mobilInfo;
	}

	public void setMobilInfo(String mobilInfo) {
		this.mobilInfo = mobilInfo;
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


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getSaleSum() {
		return saleSum;
	}


	public void setSaleSum(String saleSum) {
		this.saleSum = saleSum;
	}

	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getGoodsEventName() {
		return goodsEventName;
	}


	public void setGoodsEventName(String goodsEventName) {
		this.goodsEventName = goodsEventName;
	}


	public String getGiveScore() {
		return giveScore;
	}


	public void setGiveScore(String giveScore) {
		this.giveScore = giveScore;
	}


	public String getGiveLevel() {
		return giveLevel;
	}


	public void setGiveLevel(String giveLevel) {
		this.giveLevel = giveLevel;
	}

	public String getGoodscanalId() {
		return goodscanalId;
	}

	public void setGoodscanalId(String goodscanalId) {
		this.goodscanalId = goodscanalId;
	}

	public String getGroundingTime() {
		return groundingTime;
	}

	public void setGroundingTime(String groundingTime) {
		this.groundingTime = groundingTime;
	}
	public String getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(String eventPrice) {
		this.eventPrice = eventPrice;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
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

	/**
	 * @return the homeChannel
	 */
	public String getHomeChannel() {
		return homeChannel;
	}

	/**
	 * @param homeChannel the homeChannel to set
	 */
	public void setHomeChannel(String homeChannel) {
		this.homeChannel = homeChannel;
	}
	
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
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

	public String getPackingList() {
		return packingList;
	}

	public void setPackingList(String packingList) {
		this.packingList = packingList;
	}

	public String getAfterService() {
		return afterService;
	}

	public void setAfterService(String afterService) {
		this.afterService = afterService;
	}

	public String getPackingLength() {
		return packingLength;
	}

	public void setPackingLength(String packingLength) {
		this.packingLength = packingLength;
	}

	public String getPackingWidth() {
		return packingWidth;
	}

	public void setPackingWidth(String packingWidth) {
		this.packingWidth = packingWidth;
	}

	public String getPackingHeight() {
		return packingHeight;
	}

	public void setPackingHeight(String packingHeight) {
		this.packingHeight = packingHeight;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getGoodsTypeNameOne() {
		return goodsTypeNameOne;
	}

	public void setGoodsTypeNameOne(String goodsTypeNameOne) {
		this.goodsTypeNameOne = goodsTypeNameOne;
	}

	public String getGoodsTypeNameTwo() {
		return goodsTypeNameTwo;
	}

	public void setGoodsTypeNameTwo(String goodsTypeNameTwo) {
		this.goodsTypeNameTwo = goodsTypeNameTwo;
	}

	public String getGoodsTypeNameThree() {
		return goodsTypeNameThree;
	}

	public void setGoodsTypeNameThree(String goodsTypeNameThree) {
		this.goodsTypeNameThree = goodsTypeNameThree;
	}
	public String getFullCutName() {
		return fullCutName;
	}

	public void setFullCutName(String fullCutName) {
		this.fullCutName = fullCutName;
	}
	
}

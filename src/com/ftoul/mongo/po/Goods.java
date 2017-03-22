package com.ftoul.mongo.po;

public class Goods implements java.io.Serializable {
	
	private static final long serialVersionUID = -2403238423478057883L;
	private String id;
	private User user;
	private String shopId;
	private String oneIndiana;
	private String title;
	private String subtitle;
	private Double price;
	private String deductionrate;
	private String skuCode;
	private String hasstock;
	private String place;
	private String grounding;
	private String crossborder;
	private String groundingTime;
	private int saleSum;
	private String undercarriageTime;
	private String giveScore;
	private String giveLevel;
	private String maxScorePrice;
	private String picSrc;
	private String code;
	private String pcInfo;
	private String mobilInfo;
	private String invoice;
	private String guarantee;
	private String volume;
	private String weight;
	private String lotteryNumber;
	private String trem;
	private String beginTime;
	private String recommend;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	private String state;
	private String step;
	private String businessClassifyId;
	private String goodsLabel;
	private String packingList;
	private String afterService;
	private String packingLength;
	private String packingWidth;
	private String packingHeight;
	// Constructors


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOneIndiana() {
		return this.oneIndiana;
	}

	public void setOneIndiana(String oneIndiana) {
		this.oneIndiana = oneIndiana;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDeductionrate() {
		return this.deductionrate;
	}

	public void setDeductionrate(String deductionrate) {
		this.deductionrate = deductionrate;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getGiveScore() {
		return this.giveScore;
	}

	public void setGiveScore(String giveScore) {
		this.giveScore = giveScore;
	}

	public String getGiveLevel() {
		return this.giveLevel;
	}

	public void setGiveLevel(String giveLevel) {
		this.giveLevel = giveLevel;
	}

	public String getMaxScorePrice() {
		return this.maxScorePrice;
	}

	public void setMaxScorePrice(String maxScorePrice) {
		this.maxScorePrice = maxScorePrice;
	}

	public String getPicSrc() {
		return this.picSrc;
	}

	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getPcInfo() {
		return this.pcInfo;
	}

	public void setPcInfo(String pcInfo) {
		this.pcInfo = pcInfo;
	}

	public String getMobilInfo() {
		return this.mobilInfo;
	}

	public void setMobilInfo(String mobilInfo) {
		this.mobilInfo = mobilInfo;
	}

	public String getInvoice() {
		return this.invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getGuarantee() {
		return this.guarantee;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getLotteryNumber() {
		return this.lotteryNumber;
	}

	public void setLotteryNumber(String lotteryNumber) {
		this.lotteryNumber = lotteryNumber;
	}

	public String getTrem() {
		return this.trem;
	}

	public void setTrem(String trem) {
		this.trem = trem;
	}

	public String getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getRecommend() {
		return this.recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreatePerson() {
		return this.createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyPerson() {
		return this.modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public String getState() {
		return this.state;
	}
	
	public String getStep() {
		return this.step;
	}
	public void setStep(String step) {
		this.step = step;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCrossborder() {
		return crossborder;
	}

	public void setCrossborder(String crossborder) {
		this.crossborder = crossborder;
	}
	
	public String getGrounding() {
		return grounding;
	}

	public void setGrounding(String grounding) {
		this.grounding = grounding;
	}

	public String getGroundingTime() {
		return groundingTime;
	}

	public void setGroundingTime(String groundingTime) {
		this.groundingTime = groundingTime;
	}

	public String getUndercarriageTime() {
		return undercarriageTime;
	}

	public void setUndercarriageTime(String undercarriageTime) {
		this.undercarriageTime = undercarriageTime;
	}
	public int getSaleSum() {
		return saleSum;
	}

	public void setSaleSum(int saleSum) {
		this.saleSum = saleSum;
	}

	
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	public String getSubtitle() {
		return this.subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
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
	
	public String getHasstock() {
		return this.hasstock;
	}

	public void setHasstock(String hasstock) {
		this.hasstock = hasstock;
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


}
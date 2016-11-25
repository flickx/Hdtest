package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods", catalog = "ftoul_shop")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)  
public class Goods implements java.io.Serializable {
	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -2403238423478057883L;
	private String id;
	private ShopFreightTemplate shopFreightTemplate;
	private User user;
	private String shopId;
	private GoodsCanal goodsCanal;
	private GoodsType goodsType1;
	private GoodsType goodsType2;
	private GoodsType goodsType3;
	private GoodsPropType goodsPropType;
	private GoodsBrand goodsBrand;
	private String oneIndiana;
	private String title;
	private String subtitle;
	private Double price;
	private String deductionrate;
	private String skuCode;
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


	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** full constructor */
	public Goods(ShopFreightTemplate shopFreightTemplate, User user,
			GoodsType goodsType1, GoodsPropType goodsPropType,
			GoodsBrand goodsBrand, String oneIndiana, String title,
			Double price, String place, String giveScore, String giveLevel,
			String maxScorePrice, String picSrc, String code, String pcInfo,
			String mobilInfo, String invoice, String guarantee, String volume,
			String weight, String lotteryNumber, String trem, String beginTime,
			String recommend, String createTime, String createPerson,
			String modifyTime, String modifyPerson, String state,String shopId) {
		this.shopFreightTemplate = shopFreightTemplate;
		this.shopId=shopId;
		this.user = user;
		this.goodsType1 = goodsType1;
		this.goodsPropType = goodsPropType;
		this.goodsBrand = goodsBrand;
		this.oneIndiana = oneIndiana;
		this.title = title;
		this.price = price;
		this.place = place;
		this.giveScore = giveScore;
		this.giveLevel = giveLevel;
		this.maxScorePrice = maxScorePrice;
		this.picSrc = picSrc;
		this.code = code;
		this.pcInfo = pcInfo;
		this.mobilInfo = mobilInfo;
		this.invoice = invoice;
		this.guarantee = guarantee;
		this.volume = volume;
		this.weight = weight;
		this.lotteryNumber = lotteryNumber;
		this.trem = trem;
		this.beginTime = beginTime;
		this.recommend = recommend;
		this.createTime = createTime;
		this.createPerson = createPerson;
		this.modifyTime = modifyTime;
		this.modifyPerson = modifyPerson;
		this.state = state;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "freight_template_id")
	public ShopFreightTemplate getShopFreightTemplate() {
		return this.shopFreightTemplate;
	}

	public void setShopFreightTemplate(ShopFreightTemplate shopFreightTemplate) {
		this.shopFreightTemplate = shopFreightTemplate;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lottery_user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_type1")
	public GoodsType getGoodsType1() {
		return this.goodsType1;
	}

	public void setGoodsType1(GoodsType goodsType1) {
		this.goodsType1 = goodsType1;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_canal_id")
	public GoodsCanal getGoodsCanal() {
		return this.goodsCanal;
	}

	public void setGoodsCanal(GoodsCanal goodsCanal) {
		this.goodsCanal = goodsCanal;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_prop_type_id")
	public GoodsPropType getGoodsPropType() {
		return this.goodsPropType;
	}

	public void setGoodsPropType(GoodsPropType goodsPropType) {
		this.goodsPropType = goodsPropType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_brand_id")
	public GoodsBrand getGoodsBrand() {
		return this.goodsBrand;
	}

	public void setGoodsBrand(GoodsBrand goodsBrand) {
		this.goodsBrand = goodsBrand;
	}

	@Column(name = "one_indiana", length = 32)
	public String getOneIndiana() {
		return this.oneIndiana;
	}

	public void setOneIndiana(String oneIndiana) {
		this.oneIndiana = oneIndiana;
	}

	@Column(name = "title", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "price", length = 12)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "deductionrate", length = 10)
	public String getDeductionrate() {
		return this.deductionrate;
	}

	public void setDeductionrate(String deductionrate) {
		this.deductionrate = deductionrate;
	}

	@Column(name = "place", length = 100)
	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Column(name = "give_score", length = 10)
	public String getGiveScore() {
		return this.giveScore;
	}

	public void setGiveScore(String giveScore) {
		this.giveScore = giveScore;
	}

	@Column(name = "give_level", length = 10)
	public String getGiveLevel() {
		return this.giveLevel;
	}

	public void setGiveLevel(String giveLevel) {
		this.giveLevel = giveLevel;
	}

	@Column(name = "max_score_price", length = 10)
	public String getMaxScorePrice() {
		return this.maxScorePrice;
	}

	public void setMaxScorePrice(String maxScorePrice) {
		this.maxScorePrice = maxScorePrice;
	}

	@Column(name = "pic_src", length = 500)
	public String getPicSrc() {
		return this.picSrc;
	}

	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	@Column(name = "code", length = 32)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	@Column(name = "sku_code", length = 32)
	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	@Column(name = "pc_info", length = 1000)
	public String getPcInfo() {
		return this.pcInfo;
	}

	public void setPcInfo(String pcInfo) {
		this.pcInfo = pcInfo;
	}

	@Column(name = "mobil_info", length = 1000)
	public String getMobilInfo() {
		return this.mobilInfo;
	}

	public void setMobilInfo(String mobilInfo) {
		this.mobilInfo = mobilInfo;
	}

	@Column(name = "invoice", length = 1)
	public String getInvoice() {
		return this.invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	@Column(name = "guarantee", length = 1)
	public String getGuarantee() {
		return this.guarantee;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}

	@Column(name = "volume", length = 32)
	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Column(name = "weight", length = 32)
	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Column(name = "lottery_number", length = 32)
	public String getLotteryNumber() {
		return this.lotteryNumber;
	}

	public void setLotteryNumber(String lotteryNumber) {
		this.lotteryNumber = lotteryNumber;
	}

	@Column(name = "trem", length = 32)
	public String getTrem() {
		return this.trem;
	}

	public void setTrem(String trem) {
		this.trem = trem;
	}

	@Column(name = "begin_time", length = 32)
	public String getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "recommend", length = 1)
	public String getRecommend() {
		return this.recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	@Column(name = "create_time", length = 32)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "create_person", length = 32)
	public String getCreatePerson() {
		return this.createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	@Column(name = "modify_time", length = 32)
	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "modify_person", length = 32)
	public String getModifyPerson() {
		return this.modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}
	
	@Column(name = "step", length = 1)
	public String getStep() {
		return this.step;
	}
	public void setStep(String step) {
		this.step = step;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "crossborder", length = 1)
	public String getCrossborder() {
		return crossborder;
	}

	public void setCrossborder(String crossborder) {
		this.crossborder = crossborder;
	}
	
	@Column(name = "grounding", length = 1)
	public String getGrounding() {
		return grounding;
	}

	public void setGrounding(String grounding) {
		this.grounding = grounding;
	}

	@Column(name = "grounding_time", length = 32)
	public String getGroundingTime() {
		return groundingTime;
	}

	public void setGroundingTime(String groundingTime) {
		this.groundingTime = groundingTime;
	}

	@Column(name = "undercarriage_time", length = 32)
	public String getUndercarriageTime() {
		return undercarriageTime;
	}

	public void setUndercarriageTime(String undercarriageTime) {
		this.undercarriageTime = undercarriageTime;
	}
	@Column(name = "sale_sum", length = 32)
	public int getSaleSum() {
		return saleSum;
	}

	public void setSaleSum(int saleSum) {
		this.saleSum = saleSum;
	}

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_type2")
	public GoodsType getGoodsType2() {
		return this.goodsType2;
	}

	public void setGoodsType2(GoodsType goodsType2) {
		this.goodsType2 = goodsType2;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_type3")
	public GoodsType getGoodsType3() {
		return this.goodsType3;
	}

	public void setGoodsType3(GoodsType goodsType3) {
		this.goodsType3 = goodsType3;
	}
	@Column(name = "shop_id", length = 32)
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	@Column(name = "subtitle", length = 100)
	public String getSubtitle() {
		return this.subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	
}
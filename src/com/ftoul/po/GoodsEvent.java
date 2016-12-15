package com.ftoul.po;

import java.math.BigDecimal;

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
 * GoodsEvent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods_event")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE) 
public class GoodsEvent implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8405558780694945296L;
	private String id;
	private String eventName;
	private String typeName;
	private String eventBegen;
	private String eventEnd;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	private String state;
	private String eventTime;
	private String code;
	private String discount;
	private String universal;
	private BigDecimal eventPrice;
	private BigDecimal exchangeRate;
	private String firstOrder;
	private String homeChannel;
	private String shopId;
	private BigDecimal target;
	private BigDecimal discountAmount;
	// Constructors

	/** default constructor */
	public GoodsEvent() {
	}

	/** full constructor */
	public GoodsEvent(String eventName, String typeName, String eventBegen,String eventTime,
			String eventEnd, String createTime,
			String createPerson, String modifyTime, String modifyPerson,
			String state) {
		this.eventName = eventName;
		this.typeName = typeName;
		this.eventBegen = eventBegen;
		this.eventEnd = eventEnd;
		this.createTime = createTime;
		this.createPerson = createPerson;
		this.modifyTime = modifyTime;
		this.modifyPerson = modifyPerson;
		this.state = state;
		this.eventTime=eventTime;
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
	
	@Column(name = "event_begen", length = 32)
	public String getEventBegen() {
		return this.eventBegen;
	}

	@Column(name = "type_name", length = 32)
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setEventBegen(String eventBegen) {
		this.eventBegen = eventBegen;
	}

	@Column(name = "event_end", length = 32)
	public String getEventEnd() {
		return this.eventEnd;
	}

	public void setEventEnd(String eventEnd) {
		this.eventEnd = eventEnd;
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

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "event_name", length = 32)
	public String getEventName() {
		return eventName;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	@Column(name = "event_time", length = 32)
	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	@Column(name = "event_code", length = 32)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	@Column(name = "discount", length = 32)
	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	@Column(name = "universal", length = 1)
	public String getUniversal() {
		return universal;
	}

	public void setUniversal(String universal) {
		this.universal = universal;
	}

	@Column(name = "event_price", length = 10)
	public BigDecimal getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(BigDecimal eventPrice) {
		this.eventPrice = eventPrice;
	}

	@Column(name = "exchange_rate", length = 10)
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Column(name = "first_order", length = 1)
	public String getFirstOrder() {
		return firstOrder;
	}
	
	public void setFirstOrder(String firstOrder) {
		this.firstOrder = firstOrder;
	}

	@Column(name = "home_channel", length = 1)
	public String getHomeChannel() {
		return homeChannel;
	}

	public void setHomeChannel(String homeChannel) {
		this.homeChannel = homeChannel;
	}

	@Column(name = "shop_id", length = 32)
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Column(name = "target", length = 10)
	public BigDecimal getTarget() {
		return target;
	}
	public void setTarget(BigDecimal target) {
		this.target = target;
	}
	@Column(name = "discount_amount", length = 10)
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	
}
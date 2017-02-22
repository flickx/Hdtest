package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Coupon entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "coupon")
public class Coupon implements java.io.Serializable {

	// Fields

	private String id;
	private BusinessStore businessStore;
	private String code;
	private String name;
	private Integer giveoutNum;
	private Integer receiveNum;
	private Double faceValue;
	private Double targetValue;
	private String vipLevel;
	private String receiveStartTime;
	private String receiveEndTime;
	private String validStartTime;
	private String validEndTime;
	private String giveoutType;
	private String receiveType;
	private String spreadType;
	private String couponType;
	private String useType;
	private String state;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;

	// Constructors

	/** default constructor */
	public Coupon() {
	}

	/** full constructor */
	public Coupon(BusinessStore businessStore, String name, Integer giveoutNum,
			Integer receiveNum, Double faceValue, Double targetValue,
			String vipLevel, String receiveStartTime, String receiveEndTime,
			String validStartTime, String validEndTime, String giveoutType,
			String receiveType, String spreadType, String couponType,
			String useType, String state, String createTime,
			String createPerson, String modifyTime, String modifyPerson) {
		this.businessStore = businessStore;
		this.name = name;
		this.giveoutNum = giveoutNum;
		this.receiveNum = receiveNum;
		this.faceValue = faceValue;
		this.targetValue = targetValue;
		this.vipLevel = vipLevel;
		this.receiveStartTime = receiveStartTime;
		this.receiveEndTime = receiveEndTime;
		this.validStartTime = validStartTime;
		this.validEndTime = validEndTime;
		this.giveoutType = giveoutType;
		this.receiveType = receiveType;
		this.spreadType = spreadType;
		this.couponType = couponType;
		this.useType = useType;
		this.state = state;
		this.createTime = createTime;
		this.createPerson = createPerson;
		this.modifyTime = modifyTime;
		this.modifyPerson = modifyPerson;
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
	@JoinColumn(name = "shop_id")
	public BusinessStore getBusinessStore() {
		return this.businessStore;
	}

	public void setBusinessStore(BusinessStore businessStore) {
		this.businessStore = businessStore;
	}
	
	@Column(name = "code", length = 32)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "giveout_num")
	public Integer getGiveoutNum() {
		return this.giveoutNum;
	}

	public void setGiveoutNum(Integer giveoutNum) {
		this.giveoutNum = giveoutNum;
	}

	@Column(name = "receive_num")
	public Integer getReceiveNum() {
		return this.receiveNum;
	}

	public void setReceiveNum(Integer receiveNum) {
		this.receiveNum = receiveNum;
	}

	@Column(name = "face_value", precision = 6)
	public Double getFaceValue() {
		return this.faceValue;
	}

	public void setFaceValue(Double faceValue) {
		this.faceValue = faceValue;
	}

	@Column(name = "target_value", precision = 6)
	public Double getTargetValue() {
		return this.targetValue;
	}

	public void setTargetValue(Double targetValue) {
		this.targetValue = targetValue;
	}

	@Column(name = "vip_level", length = 2)
	public String getVipLevel() {
		return this.vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
	}

	@Column(name = "receive_start_time", length = 32)
	public String getReceiveStartTime() {
		return this.receiveStartTime;
	}

	public void setReceiveStartTime(String receiveStartTime) {
		this.receiveStartTime = receiveStartTime;
	}

	@Column(name = "receive_end_time", length = 32)
	public String getReceiveEndTime() {
		return this.receiveEndTime;
	}

	public void setReceiveEndTime(String receiveEndTime) {
		this.receiveEndTime = receiveEndTime;
	}

	@Column(name = "valid_start_time", length = 32)
	public String getValidStartTime() {
		return this.validStartTime;
	}

	public void setValidStartTime(String validStartTime) {
		this.validStartTime = validStartTime;
	}

	@Column(name = "valid_end_time", length = 32)
	public String getValidEndTime() {
		return this.validEndTime;
	}

	public void setValidEndTime(String validEndTime) {
		this.validEndTime = validEndTime;
	}

	@Column(name = "giveout_type", length = 1)
	public String getGiveoutType() {
		return this.giveoutType;
	}

	public void setGiveoutType(String giveoutType) {
		this.giveoutType = giveoutType;
	}

	@Column(name = "receive_type", length = 1)
	public String getReceiveType() {
		return this.receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}

	@Column(name = "spread_type", length = 1)
	public String getSpreadType() {
		return this.spreadType;
	}

	public void setSpreadType(String spreadType) {
		this.spreadType = spreadType;
	}

	@Column(name = "coupon_type", length = 1)
	public String getCouponType() {
		return this.couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	@Column(name = "use_type", length = 1)
	public String getUseType() {
		return this.useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
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

}
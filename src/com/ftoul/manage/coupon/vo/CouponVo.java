package com.ftoul.manage.coupon.vo;

import java.util.List;

public class CouponVo {
	
	private String id;
	private String code;
	private String type;
	private String useType;
	private String name;
	private String faceValue;
	private String targetValue;
	private String giveNumber;
	private String receiveNumber;
	private String validStartTime;
	private String validEndTime;
	private String createTime;
	private String giveoutType;
	private List<Object> typeList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}
	public String getGiveNumber() {
		return giveNumber;
	}
	public void setGiveNumber(String giveNumber) {
		this.giveNumber = giveNumber;
	}
	public String getReceiveNumber() {
		return receiveNumber;
	}
	public void setReceiveNumber(String receiveNumber) {
		this.receiveNumber = receiveNumber;
	}
	public String getValidStartTime() {
		return validStartTime;
	}
	public void setValidStartTime(String validStartTime) {
		this.validStartTime = validStartTime;
	}
	public String getValidEndTime() {
		return validEndTime;
	}
	public void setValidEndTime(String validEndTime) {
		this.validEndTime = validEndTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public List<Object> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<Object> typeList) {
		this.typeList = typeList;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getGiveoutType() {
		return giveoutType;
	}
	public void setGiveoutType(String giveoutType) {
		this.giveoutType = giveoutType;
	}
	
}

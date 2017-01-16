package com.ftoul.web.vo;

import java.util.List;

import com.ftoul.po.AfterOpLog;

public class AfterScheduleVo {
	
	private String id;
	private String userId;
	private String orderId;
	private String orderStatic;
	private String goodsName;
	private String backPrice;
	private String num;
	private String pic;
	private String orderTime;
	private String serviceCode;
	private String scheduleStatic;
	private String logCompany;
	private String logOdd;
	private String tel;
	private String state;
	private String salePrice;
	private List<Object> list;
	private String createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatic() {
		return orderStatic;
	}
	public void setOrderStatic(String orderStatic) {
		this.orderStatic = orderStatic;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getScheduleStatic() {
		return scheduleStatic;
	}
	public void setScheduleStatic(String scheduleStatic) {
		this.scheduleStatic = scheduleStatic;
	}
	public String getLogOdd() {
		return logOdd;
	}
	public void setLogOdd(String logOdd) {
		this.logOdd = logOdd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBackPrice() {
		return backPrice;
	}
	public void setBackPrice(String backPrice) {
		this.backPrice = backPrice;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getLogCompany() {
		return logCompany;
	}
	public void setLogCompany(String logCompany) {
		this.logCompany = logCompany;
	}
	public String getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}

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
 * AfterSchedule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "after_schedule")
public class AfterSchedule implements java.io.Serializable {

	// Fields

	private String id;
	private OrdersDetail ordersDetail;
	private User user;
	private String serviceCode;
	private String scheduleStatic;
	private String customsClearanceStatic;
	private LogisticsCompany sellerLogCompany;
	private String sellerConsigee;
	private String sellerLogOdd;
	private String sellerLogInfo;
	private String sellerAddress;
	private String sellerTel;
	private LogisticsCompany buyerLogCompany;
	private String buyerConsigee;
	private String buyerLogOdd;
	private String buyerLogInfo;
	private String buyerAddress;
	private String buyerTel;
	private String type;
	private String reason;
	private String picSrcs;
	private String backPrice;
	private String num;
	private String msg;
	private String state;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	
	// Constructors

	/** default constructor */
	public AfterSchedule() {
	}

	/** full constructor */
	public AfterSchedule(OrdersDetail ordersDetail, String serviceCode,
			String scheduleStatic, LogisticsCompany logCompany, String logOdd,
			String tel, String state, String createTime, String createPerson,
			String modifyTime, String modifyPerson) {
		this.ordersDetail = ordersDetail;
		this.serviceCode = serviceCode;
		this.scheduleStatic = scheduleStatic;
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
	@JoinColumn(name = "orders_detail_id")
	public OrdersDetail getOrdersDetail() {
		return ordersDetail;
	}

	public void setOrdersDetail(OrdersDetail ordersDetail) {
		this.ordersDetail = ordersDetail;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "service_code", length = 32)
	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	@Column(name = "schedule_static", length = 1)
	public String getScheduleStatic() {
		return this.scheduleStatic;
	}

	public void setScheduleStatic(String scheduleStatic) {
		this.scheduleStatic = scheduleStatic;
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
	
	@Column(name = "type", length = 32)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "reason", length = 32)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "back_price", length = 32)
	public String getBackPrice() {
		return backPrice;
	}

	public void setBackPrice(String backPrice) {
		this.backPrice = backPrice;
	}

	@Column(name = "num", length = 32)
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Column(name = "msg", length = 1000)
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Column(name="customs_clearance_static", length=32)
	public String getCustomsClearanceStatic() {
		return customsClearanceStatic;
	}

	public void setCustomsClearanceStatic(String customsClearanceStatic) {
		this.customsClearanceStatic = customsClearanceStatic;
	}

	@Column(name="pic_srcs", length=1000)
	public String getPicSrcs() {
		return picSrcs;
	}

	public void setPicSrcs(String picSrcs) {
		this.picSrcs = picSrcs;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "seller_log_company")
	public LogisticsCompany getSellerLogCompany() {
		return sellerLogCompany;
	}

	public void setSellerLogCompany(LogisticsCompany sellerLogCompany) {
		this.sellerLogCompany = sellerLogCompany;
	}
	
	@Column(name="seller_consigee", length=32)
	public String getSellerConsigee() {
		return sellerConsigee;
	}

	public void setSellerConsigee(String sellerConsigee) {
		this.sellerConsigee = sellerConsigee;
	}
	
	@Column(name="seller_log_odd", length=32)
	public String getSellerLogOdd() {
		return sellerLogOdd;
	}

	public void setSellerLogOdd(String sellerLogOdd) {
		this.sellerLogOdd = sellerLogOdd;
	}
	
	@Column(name="seller_log_info", length=32)
	public String getSellerLogInfo() {
		return sellerLogInfo;
	}

	public void setSellerLogInfo(String sellerLogInfo) {
		this.sellerLogInfo = sellerLogInfo;
	}
	
	@Column(name="seller_address", length=32)
	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}
	
	@Column(name="seller_tel", length=32)
	public String getSellerTel() {
		return sellerTel;
	}

	public void setSellerTel(String sellerTel) {
		this.sellerTel = sellerTel;
	}
	
	@Column(name="buyer_consigee", length=32)
	public String getBuyerConsigee() {
		return buyerConsigee;
	}

	public void setBuyerConsigee(String buyerConsigee) {
		this.buyerConsigee = buyerConsigee;
	}

	@Column(name="buyer_log_odd", length=32)
	public String getBuyerLogOdd() {
		return buyerLogOdd;
	}

	public void setBuyerLogOdd(String buyerLogOdd) {
		this.buyerLogOdd = buyerLogOdd;
	}

	@Column(name="buyer_log_info", length=32)
	public String getBuyerLogInfo() {
		return buyerLogInfo;
	}

	public void setBuyerLogInfo(String buyerLogInfo) {
		this.buyerLogInfo = buyerLogInfo;
	}

	@Column(name="buyer_address", length=32)
	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	@Column(name="buyer_tel", length=32)
	public String getBuyerTel() {
		return buyerTel;
	}

	public void setBuyerTel(String buyerTel) {
		this.buyerTel = buyerTel;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "buyer_log_company")
	public LogisticsCompany getBuyerLogCompany() {
		return buyerLogCompany;
	}

	public void setBuyerLogCompany(LogisticsCompany buyerLogCompany) {
		this.buyerLogCompany = buyerLogCompany;
	}

	
	
}
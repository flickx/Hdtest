package com.ftoul.po;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Business entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business", catalog = "ftoul_shop")
public class Business implements java.io.Serializable {

	// Fields

	private String id;
	private BusinessManage businessManage;
	private BusinessBase businessBase;
	private BusinessBank businessBank;
	private String operateId;
	private String operateTime;
	private String createId;
	private String createTime;
	private String state;

	// Constructors

	/** default constructor */
	public Business() {
	}

	/** full constructor */
	public Business(BusinessManage businessManage, BusinessBase businessBase,
			BusinessBank businessBank, String operateId, String operateTime,
			String createId, String createTime, String state,
			Set<BusinessStore> businessStores) {
		this.businessManage = businessManage;
		this.businessBase = businessBase;
		this.businessBank = businessBank;
		this.operateId = operateId;
		this.operateTime = operateTime;
		this.createId = createId;
		this.createTime = createTime;
		this.state = state;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manage_id")
	public BusinessManage getBusinessManage() {
		return this.businessManage;
	}

	public void setBusinessManage(BusinessManage businessManage) {
		this.businessManage = businessManage;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "base_id")
	public BusinessBase getBusinessBase() {
		return this.businessBase;
	}

	public void setBusinessBase(BusinessBase businessBase) {
		this.businessBase = businessBase;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bank_id")
	public BusinessBank getBusinessBank() {
		return this.businessBank;
	}

	public void setBusinessBank(BusinessBank businessBank) {
		this.businessBank = businessBank;
	}

	@Column(name = "operate_id", length = 100)
	public String getOperateId() {
		return this.operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	@Column(name = "operate_time", length = 100)
	public String getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "create_id", length = 100)
	public String getCreateId() {
		return this.createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	@Column(name = "create_time", length = 50)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}


}
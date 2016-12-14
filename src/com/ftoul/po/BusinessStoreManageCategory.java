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
 * BusinessStoreManageCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_store_manage_category")
public class BusinessStoreManageCategory implements java.io.Serializable {

	// Fields

	private String id;
	private String storeId;
	private String firstCategory;
	private String twoCategory;
	private String threeCategory;
	private String operateId;
	private String operateTime;
	private String createId;
	private String createTime;
	private String state;

	// Constructors

	/** default constructor */
	public BusinessStoreManageCategory() {
	}

	/** full constructor */
	public BusinessStoreManageCategory(String storeId,
			String firstCategory, String twoCategory, String threeCategory,
			String operateId, String operateTime, String createId,
			String createTime, String state) {
		this.storeId = storeId;
		this.firstCategory = firstCategory;
		this.twoCategory = twoCategory;
		this.threeCategory = threeCategory;
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

	@Column(name = "store_id",length=100)
	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@Column(name = "first_category", length = 100)
	public String getFirstCategory() {
		return this.firstCategory;
	}

	public void setFirstCategory(String firstCategory) {
		this.firstCategory = firstCategory;
	}

	@Column(name = "two_category", length = 100)
	public String getTwoCategory() {
		return this.twoCategory;
	}

	public void setTwoCategory(String twoCategory) {
		this.twoCategory = twoCategory;
	}

	@Column(name = "three_category", length = 100)
	public String getThreeCategory() {
		return this.threeCategory;
	}

	public void setThreeCategory(String threeCategory) {
		this.threeCategory = threeCategory;
	}

	@Column(name = "operate_id", length = 100)
	public String getOperateId() {
		return this.operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	@Column(name = "operate_time", length = 50)
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

	@Column(name = "state", length = 0)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
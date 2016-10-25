package com.ftoul.po;

import java.util.HashSet;
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
 * BusinessStore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_store", catalog = "ftoul_shop")
public class BusinessStore implements java.io.Serializable {

	// Fields

	private String id;
	private BusinessStoreRank businessStoreRank;
	private Business business;
	private BusinessStoreClassify businessStoreClassify;
	private String pic;
	private String storeName;
	private String storeDuration;
	private String operateId;
	private String operateTime;
	private String createId;
	private String createTime;
	private String verifyId;
	private String verifyTime;
	private String state;

	// Constructors

	/** default constructor */
	public BusinessStore() {
	}

	/** minimal constructor */
	public BusinessStore(String pic, String storeName, String storeDuration,
			String operateId) {
		this.pic = pic;
		this.storeName = storeName;
		this.storeDuration = storeDuration;
		this.operateId = operateId;
	}

	/** full constructor */
	public BusinessStore(BusinessStoreRank businessStoreRank,
			Business business, BusinessStoreClassify businessStoreClassify,
			String pic, String storeName, String storeDuration,
			String operateId, String operateTime, String createId,
			String createTime, String verifyId, String verifyTime,
			String state, Set<BusinessStoreLogin> businessStoreLogins,
			Set<BusinessStoreManageCategory> businessStoreManageCategories) {
		this.businessStoreRank = businessStoreRank;
		this.business = business;
		this.businessStoreClassify = businessStoreClassify;
		this.pic = pic;
		this.storeName = storeName;
		this.storeDuration = storeDuration;
		this.operateId = operateId;
		this.operateTime = operateTime;
		this.createId = createId;
		this.createTime = createTime;
		this.verifyId = verifyId;
		this.verifyTime = verifyTime;
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
	@JoinColumn(name = "rank_id")
	public BusinessStoreRank getBusinessStoreRank() {
		return this.businessStoreRank;
	}

	public void setBusinessStoreRank(BusinessStoreRank businessStoreRank) {
		this.businessStoreRank = businessStoreRank;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "business_id")
	public Business getBusiness() {
		return this.business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "classify_id")
	public BusinessStoreClassify getBusinessStoreClassify() {
		return this.businessStoreClassify;
	}

	public void setBusinessStoreClassify(
			BusinessStoreClassify businessStoreClassify) {
		this.businessStoreClassify = businessStoreClassify;
	}

	@Column(name = "pic", nullable = false, length = 500)
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name = "store_name", nullable = false, length = 100)
	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column(name = "store_duration", nullable = false, length = 50)
	public String getStoreDuration() {
		return this.storeDuration;
	}

	public void setStoreDuration(String storeDuration) {
		this.storeDuration = storeDuration;
	}

	@Column(name = "operate_id", nullable = false, length = 100)
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

	@Column(name = "verify_id", length = 100)
	public String getVerifyId() {
		return this.verifyId;
	}

	public void setVerifyId(String verifyId) {
		this.verifyId = verifyId;
	}

	@Column(name = "verify_time", length = 50)
	public String getVerifyTime() {
		return this.verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
package com.ftoul.po;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * BusinessStoreRank entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_store_rank")
public class BusinessStoreRank implements java.io.Serializable {

	// Fields

	private String id;
	private String storeRank;
	private String storeCost;
	private String serverComment;
	private String operateId;
	private String operateTime;
	private String createId;
	private String createTime;
	private String state;

	// Constructors

	/** default constructor */
	public BusinessStoreRank() {
	}

	/** minimal constructor */
	public BusinessStoreRank(String storeRank, String storeCost) {
		this.storeRank = storeRank;
		this.storeCost = storeCost;
	}

	/** full constructor */
	public BusinessStoreRank(String storeRank, String storeCost,
			String serverComment, String operateId, String operateTime,
			String createId, String createTime, String state,
			Set<BusinessStore> businessStores) {
		this.storeRank = storeRank;
		this.storeCost = storeCost;
		this.serverComment = serverComment;
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

	@Column(name = "store_rank", nullable = false, length = 50)
	public String getStoreRank() {
		return this.storeRank;
	}

	public void setStoreRank(String storeRank) {
		this.storeRank = storeRank;
	}

	@Column(name = "store_cost", nullable = false, length = 50)
	public String getStoreCost() {
		return this.storeCost;
	}

	public void setStoreCost(String storeCost) {
		this.storeCost = storeCost;
	}

	@Column(name = "server_comment", length = 500)
	public String getServerComment() {
		return this.serverComment;
	}

	public void setServerComment(String serverComment) {
		this.serverComment = serverComment;
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

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
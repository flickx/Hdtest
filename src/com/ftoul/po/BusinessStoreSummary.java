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
 * BusinessStoreSummary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_store_summary")
public class BusinessStoreSummary implements java.io.Serializable {

	// Fields

	private String id;
	private BusinessStore businessStore;
	private String summary;
	private String operateId;
	private String operateTime;
	private String createId;
	private String createTime;
	private String state;

	// Constructors

	/** default constructor */
	public BusinessStoreSummary() {
	}

	/** full constructor */
	public BusinessStoreSummary(BusinessStore businessStore, String summary,
			String operateId, String operateTime, String createId,
			String createTime, String state) {
		this.businessStore = businessStore;
		this.summary = summary;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	public BusinessStore getBusinessStore() {
		return this.businessStore;
	}

	public void setBusinessStore(BusinessStore businessStore) {
		this.businessStore = businessStore;
	}

	@Column(name = "summary", length = 1000)
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "operate_id", length = 100)
	public String getOperateId() {
		return this.operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	@Column(name = "operate_time", length = 20)
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

	@Column(name = "create_time", length = 20)
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
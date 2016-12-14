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
 * BusinessStoreClassify entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_store_classify")
public class BusinessStoreClassify implements java.io.Serializable {

	// Fields

	private String id;
	private String storeType;
	private String storeClassify;
	private String classifyComment;
	private String operateId;
	private String operateTime;
	private String createId;
	private String createTime;
	private String state;

	// Constructors

	/** default constructor */
	public BusinessStoreClassify() {
	}

	/** minimal constructor */
	public BusinessStoreClassify(String storeType, String storeClassify) {
		this.storeType = storeType;
		this.storeClassify = storeClassify;
	}

	/** full constructor */
	public BusinessStoreClassify(String storeType, String storeClassify,
			String classifyComment, String operateId, String operateTime,
			String createId, String createTime, String state,
			Set<BusinessStore> businessStores) {
		this.storeType = storeType;
		this.storeClassify = storeClassify;
		this.classifyComment = classifyComment;
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

	@Column(name = "store_type", nullable = false, length = 50)
	public String getStoreType() {
		return this.storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	@Column(name = "store_classify", nullable = false, length = 50)
	public String getStoreClassify() {
		return this.storeClassify;
	}

	public void setStoreClassify(String storeClassify) {
		this.storeClassify = storeClassify;
	}

	@Column(name = "classify_comment", length = 100)
	public String getClassifyComment() {
		return this.classifyComment;
	}

	public void setClassifyComment(String classifyComment) {
		this.classifyComment = classifyComment;
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
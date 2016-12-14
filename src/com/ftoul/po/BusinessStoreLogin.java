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
 * BusinessStoreLogin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_store_login")
public class BusinessStoreLogin implements java.io.Serializable {

	// Fields

	private String id;
	private BusinessStore businessStore;
	private String storeAccount;
	private String password;
	private String loginTIme;
	private String loginIp;
	private String operateId;
	private String operateTime;
	private String createId;
	private String createTime;
	private String state;
	// Constructors

	/** default constructor */
	public BusinessStoreLogin() {
	}

	/** minimal constructor */
	public BusinessStoreLogin(String storeAccount, String password) {
		this.storeAccount = storeAccount;
		this.password = password;
	}

	/** full constructor */
	public BusinessStoreLogin(BusinessStore businessStore, String storeAccount,
			String password, String loginTIme, String loginIp,
			String operateId, String operateTime, String createId,
			String createTime, String state) {
		this.businessStore = businessStore;
		this.storeAccount = storeAccount;
		this.password = password;
		this.loginTIme = loginTIme;
		this.loginIp = loginIp;
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
	@JoinColumn(name = "store_id")
	public BusinessStore getBusinessStore() {
		return this.businessStore;
	}

	public void setBusinessStore(BusinessStore businessStore) {
		this.businessStore = businessStore;
	}

	@Column(name = "store_account", nullable = false, length = 100)
	public String getStoreAccount() {
		return this.storeAccount;
	}

	public void setStoreAccount(String storeAccount) {
		this.storeAccount = storeAccount;
	}

	@Column(name = "password", nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "login_tIme", length = 50)
	public String getLoginTIme() {
		return this.loginTIme;
	}

	public void setLoginTIme(String loginTIme) {
		this.loginTIme = loginTIme;
	}

	@Column(name = "login_ip", length = 50)
	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
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
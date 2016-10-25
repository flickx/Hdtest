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
 * BusinessBase entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_base", catalog = "ftoul_shop")
public class BusinessBase implements java.io.Serializable {

	// Fields

	private String id;
	private String companyAddress;
	private String companyName;
	private String registeredCapital;
	private String linkmanName;
	private String linkmanNumber;
	private String email;
	private String operateId;
	private String operateTime;
	private String createId;
	private String createTime;
	private String state;

	// Constructors

	/** default constructor */
	public BusinessBase() {
	}

	/** minimal constructor */
	public BusinessBase(String companyAddress, String companyName,
			String linkmanName, String linkmanNumber, String email) {
		this.companyAddress = companyAddress;
		this.companyName = companyName;
		this.linkmanName = linkmanName;
		this.linkmanNumber = linkmanNumber;
		this.email = email;
	}

	/** full constructor */
	public BusinessBase(String companyAddress, String companyName,
			String registeredCapital, String linkmanName, String linkmanNumber,
			String email, String operateId, String operateTime,
			String createId, String createTime, String state,
			Set<Business> businesses) {
		this.companyAddress = companyAddress;
		this.companyName = companyName;
		this.registeredCapital = registeredCapital;
		this.linkmanName = linkmanName;
		this.linkmanNumber = linkmanNumber;
		this.email = email;
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

	@Column(name = "company_address", nullable = false, length = 500)
	public String getCompanyAddress() {
		return this.companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	@Column(name = "company_name", nullable = false, length = 100)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "registered_capital", length = 50)
	public String getRegisteredCapital() {
		return this.registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	@Column(name = "linkman_name", nullable = false, length = 50)
	public String getLinkmanName() {
		return this.linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	@Column(name = "linkman_number", nullable = false, length = 50)
	public String getLinkmanNumber() {
		return this.linkmanNumber;
	}

	public void setLinkmanNumber(String linkmanNumber) {
		this.linkmanNumber = linkmanNumber;
	}

	@Column(name = "email", nullable = false, length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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
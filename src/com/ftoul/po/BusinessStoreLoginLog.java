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
 * BusinessStoreLoginLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_store_login_log")
public class BusinessStoreLoginLog implements java.io.Serializable {

	// Fields

	private String id;
	private BusinessStoreLogin businessStoreLogin;
	private String operation;
	private String methodPackage;
	private String methodName;
	private String prams;
	private String operationTime;
	private String ipAddress;
	private String resStatic;
	private String resText;

	// Constructors

	/** default constructor */
	public BusinessStoreLoginLog() {
	}

	/** full constructor */
	public BusinessStoreLoginLog(BusinessStoreLogin businessStoreLogin,
			String operation, String methodPackage, String methodName,
			String prams, String operationTime, String ipAddress,
			String resStatic, String resText) {
		this.businessStoreLogin = businessStoreLogin;
		this.operation = operation;
		this.methodPackage = methodPackage;
		this.methodName = methodName;
		this.prams = prams;
		this.operationTime = operationTime;
		this.ipAddress = ipAddress;
		this.resStatic = resStatic;
		this.resText = resText;
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
	@JoinColumn(name = "login_id")
	public BusinessStoreLogin getBusinessStoreLogin() {
		return this.businessStoreLogin;
	}

	public void setBusinessStoreLogin(BusinessStoreLogin businessStoreLogin) {
		this.businessStoreLogin = businessStoreLogin;
	}

	@Column(name = "operation", length = 20)
	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Column(name = "method_package", length = 20)
	public String getMethodPackage() {
		return this.methodPackage;
	}

	public void setMethodPackage(String methodPackage) {
		this.methodPackage = methodPackage;
	}

	@Column(name = "method_name", length = 20)
	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	@Column(name = "prams", length = 100)
	public String getPrams() {
		return this.prams;
	}

	public void setPrams(String prams) {
		this.prams = prams;
	}

	@Column(name = "operation_time", length = 20)
	public String getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	@Column(name = "ip_address", length = 20)
	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name = "res_static", length = 10)
	public String getResStatic() {
		return this.resStatic;
	}

	public void setResStatic(String resStatic) {
		this.resStatic = resStatic;
	}

	@Column(name = "res_text", length = 100)
	public String getResText() {
		return this.resText;
	}

	public void setResText(String resText) {
		this.resText = resText;
	}

}
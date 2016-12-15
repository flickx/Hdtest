package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * LogisticsCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "logistics_company")
public class LogisticsCompany implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private String name;
	private String kdType;

	// Constructors

	/** default constructor */
	public LogisticsCompany() {
	}

	/** full constructor */
	public LogisticsCompany(String code, String name, String kdType) {
		this.code = code;
		this.name = name;
		this.kdType = kdType;
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

	@Column(name = "code", length = 32)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "kd_type", length = 1)
	public String getKdType() {
		return this.kdType;
	}

	public void setKdType(String kdType) {
		this.kdType = kdType;
	}

}
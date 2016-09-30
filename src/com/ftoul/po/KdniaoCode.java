package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * KdniaoCode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "kdniao_code", catalog = "ftoul_shop")
public class KdniaoCode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8459596277452923694L;
	private Integer id;
	private String code;
	private String nam;
	private String kdType;

	// Constructors

	/** default constructor */
	public KdniaoCode() {
	}

	/** full constructor */
	public KdniaoCode(String code, String nam, String kdType) {
		this.code = code;
		this.nam = nam;
		this.kdType = kdType;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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
	public String getNam() {
		return this.nam;
	}

	public void setNam(String nam) {
		this.nam = nam;
	}

	@Column(name = "kd_type", length = 1)
	public String getKdType() {
		return this.kdType;
	}

	public void setKdType(String kdType) {
		this.kdType = kdType;
	}

}
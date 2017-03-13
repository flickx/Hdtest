package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * CrossBorderMuseum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cross_border_museum", catalog = "ftoul_shop")
public class CrossBorderMuseum implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private String name;
	private String picSrc;
	private String createTime;
	private String modifyTime;
	private String state;

	// Constructors

	/** default constructor */
	public CrossBorderMuseum() {
	}

	/** full constructor */
	public CrossBorderMuseum(String code, String name, String picSrc,
			String createTime, String modifyTime, String state) {
		this.code = code;
		this.name = name;
		this.picSrc = picSrc;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.state = state;
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

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "pic_src")
	public String getPicSrc() {
		return this.picSrc;
	}

	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	@Column(name = "create_time", length = 32)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "modify_time", length = 32)
	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
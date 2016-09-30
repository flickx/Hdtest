package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * JPositionProvice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "j_position_provice", catalog = "ftoul_shop")
public class JPositionProvice implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8354247476592989493L;
	private Integer id;
	private Long proviceId;
	private String proviceName;

	// Constructors

	/** default constructor */
	public JPositionProvice() {
	}

	/** full constructor */
	public JPositionProvice(Long proviceId, String proviceName) {
		this.proviceId = proviceId;
		this.proviceName = proviceName;
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

	@Column(name = "provice_id")
	public Long getProviceId() {
		return this.proviceId;
	}

	public void setProviceId(Long proviceId) {
		this.proviceId = proviceId;
	}

	@Column(name = "provice_name", length = 64)
	public String getProviceName() {
		return this.proviceName;
	}

	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}

}
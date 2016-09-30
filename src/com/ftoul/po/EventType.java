package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * 活动类型实体类
 */
@Entity
@Table(name = "event_type", catalog = "ftoul_shop", uniqueConstraints = @UniqueConstraint(columnNames = "type_code"))
public class EventType implements java.io.Serializable {

	// Fields

	private String id;
	private String typeCode;
	private String typeName;
	private String state;

	// Constructors

	/** default constructor */
	public EventType() {
	}

	/** full constructor */
	public EventType(String typeCode, String typeName, String state) {
		this.typeCode = typeCode;
		this.typeName = typeName;
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

	@Column(name = "type_code", unique = true, length = 32)
	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Column(name = "type_name", length = 32)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * SearchKeyName entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "search_key_name")
public class SearchKeyName implements java.io.Serializable {

	// Fields

	private String id;
	private String keyName;
	private String fontColor;
	private String state;

	// Constructors

	/** default constructor */
	public SearchKeyName() {
	}

	/** full constructor */
	public SearchKeyName(String keyName, String fontColor, String state) {
		this.keyName = keyName;
		this.fontColor = fontColor;
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

	@Column(name = "key_name", length = 50)
	public String getKeyName() {
		return this.keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	@Column(name = "font_color", length = 50)
	public String getFontColor() {
		return this.fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
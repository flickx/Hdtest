package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * AppSet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_set", catalog = "ftoul_shop")
public class AppSet implements java.io.Serializable {

	// Fields

	private String id;
	private String version;
	private String url;

	// Constructors

	/** default constructor */
	public AppSet() {
	}

	/** full constructor */
	public AppSet(String version, String url) {
		this.version = version;
		this.url = url;
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

	@Column(name = "version", length = 10)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
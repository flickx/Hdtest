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
@Table(name = "app_set")
public class AppSet implements java.io.Serializable {

	// Fields

	private String id;
	private String version;
	private String url;
	private String content;
	private String type;
	private String appUrl;
	private String iosUrl;
	private String appUpload;
	private String iosUpload;
	

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

	@Column(name = "content", length = 500)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "type", length = 1)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "app_url", length = 500)
	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	@Column(name = "ios_url", length = 500)
	public String getIosUrl() {
		return iosUrl;
	}

	public void setIosUrl(String iosUrl) {
		this.iosUrl = iosUrl;
	}
	
	@Column(name = "app_upload", length = 10)
	public String getAppUpload() {
		return appUpload;
	}

	public void setAppUpload(String appUpload) {
		this.appUpload = appUpload;
	}
	
	@Column(name = "ios_upload", length = 10)
	public String getIosUpload() {
		return iosUpload;
	}

	public void setIosUpload(String iosUpload) {
		this.iosUpload = iosUpload;
	}

}
package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * SiteInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "site_info", catalog = "ftoul_shop")
public class SiteInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String tag;
	private String description;
	private String url;
	private String logoImg;
	private String topImg;
	private String topHeight;
	private String icp;
	private String businessPermit;
	private String copyright;

	// Constructors

	/** default constructor */
	public SiteInfo() {
	}

	/** full constructor */
	public SiteInfo(String name, String tag, String description, String url,
			String logoImg, String topImg, String topHeight, String icp,
			String businessPermit, String copyright) {
		this.name = name;
		this.tag = tag;
		this.description = description;
		this.url = url;
		this.logoImg = logoImg;
		this.topImg = topImg;
		this.topHeight = topHeight;
		this.icp = icp;
		this.businessPermit = businessPermit;
		this.copyright = copyright;
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

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "tag", length = 100)
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "url", length = 32)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "logo_img")
	public String getLogoImg() {
		return this.logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	@Column(name = "top_img")
	public String getTopImg() {
		return this.topImg;
	}

	public void setTopImg(String topImg) {
		this.topImg = topImg;
	}

	@Column(name = "top_height", length = 10)
	public String getTopHeight() {
		return this.topHeight;
	}

	public void setTopHeight(String topHeight) {
		this.topHeight = topHeight;
	}

	@Column(name = "icp", length = 100)
	public String getIcp() {
		return this.icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}

	@Column(name = "business_permit", length = 200)
	public String getBusinessPermit() {
		return this.businessPermit;
	}

	public void setBusinessPermit(String businessPermit) {
		this.businessPermit = businessPermit;
	}

	@Column(name = "copyright", length = 200)
	public String getCopyright() {
		return this.copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

}
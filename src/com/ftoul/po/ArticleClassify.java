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
 * ArticleClassify entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "article_classify", catalog = "ftoul_shop")
public class ArticleClassify implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String anotherName;
	private String sort;
	private ArticleClassify classify;
	private String description;
	private String createTime;
	private String modifyTime;
	private String state;
	// Constructors

	/** default constructor */
	public ArticleClassify() {
	}

	/** full constructor */
	public ArticleClassify(String name, String anotherName, String sort,String description) {
		this.name = name;
		this.anotherName = anotherName;
		this.sort = sort;
		this.description = description;
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

	@Column(name = "name", length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "another_name", length = 32)
	public String getAnotherName() {
		return this.anotherName;
	}

	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName;
	}

	@Column(name = "sort", length = 10)
	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "p_id")
	public ArticleClassify getClassify() {
		return this.classify;
	}

	public void setClassify(ArticleClassify classify) {
		this.classify = classify;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "create_time")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "modify_time")
	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
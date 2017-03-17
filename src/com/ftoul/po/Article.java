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
 * Article entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "article")
public class Article implements java.io.Serializable {

	// Fields

	private String id;
	private Integer sort;
	private ArticleClassify classify;
	private String title;
	private String keyword;
	private String publishTime;
	private String isShow;
	private String isTop;
	private String content;
	private String state;
	private String createTime;
	private String modifyTime;

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** full constructor */
	public Article(Integer sort, ArticleClassify classify, String title, String keyword,
			String publishTime, String isShow , String isTop, String content,
			String state, String createTime, String modifyTime) {
		this.sort = sort;
		this.classify = classify;
		this.title = title;
		this.keyword = keyword;
		this.publishTime = publishTime;
		this.isShow = isShow;
		this.isTop = isTop;
		this.content = content;
		this.state = state;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
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

	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "classify")
	public ArticleClassify getClassify() {
		return this.classify;
	}

	public void setClassify(ArticleClassify classify) {
		this.classify = classify;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "keyword")
	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name = "publish_time", length = 32)
	public String getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	@Column(name = "is_show", length = 10)
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	@Column(name = "is_top", length = 10)
	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
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

}
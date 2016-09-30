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
 * BaseAuthorResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_author_resource", catalog = "ftoul_shop")
public class BaseAuthorResource implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7770051749464318634L;
	private String id;
	private BaseResource baseResource;
	private BaseAuthor baseAuthor;
	private String buckup;
	private String createPerson;
	private String createTime;
	private String modifyPerson;
	private String modifyTime;
	private String state;

	// Constructors

	/** default constructor */
	public BaseAuthorResource() {
	}

	/** full constructor */
	public BaseAuthorResource(BaseResource baseResource, BaseAuthor baseAuthor,
			String buckup, String createPerson, String createTime,
			String modifyPerson, String modifyTime, String state) {
		this.baseResource = baseResource;
		this.baseAuthor = baseAuthor;
		this.buckup = buckup;
		this.createPerson = createPerson;
		this.createTime = createTime;
		this.modifyPerson = modifyPerson;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rid")
	public BaseResource getBaseResource() {
		return this.baseResource;
	}

	public void setBaseResource(BaseResource baseResource) {
		this.baseResource = baseResource;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "aid")
	public BaseAuthor getBaseAuthor() {
		return this.baseAuthor;
	}

	public void setBaseAuthor(BaseAuthor baseAuthor) {
		this.baseAuthor = baseAuthor;
	}

	@Column(name = "buckup", length = 200)
	public String getBuckup() {
		return this.buckup;
	}

	public void setBuckup(String buckup) {
		this.buckup = buckup;
	}

	@Column(name = "create_person", length = 32)
	public String getCreatePerson() {
		return this.createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	@Column(name = "create_time", length = 32)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "modify_person", length = 32)
	public String getModifyPerson() {
		return this.modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
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
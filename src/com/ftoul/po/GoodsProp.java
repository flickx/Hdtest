package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * GoodsProp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods_prop", catalog = "ftoul_shop")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE) 
public class GoodsProp implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6660967311304040140L;
	private String id;
	private Goods goods;
	private GoodsPropertyTypeInfo goodsPropertyTypeInfo;
	private String content;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	private String state;

	// Constructors

	/** default constructor */
	public GoodsProp() {
	}

	/** full constructor */
	public GoodsProp(Goods goods, GoodsPropertyTypeInfo goodsPropertyTypeInfo,
			String content, String createTime, String createPerson,
			String modifyTime, String modifyPerson, String state) {
		this.goods = goods;
		this.goodsPropertyTypeInfo = goodsPropertyTypeInfo;
		this.content = content;
		this.createTime = createTime;
		this.createPerson = createPerson;
		this.modifyTime = modifyTime;
		this.modifyPerson = modifyPerson;
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
	@JoinColumn(name = "goods_id")
	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_property_type_info_id")
	public GoodsPropertyTypeInfo getGoodsPropertyTypeInfo() {
		return this.goodsPropertyTypeInfo;
	}

	public void setGoodsPropertyTypeInfo(
			GoodsPropertyTypeInfo goodsPropertyTypeInfo) {
		this.goodsPropertyTypeInfo = goodsPropertyTypeInfo;
	}

	@Column(name = "content", length = 32)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "create_time", length = 32)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "create_person", length = 32)
	public String getCreatePerson() {
		return this.createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	@Column(name = "modify_time", length = 32)
	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "modify_person", length = 32)
	public String getModifyPerson() {
		return this.modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
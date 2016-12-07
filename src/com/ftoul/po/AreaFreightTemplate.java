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

@Entity
@Table(name = "area_freight_template", catalog = "ftoul_shop")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)  
public class AreaFreightTemplate implements java.io.Serializable {

	// Fields

	private String id;
	private ShopFreightTemplate shopFreightTemplate;
	private String provinceId;
	private String freightArea;
	private Integer less;
	private Double lessPrice;
	private Integer increase;
	private Double increasePrice;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	private String state;

	// Constructors

	/** default constructor */
	public AreaFreightTemplate() {
	}

	/** minimal constructor */
	public AreaFreightTemplate(String id) {
		this.id = id;
	}

	/** full constructor */
	public AreaFreightTemplate(String id,
			String freightArea, Integer less, Double lessPrice,
			Integer increase, Double increasePrice, String createTime,
			String createPerson, String modifyTime, String modifyPerson,
			String state) {
		this.id = id;
		this.freightArea = freightArea;
		this.less = less;
		this.lessPrice = lessPrice;
		this.increase = increase;
		this.increasePrice = increasePrice;
		this.createTime = createTime;
		this.createPerson = createPerson;
		this.modifyTime = modifyTime;
		this.modifyPerson = modifyPerson;
		this.state = state;
	}

	  // Property accessors
    @GenericGenerator(name="generator", strategy="uuid")@Id @GeneratedValue(generator="generator")
    
    @Column(name="id", unique=true, nullable=false, length=32)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "freight_template_id")
	public ShopFreightTemplate getShopFreightTemplate() {
		return this.shopFreightTemplate;
	}

	public void setShopFreightTemplate(ShopFreightTemplate shopFreightTemplate) {
		this.shopFreightTemplate = shopFreightTemplate;
	}

	@Column(name = "freight_area")
	public String getFreightArea() {
		return this.freightArea;
	}

	public void setFreightArea(String freightArea) {
		this.freightArea = freightArea;
	}

	@Column(name = "less")
	public Integer getLess() {
		return this.less;
	}

	public void setLess(Integer less) {
		this.less = less;
	}

	@Column(name = "less_price", precision = 10)
	public Double getLessPrice() {
		return this.lessPrice;
	}

	public void setLessPrice(Double lessPrice) {
		this.lessPrice = lessPrice;
	}

	@Column(name = "increase")
	public Integer getIncrease() {
		return this.increase;
	}

	public void setIncrease(Integer increase) {
		this.increase = increase;
	}

	@Column(name = "increase_price", precision = 10)
	public Double getIncreasePrice() {
		return this.increasePrice;
	}

	public void setIncreasePrice(Double increasePrice) {
		this.increasePrice = increasePrice;
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
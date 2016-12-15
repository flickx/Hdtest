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
 * ShopFreightTemplate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="shop_freight_template")

public class ShopFreightTemplate  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	 private static final long serialVersionUID = -735994700427760647L;
	 private String id;
     private String shopId;
     private String templateName;
     private String templateType;
     private String shopAddress;
     private String shopTime;
     private String freeShop;
     private String priceType;
     private String defaultFreight;
     private String defaultPrice;
     private String increaseUnit;
     private String increasePrice;
     private String kdId;
     private String createTime;
     private String createPerson;
     private String modifyTime;
     private String modifyPerson;
     private String state;
     private String activety;
    // Constructors

    /** default constructor */
    public ShopFreightTemplate() {
    }

    
    /** full constructor */
    public ShopFreightTemplate(String shopAddress, String shopTime, String freeShop, String priceType, String defaultFreight, String defaultPrice, String increaseUnit, String increasePrice, String kdId, String createTime, String createPerson, String modifyTime, String modifyPerson, String state) {
        this.shopAddress = shopAddress;
        this.shopTime = shopTime;
        this.freeShop = freeShop;
        this.priceType = priceType;
        this.defaultFreight = defaultFreight;
        this.defaultPrice = defaultPrice;
        this.increaseUnit = increaseUnit;
        this.increasePrice = increasePrice;
        this.kdId = kdId;
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
    @Column(name="shop_id", length=32)
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}


	@Column(name="template_name", length=100)
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	@Column(name="template_type", length=32)
	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	@Column(name="shop_address", length=32)

    public String getShopAddress() {
        return this.shopAddress;
    }
    
    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }
    
    @Column(name="shop_time", length=32)

    public String getShopTime() {
        return this.shopTime;
    }
    
    public void setShopTime(String shopTime) {
        this.shopTime = shopTime;
    }
    
    @Column(name="free_shop", length=32)

    public String getFreeShop() {
        return this.freeShop;
    }
    
    public void setFreeShop(String freeShop) {
        this.freeShop = freeShop;
    }
    
    @Column(name="price_type", length=32)

    public String getPriceType() {
        return this.priceType;
    }
    
    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }
    
    @Column(name="default_freight", length=32)

    public String getDefaultFreight() {
        return this.defaultFreight;
    }
    
    public void setDefaultFreight(String defaultFreight) {
        this.defaultFreight = defaultFreight;
    }
    
    @Column(name="default_price", length=32)

    public String getDefaultPrice() {
        return this.defaultPrice;
    }
    
    public void setDefaultPrice(String defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
    
    @Column(name="increase_unit", length=32)

    public String getIncreaseUnit() {
        return this.increaseUnit;
    }
    
    public void setIncreaseUnit(String increaseUnit) {
        this.increaseUnit = increaseUnit;
    }
    
    @Column(name="increase_price", length=32)

    public String getIncreasePrice() {
        return this.increasePrice;
    }
    
    public void setIncreasePrice(String increasePrice) {
        this.increasePrice = increasePrice;
    }
    
    @Column(name="kd_id", length=32)

    public String getKdId() {
        return this.kdId;
    }
    
    public void setKdId(String kdId) {
        this.kdId = kdId;
    }
    
    @Column(name="create_time", length=32)

    public String getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="create_person", length=32)

    public String getCreatePerson() {
        return this.createPerson;
    }
    
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }
    
    @Column(name="modify_time", length=32)

    public String getModifyTime() {
        return this.modifyTime;
    }
    
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    @Column(name="modify_person", length=32)

    public String getModifyPerson() {
        return this.modifyPerson;
    }
    
    public void setModifyPerson(String modifyPerson) {
        this.modifyPerson = modifyPerson;
    }
    
    @Column(name="state", length=1)
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    @Column(name="activety", length=1)
	public String getActivety() {
		return activety;
	}
	public void setActivety(String activety) {
		this.activety = activety;
	}
    
}
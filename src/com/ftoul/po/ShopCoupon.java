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
 * ShopCoupon entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="shop_coupon"
    ,catalog="ftoul_shop"
)

public class ShopCoupon  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1616629579812544514L;
	private String id;
     private Shop shop;
     private String name;
     private String price;
     private String validBegen;
     private String validEnd;
     private String minOrderPrice;
     private String stock;
     private String createTime;
     private String createPerson;
     private String modifyTime;
     private String modifyPerson;
     private String state;


    // Constructors

    /** default constructor */
    public ShopCoupon() {
    }

    
    /** full constructor */
    public ShopCoupon(Shop shop, String name, String price, String validBegen, String validEnd, String minOrderPrice, String stock, String createTime, String createPerson, String modifyTime, String modifyPerson, String state) {
        this.shop = shop;
        this.name = name;
        this.price = price;
        this.validBegen = validBegen;
        this.validEnd = validEnd;
        this.minOrderPrice = minOrderPrice;
        this.stock = stock;
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
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="shop_id")

    public Shop getShop() {
        return this.shop;
    }
    
    public void setShop(Shop shop) {
        this.shop = shop;
    }
    
    @Column(name="name", length=32)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="price", length=32)

    public String getPrice() {
        return this.price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
    @Column(name="valid_begen", length=32)

    public String getValidBegen() {
        return this.validBegen;
    }
    
    public void setValidBegen(String validBegen) {
        this.validBegen = validBegen;
    }
    
    @Column(name="valid_end", length=32)

    public String getValidEnd() {
        return this.validEnd;
    }
    
    public void setValidEnd(String validEnd) {
        this.validEnd = validEnd;
    }
    
    @Column(name="min_order_price", length=32)

    public String getMinOrderPrice() {
        return this.minOrderPrice;
    }
    
    public void setMinOrderPrice(String minOrderPrice) {
        this.minOrderPrice = minOrderPrice;
    }
    
    @Column(name="stock", length=32)

    public String getStock() {
        return this.stock;
    }
    
    public void setStock(String stock) {
        this.stock = stock;
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

}
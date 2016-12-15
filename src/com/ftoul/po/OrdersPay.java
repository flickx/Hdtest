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
 * OrdersPay entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="orders_pay")

public class OrdersPay  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -1576537790141738440L;
	private String id;
     private Orders orders;
     private String payStatic;
     private String payType;
     private String payPrice;
     private String payCard;
     private String serialNumber;
     private String createTime;
     private String createPerson;
     private String modifyTime;
     private String modifyPerson;
     private String state;


    // Constructors

    /** default constructor */
    public OrdersPay() {
    }

    
    /** full constructor */
    public OrdersPay(Orders orders, String payStatic, String payType, String payPrice, String payCard, String serialNumber, String createTime, String createPerson, String modifyTime, String modifyPerson, String state) {
        this.orders = orders;
        this.payStatic = payStatic;
        this.payType = payType;
        this.payPrice = payPrice;
        this.payCard = payCard;
        this.serialNumber = serialNumber;
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
        @JoinColumn(name="orders_id")

    public Orders getOrders() {
        return this.orders;
    }
    
    public void setOrders(Orders orders) {
        this.orders = orders;
    }
    
    @Column(name="pay_static", length=32)

    public String getPayStatic() {
        return this.payStatic;
    }
    
    public void setPayStatic(String payStatic) {
        this.payStatic = payStatic;
    }
    
    @Column(name="pay_type", length=32)

    public String getPayType() {
        return this.payType;
    }
    
    public void setPayType(String payType) {
        this.payType = payType;
    }
    
    @Column(name="pay_price", length=32)

    public String getPayPrice() {
        return this.payPrice;
    }
    
    public void setPayPrice(String payPrice) {
        this.payPrice = payPrice;
    }
    
    @Column(name="pay_card", length=32)

    public String getPayCard() {
        return this.payCard;
    }
    
    public void setPayCard(String payCard) {
        this.payCard = payCard;
    }
    
    @Column(name="serial_number", length=32)

    public String getSerialNumber() {
        return this.serialNumber;
    }
    
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
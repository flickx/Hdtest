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
 * GiftPurchase entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="gift_purchase"
    ,catalog="ftoul_shop"
)

public class GiftPurchase  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 3118377186452031709L;
	private String id;
     private Gift gift;
     private String num;
     private String buckup;
     private String createTime;
     private String createPerson;
     private String modifyTime;
     private String modifyPerson;
     private String state;


    // Constructors

    /** default constructor */
    public GiftPurchase() {
    }

    
    /** full constructor */
    public GiftPurchase(Gift gift, String num, String buckup, String createTime, String createPerson, String modifyTime, String modifyPerson, String state) {
        this.gift = gift;
        this.num = num;
        this.buckup = buckup;
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
        @JoinColumn(name="gift_id")

    public Gift getGift() {
        return this.gift;
    }
    
    public void setGift(Gift gift) {
        this.gift = gift;
    }
    
    @Column(name="num", length=32)

    public String getNum() {
        return this.num;
    }
    
    public void setNum(String num) {
        this.num = num;
    }
    
    @Column(name="buckup", length=200)

    public String getBuckup() {
        return this.buckup;
    }
    
    public void setBuckup(String buckup) {
        this.buckup = buckup;
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
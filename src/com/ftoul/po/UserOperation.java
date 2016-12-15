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
 * UserOperation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user_operation")

public class UserOperation  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -8318452578230677161L;
	private String id;
     private User user;
     private String ipAddress;
     private String goodsId;
     private String browseTime;


    // Constructors

    /** default constructor */
    public UserOperation() {
    }

    
    /** full constructor */
    public UserOperation(User user, String ipAddress, String goodsId, String browseTime) {
        this.user = user;
        this.ipAddress = ipAddress;
        this.goodsId = goodsId;
        this.browseTime = browseTime;
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
        @JoinColumn(name="user_id")

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    @Column(name="ip_address", length=32)

    public String getIpAddress() {
        return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    @Column(name="goods_id", length=32)

    public String getGoodsId() {
        return this.goodsId;
    }
    
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    
    @Column(name="browse_time", length=32)

    public String getBrowseTime() {
        return this.browseTime;
    }
    
    public void setBrowseTime(String browseTime) {
        this.browseTime = browseTime;
    }
   








}
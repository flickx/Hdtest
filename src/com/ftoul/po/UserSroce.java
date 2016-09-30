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
 * UserSroce entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user_sroce"
    ,catalog="ftoul_shop"
)

public class UserSroce  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 3593158423170122443L;
	private String id;
     private User user;
     private Goods goods;
     private String goodId;
     private String score;
     private String createTime;
     private String createPerson;
     private String modifyTime;
     private String modifyPerson;
     private String state;


    // Constructors

    /** default constructor */
    public UserSroce() {
    }

    
    /** full constructor */
    public UserSroce(User user, Goods goods, String goodId, String score, String createTime, String createPerson, String modifyTime, String modifyPerson, String state) {
        this.user = user;
        this.goods = goods;
        this.goodId = goodId;
        this.score = score;
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
        @JoinColumn(name="user_id")

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="user_id", insertable=false, updatable=false)

    public Goods getGoods() {
        return this.goods;
    }
    
    public void setGoods(Goods goods) {
        this.goods = goods;
    }
    
    @Column(name="good_id", length=32)

    public String getGoodId() {
        return this.goodId;
    }
    
    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }
    
    @Column(name="score", length=32)

    public String getScore() {
        return this.score;
    }
    
    public void setScore(String score) {
        this.score = score;
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
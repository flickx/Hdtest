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
 * GoodsPropertyTypeInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="goods_property_type_info")

public class GoodsPropertyTypeInfo  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -5146004943112321852L;
	private String id;
     private GoodsPropType goodsPropType;
     private String name;
     private String textOrSelect;
     private String content;
     private String createTime;
     private String createPerson;
     private String modifyTime;
     private String modifyPerson;
     private String state;


    // Constructors

    /** default constructor */
    public GoodsPropertyTypeInfo() {
    }

    
    /** full constructor */
    public GoodsPropertyTypeInfo(GoodsPropType goodsPropType, String name, String textOrSelect, String content, String createTime, String createPerson, String modifyTime, String modifyPerson, String state) {
        this.goodsPropType = goodsPropType;
        this.name = name;
        this.textOrSelect = textOrSelect;
        this.content = content;
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
        @JoinColumn(name="good_prop_type_id")

    public GoodsPropType getGoodsPropType() {
        return this.goodsPropType;
    }
    
    public void setGoodsPropType(GoodsPropType goodsPropType) {
        this.goodsPropType = goodsPropType;
    }
    
    @Column(name="name", length=32)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="text_or_select", length=1)

    public String getTextOrSelect() {
        return this.textOrSelect;
    }
    
    public void setTextOrSelect(String textOrSelect) {
        this.textOrSelect = textOrSelect;
    }
    
    @Column(name="content", length=200)

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
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
package com.ftoul.po;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * BaseRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="base_role"
    ,catalog="ftoul_shop"
)

public class BaseRole  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 2493985537375438738L;
	private String id;
     private String name;
     private String createPerson;
     private String createTime;
     private String modifyPerson;
     private String modifyTime;
     private String state;


    // Constructors

    /** default constructor */
    public BaseRole() {
    }

    
    /** full constructor */
    public BaseRole(String name, String createPerson, String createTime, String modifyPerson, String modifyTime, String state, Set<BasePersonRoler> basePersonRolers, Set<BaseRoleAuthor> baseRoleAuthors) {
        this.name = name;
        this.createPerson = createPerson;
        this.createTime = createTime;
        this.modifyPerson = modifyPerson;
        this.modifyTime = modifyTime;
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
    
    @Column(name="name", length=32)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="create_person", length=32)

    public String getCreatePerson() {
        return this.createPerson;
    }
    
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }
    
    @Column(name="create_time", length=32)

    public String getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="modify_person", length=32)

    public String getModifyPerson() {
        return this.modifyPerson;
    }
    
    public void setModifyPerson(String modifyPerson) {
        this.modifyPerson = modifyPerson;
    }
    
    @Column(name="modify_time", length=32)

    public String getModifyTime() {
        return this.modifyTime;
    }
    
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    @Column(name="state", length=1)

    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }

}
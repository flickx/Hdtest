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
 * BaseRoleAuthor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="base_role_author")

public class BaseRoleAuthor  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 3501058848306513824L;
	private String id;
     private BaseRole baseRole;
     private BaseAuthor baseAuthor;
     private String createPerson;
     private String createTime;
     private String modifyPerson;
     private String modifyTime;
     private String state;


    // Constructors

    /** default constructor */
    public BaseRoleAuthor() {
    }

    
    /** full constructor */
    public BaseRoleAuthor(BaseRole baseRole, BaseAuthor baseAuthor, String createPerson, String createTime, String modifyPerson, String modifyTime, String state) {
        this.baseRole = baseRole;
        this.baseAuthor = baseAuthor;
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
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="rid")

    public BaseRole getBaseRole() {
        return this.baseRole;
    }
    
    public void setBaseRole(BaseRole baseRole) {
        this.baseRole = baseRole;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="aid")

    public BaseAuthor getBaseAuthor() {
        return this.baseAuthor;
    }
    
    public void setBaseAuthor(BaseAuthor baseAuthor) {
        this.baseAuthor = baseAuthor;
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
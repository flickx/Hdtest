package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * IndexLinks entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="index_links"
    ,catalog="ftoul_shop"
)

public class IndexLinks  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 206775326656443059L;
	private String id;
     private String siteName;
     private String siteUrl;
     private String state;
     private String sitePic;
     private String createTime;
     private String createPerson;
     private String modifyTime;
     private String modifyPerson;


    // Constructors

    /** default constructor */
    public IndexLinks() {
    }

    
    /** full constructor */
    public IndexLinks(String siteName, String siteUrl, String state, String sitePic, String createTime, String createPerson, String modifyTime, String modifyPerson) {
        this.siteName = siteName;
        this.siteUrl = siteUrl;
        this.state = state;
        this.sitePic = sitePic;
        this.createTime = createTime;
        this.createPerson = createPerson;
        this.modifyTime = modifyTime;
        this.modifyPerson = modifyPerson;
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
    
    @Column(name="site_name", length=32)

    public String getSiteName() {
        return this.siteName;
    }
    
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    
    @Column(name="site_url", length=200)

    public String getSiteUrl() {
        return this.siteUrl;
    }
    
    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }
    
    @Column(name="state", length=1)

    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    @Column(name="site_pic", length=200)

    public String getSitePic() {
        return this.sitePic;
    }
    
    public void setSitePic(String sitePic) {
        this.sitePic = sitePic;
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
   
}
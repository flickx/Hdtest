package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * IndexArticle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="index_article"
    ,catalog="ftoul_shop"
)

public class IndexArticle  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -7700376862839091073L;
	private String id;
     private String title;
     private String content;
     private String indexShow;
     private String showBegen;
     private String createTime;
     private String createPerson;
     private String modifyTime;
     private String modifyPerson;
     private String state;
     private String showEnd;


    // Constructors

    /** default constructor */
    public IndexArticle() {
    }

    
    /** full constructor */
    public IndexArticle(String title, String content, String indexShow, String showBegen, String createTime, String createPerson, String modifyTime, String modifyPerson, String state, String showEnd) {
        this.title = title;
        this.content = content;
        this.indexShow = indexShow;
        this.showBegen = showBegen;
        this.createTime = createTime;
        this.createPerson = createPerson;
        this.modifyTime = modifyTime;
        this.modifyPerson = modifyPerson;
        this.state = state;
        this.showEnd = showEnd;
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
    
    @Column(name="title", length=100)

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="content", length=1000)

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="index_show", length=1)

    public String getIndexShow() {
        return this.indexShow;
    }
    
    public void setIndexShow(String indexShow) {
        this.indexShow = indexShow;
    }
    
    @Column(name="show_begen", length=32)

    public String getShowBegen() {
        return this.showBegen;
    }
    
    public void setShowBegen(String showBegen) {
        this.showBegen = showBegen;
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
    
    @Column(name="show_end", length=32)

    public String getShowEnd() {
        return this.showEnd;
    }
    
    public void setShowEnd(String showEnd) {
        this.showEnd = showEnd;
    }
   
}
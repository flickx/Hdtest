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
 * MessageVerification entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="message_verification"
    ,catalog="ftoul_shop"
)

public class MessageVerification  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	 private static final long serialVersionUID = 8547349121730614066L;
	 private String id;
     private String mobile;
     private Integer sort;
     private String messageType;//1：注册  ，2：找回密码 
     private String content;
     private String verificationCode;
     private String validTime;
     private String createTime;
     private String createPerson;
     private String modifyTime;
     private String modifyPerson;
     private String state;
     private String ip;


    // Constructors

    /** default constructor */
    public MessageVerification() {
    }

    
    /** full constructor */
    public MessageVerification( String messageType, String content, String verificationCode, String validTime, String createTime, String createPerson, String modifyTime, String modifyPerson, String state,String ip) {
        this.messageType = messageType;
        this.content = content;
        this.verificationCode = verificationCode;
        this.validTime = validTime;
        this.createTime = createTime;
        this.createPerson = createPerson;
        this.modifyTime = modifyTime;
        this.modifyPerson = modifyPerson;
        this.state = state;
        this.ip=ip;
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
    
    @Column(name="message_type", length=32)
    public String getMessageType() {
        return this.messageType;
    }

	public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
	
	@Column(name="mobile", length=32)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    @Column(name="content", length=100)

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="verification_code", length=32)

    public String getVerificationCode() {
        return this.verificationCode;
    }
    
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
    
    @Column(name="valid_time", length=32)

    public String getValidTime() {
        return this.validTime;
    }
    
    public void setValidTime(String validTime) {
        this.validTime = validTime;
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

    @Column(name="sort", length=32)
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Column(name="ip", length=32)
	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
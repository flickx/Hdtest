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
 * LoginUserLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="login_user_log"
    ,catalog="ftoul_shop"
)

public class LoginUserLog  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -614154366575222780L;
	private String id;
     private LoginUser loginUser;
     private String operation;
     private String methodPackage;
     private String methodName;
     private String prams;
     private String operationTime;
     private String ipAddress;
     private String resStatic;
     private String resText;


    // Constructors

    /** default constructor */
    public LoginUserLog() {
    }

    
    /** full constructor */
    public LoginUserLog(LoginUser loginUser, String operation, String prams, String operationTime) {
        this.loginUser = loginUser;
        this.operation = operation;
        this.prams = prams;
        this.operationTime = operationTime;
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
        @JoinColumn(name="login_user_id")

    public LoginUser getLoginUser() {
        return this.loginUser;
    }
    
    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }
    
    @Column(name="operation", length=100)

    public String getOperation() {
        return this.operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    @Column(name="method_package", length=100)
    public String getMethodPackage() {
		return methodPackage;
	}


	public void setMethodPackage(String methodPackage) {
		this.methodPackage = methodPackage;
	}

	@Column(name="method_name", length=100)
	public String getMethodName() {
		return methodName;
	}


	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}


	@Column(name="prams", length=5000)

    public String getPrams() {
        return this.prams;
    }
    
    public void setPrams(String prams) {
        this.prams = prams;
    }
    
    @Column(name="operation_time", length=32)

    public String getOperationTime() {
        return this.operationTime;
    }
    
    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }
    
    @Column(name="ip_address", length=32)
	public String getIpAddress() {
		return ipAddress;
	}
	
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name="res_static", length=32)
	public String getResStatic() {
		return resStatic;
	}


	public void setResStatic(String resStatic) {
		this.resStatic = resStatic;
	}

	@Column(name="res_text", length=2000)
	public String getResText() {
		return resText;
	}

	public void setResText(String resText) {
		this.resText = resText;
	}

}
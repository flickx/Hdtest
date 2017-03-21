package com.ftoul.mongo.po;

public class LoginUserLog  implements java.io.Serializable {


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


    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
	
    public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	public String getOperation() {
        return this.operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public String getMethodPackage() {
		return methodPackage;
	}


	public void setMethodPackage(String methodPackage) {
		this.methodPackage = methodPackage;
	}

	public String getMethodName() {
		return methodName;
	}


	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}



    public String getPrams() {
        return this.prams;
    }
    
    public void setPrams(String prams) {
        this.prams = prams;
    }
    

    public String getOperationTime() {
        return this.operationTime;
    }
    
    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }
    
	public String getIpAddress() {
		return ipAddress;
	}
	
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getResStatic() {
		return resStatic;
	}


	public void setResStatic(String resStatic) {
		this.resStatic = resStatic;
	}

	public String getResText() {
		return resText;
	}

	public void setResText(String resText) {
		this.resText = resText;
	}

}
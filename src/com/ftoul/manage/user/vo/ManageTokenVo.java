package com.ftoul.manage.user.vo;

import com.ftoul.po.LoginUser;

/**
 * 管理员token
 * @author flick
 *
 */
public class ManageTokenVo {

	private LoginUser loginUser;
	private String secretKey;
	private String token;
	private String uploadTime;
	
	public LoginUser getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	
}

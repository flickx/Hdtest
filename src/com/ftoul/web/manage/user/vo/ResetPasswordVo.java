package com.ftoul.web.manage.user.vo;
//重新设置密码vo
import com.ftoul.po.User;

public class ResetPasswordVo {
	private User user;
	private String oldPassword;
	private String newPassword;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}

package com.ftoul.app.vo;

public class AfterOpLogAppVo {
	private String id;//id
	private String msg;//操作记录
	private String userId;//操作人
	private String remark;//备注
	private String afterId;//售后申请单主键
	private String scheduleStatic;//进度状态
	private String createTime;//操作时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAfterId() {
		return afterId;
	}
	public void setAfterId(String afterId) {
		this.afterId = afterId;
	}
	public String getScheduleStatic() {
		return scheduleStatic;
	}
	public void setScheduleStatic(String scheduleStatic) {
		this.scheduleStatic = scheduleStatic;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}

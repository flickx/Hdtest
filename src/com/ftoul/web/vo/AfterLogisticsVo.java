package com.ftoul.web.vo;

public class AfterLogisticsVo {
	
	private String serviceCode;//服务编号
	private String createTime;//服务发起时间
	private String odd;//运单编号
	private String logisticeCompanyName;//承运物流
	private String logisticeInfo;//物流进度详情
	private String customsClearanceStatic;
	private String logInfo;//物流说明
	
	public String getOdd() {
		return odd;
	}
	public void setOdd(String odd) {
		this.odd = odd;
	}
	public String getLogisticeCompanyName() {
		return logisticeCompanyName;
	}
	public void setLogisticeCompanyName(String logisticeCompanyName) {
		this.logisticeCompanyName = logisticeCompanyName;
	}
	public String getLogisticeInfo() {
		return logisticeInfo;
	}
	public void setLogisticeInfo(String logisticeInfo) {
		this.logisticeInfo = logisticeInfo;
	}
	public String getCustomsClearanceStatic() {
		return customsClearanceStatic;
	}
	public void setCustomsClearanceStatic(String customsClearanceStatic) {
		this.customsClearanceStatic = customsClearanceStatic;
	}
	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	

}

package com.ftoul.app.vo;


public class AddressAppVo {
	private String id;//id
	private String name;//省市区中文名
	private String province;//省
	private String city;//市
	private String county;//区
	private String consignee;//收货人
	private String tel;//电话
	private String address;//详细地址
	private String defulat;//是否默认
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDefulat() {
		return defulat;
	}
	public void setDefulat(String defulat) {
		this.defulat = defulat;
	}
	
}

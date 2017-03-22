package com.ftoul.mongo.po;

public class UserBrowse implements java.io.Serializable {

	private static final long serialVersionUID = -4679849833817084834L;
	private String id;
	private User user;
	private String ipAddress;
	private String source;
	private Goods goods;
	private String browseTime;
	private String state;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getBrowseTime() {
		return this.browseTime;
	}

	public void setBrowseTime(String browseTime) {
		this.browseTime = browseTime;
	}
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}

}
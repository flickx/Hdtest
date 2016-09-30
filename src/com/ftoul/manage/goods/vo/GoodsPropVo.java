package com.ftoul.manage.goods.vo;

import com.ftoul.po.Goods;
import com.ftoul.po.GoodsPropertyTypeInfo;



public class GoodsPropVo {
	
	private static final long serialVersionUID = -6660967311304040140L;
	private String id;
	private Goods goods;
	private String name;
	private String goodsPropTypeInfoId;
	private String content;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	private String state;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyPerson() {
		return modifyPerson;
	}
	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getGoodsPropTypeInfoId() {
		return goodsPropTypeInfoId;
	}
	public void setGoodsPropTypeInfoId(String goodsPropTypeInfoId) {
		this.goodsPropTypeInfoId = goodsPropTypeInfoId;
	}

	
}

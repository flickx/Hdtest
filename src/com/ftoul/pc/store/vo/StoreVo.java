package com.ftoul.pc.store.vo;

import java.util.List;

import com.ftoul.po.Goods;

public class StoreVo {
	private String id;
	private String name;
	private List<Object> objList;
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
	public List<Object> getObjList() {
		return objList;
	}
	public void setObjList(List<Object> objList) {
		this.objList = objList;
	}
}

package com.ftoul.manage.goods.vo;

import java.util.List;

public class GoodsTypeVo {

	private String id;
	private String name;
	private String picSrc;
	private List<GoodsTypeVo> goodsTypelist;
	
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
	public List<GoodsTypeVo> getGoodsTypelist() {
		return goodsTypelist;
	}
	public void setGoodsTypelist(List<GoodsTypeVo> goodsTypelist) {
		this.goodsTypelist = goodsTypelist;
	}
	public String getPicSrc() {
		return picSrc;
	}
	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}
	
	
}

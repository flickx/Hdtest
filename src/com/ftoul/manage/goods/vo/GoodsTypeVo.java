package com.ftoul.manage.goods.vo;

import java.util.ArrayList;
import java.util.List;

import com.ftoul.pc.interfaces.vo.PcGoodsTypeVo;

public class GoodsTypeVo {

	private String id;
	private String name;
	private String picSrc;
	private List<GoodsTypeVo> goodsTypelist;
	private List<PcGoodsTypeVo> goodsList = new ArrayList<PcGoodsTypeVo>();
	
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
	public List<PcGoodsTypeVo> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<PcGoodsTypeVo> goodsList) {
		this.goodsList = goodsList;
	}
	
	
}

package com.ftoul.manage.goods.vo;

import java.util.List;

import com.ftoul.po.GoodsParam;
import com.ftoul.po.GoodsProp;

public class GoodsCardVo {
	private String id;
	private String goodsTypeId;
	private String goodsPropTypeId;
	private String goodsBrandId;
	private String code;
	private String grounding;
	private String title;
	private String price;
	private String pcInfo;
	private String mobilInfo;
	private List<GoodsProp> goodsPropList;
	private GoodsParam goodsParam;
	private String amount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsTypeId() {
		return goodsTypeId;
	}
	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}
	public String getGoodsPropTypeId() {
		return goodsPropTypeId;
	}
	public void setGoodsPropTypeId(String goodsPropTypeId) {
		this.goodsPropTypeId = goodsPropTypeId;
	}
	public String getGoodsBrandId() {
		return goodsBrandId;
	}
	public void setGoodsBrandId(String goodsBrandId) {
		this.goodsBrandId = goodsBrandId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGrounding() {
		return grounding;
	}
	public void setGrounding(String grounding) {
		this.grounding = grounding;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPcInfo() {
		return pcInfo;
	}
	public void setPcInfo(String pcInfo) {
		this.pcInfo = pcInfo;
	}
	public String getMobilInfo() {
		return mobilInfo;
	}
	public void setMobilInfo(String mobilInfo) {
		this.mobilInfo = mobilInfo;
	}
	public List<GoodsProp> getGoodsPropList() {
		return goodsPropList;
	}
	public void setGoodsPropList(List<GoodsProp> goodsPropList) {
		this.goodsPropList = goodsPropList;
	}
	public GoodsParam getGoodsParam() {
		return goodsParam;
	}
	public void setGoodsParam(GoodsParam goodsParam) {
		this.goodsParam = goodsParam;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}

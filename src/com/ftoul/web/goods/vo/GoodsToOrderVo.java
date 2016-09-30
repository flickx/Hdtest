package com.ftoul.web.goods.vo;

import com.ftoul.po.GoodsParam;

public class GoodsToOrderVo {
	
	
	private String amount;
	private GoodsParam goodsParam;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public GoodsParam getGoodsParam() {
		return goodsParam;
	}
	public void setGoodsParam(GoodsParam goodsParam) {
		this.goodsParam = goodsParam;
	}
}

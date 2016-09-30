package com.ftoul.web.vo;

import com.ftoul.po.Goods;
import com.ftoul.po.GoodsEvent;

public class MjGoodsEventVo {
	
	private GoodsEvent goodsEvent;
	
	private Goods goods;
	
	private double orderPrice;

	public GoodsEvent getGoodsEvent() {
		return goodsEvent;
	}

	public void setGoodsEvent(GoodsEvent goodsEvent) {
		this.goodsEvent = goodsEvent;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	

}

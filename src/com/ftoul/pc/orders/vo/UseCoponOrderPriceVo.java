package com.ftoul.pc.orders.vo;

import java.math.BigDecimal;

public class UseCoponOrderPriceVo {
	
	private BigDecimal subOrderPrice;//子订单价格
	private BigDecimal parentOrderPrice;//总订单价格
	
	public BigDecimal getSubOrderPrice() {
		return subOrderPrice;
	}
	public void setSubOrderPrice(BigDecimal subOrderPrice) {
		this.subOrderPrice = subOrderPrice;
	}
	public BigDecimal getParentOrderPrice() {
		return parentOrderPrice;
	}
	public void setParentOrderPrice(BigDecimal parentOrderPrice) {
		this.parentOrderPrice = parentOrderPrice;
	}
	
	
}

package com.ftoul.manage.goods.vo;

import com.ftoul.po.GoodsType;

/**
 * 一个商品对应的商品分类集合
*
* 类描述：
* @author: yw
* @date： 日期：2016年9月9日 时间：下午5:07:15
* @version 1.0
*
 */

public class GoodsTypeSetVo {

	private GoodsType goodsTypeLevel1;
	private GoodsType goodsTypeLevel2;
	private GoodsType goodsTypeLevel3;
	public GoodsType getGoodsTypeLevel1() {
		return goodsTypeLevel1;
	}
	public void setGoodsTypeLevel1(GoodsType goodsTypeLevel1) {
		this.goodsTypeLevel1 = goodsTypeLevel1;
	}
	public GoodsType getGoodsTypeLevel2() {
		return goodsTypeLevel2;
	}
	public void setGoodsTypeLevel2(GoodsType goodsTypeLevel2) {
		this.goodsTypeLevel2 = goodsTypeLevel2;
	}
	public GoodsType getGoodsTypeLevel3() {
		return goodsTypeLevel3;
	}
	public void setGoodsTypeLevel3(GoodsType goodsTypeLevel3) {
		this.goodsTypeLevel3 = goodsTypeLevel3;
	}
	
	
	
	
}

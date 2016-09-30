/**
 * 
 */
package com.ftoul.web.vo;

import java.util.List;

import com.ftoul.po.Goods;
import com.ftoul.po.GoodsEvent;

/**
 * @author 李丁
 * @date:2016年8月3日 上午10:41:12
 * @类说明 :
 */

public class GoodsEventVO {
	private static final long serialVersionUID = -8405558780694945296L;
	
	private GoodsEvent goodsEvent;
	private List<Goods> goodsList;//活动商品列表
	
	/**
	 * @return the goodsEvent
	 */
	public GoodsEvent getGoodsEvent() {
		return goodsEvent;
	}
	/**
	 * @param goodsEvent the goodsEvent to set
	 */
	public void setGoodsEvent(GoodsEvent goodsEvent) {
		this.goodsEvent = goodsEvent;
	}
	/**
	 * @return the goodsList
	 */
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	/**
	 * @param goodsList the goodsList to set
	 */
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	
	
}

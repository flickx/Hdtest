/**
 * 
 */
package com.ftoul.po;

/**
 * @author 李丁
 * @date:2016年8月31日 上午11:04:29
 * @类说明 : 活动订单处理VO
 */

public class EventOrderVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String eventId;//活动ID
	private String goodsId;//商品ID
	private String quantity;//订单完成后，剩余活动商品的数量
	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return the goodsId
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	
}

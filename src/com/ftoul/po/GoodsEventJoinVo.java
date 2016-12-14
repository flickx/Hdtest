package com.ftoul.po;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 活动 商品关联实体类
 */
@Entity
@Table(name = "goods_event_join")
public class GoodsEventJoinVo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private GoodsEvent goodsEvent;
	private Goods goods;
	private Integer quantity;
	private String state;
	private BigDecimal eventPrice;
	private Integer stock;
	private GoodsEventJoin goodsEventJoin;
	
	
	/**
	 * @return the goodsEventJoin
	 */
	public GoodsEventJoin getGoodsEventJoin() {
		return goodsEventJoin;
	}
	/**
	 * @param goodsEventJoin the goodsEventJoin to set
	 */
	public void setGoodsEventJoin(GoodsEventJoin goodsEventJoin) {
		this.goodsEventJoin = goodsEventJoin;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
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
	 * @return the goods
	 */
	public Goods getGoods() {
		return goods;
	}
	/**
	 * @param goods the goods to set
	 */
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the eventPrice
	 */
	public BigDecimal getEventPrice() {
		return eventPrice;
	}
	/**
	 * @param eventPrice the eventPrice to set
	 */
	public void setEventPrice(BigDecimal eventPrice) {
		this.eventPrice = eventPrice;
	}
	/**
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
}
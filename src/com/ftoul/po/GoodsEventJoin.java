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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 活动 商品关联实体类
 */
@Entity
@Table(name = "goods_event_join", catalog = "ftoul_shop")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)  
public class GoodsEventJoin implements java.io.Serializable {

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
	// Constructors

	/** default constructor */
	public GoodsEventJoin() {
	}

	/** full constructor */
	public GoodsEventJoin(GoodsEvent goodsEvent, Goods goods) {
		this.goodsEvent = goodsEvent;
		this.goods = goods;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id")
	public GoodsEvent getGoodsEvent() {
		return this.goodsEvent;
	}

	public void setGoodsEvent(GoodsEvent goodsEvent) {
		this.goodsEvent = goodsEvent;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_id")
	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	@Column(name = "quantity", length = 32)
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "state", length = 1)
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "event_price", length = 10)
	public BigDecimal getEventPrice() {
		return eventPrice;
	}

	/**
	 * @param eventPrice the eventPrice to set
	 */
	public void setEventPrice(BigDecimal eventPrice) {
		this.eventPrice = eventPrice;
	}
	
}
package com.ftoul.po;

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
 * GoodsEventLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods_event_log", catalog = "ftoul_shop")
public class GoodsEventLog implements java.io.Serializable {

	// Fields

	private String id;
	private User user;
	private GoodsEvent goodsEvent;
	private Orders orders;
	private GoodsParam goodsParam;
	private String goodsNum;
	private String goodsPayable;
	private String goodsTotalPayable;
	private String goodsCostable;
	private String goodsTotalCostable;
	private String cost;
	private String goodsBenprice;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	private String state;

	// Constructors

	/** default constructor */
	public GoodsEventLog() {
	}

	/** full constructor */
	public GoodsEventLog(User user, GoodsEvent goodsEvent, Orders orders,
			GoodsParam goodsParam, String goodsNum, String goodsPayable,
			String goodsTotalPayable, String goodsCostable,
			String goodsTotalCostable, String cost, String goodsBenprice,
			String createTime, String createPerson, String modifyTime,
			String modifyPerson, String state) {
		this.user = user;
		this.goodsEvent = goodsEvent;
		this.orders = orders;
		this.goodsParam = goodsParam;
		this.goodsNum = goodsNum;
		this.goodsPayable = goodsPayable;
		this.goodsTotalPayable = goodsTotalPayable;
		this.goodsCostable = goodsCostable;
		this.goodsTotalCostable = goodsTotalCostable;
		this.cost = cost;
		this.goodsBenprice = goodsBenprice;
		this.createTime = createTime;
		this.createPerson = createPerson;
		this.modifyTime = modifyTime;
		this.modifyPerson = modifyPerson;
		this.state = state;
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
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_event")
	public GoodsEvent getGoodsEvent() {
		return this.goodsEvent;
	}

	public void setGoodsEvent(GoodsEvent goodsEvent) {
		this.goodsEvent = goodsEvent;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orders_id")
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_param_id")
	public GoodsParam getGoodsParam() {
		return this.goodsParam;
	}

	public void setGoodsParam(GoodsParam goodsParam) {
		this.goodsParam = goodsParam;
	}

	@Column(name = "goods_num", length = 32)
	public String getGoodsNum() {
		return this.goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	@Column(name = "goods_payable", length = 32)
	public String getGoodsPayable() {
		return this.goodsPayable;
	}

	public void setGoodsPayable(String goodsPayable) {
		this.goodsPayable = goodsPayable;
	}

	@Column(name = "goods_total_payable", length = 32)
	public String getGoodsTotalPayable() {
		return this.goodsTotalPayable;
	}

	public void setGoodsTotalPayable(String goodsTotalPayable) {
		this.goodsTotalPayable = goodsTotalPayable;
	}

	@Column(name = "goods_costable", length = 32)
	public String getGoodsCostable() {
		return this.goodsCostable;
	}

	public void setGoodsCostable(String goodsCostable) {
		this.goodsCostable = goodsCostable;
	}

	@Column(name = "goods_total_costable", length = 32)
	public String getGoodsTotalCostable() {
		return this.goodsTotalCostable;
	}

	public void setGoodsTotalCostable(String goodsTotalCostable) {
		this.goodsTotalCostable = goodsTotalCostable;
	}

	@Column(name = "cost", length = 32)
	public String getCost() {
		return this.cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	@Column(name = "goods_benprice", length = 32)
	public String getGoodsBenprice() {
		return this.goodsBenprice;
	}

	public void setGoodsBenprice(String goodsBenprice) {
		this.goodsBenprice = goodsBenprice;
	}

	@Column(name = "create_time", length = 32)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "create_person", length = 32)
	public String getCreatePerson() {
		return this.createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	@Column(name = "modify_time", length = 32)
	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "modify_person", length = 32)
	public String getModifyPerson() {
		return this.modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	@Column(name = "state", length = 32)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
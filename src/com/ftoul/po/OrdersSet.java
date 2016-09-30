package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * OrdersSet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orders_set", catalog = "ftoul_shop")
public class OrdersSet implements java.io.Serializable {

	// Fields

	private String id;
	private String backStatic;
	private String autoCancleTime;
	private String antoTakeGoodsTime;
	private String backLimitTime;
	private String backGoodsExplain;
	private String backPriceExplain;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	private String state;

	// Constructors

	/** default constructor */
	public OrdersSet() {
	}

	/** full constructor */
	public OrdersSet(String backStatic, String autoCancleTime,
			String antoTakeGoodsTime, String backLimitTime,
			String backGoodsExplain, String backPriceExplain,
			String createTime, String createPerson, String modifyTime,
			String modifyPerson, String state) {
		this.backStatic = backStatic;
		this.autoCancleTime = autoCancleTime;
		this.antoTakeGoodsTime = antoTakeGoodsTime;
		this.backLimitTime = backLimitTime;
		this.backGoodsExplain = backGoodsExplain;
		this.backPriceExplain = backPriceExplain;
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

	@Column(name = "back_static", length = 1)
	public String getBackStatic() {
		return this.backStatic;
	}

	public void setBackStatic(String backStatic) {
		this.backStatic = backStatic;
	}

	@Column(name = "auto_cancle_time", length = 32)
	public String getAutoCancleTime() {
		return this.autoCancleTime;
	}

	public void setAutoCancleTime(String autoCancleTime) {
		this.autoCancleTime = autoCancleTime;
	}

	@Column(name = "anto_take_goods_time", length = 32)
	public String getAntoTakeGoodsTime() {
		return this.antoTakeGoodsTime;
	}

	public void setAntoTakeGoodsTime(String antoTakeGoodsTime) {
		this.antoTakeGoodsTime = antoTakeGoodsTime;
	}

	@Column(name = "back_limit_time", length = 32)
	public String getBackLimitTime() {
		return this.backLimitTime;
	}

	public void setBackLimitTime(String backLimitTime) {
		this.backLimitTime = backLimitTime;
	}

	@Column(name = "back_goods_explain", length = 5000)
	public String getBackGoodsExplain() {
		return this.backGoodsExplain;
	}

	public void setBackGoodsExplain(String backGoodsExplain) {
		this.backGoodsExplain = backGoodsExplain;
	}

	@Column(name = "back_price_explain", length = 5000)
	public String getBackPriceExplain() {
		return this.backPriceExplain;
	}

	public void setBackPriceExplain(String backPriceExplain) {
		this.backPriceExplain = backPriceExplain;
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

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
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
 * GoodsTypeEventJoin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods_type_event_join")
public class GoodsTypeEventJoin implements java.io.Serializable {

	// Fields

	private String id;
	private BusinessStore businessStore;
	private String eventId;
	private String goodsType1;
	private String goodsType2;
	private String goodsType3;
	private String level;
	private String state;
	private String createPerson;
	private String createTime;
	private String modifyPerson;
	private String modifyTime;

	// Constructors

	/** default constructor */
	public GoodsTypeEventJoin() {
	}

	/** full constructor */
	public GoodsTypeEventJoin(BusinessStore businessStore, String eventId,
			String goodsType1, String goodsType2, String goodsType3,
			String state, String createPerson, String createTime,
			String modifyPerson, String modifyTime) {
		this.businessStore = businessStore;
		this.eventId = eventId;
		this.goodsType1 = goodsType1;
		this.goodsType2 = goodsType2;
		this.goodsType3 = goodsType3;
		this.state = state;
		this.createPerson = createPerson;
		this.createTime = createTime;
		this.modifyPerson = modifyPerson;
		this.modifyTime = modifyTime;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_id")
	public BusinessStore getBusinessStore() {
		return this.businessStore;
	}

	public void setBusinessStore(BusinessStore businessStore) {
		this.businessStore = businessStore;
	}

	@Column(name = "event_id", length = 32)
	public String getEventId() {
		return this.eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Column(name = "goods_type1", length = 32)
	public String getGoodsType1() {
		return this.goodsType1;
	}

	public void setGoodsType1(String goodsType1) {
		this.goodsType1 = goodsType1;
	}

	@Column(name = "goods_type2", length = 32)
	public String getGoodsType2() {
		return this.goodsType2;
	}

	public void setGoodsType2(String goodsType2) {
		this.goodsType2 = goodsType2;
	}

	@Column(name = "goods_type3", length = 32)
	public String getGoodsType3() {
		return this.goodsType3;
	}

	public void setGoodsType3(String goodsType3) {
		this.goodsType3 = goodsType3;
	}
	
	@Column(name = "level", length = 1)
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "create_person", length = 32)
	public String getCreatePerson() {
		return this.createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	@Column(name = "create_time", length = 32)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "modify_person", length = 32)
	public String getModifyPerson() {
		return this.modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	@Column(name = "modify_time", length = 32)
	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

}
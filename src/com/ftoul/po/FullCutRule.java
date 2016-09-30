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
 * 满减规则实体类
 */
@Entity
@Table(name = "full_cut_rule", catalog = "ftoul_shop")
public class FullCutRule implements java.io.Serializable {

	// Fields

	private String id;
	private GoodsEvent goodsEvent;
	private Integer target;
	private Integer discountAmount;
	private String state;
	private String createTime;
	private String modifyTime;
	// Constructors

	/** default constructor */
	public FullCutRule() {
	}

	/** minimal constructor */
	public FullCutRule(GoodsEvent goodsEvent) {
		this.goodsEvent = goodsEvent;
	}

	/** full constructor */
	public FullCutRule(GoodsEvent goodsEvent, Integer target,
			Integer discountAmount) {
		this.goodsEvent = goodsEvent;
		this.target = target;
		this.discountAmount = discountAmount;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
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

	@Column(name = "target")
	public Integer getTarget() {
		return this.target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

	@Column(name = "discount_amount")
	public Integer getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}
	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "create_time")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "modify_time")
	public String getModifyTime() {
		return modifyTime;
	}

	
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	
}
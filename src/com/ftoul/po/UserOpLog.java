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
 * UserOpLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_op_log")
public class UserOpLog implements java.io.Serializable {

	// Fields

	private String id;
	private User user;
	private String opTypeCode;
	private String opTypeName;
	private String opTime;
	private String opReason;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	private String state;
	private Orders orders;

	// Constructors

	/** default constructor */
	public UserOpLog() {
	}

	/** full constructor */
	public UserOpLog(User user, String opTypeCode, String opTypeName,
			String opTime, String opReason, String createTime,
			String createPerson, String modifyTime, String modifyPerson,
			String state) {
		this.user = user;
		this.opTypeCode = opTypeCode;
		this.opTypeName = opTypeName;
		this.opTime = opTime;
		this.opReason = opReason;
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
	@JoinColumn(name = "op_user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orders_id")
	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Column(name = "op_type_code", length = 32)
	public String getOpTypeCode() {
		return this.opTypeCode;
	}

	public void setOpTypeCode(String opTypeCode) {
		this.opTypeCode = opTypeCode;
	}

	@Column(name = "op_type_name", length = 32)
	public String getOpTypeName() {
		return this.opTypeName;
	}

	public void setOpTypeName(String opTypeName) {
		this.opTypeName = opTypeName;
	}

	@Column(name = "op_time", length = 32)
	public String getOpTime() {
		return this.opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	@Column(name = "op_reason", length = 32)
	public String getOpReason() {
		return this.opReason;
	}

	public void setOpReason(String opReason) {
		this.opReason = opReason;
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
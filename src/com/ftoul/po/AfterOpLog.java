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
 * AfterOpLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "after_op_log", catalog = "ftoul_shop")
public class AfterOpLog implements java.io.Serializable {

	// Fields

	private String id;
	private AfterSchedule afterSchedule;
	private String msg;
	private String userId;
	private String remark;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String state;
	private String modifyPerson;

	// Constructors

	/** default constructor */
	public AfterOpLog() {
	}

	/** full constructor */
	public AfterOpLog(AfterSchedule afterSchedule, String msg, String userId,
			String remark, String createTime, String createPerson,
			String modifyTime, String state, String modifyPerson) {
		this.afterSchedule = afterSchedule;
		this.msg = msg;
		this.userId = userId;
		this.remark = remark;
		this.createTime = createTime;
		this.createPerson = createPerson;
		this.modifyTime = modifyTime;
		this.state = state;
		this.modifyPerson = modifyPerson;
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
	@JoinColumn(name = "after_id")
	public AfterSchedule getAfterSchedule() {
		return this.afterSchedule;
	}

	public void setAfterSchedule(AfterSchedule afterSchedule) {
		this.afterSchedule = afterSchedule;
	}

	@Column(name = "msg", length = 32)
	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Column(name = "user_id", length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "remark", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Column(name = "state", length = 32)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "modify_person", length = 32)
	public String getModifyPerson() {
		return this.modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

}
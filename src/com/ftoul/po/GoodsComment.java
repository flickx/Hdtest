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
 * GoodsComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods_comment")
public class GoodsComment implements java.io.Serializable {

	// Fields

	private String id;
	private OrdersDetail ordersDetail;
	private String commentCode;
	private String userName;
	private String commentType;
	private String commentContent;
	private String ip;
	private String commentTime;
	private String isShow;
	private String auditState;
	private String state;
	private String picSrc;
	private String star;
	private String anonymousComment;
	private String comeFrom;
	private String createPerson;
	private String createTime;
	private String modifyPerson;
	private String modifyTime;

	// Constructors

	/** default constructor */
	public GoodsComment() {
	}

	/** full constructor */
	public GoodsComment(OrdersDetail ordersDetail, String commentCode,
			String userName, String commentType, String commentContent,
			String ip, String commentTime, String isShow, String state,
			String picSrc, String star, String anonymousComment,
			String createPerson, String createTime, String modifyPerson,
			String modifyTime) {
		this.ordersDetail = ordersDetail;
		this.commentCode = commentCode;
		this.userName = userName;
		this.commentType = commentType;
		this.commentContent = commentContent;
		this.ip = ip;
		this.commentTime = commentTime;
		this.isShow = isShow;
		this.state = state;
		this.picSrc = picSrc;
		this.star = star;
		this.anonymousComment = anonymousComment;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orders_detail_id")
	public OrdersDetail getOrdersDetail() {
		return this.ordersDetail;
	}

	public void setOrdersDetail(OrdersDetail ordersDetail) {
		this.ordersDetail = ordersDetail;
	}

	@Column(name = "comment_code", length = 32)
	public String getCommentCode() {
		return this.commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	@Column(name = "user_name", length = 32)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "comment_type", length = 1)
	public String getCommentType() {
		return this.commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	@Column(name = "comment_content", length = 1000)
	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	@Column(name = "ip", length = 32)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "comment_time", length = 32)
	public String getCommentTime() {
		return this.commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	@Column(name = "is_show", length = 1)
	public String getIsShow() {
		return this.isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "pic_src", length = 1000)
	public String getPicSrc() {
		return this.picSrc;
	}

	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	@Column(name = "star", length = 5)
	public String getStar() {
		return this.star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	@Column(name = "anonymous_comment", length = 1)
	public String getAnonymousComment() {
		return this.anonymousComment;
	}

	public void setAnonymousComment(String anonymousComment) {
		this.anonymousComment = anonymousComment;
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
	
	@Column(name = "come_from", length = 32)
	public String getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}
	@Column(name = "audit_state", length = 1)
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

}
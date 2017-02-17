package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * UserGrowth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_growth", catalog = "ftoul_shop")
public class UserGrowth implements java.io.Serializable {

	// Fields

	private String id;
	private String firstLoginAble;
	private String firstLogin;
	private String manualLoginAble;
	private String manualLogin;
	private String birthAble;
	private String birth;
	private String orderFinishAble;
	private String orderFinish;
	private String commentAble;
	private String comment;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;

	// Constructors

	/** default constructor */
	public UserGrowth() {
	}

	/** full constructor */
	public UserGrowth(String firstLoginAble, String firstLogin,
			String manualLoginAble, String manualLogin, String birthAble,
			String birth, String orderFinishAble,String orderFinish,String commentAble,String comment,
			String createTime, String createPerson,
			String modifyTime, String modifyPerson) {
		this.firstLoginAble = firstLoginAble;
		this.firstLogin = firstLogin;
		this.manualLoginAble = manualLoginAble;
		this.manualLogin = manualLogin;
		this.birthAble = birthAble;
		this.birth = birth;
		this.orderFinishAble = orderFinishAble;
		this.orderFinish = orderFinish;
		this.commentAble = commentAble;
		this.comment = comment;
		this.createTime = createTime;
		this.createPerson = createPerson;
		this.modifyTime = modifyTime;
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

	@Column(name = "first_login_able", length = 1)
	public String getFirstLoginAble() {
		return this.firstLoginAble;
	}

	public void setFirstLoginAble(String firstLoginAble) {
		this.firstLoginAble = firstLoginAble;
	}

	@Column(name = "first_login", length = 32)
	public String getFirstLogin() {
		return this.firstLogin;
	}

	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}

	@Column(name = "manual_login_able", length = 1)
	public String getManualLoginAble() {
		return this.manualLoginAble;
	}

	public void setManualLoginAble(String manualLoginAble) {
		this.manualLoginAble = manualLoginAble;
	}

	@Column(name = "manual_login", length = 32)
	public String getManualLogin() {
		return this.manualLogin;
	}

	public void setManualLogin(String manualLogin) {
		this.manualLogin = manualLogin;
	}

	@Column(name = "birth_able", length = 1)
	public String getBirthAble() {
		return this.birthAble;
	}

	public void setBirthAble(String birthAble) {
		this.birthAble = birthAble;
	}

	@Column(name = "birth", length = 32)
	public String getBirth() {
		return this.birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
	@Column(name = "order_finish_able", length = 1)
	public String getOrderFinishAble() {
		return orderFinishAble;
	}

	public void setOrderFinishAble(String orderFinishAble) {
		this.orderFinishAble = orderFinishAble;
	}
	@Column(name = "order_finish", length =32)
	public String getOrderFinish() {
		return orderFinish;
	}

	public void setOrderFinish(String orderFinish) {
		this.orderFinish = orderFinish;
	}
	@Column(name = "comment_able", length = 1)
	public String getCommentAble() {
		return commentAble;
	}
	
	public void setCommentAble(String commentAble) {
		this.commentAble = commentAble;
	}
	@Column(name = "comment", length = 32)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

}
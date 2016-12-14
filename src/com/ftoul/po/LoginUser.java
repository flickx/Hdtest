package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * LoginUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "login_user")
public class LoginUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8727362029972624316L;
	private String id;
	private String loginName;
	private String password;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	private String state;

	// Constructors

	/** default constructor */
	public LoginUser() {
	}

	/** full constructor */
	public LoginUser(String loginName, String password, String createTime,
			String createPerson, String modifyTime, String modifyPerson,
			String state) {
		this.loginName = loginName;
		this.password = password;
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

	@Column(name = "login_name", length = 32)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "password", length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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
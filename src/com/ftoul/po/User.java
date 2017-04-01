package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1719272775014932668L;
	private String id;
	private String p2pId;
	private String ip;//注册IP
	private String source;//注册来源
	private String username;
	private Integer score;
	private String nickname;
	private String birth;
	private Integer xp;
	private String email;
	private String level;
	private String pic;
	private String name;
	private String sex;
	private String mobil;
	private String cardId;
	private String payPassword;
	private String passwordVersion;
	private String static_;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	private String state;
	private String driveId;
	private String cardFront; 
	private String cardBack; 
	private String validateCode;
	private String activeState;
	private String firstLogin;
	private String lastLoginTime;
	private String birthState;
	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String p2pId, String username, Integer score, Integer xp,
			String email, String level, String pic, String name, String sex,
			String mobil, String cardId, String payPassword,
			String passwordVersion, String static_, String createTime,
			String createPerson, String modifyTime, String modifyPerson,
			String state) {
		this.p2pId = p2pId;
		this.username = username;
		this.score = score;
		this.xp = xp;
		this.email = email;
		this.level = level;
		this.pic = pic;
		this.name = name;
		this.sex = sex;
		this.mobil = mobil;
		this.cardId = cardId;
		this.payPassword = payPassword;
		this.passwordVersion = passwordVersion;
		this.static_ = static_;
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

	@Column(name = "p2p_id", length = 32)
	public String getP2pId() {
		return this.p2pId;
	}

	public void setP2pId(String p2pId) {
		this.p2pId = p2pId;
	}
	
	@Column(name = "ip", length = 32)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name = "source", length = 32)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "username", length = 32)
	public String getUsername() {
		return this.username;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Column(name = "nickname", length = 32)
	public String getNickname() {
		return this.nickname;
	}
	

	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	@Column(name = "birth", length = 32)
	public String getBirth() {
		return this.birth;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "score")
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "xp")
	public Integer getXp() {
		return this.xp;
	}

	public void setXp(Integer xp) {
		this.xp = xp;
	}

	@Column(name = "email", length = 32)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "level", length = 32)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "pic", length = 100)
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name = "name", length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex", length = 10)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "mobil", length = 32)
	public String getMobil() {
		return this.mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}

	@Column(name = "card_id", length = 32)
	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Column(name = "pay_password", length = 32)
	public String getPayPassword() {
		return this.payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	@Column(name = "password_version", length = 32)
	public String getPasswordVersion() {
		return this.passwordVersion;
	}

	public void setPasswordVersion(String passwordVersion) {
		this.passwordVersion = passwordVersion;
	}

	@Column(name = "static", length = 1)
	public String getStatic_() {
		return this.static_;
	}

	public void setStatic_(String static_) {
		this.static_ = static_;
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

	@Column(name = "driveId", length = 32)
	public String getDriveId() {
		return driveId;
	}

	public void setDriveId(String driveId) {
		this.driveId = driveId;
	}
	@Column(name = "card_front", length = 100)
	public String getCardFront() {
		return cardFront;
	}

	public void setCardFront(String cardFront) {
		this.cardFront = cardFront;
	}
	@Column(name = "card_back", length = 100)
	public String getCardBack() {
		return cardBack;
	}
	
	public void setCardBack(String cardBack) {
		this.cardBack = cardBack;
	}
	@Column(name = "validate_code", length = 100)
	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	@Column(name = "active_state", length = 1)
	public String getActiveState() {
		return activeState;
	}

	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}
	@Column(name = "first_login", length = 1)
	public String getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}
	@Column(name = "last_login_time", length = 32)
	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	@Column(name = "birth_state", length = 1)
	public String getBirthState() {
		return birthState;
	}

	public void setBirthState(String birthState) {
		this.birthState = birthState;
	}
	
}
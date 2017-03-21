package com.ftoul.mongo.po;

public class User implements java.io.Serializable {

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
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getP2pId() {
		return this.p2pId;
	}

	public void setP2pId(String p2pId) {
		this.p2pId = p2pId;
	}
	
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUsername() {
		return this.username;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	

	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	public String getBirth() {
		return this.birth;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getXp() {
		return this.xp;
	}

	public void setXp(Integer xp) {
		this.xp = xp;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobil() {
		return this.mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getPayPassword() {
		return this.payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getPasswordVersion() {
		return this.passwordVersion;
	}

	public void setPasswordVersion(String passwordVersion) {
		this.passwordVersion = passwordVersion;
	}

	public String getStatic_() {
		return this.static_;
	}

	public void setStatic_(String static_) {
		this.static_ = static_;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreatePerson() {
		return this.createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyPerson() {
		return this.modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDriveId() {
		return driveId;
	}

	public void setDriveId(String driveId) {
		this.driveId = driveId;
	}
	public String getCardFront() {
		return cardFront;
	}

	public void setCardFront(String cardFront) {
		this.cardFront = cardFront;
	}
	public String getCardBack() {
		return cardBack;
	}
	
	public void setCardBack(String cardBack) {
		this.cardBack = cardBack;
	}
}
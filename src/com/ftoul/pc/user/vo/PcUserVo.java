package com.ftoul.pc.user.vo;

public class PcUserVo {

	private String id;

	private String username;// 用户名

	private String nickname;// 昵称

	private String sex;// 性别

	private String birth;// 生日

	private String mobil;// 手机号码

	private String email;// 邮箱

	private String name;// 真实姓名

	private String pic;// 会员头像

	private String cardFront;// 身份证正面

	private String cardBack;// 身份证背面

	private String activeState;// 邮箱是否验证

	private Integer coinAmount;// 蜂币数量

	private Integer couponCount;// 优惠券数量

	private Double couponTotal;// 优惠券总价格

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getMobil() {
		return mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActiveState() {
		return activeState;
	}

	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}

	public Integer getCoinAmount() {
		return coinAmount;
	}

	public void setCoinAmount(Integer coinAmount) {
		this.coinAmount = coinAmount;
	}

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

	public Double getCouponTotal() {
		return couponTotal;
	}

	public void setCouponTotal(Double couponTotal) {
		this.couponTotal = couponTotal;
	}

}

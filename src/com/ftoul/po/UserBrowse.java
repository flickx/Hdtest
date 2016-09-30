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
 * UserBrowse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_browse", catalog = "ftoul_shop")
public class UserBrowse implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4679849833817084834L;
	private String id;
	private User user;
	private String ipAddress;
	private String source;
	private Goods goods;
	private String browseTime;
	private String state;

	// Constructors

	/** default constructor */
	public UserBrowse() {
	}

	/** full constructor */
	public UserBrowse(User user, String ipAddress, String source,
			Goods goods, String browseTime) {
		this.user = user;
		this.ipAddress = ipAddress;
		this.source = source;
		this.goods = goods;
		this.browseTime = browseTime;
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
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "ip_address", length = 32)
	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name = "source", length = 200)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_id")
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Column(name = "browse_time", length = 32)
	public String getBrowseTime() {
		return this.browseTime;
	}

	public void setBrowseTime(String browseTime) {
		this.browseTime = browseTime;
	}
	@Column(name = "state", length = 1)
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}

}
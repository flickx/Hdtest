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
 * UserToken entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_token", catalog = "ftoul_shop")
public class UserToken implements java.io.Serializable {

	// Fields

	private String id;
	private User user;
	private String secretKey;
	private String mobilToken;
	private String pcToken;
	private String uploadTime;
	private String driveId;

	// Constructors

	/** default constructor */
	public UserToken() {
	}

	/** full constructor */
	public UserToken(User user, String secretKey, String mobilToken,String pcToken,
			String uploadTime) {
		this.user = user;
		this.secretKey = secretKey;
		this.mobilToken = mobilToken;
		this.pcToken = pcToken;
		this.uploadTime = uploadTime;
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

	@Column(name = "secret_key", length = 32)
	public String getSecretKey() {
		return this.secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	@Column(name = "mobil_token", length = 64)
	public String getMobilToken() {
		return mobilToken;
	}

	public void setMobilToken(String mobilToken) {
		this.mobilToken = mobilToken;
	}

	@Column(name = "pc_token", length = 64)
	public String getPcToken() {
		return pcToken;
	}

	public void setPcToken(String pcToken) {
		this.pcToken = pcToken;
	}

	@Column(name = "upload_time", length = 32)
	public String getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Column(name = "driveId", length = 32)
	public String getDriveId() {
		return driveId;
	}

	public void setDriveId(String driveId) {
		this.driveId = driveId;
	}

}
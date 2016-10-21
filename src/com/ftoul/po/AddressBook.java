package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * AddressBook entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "address_book", catalog = "ftoul_shop")
public class AddressBook implements java.io.Serializable {

	// Fields

	private String id;
	private String seq;
	private String displayName;
	private String name;
	private String nickname;
	private String birthday;
	private String note;
	private String phoneNumbers;
	private String emails;
	private String addresses;
	private String ims;
	private String organizations;
	private String photos;
	private String categories;
	private String urls;
	private String userMobile;
	private String version;
	private String createTime;

	// Constructors

	/** default constructor */
	public AddressBook() {
	}

	/** full constructor */
	public AddressBook(String seq, String displayName, String name,
			String nickname, String birthday, String note, String phoneNumbers,
			String emails, String addresses, String ims, String organizations,
			String photos, String categories, String urls, String userMobile) {
		this.seq = seq;
		this.displayName = displayName;
		this.name = name;
		this.nickname = nickname;
		this.birthday = birthday;
		this.note = note;
		this.phoneNumbers = phoneNumbers;
		this.emails = emails;
		this.addresses = addresses;
		this.ims = ims;
		this.organizations = organizations;
		this.photos = photos;
		this.categories = categories;
		this.urls = urls;
		this.userMobile = userMobile;
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

	@Column(name = "seq", length = 1000)
	public String getSeq() {
		return this.seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	@Column(name = "display_name", length = 100)
	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "nickname", length = 100)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "birthday", length = 100)
	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "note", length = 100)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "phoneNumbers", length = 1000)
	public String getPhoneNumbers() {
		return this.phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	@Column(name = "emails", length = 1000)
	public String getEmails() {
		return this.emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	@Column(name = "addresses", length = 1000)
	public String getAddresses() {
		return this.addresses;
	}

	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}

	@Column(name = "ims", length = 1000)
	public String getIms() {
		return this.ims;
	}

	public void setIms(String ims) {
		this.ims = ims;
	}

	@Column(name = "organizations", length = 1000)
	public String getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(String organizations) {
		this.organizations = organizations;
	}

	@Column(name = "photos", length = 1000)
	public String getPhotos() {
		return this.photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	@Column(name = "categories", length = 1000)
	public String getCategories() {
		return this.categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	@Column(name = "urls", length = 1000)
	public String getUrls() {
		return this.urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	@Column(name = "user_mobile", length = 32)
	public String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	
	@Column(name = "version", length = 1000)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Column(name = "create_time", length = 100)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
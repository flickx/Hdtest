package com.ftoul.po;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * BusinessManage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_manage")
public class BusinessManage implements java.io.Serializable {

	// Fields

	private String id;
	private String legalPerson;
	private String idCard;
	private String idCardFaceImg;
	private String idCardConImg;
	private String businessLicenceNumber;
	private String businessLicenceAddress;
	private String establishTime;
	private String manageScope;
	private String businessLicenceImg;
	private String businesSlicenceDate;
	private String operateId;
	private String operateTime;
	private String createId;
	private String createTime;
	private String state;

	// Constructors

	/** default constructor */
	public BusinessManage() {
	}

	/** minimal constructor */
	public BusinessManage(String legalPerson, String idCard,
			String idCardFaceImg, String idCardConImg,
			String businessLicenceNumber, String establishTime,
			String manageScope, String businessLicenceImg,
			String businesSlicenceDate,String businessLicenceAddress) {
		this.businessLicenceAddress=businessLicenceAddress;
		this.legalPerson = legalPerson;
		this.idCard = idCard;
		this.idCardFaceImg = idCardFaceImg;
		this.idCardConImg = idCardConImg;
		this.businessLicenceNumber = businessLicenceNumber;
		this.establishTime = establishTime;
		this.manageScope = manageScope;
		this.businessLicenceImg = businessLicenceImg;
		this.businesSlicenceDate = businesSlicenceDate;
	}

	/** full constructor */
	public BusinessManage(String legalPerson, String idCard,
			String idCardFaceImg, String idCardConImg,
			String businessLicenceNumber, String establishTime,
			String manageScope, String businessLicenceImg,
			String businesSlicenceDate, String operateId, String operateTime,
			String createId, String createTime, String state,
			Set<Business> businesses) {
		this.legalPerson = legalPerson;
		this.idCard = idCard;
		this.idCardFaceImg = idCardFaceImg;
		this.idCardConImg = idCardConImg;
		this.businessLicenceNumber = businessLicenceNumber;
		this.businessLicenceAddress=businessLicenceAddress;
		this.establishTime = establishTime;
		this.manageScope = manageScope;
		this.businessLicenceImg = businessLicenceImg;
		this.businesSlicenceDate = businesSlicenceDate;
		this.operateId = operateId;
		this.operateTime = operateTime;
		this.createId = createId;
		this.createTime = createTime;
		this.state = state;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "legal_person", nullable = false, length = 50)
	public String getLegalPerson() {
		return this.legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	@Column(name = "id_card", nullable = false, length = 50)
	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "Id_card_face_img", nullable = false, length = 500)
	public String getIdCardFaceImg() {
		return this.idCardFaceImg;
	}

	public void setIdCardFaceImg(String idCardFaceImg) {
		this.idCardFaceImg = idCardFaceImg;
	}

	@Column(name = "id_card_con_img", nullable = false, length = 500)
	public String getIdCardConImg() {
		return this.idCardConImg;
	}

	public void setIdCardConImg(String idCardConImg) {
		this.idCardConImg = idCardConImg;
	}

	@Column(name = "business_licence_number", nullable = false, length = 100)
	public String getBusinessLicenceNumber() {
		return this.businessLicenceNumber;
	}

	public void setBusinessLicenceNumber(String businessLicenceNumber) {
		this.businessLicenceNumber = businessLicenceNumber;
	}

	@Column(name = "establish_time", nullable = false, length = 50)
	public String getEstablishTime() {
		return this.establishTime;
	}

	public void setEstablishTime(String establishTime) {
		this.establishTime = establishTime;
	}

	@Column(name = "manage_scope", nullable = false, length = 500)
	public String getManageScope() {
		return this.manageScope;
	}

	public void setManageScope(String manageScope) {
		this.manageScope = manageScope;
	}

	@Column(name = "business_licence_img", nullable = false, length = 500)
	public String getBusinessLicenceImg() {
		return this.businessLicenceImg;
	}

	public void setBusinessLicenceImg(String businessLicenceImg) {
		this.businessLicenceImg = businessLicenceImg;
	}

	@Column(name = "busines_slicence_date", nullable = false, length = 100)
	public String getBusinesSlicenceDate() {
		return this.businesSlicenceDate;
	}

	public void setBusinesSlicenceDate(String businesSlicenceDate) {
		this.businesSlicenceDate = businesSlicenceDate;
	}

	@Column(name = "operate_id", length = 100)
	public String getOperateId() {
		return this.operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	@Column(name = "operate_time", length = 50)
	public String getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "create_id", length = 100)
	public String getCreateId() {
		return this.createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	@Column(name = "create_time", length = 50)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "business_licence_address", length = 100)
	public String getBusinessLicenceAddress() {
		return businessLicenceAddress;
	}

	public void setBusinessLicenceAddress(String businessLicenceAddress) {
		this.businessLicenceAddress = businessLicenceAddress;
	}


}
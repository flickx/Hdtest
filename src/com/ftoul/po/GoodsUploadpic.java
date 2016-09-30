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
 * GoodsUploadpic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods_uploadpic", catalog = "ftoul_shop")
public class GoodsUploadpic implements java.io.Serializable {

	private static final long serialVersionUID = -5588754152218498485L;
	private String id;
	private Goods goods;
	private String picSrc;
	private String thumbnailSrc;
	private String picType;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	private String state;

	// Constructors

	/** default constructor */
	public GoodsUploadpic() {
	}

	/** full constructor */
	public GoodsUploadpic(Goods goods, String picSrc, String picType, String createTime,
			String createPerson, String modifyTime, String modifyPerson,
			String state) {
		this.goods = goods;
		this.picSrc = picSrc;
		this.picType = picType;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_id")
	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
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
	
	@Column(name = "thumbnail_src", length = 500)
	public String getThumbnailSrc() {
		return thumbnailSrc;
	}

	/**
	 * @param thumbnailSrc the thumbnailSrc to set
	 */
	public void setThumbnailSrc(String thumbnailSrc) {
		this.thumbnailSrc = thumbnailSrc;
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

	/**
	 * @return the picSrc
	 */
	@Column(name = "pic_src", length = 500)
	public String getPicSrc() {
		return picSrc;
	}

	/**
	 * @param picSrc the picSrc to set
	 */
	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	/**
	 * @return the picType
	 */
	@Column(name = "pic_type", length = 32)
	public String getPicType() {
		return picType;
	}

	/**
	 * @param picType the picType to set
	 */
	public void setPicType(String picType) {
		this.picType = picType;
	}
	
}
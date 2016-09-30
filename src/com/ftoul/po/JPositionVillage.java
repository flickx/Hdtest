package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * JPositionVillage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "j_position_village", catalog = "ftoul_shop")
public class JPositionVillage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4055052743547178866L;
	private Integer id;
	private Long townId;
	private Long villageId;
	private String villageName;

	// Constructors

	/** default constructor */
	public JPositionVillage() {
	}

	/** full constructor */
	public JPositionVillage(Long townId, Long villageId, String villageName) {
		this.townId = townId;
		this.villageId = villageId;
		this.villageName = villageName;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "town_id")
	public Long getTownId() {
		return this.townId;
	}

	public void setTownId(Long townId) {
		this.townId = townId;
	}

	@Column(name = "village_id")
	public Long getVillageId() {
		return this.villageId;
	}

	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}

	@Column(name = "village_name", length = 64)
	public String getVillageName() {
		return this.villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

}
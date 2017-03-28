package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * JPositionTown entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="j_position_town")

public class JPositionTown  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	 private static final long serialVersionUID = -637994693925709180L;
	 private Integer id;
     private Long countyId;
     private Long townId;
     private String townName;
     private String state;

    // Constructors

    /** default constructor */
    public JPositionTown() {
    }

    
    /** full constructor */
    public JPositionTown(Long countyId, Long townId, String townName) {
        this.countyId = countyId;
        this.townId = townId;
        this.townName = townName;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid")@Id @GeneratedValue(generator="generator")
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="county_id")

    public Long getCountyId() {
        return this.countyId;
    }
    
    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }
    
    @Column(name="town_id")

    public Long getTownId() {
        return this.townId;
    }
    
    public void setTownId(Long townId) {
        this.townId = townId;
    }
    
    @Column(name="town_name", length=64)

    public String getTownName() {
        return this.townName;
    }
    
    public void setTownName(String townName) {
        this.townName = townName;
    }
	@Column(name = "state", length = 1)
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
}
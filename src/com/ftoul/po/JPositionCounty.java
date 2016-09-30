package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * JPositionCounty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="j_position_county"
    ,catalog="ftoul_shop"
)

public class JPositionCounty  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -57333607749067953L;
	private Integer id;
     private Long cityId;
     private Long countyId;
     private String countyName;


    // Constructors

    /** default constructor */
    public JPositionCounty() {
    }

    
    /** full constructor */
    public JPositionCounty(Long cityId, Long countyId, String countyName) {
        this.cityId = cityId;
        this.countyId = countyId;
        this.countyName = countyName;
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
    
    @Column(name="city_id")

    public Long getCityId() {
        return this.cityId;
    }
    
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
    
    @Column(name="county_id")

    public Long getCountyId() {
        return this.countyId;
    }
    
    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }
    
    @Column(name="county_name", length=64)

    public String getCountyName() {
        return this.countyName;
    }
    
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
   
}
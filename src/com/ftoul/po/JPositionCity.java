package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * JPositionCity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="j_position_city")

public class JPositionCity  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 2677145128146905736L;
	private Integer id;
     private Long provinceId;
     private Long cityId;
     private String cityName;
     private String state;

    // Constructors

    /** default constructor */
    public JPositionCity() {
    }

    
    /** full constructor */
    public JPositionCity(Long provinceId, Long cityId, String cityName) {
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.cityName = cityName;
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
    
    @Column(name="province_id")

    public Long getProvinceId() {
        return this.provinceId;
    }
    
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
    
    @Column(name="city_id")

    public Long getCityId() {
        return this.cityId;
    }
    
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
    
    @Column(name="city_name", length=64)

    public String getCityName() {
        return this.cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
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
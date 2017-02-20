package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;


/**
 * IndexCarouselPic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="index_carousel_pic")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE) 
public class IndexCarouselPic  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -2676793466704670685L;
	private String id;
	private String name;
     private String carouselType;
     private String createTime;
     private String createPerson;
     private String modifyTime;
     private String modifyPerson;
     private String state;
     private String picName;
     private String picAddress;
     private String thumbnailSrc;
     private String url;
     private String terminal;
     private String active;
     private String beginTime;
     private String endTime;
    // Constructors

    /** default constructor */
    public IndexCarouselPic() {
    }

    
    /** full constructor */
    public IndexCarouselPic(String carouselType, String createTime, String createPerson, String modifyTime, String modifyPerson, String state) {
        this.carouselType = carouselType;
        this.createTime = createTime;
        this.createPerson = createPerson;
        this.modifyTime = modifyTime;
        this.modifyPerson = modifyPerson;
        this.state = state;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid")@Id @GeneratedValue(generator="generator")
    
    @Column(name="id", unique=true, nullable=false, length=32)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="carousel_type", length=2)
    public String getCarouselType() {
        return this.carouselType;
    }
    
    public void setCarouselType(String carouselType) {
        this.carouselType = carouselType;
    }
    
    @Column(name="create_time", length=32)
    public String getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="create_person", length=32)
    public String getCreatePerson() {
        return this.createPerson;
    }
    
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }
    
    @Column(name="modify_time", length=32)
    public String getModifyTime() {
        return this.modifyTime;
    }
    
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    @Column(name="modify_person", length=32)
    public String getModifyPerson() {
        return this.modifyPerson;
    }
    
    public void setModifyPerson(String modifyPerson) {
        this.modifyPerson = modifyPerson;
    }
    
    @Column(name="state", length=1)
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    @Column(name="pic_name", length=100)
	public String getPicName() {
		return picName;
	}
    
	public void setPicName(String picName) {
		this.picName = picName;
	}

	@Column(name="pic_address", length=500)
	public String getPicAddress() {
		return picAddress;
	}

	public void setPicAddress(String picAddress) {
		this.picAddress = picAddress;
	}
	
	@Column(name="thumbnail_src", length=500)
	public String getThumbnailSrc() {
		return thumbnailSrc;
	}

	public void setThumbnailSrc(String thumbnailSrc) {
		this.thumbnailSrc = thumbnailSrc;
	}


	@Column(name="url", length=100)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="terminal", length=32)
	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	@Column(name="active", length=10)
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	@Column(name="begin_time", length=32)
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	
	@Column(name="end_time", length=32)
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Column(name="name", length=255)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
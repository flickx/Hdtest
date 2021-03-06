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
 * GoodsCanal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods_canal")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE) 
public class GoodsCanal implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String balanceMode;
    private String createTime;
    private String createPerson;
    private String modifyTime;
    private String modifyPerson;
    private String state;


	// Constructors

	/** default constructor */
	public GoodsCanal() {
	}

	/** full constructor */
	public GoodsCanal(String name) {
		this.name = name;
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

	@Column(name = "name", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	    @Column(name="balance_mode", length=32)
		public String getBalanceMode() {
			return balanceMode;
		}
		
		
		public void setBalanceMode(String balanceMode) {
			this.balanceMode = balanceMode;
		}
	   
}
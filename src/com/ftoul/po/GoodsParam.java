package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;


/**
 * GoodsParam entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="goods_param")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE) 
public class GoodsParam  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -6611600438671097129L;
	private String id;
     private Goods goods;
     private String paramName;
     private String price;
 	 private String batchprice;
 	 private String costprice;
     private String defalut;
     private String stock;
     private int saleNumber;
     private String marketPrice;
     private String createTime;
     private String createPerson;
     private String modifyTime;
     private String modifyPerson;
     private String state;


    // Constructors

    /** default constructor */
    public GoodsParam() {
    }

    
    /** full constructor */
    public GoodsParam(Goods goods, String paramName, String price, String defalut, String stock, int saleNumber, String marketPrice, String createTime, String createPerson, String modifyTime, String modifyPerson, String state) {
        this.goods = goods;
        this.paramName = paramName;
        this.price = price;
        this.defalut = defalut;
        this.stock = stock;
        this.saleNumber = saleNumber;
        this.marketPrice = marketPrice;
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
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="goods_id")

    public Goods getGoods() {
        return this.goods;
    }
    
    public void setGoods(Goods goods) {
        this.goods = goods;
    }
    
    @Column(name="param_name", length=50)

    public String getParamName() {
        return this.paramName;
    }
    
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
    
    @Column(name="price", length=10)

    public String getPrice() {
        return this.price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
    @Column(name="defalut", length=1)

    public String getDefalut() {
        return this.defalut;
    }
    
    public void setDefalut(String defalut) {
        this.defalut = defalut;
    }
    
    @Column(name="stock", length=10)

    public String getStock() {
        return this.stock;
    }
    
    public void setStock(String stock) {
        this.stock = stock;
    }
    
    @Column(name="sale_number", length=10)

    public int getSaleNumber() {
        return this.saleNumber;
    }
    
    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }
    
    @Column(name="market_price", length=10)

    public String getMarketPrice() {
        return this.marketPrice;
    }
    
    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
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
    
    @Column(name = "batchprice", length = 10)
	public String getBatchprice() {
		return this.batchprice;
	}

	public void setBatchprice(String batchprice) {
		this.batchprice = batchprice;
	}
	
	@Column(name = "costprice", length = 10)
	public String getCostprice() {
		return this.costprice;
	}

	public void setCostprice(String costprice) {
		this.costprice = costprice;
	}

}
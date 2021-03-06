package com.ftoul.po;

import java.math.BigDecimal;

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
 * OrdersDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="orders_detail")

public class OrdersDetail  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -7522502846704060534L;
	private String id;
     private Orders orders;
     private GoodsParam goodsParam;
     private String number;
     private String eventBegen;
     private String eventEnd;
     private String gift;
     private String giftNumber;
     private String discount;
     private String createTime;
     private String createPerson;
     private String modifyTime;
     private String modifyPerson;
     private String state;
     private String price;
     private String eventType;
     private String isAfter;
     private String shopId;
     private BigDecimal totalPrice;
     private String goodsTitle;
     private String paramName;
     private String picSrc;
     private String isComment;

    // Constructors

    /** default constructor */
    public OrdersDetail() {
    }

    
    /** full constructor */
    public OrdersDetail(Orders orders, GoodsParam goodsParam, String number, String eventBegen, String eventEnd, String gift, String giftNumber, String discount, String createTime, String createPerson, String modifyTime, String modifyPerson, String state) {
        this.orders = orders;
        this.goodsParam = goodsParam;
        this.number = number;
        this.eventBegen = eventBegen;
        this.eventEnd = eventEnd;
        this.gift = gift;
        this.giftNumber = giftNumber;
        this.discount = discount;
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
        @JoinColumn(name="orders_id")

    public Orders getOrders() {
        return this.orders;
    }
    
    public void setOrders(Orders orders) {
        this.orders = orders;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="good_param_id")

    public GoodsParam getGoodsParam() {
        return this.goodsParam;
    }
    
    public void setGoodsParam(GoodsParam goodsParam) {
        this.goodsParam = goodsParam;
    }
    
    @Column(name="number", length=32)

    public String getNumber() {
        return this.number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    @Column(name="event_begen", length=32)

    public String getEventBegen() {
        return this.eventBegen;
    }
    
    public void setEventBegen(String eventBegen) {
        this.eventBegen = eventBegen;
    }
    
    @Column(name="event_end", length=32)

    public String getEventEnd() {
        return this.eventEnd;
    }
    
    public void setEventEnd(String eventEnd) {
        this.eventEnd = eventEnd;
    }
    
    @Column(name="gift", length=1)

    public String getGift() {
        return this.gift;
    }
    
    public void setGift(String gift) {
        this.gift = gift;
    }
    
    @Column(name="gift_number", length=10)

    public String getGiftNumber() {
        return this.giftNumber;
    }
    
    public void setGiftNumber(String giftNumber) {
        this.giftNumber = giftNumber;
    }
    
    @Column(name="discount", length=32)

    public String getDiscount() {
        return this.discount;
    }
    
    public void setDiscount(String discount) {
        this.discount = discount;
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
    
    @Column(name="event_type", length=32)
	public String getEventType() {
		return eventType;
	}

    public void setEventType(String eventType) {
		this.eventType = eventType;
	}

    @Column(name="price", length=32)
	public String getPrice() {
		return price;
    }
	
	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name="is_after", length=1)
	public String getIsAfter() {
		return isAfter;
	}

	public void setIsAfter(String isAfter) {
		this.isAfter = isAfter;
	}

	@Column(name="shop_id", length=32)
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Column(name="total_price", length=20)
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name="goods_title", length=1000)
	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	@Column(name="param_name", length=20)
	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Column(name="pic_src", length=20)
	public String getPicSrc() {
		return picSrc;
	}

	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	@Column(name="is_comment", length=1)
	public String getIsComment() {
		return isComment;
	}

	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}
	
}
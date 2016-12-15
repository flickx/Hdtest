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
 * Orders entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="orders")

public class Orders  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -4958906136735588973L;
	private String id;
     private User user;
//     private UserAddress userAddress;
     private String goodsTotal;
     private String orderNumber;
     private String payType;
     private String confirmTime;
     private String orderStatic;
     private String deliverStatic;
     private String payStatic;
     private String confirmStatic;
     private String odd;
     private String logInfo;
     private LogisticsCompany logisticsCompany;
     private String orderTime;
     private String payTime;
     private String deliverTime;
     private String feedback;
     private String payable;
     private String orderPrice;
     private String shopMessage;
     private String outOfStock;
     private String beeCoins;
     private String coinPrice;
     private String invoiceType;
     private String invoiceHead;
     private String invoiceContent;
     private String createTime;
     private String createPerson;
     private String modifyTime;
     private String modifyPerson;
     private String state;
     private String oneIndiana;
     private String customsClearanceStatic;
     private String benefitPrice;
     private String benefitReason;
     private String consignee;
     private String consigneeTel;
     private String address;
     private BusinessStore shopId;
     private String parentOrdersId;
     private String isHasChild;
     private String province;
     private BigDecimal freight;
     private BigDecimal goodsTotalPrice;
    // Constructors

    /** default constructor */
    public Orders() {
    }

    
    /** full constructor */
    public Orders(User user,  String orderNumber, String payType, String confirmTime, String orderStatic, String deliverStatic, String payStatic, String confirmStatic, String odd, String orderTime, String payTime, String deliverTime, String feedback, String payable, String orderPrice, String shopMessage, String outOfStock, String beeCoins, String invoiceType, String invoiceHead, String invoiceContent, String createTime, String createPerson, String modifyTime, String modifyPerson, String state, String oneIndiana,String coinPrice) {
        this.user = user;
//        this.userAddress = userAddress;
        this.orderNumber = orderNumber;
        this.payType = payType;
        this.confirmTime = confirmTime;
        this.orderStatic = orderStatic;
        this.deliverStatic = deliverStatic;
        this.payStatic = payStatic;
        this.confirmStatic = confirmStatic;
        this.odd = odd;
        this.orderTime = orderTime;
        this.payTime = payTime;
        this.deliverTime = deliverTime;
        this.feedback = feedback;
        this.payable = payable;
        this.orderPrice = orderPrice;
        this.shopMessage = shopMessage;
        this.outOfStock = outOfStock;
        this.beeCoins = beeCoins;
        this.invoiceType = invoiceType;
        this.invoiceHead = invoiceHead;
        this.invoiceContent = invoiceContent;
        this.createTime = createTime;
        this.createPerson = createPerson;
        this.modifyTime = modifyTime;
        this.modifyPerson = modifyPerson;
        this.state = state;
        this.oneIndiana = oneIndiana;
        this.coinPrice = coinPrice;
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
        @JoinColumn(name="user_id")

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    @Column(name="goods_total", length=32)
	public String getGoodsTotal() {
		return goodsTotal;
	}


	public void setGoodsTotal(String goodsTotal) {
		this.goodsTotal = goodsTotal;
	}


//	@ManyToOne(fetch=FetchType.EAGER)
//        @JoinColumn(name="user_address_id")
//
//    public UserAddress getUserAddress() {
//        return this.userAddress;
//    }
//    
//    public void setUserAddress(UserAddress userAddress) {
//        this.userAddress = userAddress;
//    }
    
    @Column(name="order_number", length=32)

    public String getOrderNumber() {
        return this.orderNumber;
    }
    
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    @Column(name="pay_type", length=50)

    public String getPayType() {
        return this.payType;
    }
    
    public void setPayType(String payType) {
        this.payType = payType;
    }
    
    @Column(name="confirm_time", length=32)

    public String getConfirmTime() {
        return this.confirmTime;
    }
    
    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }
    
    @Column(name="order_static", length=1)

    public String getOrderStatic() {
        return this.orderStatic;
    }
    
    public void setOrderStatic(String orderStatic) {
        this.orderStatic = orderStatic;
    }
    
    @Column(name="deliver_static", length=1)

    public String getDeliverStatic() {
        return this.deliverStatic;
    }
    
    public void setDeliverStatic(String deliverStatic) {
        this.deliverStatic = deliverStatic;
    }
    
    @Column(name="pay_static", length=32)

    public String getPayStatic() {
        return this.payStatic;
    }
    
    public void setPayStatic(String payStatic) {
        this.payStatic = payStatic;
    }
    
    @Column(name="confirm_static", length=1)

    public String getConfirmStatic() {
        return this.confirmStatic;
    }
    
    public void setConfirmStatic(String confirmStatic) {
        this.confirmStatic = confirmStatic;
    }
    
    @Column(name="odd", length=10)

    public String getOdd() {
        return this.odd;
    }
    
    public void setOdd(String odd) {
        this.odd = odd;
    }
    
    @Column(name="log_info", length=1000)

    public String getLogInfo() {
        return this.logInfo;
    }
    
    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }
    
    @Column(name="order_time", length=32)

    public String getOrderTime() {
        return this.orderTime;
    }
    
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
    
    @Column(name="pay_time", length=32)

    public String getPayTime() {
        return this.payTime;
    }
    
    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
    
    @Column(name="deliver_time", length=32)

    public String getDeliverTime() {
        return this.deliverTime;
    }
    
    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }
    
    @Column(name="feedback", length=200)

    public String getFeedback() {
        return this.feedback;
    }
    
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    @Column(name="payable", length=32)

    public String getPayable() {
        return this.payable;
    }
    
    public void setPayable(String payable) {
        this.payable = payable;
    }
    
    @Column(name="order_price", length=32)

    public String getOrderPrice() {
        return this.orderPrice;
    }
    
    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }
    
    @Column(name="shop_message", length=200)

    public String getShopMessage() {
        return this.shopMessage;
    }
    
    public void setShopMessage(String shopMessage) {
        this.shopMessage = shopMessage;
    }
    
    @Column(name="out_of_stock", length=32)

    public String getOutOfStock() {
        return this.outOfStock;
    }
    
    public void setOutOfStock(String outOfStock) {
        this.outOfStock = outOfStock;
    }
    
    @Column(name="bee_coins", length=32)

    public String getBeeCoins() {
        return this.beeCoins;
    }
    
    public void setBeeCoins(String beeCoins) {
        this.beeCoins = beeCoins;
    }
    
    @Column(name="invoice_type", length=32)

    public String getInvoiceType() {
        return this.invoiceType;
    }
    
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
    
    @Column(name="invoice_head", length=32)

    public String getInvoiceHead() {
        return this.invoiceHead;
    }
    
    public void setInvoiceHead(String invoiceHead) {
        this.invoiceHead = invoiceHead;
    }
    
    @Column(name="invoice_content", length=100)

    public String getInvoiceContent() {
        return this.invoiceContent;
    }
    
    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
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
    
    @Column(name="one_indiana", length=32)

    public String getOneIndiana() {
        return this.oneIndiana;
    }
    
    public void setOneIndiana(String oneIndiana) {
        this.oneIndiana = oneIndiana;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="logistics_company_id")
	public LogisticsCompany getLogisticsCompany() {
		return logisticsCompany;
	}

	public void setLogisticsCompany(LogisticsCompany logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}

	@Column(name="customs_clearance_static", length=32)
	public String getCustomsClearanceStatic() {
		return customsClearanceStatic;
	}

	public void setCustomsClearanceStatic(String customsClearanceStatic) {
		this.customsClearanceStatic = customsClearanceStatic;
	}

	@Column(name="benefit_price", length=32)
	public String getBenefitPrice() {
		return benefitPrice;
	}

	public void setBenefitPrice(String benefitPrice) {
		this.benefitPrice = benefitPrice;
	}

	@Column(name="benefit_reason", length=1000)
	public String getBenefitReason() {
		return benefitReason;
	}

	public void setBenefitReason(String benefitReason) {
		this.benefitReason = benefitReason;
	}
	
	@Column(name="coin_price", length=32)
	public String getCoinPrice() {
		return coinPrice;
	}

	public void setCoinPrice(String coinPrice) {
		this.coinPrice = coinPrice;
	}


	@Column(name="consignee", length=100)
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	@Column(name="consignee_tel", length=100)
	public String getConsigneeTel() {
		return consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}
	
	@Column(name="address", length=200)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="parent_orders_id", length=100)
	public String getParentOrdersId() {
		return parentOrdersId;
	}

	public void setParentOrdersId(String parentOrdersId) {
		this.parentOrdersId = parentOrdersId;
	}
	
	@Column(name="is_has_child", length=100)
	public String getIsHasChild() {
		return isHasChild;
	}

	public void setIsHasChild(String isHasChild) {
		this.isHasChild = isHasChild;
	}

	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="shop_id")
	public BusinessStore getShopId() {
		return shopId;
	}

	public void setShopId(BusinessStore shopId) {
		this.shopId = shopId;
	}

	@Column(name="province", length=100)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name="freight", length=20)
	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	@Column(name="goods_Total_Price", length=20)
	public BigDecimal getGoodsTotalPrice() {
		return goodsTotalPrice;
	}

	public void setGoodsTotalPrice(BigDecimal goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}
	

}
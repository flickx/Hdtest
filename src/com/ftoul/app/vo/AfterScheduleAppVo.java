package com.ftoul.app.vo;

import java.util.List;

/**
 * 售后进度vo
 * @author yzw
 *
 */
public class AfterScheduleAppVo {
	private String id;//id
	private String serviceCode;//服务单号
	private String scheduleStatic;//进度状态
	private String goodsName;//商品名称
	private String salePrice;//价格
	private String goodsPic;//商品图片
	private String orderTime;//下单时间
	private List<AfterOpLogAppVo> logList;//进度详情
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getScheduleStatic() {
		return scheduleStatic;
	}
	public void setScheduleStatic(String scheduleStatic) {
		this.scheduleStatic = scheduleStatic;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	public String getGoodsPic() {
		return goodsPic;
	}
	public void setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public List<AfterOpLogAppVo> getLogList() {
		return logList;
	}
	public void setLogList(List<AfterOpLogAppVo> logList) {
		this.logList = logList;
	}
	
}

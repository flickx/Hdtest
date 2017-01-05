package com.ftoul.app.vo;

import java.util.List;

import com.ftoul.po.GoodsEventJoin;

/**
 * app限时抢商品vo类
 * @author lid
 *
 */
public class AppLimitGoodsVo {
	
	private String startTime;
	private long endTime;
	private List<AppLimitGoods> appLimitGoodsList;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public List<AppLimitGoods> getAppLimitGoodsList() {
		return appLimitGoodsList;
	}
	public void setAppLimitGoodsList(List<AppLimitGoods> appLimitGoodsList) {
		this.appLimitGoodsList = appLimitGoodsList;
	}
	
}

package com.ftoul.pc.interfaces.vo;

import java.util.List;

/**
 * app限时抢商品vo类
 * @author lid
 *
 */
public class PcLimitGoodsVo {
	
	private String startTime;
	private long endTime;
	private String hasBegin;
	private List<PcLimitGoods> pcLimitGoodsList;
	
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
	public List<PcLimitGoods> getPcLimitGoodsList() {
		return pcLimitGoodsList;
	}
	public void setPcLimitGoodsList(List<PcLimitGoods> pcLimitGoodsList) {
		this.pcLimitGoodsList = pcLimitGoodsList;
	}
	/**
	 * @return the hasBegin
	 */
	public String getHasBegin() {
		return hasBegin;
	}
	/**
	 * @param hasBegin the hasBegin to set
	 */
	public void setHasBegin(String hasBegin) {
		this.hasBegin = hasBegin;
	}
	
}

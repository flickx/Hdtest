package com.ftoul.app.vo;

import java.util.List;

/**
 * pc每日上新vo类
 * @author lid
 *
 */
public class PcNewGoodsVo {
	private String goodsType1Name;
	private List<PcNewGoods> pcNewGoodsList;
	private Integer total;
	/**
	 * @return the goodsType1Name
	 */
	public String getGoodsType1Name() {
		return goodsType1Name;
	}
	/**
	 * @param goodsType1Name the goodsType1Name to set
	 */
	public void setGoodsType1Name(String goodsType1Name) {
		this.goodsType1Name = goodsType1Name;
	}
	public List<PcNewGoods> getPcNewGoodsList() {
		return pcNewGoodsList;
	}
	public void setPcNewGoodsList(List<PcNewGoods> pcNewGoodsList) {
		this.pcNewGoodsList = pcNewGoodsList;
	}
	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}

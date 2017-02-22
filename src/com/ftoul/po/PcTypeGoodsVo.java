package com.ftoul.po;

import java.util.List;
import java.util.Map;

/**
 * pc首页商品分类商品VO
 * @author lid
 *
 */
public class PcTypeGoodsVo {
	private String goodsType1Name;//一级分类名称
	private Map<String,List<PcTypeGoods>> pcTypeGoodsList;//分类--分类商品List
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
	/**
	 * @return the pcTypeGoodsList
	 */
	public Map<String, List<PcTypeGoods>> getPcTypeGoodsList() {
		return pcTypeGoodsList;
	}
	/**
	 * @param pcTypeGoodsList the pcTypeGoodsList to set
	 */
	public void setPcTypeGoodsList(Map<String, List<PcTypeGoods>> pcTypeGoodsList) {
		this.pcTypeGoodsList = pcTypeGoodsList;
	}
	
}

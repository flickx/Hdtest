package com.ftoul.pc.goods.vo;

import java.util.List;

public class GoodsSearchVo {
	private List<GoodsSearchMainVo> goodsList;// 商品集合
	private List<SearchVo> goodsBrandList;// 品牌集合
	private List<SearchVo> countryList;// 国家地区集合
	private List<SearchVo> goodsTypeList;// 分类集合

	public List<GoodsSearchMainVo> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<GoodsSearchMainVo> goodsList) {
		this.goodsList = goodsList;
	}

	public List<SearchVo> getGoodsBrandList() {
		return goodsBrandList;
	}

	public void setGoodsBrandList(List<SearchVo> goodsBrandList) {
		this.goodsBrandList = goodsBrandList;
	}

	public List<SearchVo> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<SearchVo> countryList) {
		this.countryList = countryList;
	}

	public List<SearchVo> getGoodsTypeList() {
		return goodsTypeList;
	}

	public void setGoodsTypeList(List<SearchVo> goodsTypeList) {
		this.goodsTypeList = goodsTypeList;
	}

}

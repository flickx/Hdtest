package com.ftoul.pc.interfaces.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.app.vo.PcNewGoods;
import com.ftoul.app.vo.PcNewGoodsVo;
import com.ftoul.common.Common;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.GoodsType;
import com.ftoul.po.PcTypeGoods;
import com.ftoul.web.goods.service.GoodsServ;
import com.ftoul.web.goods.service.GoodsTypeServ;

/**
 * pc首页商品分类专区接口
 * @author LiDing
 * 2017-02-21
 */
@Controller
@RequestMapping(value = "/pcInterface/typeGoods/")
public class PcGoodsTypeAction {

	@Autowired
	private GoodsServ goodsServ; 
	@Autowired
	private GoodsTypeServ goodsTypeServ; 
	/**
	 * pc端接口：根据一级分类获取二级分类列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getSubTypesByPid")  
	public @ResponseBody Result getSubTypesByPid(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		List<Object> typeList = (List<Object>)goodsTypeServ.getNextGoodsTypes(parameter).getObj();
		return ObjectToResult.getResult(typeList);
	}
	/**
	 * pc端接口：随机查询一级分类下的商品，显示6个
	 * @param param 页面传递参数对象 一级分类id
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsByType")  
	public @ResponseBody Result getGoodsByType(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		List<Object[]> goodsList = (List<Object[]>)goodsServ.getGoodsByType(parameter).getObj();
		List<PcTypeGoods> typeGoodsList = new ArrayList<PcTypeGoods>();
		for (Object[] goods : goodsList) {
			PcTypeGoods typeGoods = new PcTypeGoods();
			typeGoods.setGoodsId(goods[0].toString());
			typeGoods.setTitle(goods[1].toString());
			typeGoods.setSubTitle(goods[2].toString());
			typeGoods.setPrice((double)goods[3]);
			typeGoods.setMarketPrice(Double.parseDouble(goods[4].toString()));
			typeGoods.setPicSrc(goods[5].toString());
			typeGoodsList.add(typeGoods);
		}
		return ObjectToResult.getResult(typeGoodsList);
	}
}

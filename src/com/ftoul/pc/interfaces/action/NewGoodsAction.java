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
import com.ftoul.web.goods.service.GoodsServ;
import com.ftoul.web.goods.service.GoodsTypeServ;

/**
 * pc限时抢接口
 * @author LiDing
 * 2017-02-17
 */
@Controller
@RequestMapping(value = "/pcInterface/newGoods/")
public class NewGoodsAction {

	@Autowired
	private GoodsServ goodsServ; 
	@Autowired
	private GoodsTypeServ goodsTypeServ; 
	/**
	 * 获取pc端首页每日上新商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getPcNewGoods")  
	public @ResponseBody Result getPcNewGoods(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		List<Object[]> goodsList = (List<Object[]>)goodsServ.getPcNewGoods(parameter).getObj();
		List<PcNewGoods> newGoodsList = new ArrayList<PcNewGoods>();
		for (Object[] goods : goodsList) {
			PcNewGoods newGoods = new PcNewGoods();
			newGoods.setGoodsId(goods[0].toString());
			newGoods.setTitle(goods[1].toString());
			newGoods.setModel(goods[2].toString());
			newGoods.setPrice((double)goods[3]);
			newGoods.setMarketPrice(Double.parseDouble(goods[4].toString()));
			newGoods.setPicSrc(goods[5].toString());
			newGoods.setNum(Double.toString(Math.round((double)goods[3]*1.0/Double.parseDouble(goods[4].toString())*10)));
			newGoodsList.add(newGoods);
		}
		return ObjectToResult.getResult(newGoodsList);
	}
	/**
	 * 获取pc端每日上新详情页面商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getPcNewGoodsList")  
	public @ResponseBody Result getPcNewGoodsList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		//获取全部一级分类
		List<GoodsType> typeList = (List<GoodsType>)goodsTypeServ.getGoodsTypeLevel1List(parameter).getObj();
		List<PcNewGoodsVo> pcNewGoodsList = new ArrayList<PcNewGoodsVo>();
		//查询一级分类下每日上新商品
		for (GoodsType type : typeList) {
			PcNewGoodsVo vo = new PcNewGoodsVo();
			List<Object[]> goodsList = (List<Object[]>)goodsServ.getPcNewGoodsList(type.getId()).getObj();
			List<PcNewGoods> newGoodsList = new ArrayList<PcNewGoods>();
			for (Object[] goods : goodsList) {
				PcNewGoods newGoods = new PcNewGoods();
				newGoods.setGoodsId(goods[0].toString());
				newGoods.setTitle(goods[1].toString());
				newGoods.setModel(goods[2].toString()); 
				newGoods.setPrice((double)goods[3]);
				newGoods.setMarketPrice(Double.parseDouble(goods[4].toString()));
				newGoods.setPicSrc(goods[5].toString());
				newGoods.setNum(Double.toString(Math.round((double)goods[3]*1.0/Double.parseDouble(goods[4].toString())*10)));
				newGoodsList.add(newGoods);  
			}
			vo.setTotal(goodsList.size());
			vo.setGoodsType1Name(type.getName());
			vo.setPcNewGoodsList(newGoodsList);
			pcNewGoodsList.add(vo);
		}
		return ObjectToResult.getResult(pcNewGoodsList);
	}
}

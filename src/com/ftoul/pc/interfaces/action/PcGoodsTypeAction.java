package com.ftoul.pc.interfaces.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
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
		return goodsTypeServ.getNextGoodsTypes(parameter);
	}
	
	/**
	 * pc端接口：通过商品一级类别得到二三级分类列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getTypeList")  
	public @ResponseBody Result getTypeList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsTypeServ.getGoodsTypeLevel23from1List(parameter);
	}
	
	/**
	 * pc端接口：通过商品一级类别得到二三级分类商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsList")  
	public @ResponseBody Result getGoodsList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsTypeServ.getGoodsTypeList(parameter);
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
		return goodsServ.getGoodsByType(parameter);
	}
}

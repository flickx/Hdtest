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
 * pc每日上新接口
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
		return goodsServ.getPcNewGoods(parameter);
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
		return goodsServ.getPcNewGoodsList(parameter);
	}
}

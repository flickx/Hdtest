package com.ftoul.pc.interfaces.action;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goodsEvent.service.GoodsEventServ;
import com.ftoul.pc.interfaces.vo.PcSqpGoods;
import com.ftoul.po.GoodsEventJoin;
import com.ftoul.po.GoodsParam;

/**
 * pc端省钱趴接口
 * @author lid
 *
 */
@Controller
@RequestMapping(value = "/pcInterface/sqpGoods/")
public class SqpAction {
	@Autowired
	private GoodsEventServ goodsEventServ;
	/**
	 *获取省钱趴后台配置价格 
	 */
	@RequestMapping(value = "getSqpPrice")  
	public @ResponseBody Result getSqpPrice(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return  goodsEventServ.getSqpPrice(parameter);
	}
	
	/**
	 * 通过活动代码获取所有活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getGoodsByEventCode")  
	public @ResponseBody Result getGoodsByEventCode(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result re =  goodsEventServ.getAppGoodsByEventCode(parameter);
		List<GoodsEventJoin> goodsAppVos = (List<GoodsEventJoin>)re.getObj();
		List<PcSqpGoods> sqpList = new ArrayList<PcSqpGoods>();
		for (GoodsEventJoin goodsAppVo : goodsAppVos) {
			GoodsParam goodsParam = (GoodsParam)goodsEventServ.getSaleSumByGoodsId(goodsAppVo.getGoods().getId()).getObj();
			PcSqpGoods i  =new PcSqpGoods();
			i.setGoodsId(goodsAppVo.getGoods().getId());
			i.setPicSrc(goodsAppVo.getGoods().getPicSrc());
			i.setTitle(goodsAppVo.getGoods().getTitle());
			i.setSubTitle(goodsAppVo.getGoods().getSubtitle());
			i.setMarketPrice(goodsParam.getMarketPrice());
			if (null != goodsAppVo.getEventPrice()) {
				i.setEventPrice(goodsAppVo.getEventPrice().toString());
			}
			if (null != goodsAppVo.getGoods().getPrice()) {
				i.setPrice(goodsAppVo.getGoods().getPrice().toString());
			}
			if (null != goodsAppVo.getQuantity()) {
				i.setQunatity(goodsAppVo.getQuantity().toString());
				NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
			    format.setMinimumFractionDigits(0);// 设置小数位
			    if (null != goodsAppVo.getDefaultQuantity()) {
				    i.setNum(format.format(1-goodsAppVo.getQuantity()*1.0/goodsAppVo.getDefaultQuantity()));
				}
			}
			i.setSaleSum(Integer.toString(goodsParam.getSaleNumber()));
			sqpList.add(i);
		}  
		re.setObj(sqpList);
		return re;
	}
}

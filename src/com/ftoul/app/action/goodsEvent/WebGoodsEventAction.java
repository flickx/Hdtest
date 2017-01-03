/**
 * 
 */
package com.ftoul.app.action.goodsEvent;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.app.vo.IndexCarouselAppVo;
import com.ftoul.app.vo.IndexGoodsAppVo;
import com.ftoul.common.Common;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goodsEvent.service.GoodsEventServ;

/**
 * 活动操作相关类
 * @author
 *
 */
@Controller("AppEvent")
@RequestMapping(value = "/app/goodsEvent")
public class WebGoodsEventAction {
	
	@Autowired
	private GoodsEventServ goodsEventServ;
	
	/**
	 * 通过ID获取活动对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsEventById")  
	public @ResponseBody Result getGoodsEventById(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getGoodsEventById(parameter);
	}
	
	/**
	 * 通过代码获取活动对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsEventByCode")  
	public @ResponseBody Result getGoodsEventByCode(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		Result re =  goodsEventServ.getGoodsEventByCode(parameter);
		List<IndexGoodsAppVo> index = (List<IndexGoodsAppVo>)re.getObj();
		return ObjectToResult.getResult(index);
	}
	
	/**
	 * 通过ID获取活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getGoodsByEventId")  
	public @ResponseBody Result getGoodsByEventId(String param) throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getGoodsByEventId(parameter);
	}
	
	/**
	 * 通过活动代码获取所有活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getGoodsByEventCode")  
	public @ResponseBody Result getGoodsByEventCode(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getGoodsByEventCode(parameter);
	}
	/**
	 * 获取每日上新商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getNewestGoodsList")  
	public @ResponseBody Result getNewestGoodsList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsEventServ.getNewestGoodsList(parameter);
	}
	
	/**
	 * 获取"为你推荐"商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getCommendGoodsList")  
	public @ResponseBody Result getCommendGoodsList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result re =  goodsEventServ.getAllGoods(parameter);
		List<IndexGoodsAppVo> goodsList = (List<IndexGoodsAppVo>)re.getObj();
		return ObjectToResult.getResult(goodsList);
	}
}

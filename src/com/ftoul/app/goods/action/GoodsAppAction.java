/**
 * 
 */
package com.ftoul.app.goods.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.api.sms.util.MessageUtil;
import com.ftoul.common.Common;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goods.vo.GoodsVo;
import com.ftoul.web.goods.service.GoodsBrandServ;
import com.ftoul.web.goods.service.GoodsParamServ;
import com.ftoul.web.goods.service.GoodsPropTypeServ;
import com.ftoul.web.goods.service.GoodsPropertyTypeInfoServ;
import com.ftoul.web.goods.service.GoodsServ;
import com.ftoul.web.goods.service.GoodsTypeServ;
import com.ftoul.web.goods.service.UserCollectionServ;

/**
 *
 * 类描述  商品品牌控制类
 * 
 * @author: yw
 * @date： 日期：2016年7月20日 时间：上午11:11:05
 * @version 1.0
 *
 */
@Controller("GoodsAppAction")
@RequestMapping(value = "/app/goods")
public class GoodsAppAction {
	@Autowired
	private GoodsTypeServ goodsTypeServ;
	
	@Autowired
	private GoodsPropTypeServ goodsPropTypeServ;
	
	@Autowired
	private GoodsPropertyTypeInfoServ goodsPropertyTypeInfoServ;
	
	@Autowired
	private UserCollectionServ userCollectionServ;
	
	@Autowired
	private GoodsServ goodsServ;
	
	@Autowired
	private GoodsParamServ goodsParamServ;
	
	
	
	@Autowired
	private GoodsBrandServ goodsBrandServ;
	
	/**
	 * 获取下一级商品类别
	 * @param param 当前级别参数
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getNextGoodsTypes")  
	public @ResponseBody Result getNextGoodsTypes(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result r = goodsTypeServ.getNextGoodsTypes(parameter);
		GoodsVo vo = (GoodsVo)r.getObj();
		return ObjectToResult.getResult(vo);
	}
	
	
	/**
	 * 获取第一级商品类别
	 * @param param 当前级别参数
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsTypeLevel1List")  
	public @ResponseBody Result getGoodsTypeLevel1List(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsTypeServ.getGoodsTypeLevel1List(parameter);
	}
			
	
	
	@RequestMapping(value = "sendMessage")  
	public @ResponseBody Result sendMessage(String param) throws Exception{
//		Parameter parameter = Common.jsonToParam(param);
//		return goodsTypeServ.getNextGoodsTypes(parameter);
		
		String mobile ="18570614771";
		String content="test";
		String ret=MessageUtil.send(mobile, content);
		System.out.println(ret);
		return null;
	}
	
	/**
	 * 保存商品类别
	 * @param param 
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveGoodsType")  
	public @ResponseBody Result saveGoodsType(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsTypeServ.saveGoodsType(parameter);
	}
	
	/**
	 * 保存商品
	 * @param param 
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveGoodsPropType")  
	public @ResponseBody Result saveGoodsPropType(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropTypeServ.saveGoodsPropType(parameter);
	}
	
	
	/**
	 * 保存商品属性分类
	 * @param param 
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveGoods")  
	public @ResponseBody Result saveGoods(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsServ.saveGoods(parameter);
	}
	
	/**
	 * 通过商品类别一级得到二三级商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getGoodsTypeLevel23from1List")  
	public @ResponseBody Result getGoodsTypeLevel23from1List(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsTypeServ.getGoodsTypeLevel23from1List(parameter);
	}
	
	/**
	 * 
	 * 保存/更新商品品牌
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "saveGoodsBrand")  
	public @ResponseBody Result saveGoodsBrand(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBrandServ.saveGoodsBrand(parameter);
	}
	
	
	/**
	 * 
	 * 通过id查找goodsBrand
	 * @param   param Parameter对象
	 */
	@RequestMapping(value = "getGoodsBrandById")  
	public @ResponseBody Result getGoodsBrandById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBrandServ.getGoodsBrandById(parameter);
	}
	
	
	/**
	 *	得到第一级的所有商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getLevel1GoodsType")  
	public @ResponseBody Result getLevel1GoodsType(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsTypeServ.getLevel1GoodsType(parameter);
	}
	
	
	/**
	 *	得到第三级的所有商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getLevel3GoodsType")  
	public @ResponseBody Result getLevel3GoodsType(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsTypeServ.getLevel3GoodsType(parameter);
	}
	
	/**
	 * 获取商品品牌（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsBrandListPage")  
	public @ResponseBody Result getGoodsBrandListPage(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsBrandServ.getGoodsBrandListPage(parameter);
	}
	
	
	/**
	 * 得到商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsList")  
	public @ResponseBody Result getGoodsList(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsServ.getGoodsList(parameter);
	}
	
	/**
	 * 得到所有商品属性大类
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsPropType")  
	public @ResponseBody Result getGoodsPropType(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropTypeServ.getGoodsPropType(parameter);
	}
	
	/**
	 * 通过大类id得到商品属性详情
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsPropInfoTypeListByTypeId")  
	public @ResponseBody Result getGoodsPropInfoTypeListByTypeId(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropertyTypeInfoServ.getGoodsPropInfoTypeListByTypeId(parameter);
	}
	
	
	/**
	 * 商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsListPage")  
	public @ResponseBody Result getGoodsListPage(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsServ.getGoodsListPage(parameter);
	}
	
	/**
	 * 商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsDetail")  
	public @ResponseBody Result getGoodsDetail(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		Result ret =  goodsServ.getGoodsDetail(parameter);
		GoodsVo goodsVo = (GoodsVo)ret.getObj();
		return ObjectToResult.getResult(goodsVo);
	}
	
	/**
	 * 商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsListByKeyWord")  
	public @ResponseBody Result getGoodsListByKeyWord(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsServ.getGoodsListByKeyWord(parameter);
	}
	/**
	 * 查询商品的活动价
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsEventPrice")  
	public @ResponseBody Result getGoodsEventPrice(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsServ.getGoodsEventPrice(parameter);
	}
	
	/**
	 * 查找所有的跨境商品
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsListPageByCross")  
	public @ResponseBody Result getGoodsListPageByCross(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsServ.getGoodsListPageByCross(parameter);
	}
	
	/**
	 *  新增收藏
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveUserCollection")  
	public @ResponseBody Result saveUserCollection(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userCollectionServ.saveUserCollection(parameter);
	}

	/**
	 *  新增收藏
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "findUserCollection")  
	public @ResponseBody Result findUserCollection(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userCollectionServ.findUserCollection(parameter);
	}
	
	@RequestMapping(value = "getUserCollectionList")  
	public @ResponseBody Result getUserCollectionList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userCollectionServ.getUserCollectionList(parameter);
	}
	
	/**
	 * 删除收藏商品.
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteUserCollection")  
	public @ResponseBody Result deleteUserCollection(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userCollectionServ.deleteUserCollection(parameter);
	}
	
	
	/**
	 * 取消收藏商品.
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delUserCollection")  
	public @ResponseBody Result delUserCollection(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return userCollectionServ.delUserCollection(parameter);
	}
	
	
	/**
	 * 根据goodsId得到总库存
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getSumStockBygoodsId")  
	public @ResponseBody int getSumStockBygoodsId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsParamServ.getSumStockBygoodsId(parameter);
	}
	
	
}

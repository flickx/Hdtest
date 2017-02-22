/**
 * 
 */
package com.ftoul.pc.goods.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.app.action.goods.service.GoodsAppServ;
import com.ftoul.app.vo.GoodsTypeAppVo;
import com.ftoul.app.vo.IndexGoodsAppVo;
import com.ftoul.common.Common;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsType;
import com.ftoul.web.business.service.BusinessWebServ;
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
@Controller("PcGoodsAction")
@RequestMapping(value = "/pc/goods")
public class PcGoodsAction {
	@Autowired
	private GoodsTypeServ goodsTypeServ;
	
	@Autowired
	private GoodsPropTypeServ goodsPropTypeServ;
	
	@Autowired
	private GoodsPropertyTypeInfoServ goodsPropertyTypeInfoServ;
	
	@Autowired
	private UserCollectionServ userCollectionServ;
	
	@Autowired
	private GoodsAppServ goodsAppServ;
	
	@Autowired
	private GoodsParamServ goodsParamServ;
	
	@Autowired
	private GoodsBrandServ goodsBrandServ;
	
	@Autowired
	private BusinessWebServ businessWebServ;
	
	@Autowired
	private GoodsServ goodsServ;
	
	/**
	 * 商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsDetail")  
	public @ResponseBody Result getGoodsDetail(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsAppServ.getGoodsDetail(parameter);
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
		Result ret = userCollectionServ.findUserCollection(parameter);
		if(ret.getResult()==0){
			return userCollectionServ.saveUserCollection(parameter);
		}else{
			return userCollectionServ.delUserCollection(parameter);
		}
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
		return goodsAppServ.getUserCollectionList(parameter);
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
	 * app商品分类页面 获取第一级商品类别
	 * @param param 当前级别参数
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsTypeLevel1List")  
	public @ResponseBody Result getGoodsTypeLevel1List(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result re =  goodsTypeServ.getGoodsTypeLevel1List(parameter);
		List<GoodsType> goodsTypeList = (List<GoodsType>)re.getObj();
		List<GoodsTypeAppVo> typeList = new ArrayList<GoodsTypeAppVo>();
		for (GoodsType goodsTypeAppVo : goodsTypeList) {
			GoodsTypeAppVo type = new GoodsTypeAppVo();
			type.setId(goodsTypeAppVo.getId());
			type.setName(goodsTypeAppVo.getName());
			type.setPicSrc(goodsTypeAppVo.getPicSrc());
			typeList.add(type);
		}
		return ObjectToResult.getResult(typeList);
	}
	/**
	 * app商品分类页面 通过商品类别一级得到二三级商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getGoodsTypeLevel23from1List")  
	public @ResponseBody Result getGoodsTypeLevel23from1List(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsTypeServ.getGoodsTypeLevel23from1List(parameter);
	}
	
	/**
	 * app首页关键字搜索
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getAppGoodsListByKeyWord")  
	public @ResponseBody Result getAppGoodsListByKeyWord(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		Result re =  goodsServ.getAppGoodsListByKeyWord(parameter);
		List<Goods> goodsList = (List<Goods>)re.getObj();
		List<IndexGoodsAppVo> goodsAppVoList = new ArrayList<IndexGoodsAppVo>();
		for (Goods goods : goodsList) {
			IndexGoodsAppVo i  =new IndexGoodsAppVo();
			i.setGoodsId(goods.getId());
			i.setPicSrc(goods.getPicSrc());
			i.setPrice(goods.getPrice());
			i.setTitle(goods.getTitle());
			goodsAppVoList.add(i);
		}
		return ObjectToResult.getResult(goodsAppVoList);
	}
	/**
	 * app查询指定分类商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsListByGoodsType")  
	public @ResponseBody Result getGoodsListByGoodsType(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		Result re =  goodsServ.getGoodsListPage(parameter);
		List<Goods> goodsList = (List<Goods>)re.getObj();
		List<IndexGoodsAppVo> goodsAppVoList = new ArrayList<IndexGoodsAppVo>();
		for (Goods goods : goodsList) {
			IndexGoodsAppVo i  =new IndexGoodsAppVo();
			i.setGoodsId(goods.getId());
			i.setPicSrc(goods.getPicSrc());
			i.setPrice(goods.getPrice());
			i.setTitle(goods.getTitle());
			goodsAppVoList.add(i);
		}
		return ObjectToResult.getResult(goodsAppVoList);
	}
}

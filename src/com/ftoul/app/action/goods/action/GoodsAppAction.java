/**
 * 
 */
package com.ftoul.app.action.goods.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.app.action.goods.service.GoodsAppServ;
import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.business.service.BusinessWebServ;
import com.ftoul.web.goods.service.GoodsBrandServ;
import com.ftoul.web.goods.service.GoodsParamServ;
import com.ftoul.web.goods.service.GoodsPropTypeServ;
import com.ftoul.web.goods.service.GoodsPropertyTypeInfoServ;
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
@Controller("AppGoodsAppAction")
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
	private GoodsAppServ goodsAppServ;
	
	@Autowired
	private GoodsParamServ goodsParamServ;
	
	@Autowired
	private GoodsBrandServ goodsBrandServ;
	
	@Autowired
	private BusinessWebServ businessWebServ;
	
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
			return ret;
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
	
	
	
	
}

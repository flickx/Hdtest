package com.ftoul.pc.shoppingCart.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.shoppingCart.action.service.CartPcServ;

@Controller("CartPcAction")
@RequestMapping("/pc/cart")
public class CartPcAction {
	@Autowired
	private CartPcServ cartPcServ;
	/**
	 * 根据用户ID获取购物车信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getShopCartByUserId")  
	public @ResponseBody Result getShopCartByUserId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return cartPcServ.getShopCartByUserId(parameter);
	}
	/**
	 * 保存购物车信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveShopCart")  
	public @ResponseBody Result saveShopCart(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return cartPcServ.saveShopCart(parameter);
	}
	/**
	 * 修改购物车数量
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateShopCart")  
	public @ResponseBody Result updateShopCart(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return cartPcServ.updateShopCart(parameter);
	}
	
	/**
	 * 删除购物车
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delShopCart")  
	public @ResponseBody Result delShopCart(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return cartPcServ.delShopCart(parameter);
	}
	/**
	 * 清空购物车
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "clearShopCart")  
	public @ResponseBody Result clearShopCart(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return cartPcServ.clearShopCart(parameter);
	}
	/**
	 * 清除无货商品
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "clearNoStock")  
	public @ResponseBody Result clearNoStock(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return cartPcServ.clearNoStock(parameter);
	}
	/**
	 * 推荐商品
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsList")  
	public @ResponseBody Result getGoodsList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return cartPcServ.getGoodsList(parameter);
	}
}

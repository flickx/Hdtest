package com.ftoul.manage.cart.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.cart.service.CartServ;

@Controller
@RequestMapping("/manage/cart")
public class CartAction {
	
	@Autowired
	private CartServ cartServ;
	/**
	 * 根据用户ID获取购物车信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getShopCartByUserId")  
	public @ResponseBody Result getShopCartByUserId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return cartServ.getShopCartByUserId(parameter);
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
		return cartServ.saveShopCart(parameter);
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
		return cartServ.updateShopCart(parameter);
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
		return cartServ.delShopCart(parameter);
	}
}

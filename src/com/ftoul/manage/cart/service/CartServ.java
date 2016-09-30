package com.ftoul.manage.cart.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface CartServ {
	/**
	 * 根据用户ID获取购物车信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getShopCartByUserId(Parameter param) throws Exception;
	/**
	 * 保存购物车信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result saveShopCart(Parameter param) throws Exception;
	
	/**
	 * 更新购物车信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result updateShopCart(Parameter param) throws Exception;

	/**
	 * 删除购物车
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result delShopCart(Parameter param) throws Exception;
	
	
	/**
	 * 获取商品列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getGoodsList(Parameter param)throws Exception;
	
	
	/**
	 * 清空购物车
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result clearShopCart(Parameter param)throws Exception;
}

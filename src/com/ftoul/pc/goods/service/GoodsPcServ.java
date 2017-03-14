package com.ftoul.pc.goods.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 商品PC端接口
 * @author yzw
 *
 */
public interface GoodsPcServ{
	/**
	 * 查询商品详情
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result getGoodsDetail(Parameter parameter) throws Exception;
	
	/**
	 * 根据搜索名获取商品列表
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result getGoodsBySearchName(Parameter parameter) throws Exception;
}

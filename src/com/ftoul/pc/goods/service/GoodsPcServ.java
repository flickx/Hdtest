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
	 * 查询品牌
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result getGoodsBrand(Parameter parameter) throws Exception;
	
	/**
	 * 根据一级分类id查询品牌
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result getGoodsBrandByTypeId1(Parameter parameter) throws Exception;
	
	/**
	 * 根据二级分类id查询品牌
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result getGoodsBrandByTypeId2(Parameter parameter) throws Exception;
}

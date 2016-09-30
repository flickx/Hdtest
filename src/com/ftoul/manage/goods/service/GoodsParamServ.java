package com.ftoul.manage.goods.service;

import org.springframework.stereotype.Service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.GoodsParam;

/**
 * 
	*
	* 类描述：商品参数
	* @author: yw
	* @date： 日期：2016年8月22日 时间：下午3:03:30
	* @version 1.0
	*
*/


@Service("GoodsParamImpl")
public interface GoodsParamServ{

	/**
	 * 保存商品第三步
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveGoodsParam(Parameter parameter) throws Exception;
	
	/**
	 * 保存商品第三步
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveGoodsParamByGoods(Parameter parameter) throws Exception;
	/**
	 * 
	 * 删除商品参数
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */

	Result delGoodsParam(Parameter parameter)  throws Exception;
	
	/**
	 * 
	 * 编辑商品参数
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */

	Result editGoodsParam(Parameter parameter)  throws Exception;
	/**
	 * 
	 * 得到商品参数列列表
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */

	Result getGoodsParam(Parameter parameter)  throws  Exception;
	
	/**
	 * 根据商品ID获取商品信息参数
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	GoodsParam getGoodsParamByGoodsId(String goodsId)throws  Exception;
	/**
	 * 根据商品ID获取商品信息参数
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	
	Result getGoodsParamList(Parameter parameter)throws  Exception;

	/**
	 * 商品入库操作
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result saveStock(Parameter parameter)throws  Exception;

	/**
	 *	设置商品参数为默认
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result saveDefault(Parameter parameter)throws  Exception;
}

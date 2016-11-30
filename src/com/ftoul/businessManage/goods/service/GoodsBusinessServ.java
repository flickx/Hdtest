package com.ftoul.businessManage.goods.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 
*
* 类描述：
* @author: yw
* @date： 日期：2016年8月8日 时间：上午9:58:52
* @version 1.0
*
*/


public interface GoodsBusinessServ{

	/**
	 * 保存/更新商品对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveGoods(Parameter param) throws Exception;

	/**
	 * 
	 * 得到商品列表
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	Result getGoodsList(Parameter parameter) throws Exception;

	/**
	 * 
	 * 保存商品第一步
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result saveGoodsFisrtStep(Parameter parameter) throws Exception;
	

	/**
	 * 
	 * 保存商品第二步
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */

	Result saveGoodsSecondStep(Parameter parameter) throws Exception;

	/**
	 * 获取商品列表
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result getGoodsVoList(Parameter parameter) throws Exception;
	
	/**
	 *通过id得到goods
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result getGoodsById(Parameter parameter) throws Exception;
	
	
	/**
	 * 上下架商品
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result updateGoods(Parameter parameter,String id,String grounding) throws Exception;
	
	/**
	 * 得到商品预警列表
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	Result getGoodsListByEarlyWarning(Parameter parameter)throws Exception;
	
	
	/**
	 * 通过商品id得到商品vo
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getGoodsVoById(Parameter parameter)throws Exception;
	
	/**
	 * 通过通过商品id查询所对应的goodsType的集合
	 * @param param
	 * @return
	 * @throws Exception
	 */

	Result getGoodsTypeSetByGoodsId(Parameter parameter)throws Exception;

	/**
	 * 
	 * 删除goods
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result delGoods(Parameter parameter)throws Exception;


	/**
	 * 
	 * 查找跨境商品
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result getGoodsListPageByCross(Parameter parameter)throws Exception;

	
}

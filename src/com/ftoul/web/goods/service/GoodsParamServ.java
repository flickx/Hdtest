package com.ftoul.web.goods.service;

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


@Service("WebGoodsParamImpl")
public interface GoodsParamServ{


/**
 * 
 * 对接订单接口，对应的减少库存
 * @param   param Parameter对象
 * @return  返回结果（前台用Result对象）
 */
	Result saveStockFromOrder(Parameter parameter)throws  Exception;
	
	/**
	 * 根据goodsId得到总库存
	 * @param param
	 * @return
	 * @throws Exception
	 */
	
	public int getSumStockBygoodsId(Parameter parameter) throws Exception;
	
	
}

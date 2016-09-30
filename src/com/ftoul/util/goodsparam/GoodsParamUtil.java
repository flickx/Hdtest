package com.ftoul.util.goodsparam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.goods.service.GoodsParamServ;

@Component
public class GoodsParamUtil {
	
	@Autowired
	GoodsParamServ goodsParamServ;
	
	
	/**
	 * 
	 * 对接订单接口，对应的减少库存 
	 * @param   id 商品参数id，amount购买数量
	 * @return  返回结果（前台用Result对象）
	 */
	
	public Result saveStockFromOrder(String id,String amount) throws Exception {
		Parameter parameter = new Parameter();
		parameter.setId(id);
		parameter.setKey(amount);
		return goodsParamServ.saveStockFromOrder(parameter);
	}
}

package com.ftoul.web.orders.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.vo.OrderPriceVo;


/**
 * 订单处理的业务接口
 * @author HuDong
 * 2016-07-18
 */
public interface OrdersServ {
	
	Result getOrdersListByUserId(Parameter param) throws Exception;

	Result cancelOrders(Parameter param) throws Exception;

	Result deleteOrders(Parameter param) throws Exception;

	Result getOrdersList(Parameter param) throws Exception;

	Result getOrdersByOrdersId(Parameter param) throws Exception;

	Result saveOrders(Parameter param) throws Exception;

	Result getOrderPayDetailByPayId(Parameter parameter) throws Exception;

	Result getOrderAllStaticSizeByUserId(Parameter parameter) throws Exception;

	Result getOrdersLogistics(Parameter parameter) throws Exception;

	Result payOrders(Parameter parameter) throws Exception;

	Result getOrdersPayable(Parameter parameter) throws Exception;

	Result orderPay(Parameter parameter) throws Exception;

	Result getGoods(Parameter parameter) throws Exception;
	
	//OrderPriceVo getOrderGoodsBenPrice(Parameter param) throws Exception;

	Result getOrdersById(Parameter parameter) throws Exception;

	Result confirmTakeGoods(Parameter parameter) throws Exception;
	
	Result getOrdersByOrdersNumber(Parameter param) throws Exception;
	
	//Result autoCancelOrders(Parameter param) throws Exception;

	Result getOrdersDetailById(Parameter parameter) throws Exception;

	Result getOrdersFreightByOrderNumber(Parameter parameter) throws Exception;

}

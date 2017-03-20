package com.ftoul.pc.orders.service;

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
	
	Result getOrderAllStaticSizeByUserId(Parameter param) throws Exception;

	Result saveOrders(Parameter param) throws Exception;

	Result getOrderPayDetailByPayId(Parameter parameter) throws Exception;

	Result getOrdersLogistics(Parameter parameter) throws Exception;

	Result payOrders(Parameter parameter) throws Exception;

	Result getOrdersPayable(Parameter parameter) throws Exception;

	Result orderPay(Parameter parameter) throws Exception;

	Result getGoods(Parameter parameter) throws Exception;
	
	Result getOrdersById(Parameter parameter) throws Exception;

	Result confirmTakeGoods(Parameter parameter) throws Exception;
	
	Result getOrdersByOrdersNumber(Parameter param) throws Exception;
	
	Result getOrdersDetailById(Parameter parameter) throws Exception;

	Result getOrdersFreightByOrderNumber(Parameter parameter) throws Exception;
	
	Result recoveryOrders(Parameter param) throws Exception;
	
	Result restoreOrders(Parameter param) throws Exception;

	Result getRecoveryOrdersPage(Parameter parameter) throws Exception;

	Result useCoupon(Parameter parameter) throws Exception;

	Result toPay(Parameter parameter) throws Exception;
}

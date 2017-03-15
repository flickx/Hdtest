package com.ftoul.manage.orders.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 订单处理的业务接口
 * @author HuDong
 * 2016-07-18
 */
public interface OrdersServ {
	
	Result deleteOrders(Parameter param) throws Exception;
	
	Result getOrdersList(Parameter param) throws Exception;

	Result getOrdersByOrdersId(Parameter param) throws Exception;

	Result getOrdersPayListPage(Parameter param) throws Exception;

	Result getOrderListPage(Parameter parameter) throws Exception;

	Result getOrderDetailVoById(Parameter param) throws Exception;

	Result saveOrderLogisticsCompany(Parameter parameter) throws Exception;

	Result getOrderOpLog(Parameter parameter) throws Exception;

	Result saveOrderStatic(Parameter parameter) throws Exception;

	Result saveBenefitPrice(Parameter parameter) throws Exception;

	Result getOrderPayDetailByPayId(Parameter parameter) throws Exception;

	Result getdeliveryListPage(Parameter parameter) throws Exception;

	Result getOrdersSet(Parameter parameter) throws Exception;

	Result saveOrdersSet(Parameter parameter) throws Exception;

	Result getOrdersById(Parameter parameter) throws Exception;

	Result getOrdersDetailByOrdersId(Parameter parameter) throws Exception;

	Result getSendGoodsListPage(Parameter parameter) throws Exception;

	Result modifyClearCustom(Parameter parameter) throws Exception;
	
	Result getOrdersPayExportList(Parameter param) throws Exception;
	
	Result getOrderListByUserId(Parameter param)throws Exception;

}

package com.ftoul.util.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.Parameter;
import com.ftoul.web.orders.service.OrdersServ;

@Component
public class OrdersUtil {
	
	@Autowired
	OrdersServ ordersServ;
	
	/**
	 * 自动取消订单
	 */
	
	public void autoCancelOrders(Parameter param) throws Exception {
		ordersServ.autoCancelOrders(param);
	}
}

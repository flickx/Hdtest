package com.ftoul.util.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.Parameter;
import com.ftoul.web.orders.service.OrdersServ;
import com.ftoul.web.vo.OrderPriceVo;

@Component
public class PriceUtil {
	
	@Autowired
	OrdersServ ordersServ;
	
	public OrderPriceVo getOrderGoodsBenPrice(Parameter param) throws Exception{
		return ordersServ.getOrderGoodsBenPrice(param);
	}
	

}

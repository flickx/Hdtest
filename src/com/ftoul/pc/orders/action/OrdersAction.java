package com.ftoul.pc.orders.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.orders.service.OrdersServ;

/**
 * 订单管理
 * @author HuDong
 *
 */
@Controller("PcOrdersAction")
@RequestMapping(value = "/pc/orders")
public class OrdersAction {

	@Autowired
	private OrdersServ ordersServ;
	
	/**
	 * 获取订单所有状态数量
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrderAllStaticSizeByUserId")  
	public @ResponseBody Result getOrderAllStaticSizeByUserId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrderAllStaticSizeByUserId(parameter);
	}
	
	/**
	 * 获取用户订单列表
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersListByUserId")  
	public @ResponseBody Result getOrdersListByUserId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersListByUserId(parameter);
	}
	

	/**
	 * 获取订单详情
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersByOrdersId")  
	public @ResponseBody Result getOrdersByOrdersId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersByOrdersId(parameter);
	}
	
	/**
	 * 取消订单
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "cancelOrders")  
	public @ResponseBody Result cancelOrders(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.cancelOrders(parameter);
	}
	
	/**
	 * 删除订单
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "deleteOrders")  
	public @ResponseBody Result deleteOrders(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.deleteOrders(parameter);
	}
	
	/**
	 * 获取订单物流信息
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersLogistics")  
	public @ResponseBody Result getOrdersLogistics(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersLogistics(parameter);
	}
	
	/**
	 * 确认收货
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "confirmTakeGoods")  
	public @ResponseBody Result confirmTakeGoods(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.confirmTakeGoods(parameter);
	}
	
	/**
	 * 回收订单
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "recoveryOrders")  
	public @ResponseBody Result recoveryOrders(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.recoveryOrders(parameter);
	}
	
	/**
	 * 回收站订单查询
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getRecoveryOrdersPage")  
	public @ResponseBody Result getRecoveryOrdersPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getRecoveryOrdersPage(parameter);
	}
	
	/**
	 * 还原订单
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "restoreOrders")  
	public @ResponseBody Result restoreOrders(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.restoreOrders(parameter);
	}
	
	/**
	 * 保存订单信息
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveOrders")  
	public @ResponseBody Result saveOrders(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.saveOrders(parameter);
	}
	
	/**
	 * 支付订单
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "payOrders")  
	public @ResponseBody Result payOrders(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.payOrders(parameter);
	}
	
	
	
	/**
	 * 通过各状态的订单列表
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersList")  
	public @ResponseBody Result getOrdersList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersList(parameter);
	}
	
	
	/**
	 * 获取订单总价
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersPayable")  
	public @ResponseBody Result getOrdersPayable(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersPayable(parameter);
	}
	
	/**
	 * 订单支付
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "orderPay")  
	public @ResponseBody Result orderPay(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.orderPay(parameter);
	}
	
	/**
	 * 获取订单商品展示信息
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoods")  
	public @ResponseBody Result getGoods(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getGoods(parameter);
	}
	
	/**
	 * 通过主键获取订单信息
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersById")  
	public @ResponseBody Result getOrdersById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersById(parameter);
	}
	
	/**
	 * 获取订单
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersByOrdersNumber")  
	public @ResponseBody Result getOrdersByOrdersNumber(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersByOrdersNumber(parameter);
	}
	
	/**
	 * 根据订单详情主键获取订单详情
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersDetailById")  
	public @ResponseBody Result getOrdersDetailById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersDetailById(parameter);
	}
	
	/**
	 * 根据订单号查询该订单所在默认地址的运费
	 * @param param 用户ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrdersFreightByOrderNumber")  
	public @ResponseBody Result getOrdersFreightByOrderNumber(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return ordersServ.getOrdersFreightByOrderNumber(parameter);
	}
}

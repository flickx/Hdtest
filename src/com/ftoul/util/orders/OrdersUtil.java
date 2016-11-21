package com.ftoul.util.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.DateStr;
import com.ftoul.common.Parameter;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.orders.service.OrdersServ;

@Component
public class OrdersUtil {
	
	@Autowired
	OrdersServ ordersServ;
	
	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * 自动取消订单
	 */
	
	public void autoCancelOrders(Parameter param) throws Exception {
		ordersServ.autoCancelOrders(param);
	}
	
	/**
	 * 生成订单号
	 * @return
	 */
	public String getOrderNumber(){
		Object obj = hibernateUtil.hqlFirst("select max(orderNumber) from Orders");
		String max = "";
		String seq = "";
		String current = new DateStr("yyyyMMdd").toString();
		if(obj!=null){
			max = obj.toString();
			String time = max.substring(0, 8);
			seq = max.substring(8);
			if(current.equals(time)){
				seq = String.valueOf(Integer.parseInt(seq)+1);
				int length = seq.length();
				int l = 6-length;
				if(l!=0){
					for (int i = 0; i < l; i++) {
						seq = "0"+seq;
					}
				}
			}else{
				seq ="000001";
			}
		}else{
			seq ="000001";
		}
		max = current+seq;
		return max;
	}
	
	/**
	 * 生成售后服务号
	 * @return
	 */
	public String getAfterServiceCode(){
		Object obj = hibernateUtil.hqlFirst("select max(serviceCode) from AfterSchedule");
		String max = "";
		String seq = "";
		String current = new DateStr("yyyyMMdd").toString();
		if(obj!=null){
			max = obj.toString();
			String time = max.substring(0, 8);
			seq = max.substring(8);
			if(current.equals(time)){
				seq = String.valueOf(Integer.parseInt(seq)+1);
				int length = seq.length();
				int l = 6-length;
				if(l!=0){
					for (int i = 0; i < l; i++) {
						seq = "0"+seq;
					}
				}
			}else{
				seq ="000001";
			}
		}else{
			seq ="000001";
		}
		max = current+seq;
		return max;
	}
}
	/**
	 * 获取订单状态
	 * @param orderState
	 * @return
	 */
	public String getState(String orderState){
		//1：待付款，2：已付款 ，3：待发货，4：已发货，5：待收货，6：已完成 ，7：未评价，8：已取消
		String state = null;
		if("1".equals(orderState)){
			state = "待付款";
		}else if("2".equals(orderState)){
			state = "已付款";
		}else if("3".equals(orderState)){
			state = "待发货";
		}else if("4".equals(orderState)){
			state = "已发货";
		}else if("5".equals(orderState)){
			state = "待收货";
		}else if("6".equals(orderState)){
			state = "已完成";
		}else if("7".equals(orderState)){
			state = "未评价";
		}else if("8".equals(orderState)){
			state = "已取消";
		}
		return state;
	}
}

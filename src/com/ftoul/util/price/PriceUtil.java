package com.ftoul.util.price;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.Parameter;
import com.ftoul.po.FullCutRule;
import com.ftoul.po.GoodsEvent;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.orders.service.OrdersServ;
import com.ftoul.web.vo.OrderPriceVo;

@Component
public class PriceUtil {
	
	@Autowired
	OrdersServ ordersServ;
	@Autowired
	private HibernateUtil hibernateUtil;
	
	public OrderPriceVo getOrderGoodsBenPrice(Parameter param) throws Exception{
		return ordersServ.getOrderGoodsBenPrice(param);
	}
	
	/**
	 * 每满减活动价格计算
	 * @param group
	 */
	public List<Double> MmjGoodsEventGroupCount(Map<String, Double> group){
		double orderPrice = 0.0;
		double benPrice = 0.0;
		double target;
		double discountAmount;
		double per;
		for (String key : group.keySet()) {
			//满减对象
			GoodsEvent goodsEvent = (GoodsEvent) hibernateUtil.find(GoodsEvent.class, key+"");
			target = goodsEvent.getTarget().doubleValue();
			discountAmount = goodsEvent.getDiscountAmount().doubleValue();
			//订单总价
			double value = group.get(key);
			per = Math.floor(value/target);
			discountAmount = discountAmount*per;
			value = value - discountAmount;
			orderPrice += value;
			benPrice += discountAmount;
		}
		List<Double> result = new ArrayList<Double>();
		result.add(orderPrice);
		result.add(benPrice);
		System.out.println("每满减后的订单价格："+orderPrice);
		System.out.println("每满减优惠："+benPrice);
		return result;
		
	}
	
	
	private List sort(List<FullCutRule> list){
		Collections.sort(list, new Comparator<FullCutRule>() {
            public int compare(FullCutRule arg0, FullCutRule arg1) {
                return arg0.getTarget().compareTo(arg1.getTarget());
            }
        });
		return list;
	}
	

	public static void main(String[] args) {
		double a = 130;
		double b = 100;
		double c = 10;
		double d = a/b;
		System.out.println(Math.floor(d));
		System.out.println((230/100));
		System.out.println((Math.floor(d)*c));
		System.out.println(a-(Math.floor(d)*c));
	}
}

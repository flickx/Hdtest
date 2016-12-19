package com.ftoul.util.price;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.Parameter;
import com.ftoul.po.FullCutRule;
import com.ftoul.po.GoodsEvent;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.orders.service.OrdersServ;
import com.ftoul.web.vo.MjGoodsEventVo;
import com.ftoul.web.vo.OrderPriceVo;

@Component
public class PriceUtil {
	
	@Autowired
	OrdersServ ordersServ;
	@Autowired
	private HibernateUtil hibernateUtil;
	
//	public OrderPriceVo getOrderGoodsBenPrice(Parameter param) throws Exception{
//		return ordersServ.getOrderGoodsBenPrice(param);
//	}
	
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
			System.out.println(goodsEvent.getEventName()+"活动【前】的订单价："+value);
			per = Math.floor(value/target);
			discountAmount = discountAmount*per;
			value = value - discountAmount;
			System.out.println(goodsEvent.getEventName()+"活动【后】的订单价："+value);
			orderPrice += value;
			benPrice += discountAmount;
		}
		List<Double> result = new ArrayList<Double>();
		result.add(orderPrice);
		result.add(benPrice);
		System.out.println("最终每满减后的订单价格："+orderPrice);
		System.out.println("最终每满减优惠："+benPrice);
		return result;
		
	}
	
	/**
	 * 阶梯满减活动价格计算
	 * @param group
	 */
	public List<Double> MjGoodsEventGroupCount(Map<String, Double> group){
		FullCutRule rule = new FullCutRule();
		double orderPrice = 0.0;
		double benPrice = 0.0;
		for (String key : group.keySet()) {
			//满减对象
			List<Object> list = hibernateUtil.hql("from FullCutRule where state='1' and goodsEvent.id='"+key+"'");
			List<FullCutRule> ruleList = new ArrayList<FullCutRule>();
			for (int i = 0; i < list.size(); i++) {
				rule = (FullCutRule) list.get(i);
				ruleList.add(rule);
			}
			ruleList = sort(ruleList);
			//订单总价
			double value = group.get(key);
			double temp = value;
			for (int i = ruleList.size()-1; i >= 0; i--) {
				rule = (FullCutRule) ruleList.get(i);
				if(value>=rule.getTarget()){
					temp = temp - rule.getDiscountAmount();
					benPrice += rule.getDiscountAmount();
					break;
				}
			}
			orderPrice += temp;
		
		}
		List<Double> result = new ArrayList<Double>();
		result.add(orderPrice);
		result.add(benPrice);
		System.out.println("满减后的订单价格："+orderPrice);
		System.out.println("满减优惠："+benPrice);
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
	
	/**
	 * 将参加满减活动的商品进行分组
	 * @param mjGoodsEventList
	 * @return
	 */
	public Map<String, Double> getMjGoodsEventGroup(List<MjGoodsEventVo> mjGoodsEventList){
		Map<String, Double> group = new HashMap<String, Double>();
		String type = null;
		Double moneyTotal = 0.0;
		for (int i = 0; i < mjGoodsEventList.size(); i++) {
			MjGoodsEventVo vo = mjGoodsEventList.get(i);
			if(vo!=null){
				type = vo.getGoodsEvent().getId();
				moneyTotal = group.get(type);
				if(moneyTotal == null){
					moneyTotal = 0.0;
				}
				group.put(type, moneyTotal + vo.getOrderPrice());
			}
		}
		
		return group;
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

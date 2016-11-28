package com.ftoul.util.orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.DateStr;
import com.ftoul.common.Parameter;
import com.ftoul.po.Orders;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.orders.service.OrdersServ;
import com.ftoul.web.vo.ManyVsOneVo;
import com.ftoul.web.vo.MjGoodsEventVo;
import com.ftoul.web.vo.ShopGoodsParamVo;

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
	
	/**
	 * 获取售后申请状态
	 * @param orderState
	 * @return
	 */
	public String getAfterState(String afterState){
		String state = null;
		if("1".equals(afterState)){
			state = "申请售后";
		}else if("2".equals(afterState)){
			state = "同意换货";
		}else if("3".equals(afterState)){
			state = "拒绝换货";
		}else if("4".equals(afterState)){
			state = "同意退款";
		}else if("5".equals(afterState)){
			state = "拒绝退款";
		}else if("6".equals(afterState)){
			state = "同意退款/不退货";
		}else if("7".equals(afterState)){
			state = "拒绝退款/不退货";
		}else if("8".equals(afterState)){
			state = "买家已发货";
		}else if("9".equals(afterState)){
			state = "商家已收货";
		}else if("10".equals(afterState)){
			state = "商家已发货";
		}else if("11".equals(afterState)){
			state = "买家已收货";
		}else if("12".equals(afterState)){
			state = "商家已退款并完成售后服务";
		}
		return state;
	}
	
	/**
	 * 获取售后申请类型
	 * @param orderState
	 * @return
	 */
	public String getAfterType(String afterState){
		String state = null;
		if("1".equals(afterState)){
			state = "换货";
		}else if("2".equals(afterState)){
			state = "退款";
		}else if("3".equals(afterState)){
			state = "退款/不退货";
		}
		return state;
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
	
	public ManyVsOneVo transformObject(Orders order,List<Object> ordersDetailList){
		ManyVsOneVo vo = new ManyVsOneVo();
		vo.setObj(order);
		vo.setList(ordersDetailList);
		return vo;
	}
	
	/**
	 * 将购买的商品按店铺分组
	 * @param param
	 * @return
	 */
	public Map<String, List<ShopGoodsParamVo>> getShopAndGoodsParam(String param){
		Map<String, List<ShopGoodsParamVo>> group = new HashMap<String, List<ShopGoodsParamVo>>();
		List<ShopGoodsParamVo> goodsParamVo = null;
		String[] strList = param.split(":");
		List<ShopGoodsParamVo> voList = new ArrayList<ShopGoodsParamVo>();
		for (int i = 0; i < strList.length; i++) {
			String[] str = strList[i].split(",");
			ShopGoodsParamVo vo = new ShopGoodsParamVo();
			vo.setGoodsParamId(str[0]);
			vo.setNum(str[1]);
			vo.setPrice(str[2]);
			vo.setShopId(str[4]);
			voList.add(vo);
		}
		
		for (int j = 0; j < voList.size(); j++) {
			ShopGoodsParamVo vo = voList.get(j);
			String shopId = vo.getShopId();
			if(shopId!=null){
				goodsParamVo = group.get(shopId);
				if(goodsParamVo == null){
					List<ShopGoodsParamVo> newVoList = new ArrayList<ShopGoodsParamVo>();
					newVoList.add(vo);
					group.put(shopId, newVoList);
				}else{
					goodsParamVo.add(vo);
					group.put(shopId, goodsParamVo);
				}
			}
		}
		
		return group;
	}
	
	public static void main(String[] args) {
		System.out.println(new DateStr("yyMMddHHmmss").toString());
	}
	
}

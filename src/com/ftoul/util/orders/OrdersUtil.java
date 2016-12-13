package com.ftoul.util.orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.Parameter;
import com.ftoul.manage.cart.service.CartServ;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.Orders;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.orders.service.OrdersServ;
import com.ftoul.web.vo.ManyVsOneVo;
import com.ftoul.web.vo.OrderVo;
import com.ftoul.web.vo.ShopGoodsParamVo;

@Component
public class OrdersUtil {
	
	@Autowired
	OrdersServ ordersServ;
	@Autowired
	CartServ cartServ;
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
		String state = null;
		if("0".equals(orderState)){
			state = "待提单";
		}else if("1".equals(orderState)){
			state = "待付款";
		}else if("2".equals(orderState)){
			state = "已付款";
		}else if("3".equals(orderState)){
			state = "待发货";
		}else if("4".equals(orderState)){
			state = "已发货";
		}else if("5".equals(orderState)){
			state = "已收货";
		}else if("6".equals(orderState)){
			state = "已完成";
		}else if("7".equals(orderState)){
			state = "已删除";
		}else if("8".equals(orderState)){
			state = "已取消";
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
	
	/**
	 * 删除购物车的内容
	 * @param param
	 * @throws Exception
	 */
	public void delShopCart(Parameter param) throws Exception{
		OrderVo vo = (OrderVo) Common.jsonToBean(param.getObj().toString(), OrderVo.class);
		String[] strList = vo.getGoodsParameter().split(":");
		for (int i = 0; i < strList.length; i++) {
			String[] str = strList[i].split(",");
			param.setId(str[3]);
			cartServ.delShopCart(param);
		}
	}
	
	/**
	 * 根据goodsId得到总库存
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int getSumStockBygoodsId(Parameter param) throws Exception {
		String hql = "from GoodsParam where state ='1' and goods.id ='"+ param.getId()+"'";
		List<Object>goodsParamList = this.hibernateUtil.hql(hql);
		int sumStock =0;
		if(goodsParamList==null||goodsParamList.size()<=0){
			return 0;
		}
		for(Object obj:goodsParamList ){
			int stock =0;
			GoodsParam goodsParam = (GoodsParam) obj;
			if(goodsParam.getStock()==null||goodsParam.getStock()==""){
				stock =0;
			}else{
				stock = Integer.parseInt(goodsParam.getStock());
			}
			sumStock+=stock;
		}
		return sumStock;
	}
	
	public static void main(String[] args) {
		System.out.println(new DateStr("yyMMddHHmmss").toString());
	}
	
}

package com.ftoul.util.orders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.Parameter;
import com.ftoul.manage.cart.service.CartServ;
import com.ftoul.po.FullCutRule;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.Orders;
import com.ftoul.po.OrdersDetail;
import com.ftoul.po.OrdersSet;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.webservice.WebserviceUtil;
import com.ftoul.web.orders.service.OrdersServ;
import com.ftoul.web.vo.ManyVsOneVo;
import com.ftoul.web.vo.OrderVo;
import com.ftoul.web.vo.ShopGoodsParamVo;
import com.ftoul.web.webservice.UserService;

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
		//ordersServ.autoCancelOrders(param);
		String now = new DateStr().toString();
		List<Object> list = hibernateUtil.hql("from Orders where state='1' and orderStatic ='1'");
		for (int i = 0; i < list.size(); i++) {
			Orders order = (Orders) list.get(i);
			long min = new DateStr().compare(now,order.getOrderTime());
			List<Object> setList = hibernateUtil.hql("from OrdersSet where state='1'");
			if(setList!=null&&setList.size()>0){
				OrdersSet set = (OrdersSet) setList.get(0);
				String time = set.getAutoCancleTime();
				if(min>=(Double.parseDouble(time)*60)){
					order.setOrderStatic("8");
					order.setModifyTime(now);
					hibernateUtil.update(order);
					updateGoodsParam(order.getId(),"cancel");
					if(order.getBeeCoins()!=null){
						updateCoinInfo(order.getUser().getUsername(),-Integer.parseInt(order.getBeeCoins()));
					}
				}
			}
			
		}
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
	 * 生成订单号 年月日时分秒+6位随机数，总共18位
	 * @return
	 */
	public String getOrderNumber(){
		String current = new DateStr("yyMMddHHmmss").toString();
		String code = "";
        for (int i = 0; i < 6; i++) {
            code = code + (int)(Math.random() * 9);
        }
		return current+code;
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
			if(str.length==4){
				vo.setShopId("1");
			}else{
				vo.setShopId(str[4]);
			}
			
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
	
	/**
	 * 删除/取消订单，减少库存、增加销售量
	 * @param param
	 */
	public void updateGoodsParam(String orderId,String flag) throws  Exception{
		List<Object> list = hibernateUtil.hql("from OrdersDetail where orders.id='"+orderId+"'");
		for (int i = 0; i < list.size(); i++) {
			OrdersDetail ordersDetail = (OrdersDetail) list.get(i);
			int num = Integer.parseInt(ordersDetail.getNumber());
			GoodsParam goodsParam = ordersDetail.getGoodsParam();
			Goods goods = goodsParam.getGoods();
			if("add".equals(flag)){
				goodsParam.setStock(String.valueOf(Integer.parseInt(goodsParam.getStock())-num));
				goodsParam.setSaleNumber(goodsParam.getSaleNumber()+num);
				goods.setSaleSum(goods.getSaleSum()+num);
			}else if("cancel".equals(flag)){
				goodsParam.setStock(String.valueOf(Integer.parseInt(goodsParam.getStock())+num));
				goodsParam.setSaleNumber(goodsParam.getSaleNumber()-num);
				goods.setSaleSum(goods.getSaleSum()-num);
			}
			hibernateUtil.update(goodsParam);
			//判断商品是否还有库存
			Parameter param = new Parameter();
			param.setId(goods.getId());
			int ret = getSumStockBygoodsId(param);
			if(ret==0){//无库存
				goods.setHasstock("0");
			}else{
				goods.setHasstock("1");
			}
			hibernateUtil.update(goods);
		}
	}
	
	/**
	 * 修改用户蜂币
	 * @param param
	 * @param vo
	 * @throws Exception
	 */
	public void updateCoinInfo(String param,int num) throws Exception{
		UserService userService = WebserviceUtil.getService();
		userService.modifyIntegral(param, num);
	}
	
	
	public static void main(String[] args) {
		System.out.println(new DateStr("yyMMddHHmmss").toString());
	}
	
}

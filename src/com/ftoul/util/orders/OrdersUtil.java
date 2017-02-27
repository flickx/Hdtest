package com.ftoul.util.orders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.api.KdniaoTrackQueryAPI;
import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.DateUtil;
import com.ftoul.common.OrdersConstant;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.cart.service.CartServ;
import com.ftoul.manage.coin.service.CoinSetServ;
import com.ftoul.pc.orders.vo.PcDetailVo;
import com.ftoul.pc.orders.vo.PcOrderVo;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsEvent;
import com.ftoul.po.GoodsEventJoin;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.Orders;
import com.ftoul.po.OrdersDetail;
import com.ftoul.po.OrdersSet;
import com.ftoul.po.SystemSet;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.webservice.WebserviceUtil;
import com.ftoul.web.orders.service.OrdersServ;
import com.ftoul.web.vo.ManyVsOneVo;
import com.ftoul.web.vo.OrderPriceVo;
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
	CoinSetServ coinSetServ;
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
	 * 获取支付类型
	 * @param param
	 * @return
	 */
	public String getPayType(String param){
		String state = null;
		if(OrdersConstant.CHINAPAY.equals(param)){
			state = "银联支付";
		}else if(OrdersConstant.ALIPAY.equals(param)){
			state = "支付宝支付";
		}else if(OrdersConstant.WXPAY.equals(param)){
			state = "微信支付";
		}else if(OrdersConstant.ALIQBPAY.equals(param)){
			state = "支付宝钱包支付";
		}
		return state;
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
		}else if("9".equals(orderState)){
			state = "已评论";
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
	 * 订单列表转换组装
	 * @param order
	 * @param ordersDetailList
	 * @return
	 */
	public PcOrderVo transformOrder(Orders order,List<Object> ordersDetailList){
		PcOrderVo vo = new PcOrderVo();
		vo.setConsigeeName(order.getConsignee());
		vo.setId(order.getId());
		vo.setOrderNumber(order.getOrderNumber());
		vo.setOrderPrice(order.getOrderPrice());
		vo.setOrderStatic(order.getOrderStatic());
		vo.setOrderTime(order.getOrderTime());
		vo.setPayType(getPayType(order.getPayType()));
		vo.setShopName(order.getShopId().getStoreName());
		List<Object> detailList = new ArrayList<Object>();
		for (Object object : ordersDetailList) {
			OrdersDetail orderDetail = (OrdersDetail) object;
			PcDetailVo detailVo = new PcDetailVo();
			detailVo.setNum(orderDetail.getNumber());
			detailVo.setParamName(orderDetail.getParamName());
			detailVo.setPicSrc(orderDetail.getPicSrc());
			detailVo.setPrice(orderDetail.getPrice());
			detailVo.setTitle(orderDetail.getGoodsTitle());
			detailVo.setIsComment(orderDetail.getIsComment());
			detailVo.setDetailId(orderDetail.getId());
			if(orderDetail.getIsAfter()==null){
				detailVo.setIsAfter("0");
			}else{
				detailVo.setIsAfter(orderDetail.getIsAfter());
			}
			detailList.add(detailVo);
		}
		vo.setDetailVoList(detailList);
		return vo;
	}
	
	/**
	 * 订单转换组装成订单详情
	 * @param order
	 * @param ordersDetailList
	 * @return
	 * @throws Exception 
	 */
	public PcOrderVo transformOrderDetail(Orders order,List<Object> oneVsManyList) throws Exception{
		PcOrderVo vo = new PcOrderVo();
		vo.setConsigeeName(order.getConsignee());
		vo.setTel(order.getConsigneeTel());
		vo.setAddress(order.getAddress());
		vo.setId(order.getId());
		vo.setOrderNumber(order.getOrderNumber());
		vo.setOrderPrice(order.getOrderPrice());
		if(order.getGoodsTotalPrice()!=null){
			vo.setGoodsTotalPrice(order.getGoodsTotalPrice().toString());
		}
		vo.setBenPrice(order.getBenefitPrice());
		vo.setOrderStatic(getState(order.getOrderStatic()));
		vo.setOrderTime(order.getOrderTime());
		vo.setPayType(getPayType(order.getPayType()));
		vo.setPayTime(order.getPayTime());
		vo.setOdd(order.getOdd());
		if(order.getLogisticsCompany()!=null){
			vo.setLogCompany(order.getLogisticsCompany().getName());
		}
		List<Object> detailList = new ArrayList<Object>();
		for (Object object : oneVsManyList) {
			ManyVsOneVo vsVo = (ManyVsOneVo) object;
			List<Object> orderDetailList = vsVo.getList();
			for (Object detail : orderDetailList) {
				OrdersDetail orderDetail = (OrdersDetail) detail;
				PcDetailVo detailVo = new PcDetailVo();
				detailVo.setNum(orderDetail.getNumber());
				detailVo.setParamName(orderDetail.getParamName());
				detailVo.setPicSrc(orderDetail.getPicSrc());
				detailVo.setPrice(orderDetail.getPrice());
				detailVo.setTitle(orderDetail.getGoodsTitle());
				if(orderDetail.getTotalPrice()!=null){
					detailVo.setTotalPrice(orderDetail.getTotalPrice().toString());
				}
				detailList.add(detailVo);
			}
		}
		vo.setDetailVoList(detailList);
		KdniaoTrackQueryAPI kdniaoTrackQueryAPI = new KdniaoTrackQueryAPI();
		if(order.getLogisticsCompany()!=null){
			String res = kdniaoTrackQueryAPI.getOrderTracesByJson(order.getLogisticsCompany().getCode(), order.getOdd());
			if(res!=null){
				vo.setLogistInfo(res);
			}else{
				vo.setLogistInfo("暂无物流信息");
			}
		}
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
	 * 将购买的商品按店铺分组
	 * @param param
	 * @return
	 */
	public Map<String, List<ShopGoodsParamVo>> getNewShopAndGoodsParam(String param){
		Map<String, List<ShopGoodsParamVo>> group = new HashMap<String, List<ShopGoodsParamVo>>();
		List<ShopGoodsParamVo> goodsParamVo = null;
		String[] strList = param.split(":");
		List<ShopGoodsParamVo> voList = new ArrayList<ShopGoodsParamVo>();
		for (int i = 0; i < strList.length; i++) {
			String[] str = strList[i].split(",");
			ShopGoodsParamVo vo = new ShopGoodsParamVo();
			vo.setGoodsParamId(str[0]);
			vo.setNum(str[1]);
			//vo.setPrice(str[2]);
			vo.setShopId(str[2]);
//			if(str.length==4){
//				vo.setShopId("1");
//			}else{
//				vo.setShopId(str[4]);
//			}
			
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
	
	/**
	 * 获取用户蜂币
	 * @param param
	 * @param vo
	 * @throws Exception
	 */
	public void getCoinInfo(Parameter param,OrderPriceVo vo) throws Exception{
		UserService userService = WebserviceUtil.getService();
		int coinNumber = userService.getIntegral(param.getUserToken().getUser().getUsername());
		Result result = coinSetServ.getCoinSet(param);
		SystemSet set = (SystemSet) result.getObj();
		String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
		if(set!=null){
			vo.setCoinNumber(coinNumber);
			vo.setTotalCoinNumber(coinNumber);
			double price = Double.parseDouble(set.getValue());
			//如果商品享受蜂币翻倍则蜂币按翻倍规则计算抵扣蜂币
			String params = param.getKey();
			String[] goodsParams = {};
			if (params == null) {
				OrderVo v = (OrderVo) Common.jsonToBean(param.getObj().toString(), OrderVo.class);
				goodsParams = v.getGoodsParameter().split(":");
			}else{
				goodsParams = param.getKey().split(":");
			}
			List<Object> goodsEventJoinList = new ArrayList<Object>();
			for (int i = 0; i < goodsParams.length; i++) {
				String goodsParam = goodsParams[i];
				String[] goods = goodsParam.split(",");
				GoodsParam goodsP = (GoodsParam) hibernateUtil.find(GoodsParam.class, goods[0]+"");
				Goods good = goodsP.getGoods();
				goodsEventJoinList = hibernateUtil.hql("from GoodsEventJoin where goods.id='"+good.getId()+"' and state='1'");
			}
			for (int j = 0; j < goodsEventJoinList.size(); j++) {
				GoodsEventJoin eventJoin = (GoodsEventJoin) goodsEventJoinList.get(j);
				GoodsEvent goodsEvent = eventJoin.getGoodsEvent();
				if("1".equals(goodsEvent.getHomeChannel())){
					String hql = "select o from OrdersDetail od, Orders o where od.orders.id = o.id  and od.eventType in (select typeName from GoodsEvent where homeChannel='1' and state='1') and od.state='1' and o.orderStatic !='8' and o.state='1' and o.user.id= '"+param.getUserToken().getUser().getId()+"'";

					List<Object> orderList = hibernateUtil.hql(hql);				
					if (orderList.size() == 0) {
//					vo.setMsg("您已有订单享受蜂币翻倍抵扣，不可再次享受翻倍抵扣，请知悉！");
					GoodsEvent ge =(GoodsEvent) this.hibernateUtil.hqlFirst("from GoodsEvent where typeName='蜂币翻倍' and eventBegen<='"+current+"' and eventEnd>='"+current+"' and state='1'");
//					base = base*(ge.getExchangeRate().doubleValue());
					//原始倍率*活动翻倍率
					if (ge!=null) {
						price = ge.getExchangeRate().doubleValue()*price;
						BigDecimal b = new BigDecimal(price);  
						price = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
					}
					}
				}
			}
			vo.setCoinPrice(coinNumber*price);
		}
	}
	
	
	/**
	 * 获取可抵扣的蜂币
	 * @throws Exception
	 */
	public void getDeductionCoinInfo(Parameter param,OrderPriceVo vo, Orders orders) throws Exception{
		//double orderPrice = Double.parseDouble(orders.getOrderPrice());
		double orderPrice = orders.getGoodsTotalPrice().doubleValue();//改成只能抵扣商品总价(不包含运费)
		int coinNumber = vo.getCoinNumber();//账户总蜂币
		double newCoinNumber;
		double coinPrice = vo.getCoinPrice();
		double base = coinPrice/coinNumber;
		
		double newOrderPrice = orderPrice - coinPrice;
		if(newOrderPrice<0){
			BigDecimal orderPriceB = new BigDecimal(Double.toString(orderPrice));
			BigDecimal baseB = new BigDecimal(Double.toString(base));
			double value = orderPriceB.divide(baseB,2,BigDecimal.ROUND_UP).doubleValue();
			newCoinNumber = Math.floor(value);
			coinPrice = newCoinNumber*base;
			newOrderPrice = orderPrice - coinPrice;
			vo.setCoinNumber((int)newCoinNumber);
			vo.setCoinPrice(coinPrice);
		}
	}
	
	/**
	 * 蜂币翻倍活动抵扣价格计算 
	 * modify by 李丁
	 * @param param
	 * @param vo
	 */
	public void getDoubleCoinData(Parameter param,OrderPriceVo vo){
		String id = param.getKey();
		String[] goodsParams = id.split(":");
		String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
		String hql = "from Orders where state='1' and orderTime>='' and orderTime<=''";
		for (int i = 0; i < goodsParams.length; i++) {
			String goodsParam = goodsParams[i];
			String[] goods = goodsParam.split(",");
			GoodsParam goodsP = (GoodsParam) hibernateUtil.find(GoodsParam.class, goods[0]+"");
			Goods good = goodsP.getGoods();
			//查询此商品是否参加了倍蜂币活动
			List<Object> goodsEventList = 
					hibernateUtil.hql("from GoodsEvent where id in (select goodsEvent.id from GoodsEventJoin where goods.id='"+good.getId()+"' and state='1') and state='1' and homeChannel = '1' and eventBegen<='"+current+"' and eventEnd>='"+current+"'");
			for (int j = 0; j < goodsEventList.size(); j++) {
				GoodsEvent event = (GoodsEvent) goodsEventList.get(j);
				if("1".equals(event.getFirstOrder())){
					hql = "select od from OrdersDetail od, Orders o where od.orders.id = o.id and o.orderStatic not in('0','8') and od.goodsParam.goods.id = '"+good.getId()+"' and od.eventType='"+event.getTypeName()+"' and od.eventBegen='"+event.getEventBegen()+"' and od.eventEnd = '"+event.getEventEnd()+"' and o.user.username = '"+param.getUserToken().getUser().getUsername()+"'";
					List<Object> ordersDetailList = hibernateUtil.hql(hql);
					if(ordersDetailList.size()>0){
						vo.setMsg("你已经购买过【"+good.getTitle()+"】了，此商品参加的【"+event.getEventName()+"】活动一人只能购买一件");
					}
					
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(new DateStr("yyMMddHHmmss").toString());
	}
	
}

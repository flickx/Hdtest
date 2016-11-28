package com.ftoul.web.orders.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Service;

import com.ftoul.api.KdniaoTrackQueryAPI;
import com.ftoul.api.aliPay.util.AliPayUtil;
import com.ftoul.api.chinaPay.util.ChinaPayUtil;
import com.ftoul.api.weiXinPay.util.WeiXinPayUtil;
import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.DateUtil;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.OrdersConstant;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.cart.service.CartServ;
import com.ftoul.manage.coin.service.CoinSetServ;
import com.ftoul.po.AfterOpLog;
import com.ftoul.po.AfterSchedule;
import com.ftoul.po.BusinessStore;
import com.ftoul.po.FullCutRule;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsEvent;
import com.ftoul.po.GoodsEventJoin;
import com.ftoul.po.GoodsEventLog;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.Orders;
import com.ftoul.po.OrdersDetail;
import com.ftoul.po.OrdersPay;
import com.ftoul.po.OrdersSet;
import com.ftoul.po.SystemSet;
import com.ftoul.po.User;
import com.ftoul.po.UserAddress;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.orders.OrdersUtil;
import com.ftoul.util.webservice.WebserviceUtil;
import com.ftoul.web.orders.service.OrdersServ;
import com.ftoul.web.vo.AfterScheduleVo;
import com.ftoul.web.vo.GoodsVo;
import com.ftoul.web.vo.ManyVsOneVo;
import com.ftoul.web.vo.MjGoodsEventVo;
import com.ftoul.web.vo.OrderPriceVo;
import com.ftoul.web.vo.OrderStaticCountVo;
import com.ftoul.web.vo.OrderVo;
import com.ftoul.web.vo.OrdersLogisticsVo;
import com.ftoul.web.vo.ShopGoodsParamVo;
import com.ftoul.web.vo.ShopGoodsVo;
import com.ftoul.web.webservice.UserService;

@Service("OrdersWebServImpl")
public class OrdersServImpl implements OrdersServ {

	@Autowired
	private HibernateUtil hibernateUtil;
//	@Autowired
//	ChinaPayTestUtil chinaPayTestUtil;
	@Autowired
	ChinaPayUtil chinaPayUtil;
	@Autowired
	AliPayUtil aliPayUtil;
	@Autowired
	WeiXinPayUtil weiXinPayUtil;
	@Autowired
	CartServ cartServ;
	@Autowired
	CoinSetServ coinSetServ;
	@Autowired  
	private  HttpServletRequest req;
	@Autowired  
	OrdersUtil ordersUtil;
	/**
	 * 根据用户ID获取订单列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrdersListByUserId(Parameter param) throws Exception {
		ordersUtil.autoCancelOrders(param);
		String key = param.getKey();
		List<Object> ordersList = new ArrayList<Object>();
		Page page = new Page();
		if(OrdersConstant.NOT_PAY.equals(key)){
			page =  hibernateUtil.hqlPage("from Orders where orderStatic = '1' and user.id='"+param.getUserToken().getUser().getId()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else if(OrdersConstant.NOT_DELIVER.equals(key)){
			page =  hibernateUtil.hqlPage("from Orders where orderStatic in('2', '3') and user.id='"+param.getUserToken().getUser().getId()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else if(OrdersConstant.NOT_TASK_DELIVER.equals(key)){
			page =  hibernateUtil.hqlPage("from Orders where orderStatic in ('4','5') and user.id='"+param.getUserToken().getUser().getId()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else{
			page =  hibernateUtil.hqlPage("from Orders where orderStatic!='0' and state='1' and user.id='"+param.getUserToken().getUser().getId()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		}
		ordersList = page.getObjList();
		List<Object> ordersDetailList = new ArrayList<Object>();
		ManyVsOneVo vo;
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < ordersList.size(); i++) {
			Orders order = (Orders) ordersList.get(i);
			ordersDetailList = hibernateUtil.hql("from OrdersDetail where orders.id='"+order.getId()+"'");
			vo = ordersUtil.transformObject(order,ordersDetailList);
			list.add(vo);
		}
		page.setObjList(null);
		page.setObjList(list);
		return ObjectToResult.getResult(page);
	}
	
	/**
	 * 删除订单
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result deleteOrders(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update Orders set state = '0' , orderStatic = '7' where id in ("+StrUtil.getIds(param.getId())+")");
		updateGoodsParam(param.getId().toString(),"delete");
		return ObjectToResult.getResult(num);
	}
	
	/**
	 * 取消订单
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result cancelOrders(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update Orders set orderStatic = '8' where id in ('"+param.getId()+"')");
		updateGoodsParam(param.getId().toString(),"cancel");
		Orders order = (Orders) hibernateUtil.find(Orders.class, param.getId()+"");
		if(order.getBeeCoins()!=null){
			updateCoinInfo(param.getUserToken().getUser().getUsername(),-Integer.parseInt(order.getBeeCoins()));
		}
		
		return ObjectToResult.getResult(num);
	}
	
	/**
	 * 删除/取消订单，减少库存、增加销售量
	 * @param param
	 */
	public void updateGoodsParam(String orderId,String flag){
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
			hibernateUtil.update(goods);
		}
	}
	
	/**
	 * 获取订单列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrdersList(Parameter param) throws Exception {
		ordersUtil.autoCancelOrders(param);
		String key = param.getKey();
		List<Object> ordersList;
		
		if(OrdersConstant.NOT_PAY.equals(key)){
			ordersList =  hibernateUtil.hql("from Orders where payStatic = '0' and user.id='"+param.getUserToken().getUser().getId()+"'");
		}else if(OrdersConstant.NOT_DELIVER.equals(key)){
			ordersList =  hibernateUtil.hql("from Orders where deliverStatic = '0' and user.id='"+param.getUserToken().getUser().getId()+"'");
		}else if(OrdersConstant.NOT_TASK_DELIVER.equals(key)){
			ordersList =  hibernateUtil.hql("from Orders where confirmStatic = '0' and user.id='"+param.getUserToken().getUser().getId()+"'");
		}else if(OrdersConstant.NOT_EVALUATE.equals(key)){
			ordersList =  hibernateUtil.hql("from Orders where feedback is NULL and user.id='"+param.getUserToken().getUser().getId()+"'");
		}else{
			ordersList =  hibernateUtil.hql("from Orders where user.id='"+param.getUserToken().getUser().getId()+"'");
		}
		
		return ObjectToResult.getResult(ordersList);
	}
	/**
	 * 根据订单ID获取订单详情
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrdersByOrdersId(Parameter param) throws Exception {
		Orders orders = (Orders) hibernateUtil.find(Orders.class, param.getId()+"");
		List<Object> ordersDetailList = hibernateUtil.hql("from OrdersDetail where orders.id='"+orders.getId()+"'");
		return ObjectToResult.getResult(ordersUtil.transformObject(orders,ordersDetailList));
	}
	
	@Override
	public Result getOrdersByOrdersNumber(Parameter param) throws Exception {
		OrderVo vo = (OrderVo) Common.jsonToBean(param.getObj().toString(), OrderVo.class);
		Orders orders = (Orders) hibernateUtil.hqlFirst("from Orders where orderNumber='"+vo.getOrderNumber()+"'");
		return ObjectToResult.getResult(orders);
	}
	
	/**
	 * 生成订单
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveOrders(Parameter param) throws Exception {
		OrderVo vo = (OrderVo) Common.jsonToBean(param.getObj().toString(), OrderVo.class);
		String goodsParameter = vo.getGoodsParameter();
		String[] goodsParams = goodsParameter.split(":");
		UserAddress userAddress = (UserAddress) hibernateUtil.find(UserAddress.class, vo.getAddressId());
		Orders orders = (Orders) hibernateUtil.hqlFirst("from Orders where orderNumber='"+vo.getOrderNumber()+"'");
//		orders.setUserAddress(userAddress);
		//2016-11-7  李丁修改订单地址 
		orders.setConsignee(userAddress.getConsignee());
		orders.setConsigneeTel(userAddress.getTel());
		orders.setAddress(userAddress.getName()+userAddress.getAddress());
		
		orders.setOrderTime(new DateStr().toString());
		orders.setModifyTime(new DateStr().toString());
		orders.setInvoiceType(vo.getInvoiceType());
		orders.setInvoiceHead(vo.getInvoiceHead());
		orders.setInvoiceContent(vo.getInvoiceContent());
		orders.setFeedback(vo.getFeedBack());
		orders.setState("1");
		orders.setOrderStatic("1");
		if(vo.getCard()!=null){
			User user = userAddress.getUser();
			user.setCardId(vo.getCard());
			user.setModifyTime(new DateStr().toString());
			hibernateUtil.save(user);
		}
		String[] goods = null;
		int goodsTotal = 0;
		for (int i = 0; i < goodsParams.length; i++) {
			String goodsParam = goodsParams[i];
			goods = goodsParam.split(",");
			goodsTotal += Integer.parseInt(goods[1]);
		}
		orders.setGoodsTotal(String.valueOf(goodsTotal));
		double coinNumber = 0;
		if(vo.getCoinFlag()){
			OrderPriceVo priceVo = new OrderPriceVo();
			getCoinInfo(param,priceVo);
			double orderPrice = Double.parseDouble(orders.getOrderPrice());
			double coinPrice;
			int newCoinNumber = priceVo.getCoinNumber();
			if(newCoinNumber>=vo.getCoinNumber()){
				double base = priceVo.getCoinPrice()/priceVo.getCoinNumber();
				double newOrderPrice = orderPrice - priceVo.getCoinPrice();
				if(newOrderPrice<0){
					coinNumber = Math.floor(orderPrice/base);
					coinPrice = coinNumber*base;
					newOrderPrice = orderPrice - coinPrice;
					if(newOrderPrice==0){
						orders.setOrderStatic("2");
					}
					orders.setBeeCoins((int)coinNumber+"");
					orders.setCoinPrice(new DecimalFormat("0.00").format(coinPrice));
					orders.setOrderPrice(new DecimalFormat("0.00").format(newOrderPrice));
				}else{
					orders.setBeeCoins(String.valueOf((int)priceVo.getCoinNumber()));
					orders.setCoinPrice(new DecimalFormat("0.00").format(priceVo.getCoinPrice()));
					orders.setOrderPrice(new DecimalFormat("0.00").format(newOrderPrice));
				}
				
			}else{
				priceVo.setMsg("您兑换的蜂币不够，请重新确认");
				return ObjectToResult.getResult(priceVo);
			}
			
		}
		hibernateUtil.save(orders);
		String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
		for (int i = 0; i < goodsParams.length; i++) {
			String goodsParam = goodsParams[i];
			goods = goodsParam.split(",");
			GoodsParam goodsP = (GoodsParam) hibernateUtil.find(GoodsParam.class, goods[0]+"");
			OrdersDetail ordersDetail = new OrdersDetail();
			List<Object> goodsEventList = hibernateUtil.hql("from GoodsEvent where id in (select goodsEvent.id from GoodsEventJoin where goods.id='"+goodsP.getGoods().getId()+"' and state='1') and state='1' and eventBegen<='"+current+"' and eventEnd>='"+current+"'");
			if(goodsEventList.size()>0){
				GoodsEvent event = (GoodsEvent) goodsEventList.get(0);
				ordersDetail.setEventType(event.getTypeName());
				ordersDetail.setEventBegen(event.getEventBegen());
				ordersDetail.setEventEnd(event.getEventEnd());
			}
			ordersDetail.setGoodsParam(goodsP);
			ordersDetail.setNumber(goods[1]);
			ordersDetail.setOrders(orders);
			ordersDetail.setCreateTime(new DateStr().toString());
			ordersDetail.setState("1");
			hibernateUtil.save(ordersDetail);
			countGoodsEevntJoin(goodsP.getGoods().getId(),goods[1]);//删除参加活动的商品数量
			if(goods!=null&&goods.length>3){//删除购物车的内容
				param.setId(goods[3]);
				cartServ.delShopCart(param);
			}
		}
		
		
		updateGoodsParam(orders.getId(),"add");
		if(vo.getCoinFlag()){
			if(coinNumber!=0){
				updateCoinInfo(param.getUserToken().getUser().getUsername(),(int)coinNumber);
			}else{
				updateCoinInfo(param.getUserToken().getUser().getUsername(),vo.getCoinNumber());
			}
			
		}
		return ObjectToResult.getResult(orders);
	}
	
	/**
	 * 删除参加活动的商品数量
	 * @param goodsId
	 * @param count
	 */
	public void countGoodsEevntJoin(String goodsId,String count){
		List<Object> list = hibernateUtil.hql("from GoodsEventJoin where state='1' and quantity>0 and goods.id='"+goodsId+"'");
		for (int j = 0; j < list.size(); j++) {
			GoodsEventJoin join = (GoodsEventJoin) list.get(j);
			int num = join.getQuantity()-Integer.parseInt(count);
			join.setQuantity(num);
			hibernateUtil.update(join);
		}
	}
	
	/**
	 * 获取订单支付详情
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrderPayDetailByPayId(Parameter parameter)
			throws Exception {
		OrdersPay pay = (OrdersPay) hibernateUtil.find(OrdersPay.class, parameter.getId()+"");
		return ObjectToResult.getResult(pay);
	}
	
	/**
	 * 获取订单所有状态数量
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrderAllStaticSizeByUserId(Parameter param)
			throws Exception {
		
		List<Object> ordersList1 =  hibernateUtil.hql("from Orders where orderStatic = '1' and user.id='"+param.getUserToken().getUser().getId()+"'");
		List<Object> ordersList3 =  hibernateUtil.hql("from Orders where orderStatic in('2', '3') and user.id='"+param.getUserToken().getUser().getId()+"'");
		List<Object> ordersList5 =  hibernateUtil.hql("from Orders where orderStatic in('4', '5') and user.id='"+param.getUserToken().getUser().getId()+"'");
		OrderStaticCountVo vo = new OrderStaticCountVo();
		vo.setWaitPaymentCount(String.valueOf(ordersList1.size()));
		vo.setWaitShipmentsCount(String.valueOf(ordersList3.size()));
		vo.setWaitReceiptCount(String.valueOf(ordersList5.size()));
		return ObjectToResult.getResult(vo);
	}

	/**
	 * 获取订单物流信息
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrdersLogistics(Parameter param) throws Exception {
		Orders orders = (Orders) hibernateUtil.find(Orders.class, param.getId()+"");
		KdniaoTrackQueryAPI kdniaoTrackQueryAPI = new KdniaoTrackQueryAPI();
		//String res = kdniaoTrackQueryAPI.getOrderTracesByJson("SF", "606102226173");
		String res = kdniaoTrackQueryAPI.getOrderTracesByJson(orders.getLogisticsCompany().getCode(), orders.getOdd());
		OrdersLogisticsVo vo = new OrdersLogisticsVo();
		vo.setOrderNumber(orders.getOrderNumber());
		vo.setLogisticeCompanyName(orders.getLogisticsCompany().getName());
		vo.setLogisticeInfo(res);
		vo.setOdd(orders.getOdd());
		vo.setOrderTime(orders.getOrderTime());
		if(!"null".equals(orders.getLogInfo())){
			vo.setLogInfo(orders.getLogInfo());
		}
		if("1".equals(orders.getCustomsClearanceStatic())){
			vo.setCustomsClearanceStatic("清关中");
		}else if("2".equals(orders.getCustomsClearanceStatic())){
			vo.setCustomsClearanceStatic("已清关");
		}
		return ObjectToResult.getResult(vo);
	}
	

	/**
	 * 从详细页面进行订单支付
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result payOrders(Parameter param) throws Exception {
		Orders orders = (Orders) hibernateUtil.find(Orders.class, param.getId()+"");
		return ObjectToResult.getResult(chinaPayUtil.payByOrders(orders));
	}
	
	/**
	 * 获取订单总价
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	//@Override
	public OrderPriceVo getOrdersPayable(Parameter param,List<ShopGoodsParamVo> list,Orders o) throws Exception {
		//首次进来生成订单
		//OrderPriceVo vo = checkGoodsEvent(param);
		//if(vo.getMsg()==null){
		OrderPriceVo vo = new OrderPriceVo();
		Orders orders;
		if("1".equals(o.getIsHasChild())){
			Object obj = saveOrdersFirst(param);
			orders = (Orders) hibernateUtil.find(Orders.class, obj.toString());
		}else{
			orders = o;
		}
			
			double price = 0.00;
			double totalPayable = 0.00;
			double payable = 0.00;
			double mjOrderPrice = 0.00;
			double orderPrice = 0.00;
			double totalBenPrice = 0.00;//总优惠金额
			double costPrice = 0.00;//折后单价
			double costPayable = 0.00;//折后总价
			String isCard = "no";
			String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
			List<MjGoodsEventVo> mjGoodsEventList =  new ArrayList<MjGoodsEventVo>();
			for (int i = 0; i < list.size(); i++) {
				ShopGoodsParamVo shopGoodsParamVo = list.get(i);
				//String goodsParam = goodsParams[i];
				//String[] goods = goodsParam.split(",");
				GoodsParam goodsP = (GoodsParam) hibernateUtil.find(GoodsParam.class, shopGoodsParamVo.getGoodsParamId()+"");
				Goods good = goodsP.getGoods();
				if("1".equals(good.getCrossborder())){//判断是否是跨境商品1是
					isCard = "yes";
				}
				int num= Integer.parseInt(shopGoodsParamVo.getNum());
				System.out.println("活动原价："+goodsP.getGoods().getId()+goodsP.getGoods().getTitle()+",数量为"+num+"总价为"+payable);
				
				List<Object> goodsEventJoinList = hibernateUtil.hql("from GoodsEventJoin where goods.id='"+good.getId()+"' and state='1' and goodsEvent.eventBegen<='"+current+"' and goodsEvent.eventEnd>='"+current+"'");
				for (int j = 0; j < goodsEventJoinList.size(); j++) {
					GoodsEventJoin eventJoin = (GoodsEventJoin) goodsEventJoinList.get(j);
					int quantity = eventJoin.getQuantity();
					if(quantity<num){
						vo.setMsg("你挑选的活动商品["+good.getTitle()+"]库存不足，请重新挑选");
						return vo;
					}
				}
				
				//查询此商品参加的有效活动
				List<Object> goodsEventList = hibernateUtil.hql("from GoodsEvent where id in (select goodsEvent.id from GoodsEventJoin where goods.id='"+good.getId()+"' and state='1') and state='1' and eventBegen<='"+current+"' and eventEnd>='"+current+"'");
				MjGoodsEventVo mjVo = null;
				if(goodsEventList!=null&&goodsEventList.size()>0){
					for (int j = 0; j < goodsEventList.size(); j++) {
						GoodsEvent event = (GoodsEvent) goodsEventList.get(j);
						if("满减".equals(event.getTypeName())){
							mjVo = new MjGoodsEventVo();
							mjVo.setGoodsEvent(event);
						}else{
							GoodsEventJoin join = (GoodsEventJoin) hibernateUtil.hqlFirst("from GoodsEventJoin where state = '1' and goods.id='"+good.getId()+"' and goodsEvent.id = '"+event.getId()+"'");
							if(join.getEventPrice()!=null){//商品活动价格
								costPrice = join.getEventPrice().doubleValue();
							}else if(event.getEventPrice()!=null){
								costPrice = event.getEventPrice().doubleValue();
							}else{
								//折扣单价
								costPrice = Double.parseDouble(new DecimalFormat("0.00").format(Double.parseDouble(goodsP.getPrice())*Float.parseFloat(event.getDiscount())));
								//saveGoodsEventLog(param.getUserToken().getUser(), event, orders, goodsP, String.valueOf(num),costPrice,costPayable,benPrice);
							}
							costPayable = costPrice*num;
							payable = costPrice*num;
							totalPayable += payable;
						}
						
						if(j==goodsEventList.size()-1){
							if(mjVo!=null){//参加了满减活动
								if(j==0){//只参加了满减活动
									price = Double.parseDouble(goodsP.getPrice());
									payable = price*num;
									totalPayable += payable;
									costPayable = payable;
								}
								mjOrderPrice = costPayable;
								mjVo.setOrderPrice(mjOrderPrice);
								mjVo.setGoods(good);
								mjGoodsEventList.add(mjVo);
							}else{//没参加满减活动
								orderPrice += costPayable;
							}
						}
					}
				}else{
					price = Double.parseDouble(goodsP.getPrice());
					num= Integer.parseInt(shopGoodsParamVo.getNum());
					payable = price*num;
					totalPayable += payable;
					orderPrice += payable;
					System.out.println("无优惠活动："+goodsP.getGoods().getId()+goodsP.getGoods().getTitle()+"的单价为"+price+",数量为"+num+"总价为"+payable);
				}
			}
			System.out.println("订单总价："+totalPayable);
			System.out.println("订单优惠："+totalBenPrice);
			System.out.println("普通订单支付："+(orderPrice));
			System.out.println("需满减订单支付："+(mjOrderPrice));
			System.out.println("--------满减开始--------");
			List<Double> mjPrice = new ArrayList<Double>();
			//指定商品满减活动
			if(mjGoodsEventList.size()>0&&mjGoodsEventList.get(0)!=null){
				Map<String, Double> group = getMjGoodsEventGroup(mjGoodsEventList);
				mjPrice = MjGoodsEventGroupCount(group);
			}
			//判断是否有全场满减活动
			Object qcmj = hibernateUtil.hqlFirst("from GoodsEvent where state='1' and universal ='1' and eventBegen<='"+current+"' and eventEnd>='"+current+"'");
			
			if(qcmj!=null){
				GoodsEvent event = (GoodsEvent) qcmj;
				Map<String, Double> qcGroup = new HashMap<String, Double>();
				if(mjPrice.size()>0){
					totalBenPrice += mjPrice.get(1);
					qcGroup.put(event.getId(), mjPrice.get(0)+orderPrice);
				}else{
					qcGroup.put(event.getId(), orderPrice);
				}
				
				mjPrice = MjGoodsEventGroupCount(qcGroup);
			}
			if(mjPrice.size()>0){
				orderPrice = mjPrice.get(0);
				totalBenPrice += mjPrice.get(1);
			}
			orders.setOrderTime(new DateStr().toString());
			orders.setOrderPrice(new DecimalFormat("0.00").format(orderPrice));
			orders.setPayable(new DecimalFormat("0.00").format(totalPayable));
			orders.setBenefitPrice(new DecimalFormat("0.00").format(totalBenPrice));
			orders.setCreateTime(new DateStr().toString());
			orders.setModifyPerson(param.getUserId());
			orders.setModifyTime(new DateStr().toString());
			if("1".equals(o.getIsHasChild())){
				orders.setParentOrdersId(o.getId());
			}
			hibernateUtil.save(orders);
			
			vo.setPayable(new DecimalFormat("0.00").format(totalPayable));
			vo.setOrderPrice(new DecimalFormat("0.00").format(orderPrice));
			vo.setBenPrice(new DecimalFormat("0.00").format(totalBenPrice));
			vo.setOrderNumber(orders.getOrderNumber());
			vo.setIsCard(isCard);
			getCoinInfo(param,vo);//获取蜂币
			getDeductionCoinInfo(param,vo,orders);
			getDoubleCoinData(param,vo);//参与蜂币翻倍活动
		//}
		return vo;
	}
	
	/**
	 * 获取用户蜂币
	 * @param param
	 * @param vo
	 * @throws Exception
	 */
	private void getCoinInfo(Parameter param,OrderPriceVo vo) throws Exception{
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
	private void getDeductionCoinInfo(Parameter param,OrderPriceVo vo, Orders orders) throws Exception{
		double orderPrice = Double.parseDouble(orders.getOrderPrice());
		int coinNumber = vo.getCoinNumber();//账户总蜂币
		double newCoinNumber;
		double coinPrice = vo.getCoinPrice();
		double base = coinPrice/coinNumber;
		
		double newOrderPrice = orderPrice - coinPrice;
		if(newOrderPrice<0){
			newCoinNumber = Math.floor(orderPrice/base);
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
//		Object obj = checkExistGoodsEvent("蜂币翻倍");
//		GoodsEvent goodsEvent = new GoodsEvent();
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
//					hql = "select od from OrdersDetail od, Orders o where od.orders.id = o.id and o.orderStatic not in('0','8') and od.goodsParam.id = '"+goodsP.getId()+"' and od.eventType='"+event.getTypeName()+"' and od.eventBegen='"+event.getEventBegen()+"' and od.eventEnd = '"+event.getEventEnd()+"' and o.user.username = '"+param.getUserToken().getUser().getUsername()+"'";
					hql = "select od from OrdersDetail od, Orders o where od.orders.id = o.id and o.orderStatic not in('0','8') and od.goodsParam.goods.id = '"+good.getId()+"' and od.eventType='"+event.getTypeName()+"' and od.eventBegen='"+event.getEventBegen()+"' and od.eventEnd = '"+event.getEventEnd()+"' and o.user.username = '"+param.getUserToken().getUser().getUsername()+"'";
					List<Object> ordersDetailList = hibernateUtil.hql(hql);
					if(ordersDetailList.size()>0){
						vo.setMsg("你已经购买过【"+good.getTitle()+"】了，此商品参加的【"+event.getEventName()+"】活动一人只能购买一件");
					}
					
				}
			}
		}
//		BigDecimal doubleRate;
//		if(obj!=null){
//			goodsEvent = (GoodsEvent) obj;
//			doubleRate = goodsEvent.getExchangeRate();
//			double newCoinPrice = new BigDecimal(vo.getCoinPrice()).multiply(doubleRate).doubleValue();
//			vo.setCoinPrice(newCoinPrice);
//			vo.setFlag("1");
//		}
	}
	

	/**
	 * 修改用户蜂币
	 * @param param
	 * @param vo
	 * @throws Exception
	 */
	private void updateCoinInfo(String param,int num) throws Exception{
		UserService userService = WebserviceUtil.getService();
		userService.modifyIntegral(param, num);
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
	 * 订单支付
	 */
	@Override
	public Result orderPay(Parameter param) throws Exception {
		Object id = param.getId();
		String payType = id.toString();
		String orderNumber = param.getKey();
		Orders order = (Orders) hibernateUtil.hqlFirst("from Orders where state = '1' and orderNumber='"+orderNumber+"'");
		Map result = new HashMap();
		//如果状态是未付款才进行付款操作
		String orderState = ordersUtil.getState(order.getOrderStatic());
		if(!"1".equals(order.getOrderStatic())){
			Result res = new Result();
			res.setResult(0);
			res.setMessage("该商品状态为"+orderState+",下单失败");
			return res;
		}
		if(OrdersConstant.CHINAPAYTEST.equals(payType)){
//			result = chinaPayTestUtil.payByOrders(order);
		}else if(OrdersConstant.CHINAPAY.equals(payType)){
			result = chinaPayUtil.payByOrders(order);
		}else if(OrdersConstant.ALIPAY.equals(payType)){
			result = aliPayUtil.payByOrders(order);
		}else if(OrdersConstant.WXPAY.equals(payType)){
			String mobilWxPayVo = weiXinPayUtil.payByOrders(order,req.getRemoteAddr());
			Result mobilWxPay = ObjectToResult.getResult(mobilWxPayVo);
			return mobilWxPay;
		}else if(OrdersConstant.ALIQBPAY.equals(payType)){
			String resultStr = aliPayUtil.payByOrdersApp(order);
			System.out.println(resultStr);
			return ObjectToResult.getResult(resultStr);
		}
		return ObjectToResult.getResult(result);
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
	
	/**
	 * 满减活动价格计算
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
				if(value>rule.getTarget()){
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

	/**
	 * 获取订单商品展示信息
	 */
	@Override
	public Result getGoods(Parameter param) throws Exception {
		List<Object> goodsList = new ArrayList<Object>();
		Map<String, List<ShopGoodsParamVo>> map = ordersUtil.getShopAndGoodsParam(param.getKey());
		List<ShopGoodsVo> shopGoodsList = new ArrayList<ShopGoodsVo>();
		Object[] obj = map.keySet().toArray();
		String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
		for (Object object : obj) {
			ShopGoodsVo shopGoodsVo = new ShopGoodsVo();
			BusinessStore store = (BusinessStore) hibernateUtil.find(BusinessStore.class, object.toString());
			shopGoodsVo.setShopId(object.toString());
			shopGoodsVo.setShopName(store.getStoreName());
			List<ShopGoodsParamVo> voList = map.get(object);
			for (ShopGoodsParamVo shopGoodsParamVo : voList) {
				GoodsVo vo = new GoodsVo();
				GoodsParam goodsP = (GoodsParam) hibernateUtil.find(GoodsParam.class, shopGoodsParamVo.getGoodsParamId()+"");
				Goods good = goodsP.getGoods();
				List<Object> goodsEventList = hibernateUtil.hql("from GoodsEvent where id in (select goodsEvent.id from GoodsEventJoin where goods.id='"+good.getId()+"' and state='1') and state='1' and eventBegen<='"+current+"' and eventEnd>='"+current+"'");
				if(goodsEventList!=null&&goodsEventList.size()>0){
					for (int j = 0; j < goodsEventList.size(); j++) {
						GoodsEvent event = (GoodsEvent) goodsEventList.get(j);
						GoodsEventJoin join = (GoodsEventJoin) hibernateUtil.hqlFirst("from GoodsEventJoin where state = '1' and goods.id='"+good.getId()+"' and goodsEvent.id = '"+event.getId()+"'");
						if(join.getEventPrice()!=null){
							vo.setPrice(join.getEventPrice().toString());
							break;
						}else if(event.getEventPrice()!=null){
							vo.setPrice(event.getEventPrice().toString());
							break;
						}else if(event.getDiscount()!=null){
							vo.setPrice(new DecimalFormat("0.00").format(Double.parseDouble(goodsP.getPrice())*Float.parseFloat(event.getDiscount())));
							break;
						}else{
							vo.setPrice(goodsP.getPrice());
						}
					}
				}else{
					vo.setPrice(goodsP.getPrice());
				}
				vo.setNum(shopGoodsParamVo.getNum());
				vo.setParam(goodsP.getParamName());
				vo.setPic(good.getPicSrc());
				vo.setTitle(good.getTitle());
				goodsList.add(vo);
				shopGoodsVo.setGoodsVoList(goodsList);
				shopGoodsList.add(shopGoodsVo);
			}
		}
		
		
		
		
//		String[] goodsParams = id.split(":");
//		String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
//		for (int i = 0; i < goodsParams.length; i++) {
//			GoodsVo vo = new GoodsVo();
//			String goodsParam = goodsParams[i];
//			String[] goods = goodsParam.split(",");
//			GoodsParam goodsP = (GoodsParam) hibernateUtil.find(GoodsParam.class, goods[0]+"");
//			Goods good = goodsP.getGoods();
//			List<Object> goodsEventList = hibernateUtil.hql("from GoodsEvent where id in (select goodsEvent.id from GoodsEventJoin where goods.id='"+good.getId()+"' and state='1') and state='1' and eventBegen<='"+current+"' and eventEnd>='"+current+"'");
//			if(goodsEventList!=null&&goodsEventList.size()>0){
//				for (int j = 0; j < goodsEventList.size(); j++) {
//					GoodsEvent event = (GoodsEvent) goodsEventList.get(j);
//					GoodsEventJoin join = (GoodsEventJoin) hibernateUtil.hqlFirst("from GoodsEventJoin where state = '1' and goods.id='"+good.getId()+"' and goodsEvent.id = '"+event.getId()+"'");
//					if(join.getEventPrice()!=null){
//						vo.setPrice(join.getEventPrice().toString());
//						break;
//					}else if(event.getEventPrice()!=null){
//						vo.setPrice(event.getEventPrice().toString());
//						break;
//					}else if(event.getDiscount()!=null){
//						vo.setPrice(new DecimalFormat("0.00").format(Double.parseDouble(goodsP.getPrice())*Float.parseFloat(event.getDiscount())));
//						break;
//					}else{
//						vo.setPrice(goodsP.getPrice());
//					}
//				}
//			}else{
//				vo.setPrice(goodsP.getPrice());
//			}
//			vo.setNum(goods[1]);
//			vo.setParam(goodsP.getParamName());
//			vo.setPic(good.getPicSrc());
//			vo.setTitle(good.getTitle());
//			goodsList.add(vo);
//		}
		return ObjectToResult.getResult(shopGoodsList);
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
	 * 给购物车提供价格接口
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public OrderPriceVo getOrderGoodsBenPrice(Parameter param) throws Exception {
		double payable = 0.0;
		double costPrice = 0.0;// 折后单价
		double totalCostPrice = 0.0;// 折后总单价
		String id = (String)param.getId();
		List<Object> list = hibernateUtil.hql("select price from GoodsParam where goods.id = '"+id+"'");
		String goodsPrice = (String)list.get(0);
		Date currentTime = DateUtil.stringFormatToDate(
				DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss"),
				"yyyy/MM/dd HH:mm:ss");
		List<Object> goodsEventJoinList = hibernateUtil
				.hql("from GoodsEventJoin where state='1' and goods.id='" + id
						+ "'");
		if (goodsEventJoinList != null && goodsEventJoinList.size() > 0) {
			for (int j = 0; j < goodsEventJoinList.size(); j++) {
				GoodsEventJoin join = (GoodsEventJoin) goodsEventJoinList
						.get(j);
				GoodsEvent event = join.getGoodsEvent();
				Date begin = DateUtil.stringFormatToDate(event.getEventBegen(),
						"yyyy/MM/dd HH:mm:ss");
				Date end = DateUtil.stringFormatToDate(event.getEventEnd(),
						"yyyy/MM/dd HH:mm:ss");
				if (currentTime.after(begin) && currentTime.before(end)
						&& "1".equals(event.getState()) && join.getQuantity() != null
						&& join.getQuantity() > 0) {
					if (!"满减".equals(event.getTypeName())) {
						payable = Double.parseDouble(goodsPrice);
						// 折后单价
						costPrice = Double
								.parseDouble(new DecimalFormat("0.00")
										.format(payable
												* Float.parseFloat(event
														.getDiscount())));
						totalCostPrice += costPrice;
					}
				} else {
					payable = Double.parseDouble(goodsPrice);
					totalCostPrice = payable;
				}
			}
		} else {
			double price = Double.parseDouble(goodsPrice);
			payable = price;
			totalCostPrice = price;
		}
		OrderPriceVo vo = new OrderPriceVo();
		vo.setPayable(String.valueOf(payable));
		vo.setOrderPrice(String.valueOf(totalCostPrice));
		return vo;
	}

	/**
	 * 通过主键获取订单信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result getOrdersById(Parameter param) throws Exception {
		Orders orders = (Orders) hibernateUtil.find(Orders.class, param.getKey()+"");
		return ObjectToResult.getResult(orders);
	}
	
	/**
	 * 记录商品的价格折扣信息
	 * @param user
	 * @param event
	 * @param order
	 * @param param
	 * @param num
	 * @param costPrice
	 * @param costPayable
	 * @param benPrice
	 */
	public void saveGoodsEventLog(User user,GoodsEvent event,Orders order,GoodsParam param,String num,Double costPrice,Double costPayable,Double benPrice){
		GoodsEventLog log = new GoodsEventLog();
		log.setGoodsEvent(event);
		log.setGoodsParam(param);
		log.setOrders(order);
		log.setUser(user);
		log.setGoodsNum(num);
		log.setGoodsPayable(param.getPrice());//商品原单价
		log.setCost(event.getDiscount());//活动折扣
		log.setGoodsBenprice(String.valueOf(benPrice));//商品优惠
		log.setGoodsCostable(String.valueOf(costPrice));//折扣单价
		log.setGoodsTotalCostable(String.valueOf(costPayable));//折扣总价
		log.setCreatePerson(user.getId());
		log.setCreateTime(new DateStr().toString());
		log.setState("1");
		hibernateUtil.save(log);
	}
	
	/**
	 * 第一次保存订单
	 * @param param
	 * @return
	 */
	public Object saveOrdersFirst(Parameter param){
		Orders orders = new Orders();
		orders.setCreatePerson(param.getUserId());
		orders.setOrderStatic("0");//订单状态
		orders.setDeliverStatic("0");//发货状态
		orders.setConfirmStatic("0");//确认收货状态
		orders.setPayStatic("0");//支付状态
		orders.setState("0");
		orders.setOrderNumber(getOrderNumber());
		orders.setUser(param.getUserToken().getUser());
		Object res = hibernateUtil.save(orders);
		return res;
	}

	/**
	 * 确认收货
	 */
	@Override
	public Result confirmTakeGoods(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update Orders set confirmStatic = '1',orderStatic = '6' where id = '"+param.getId()+"'");
		return ObjectToResult.getResult(num);
	}
	
	/**
	 * 自动取消订单
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result autoCancelOrders(Parameter param) throws Exception {
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
		return ObjectToResult.getResult(new Object());
	}
	
	/**
	 * 检查购买商品参加的活动类型、数量
	 * @param param
	 * @return
	 */
	public OrderPriceVo checkGoodsEvent(Parameter param){
		String[] eventType = new String[]{"秒杀","限时抢","新品抢先","省钱趴"};
		OrderPriceVo vo = new OrderPriceVo();
		String id = param.getKey();
		String[] goodsParams = id.split(":");
		String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
		String hql;
		for (int i = 0; i < goodsParams.length; i++) {
			String goodsParam = goodsParams[i];
			String[] goods = goodsParam.split(",");
			GoodsParam goodsP = (GoodsParam) hibernateUtil.find(GoodsParam.class, goods[0]+"");
			Goods good = goodsP.getGoods();
			//查询此商品参加的有效活动
			List<Object> goodsEventList = hibernateUtil.hql("from GoodsEvent where id in (select goodsEvent.id from GoodsEventJoin where goods.id='"+good.getId()+"' and state='1') and state='1' and eventBegen<='"+current+"' and eventEnd>='"+current+"'");
			for (int j = 0; j < goodsEventList.size(); j++) {
				GoodsEvent event = (GoodsEvent) goodsEventList.get(j);
				if(Arrays.asList(eventType).contains(event.getTypeName())&&"1".equals(event.getFirstOrder())){
					if(Integer.parseInt(goods[1])!=1){
						vo.setMsg("你购买的【"+good.getTitle()+"】商品参加了【"+event.getEventName()+"】活动，一人只能购买一件");
						return vo;
					}else{
						hql = "select od from OrdersDetail od, Orders o where od.orders.id = o.id and o.orderStatic not in('0','8') and od.goodsParam.goods.id = '"+good.getId()+"' and od.eventType='"+event.getTypeName()+"' and od.eventBegen='"+event.getEventBegen()+"' and od.eventEnd = '"+event.getEventEnd()+"' and o.user.username = '"+param.getUserToken().getUser().getUsername()+"'";
						List<Object> ordersDetailList = hibernateUtil.hql(hql);
						if(ordersDetailList.size()>0){
							vo.setMsg("你已经购买过【"+good.getTitle()+"】了，此商品参加的【"+event.getEventName()+"】活动一人只能购买一件");
							return vo;
						}
						
					}
				}
			}
		}
		return vo;
	}
	
	public Object checkExistGoodsEvent(String eventType){
		String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
		String hql = "from GoodsEvent where state='1' and typeName = '"+eventType+"' and eventBegen<='"+current+"' and eventEnd>='"+current+"'";
		Object obj = hibernateUtil.hqlFirst(hql);
		return obj;
		
	}

	/**
	 * 根据主键获取订单详情
	 */
	@Override
	public Result getOrdersDetailById(Parameter param) throws Exception {
		OrdersDetail detail = (OrdersDetail) hibernateUtil.find(OrdersDetail.class, param.getId()+"");
		return ObjectToResult.getResult(detail);
	}
	
	/**
	 * 订单拆分
	 */
	@Override
	public Result getOrdersPayable(Parameter param) throws Exception {
		double payable = 0.00;
		double orderPrice = 0.00;
		double benPrice = 0.00;
		String orderNumber;
		String isCard;
		String msg;
		int coinNumber = 0;
		int totalCoinNumber = 0;
		double coinPrice = 0.00;
		String flag;
		OrderPriceVo vo = checkGoodsEvent(param);
		List<OrderPriceVo> voList = new ArrayList<OrderPriceVo>();
		if(vo.getMsg()==null){
			Object obj = saveOrdersFirst(param);
			Orders orders = (Orders) hibernateUtil.find(Orders.class, obj.toString());
			Map<String, List<ShopGoodsParamVo>> map = ordersUtil.getShopAndGoodsParam(param.getKey());
			Set<Entry<String, List<ShopGoodsParamVo>>> set = map.entrySet();
			Iterator it = set.iterator();
			if(map.size()>1){//存在多个店铺，需要拆分订单
				orders.setIsHasChild("1");
			}else{
				orders.setIsHasChild("0");
			}
			while(it.hasNext()){
				List<ShopGoodsParamVo> list = (List<ShopGoodsParamVo>) it.next();
				vo = getOrdersPayable(param, list, orders);
				voList.add(vo);
			}
			
			for (OrderPriceVo orderPriceVo : voList) {
				payable += Double.parseDouble(orderPriceVo.getPayable());
				orderPrice += Double.parseDouble(orderPriceVo.getOrderPrice());
				benPrice += Double.parseDouble(orderPriceVo.getBenPrice());
			}
			vo.setBenPrice(String.valueOf(benPrice));
			vo.setCoinNumber(totalCoinNumber);
			vo.setCoinPrice(coinPrice);
			vo.setOrderNumber(orders.getOrderNumber());
			vo.setOrderPrice(String.valueOf(orderPrice));
			vo.setPayable(String.valueOf(payable));
			vo.setTotalCoinNumber(totalCoinNumber);
		}
		return ObjectToResult.getResult(vo);
	}

}

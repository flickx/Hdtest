package com.ftoul.pc.orders.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ftoul.po.BusinessStore;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsEvent;
import com.ftoul.po.GoodsEventJoin;
import com.ftoul.po.GoodsEventLog;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.JPositionProvice;
import com.ftoul.po.Orders;
import com.ftoul.po.OrdersDetail;
import com.ftoul.po.OrdersPay;
import com.ftoul.po.User;
import com.ftoul.po.UserAddress;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.logistics.LogisticsUtil;
import com.ftoul.util.orders.OrdersUtil;
import com.ftoul.util.price.PriceUtil;
import com.ftoul.web.goods.service.GoodsParamServ;
import com.ftoul.pc.orders.service.OrdersServ;
import com.ftoul.pc.orders.vo.PcOrderVo;
import com.ftoul.web.vo.GoodsVo;
import com.ftoul.web.vo.ManyVsOneVo;
import com.ftoul.web.vo.MjGoodsEventVo;
import com.ftoul.web.vo.OrderPriceVo;
import com.ftoul.web.vo.OrderStaticCountVo;
import com.ftoul.web.vo.OrderVo;
import com.ftoul.web.vo.OrdersLogisticsVo;
import com.ftoul.web.vo.ShopGoodsParamVo;
import com.ftoul.web.vo.ShopGoodsVo;

@Service("PcOrdersServImpl")
public class OrdersServImpl implements OrdersServ {

	@Autowired
	private HibernateUtil hibernateUtil;
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
	GoodsParamServ goodsParamServ;
	@Autowired  
	private  HttpServletRequest req;
	@Autowired  
	OrdersUtil ordersUtil;
	@Autowired  
	PriceUtil priceUtil;
	@Autowired  
	LogisticsUtil logisticsUtil;
	
	/**
	 * 获取订单所有状态数量
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrderAllStaticSizeByUserId(Parameter param)
			throws Exception {
		List<Object> ordersList1 =  hibernateUtil.hql("from Orders where orderStatic = '1' and state= '1' and user.id='"+param.getUserToken().getUser().getId()+"'");
		List<Object> ordersList3 =  hibernateUtil.hql("from Orders where orderStatic in('2', '3') and isHasChild!='1' and state= '1' and user.id='"+param.getUserToken().getUser().getId()+"'");//待发货
		List<Object> ordersList5 =  hibernateUtil.hql("from Orders where orderStatic in('4', '5') and state= '1' and user.id='"+param.getUserToken().getUser().getId()+"'");
		List<Object> afterList =  hibernateUtil.hql("from AfterSchedule where scheduleStatic='1' and state= '1' and user.id='"+param.getUserToken().getUser().getId()+"'"); 
		List<Object> ordersList7 =  hibernateUtil.hql("from Orders where orderStatic = '7' and state= '1' and user.id='"+param.getUserToken().getUser().getId()+"'"); //待评价
		List<Object> newOrdersList1 = new ArrayList<Object>();
		for (Object object : ordersList1) {//将有父订单的订单去除掉，只计算父订单为未付款数量
			Orders orders = (Orders) object;
			if(orders.getParentOrdersId()==null){
				newOrdersList1.add(orders);
			}
		}
		OrderStaticCountVo vo = new OrderStaticCountVo();
		vo.setWaitPaymentCount(String.valueOf(newOrdersList1.size()));
		vo.setWaitShipmentsCount(String.valueOf(ordersList3.size()));
		vo.setWaitReceiptCount(String.valueOf(ordersList5.size()));
		vo.setAfterCount(String.valueOf(afterList.size()));
		vo.setWaitCommentCount(String.valueOf(ordersList7.size()));
		return ObjectToResult.getResult(vo);
	}
	
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
			page =  hibernateUtil.hqlPage(null,"from Orders where orderStatic = '1' and state='1' and user.id='"+param.getUserToken().getUser().getId()+param.getWhereStr()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else if(OrdersConstant.NOT_DELIVER.equals(key)){
			page =  hibernateUtil.hqlPage(null,"from Orders where orderStatic in('2', '3') and state='1' and user.id='"+param.getUserToken().getUser().getId()+param.getWhereStr()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else if(OrdersConstant.NOT_TASK_DELIVER.equals(key)){
			page =  hibernateUtil.hqlPage(null,"from Orders where orderStatic in ('4','5') and state='1' and user.id='"+param.getUserToken().getUser().getId()+param.getWhereStr()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else if(OrdersConstant.RECOVERY.equals(key)){//查询回收站的数据
			page =  hibernateUtil.hqlPage(null,"from Orders where state='2' and user.id='"+param.getUserToken().getUser().getId()+param.getWhereStr()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else if(OrdersConstant.NOT_COMMENT.equals(key)){//查询待评价的数据
			page =  hibernateUtil.hqlPage(null,"from Orders where orderStatic = '7' and state='1' and user.id='"+param.getUserToken().getUser().getId()+param.getWhereStr()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else{
			page =  hibernateUtil.hqlPage(null,"from Orders where orderStatic!='0' and isHasChild!='1' and state='1' and user.id='"+param.getUserToken().getUser().getId()+param.getWhereStr()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		}
		ordersList = page.getObjList();
		
		List<Object> childOrdersDetailList = new ArrayList<Object>();
		//ManyVsOneVo vo = new ManyVsOneVo();
		PcOrderVo vo = new PcOrderVo();
		List<Object> list = new ArrayList<Object>();
		if(OrdersConstant.NOT_PAY.equals(key)){
			for (int i = 0; i < ordersList.size(); i++) {
				Orders order = (Orders) ordersList.get(i);
				List<Object> ordersDetailList = new ArrayList<Object>();
				if("1".equals(order.getIsHasChild())){
					List<Object> childList = hibernateUtil.hql("from Orders where state='1' and parentOrdersId = '"+order.getId()+"'");
					for (Object object : childList) {
						Orders childOrders = (Orders) object;
						childOrdersDetailList = hibernateUtil.hql("from OrdersDetail where orders.id='"+childOrders.getId()+"'");
						ordersDetailList.addAll(childOrdersDetailList);
					}
					//vo = ordersUtil.transformObject(order,ordersDetailList);
					vo = ordersUtil.transformOrder(order,ordersDetailList);
					list.add(vo);
				}else{
					if(order.getParentOrdersId()==null){
						List<Object> detailList = hibernateUtil.hql("from OrdersDetail where orders.id='"+order.getId()+"'");
						//vo = ordersUtil.transformObject(order,detailList);
						vo = ordersUtil.transformOrder(order,ordersDetailList);
						list.add(vo);
					}
				}
				
			}
		}else{
			for (int i = 0; i < ordersList.size(); i++) {
				Orders order = (Orders) ordersList.get(i);
				List<Object> ordersDetailList = new ArrayList<Object>();
				//if(!"1".equals(order.getIsHasChild())){
					ordersDetailList = hibernateUtil.hql("from OrdersDetail where orders.id='"+order.getId()+"'");
					//vo = ordersUtil.transformObject(order,ordersDetailList);
					vo = ordersUtil.transformOrder(order,ordersDetailList);
					list.add(vo);
				//}
			}
		}
		
		page.setObjList(null);
		page.setObjList(list);
		return ObjectToResult.getResult(page);
	}
	
	/**
	 * 删除订单=进入回收站
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result recoveryOrders(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update Orders set state='2' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}
	
	/**
	 * 还原订单
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result restoreOrders(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update Orders set state='1' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}
	
	/**
	 * 删除订单
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result deleteOrders(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update Orders set state='0' and orderStatic='7' where id in ("+StrUtil.getIds(param.getId())+")");
		ordersUtil.updateGoodsParam(param.getId().toString(),"delete");
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
		Orders order = (Orders) hibernateUtil.find(Orders.class, param.getId()+"");
		if("1".equals(order.getIsHasChild())){
			List<Object> obj = hibernateUtil.hql("from Orders where parentOrdersId ='"+order.getId()+"'");
			for (Object object : obj) {
				Orders child = (Orders) object;
				hibernateUtil.execHql("update Orders set orderStatic = '8' where id in ('"+child.getId()+"')");
				ordersUtil.updateGoodsParam(child.getId(),"cancel");
			}
		}else{
			ordersUtil.updateGoodsParam(param.getId().toString(),"cancel");
		}
		if(order.getBeeCoins()!=null){
			ordersUtil.updateCoinInfo(param.getUserToken().getUser().getUsername(),-Integer.parseInt(order.getBeeCoins()));
		}
		
		return ObjectToResult.getResult(num);
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
		List<Object> ordersDetailList = new ArrayList<Object>();
		List<Object> oneVoManyList = new ArrayList<Object>();
		if("1".equals(orders.getIsHasChild())){
			List<Object> childOrdersList =  hibernateUtil.hql("from Orders where state='1' and parentOrdersId='"+orders.getId()+"'");
			Orders child = new Orders();
			List<Object> childDetailList = new ArrayList<Object>();
			for (Object object : childOrdersList) {
				child = (Orders) object;
				childDetailList = hibernateUtil.hql("from OrdersDetail where orders.id='"+child.getId()+"'");
				ordersDetailList.addAll(childDetailList);
				oneVoManyList.add(ordersUtil.transformObject(child,childDetailList));
			}
		}else{
			ordersDetailList = hibernateUtil.hql("from OrdersDetail where orders.id='"+orders.getId()+"'");
			oneVoManyList.add(ordersUtil.transformObject(orders,ordersDetailList));
		}
		
		return ObjectToResult.getResult(ordersUtil.transformOrderDetail(orders,oneVoManyList));
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
		int num = 0;
		double totalFreight = 0.0;
		double freight = 0.0;
		OrderVo vo = (OrderVo) Common.jsonToBean(param.getObj().toString(), OrderVo.class);
		UserAddress userAddress = (UserAddress) hibernateUtil.find(UserAddress.class, vo.getAddressId());
		Orders orders = (Orders) hibernateUtil.hqlFirst("from Orders where orderNumber='"+vo.getOrderNumber()+"'");
		orders.setConsignee(userAddress.getConsignee());
		orders.setConsigneeTel(userAddress.getTel());
		Object obj = hibernateUtil.hqlFirst("from JPositionProvice where proviceId='"+userAddress.getProvince()+"'"); 
		JPositionProvice province = (JPositionProvice) obj;
		orders.setProvince(province.getProviceName());
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
		hibernateUtil.save(orders);
		
		List<Object> objList = vo.getVoList();
		for (Object object : objList) {
			Map orderPriceVo = (Map) object;
			Orders child = (Orders) hibernateUtil.hqlFirst("from Orders where orderNumber='"+orderPriceVo.get("orderNumber")+"'");
			freight = logisticsUtil.getFreight(province.getProviceName(), child.getShopId().getId(), Integer.parseInt(child.getGoodsTotal()));
			totalFreight += freight;
			child.setConsignee(userAddress.getConsignee());
			child.setProvince(province.getProviceName());
			child.setConsigneeTel(userAddress.getTel());
			child.setAddress(userAddress.getName()+userAddress.getAddress());
			child.setOrderTime(new DateStr().toString());
			child.setModifyTime(new DateStr().toString());
			child.setInvoiceType(vo.getInvoiceType());
			child.setInvoiceHead(vo.getInvoiceHead());
			child.setInvoiceContent(vo.getInvoiceContent());
			child.setFeedback(vo.getFeedBack());
			child.setIsHasChild("0");
			child.setState("1");
			child.setOrderStatic("1");
			child.setFreight(new BigDecimal(freight));
			hibernateUtil.save(child);
			List<Map> paramList = (List<Map>) orderPriceVo.get("voList");
			
			String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
			for (Map shopGoodsParamVo : paramList) {
				GoodsParam goodsP = (GoodsParam) hibernateUtil.find(GoodsParam.class, shopGoodsParamVo.get("id")+"");
				OrdersDetail ordersDetail = new OrdersDetail();
				num = Integer.parseInt((String)shopGoodsParamVo.get("num"));
				//totalNum += num;
				ordersDetail.setNumber((String)shopGoodsParamVo.get("num"));
				List<Object> goodsEventList = hibernateUtil.hql("from GoodsEvent where id in (select goodsEvent.id from GoodsEventJoin where goods.id='"+goodsP.getGoods().getId()+"' and state='1') and state='1' and eventBegen<='"+current+"' and eventEnd>='"+current+"'");
				if(goodsEventList.size()>0){
					GoodsEvent event = (GoodsEvent) goodsEventList.get(0);
					ordersDetail.setEventType(event.getTypeName());
					ordersDetail.setEventBegen(event.getEventBegen());
					ordersDetail.setEventEnd(event.getEventEnd());
					GoodsEventJoin join = (GoodsEventJoin) hibernateUtil.hqlFirst("from GoodsEventJoin where state='1' and goodsEvent.id='"+event.getId()+"' and goods.id='"+goodsP.getGoods().getId()+"'");
					if(join.getEventPrice()!=null){
						ordersDetail.setPrice(join.getEventPrice().toString());
					}else if(event.getEventPrice()!=null){
						ordersDetail.setPrice(event.getEventPrice().toString());
					}else if(event.getDiscount()!=null){
						ordersDetail.setPrice(new DecimalFormat("0.00").format(Double.parseDouble(goodsP.getPrice())*Float.parseFloat(event.getDiscount())));
					}else{
						ordersDetail.setPrice(goodsP.getPrice());
					}
				}else{
					ordersDetail.setPrice(goodsP.getPrice());
				}
				double price = Double.parseDouble(ordersDetail.getPrice());
				ordersDetail.setTotalPrice(new BigDecimal(num*price));
				ordersDetail.setGoodsParam(goodsP);
				ordersDetail.setOrders(child);
				ordersDetail.setShopId(child.getShopId().getId());
				ordersDetail.setCreateTime(new DateStr().toString());
				ordersDetail.setState("1");
				hibernateUtil.save(ordersDetail);
				countGoodsEevntJoin(goodsP.getGoods().getId(),(String)shopGoodsParamVo.get("num"));//删除参加活动的商品数量
			}
			ordersUtil.updateGoodsParam(child.getId(),"add");
		}
		orders.setOrderPrice(String.valueOf(orders.getGoodsTotalPrice().doubleValue()+totalFreight));
		BigDecimal goodsTotalPriceD = new BigDecimal(orders.getGoodsTotalPrice().doubleValue());
		BigDecimal totalFreightB = new BigDecimal(totalFreight);
		orders.setOrderPrice(String.valueOf(goodsTotalPriceD.add(totalFreightB).doubleValue()));
		//orders.setOrderPrice(new BigDecimal(orders.getGoodsTotalPrice().doubleValue()).add(new BigDecimal(totalFreight)).toString());
//		double coinNumber = 0;
		BigDecimal coinNumberB;
		int coinNumber = 0;
		if(vo.getCoinFlag()){
			OrderPriceVo priceVo = new OrderPriceVo();
			ordersUtil.getCoinInfo(param,priceVo);
			//double orderPrice = Double.valueOf(orders.getOrderPrice());
			double orderPrice = orders.getGoodsTotalPrice().doubleValue();
			double coinPrice;
			int newCoinNumber = priceVo.getCoinNumber();
			if(newCoinNumber>=vo.getCoinNumber()){
				double base = priceVo.getCoinPrice()/priceVo.getCoinNumber();
				double newOrderPrice = orderPrice - priceVo.getCoinPrice();
				if(newOrderPrice<0){
					BigDecimal orderPriceB = new BigDecimal(Double.toString(orderPrice));
					BigDecimal baseB = new BigDecimal(Double.toString(base));
					double value = orderPriceB.divide(baseB,2,BigDecimal.ROUND_UP).doubleValue();
					//coinNumber = Math.floor(value);
					//coinPrice = coinNumber*base;
					coinNumberB = new BigDecimal(Math.floor(value));
					baseB = new BigDecimal(Double.toString(base));
					coinPrice = coinNumberB.multiply(baseB).doubleValue();
					newOrderPrice = orderPrice - coinPrice;
					coinNumber = coinNumberB.intValue();
					
					if(newOrderPrice==0||newOrderPrice==0.0||newOrderPrice==0.00){
						if(totalFreight==0.00){
							orders.setOrderPrice(new DecimalFormat("0.00").format(newOrderPrice));
							orders.setOrderStatic("2");
							orders.setPayStatic("1");
							orders.setPayTime(new DateStr().toString());
							orders.setPayType("5");//全蜂币支付方式
							if("1".equals(orders.getIsHasChild())){
								List<Object> childList = hibernateUtil.hql("from Orders where parentOrdersId='"+orders.getId()+"'");
								for (Object object : childList) {
									Orders childOrder = (Orders) object;
									childOrder.setOrderStatic("2");
									childOrder.setPayStatic("1");
									childOrder.setPayTime(new DateStr().toString());
									childOrder.setPayType("5");//全蜂币支付方式
									hibernateUtil.update(childOrder);
								}
							}
						}else{
							orders.setOrderPrice(new DecimalFormat("0.00").format(totalFreight));
						}
					}
					//orders.setBeeCoins((int)coinNumber+"");
					orders.setBeeCoins(coinNumber+"");
					orders.setCoinPrice(new DecimalFormat("0.00").format(coinPrice));
					//orders.setOrderPrice(new DecimalFormat("0.00").format(newOrderPrice));
				}else{
					orders.setBeeCoins(String.valueOf((int)priceVo.getCoinNumber()));
					orders.setCoinPrice(new DecimalFormat("0.00").format(priceVo.getCoinPrice()));
					orders.setOrderPrice(new DecimalFormat("0.00").format(newOrderPrice+totalFreight));
				}
				
			}else{
				priceVo.setMsg("您兑换的蜂币不够，请重新确认");
				return ObjectToResult.getResult(priceVo);
			}
			
		}
		orders.setFreight(new BigDecimal(totalFreight));
		hibernateUtil.save(orders);
		if(vo.getCoinFlag()){
			if(coinNumber!=0){
				ordersUtil.updateCoinInfo(param.getUserToken().getUser().getUsername(),coinNumber);
			}else{
				ordersUtil.updateCoinInfo(param.getUserToken().getUser().getUsername(),vo.getCoinNumber());
			}
			
		}
		//删除购物车的内容
		ordersUtil.delShopCart(param);
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
	 * 获取订单物流信息
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrdersLogistics(Parameter param) throws Exception {
		Orders orders = (Orders) hibernateUtil.find(Orders.class, param.getId()+"");
		KdniaoTrackQueryAPI kdniaoTrackQueryAPI = new KdniaoTrackQueryAPI();
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
	public OrderPriceVo getOrdersPayable(Parameter param,List<ShopGoodsParamVo> list,Orders o) throws Exception {
		//首次进来生成订单
		//OrderPriceVo vo = checkGoodsEvent(param);
		//if(vo.getMsg()==null){
		OrderPriceVo vo = new OrderPriceVo();
		Orders orders;
		if("1".equals(o.getIsHasChild())){
			Object obj = saveOrdersFirst(param);
			orders = (Orders) hibernateUtil.find(Orders.class, obj.toString());
			orders.setParentOrdersId(o.getId());
		}else{
			orders = o;
		}
			
		double price = 0.00;
		double goodsPrice = 0.00;
		double totalPayable = 0.00;
		double payable = 0.00;
		double mjOrderPrice = 0.00;
		double orderPrice = 0.00;
		double totalBenPrice = 0.00;//总优惠金额
		double costPrice = 0.00;//折后单价
		double costPayable = 0.00;//折后总价
		int goodsNum=0;
		String isCard = "no";
		String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
		List<Object> goodsVoList = new ArrayList<Object>();
		List<MjGoodsEventVo> mjGoodsEventList =  new ArrayList<MjGoodsEventVo>();
		List<MjGoodsEventVo> mmjGoodsEventList =  new ArrayList<MjGoodsEventVo>();
		String eventName="";
		for (int i = 0; i < list.size(); i++) {
			ShopGoodsParamVo shopGoodsParamVo = (ShopGoodsParamVo) list.get(i);
			BusinessStore businessStore = (BusinessStore)hibernateUtil.find(BusinessStore.class, shopGoodsParamVo.getShopId());
			orders.setShopId(businessStore);
			vo.setShopName(businessStore.getStoreName());
			vo.setShopId(businessStore.getId());
			GoodsParam goodsP = (GoodsParam) hibernateUtil.find(GoodsParam.class, shopGoodsParamVo.getGoodsParamId()+"");
			Goods good = goodsP.getGoods();
			if("1".equals(good.getCrossborder())){//判断是否是跨境商品1是
				isCard = "yes";
			}
			int num= Integer.parseInt(shopGoodsParamVo.getNum());
			goodsNum+=num;
			GoodsVo goodsVo = new GoodsVo();
			goodsVo.setId(shopGoodsParamVo.getGoodsParamId());
			goodsVo.setNum(shopGoodsParamVo.getNum());
			goodsVo.setParam(goodsP.getParamName());
			goodsVo.setPic(good.getPicSrc());
			goodsVo.setTitle(good.getTitle());
			List<Object> goodsEventJoinList = hibernateUtil.hql("from GoodsEventJoin where goodsEvent.state='1' and goods.id='"+good.getId()+"' and state='1' and goodsEvent.eventBegen<='"+current+"' and goodsEvent.eventEnd>='"+current+"'");
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
			MjGoodsEventVo mmjVo = null;
			if(goodsEventList!=null&&goodsEventList.size()>0){
				for (int j = 0; j < goodsEventList.size(); j++) {
					GoodsEvent event = (GoodsEvent) goodsEventList.get(j);
//					if("阶梯满减".equals(event.getTypeName())){
//						mjVo = new MjGoodsEventVo();
//						mjVo.setGoodsEvent(event);
//						eventName = event.getEventName();
//					}else if("每满减".equals(event.getTypeName())){
//						mmjVo = new MjGoodsEventVo();
//						mmjVo.setGoodsEvent(event);
//						eventName = event.getEventName();
//					}
					
					GoodsEventJoin join = (GoodsEventJoin) hibernateUtil.hqlFirst("from GoodsEventJoin where state = '1' and goods.id='"+good.getId()+"' and goodsEvent.id = '"+event.getId()+"'");
					if(join.getEventPrice()!=null){//活动关联的商品的价格
						costPrice = join.getEventPrice().doubleValue();
					}else if(event.getEventPrice()!=null){//活动价格
						costPrice = event.getEventPrice().doubleValue();
					}else if(event.getDiscount()!=null){//折扣单价
						costPrice = Double.parseDouble(new DecimalFormat("0.00").format(Double.parseDouble(goodsP.getPrice())*Float.parseFloat(event.getDiscount())));
					}else{
						costPrice = Double.parseDouble(goodsP.getPrice());
					}
					System.out.println(event.getEventName()+":"+goodsP.getGoods().getId()+":"+goodsP.getGoods().getTitle()+"的优惠价为"+costPrice+",数量为"+num+"总价为"+payable+"订单商品价为"+totalPayable);
					System.out.println(event.getEventName()+":"+goodsP.getGoods().getId()+":"+goodsP.getGoods().getTitle()+"的优惠价为"+costPrice+",数量为"+num+"总价为"+payable+"订单商品价为"+totalPayable);
					System.out.println(event.getEventName()+":"+goodsP.getGoods().getId()+":"+goodsP.getGoods().getTitle()+"的优惠价为"+costPrice+",数量为"+num+"总价为"+payable+"订单商品价为"+totalPayable);
					costPayable = costPrice*num;//当前商品折后总价
					//payable = costPrice*num;//当前商品原总价
					goodsPrice = Double.parseDouble(goodsP.getPrice());//商品原价
					payable = goodsPrice*num;//当前商品原总价
					totalPayable += payable;//订单原总价
					
					//判断此商品是否参加了阶梯满减活动
					mjVo = priceUtil.getMjGoodsEvent(good, "阶梯满减");
					//判断此商品是否参加了每满减活动
					mmjVo = priceUtil.getMjGoodsEvent(good, "每满减");
					
					if(j==goodsEventList.size()-1){
						if(mjVo!=null){//参加阶梯满减活动
							if(j==0){//只参加了阶梯满减活动
								price = Double.parseDouble(goodsP.getPrice());
								payable = price*num;
								totalPayable += payable;
								costPayable = payable;
							}
							mjOrderPrice = costPayable;
							mjVo.setOrderPrice(mjOrderPrice);
							mjVo.setGoods(good);
							mjGoodsEventList.add(mjVo);
						}else if(mmjVo!=null){//参加每满减活动
							if(j==0){//只参加了每满减活动
								price = Double.parseDouble(goodsP.getPrice());
								payable = price*num;
								totalPayable += payable;
								costPayable = payable;
							}
							mjOrderPrice = costPayable;
							mmjVo.setOrderPrice(mjOrderPrice);
							mmjVo.setGoods(good);
							mmjGoodsEventList.add(mmjVo);
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
				System.out.println("无优惠活动："+goodsP.getGoods().getId()+":"+goodsP.getGoods().getTitle()+"的单价为"+price+",数量为"+num+"总价为"+payable+"订单商品价为"+totalPayable);
			}
			if(costPrice>0){
				goodsVo.setPrice(String.valueOf(costPrice));
				costPrice = 0.00;
			}else{
				goodsVo.setPrice(String.valueOf(price));
			}
			goodsVoList.add(goodsVo);
		}
		System.out.println("--------满减开始--------");
		List<Double> mjPrice = new ArrayList<Double>();
		List<Double> mmjPrice = new ArrayList<Double>();
		//阶梯满减活动
		if(mjGoodsEventList.size()>0&&mjGoodsEventList.get(0)!=null){
			Map<String, Double> group = priceUtil.getMjGoodsEventGroup(mjGoodsEventList);
			mjPrice = priceUtil.MjGoodsEventGroupCount(group);
		}
		//每满减
		if(mmjGoodsEventList.size()>0&&mmjGoodsEventList.get(0)!=null){
			Map<String, Double> group = priceUtil.getMjGoodsEventGroup(mmjGoodsEventList);
			mmjPrice = priceUtil.MmjGoodsEventGroupCount(group);
		}
		
		//判断是否有全场满减活动
//		Object qcmj = hibernateUtil.hqlFirst("from GoodsEvent where typeName='满减' state='1' and universal ='1' and eventBegen<='"+current+"' and eventEnd>='"+current+"'");
//		
//		if(qcmj!=null){
//			GoodsEvent event = (GoodsEvent) qcmj;
//			eventName = event.getEventName();
//			Map<String, Double> qcGroup = new HashMap<String, Double>();
//			if(mjPrice.size()>0){
//				totalBenPrice += mjPrice.get(1);
//				qcGroup.put(event.getId(), mjPrice.get(0)+orderPrice);
//			}else{
//				qcGroup.put(event.getId(), orderPrice);
//			}
//			
//			mjPrice = MjGoodsEventGroupCount(qcGroup);
//		}
		
		if(mjPrice.size()>0){
			orderPrice += mjPrice.get(0);
			totalBenPrice += mjPrice.get(1);
			vo.setEventName(eventName);
		}
		
		if(mmjPrice.size()>0){
			orderPrice += mmjPrice.get(0);
			totalBenPrice += mmjPrice.get(1);
			vo.setEventName(eventName);
		}
		
		String province = logisticsUtil.getDefaultUserAddressProvince(param.getUserToken().getUser().getId());
		double freight = logisticsUtil.getFreight(province, vo.getShopId(), goodsNum);
		orders.setGoodsTotal(String.valueOf(goodsNum));
		
		orders.setOrderTime(new DateStr().toString());
		orders.setFreight(new BigDecimal(freight));//运费
		orders.setGoodsTotalPrice(new BigDecimal(orderPrice));//商品总价
		orders.setOrderPrice(new DecimalFormat("0.00").format(orderPrice+freight));//订单支付金额
		orders.setPayable(new DecimalFormat("0.00").format(totalPayable));
		orders.setBenefitPrice(new DecimalFormat("0.00").format(totalBenPrice));
		orders.setCreateTime(new DateStr().toString());
		orders.setModifyPerson(param.getUserId());
		orders.setModifyTime(new DateStr().toString());
		System.out.println("店铺："+orders.getShopId().getStoreName()+"，订单号："+orders.getOrderNumber()+",商品最终总价格："+orders.getGoodsTotalPrice().doubleValue()+",订单金额（包含运费）："+orders.getOrderPrice()+",运费："+orders.getFreight().doubleValue());
		hibernateUtil.save(orders);
		vo.setGoodsNum(goodsNum);
		vo.setFreight(freight);
		vo.setPayable(new DecimalFormat("0.00").format(totalPayable));
		vo.setOrderPrice(new DecimalFormat("0.00").format(orderPrice));//商品总价，不包括运费
		vo.setBenPrice(new DecimalFormat("0.00").format(totalBenPrice));
		vo.setOrderNumber(orders.getOrderNumber());
		vo.setIsCard(isCard);
		vo.setVoList(goodsVoList);
		return vo;
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
	 * 获取订单商品展示信息
	 */
	@Override
	public Result getGoods(Parameter param) throws Exception {
		
		Map<String, List<ShopGoodsParamVo>> map = ordersUtil.getShopAndGoodsParam(param.getKey());
		List<ShopGoodsVo> shopGoodsList = new ArrayList<ShopGoodsVo>();
		Object[] obj = map.keySet().toArray();
		String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
		String isCard = "no";
		for (Object object : obj) {
			ShopGoodsVo shopGoodsVo = new ShopGoodsVo();
			List<Object> goodsList = new ArrayList<Object>();
			BusinessStore store = (BusinessStore) hibernateUtil.find(BusinessStore.class, object.toString());
			shopGoodsVo.setShopId(object.toString());
			shopGoodsVo.setShopName(store.getStoreName());
			List<ShopGoodsParamVo> voList = map.get(object);
			for (ShopGoodsParamVo shopGoodsParamVo : voList) {
				GoodsVo vo = new GoodsVo();
				GoodsParam goodsP = (GoodsParam) hibernateUtil.find(GoodsParam.class, shopGoodsParamVo.getGoodsParamId()+"");
				Goods good = goodsP.getGoods();
				if("1".equals(good.getCrossborder())){//判断是否是跨境商品1是
					isCard = "yes";
				}
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
				shopGoodsVo.setIsCard(isCard);
				shopGoodsVo.setGoodsVoList(goodsList);
			}
			shopGoodsList.add(shopGoodsVo);
		}

		return ObjectToResult.getResult(shopGoodsList);
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
		orders.setCreateTime(new DateStr().toString());
		orders.setCreatePerson(param.getUserId());
		orders.setOrderStatic("0");//订单状态
		orders.setDeliverStatic("0");//发货状态
		orders.setConfirmStatic("0");//确认收货状态
		orders.setPayStatic("0");//支付状态
		orders.setState("0");
		orders.setOrderNumber(ordersUtil.getOrderNumber());
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
			if(goodsP!=null){
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
			}else{
				vo.setMsg("没有此商品参数");
				return vo;
			}
			
		}
		return vo;
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
	 * 根据店铺进行订单拆分
	 */
	@Override
	public Result getOrdersPayable(Parameter param) throws Exception {
		double payable = 0.00;
		double orderPrice = 0.00;
		double benPrice = 0.00;
		double coinPrice = 0.00;
		double freight = 0.00;
		int totalCoinNumber = 0;
		int goodsTotalNum = 0;
		OrderPriceVo msgVo = checkGoodsEvent(param);
		OrderPriceVo vo = new OrderPriceVo();
		List<Object> voList = new ArrayList<Object>();
		if(msgVo.getMsg()==null){
			Object obj = saveOrdersFirst(param);
			Orders orders = (Orders) hibernateUtil.find(Orders.class, obj.toString());
			Map<String, List<ShopGoodsParamVo>> map = ordersUtil.getShopAndGoodsParam(param.getKey());
			if(map.size()>1){//存在多个店铺，需要拆分订单
				orders.setIsHasChild("1");
				Object[] key = map.keySet().toArray();
				List<ShopGoodsParamVo> list = new ArrayList<ShopGoodsParamVo>();
				for (Object object : key) {
					list = map.get(object);
					OrderPriceVo orderPriceVo = getOrdersPayable(param, list, orders);
					voList.add(orderPriceVo);
				}
				
				for (int i = 0; i < voList.size(); i++) {
					OrderPriceVo orderPriceVo = (OrderPriceVo) voList.get(i);
					if(orderPriceVo.getMsg()!=null){
						return ObjectToResult.getResult(orderPriceVo);
					}
					payable += Double.parseDouble(orderPriceVo.getPayable());
					orderPrice += Double.parseDouble(orderPriceVo.getOrderPrice());
					benPrice += Double.parseDouble(orderPriceVo.getBenPrice());
					freight += orderPriceVo.getFreight();
					goodsTotalNum += orderPriceVo.getGoodsNum();
					if("1".equals(orderPriceVo.getIsCard())){
						vo.setIsCard("yes");
					}
				}
				
				vo.setBenPrice(String.valueOf(benPrice));
				vo.setCoinNumber(totalCoinNumber);
				vo.setCoinPrice(coinPrice);
				vo.setOrderNumber(orders.getOrderNumber());
				vo.setFreight(freight);
				vo.setOrderPrice(String.valueOf(orderPrice+freight));
				vo.setPayable(String.valueOf(payable));
				vo.setTotalCoinNumber(totalCoinNumber);
				vo.setGoodsTotalPrice(orderPrice);
				vo.setVoList(voList);
				orders.setGoodsTotalPrice(new BigDecimal(orderPrice));
				orders.setFreight(new BigDecimal(vo.getFreight()));
				orders.setPayable(vo.getPayable());
				orders.setOrderPrice(vo.getOrderPrice());
				orders.setBenefitPrice(vo.getBenPrice());
				orders.setGoodsTotal(String.valueOf(goodsTotalNum));
				hibernateUtil.update(orders);
				
			}else{
				orders.setIsHasChild("0");
				Object[] key = map.keySet().toArray();
				List<ShopGoodsParamVo> list = new ArrayList<ShopGoodsParamVo>();
				Object object = key[0];
				list = map.get(object);
				OrderPriceVo orderPriceVo = getOrdersPayable(param, list, orders);
				if(orderPriceVo.getMsg()!=null){
					return ObjectToResult.getResult(orderPriceVo);
				}
				voList.add(orderPriceVo);
				if("yes".equals(orderPriceVo.getIsCard())){
					vo.setIsCard("yes");
				}
				vo.setBenPrice(orderPriceVo.getBenPrice());
				vo.setCoinNumber(orderPriceVo.getCoinNumber());
				vo.setCoinPrice(orderPriceVo.getCoinPrice());
				vo.setOrderNumber(orders.getOrderNumber());
				vo.setOrderPrice(String.valueOf(Double.valueOf(orderPriceVo.getOrderPrice())+orderPriceVo.getFreight()));
				vo.setPayable(orderPriceVo.getPayable());
				vo.setTotalCoinNumber(orderPriceVo.getTotalCoinNumber());
				vo.setGoodsTotalPrice(Double.valueOf(orderPriceVo.getOrderPrice()));
				vo.setVoList(voList);
				vo.setFreight(orderPriceVo.getFreight());
			}
			
			ordersUtil.getCoinInfo(param,vo);//获取蜂币
			ordersUtil.getDeductionCoinInfo(param,vo,orders);
			ordersUtil.getDoubleCoinData(param,vo);//参与蜂币翻倍活动
		}
		
		return ObjectToResult.getResult(vo);
	}

	/**
	 * 根据订单号查询该订单所在地址的运费
	 */
	@Override
	public Result getOrdersFreightByOrderNumber(Parameter param)
			throws Exception {
		OrderPriceVo vo = new OrderPriceVo();
		Object obj = hibernateUtil.hqlFirst("from Orders where state='0' and orderNumber='"+param.getId()+"'");
		double freight = 0.0;
		String provinceName = null;
		if(obj!=null){
			Orders order = (Orders) obj;
			UserAddress userAddress = (UserAddress) hibernateUtil.find(UserAddress.class, param.getKey()+"");
			if(userAddress!=null){
				Object object = hibernateUtil.hqlFirst("from JPositionProvice where proviceId='"+userAddress.getProvince()+"'"); 
				JPositionProvice province = (JPositionProvice) object;
				provinceName = province.getProviceName();
			}
			if("0".equals(order.getIsHasChild())){
				freight = logisticsUtil.getFreight(provinceName, order.getShopId().getId(), Integer.parseInt(order.getGoodsTotal()));
			}else{
				List<Object> list = hibernateUtil.hql("from Orders where state='0' and parentOrdersId='"+order.getId()+"'");
				for (Object object : list) {
					Orders childOrder = (Orders) object;
					freight += logisticsUtil.getFreight(provinceName, childOrder.getShopId().getId(), Integer.parseInt(childOrder.getGoodsTotal()));
				}
			}
			vo.setFreight(freight);
			vo.setOrderPrice(String.valueOf((order.getGoodsTotalPrice().doubleValue()+freight)));
		}
		
		return ObjectToResult.getResult(vo);
	}

	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(Double.toString(50.9));
		BigDecimal b = new BigDecimal("0.1");
//		System.out.println(String.valueOf(a.add(b).doubleValue()));
		System.out.println(a.divide(b,2,BigDecimal.ROUND_UP ).doubleValue());
		System.out.println(a.divide(b,2,BigDecimal.ROUND_UP ).toString());
	}
}

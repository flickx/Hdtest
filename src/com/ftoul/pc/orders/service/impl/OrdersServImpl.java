package com.ftoul.pc.orders.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.ftoul.pc.orders.service.OrdersServ;
import com.ftoul.pc.orders.vo.PcOrderVo;
import com.ftoul.pc.orders.vo.UseCoponOrderPriceVo;
import com.ftoul.po.BusinessStore;
import com.ftoul.po.Coupon;
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
import com.ftoul.po.UserCoupon;
import com.ftoul.util.coin.CoinUtil;
import com.ftoul.util.coupon.CouponUtil;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.logistics.LogisticsUtil;
import com.ftoul.util.orders.OrdersUtil;
import com.ftoul.util.price.PriceUtil;
import com.ftoul.web.goods.service.GoodsParamServ;
import com.ftoul.web.vo.GoodsVo;
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
	HttpServletRequest req;
	@Autowired  
	OrdersUtil ordersUtil;
	@Autowired  
	PriceUtil priceUtil;
	@Autowired  
	LogisticsUtil logisticsUtil;
	@Autowired  
	CouponUtil couponUtil;
	@Autowired  
	CoinUtil coinUtil;
	DecimalFormat formate = new DecimalFormat("0.00");
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
		List<?> ordersList = new ArrayList<Object>();
		Page page = new Page();
		String whereStr = param.getWhereStr();
		if(whereStr==null){
			whereStr = "";
		}
		if(OrdersConstant.NOT_PAY.equals(key)){
			page =  hibernateUtil.hqlPage(null,"from Orders where orderStatic = '1' and state='1' and user.id='"+param.getUserToken().getUser().getId()+"'"+whereStr+" order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else if(OrdersConstant.NOT_DELIVER.equals(key)){
			page =  hibernateUtil.hqlPage(null,"from Orders where orderStatic in('2', '3') and isHasChild!='1' and state='1' and user.id='"+param.getUserToken().getUser().getId()+"'"+whereStr+" order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else if(OrdersConstant.NOT_TASK_DELIVER.equals(key)){
			page =  hibernateUtil.hqlPage(null,"from Orders where orderStatic in ('4','5') and isHasChild!='1' and state='1' and user.id='"+param.getUserToken().getUser().getId()+"'"+whereStr+" order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else if(OrdersConstant.RECOVERY.equals(key)){//查询回收站的数据
			page =  hibernateUtil.hqlPage(null,"from Orders where state='2' and isHasChild!='1' and user.id='"+param.getUserToken().getUser().getId()+"'"+whereStr+" order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else if(OrdersConstant.NOT_COMMENT.equals(key)){//查询待评价的数据
			page =  hibernateUtil.hqlPage(null,"from Orders where orderStatic = '7' and isHasChild!='1' and state='1' and user.id='"+param.getUserToken().getUser().getId()+"'"+whereStr+" order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else{
			page =  hibernateUtil.hqlPage(null,"from Orders where orderStatic!='0' and state='1' and user.id='"+param.getUserToken().getUser().getId()+"'"+whereStr+" order by orderTime desc",param.getPageNum(),param.getPageSize());
		}
		ordersList = page.getObjList();
		
		List<Object> childOrdersDetailList = new ArrayList<Object>();
		PcOrderVo vo = new PcOrderVo();
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < ordersList.size(); i++) {
			Orders order = (Orders) ordersList.get(i);
			List<Object> ordersDetailList = new ArrayList<Object>();
			if("1".equals(order.getIsHasChild())&&"1".equals(order.getOrderStatic())){
				List<Object> childList = hibernateUtil.hql("from Orders where state='1' and parentOrdersId = '"+order.getId()+"'");
				for (Object object : childList) {
					Orders childOrders = (Orders) object;
					childOrdersDetailList = hibernateUtil.hql("from OrdersDetail where orders.id='"+childOrders.getId()+"'");
					ordersDetailList.addAll(childOrdersDetailList);
				}
				vo = ordersUtil.transformOrder(order,ordersDetailList);
				list.add(vo);
			}else if(order.getParentOrdersId()==null){
				ordersDetailList = hibernateUtil.hql("from OrdersDetail where orders.id='"+order.getId()+"'");
				vo = ordersUtil.transformOrder(order,ordersDetailList);
				list.add(vo);
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
		Integer num = hibernateUtil.execHql("update Orders set state='0', orderStatic='7' where id in ("+StrUtil.getIds(param.getId())+")");
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
		System.out.println("11111111111111111111111");
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
		double totalFreight = 0.0;
		double freight = 0.0;
		double totalFaceValue = 0.00;
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
		orders.setInvoiceType(vo.getInvoiceType());
		orders.setInvoiceHead(vo.getInvoiceHead());
		orders.setInvoiceContent(vo.getInvoiceContent());
		orders.setFeedback(vo.getFeedBack());
		orders.setState("1");
		orders.setOrderStatic("1");
		
		if(vo.getCard()!=null){//身份证信息
			User user = userAddress.getUser();
			user.setCardId(vo.getCard());
			user.setModifyTime(new DateStr().toString());
			user.setModifyPerson(param.getUserToken().getUser().getUsername());
			hibernateUtil.save(user);
		}
		
		List<Object> objList = vo.getVoList();
		List<Object> titleList = new ArrayList<>();
		StringBuffer couponSb = new StringBuffer();
		for (Object object : objList) {
			Map orderPriceVo = (Map) object;
			Orders child = (Orders) hibernateUtil.hqlFirst("from Orders where orderNumber='"+orderPriceVo.get("orderNumber")+"'");
			if(child.getShopId()!=null){//指定的订单
				freight = logisticsUtil.getFreight(province.getProviceName(), child.getShopId().getId(), Integer.parseInt(child.getGoodsTotal()));
				saveChildOrders(child,userAddress,province.getProviceName(),freight,vo.getFeedBack());
			}else{//是用的他她乐平台的父订单，所以要查询出平台的子订单
				List<Object> childList = hibernateUtil.hql("from Orders where shopId.id='1' and parentOrdersId='"+child.getId()+"'");
				int childGoodsTotal = 0;
				for (Object object2 : childList) {
					Orders child2 = (Orders) object2;
					childGoodsTotal += Integer.parseInt(child2.getGoodsTotal());
				}
				freight = logisticsUtil.getFreight(province.getProviceName(), "1", childGoodsTotal);
				for (Object object2 : childList) {
					Orders child2 = (Orders) object2;
					saveChildOrders(child2,userAddress,province.getProviceName(),freight,vo.getFeedBack());
				}
			}
			totalFreight += freight;
			
//			child.setConsignee(userAddress.getConsignee());
//			child.setProvince(province.getProviceName());
//			child.setConsigneeTel(userAddress.getTel());
//			child.setAddress(userAddress.getName()+userAddress.getAddress());
//			child.setOrderTime(new DateStr().toString());
//			child.setInvoiceType(vo.getInvoiceType());
//			child.setInvoiceHead(vo.getInvoiceHead());
//			child.setInvoiceContent(vo.getInvoiceContent());
//			child.setFeedback(vo.getFeedBack());
//			child.setIsHasChild("0");
//			child.setState("1");
//			child.setOrderStatic("1");
//			child.setFreight(new BigDecimal(freight));
//			hibernateUtil.save(child);
			//处理优惠券
			List<Map> couponList = (List<Map>) orderPriceVo.get("couponList");
			if(couponList!=null&&couponList.size()>0){
				Map map = couponList.get(0);
				String couponId = (String) map.get("id");
				couponSb.append("couponId");
				couponSb.append(";");
				Coupon coupon = (Coupon) hibernateUtil.find(Coupon.class, couponId);
				BigDecimal faceValue = new BigDecimal(coupon.getFaceValue());
				BigDecimal orderPrice = new BigDecimal(child.getOrderPrice());
				child.setOrderPrice(formate.format(orderPrice.subtract(faceValue)));
				List<Object> childList = hibernateUtil.hql("from Orders where shopId.id='1' and parentOrdersId='"+child.getId()+"'");
				if(childList!=null&&childList.size()>0){//标记平台子订单用到的优惠券
					for (Object childObj : childList) {
						Orders childOrder = (Orders) childObj;
						childOrder.setCouponId(couponId);
						hibernateUtil.update(childOrder);
					}
				}else{
					child.setCouponId(couponId);
				}
				UserCoupon userCoupon = (UserCoupon) hibernateUtil.hqlFirst("from UserCoupon where state='1' and couponId.id='"+couponId+"' and userId='"+orders.getUser().getId()+"'");
				userCoupon.setIsUsed("2");//已使用
				userCoupon.setModifyPerson(orders.getUser().getName());
				userCoupon.setModifyTime(new DateStr().toString());
				hibernateUtil.update(userCoupon);
				totalFaceValue += faceValue.doubleValue();
			}
			
			List<Map> paramList = (List<Map>) orderPriceVo.get("voList");
			for (Map shopGoodsParamVo : paramList) {
				GoodsParam goodsP = (GoodsParam) hibernateUtil.find(GoodsParam.class, shopGoodsParamVo.get("id")+"");
				titleList.add(goodsP.getGoods().getTitle());
				ordersUtil.countGoodsEevntJoin(goodsP.getGoods().getId(),(String)shopGoodsParamVo.get("num"));//删除参加活动的商品数量
			}
			ordersUtil.updateGoodsParam(child.getId(),"add");
		}
		BigDecimal goodsTotalPriceDec = orders.getGoodsTotalPrice();
		BigDecimal totalFreightDec = new BigDecimal(totalFreight);
		BigDecimal totalFaceValueDec = new BigDecimal(totalFaceValue);
		orders.setCouponId(couponSb.toString());
		orders.setCouponPrice(String.valueOf(totalFaceValue));
		//orders.setGoodsTotalPrice(goodsTotalPriceDec.subtract(totalFaceValueDec));
		//orders.setOrderPrice(formate.format(goodsTotalPriceDec.add(totalFreightDec).subtract(totalFaceValueDec)));
		orders.setOrderPrice(formate.format(goodsTotalPriceDec.subtract(totalFaceValueDec)));
		BigDecimal coinNumberB;
		int coinNumber = 0;
		if(vo.getCoinFlag()){
			OrderPriceVo priceVo = new OrderPriceVo();
			coinUtil.getCoinInfo(param,priceVo);//计算用户有多少枚蜂币及蜂币金额
			
			double orderPrice = orders.getGoodsTotalPrice().doubleValue();
			BigDecimal coinPrice;
			int newCoinNumber = priceVo.getCoinNumber();
			int inputCoinNumber = vo.getCoinNumber();
			if(newCoinNumber>=inputCoinNumber){
				double base = priceVo.getCoinPrice()/priceVo.getCoinNumber();
				double newCoinPrice = base*inputCoinNumber;
				double newOrderPrice = orderPrice - newCoinPrice;
				if(newOrderPrice<=0){
					BigDecimal orderPriceDec = new BigDecimal(String.valueOf(orderPrice));
					BigDecimal baseDec = new BigDecimal(String.valueOf(base));
					double value = orderPriceDec.divide(baseDec,2,BigDecimal.ROUND_UP).doubleValue();
					coinNumberB = new BigDecimal(Math.floor(value));
					baseDec = new BigDecimal(String.valueOf(base));
					coinPrice = coinNumberB.multiply(baseDec);
					newOrderPrice = Double.parseDouble(formate.format(orderPriceDec.subtract(coinPrice)));
					coinNumber = coinNumberB.intValue();
					
					if(newOrderPrice==0||newOrderPrice==0.0||newOrderPrice==0.00){
						if(totalFreight==0.00){
							orders.setOrderPrice(formate.format(newOrderPrice));
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
							orders.setOrderPrice(formate.format(totalFreight));
						}
					}
					orders.setBeeCoins(inputCoinNumber+"");
					orders.setCoinPrice(formate.format(coinPrice));
				}else{
					orders.setBeeCoins(String.valueOf(inputCoinNumber));
					orders.setCoinPrice(formate.format(newCoinPrice));
					orders.setOrderPrice(formate.format(newOrderPrice+totalFreight));
				}
				
			}else{
				priceVo.setMsg("您兑换的蜂币不够，请重新确认");
				return ObjectToResult.getResult(priceVo.getMsg());
			}
			//减掉用去的蜂币数量
			if(coinNumber!=0){
				ordersUtil.updateCoinInfo(param.getUserToken().getUser().getUsername(),coinNumber);
			}else{
				ordersUtil.updateCoinInfo(param.getUserToken().getUser().getUsername(),inputCoinNumber);
			}
			
		}else{//没有使用蜂币的情况订单支付金额要加上运费
			BigDecimal orderPrice = new BigDecimal(orders.getOrderPrice());
			orders.setOrderPrice(formate.format(orderPrice.add(new BigDecimal(totalFreight))));
		}
		orders.setFreight(new BigDecimal(totalFreight));
		hibernateUtil.save(orders);
		//删除购物车的内容
		//ordersUtil.delShopCart(param);
		PcOrderVo pcOrderVo = new PcOrderVo();
		pcOrderVo.setConsigeeName(orders.getConsignee());
		pcOrderVo.setTel(orders.getConsigneeTel());
		pcOrderVo.setAddress(orders.getAddress());
		pcOrderVo.setId(orders.getId());
		pcOrderVo.setOrderNumber(orders.getOrderNumber());
		pcOrderVo.setOrderPrice(orders.getOrderPrice());
		pcOrderVo.setDetailVoList(titleList);
		return ObjectToResult.getResult(pcOrderVo);
	}
	
	public void saveChildOrders(Orders child,UserAddress userAddress,String proviceName,Double freight,String feedBack){
		child.setConsignee(userAddress.getConsignee());
		child.setProvince(proviceName);
		child.setConsigneeTel(userAddress.getTel());
		child.setAddress(userAddress.getName()+userAddress.getAddress());
		child.setOrderTime(new DateStr().toString());
		child.setFeedback(feedBack);
		child.setIsHasChild("0");
		child.setState("1");
		child.setOrderStatic("1");
		child.setFreight(new BigDecimal(freight));
		hibernateUtil.save(child);
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
		OrderPriceVo vo = new OrderPriceVo();
		Orders orders;
		if("1".equals(o.getIsHasChild())){
			orders = saveOrdersFirst(param);
			orders.setParentOrdersId(o.getId());
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
		int goodsNum=0;
		String isCard = "0";//是否是跨境商品
		String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
		List<Object> goodsVoList = new ArrayList<Object>();
		List<MjGoodsEventVo> mjGoodsEventList =  new ArrayList<MjGoodsEventVo>();
		List<MjGoodsEventVo> mmjGoodsEventList =  new ArrayList<MjGoodsEventVo>();
		List<Object> mjTitleList =  new ArrayList<Object>();
		String eventName="";
		for (int i = 0; i < list.size(); i++) {
			ShopGoodsParamVo shopGoodsParamVo = (ShopGoodsParamVo) list.get(i);
			BusinessStore businessStore = (BusinessStore)hibernateUtil.find(BusinessStore.class, shopGoodsParamVo.getShopId());
			orders.setShopId(businessStore);
			vo.setShopName(businessStore.getStoreName());
			vo.setShopId(businessStore.getId());
			GoodsParam goodsP = (GoodsParam) hibernateUtil.find(GoodsParam.class, shopGoodsParamVo.getGoodsParamId());
			Goods good = goodsP.getGoods();
			int num= Integer.parseInt(shopGoodsParamVo.getNum());
			goodsNum+=num;
			
			if("1".equals(good.getCrossborder())){//判断是否是跨境商品1是
				isCard = "1";
			}
			//查询此商品参加的有效活动
			List<Object> goodsEventList = hibernateUtil.hql("from GoodsEvent where id in (select goodsEvent.id from GoodsEventJoin where goods.id='"+good.getId()+"' and state='1') and state='1' and eventBegen<='"+current+"' and eventEnd>='"+current+"'");
			MjGoodsEventVo mjVo = null;
			MjGoodsEventVo mmjVo = null;
			GoodsEvent event = new GoodsEvent();
			if(goodsEventList!=null&&goodsEventList.size()>0){
				for (int j = 0; j < goodsEventList.size(); j++) {
					event = (GoodsEvent) goodsEventList.get(j);
					GoodsEventJoin join = (GoodsEventJoin) hibernateUtil.hqlFirst("from GoodsEventJoin where state = '1' and goods.id='"+good.getId()+"' and goodsEvent.id = '"+event.getId()+"'");
					if(join.getEventPrice()!=null){//活动关联的商品的价格
						costPrice = join.getEventPrice().doubleValue();
					}else if(event.getEventPrice()!=null){//活动价格
						costPrice = event.getEventPrice().doubleValue();
					}else if(event.getDiscount()!=null){//折扣单价
						costPrice = Double.parseDouble(formate.format(Double.parseDouble(goodsP.getPrice())*Float.parseFloat(event.getDiscount())));
					}else{
						costPrice = Double.parseDouble(goodsP.getPrice());
					}
					System.out.println(event.getEventName()+":"+goodsP.getGoods().getTitle()+"的优惠价为"+costPrice+",数量为"+num);
					costPayable = costPrice*num;//当前商品折后总价
					payable = Double.parseDouble(goodsP.getPrice())*num;//当前商品原总价
					totalPayable += payable;//订单原总价
					
					//判断此商品是否参加了阶梯满减活动
					mjVo = priceUtil.getMjGoodsEvent(good, "阶梯满减");
					//判断此商品是否参加了每满减活动
					if(mjVo==null){
						mmjVo = priceUtil.getMjGoodsEvent(good, "每满减");
					}
					
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
							if(mjVo.getGoodsEvent()!=null){
								mjTitleList.add(mjVo.getGoodsEvent().getEventName());
							}
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
							if(mmjVo.getGoodsEvent()!=null){
								mjTitleList.add(mmjVo.getGoodsEvent().getEventName());
							}
							
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
				System.out.println("无优惠活动："+":"+goodsP.getGoods().getTitle()+"的单价为"+price+",数量为"+num+"总价为"+payable+"订单商品价为"+totalPayable);
			}
			GoodsVo goodsVo = new GoodsVo();
			goodsVo.setId(shopGoodsParamVo.getGoodsParamId());
			goodsVo.setNum(shopGoodsParamVo.getNum());
			goodsVo.setParam(goodsP.getParamName());
			goodsVo.setPic(good.getPicSrc());
			goodsVo.setTitle(good.getTitle());
			if(costPrice>0){
				goodsVo.setPrice(String.valueOf(costPrice));
				costPrice = 0.00;
			}else{
				goodsVo.setPrice(String.valueOf(price));
			}
			goodsVoList.add(goodsVo);
			//将商品保存到订单明细中
			OrdersDetail ordersDetail = new OrdersDetail();
			ordersDetail.setEventType(event.getTypeName());
			ordersDetail.setEventBegen(event.getEventBegen());
			ordersDetail.setEventEnd(event.getEventEnd());
			ordersDetail.setNumber(String.valueOf(num));
			ordersDetail.setPrice(goodsVo.getPrice());
			ordersDetail.setTotalPrice(new BigDecimal(num*Double.parseDouble(goodsVo.getPrice())));
			ordersDetail.setGoodsParam(goodsP);
			ordersDetail.setGoodsTitle(goodsVo.getTitle());
			ordersDetail.setPicSrc(goodsVo.getPic());
			ordersDetail.setParamName(goodsVo.getParam());
			ordersDetail.setOrders(orders);
			ordersDetail.setShopId(orders.getShopId().getId());
			ordersDetail.setIsComment("0");
			ordersDetail.setIsAfter("0");
			ordersDetail.setCreateTime(new DateStr().toString());
			ordersDetail.setCreatePerson(param.getUserToken().getUser().getUsername());
			ordersDetail.setState("1");
			hibernateUtil.save(ordersDetail);
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
		double freight = 0.00;
		if("0".equals(orders.getIsHasChild())){
			freight = logisticsUtil.getFreight(province, vo.getShopId(), goodsNum);
		}
		orders.setFreight(new BigDecimal(freight));//运费
		orders.setGoodsTotal(String.valueOf(goodsNum));
		orders.setOrderTime(new DateStr().toString());
		orders.setGoodsTotalPrice(new BigDecimal(orderPrice));//商品总价
		orders.setOrderPrice(formate.format(orderPrice+freight));//订单支付金额
		orders.setPayable(formate.format(totalPayable));//订单原总价
		orders.setBenefitPrice(formate.format(totalBenPrice));//订单总优惠金额
		System.out.println("店铺："+orders.getShopId().getStoreName()+"，订单号："+orders.getOrderNumber()+",商品最终总价格："+orders.getGoodsTotalPrice().doubleValue()+",订单金额（包含运费）："+orders.getOrderPrice()+",运费："+orders.getFreight().doubleValue());
		hibernateUtil.save(orders);
		
		vo.setGoodsNum(goodsNum);
		vo.setGoodsTotalPrice(orderPrice);
		vo.setFreight(formate.format(freight));
		vo.setPayable(formate.format(totalPayable));
		vo.setOrderPrice(orders.getOrderPrice());//支付金额
		vo.setBenPrice(formate.format(totalBenPrice));
		vo.setOrderNumber(orders.getOrderNumber());
		vo.setIsCard(isCard);
		vo.setVoList(goodsVoList);
		//获取优惠券
		List<Object> couponList = couponUtil.getCouponByParam(list, orders.getShopId().getId(), param.getUserToken().getUser().getId(), Double.parseDouble(orders.getOrderPrice()));
		vo.setCouponList(couponList);
		vo.setMjList(mjTitleList);
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
			result = aliPayUtil.payByOrdersPc(order);
//			String resultStr = aliPayUtil.payByOrdersPc(order);
//			System.out.println(resultStr);
//			Result res = new Result();
//			res.setResult(1);
//			res.setMessage(resultStr);
//			return ObjectToResult.getResult(res);
		}else if(OrdersConstant.WXPAY.equals(payType)){
			String pay = weiXinPayUtil.payByOrdersPc(order,req);
			Result wxResult = new Result();
			wxResult.setMessage(pay);
			wxResult.setResult(1);
			return wxResult;
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
	public Orders saveOrdersFirst(Parameter param){
		Orders orders = new Orders();
		orders.setCreateTime(new DateStr().toString());
		orders.setCreatePerson(param.getUserToken().getUser().getUsername());
		orders.setOrderStatic("0");//订单状态
		orders.setDeliverStatic("0");//发货状态
		orders.setConfirmStatic("0");//确认收货状态
		orders.setPayStatic("0");//支付状态
		orders.setState("0");
		orders.setOrderNumber(ordersUtil.getOrderNumber());
		orders.setUser(param.getUserToken().getUser());
		hibernateUtil.save(orders);
		return orders;
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
	//@Override
	public Result getTOrdersPayable(Parameter param) throws Exception {
		double payable = 0.00;
		double orderPrice = 0.00;
		double goodsTotalPrice = 0.00;
		double benPrice = 0.00;
		double coinPrice = 0.00;
		double freight = 0.00;
		int totalCoinNumber = 0;
		int goodsTotalNum = 0;
		
		OrderPriceVo msgVo = ordersUtil.checkGoodsEvent(param);//检查所购买的商品的数量是否满足参加活动规则
		
		OrderPriceVo vo = new OrderPriceVo();
		List<Object> voList = new ArrayList<Object>();
		if(msgVo.getMsg()==null){
			Orders orders = saveOrdersFirst(param);
			Map<String, List<ShopGoodsParamVo>> map = ordersUtil.getNewShopAndGoodsParam(param.getKey());
			if(map.size()>1){//存在多个店铺，需要拆分订单
				orders.setIsHasChild("1");
				Object[] key = map.keySet().toArray();
				List<ShopGoodsParamVo> list = new ArrayList<ShopGoodsParamVo>();
				for (Object object : key) {
					list = map.get(object);
					OrderPriceVo orderPriceVo = getOrdersPayable(param, list, orders);
					if(orderPriceVo.getMsg()!=null){
						return ObjectToResult.getResult(orderPriceVo.getMsg());
					}
					payable += Double.parseDouble(orderPriceVo.getPayable());
					orderPrice += Double.parseDouble(orderPriceVo.getOrderPrice());
					goodsTotalPrice += orderPriceVo.getGoodsTotalPrice();
					benPrice += Double.parseDouble(orderPriceVo.getBenPrice());
					freight += Double.parseDouble(orderPriceVo.getFreight());
					goodsTotalNum += orderPriceVo.getGoodsNum();
					if("1".equals(orderPriceVo.getIsCard())){
						vo.setIsCard("1");
						vo.setCard(param.getUserToken().getUser().getCardId());
					}
					voList.add(orderPriceVo);
				}
				if(vo.getIsCard()==null){
					vo.setIsCard("0");
				}
				vo.setBenPrice(String.valueOf(benPrice));
				vo.setCoinNumber(totalCoinNumber);
				vo.setCoinPrice(coinPrice);
				vo.setOrderNumber(orders.getOrderNumber());
				vo.setFreight(String.valueOf(freight));
				vo.setOrderPrice(String.valueOf(orderPrice));
				vo.setPayable(String.valueOf(payable));
				vo.setTotalCoinNumber(totalCoinNumber);
				vo.setGoodsTotalPrice(goodsTotalPrice);
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
				Object object = key[0];
				List<ShopGoodsParamVo> list = map.get(object);
				OrderPriceVo orderPriceVo = getOrdersPayable(param, list, orders);
				if(orderPriceVo.getMsg()!=null){
					return ObjectToResult.getResult(orderPriceVo.getMsg());
				}
				voList.add(orderPriceVo);
				if("1".equals(orderPriceVo.getIsCard())){
					vo.setIsCard("1");
					vo.setCard(param.getUserToken().getUser().getCardId());
				}else{
					vo.setIsCard("0");
				}
				vo.setGoodsNum(orderPriceVo.getGoodsNum());
				vo.setBenPrice(orderPriceVo.getBenPrice());
				vo.setCoinNumber(orderPriceVo.getCoinNumber());
				vo.setCoinPrice(orderPriceVo.getCoinPrice());
				vo.setOrderNumber(orders.getOrderNumber());
				vo.setOrderPrice(orderPriceVo.getOrderPrice());
				vo.setPayable(orderPriceVo.getPayable());
				vo.setTotalCoinNumber(orderPriceVo.getTotalCoinNumber());
				vo.setGoodsTotalPrice(Double.parseDouble(orderPriceVo.getOrderPrice()));
				vo.setVoList(voList);
				vo.setFreight(orderPriceVo.getFreight());
			}
			
			coinUtil.getCoinInfo(param,vo);//获取蜂币
			ordersUtil.getDeductionCoinInfo(param,vo,orders);
			ordersUtil.getDoubleCoinData(param,vo);//参与蜂币翻倍活动
			
			return ObjectToResult.getResult(vo);
		}else{
			return ObjectToResult.getResult(vo.getMsg());
		}
		
	}
	
	/**
	 * 根据店铺进行订单拆分
	 */
	@Override
	public Result getOrdersPayable(Parameter param) throws Exception {
		double payable = 0.00;
		double orderPrice = 0.00;
		double benPrice = 0.00;
		double freight = 0.00;
		double goodsTotalPrice = 0.00;
		int goodsTotalNum = 0;
		
		OrderPriceVo msgVo = ordersUtil.checkGoodsEvent(param);//检查所购买的商品的数量是否满足参加活动规则、及库存
		
		OrderPriceVo vo = new OrderPriceVo();
		List<Object> voList = new ArrayList<Object>();
		if(msgVo.getMsg()==null){
			Orders orders = saveOrdersFirst(param);
			Map<String, List<ShopGoodsParamVo>> map = ordersUtil.getNewShopAndGoodsParam(param.getKey());
			OrderPriceVo orderPriceVo = new OrderPriceVo();
			if(map.size()>1){//存在多个店铺，需要拆分订单
				orders.setIsHasChild("1");
				Object[] key = map.keySet().toArray();
				List<ShopGoodsParamVo> list = new ArrayList<ShopGoodsParamVo>();
				for (Object object : key) {
					list = map.get(object);
					Map<String, List<ShopGoodsParamVo>> supplierMap = ordersUtil.supplierArray(list);
					if(("1".equals(object.toString())&&supplierMap.size()>1)){
						Object[] supplierKey = supplierMap.keySet().toArray();
						List<Object> goodsVoList = new ArrayList<>();
						OrderPriceVo childOrderPriceVo = new OrderPriceVo();
						List<ShopGoodsParamVo> supplierShopGoodsParamVoList = new ArrayList<>();
						for (Object supplier : supplierKey) {
							List<ShopGoodsParamVo> supplierList = supplierMap.get(supplier);
							childOrderPriceVo = getOrdersPayable(param, supplierList, orders);
							if(childOrderPriceVo.getMsg()!=null){
								return ObjectToResult.getResult(childOrderPriceVo.getMsg());
							}
							payable += Double.parseDouble(childOrderPriceVo.getPayable());
							orderPrice += Double.parseDouble(childOrderPriceVo.getOrderPrice());
							benPrice += Double.parseDouble(childOrderPriceVo.getBenPrice());
							//freight += Double.parseDouble(childOrderPriceVo.getFreight());
							goodsTotalNum += childOrderPriceVo.getGoodsNum();
							goodsTotalPrice += childOrderPriceVo.getGoodsTotalPrice();
							if("1".equals(childOrderPriceVo.getIsCard())){
								vo.setIsCard("1");
								vo.setCard(param.getUserToken().getUser().getCardId());
							}else{
								vo.setIsCard("0");
							}
							for (Object obj : childOrderPriceVo.getVoList()) {
								goodsVoList.add(obj);
							}
							supplierShopGoodsParamVoList.addAll(supplierList);
						}
						String province = logisticsUtil.getDefaultUserAddressProvince(param.getUserToken().getUser().getId());
						freight = logisticsUtil.getFreight(province, "1", goodsTotalNum);
						List<Object> couponList = couponUtil.getCouponByParam(supplierShopGoodsParamVoList, "1", param.getUserToken().getUser().getId(), orderPrice);//获取他她乐平台的优惠券
						orderPriceVo.setOrderNumber(orders.getOrderNumber());//只放他们的父订单号
						orderPriceVo.setShopId(childOrderPriceVo.getShopId());
						orderPriceVo.setShopName(childOrderPriceVo.getShopName());
						orderPriceVo.setGoodsNum(goodsTotalNum);
						orderPriceVo.setGoodsTotalPrice(goodsTotalPrice);
						orderPriceVo.setPayable(String.valueOf(payable));
						orderPriceVo.setBenPrice(String.valueOf(benPrice));
						orderPrice += freight;
						orderPriceVo.setOrderPrice(formate.format(orderPrice));
						orderPriceVo.setFreight(String.valueOf(freight));
						orderPriceVo.setCouponList(couponList);
						orderPriceVo.setVoList(goodsVoList);
						voList.add(orderPriceVo);
					}else{
						orderPriceVo = getOrdersPayable(param, list, orders);
						if(orderPriceVo.getMsg()!=null){
							return ObjectToResult.getResult(orderPriceVo.getMsg());
						}
						payable += Double.parseDouble(orderPriceVo.getPayable());
						orderPrice += Double.parseDouble(orderPriceVo.getOrderPrice());
						benPrice += Double.parseDouble(orderPriceVo.getBenPrice());
						freight += Double.parseDouble(orderPriceVo.getFreight());
						goodsTotalNum += orderPriceVo.getGoodsNum();
						goodsTotalPrice += orderPriceVo.getGoodsTotalPrice();
						if("1".equals(orderPriceVo.getIsCard())){
							vo.setIsCard("1");
							vo.setCard(param.getUserToken().getUser().getCardId());
						}else{
							vo.setIsCard("0");
						}
						voList.add(orderPriceVo);
					}
				}
				vo.setOrderNumber(orders.getOrderNumber());
				vo.setBenPrice(String.valueOf(benPrice));
				vo.setFreight(String.valueOf(freight));
				vo.setOrderPrice(String.valueOf(orderPrice));
				vo.setPayable(String.valueOf(payable));
				vo.setGoodsTotalPrice(goodsTotalPrice);
				vo.setVoList(voList);
				orders.setGoodsTotalPrice(new BigDecimal(goodsTotalPrice));
				orders.setFreight(new BigDecimal(vo.getFreight()));
				orders.setPayable(vo.getPayable());
				orders.setOrderPrice(vo.getOrderPrice());
				orders.setBenefitPrice(vo.getBenPrice());
				orders.setGoodsTotal(String.valueOf(goodsTotalNum));
				hibernateUtil.update(orders);
			}else{
				orderPriceVo = new OrderPriceVo();
				Object[] key = map.keySet().toArray();
				Object object = key[0];
				List<ShopGoodsParamVo> list = map.get(object);
				Map<String, List<ShopGoodsParamVo>> supplierMap = ordersUtil.supplierArray(list);//按供应商分组
				if("1".equals(object.toString())&&supplierMap.size()>1){//如果是他她乐自营且存在多个供应商需要进行供应商拆单
					orders.setIsHasChild("1");
					Object[] supplierKey = supplierMap.keySet().toArray();
					List<Object> goodsVoList = new ArrayList<>();
					Set<Object> couponList = new HashSet<>();
					OrderPriceVo childOrderPriceVo = null;
					for (Object supplier : supplierKey) {
						List<ShopGoodsParamVo> supplierList = supplierMap.get(supplier);
						childOrderPriceVo = getOrdersPayable(param, supplierList, orders);
						if(childOrderPriceVo.getMsg()!=null){
							return ObjectToResult.getResult(childOrderPriceVo.getMsg());
						}
						payable += Double.parseDouble(childOrderPriceVo.getPayable());
						orderPrice += Double.parseDouble(childOrderPriceVo.getOrderPrice());
						benPrice += Double.parseDouble(childOrderPriceVo.getBenPrice());
						//freight += Double.parseDouble(childOrderPriceVo.getFreight());
						goodsTotalNum += childOrderPriceVo.getGoodsNum();
						goodsTotalPrice += childOrderPriceVo.getGoodsTotalPrice();
						if("1".equals(childOrderPriceVo.getIsCard())){
							vo.setIsCard("1");
							vo.setCard(param.getUserToken().getUser().getCardId());
						}else{
							vo.setIsCard("0");
						}
						for (Object obj : childOrderPriceVo.getVoList()) {
							goodsVoList.add(obj);
						}
						for (Object obj : childOrderPriceVo.getCouponList()) {
							couponList.add(obj);
						}
					}
					String province = logisticsUtil.getDefaultUserAddressProvince(param.getUserToken().getUser().getId());
					freight = logisticsUtil.getFreight(province, "1", goodsTotalNum);
					orderPriceVo.setOrderNumber(orders.getOrderNumber());//为了结构一致，只做展示，只放他们的父订单号
					orderPriceVo.setShopId(childOrderPriceVo.getShopId());
					orderPriceVo.setShopName(childOrderPriceVo.getShopName());
					orderPriceVo.setGoodsNum(goodsTotalNum);
					orderPriceVo.setGoodsTotalPrice(goodsTotalPrice);
					orderPriceVo.setPayable(String.valueOf(payable));
					orderPriceVo.setBenPrice(String.valueOf(benPrice));
					orderPrice += freight;
					orderPriceVo.setOrderPrice(formate.format(orderPrice));
					orderPriceVo.setFreight(String.valueOf(freight));
					orderPriceVo.setCouponList(new ArrayList<>(couponList));
					orderPriceVo.setVoList(goodsVoList);
					voList.add(orderPriceVo);
					BusinessStore store = (BusinessStore) hibernateUtil.find(BusinessStore.class, orderPriceVo.getShopId());
					orders.setShopId(store);
				}else{
					orders.setIsHasChild("0");
					orderPriceVo = getOrdersPayable(param, list, orders);
					goodsTotalNum = orderPriceVo.getGoodsNum();
					if(orderPriceVo.getMsg()!=null){
						return ObjectToResult.getResult(orderPriceVo.getMsg());
					}
					voList.add(orderPriceVo);
					if("1".equals(orderPriceVo.getIsCard())){
						vo.setIsCard("1");
						vo.setCard(param.getUserToken().getUser().getCardId());
					}else{
						vo.setIsCard("0");
					}
				}
				vo.setOrderNumber(orders.getOrderNumber());
				vo.setBenPrice(orderPriceVo.getBenPrice());
				vo.setPayable(orderPriceVo.getPayable());
				vo.setGoodsTotalPrice(orderPriceVo.getGoodsTotalPrice());
				vo.setOrderPrice(orderPriceVo.getOrderPrice());
				vo.setFreight(orderPriceVo.getFreight());
				vo.setVoList(voList);
				
				orders.setGoodsTotalPrice(new BigDecimal(vo.getGoodsTotalPrice()));
				orders.setFreight(new BigDecimal(vo.getFreight()));
				orders.setPayable(vo.getPayable());
				orders.setOrderPrice(vo.getOrderPrice());
				orders.setBenefitPrice(vo.getBenPrice());
				orders.setGoodsTotal(String.valueOf(goodsTotalNum));
				hibernateUtil.update(orders);
			}
			
			coinUtil.getCoinInfo(param,vo);//获取蜂币
			ordersUtil.getDeductionCoinInfo(param,vo,orders);
			ordersUtil.getDoubleCoinData(param,vo);//参与蜂币翻倍活动
			
			return ObjectToResult.getResult(vo);
		}else{
			return ObjectToResult.getResult(msgVo.getMsg());
		}
		
	}

	/**
	 * 重新选择地址后计算订单运费及订单价格
	 */
	@Override
	public Result getOrdersFreightByOrderNumber(Parameter param)
			throws Exception {
		OrderPriceVo vo = new OrderPriceVo();
		List<Object> objList = new ArrayList<>();
		Object obj = hibernateUtil.hqlFirst("from Orders where state='0' and orderNumber='"+param.getId()+"'");
		double freight = 0.00;
		double totalFreight = 0.00;
		BigDecimal goodsTotalPriceDec = null;
		BigDecimal freightDec = null;
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
				totalFreight = freight;
				OrderPriceVo notChildVo = new OrderPriceVo();
				notChildVo.setOrderNumber(order.getOrderNumber());
				notChildVo.setFreight(String.valueOf(freight));
				goodsTotalPriceDec = order.getGoodsTotalPrice();
				freightDec = new BigDecimal(String.valueOf(freight));
				notChildVo.setOrderPrice(formate.format(goodsTotalPriceDec.add(freightDec)));
				objList.add(notChildVo);
				vo.setVoList(objList);
			}else{
				List<Object> list = hibernateUtil.hql("from Orders where state='0' and parentOrdersId='"+order.getId()+"'");
				Map<String,List<Orders>> map = new HashMap<>();
				for (Object object : list) {
					Orders childOrder = (Orders) object;
					List<Orders> ordersList = map.get(childOrder.getShopId().getId());
					if(ordersList==null){
						ordersList = new ArrayList<>();
						ordersList.add(childOrder);
						map.put(childOrder.getShopId().getId(), ordersList);
					}else{
						ordersList.add(childOrder);
					}
				}
				Object[] key = map.keySet().toArray();
				for (Object object : key) {
					List<Orders> ordersList = map.get(object);
					int goodsNum = 0;
					double goodsTotalPrice = 0.00;
					String shopId = "";
					for (Orders orders : ordersList) {
						shopId = orders.getShopId().getId();
						goodsNum += Integer.parseInt(orders.getGoodsTotal());
						goodsTotalPrice += orders.getGoodsTotalPrice().doubleValue();
					}
					freight = logisticsUtil.getFreight(provinceName, shopId, goodsNum);
					totalFreight += freight;
					OrderPriceVo childVo = new OrderPriceVo();
					childVo.setFreight(String.valueOf(freight));
					childVo.setShopId(shopId);
					goodsTotalPriceDec = new BigDecimal(goodsTotalPrice);
					freightDec = new BigDecimal(String.valueOf(freight));
					childVo.setOrderPrice(formate.format(goodsTotalPriceDec.add(freightDec)));
					objList.add(childVo);
					vo.setVoList(objList);
				}
				
			}
			goodsTotalPriceDec = order.getGoodsTotalPrice();
			freightDec = new BigDecimal(String.valueOf(totalFreight));
			vo.setOrderPrice(formate.format(goodsTotalPriceDec.add(freightDec)));
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

	/**
	 * 查询回收站订单列表
	 */
	@Override
	public Result getRecoveryOrdersPage(Parameter param) throws Exception {
		String whereStr = param.getWhereStr();
		if(whereStr==null){
			whereStr = "";
		}
		Page page =  hibernateUtil.hqlPage(null,"from Orders where isHasChild!='1' and state='2' and user.id='"+param.getUserToken().getUser().getId()+"'"+whereStr+" order by orderTime desc",param.getPageNum(),param.getPageSize());
		List<?> ordersList = page.getObjList();
		PcOrderVo vo = new PcOrderVo();
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < ordersList.size(); i++) {
			Orders order = (Orders) ordersList.get(i);
			List<Object> ordersDetailList = new ArrayList<Object>();
			ordersDetailList = hibernateUtil.hql("from OrdersDetail where orders.id='"+order.getId()+"'");
			vo = ordersUtil.transformOrder(order,ordersDetailList);
			list.add(vo);
		}
		page.setObjList(null);
		page.setObjList(list);
		return ObjectToResult.getResult(page);
	}

	/**
	 * 在结算页面中使用优惠券后该订单小计金额和总计金额
	 */
	@Override
	public Result useCoupon(Parameter param) throws Exception {
		UseCoponOrderPriceVo vo = new UseCoponOrderPriceVo();
		Coupon coupon = new Coupon();
		Object object = param.getObj();//优惠券及带的商品信息
		Map<String,List<ShopGoodsParamVo>> map = (Map) object;
		Set<String> key = map.keySet();
		for (String couponId : key) {
			coupon = (Coupon) hibernateUtil.find(Coupon.class, couponId);
		}
		BigDecimal facevalue = new BigDecimal(String.valueOf(coupon.getFaceValue()));
		
		String subOrderId = param.getKey();//子订单ID
		Orders subOrder = (Orders) hibernateUtil.find(Orders.class, subOrderId);
		BigDecimal subOrderPrice = new BigDecimal(subOrder.getOrderPrice());
		vo.setSubOrderPrice(subOrderPrice.multiply(facevalue));
		
		String parentOrderId = param.getParentId().toString();//父订单ID
		Orders parentOrder = (Orders) hibernateUtil.find(Orders.class, parentOrderId);
		BigDecimal parentOrderPrice = new BigDecimal(parentOrder.getOrderPrice());
		vo.setParentOrderPrice(parentOrderPrice.multiply(facevalue));
		return ObjectToResult.getResult(vo);
	}

	/**
	 * 去支付
	 */
	@Override
	public Result toPay(Parameter param) throws Exception {
		Orders orders = (Orders) hibernateUtil.find(Orders.class, param.getId()+"");
		PcOrderVo pcOrderVo = new PcOrderVo();
		pcOrderVo.setConsigeeName(orders.getConsignee());
		pcOrderVo.setTel(orders.getConsigneeTel());
		pcOrderVo.setAddress(orders.getAddress());
		pcOrderVo.setId(orders.getId());
		pcOrderVo.setOrderNumber(orders.getOrderNumber());
		pcOrderVo.setOrderPrice(orders.getOrderPrice());
		List<Object> objList = hibernateUtil.hql("from OrdersDetail where state='1' and orders.id='"+orders.getId()+"'");
		List<Object> titleList = new ArrayList<>();
		for (Object object : objList) {
			OrdersDetail detail = (OrdersDetail) object;
			titleList.add(detail.getGoodsTitle());
		}
		pcOrderVo.setDetailVoList(titleList);
		return ObjectToResult.getResult(pcOrderVo);
	}
	
}

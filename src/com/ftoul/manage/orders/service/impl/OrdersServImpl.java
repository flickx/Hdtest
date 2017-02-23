package com.ftoul.manage.orders.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.OrdersConstant;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.logistics.vo.LogisticsCompanyVo;
import com.ftoul.manage.orders.service.OrdersServ;
import com.ftoul.manage.orders.vo.GoodsVo;
import com.ftoul.manage.orders.vo.OrderDetailVo;
import com.ftoul.manage.orders.vo.OrdersPayVo;
import com.ftoul.manage.orders.vo.OrdersVo;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.Orders;
import com.ftoul.po.OrdersDetail;
import com.ftoul.po.OrdersPay;
import com.ftoul.po.OrdersSet;
import com.ftoul.po.UserOpLog;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.orders.OrdersUtil;
import com.ftoul.web.vo.ManyVsOneVo;

@Service("OrdersServImpl")
public class OrdersServImpl implements OrdersServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	OrdersUtil ordersUtil;
	/**
	 * 删除订单
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result deleteOrders(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update Orders set state = '0' , orderStatic = '7' where id = '"+param.getId()+"'");
		updateGoodsParam(param.getId().toString(),"delete");
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
			GoodsParam goodsParam = ordersDetail.getGoodsParam();
			if("add".equals(flag)){
				goodsParam.setStock(String.valueOf(Integer.parseInt(goodsParam.getStock())-1));
				goodsParam.setSaleNumber(goodsParam.getSaleNumber()+1);
			}else if("cancel".equals(flag)){
				goodsParam.setStock(String.valueOf(Integer.parseInt(goodsParam.getStock())+1));
				goodsParam.setSaleNumber(goodsParam.getSaleNumber()-1);
			}
			
			hibernateUtil.update(goodsParam);
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
			ordersList =  hibernateUtil.hql("from Orders where payStatic = '0' and user.id="+param.getId());
		}else if(OrdersConstant.NOT_DELIVER.equals(key)){
			ordersList =  hibernateUtil.hql("from Orders where deliverStatic = '0' and user.id="+param.getId());
		}else if(OrdersConstant.NOT_TASK_DELIVER.equals(key)){
			ordersList =  hibernateUtil.hql("from Orders where confirmStatic = '0' and user.id="+param.getId());
		}else if(OrdersConstant.NOT_EVALUATE.equals(key)){
			ordersList =  hibernateUtil.hql("from Orders where feedback is NULL and user.id="+param.getId());
		}else{
			ordersList =  hibernateUtil.hql("from Orders where user.id="+param.getId());
		}
		
		return ObjectToResult.getResult(ordersList);
	}
	/**
	 * 获取订单
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrdersByOrdersId(Parameter param) throws Exception {
		Orders orders =  (Orders) hibernateUtil.find(Orders.class, param.getId()+"");
		List<Object> ordersDetailList = new ArrayList<Object>();
		GoodsParam gp = new GoodsParam();
		OrdersDetail od = new OrdersDetail();
		Goods goods = new Goods();
		ordersDetailList = hibernateUtil.hql("from OrdersDetail where orders.id="+orders.getId());
		for (int j = 0; j < ordersDetailList.size(); j++) {
			od = (OrdersDetail) ordersDetailList.get(j);
			gp = (GoodsParam) hibernateUtil.find(GoodsParam.class, od.getGoodsParam());
			goods = (Goods) hibernateUtil.find(Goods.class, gp.getGoods());
			od.setGoodsParam(gp);
			gp.setGoods(goods);
		}
		ManyVsOneVo vo = transformObject(orders,ordersDetailList);
		return ObjectToResult.getResult(vo);
	}
	
	/**
	 * 获取订单支付列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrdersPayListPage(Parameter param) throws Exception {
		ordersUtil.autoCancelOrders(param);
		String whereStr = param.getWhereStr();
		String hql;
		if(whereStr!=null){
			hql = "from OrdersPay where state = '1' "+whereStr+" order by createTime desc";
		}else{
			hql = "from OrdersPay where state = '1' order by createTime desc";
		}
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		List<Object> list = page.getObjList();
		List<Object> ordersPayList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			OrdersPay pay = (OrdersPay) list.get(i);
			OrdersPayVo vo = new OrdersPayVo();
			vo.setOrderNumber(pay.getOrders().getOrderNumber());
			vo.setPayPrice(pay.getPayPrice());
			if("1".equals(pay.getPayStatic())){
				vo.setPayStatic("支付成功");
			}else{
				vo.setPayStatic("支付失败");
			}
			vo.setPayTime(pay.getOrders().getPayTime());
			if(OrdersConstant.CHINAPAY.equals(pay.getPayType())){
				vo.setPayType("银联支付");
			}else if(OrdersConstant.ALIPAY.equals(pay.getPayType())){
				vo.setPayType("支付宝支付");
			}else if(OrdersConstant.WXPAY.equals(pay.getPayType())){
				vo.setPayType("微信支付");
			}else if(OrdersConstant.ALIQBPAY.equals(pay.getPayType())){
				vo.setPayType("支付宝钱包支付");
			}
			vo.setSerialNumber(pay.getSerialNumber());
			ordersPayList.add(vo);
		}
		page.setObjList(null);
		page.setObjList(ordersPayList);
		return ObjectToResult.getResult(page);
	}
	
	/**
	 * 后台订单列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrderListPage(Parameter param) throws Exception {
		ordersUtil.autoCancelOrders(param);
		String queryStr = param.getWhereStr();
		String hql;
		if(queryStr!=null){
			hql = " from Orders where orderStatic!='0' "+queryStr+" order by orderTime desc";
		}else{
			hql = " from Orders where orderStatic!='0' order by orderTime desc";
		}
		
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		List<Object> ordersList = page.getObjList();
		List<Object> voList = new ArrayList<Object>();
		for (Object object : ordersList) {
			Orders orders = (Orders) object;
			OrdersVo ordersVo = new OrdersVo();
			ordersVo.setId(orders.getId());
			ordersVo.setOrderNumber(orders.getOrderNumber());
			ordersVo.setGoodsTotal(orders.getGoodsTotal());
			ordersVo.setOrderTime(orders.getOrderTime());
			ordersVo.setOrderStatic(ordersUtil.getState(orders.getOrderStatic()));
			ordersVo.setOrderPrice(orders.getOrderPrice());
			ordersVo.setPayable(orders.getPayable());
			hql = " from OrdersDetail where orders.id='"+orders.getId()+"'";
			List<Object> ordersDetailList = hibernateUtil.hql(hql);
			List<String> ordersDetailVoList = new ArrayList<String>();
			for (Object object2 : ordersDetailList) {
				OrdersDetail ordersDetail = (OrdersDetail) object2;
				String goodsPicSrc = ordersDetail.getGoodsParam().getGoods().getPicSrc();
				ordersDetailVoList.add(goodsPicSrc);
			}
			ordersVo.setGoodPicSrcs(ordersDetailVoList);
			ordersVo.setUserName(orders.getUser().getUsername());
			ordersVo.setTel(orders.getConsigneeTel());
			ordersVo.setConginee(orders.getConsignee());
			ordersVo.setAddress(orders.getAddress());
			if(orders.getFreight()!=null){
				ordersVo.setFreight(orders.getFreight().toString());
			}
			if(orders.getShopId()!=null){
				ordersVo.setShopName(orders.getShopId().getStoreName());
			}
			if("1".equals(orders.getIsHasChild())){
				ordersVo.setIsHasChild("是");
			}
			Orders parent = (Orders) hibernateUtil.find(Orders.class, orders.getParentOrdersId()+"");
			if(parent!=null){
				ordersVo.setParentOrderNumber(parent.getOrderNumber());
			}
			
			voList.add(ordersVo);
		}
		page.setObjList(voList);
		
		return ObjectToResult.getResult(page);
	}
	
	/**
	 * 后台查看订单详情
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrderDetailVoById(Parameter param) throws Exception {
		OrderDetailVo orderDetailVo = new OrderDetailVo();
		Orders orders = (Orders) hibernateUtil.find(Orders.class, param.getId()+"");
		orderDetailVo.setId(orders.getId());
		orderDetailVo.setOrderNumber(orders.getOrderNumber());
		orderDetailVo.setOrderTime(orders.getOrderTime());
		orderDetailVo.setOrderStatic(ordersUtil.getState(orders.getOrderStatic()));
		if(OrdersConstant.CHINAPAY.equals(orders.getPayType())){
			orderDetailVo.setPayType("银联支付");
		}else if(OrdersConstant.ALIPAY.equals(orders.getPayType())){
			orderDetailVo.setPayType("支付宝支付");
		}else if(OrdersConstant.WXPAY.equals(orders.getPayType())){
			orderDetailVo.setPayType("微信支付");
		}else if(OrdersConstant.ALIQBPAY.equals(orders.getPayType())){
			orderDetailVo.setPayType("支付宝钱包支付");
		}else if(OrdersConstant.FBPAY.equals(orders.getPayType())){
			orderDetailVo.setPayType("全蜂币支付");
		}
		OrdersPay pay = (OrdersPay) hibernateUtil.hqlFirst("from OrdersPay where orders.id='"+orders.getId()+"'");
		if(pay!=null){
			orderDetailVo.setSerilNumber(pay.getSerialNumber());
		}else{
			orderDetailVo.setSerilNumber("无");
		}
		orderDetailVo.setOrderPrice(orders.getOrderPrice());
		if(orders.getBeeCoins()!=null){
			orderDetailVo.setBeeCoins(orders.getBeeCoins());
			orderDetailVo.setCoinPrice(orders.getCoinPrice());
		}else{
			orderDetailVo.setBeeCoins("无");
		}
		if(orders.getParentOrdersId()!=null){
			orderDetailVo.setIsHasParentOrder("有父订单");
		}else{
			orderDetailVo.setIsHasParentOrder("无父订单");
		}
		orderDetailVo.setPayAble(orders.getPayable());
		orderDetailVo.setBenefitMoney(orders.getBenefitPrice());//订单优惠金额
		orderDetailVo.setModifyPrice(orders.getBenefitPrice());//订单修改金额
		if(orders.getCustomsClearanceStatic()!=null){
			if("1".equals(orders.getCustomsClearanceStatic())){
				orderDetailVo.setClearCustomState("清关中");
			}else{
				orderDetailVo.setClearCustomState("已清关");
			}
		}
		if(orders.getLogisticsCompany()!=null){
			orderDetailVo.setCompany(orders.getLogisticsCompany().getName());
		}else{
			orderDetailVo.setCompany("无");
		}
		if(orders.getFreight()!=null){
			orderDetailVo.setFreight(orders.getFreight().toString());
		}
		
		orderDetailVo.setTel(orders.getConsigneeTel());
		orderDetailVo.setConsignee(orders.getConsignee());
		orderDetailVo.setAddress(orders.getAddress());
		if(orders.getUser().getCardId()!=null){
			orderDetailVo.setCard(orders.getUser().getCardId());
		}else{
			orderDetailVo.setCard("无");
		}
		orderDetailVo.setMobil(orders.getUser().getMobil());
		if(orders.getOdd()!=null){
			orderDetailVo.setOdd(orders.getOdd());
		}else{
			orderDetailVo.setOdd("无");
		}
		orderDetailVo.setFeedBack(orders.getFeedback());
		List<Object> detailList = hibernateUtil.hql("from OrdersDetail where orders.id = '"+orders.getId()+"'");
		OrdersDetail ordersDetail = new OrdersDetail();
		List<GoodsVo> goodsVoList = new ArrayList<GoodsVo>();
		for (int i = 0; i < detailList.size(); i++) {
			GoodsVo goodsVo = new GoodsVo();
			ordersDetail = (OrdersDetail) detailList.get(i);
			goodsVo.setNumber(ordersDetail.getNumber());
			goodsVo.setPrice(ordersDetail.getGoodsParam().getPrice());
			goodsVo.setCurrentPrice(ordersDetail.getPrice());
			//goodsVo.setTotalPrice(String.valueOf(Double.parseDouble(ordersDetail.getNumber())*Double.parseDouble(ordersDetail.getGoodsParam().getPrice())));//总价
			goodsVo.setTotalPrice(ordersDetail.getTotalPrice().toString());
			goodsVo.setCostPrice(ordersDetail.getGoodsParam().getCostprice());
			//判断是否有供货商
			if(null!=ordersDetail.getGoodsParam().getGoods().getGoodsCanal()){
				goodsVo.setCanal(ordersDetail.getGoodsParam().getGoods().getGoodsCanal().getName());
			}else{
				goodsVo.setCanal("无");
			}
			goodsVo.setGoodsId(ordersDetail.getGoodsParam().getGoods().getId());
			goodsVo.setGoodsName(ordersDetail.getGoodsParam().getGoods().getTitle());
			goodsVo.setGoodsPicSrcs(ordersDetail.getGoodsParam().getGoods().getPicSrc());
			goodsVo.setMarketPrice(ordersDetail.getGoodsParam().getMarketPrice());
			goodsVo.setParam(ordersDetail.getGoodsParam().getParamName());
			goodsVoList.add(goodsVo);
		}
		orderDetailVo.setGoodsVoList(goodsVoList);
		return ObjectToResult.getResult(orderDetailVo);
	}
	
	public ManyVsOneVo transformObject(Orders order,List<Object> ordersDetailList){
		ManyVsOneVo vo = new ManyVsOneVo();
		vo.setObj(order);
		vo.setList(ordersDetailList);
		return vo;
	}
	
	/**
	 * 设置清关状态
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result modifyClearCustom(Parameter parameter)
			throws Exception {
		String hql = "update Orders set customsClearanceStatic ='"+parameter.getKey()+"' where id = '"+parameter.getId().toString()+"'";
		int result = hibernateUtil.execHql(hql);
		UserOpLog log = new UserOpLog();
		log.setCreateTime(new DateStr().toString());
		log.setOpTime(new DateStr().toString());
		log.setOpTypeCode(parameter.getKey());
		log.setOpTypeName("设置清关状态");
		log.setState("1");
		log.setOrders((Orders) hibernateUtil.find(Orders.class, parameter.getId().toString()+""));
		hibernateUtil.save(log);
		return ObjectToResult.getResult(result);
	}

	/**
	 * 一键发货
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveOrderLogisticsCompany(Parameter parameter)
			throws Exception {
		Object obj = parameter.getObj();
		LogisticsCompanyVo logisticsCompanyVo = (LogisticsCompanyVo) Common.jsonToBean(obj.toString(), LogisticsCompanyVo.class);
		String hql = "update Orders set logisticsCompany.id = '"+logisticsCompanyVo.getLogisticsCompanyID()+"', odd = '"+logisticsCompanyVo.getOdd()+"', logInfo = '"+logisticsCompanyVo.getLogInfo()+"', deliverStatic = '1', orderStatic = '4' where id = '"+logisticsCompanyVo.getId()+"'";
		int result = hibernateUtil.execHql(hql);
		UserOpLog log = new UserOpLog();
		log.setCreateTime(new DateStr().toString());
		log.setOpTime(new DateStr().toString());
		log.setOpTypeCode(OrdersConstant.YES_DELIVER);
		log.setOpTypeName("已发货");
		log.setState("1");
		log.setOrders((Orders) hibernateUtil.find(Orders.class, logisticsCompanyVo.getId()+""));
		hibernateUtil.save(log);
		return ObjectToResult.getResult(result);
	}

	/**
	 * 订单操作日志
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrderOpLog(Parameter param) throws Exception {
		String hql = "from UserOpLog where orders.id ='"+param.getId()+"' order by opTime desc";
		List<Object> logList = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(logList);
	}

	/**
	 * 修改订单状态
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveOrderStatic(Parameter parameter) throws Exception {
		String hql = "update Orders set orderStatic ='"+parameter.getKey()+"' where id='"+parameter.getId()+"'";
		int result = hibernateUtil.execHql(hql);
		Orders orders = (Orders) hibernateUtil.find(Orders.class, parameter.getId()+"");
		if("1".equals(orders.getIsHasChild())){
			List<Object> childList = hibernateUtil.hql("from Orders where parentOrdersId='"+orders.getId()+"'");
			for (Object object : childList) {
				Orders child = (Orders) object;
				child.setOrderStatic("2");
				child.setModifyTime(new DateStr().toString());
				child.setModifyPerson(parameter.getManageToken().getLoginUser().getLoginName());
				hibernateUtil.update(child);
			}
		}
		UserOpLog log = new UserOpLog();
		log.setCreateTime(new DateStr().toString());
		log.setOpTime(new DateStr().toString());
		log.setOpTypeCode(parameter.getKey());
		log.setOpTypeName("修改订单状态");
		log.setOpReason("后台修改订单状态");
		log.setState("1");
		log.setOrders(orders);
		hibernateUtil.save(log);
		return ObjectToResult.getResult(result);
	}
	
	/**
	 * 保存订单优惠金额
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveBenefitPrice(Parameter parameter) throws Exception {
		String keys = parameter.getKey();
		String[] key = keys.split(";");
		Orders orders = (Orders) hibernateUtil.find(Orders.class, parameter.getId()+"");
		BigDecimal payableDec = new BigDecimal(orders.getPayable());
		BigDecimal benDec = new BigDecimal(key[0]);
		String orderPrice = payableDec.subtract(benDec).toString();
		if(payableDec.subtract(benDec).doubleValue()<0){
			throw new Exception("优惠金额已经大于原价了");
		}
		String hql;
		if(key[1]==null){
			hql = "update Orders set benefitPrice ='"+key[0]+"',orderPrice='"+orderPrice+"' where id='"+parameter.getId()+"'";
		}else{
			hql = "update Orders set benefitPrice ='"+key[0]+"',benefitReason ='"+key[1]+"',orderPrice='"+orderPrice+"' where id='"+parameter.getId()+"'";
		}
		int result = hibernateUtil.execHql(hql);
		UserOpLog log = new UserOpLog();
		log.setCreateTime(new DateStr().toString());
		log.setOpTime(new DateStr().toString());
		log.setOpTypeCode(parameter.getKey());
		log.setOpTypeName("修改订单价格");
		log.setOpReason("后台修改订单价格,优惠金额为"+key[0]);
		log.setState("1");
		log.setOrders(orders);
		hibernateUtil.save(log);
		return ObjectToResult.getResult(result);
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
	 * 获取需出库的订单列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getdeliveryListPage(Parameter parameter) throws Exception {
		String whereStr = parameter.getWhereStr();
		String hql = "from Orders where deliverStatic='0' "+whereStr;
		Page page = hibernateUtil.hqlPage(null, hql, parameter.getPageNum(), parameter.getPageSize());
		List<Object> ordersList = page.getObjList();
		List<Object> voList = new ArrayList<Object>();
		for (Object object : ordersList) {
			Orders orders = (Orders) object;
			OrdersVo ordersVo = new OrdersVo();
			ordersVo.setId(orders.getId());
			ordersVo.setOrderNumber(orders.getOrderNumber());
			ordersVo.setGoodsTotal(orders.getGoodsTotal());
			ordersVo.setOrderTime(orders.getOrderTime());
			ordersVo.setOrderStatic(orders.getOrderStatic());
			ordersVo.setOrderPrice(orders.getOrderPrice());
			ordersVo.setPayable(orders.getPayable());
			hql = " from OrdersDetail where orders.id='"+orders.getId()+"'";
			List<Object> ordersDetailList = hibernateUtil.hql(hql);
			List<String> ordersDetailVoList = new ArrayList<String>();
			for (Object object2 : ordersDetailList) {
				OrdersDetail ordersDetail = (OrdersDetail) object2;
				String goodsPicSrc = ordersDetail.getGoodsParam().getGoods().getPicSrc();
				ordersDetailVoList.add(goodsPicSrc);
			}
			ordersVo.setGoodPicSrcs(ordersDetailVoList);
//			if(orders.getUserAddress()!=null){
//				ordersVo.setUserName(orders.getUserAddress().getUser().getUsername());
//				ordersVo.setTel(orders.getUserAddress().getTel());
//				ordersVo.setConginee(orders.getUserAddress().getConsignee());
//			}
			ordersVo.setUserName(orders.getUser().getUsername());
			ordersVo.setTel(orders.getConsigneeTel());
			ordersVo.setConginee(orders.getConsignee());
			ordersVo.setAddress(orders.getAddress());
			voList.add(ordersVo);
		}
		page.setObjList(voList);
		
		return ObjectToResult.getResult(page);
	}

	/**
	 * 获取订单设置信息
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrdersSet(Parameter parameter) throws Exception {
		List<Object> list = hibernateUtil.hql("from OrdersSet");
		OrdersSet set = new OrdersSet();
		for (Object object : list) {
			set = (OrdersSet) object;
		}
		return ObjectToResult.getResult(set);
	}

	/**
	 * 修改或保存订单设置信息
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveOrdersSet(Parameter parameter) throws Exception {
		OrdersSet set = (OrdersSet) JSONObject.toBean((JSONObject) parameter.getObj(),OrdersSet.class);
		Object res;
		if(Common.isNull(set.getId())){
			set.setCreateTime(new DateStr().toString());
			set.setState("1");
			res = hibernateUtil.save(set);
		}else{
			set.setModifyTime(new DateStr().toString());
			res = hibernateUtil.update(set);
		}
		return ObjectToResult.getResult(res);
	}

	/**
	 * 根据主键获取订单
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrdersById(Parameter param) throws Exception {
		Orders orders = (Orders) hibernateUtil.find(Orders.class, param.getId()+"");
		return ObjectToResult.getResult(orders);
	}

	/**
	 * 订单导出信息
	 */
	@Override
	public Result getOrdersDetailByOrdersId(Parameter param) throws Exception {
		Result result = new Result();
		String hql;
		if(param.getWhereStr()!=null){
			hql = "from OrdersDetail where state = '1' "+param.getWhereStr()+" order by orders.orderTime desc";
		}else{
			hql = "from OrdersDetail where state = '1' order by orders.orderTime desc";
		}
		List<Object> list = hibernateUtil.hql(hql);
		List<Object> reportDataList = new ArrayList<Object>();
		Object[] vo = null;
		OrdersPay pay = null;
		for (int i = 0; i <list.size(); i++) {
			OrdersDetail od = (OrdersDetail) list.get(i);
			vo = new Object[28];
			vo[0] = od.getOrders().getOrderNumber();
			vo[1] = od.getOrders().getOrderTime();
			vo[2] = ordersUtil.getState(od.getOrders().getOrderStatic());
			vo[3] = od.getOrders().getUser().getUsername();
			vo[4] = od.getOrders().getConsignee();
			vo[5] = od.getOrders().getConsigneeTel();
			
			vo[6] = od.getOrders().getAddress();
			if(od.getOrders().getFreight()!=null){
				vo[7] = od.getOrders().getFreight().doubleValue();//运费
			}
			vo[8] = od.getGoodsParam().getGoods().getTitle();
			vo[9] = od.getGoodsParam().getGoods().getSkuCode();
			vo[10] = "";//商品编号
			
			vo[11] = od.getGoodsParam().getParamName();
			if("1".equals(od.getOrders().getPayType())){
				vo[12] = "银联支付";
			}else if("2".equals(od.getOrders().getPayType())){
				vo[12] = "支付宝支付";
			}else if("3".equals(od.getOrders().getPayType())){
				vo[12] = "微信支付";
			}else if("4".equals(od.getOrders().getPayType())){
				vo[12] = "支付宝支付";
			}
			vo[13] = od.getGoodsParam().getCostprice();
			vo[14] = od.getNumber();
			vo[15] = od.getPrice();
			vo[16] = od.getTotalPrice().doubleValue();
			vo[17] = od.getOrders().getPayable();
			
			vo[18] = od.getOrders().getOrderPrice();
			vo[19] = od.getOrders().getBenefitPrice();
			vo[20] = od.getOrders().getBeeCoins();
			vo[21] = od.getOrders().getCoinPrice();
			hql = "from OrdersPay where orders.id='"+od.getOrders().getId()+"'";
			pay = (OrdersPay) hibernateUtil.hqlFirst(hql);
			if(pay!=null){
				vo[22] = pay.getSerialNumber();
			}else{
				vo[22] = "";
			}
			if(od.getGoodsParam().getCostprice()!=null&&!"".equals(od.getGoodsParam().getCostprice())){
				double totalCostPrice = Double.parseDouble(od.getGoodsParam().getCostprice())*Integer.parseInt(od.getNumber());
				vo[23] = String.valueOf(totalCostPrice);
			}else{
				vo[23] = "";
			}
			
			if(od.getGoodsParam().getGoods().getGoodsCanal()!=null){
				vo[24] = od.getGoodsParam().getGoods().getGoodsCanal().getName();
				vo[25] = od.getGoodsParam().getGoods().getGoodsCanal().getBalanceMode();
			}else{
				vo[24] = "";
				vo[25] = "";
			}
			
			vo[26] = od.getGoodsParam().getGoods().getDeductionrate();
			vo[27] = od.getOrders().getFeedback();
			
			reportDataList.add(vo);
		}
		result.setObj(reportDataList);
		return result;
	}

	@Override
	public Result getSendGoodsListPage(Parameter param) throws Exception {
		
		String hql = " from Orders where state = '1' and orderStatic = '2'";
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		
		List<Object> ordersList = page.getObjList();
		List<Object> voList = new ArrayList<Object>();
		for (Object object : ordersList) {
			Orders orders = (Orders) object;
			OrdersVo ordersVo = new OrdersVo();
			ordersVo.setId(orders.getId());
			ordersVo.setOrderNumber(orders.getOrderNumber());
			ordersVo.setGoodsTotal(orders.getGoodsTotal());
			ordersVo.setOrderTime(orders.getOrderTime());
			ordersVo.setOrderStatic(orders.getOrderStatic());
			ordersVo.setOrderPrice(orders.getOrderPrice());
			ordersVo.setPayable(orders.getPayable());
			hql = " from OrdersDetail where orders.id='"+orders.getId()+"'";
			List<Object> ordersDetailList = hibernateUtil.hql(hql);
			List<String> ordersDetailVoList = new ArrayList<String>();
			for (Object object2 : ordersDetailList) {
				OrdersDetail ordersDetail = (OrdersDetail) object2;
				String goodsPicSrc = ordersDetail.getGoodsParam().getGoods().getPicSrc();
				ordersDetailVoList.add(goodsPicSrc);
			}
			ordersVo.setGoodPicSrcs(ordersDetailVoList);
//			if(orders.getUserAddress()!=null){
//				ordersVo.setUserName(orders.getUserAddress().getUser().getUsername());
//				ordersVo.setTel(orders.getUserAddress().getTel());
//				ordersVo.setConginee(orders.getUserAddress().getConsignee());
//			}
			ordersVo.setUserName(orders.getUser().getUsername());
			ordersVo.setTel(orders.getConsigneeTel());
			ordersVo.setConginee(orders.getConsignee());
			voList.add(ordersVo);
		}
		page.setObjList(voList);
		
		return ObjectToResult.getResult(page);
	}
	
	/**
	 * 获取订单支付导出列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrdersPayExportList(Parameter param) throws Exception {
		String whereStr = param.getWhereStr();
		String hql;
		if(whereStr!=null){
			hql = "from OrdersPay where state = '1' "+whereStr+" order by createTime desc";
		}else{
			hql = "from OrdersPay where state = '1' order by createTime desc";
		}
		List<Object> list = hibernateUtil.hql(hql);
		List<Object> ordersPayList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			OrdersPay pay = (OrdersPay) list.get(i);
			OrdersPayVo vo = new OrdersPayVo();
			vo.setOrderNumber(pay.getOrders().getOrderNumber());
			vo.setPayPrice(pay.getPayPrice());
			if("1".equals(pay.getPayStatic())){
				vo.setPayStatic("支付成功");
			}else{
				vo.setPayStatic("支付失败");
			}
			vo.setPayTime(pay.getOrders().getPayTime());
			if(OrdersConstant.CHINAPAY.equals(pay.getPayType())){
				vo.setPayType("银联支付");
			}else if(OrdersConstant.ALIPAY.equals(pay.getPayType())){
				vo.setPayType("支付宝支付");
			}else if(OrdersConstant.WXPAY.equals(pay.getPayType())){
				vo.setPayType("微信支付");
			}else if(OrdersConstant.ALIQBPAY.equals(pay.getPayType())){
				vo.setPayType("支付宝钱包支付");
			}
			vo.setCoinNumber(pay.getOrders().getBeeCoins());
			vo.setCoinPrice(pay.getOrders().getCoinPrice());
			vo.setSerialNumber(pay.getSerialNumber());
			ordersPayList.add(vo);
		}
		List<Object> reportDataList = new ArrayList<Object>();
		Object[] vo = null;
		for (int j = 0; j < ordersPayList.size(); j++) {
			OrdersPayVo payVo = (OrdersPayVo) ordersPayList.get(j);
			vo = new Object[8];
			vo[0] = payVo.getSerialNumber();
			vo[1] = payVo.getPayTime();
			vo[2] = payVo.getPayType();
			vo[3] = payVo.getPayPrice();
			vo[4] = payVo.getCoinNumber();
			vo[5] = payVo.getCoinPrice();
			vo[6] = payVo.getPayStatic();
			vo[7] = payVo.getOrderNumber();
			reportDataList.add(vo);
		}
		return ObjectToResult.getResult(reportDataList);
	}

}

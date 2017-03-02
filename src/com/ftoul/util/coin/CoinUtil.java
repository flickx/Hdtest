package com.ftoul.util.coin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.Common;
import com.ftoul.common.DateUtil;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.cart.service.CartServ;
import com.ftoul.manage.coin.service.CoinSetServ;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsEvent;
import com.ftoul.po.GoodsEventJoin;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.SystemSet;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.webservice.WebserviceUtil;
import com.ftoul.web.orders.service.OrdersServ;
import com.ftoul.web.vo.OrderPriceVo;
import com.ftoul.web.vo.OrderVo;
import com.ftoul.web.webservice.UserService;

@Component
public class CoinUtil {
	
	@Autowired
	OrdersServ ordersServ;
	@Autowired
	CartServ cartServ;
	@Autowired
	CoinSetServ coinSetServ;
	@Autowired
	private HibernateUtil hibernateUtil;
	
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

}

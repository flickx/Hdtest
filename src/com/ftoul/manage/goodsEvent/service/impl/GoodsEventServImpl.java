package com.ftoul.manage.goodsEvent.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.DateUtil;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.goods.vo.GoodsListVo;
import com.ftoul.manage.goods.vo.GoodsVo;
import com.ftoul.manage.goodsEvent.service.GoodsEventServ;
import com.ftoul.pc.interfaces.vo.PcLimitGoods;
import com.ftoul.pc.interfaces.vo.PcLimitGoodsVo;
import com.ftoul.po.EventOrderVO;
import com.ftoul.po.EventType;
import com.ftoul.po.FullCutRule;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsEvent;
import com.ftoul.po.GoodsEventJoin;
import com.ftoul.po.GoodsEventJoinVo;
import com.ftoul.po.GoodsType;
import com.ftoul.po.SystemSet;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.vo.GoodsEventVO;

@Service("GoodsEventServImpl")
public class GoodsEventServImpl implements GoodsEventServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * 获取活动类型（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@Override
	public Result getEventTypeListPage(Parameter param) throws Exception {
		String hql = "from EventType where state = '1'" + param.getWhereStr() + param.getOrderBy() ;
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 获取所有活动列表（带分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsEventListPage(Parameter param) throws Exception {
		String queryStr = param.getWhereStr();
		String hql = "from GoodsEvent where state = '1' and shopId is null and eventType='"+param.getId()+"' ";
		if(queryStr!=null){
			hql = hql + queryStr + " order by createTime desc";
		}else{
			hql = hql+" order by createTime desc";
		}
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 获取所有"限时抢"
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getLimitEventList(Parameter param) throws Exception {
		String now = new DateStr().getNowTime();
		String startTime = new DateStr().getStartTime();
		String endTime = new DateStr().getEndTime();
		String hql = "from GoodsEvent where state = '1' and '"+startTime+"' < eventBegen and eventBegen < '"+endTime+"' and eventEnd > '"+now+"' and shopId is null and typeName = '限时抢' order by eventBegen asc limit 5";
		List<Object> list = new ArrayList<Object>(5);
		list =	hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}
	/**
	 * 获取pc端首页正在限时抢活动
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@Override
	public Result getPcLimitEvent(Parameter param) throws Exception {
		String now = new DateStr().getNowTime();
		String hql = "from GoodsEvent where state = '1' and '"+now+"' > eventBegen and eventEnd > '"+now+"' and shopId is null and typeName = '限时抢' order by eventBegen asc";
		GoodsEvent goodsEvent = (GoodsEvent)hibernateUtil.hqlFirst(hql);
		List<PcLimitGoodsVo> goodsPcVoList = new ArrayList<PcLimitGoodsVo>();
		if(goodsEvent!=null){
		String eventId = goodsEvent.getId();
		String hql2 = "from GoodsEventJoin where state='1' and goodsEvent.state = '1' and goodsEvent.id= '"+eventId+"' and goods.state='1' and goods.grounding = '1'";	
		List<Object> list = (List<Object>)hibernateUtil.hqlLimit(hql2,0,4);
		List<PcLimitGoods> goodsList = new ArrayList<PcLimitGoods>();
		for (Object goodsEventJoin : list) {
			GoodsEventJoin g = (GoodsEventJoin)goodsEventJoin;
			PcLimitGoods pcLimitGoods = new PcLimitGoods();
			pcLimitGoods.setGoodsId(g.getGoods().getId());
			pcLimitGoods.setImgUrl(g.getGoods().getPicSrc());
			pcLimitGoods.setName(g.getGoods().getTitle());
			pcLimitGoods.setSubName(g.getGoods().getSubtitle());
	        NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
	        format.setMinimumFractionDigits(0);// 设置小数位
	        pcLimitGoods.setNum(format.format(g.getQuantity()*1.0/g.getDefaultQuantity()));
	        pcLimitGoods.setOriginalPrice(g.getGoods().getPrice());
	        pcLimitGoods.setPresentPrice(g.getEventPrice());
			goodsList.add(pcLimitGoods);
		}
		
		PcLimitGoodsVo i  =new PcLimitGoodsVo();
		long beginTime = DateUtil.stringFormatToDate(goodsEvent.getEventBegen().toString(), "yyyy/MM/dd HH:mm:ss").getTime();
		long endTime = DateUtil.stringFormatToDate(goodsEvent.getEventEnd().toString(), "yyyy/MM/dd HH:mm:ss").getTime();
		long nowTime = new Date().getTime();
		if (nowTime > beginTime) {
			i.setEndTime((endTime - nowTime)/1000);
			i.setHasBegin("1");
		}else{
			i.setEndTime((beginTime - nowTime)/1000);
			i.setHasBegin("0");
		}
		i.setStartTime(goodsEvent.getEventBegen().toString().substring(11,16));
		i.setPcLimitGoodsList(goodsList);
		goodsPcVoList.add(i);
		}
		return ObjectToResult.getResult(goodsPcVoList);
	}
	/**
	 * 获取pc端限时抢页面限时抢活动商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@Override
	public Result getPcLimitEventList(Parameter param) throws Exception {
		String start = new DateStr().getStartTime();
		String end = new DateStr().getEndTime();
		String now = new DateStr().getNowTime();
		String hql = "from GoodsEvent where state = '1' and eventEnd > '"+now+"' and '"+start+"' < eventBegen and eventBegen < '"+end+"' and shopId is null and typeName = '限时抢' order by eventBegen asc";
		List<Object> goodsEventList = (List<Object>)hibernateUtil.hql(hql);
		List<PcLimitGoodsVo> goodsPcVoList = new ArrayList<PcLimitGoodsVo>();
		if (goodsEventList!=null && goodsEventList.size()>0) {
			for (Object o : goodsEventList) {
				GoodsEvent	goodsEvent = (GoodsEvent)o;
				String eventId = goodsEvent.getId();
				String hql2 = "from GoodsEventJoin where state='1'  and goodsEvent.state = '1' and goodsEvent.id= '"+eventId+"' and goods.state='1' and goods.grounding = '1' ";	
				List<Object> list = (List<Object>)hibernateUtil.hql(hql2);
				List<PcLimitGoods> goodsList = new ArrayList<PcLimitGoods>();
				for (Object goodsEventJoin : list) {
					GoodsEventJoin g = (GoodsEventJoin)goodsEventJoin;
					PcLimitGoods pcLimitGoods = new PcLimitGoods();
					pcLimitGoods.setGoodsId(g.getGoods().getId());
					pcLimitGoods.setImgUrl(g.getGoods().getPicSrc());
					pcLimitGoods.setName(g.getGoods().getTitle());
					pcLimitGoods.setSubName(g.getGoods().getSubtitle());
			        NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
			        format.setMinimumFractionDigits(2);// 设置小数位
			        pcLimitGoods.setNum(format.format(g.getQuantity()*1.0/g.getDefaultQuantity()));
			        pcLimitGoods.setQunatity(g.getQuantity());
			        pcLimitGoods.setOriginalPrice(g.getGoods().getPrice());
			        pcLimitGoods.setPresentPrice(g.getEventPrice());
					goodsList.add(pcLimitGoods);
				}
				
				PcLimitGoodsVo i  =new PcLimitGoodsVo();
				long beginTime = DateUtil.stringFormatToDate(goodsEvent.getEventBegen().toString(), "yyyy/MM/dd HH:mm:ss").getTime();
				long endTime = DateUtil.stringFormatToDate(goodsEvent.getEventEnd().toString(), "yyyy/MM/dd HH:mm:ss").getTime();
				long nowTime = new Date().getTime();
				if (nowTime > beginTime) {
					i.setEndTime((endTime - nowTime)/1000);
					i.setHasBegin("1");
				}else{
					i.setEndTime((beginTime - nowTime)/1000);
					i.setHasBegin("0");
				}
				i.setStartTime(goodsEvent.getEventBegen().toString().substring(11,16));
				i.setPcLimitGoodsList(goodsList);
				goodsPcVoList.add(i);
			}
		}
		return ObjectToResult.getResult(goodsPcVoList);
	}
	/**
	 * 根据活动ID获取单个活动对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsEventById(Parameter param) throws Exception {
		GoodsEvent goodsEvent = (GoodsEvent) hibernateUtil.find(GoodsEvent.class, param.getId()+"");
		String hql = "from SystemSet where state=1 and type='coin'";
		SystemSet systemSet = (SystemSet)hibernateUtil.hqlFirst(hql);
		GoodsEventVO vo = new GoodsEventVO();
		vo.setGoodsEvent(goodsEvent);
		vo.setSystemSet(systemSet);
		return ObjectToResult.getResult(vo);
	}
	/**
	 * 保存/更新活动对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveGoodsEvent(Parameter param) throws Exception {
		Object res;
		if(param.getObj() == null){
			GoodsEvent goodsEvent =  new GoodsEvent();
			goodsEvent.setCreateTime(new DateStr().toString());
			hibernateUtil.save(goodsEvent);
			res = goodsEvent;
		}else{
			GoodsEvent goodsEvent=(GoodsEvent) JSONObject.toBean((JSONObject) param.getObj(),GoodsEvent.class);
//			if (goodsEvent.getEventPrice()!=null) {
//				hibernateUtil.execHql("update GoodsEventJoin set eventPrice = '"+goodsEvent.getEventPrice()+"' where goodsEvent.id = '"+goodsEvent.getId()+"'");
//			}
			if(Common.isNull(goodsEvent.getId())){
				goodsEvent.setCreateTime(new DateStr().toString());
				goodsEvent.setState("1");
				res = hibernateUtil.save(goodsEvent);
			}else{
				goodsEvent.setModifyTime(new DateStr().toString());
				goodsEvent.setState("1");
				res = hibernateUtil.update(goodsEvent);
			}
		}
		return ObjectToResult.getResult(res);
	}
	/**
	 * 删除活动
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result delGoodsEvent(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update GoodsEvent set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}
	
	/**
	 * 保存/更新活动类型对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveEventType(Parameter param) throws Exception {
		EventType eventType=(EventType) JSONObject.toBean((JSONObject) param.getObj(),EventType.class);
		Object res;
		if(Common.isNull(eventType.getId())){
			eventType.setState("1");
			res = hibernateUtil.save(eventType);
		}else{
			res=hibernateUtil.update(eventType);
		}
		
		return ObjectToResult.getResult(res);
	}
	/**
	 * 删除活动类型
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result delEventType(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update EventType set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}
	
	/**
	 * 获取所有上架商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	public Result getAllGoods(Parameter parameter) throws Exception{
		String countSql  = "select count(*) from Goods gs where gs.state = '1' AND gs.grounding = '1' ";
		String sql ="select gs.id,  " +
					"	gs.title,  " +
					"   gs.pic_src,"+
					"   gs.price FROM  " +
					"	Goods gs  " +
					"JOIN Goods_Param gp ON gs.id = gp.goods_id  " +
					"AND gs.state = '1'  " +
					"AND gp.state = '1'  " +
					"LEFT JOIN Goods_Type gt ON gs.goods_type3 = gt.id  " +
					"AND gt.state = '1'  " +	
					"AND gs.state = '1'  " +
					"LEFT JOIN Goods_Brand gb ON gs.goods_brand_id = gb.id  " +
					"AND gb.state = '1'  " +
					"AND gs.state = '1'  " +
					"JOIN Goods_Prop_Type gpt ON gs.goods_prop_type_id = gpt.id  " +
					"AND gpt.state = '1'  " +
					"AND gs.state = '1'  " +
					"AND gs.grounding = '1'  " +
					"GROUP BY gs.id order by rand() limit 0,100 ";
			Page page = hibernateUtil.sqlPage(countSql ,sql,parameter.getPageNum(),parameter.getPageSize());
			List<GoodsListVo> list = new ArrayList<GoodsListVo>();
			for (int i = 0; i < page.getObjList().size(); i++) {
				GoodsListVo goodsListVo = new GoodsListVo();
				Object[] obj = (Object[])page.getObjList().get(i);
				if(obj[0]!=null)
				  goodsListVo.setId(obj[0].toString());
				if(obj[1]!=null)
				  goodsListVo.setTitle(obj[1].toString());
				if(obj[2]!=null)
				  goodsListVo.setPicSrc(obj[2].toString());
				if(obj[3]!=null)
				  goodsListVo.setPrice(obj[3].toString());
				list.add(goodsListVo);
			}
			page.setVoList(list);
			return ObjectToResult.getResult(page);
		}
	/**
	 * 通过活动ID获取所有活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	public Result getGoodsByEventId(Parameter param) throws Exception{		
		String hql = "from GoodsEventJoin where state='1' and goodsEvent.state = '1' and goods.state='1' and goods.grounding = '1'  and goodsEvent.id = '" + param.getId() +"' " + param.getWhereStr() + param.getOrderBy() ;
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 获取app所有限时抢商品列表
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	public Result getTimeLimitGoods(String id) throws Exception{		
		String hql = "from GoodsEventJoin where state='1' and goodsEvent.state = '1' and goodsEvent.id= '"+id+"' and goods.state='1' and goods.grounding = '1' ";
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}
	/**
	 * 通过活动代码获取所有活动商品
	 * 通过活动ID获取此活动排除的商品品类
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	public Result getGoodsTypeByEventId(Parameter param) throws Exception{		
		String hql = "from GoodsEventJoin where state='1' and goodsEvent.state = '1' and goodsEvent.id = '" + param.getId() +"' " + param.getWhereStr() + param.getOrderBy() ;
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 通过活动代码获取所有活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	public Result getGoodsByEventCode(Parameter param) throws Exception{		
		String hql = "from GoodsEventJoin where state='1' and goods.state='1' and goodsEvent.state = '1' and goods.grounding = '1' " + param.getWhereStr() + param.getOrderBy();
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 通过活动代码获取app端首页活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	public Result getAppGoodsByEventCode(Parameter param) throws Exception{		
		String typeName = param.getId().toString();
		String price = "9.9";
		if (param.getKey()!=null) {
			price = param.getKey().toString();
		}
		String query ="";
		if ("sqp".equals(typeName)) {
			typeName = "省钱趴";
			query = " and eventPrice = '"+price+"'";
		}
		if ("csh".equals(typeName)) {
			typeName = "超实惠";
		}
		if ("tshh".equals(typeName)) {
			typeName = "特色好货";
		}
		if ("dpx".equals(typeName)) {
			typeName = "大牌秀";
		}
		String hql = "from GoodsEventJoin where state='1' and goods.state='1' and goods.grounding = '1' and goodsEvent.state = '1' and goodsEvent.typeName= '" + typeName+ "'" + query+" order by eventPrice DESC";
		Page page = hibernateUtil.hqlPage(null,hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 获取省钱趴配置价格
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result getSqpPrice(Parameter param) throws Exception{		
		String typeName = param.getId().toString();
		if ("sqp".equals(typeName)) {
			typeName = "省钱趴";
		}
		String sql = "select DISTINCT truncate(gej.event_price,1) from goods_event_join gej,goods_event ge where gej.event_id = ge.id and gej.state='1' and ge.state='1' and ge.event_end > now() and  gej.event_price is not null and ge.type_name= '" + typeName+ "' order by gej.event_price asc";
		List<Object[]> priceList = hibernateUtil.sql(sql);
		return ObjectToResult.getResult(priceList);
	}
	/**
	 * 根据类型ID获取活动类型
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getEventTypeById(Parameter param) throws Exception {
		EventType eventType = (EventType) hibernateUtil.find(EventType.class, param.getId()+"");
		return ObjectToResult.getResult(eventType);
	}
	/**
	 * 通过ID删除活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result delGoodsByEventId(Parameter param) throws Exception{
		Integer num = hibernateUtil.execHql("update GoodsEventJoin set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
}
	/**
	 * 保存活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result saveGoodsEventJoinList(Parameter param) throws Exception {
		Object res=null;
		String eventId=param.getParentId()+"";
		String goodsIds = param.getId()+"";
		String[] ids = goodsIds.split(",");
		for (String goodsId :ids) {
			Goods goods = (Goods) hibernateUtil.find(Goods.class, goodsId.trim());
			//通过活动ID和商品id查询商品活动关联表
			String hql = "from GoodsEventJoin where goodsEvent.id = '" + eventId +"' and goods.id='" + goodsId+"' and state=1";
			GoodsEventJoin gej = (GoodsEventJoin) hibernateUtil.hqlFirst(hql);
			//如果商品活动关联表已经存在该商品记录，不新增，不存在则新增
			if (gej==null) {
				GoodsEvent goodsEvent = (GoodsEvent) hibernateUtil.find(GoodsEvent.class, eventId.trim());
				GoodsEventJoin goodsEventJoin=new GoodsEventJoin();
				goodsEventJoin.setGoods(goods);
				goodsEventJoin.setGoodsEvent(goodsEvent);
				goodsEventJoin.setState("1");
				String quantity = (String)hibernateUtil.hqlFirst("SELECT sum(stock) from GoodsParam where state = '1' and goods.grounding = '1' and goods.id = '"+goodsId+"'");
				if (quantity!=null) {
					goodsEventJoin.setQuantity(Integer.parseInt(quantity));
					goodsEventJoin.setDefaultQuantity(Integer.parseInt(quantity));
				}
				res=hibernateUtil.save(goodsEventJoin);
			}
		}
		return ObjectToResult.getResult(res);
	}
	/**
	 * 活动排除品类下所有商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result saveGoodsEventJoin(Parameter param) throws Exception {
		Object res=null;
		String eventId=param.getParentId()+"";
		String typeId = param.getId()+"";
			//通过活动ID和商品id查询商品活动关联表
			String hql = "from GoodsEventJoin where goodsEvent.id = '" + eventId +"' and goodsType.id='" + typeId+"' and state=1";
			GoodsEventJoin gej = (GoodsEventJoin) hibernateUtil.hqlFirst(hql);
			//如果商品活动关联表已经存在该商品记录，不新增，不存在则新增
			if (gej==null) {
				GoodsEvent goodsEvent = (GoodsEvent) hibernateUtil.find(GoodsEvent.class, eventId.trim());
				GoodsType goodsType = (GoodsType) hibernateUtil.find(GoodsType.class, typeId.trim());
				GoodsEventJoin goodsEventJoin=new GoodsEventJoin();
				goodsEventJoin.setGoodsEvent(goodsEvent);
				goodsEventJoin.setState("1");
				goodsEventJoin.setGoodsTypeLevel(goodsType.getLevel());
				goodsEventJoin.setGoodsType(goodsType);
				res=hibernateUtil.save(goodsEventJoin);
			}
		return ObjectToResult.getResult(res);
	}
	/**
	 * 获取活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsEventJoin(Parameter param) throws Exception{
		GoodsEventJoin goodsEventJoin=(GoodsEventJoin)hibernateUtil.find(GoodsEventJoin.class,param.getId()+"");
		GoodsEventJoinVo goodsEventJoinVo = new GoodsEventJoinVo();
		String stock = (String)hibernateUtil.hqlFirst("SELECT sum(stock) from GoodsParam where state = '1' and goods.grounding = '1' and goods.id = '"+goodsEventJoin.getGoods().getId()+"'");
		goodsEventJoinVo.setStock(Integer.parseInt(stock));
		goodsEventJoinVo.setGoodsEventJoin(goodsEventJoin);
		return ObjectToResult.getResult(goodsEventJoinVo);
	}
	/**
	 * 保存活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result updateGoodsEventJoin(Parameter param) throws Exception{
		GoodsEventJoinVo goodsEventJoinVo=(GoodsEventJoinVo)JSONObject.toBean((JSONObject) param.getObj(),GoodsEventJoinVo.class);
		Object num = hibernateUtil.execHql("update GoodsEventJoin set quantity = '"+goodsEventJoinVo.getQuantity()+"',eventPrice = "+goodsEventJoinVo.getEventPrice()+"  where id ='"+goodsEventJoinVo.getId()+"'");
		return ObjectToResult.getResult(num);
	}
	/**
	 * 获取app首页每日上新商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result getAppNewestGoodsList(Parameter param) throws Exception{
		String hql="from Goods where state='1' and grounding = '1' order by rand() DESC ";
		Page page = hibernateUtil.hqlPage(null,hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 获取每日上新商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result getNewestGoodsList(Parameter param) throws Exception{
		String hql="from Goods where state='1' and grounding = '1' "+param.getWhereStr() + param.getOrderBy();
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 通过活动类型获取活动
	 */
	@Override
	public Result getGoodsEventByCode(Parameter param) throws Exception {
		String hql="from GoodsEvent where state = '1' " + param.getWhereStr() + param.getOrderBy();
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		List<Object> objList = new ArrayList<Object>();
		for (int i = 0; i < page.getObjList().size(); i++) {
		GoodsEvent goodsEvent = (GoodsEvent)page.getObjList().get(i);
		String begenTime = goodsEvent.getEventBegen();
		long begin = DateUtil.stringFormatToDate(begenTime, "yyyy/MM/dd HH:mm:ss").getTime();
		long now = new Date().getTime();
		long distance = begin-now;
		//48小时之内的秒杀，显示“距开始倒计时”
			if (distance<=172800000) {
				objList.add(goodsEvent);
			}
		}
		page.setObjList(objList);
		return ObjectToResult.getResult(page);
	}
	/**
	 * 满减规则列表
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result getFullCutRuleList(Parameter param) throws Exception{
		String hql="from FullCutRule where state = '1' and goodsEvent.id = '" + param.getId() +"' " + param.getWhereStr() + param.getOrderBy();
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 新增满减规则
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result saveFullCutRule(Parameter param) throws Exception{
		FullCutRule fullCutRule=(FullCutRule) JSONObject.toBean((JSONObject) param.getObj(),FullCutRule.class);
		GoodsEvent goodsEvent = (GoodsEvent) hibernateUtil.find(GoodsEvent.class, param.getId()+"");
		if (goodsEvent!=null) {
			fullCutRule.setGoodsEvent(goodsEvent);
		}
		Object res;
		if(fullCutRule.getId().length()<3){
			fullCutRule.setState("1");
			fullCutRule.setCreateTime(new DateStr().toString());
			res = hibernateUtil.save(fullCutRule);
		}else{
			FullCutRule rule = (FullCutRule) hibernateUtil.find(FullCutRule.class, fullCutRule.getId());
			fullCutRule.setCreateTime(rule.getCreateTime());
			fullCutRule.setModifyTime(new DateStr().toString());
			res=hibernateUtil.update(fullCutRule);
		}
		
		return ObjectToResult.getResult(res);
	}
	/**
	 * 删除满减规则
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result delFullCutRule(Parameter param) throws Exception{
		Integer num = hibernateUtil.execHql("update FullCutRule set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}
	/**
	 * 
	 * 得到单品促销商品列表
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsListPage(Parameter parameter) throws Exception {		
		String queryStr = parameter.getWhereStr();
		String now = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
		
		String sql = "select g.id,g.title,g.price,g.create_time from goods g where g.shop_id='1' and g.state = '1' and g.id not in(SELECT distinct t1.id from goods t1 "
					+ "left join goods_event_join t2 on t1.state = '1' and t2.state = '1' and t2.goods_id = t1.id "
					+ "left join goods_event t3 on t3.state = '1' and t2.state = '1' and t3.id = t2.event_id "
					+ "where t1.state = '1' and t1.grounding = '1' and t1.shop_id ='1' and t3.event_begen<'" + now + "'< t3.event_end) ";	
		if(queryStr!=null){
			sql = sql + queryStr + " order by g.create_time desc";
		}else{
			sql = sql+" order by g.create_time desc";
		}
		String countSql = "select count(*) from ("+sql+") counts";
		Page page = hibernateUtil.sqlPage(countSql,sql,parameter.getPageNum(),parameter.getPageSize());
		List<GoodsVo> list = new ArrayList<GoodsVo>();
		for (int i = 0; i < page.getObjList().size(); i++) {
			GoodsVo goodsVo = new GoodsVo();
			Object[] obj = (Object[])page.getObjList().get(i);
			if (obj[0]!=null) {
				goodsVo.setId(obj[0].toString());
			}
			if (obj[1]!=null) {
				goodsVo.setTitle(obj[1].toString());
			}
			if (obj[2]!=null) {
				goodsVo.setPrice(obj[2].toString());
			}
			if (obj[3]!=null) {
				goodsVo.setCreateTime(obj[3].toString());
			}
			list.add(goodsVo);
		}
		page.setVoList(list);
		
		return ObjectToResult.getResult(page);
	}
	/**
	 * 
	 * 减少活动商品数量，用于订单调用
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result reduceEventGoodsSum(Parameter param) throws Exception {
		EventOrderVO eventOrderVO=(EventOrderVO)JSONObject.toBean((JSONObject) param.getObj(),EventOrderVO.class);
		Object num = hibernateUtil.execHql("update GoodsEventJoin set quantity = '"+eventOrderVO.getQuantity()+"' where goods.goodsId ='"+eventOrderVO.getGoodsId()+"' and goodsEvent.id='"+eventOrderVO.getEventId()+"'");
		return ObjectToResult.getResult(num);
	}
	/**
	 * 
	 *  查找第一级产品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */

	@Override
	public Result getGoodsTypeLevel1List(Parameter param) throws Exception {
		String hql = "from GoodsType where level =1 and state = 1 and id not in (select goodsType.id from GoodsEventJoin where state = '1'and goodsType.id !=null)";
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}
	/**
	 * 
	 *  通过pid查找
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getByPid(Parameter param) {
		List<Object> goodsTypeList = new ArrayList<Object>();
		if(null!=param.getId()){
			String hql = "from GoodsType where state=1 and pid='"+param.getId()+"'  and id not in (select goodsType.id from GoodsEventJoin where state = '1' and goodsType.id !=null)";
			goodsTypeList = hibernateUtil.hql(hql);
		}
		return ObjectToResult.getResult(goodsTypeList);
	}
	/**
	 * 
	 *  查询商品销量
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getSaleSumByGoodsId(String goodsId) {
		Object o = null;
		if(null != goodsId){
			String hql = "from GoodsParam where state=1 and goods.id='"+goodsId+"'";
			o = hibernateUtil.hqlFirst(hql);
		}
		return ObjectToResult.getResult(o);
	}
}
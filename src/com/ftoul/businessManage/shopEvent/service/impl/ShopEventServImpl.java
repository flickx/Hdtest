package com.ftoul.businessManage.shopEvent.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.businessManage.shopEvent.service.ShopEventServ;
import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.goods.vo.GoodsVo;
import com.ftoul.po.EventOrderVO;
import com.ftoul.po.EventType;
import com.ftoul.po.FullCutRule;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsEvent;
import com.ftoul.po.GoodsEventJoin;
import com.ftoul.po.GoodsEventJoinVo;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("ShopEventServImpl")
public class ShopEventServImpl implements ShopEventServ {

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
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
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
		String hql = "from GoodsEvent where state = '1' ";
		if (queryStr != null) {			
			hql = hql + queryStr + param.getOrderBy() ;
		}else{
			hql = hql + param.getOrderBy() ;
		}
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	
	/**
	 * 根据活动ID获取单个活动对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsEventById(Parameter param) throws Exception {
		GoodsEvent goodsEvent = (GoodsEvent) hibernateUtil.find(GoodsEvent.class, param.getId()+"");
		return ObjectToResult.getResult(goodsEvent);
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
	public Result getAllGoods(Parameter param) throws Exception{
		String hql = "from Goods where state = '1' and grounding = '1' ";
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 通过活动ID获取所有活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	public Result getGoodsByEventId(Parameter param) throws Exception{		
		String hql = "from GoodsEventJoin where state='1' and goods.grounding = '1'  and goodsEvent.id = '" + param.getId() +"' " + param.getWhereStr() + param.getOrderBy() ;
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 通过活动代码获取所有活动商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	public Result getGoodsByEventCode(Parameter param) throws Exception{		
		String hql = "from GoodsEventJoin where state='1' and goods.grounding = '1' " + param.getWhereStr() + param.getOrderBy();
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
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
				goodsEventJoin.setQuantity(Integer.parseInt(quantity));
				res=hibernateUtil.save(goodsEventJoin);
			}
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
	 * 获取每日上新商品
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result getNewestGoodsList(Parameter param) throws Exception{
		String hql="from Goods where state='1' and grounding = '1' "+param.getWhereStr() + param.getOrderBy();
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 通过活动类型获取活动
	 */
	@Override
	public Result getGoodsEventByCode(Parameter param) throws Exception {
		String hql="from GoodsEvent where state = '1' " + param.getWhereStr() + param.getOrderBy();
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
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
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
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
	 * 得到商品列表（带分页） 一类活动不能重叠，须在商品选择时就排除掉当期选择过的商品。
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsListPage(Parameter parameter) throws Exception {		
		String queryStr = parameter.getWhereStr();
		String sql = "SELECT t1.id,t1.title,t1.price,t1.create_time from goods t1 "
				+ "left join goods_event_join t2 on t1.state = '1' and t2.state = '1' and t2.goods_id = t1.id "
				+ "left join goods_event t3 on t3.state = '1' and t2.state = '1' and t3.id = t2.event_id "
				+ "where t1.state = '1' and t1.grounding = '1'  and (t3.type_name is null or t3.type_name = '满减')";
		if(queryStr!=null){
			sql = sql + queryStr + " order by t1.create_time desc";
		}else{
			sql = sql+" order by t1.create_time desc";
		}
		Page page = hibernateUtil.sqlPage(sql,parameter.getPageNum(),parameter.getPageSize());
		List<GoodsVo> list = new ArrayList<GoodsVo>();
		for (int i = 0; i < page.getObjList().size(); i++) {
			GoodsVo goodsVo = new GoodsVo();
			Object[] obj = (Object[])page.getObjList().get(i);
			goodsVo.setId(obj[0].toString());
			goodsVo.setTitle(obj[1].toString());
			goodsVo.setPrice(obj[2].toString());
			goodsVo.setCreateTime(obj[3].toString());
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
}
package com.ftoul.web.goods.service.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.DateStr;
import com.ftoul.common.DateUtil;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goods.vo.GoodsVo;
import com.ftoul.mongo.po.UserBrowse;
import com.ftoul.pc.interfaces.vo.PcNewGoods;
import com.ftoul.pc.interfaces.vo.PcNewGoodsVo;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsEvent;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.GoodsProp;
import com.ftoul.po.GoodsType;
import com.ftoul.po.GoodsUploadpic;
import com.ftoul.po.PcTypeGoods;
import com.ftoul.po.User;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.logistics.LogisticsUtil;
import com.ftoul.util.mongodb.MongoDbUtil;
import com.ftoul.util.price.PriceUtil;
import com.ftoul.web.goods.service.GoodsBrandServ;
import com.ftoul.web.goods.service.GoodsPropTypeServ;
import com.ftoul.web.goods.service.GoodsServ;
import com.ftoul.web.goods.service.GoodsTypeServ;

/**
* 
*
* 类描述：商品业务实现类
* @author: yw
* @date： 日期：2016年8月8日 时间：上午10:01:10
* @version 1.0
*
*/
@Service("WebGoodsImpl")
public class GoodsServImpl implements GoodsServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private GoodsTypeServ goodsTypeServ;
	@Autowired
	private GoodsBrandServ goodsBrandServ;
	@Autowired
	private GoodsPropTypeServ goodsPropTypeServ;
	@Autowired
	private PriceUtil priceUtil;
	@Autowired
	private HttpServletRequest req;
	@Autowired
	MongoDbUtil mongoDbUtil;
	
	@Autowired
	private LogisticsUtil logisticsUtil;
	/**
	 * 保存/更新商品对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveGoods(Parameter param) throws Exception {
		 return null;
	}

	/**
	 * 通过s商品分类查找商品
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsList(Parameter param) {
		if(param.getId()!=null){
			GoodsType goodsType =(GoodsType) hibernateUtil.find(GoodsType.class, param.getId()+"");
			if(goodsType!=null){
				String hql1 ="from Goods where goodsType3.id = '"+goodsType.getId() +"'";
				List<Object> goodsList = hibernateUtil.hql(hql1);
				return ObjectToResult.getResult(goodsList);
			}
		}
		String hql2 = "from Goods";
		List<Object> allGoodsList = hibernateUtil.hql(hql2);
		return ObjectToResult.getResult(allGoodsList);
	}

	/**
	 * 
	 * 得到商品列表（带分页）
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsListPage(Parameter param) throws Exception {
		    String hql1 = "from Goods where state = '1' and grounding = '1' and id in (select gp.goods.id from GoodsParam gp where gp.state='1') and goodsType3.id='"+param.getId()+"' "+param.getOrderBy();
			Page page = hibernateUtil.hqlPage(null, hql1, param.getPageNum(), param.getPageSize());
			return ObjectToResult.getResult(page);
			
	}
	
	/**
	 * 
	 * 得到商品详情
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsDetail(Parameter param) throws Exception {
		Goods goods =(Goods) hibernateUtil.find(Goods.class, param.getId()+"");
		com.ftoul.mongo.po.Goods goods2 = new com.ftoul.mongo.po.Goods();
		BeanUtils.copyProperties(goods, goods2);
		UserBrowse userBrowse = new UserBrowse();
		if(param.getUserToken()!=null){
			User user = param.getUserToken().getUser();
			com.ftoul.mongo.po.User user2 = new com.ftoul.mongo.po.User(); 
			BeanUtils.copyProperties(user, user2);
//			List userList = hibernateUtil.hql(" from User where state = '1' and id = '" + user.getId() +"'");
//			if(userList != null && userList.size() > 0)
			userBrowse.setUser(user2);
		}
		userBrowse.setIpAddress(getRemoteHost());
		userBrowse.setBrowseTime(new DateStr().toString());
		userBrowse.setGoods(goods2);
		userBrowse.setState("1");
//		hibernateUtil.save(userBrowse);
		mongoDbUtil.insert(userBrowse);
		
		GoodsVo goodsVo = new GoodsVo();
		
		String hql ="from GoodsProp where state = 1 and goods.id ='"+param.getId()+"'" ;
		List<Object> gp = hibernateUtil.hql(hql);
		List<GoodsProp> goodsPropList = new ArrayList<GoodsProp>();
		if(gp!=null){
			for(Object obj :gp){
				goodsPropList.add((GoodsProp)obj);
			}
		}
		//计算运费
		String provice = null;
		if(null!=param.getUserToken()){
			provice = logisticsUtil.getDefaultUserAddressProvince(param.getUserToken().getUser().getId());
		}
		//String userId = param.getUserToken().getUser().getId();
		double freight = logisticsUtil.getFreight(provice,goods.getShopId(),1);
		goodsVo.setFreight(freight);
		String hql1 ="from GoodsParam where state=1 and goods.id='"+param.getId()+"'";
		List<Object> gpList = this.hibernateUtil.hql(hql1);
		List<GoodsParam> goodsParamList = new ArrayList<GoodsParam>();
		if(goodsParamList!=null){
			for(Object object :gpList){
				goodsParamList.add((GoodsParam)object);
			}
		}
		String sql = "select sum(stock) from goods_param where goods_id='"+param.getId()+"'"+" and state = 1";
		List<Object[]> stockTotal = this.hibernateUtil.sql(sql);
//		Object[] s = stockTotal.get(0);
		for (int i = 0; i < stockTotal.size(); i++){
			if(stockTotal.get(0)!=null){
				Double d = Double.parseDouble(String.valueOf(stockTotal.get(0)));
				String sumStock = new Double(d).intValue()+"";
				goodsVo.setSumStock(sumStock);
			}else{
				goodsVo.setSumStock("0");
			}
		}
		//获取商品价格 1.优先取商品活动价格2.取活动促销价格.3 折扣价
		String hql2 = "select ge.eventName,gej.eventPrice,ge.eventPrice,ge.discount,gej.quantity,ge.eventBegen,ge.eventEnd,gs.picSrc,ge.state,gej.state,ge.typeName,ge.homeChannel,gs.subtitle from Goods gs,"
				+ "GoodsEventJoin gej,GoodsEvent ge where gs.id = gej.goods.id and ge.id = gej.goodsEvent.id and gej.state = '1' and ge.state = '1' and ge.typeName!='满减' and gs.id ='"+param.getId()+"'";
		List<Object> eventList = this.hibernateUtil.hql(hql2);
		Date currentTime = DateUtil.stringFormatToDate(
				DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss"),
				"yyyy/MM/dd HH:mm:ss");
		if(eventList.size()>0){
			Object[] obj = (Object[])eventList.get(0);
			Date begin = DateUtil.stringFormatToDate(obj[5]+"",
					"yyyy/MM/dd HH:mm:ss");
			Date end = DateUtil.stringFormatToDate(obj[6]+"",
					"yyyy/MM/dd HH:mm:ss");
			if (currentTime.after(begin) && currentTime.before(end)){
				goodsVo.setGoodsEventName(obj[0]+"");
				goodsVo.setTypeName(obj[10]+"");
				if(null!=obj[1]){
					goodsVo.setEventPrice(obj[1]+"");
				}else{
					if(null!=obj[2]){
						goodsVo.setEventPrice(obj[2]+"");
					}else{
						if(null!=obj[3]&&!"".equals(obj[3]+"")){
							if(!"1".equals(obj[3]+"")){
								float f = Float.parseFloat(String.valueOf(goods.getPrice()))*Float.parseFloat(obj[3]+"");
								goodsVo.setEventPrice(new DecimalFormat("0.00").format(f));	
							}else{
								float f = Float.parseFloat(String.valueOf(goods.getPrice()));
								goodsVo.setEventPrice(new DecimalFormat("0.00").format(f));	
							}
						}else{
							float f = Float.parseFloat(String.valueOf(goods.getPrice()));
							goodsVo.setEventPrice(new DecimalFormat("0.00").format(f));	
						}
					}
				}
				if(null !=obj[4]){
					goodsVo.setQuantity(obj[4]+"");
				}
				goodsVo.setTypeName(obj[10]+"");
				goodsVo.setSubtitle(obj[12]+"");
			}
			//判断倍蜂币活动是否已经开始
			String current = DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss");
			GoodsEvent ge =(GoodsEvent) this.hibernateUtil.hqlFirst("from GoodsEvent where typeName='蜂币翻倍' and eventBegen<='"+current+"' and eventEnd>='"+current+"' and state='1'");
//			base = base*(ge.getExchangeRate().doubleValue());
			//原始倍率*活动翻倍率
			if (ge!=null) { 
				//倍蜂币开始页面判断标识
				goodsVo.setHomeChannel("1");
			}
		}
		
		//是否参加满减活动
		String hql3 = "select ge.eventName,ge.typeName,ge.eventBegen,ge.eventEnd from Goods gs,"
				+ "GoodsEventJoin gej,GoodsEvent ge where gs.id = gej.goods.id and ge.id = gej.goodsEvent.id "
				+ "and gej.state = '1' and ge.state = '1' and ge.typeName='满减' "
				+ "and gs.id ='"+param.getId()+"'";
		List<Object> fullCutList = this.hibernateUtil.hql(hql3);
		if(fullCutList.size()>0){
			Object[] obj = (Object[])fullCutList.get(0);
			Date begin = DateUtil.stringFormatToDate(obj[2]+"",
					"yyyy/MM/dd HH:mm:ss");
			Date end = DateUtil.stringFormatToDate(obj[3]+"",
					"yyyy/MM/dd HH:mm:ss");
			if (currentTime.after(begin) && currentTime.before(end)){
				goodsVo.setFullCutName(obj[0].toString());
			}
		}
		goodsVo.setPicSrc(goods.getPicSrc());
		DecimalFormat df = new DecimalFormat("0.00");
		goodsVo.setPrice(df.format(goods.getPrice()));
		//获取商品图片集合
		String picHql = "from GoodsUploadpic where state=1 and (picType=0 or picType=1) and  goods.id='"+param.getId()+"' order by picType";
		List<Object> picList = this.hibernateUtil.hql(picHql);
		List<GoodsUploadpic> goodsPicList = new ArrayList<GoodsUploadpic>();
		if(picList!=null){
			for(Object object :picList){
				goodsPicList.add((GoodsUploadpic)object);
			}
			goodsVo.setGoodsPicList(goodsPicList);
		}
		//获取商品详情图片集合
		String picInfoHql = "from GoodsUploadpic where state=1 and picType=2 and  goods.id='"+param.getId()+"'";
		List<Object> picInfoList = this.hibernateUtil.hql(picInfoHql);
		List<GoodsUploadpic> goodsPicInfoList = new ArrayList<GoodsUploadpic>();
		if(picInfoList!=null){
			for(Object object :picInfoList){
				goodsPicInfoList.add((GoodsUploadpic)object);
			}
			goodsVo.setGoodsPicInfoList(goodsPicInfoList);
		}
		goodsVo.setId(goods.getId());
		if(goods.getGoodsType1()!=null){
			goodsVo.setGoodsType1(goods.getGoodsType1().getId());
			goodsVo.setGoodsTypeNameOne(goods.getGoodsType1().getName());
		}
		if(goods.getGoodsType2()!=null){
			goodsVo.setGoodsType2(goods.getGoodsType2().getId());
			goodsVo.setGoodsTypeNameTwo(goods.getGoodsType2().getName());
		}
		if(goods.getGoodsType3()!=null){
			goodsVo.setGoodsType3(goods.getGoodsType3().getId());
			goodsVo.setGoodsTypeNameThree(goods.getGoodsType3().getName());
		}
		if(goods.getGoodsPropType()!=null){
			goodsVo.setGoodsPropTypeId(goods.getGoodsPropType().getId());
		}
		if(goods.getGoodsBrand().getId()!=null)
		goodsVo.setGoodsBrandId(goods.getGoodsBrand().getId());
		if(goods.getGrounding()!=null)
		goodsVo.setGrounding(goods.getGrounding());
		if(goods.getTitle()!=null)
		goodsVo.setTitle(goods.getTitle());
		if(goods.getPcInfo()!=null)
		goodsVo.setPcInfo(goods.getPcInfo());
		if(goods.getMobilInfo()!=null)
		goodsVo.setMobilInfo(goods.getMobilInfo());
		if(goodsPropList!=null)
		goodsVo.setGoodsPropList(goodsPropList);
		if(goodsParamList!=null)
		goodsVo.setGoodsParamList(goodsParamList);
		if(goods.getCode()!=null)
		goodsVo.setCode(goods.getCode());
		goodsVo.setSaleSum(String.valueOf(goods.getSaleSum()));
		if(null!=goods.getShopId())	{
			goodsVo.setShopId(goods.getShopId());
		}
		if(goods.getSubtitle()!=null){
			goodsVo.setSubtitle(goods.getSubtitle());
		}
		if(goods.getGoodsLabel()!=null){
			goodsVo.setGoodsLabel(goods.getGoodsLabel());
		}
		if(goods.getWeight()!=null){
			goodsVo.setWeight(goods.getWeight());
		}
		if(goods.getPackingLength()!=null){
			goodsVo.setPackingLength(goods.getPackingLength());
		}
		if(goods.getPackingWidth()!=null){
			goodsVo.setPackingWidth(goods.getPackingWidth());
		}
		if(goods.getPackingHeight()!=null){
			goodsVo.setPackingHeight(goods.getPackingHeight());
		}
		if(goods.getPackingList()!=null){
			goodsVo.setPackingList(goods.getPackingList());
		}
		if(goods.getAfterService()!=null){
			goodsVo.setAfterService(goods.getAfterService());
		}
		return ObjectToResult.getResult(goodsVo);
		
	}
	/**
	 * app首页模糊搜索
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result getAppGoodsListByKeyWord(Parameter param) throws Exception {
		String key = new String(param.getKey().getBytes("ISO-8859-1"),"UTF-8");
		String hql=" FROM Goods WHERE state =1 and grounding='1' and title LIKE '%"+ key+ "%' "+param.getOrderBy();
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	@Override
	public Result getGoodsListByKeyWord(Parameter param) throws Exception {
		String hql=
				" FROM" +
				"	Goods " +
				"WHERE  state =1  and grounding='1' and (" +
				"	title LIKE '%"+ param.getKey()+ "%' " +
				"OR goodsType3.id IN (" +
				"	SELECT" +
				"		id" +
				"	FROM" +
				"		GoodsType" +
				"	WHERE" +
				"		NAME LIKE '%"+ param.getKey()+ "%' " +
				")" +
				"OR goodsBrand.id IN ( " +
				"	SELECT " +
				"		id " +
				"	FROM " +
				"		GoodsBrand " +
				"	WHERE " +
				"		NAME LIKE '%"+ param.getKey()+"%' " +
				")" +
				"OR goodsPropType.id IN ( " +
				"	SELECT" +
				"		id " +
				"	FROM" +
				"		GoodsPropType " +
				"	WHERE" +
				"		NAME LIKE '%"+ param.getKey()+"%' )) "+param.getOrderBy();
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}

	@Override
	public Result getGoodsEventPrice(Parameter param) throws Exception {
		// 获取商品价格 1.优先取商品活动价格2.取活动促销价格.3 折扣价
		GoodsVo goodsVo = new GoodsVo();
		String hql2 = "select ge.eventName,gej.eventPrice,ge.eventPrice,ge.discount,gej.quantity,ge.eventBegen,ge.eventEnd,gp.price from Goods gs,GoodsParam gp,"
				+ "GoodsEventJoin gej,GoodsEvent ge where gs.id = gp.goods.id and gs.id = gej.goods.id and ge.id = gej.goodsEvent.id and gej.state = '1' and ge.state = '1' and gp.id ='"
				+ param.getId() + "'";
		List<Object> eventList = this.hibernateUtil.hql(hql2);
		Date currentTime = DateUtil.stringFormatToDate(
				DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss"),
				"yyyy/MM/dd HH:mm:ss");
		if (eventList.size() > 0) {
			Object[] obj = (Object[]) eventList.get(0);
			goodsVo.setPrice(obj[7]+"");
			Date begin = DateUtil.stringFormatToDate(obj[5]+"",
					"yyyy/MM/dd HH:mm:ss");
			Date end = DateUtil.stringFormatToDate(obj[6]+"",
					"yyyy/MM/dd HH:mm:ss");
			if (currentTime.after(begin) && currentTime.before(end)) {
				goodsVo.setGoodsEventName(obj[0]+"");
				if (null != obj[1]) {
					goodsVo.setEventPrice(obj[1]+"");
				} else {
					if (null != obj[2]) {
						goodsVo.setEventPrice(obj[2]+"");
					} else {
						if(null!=obj[3]&&!"".equals(obj[3]+"")){
							if(!"1".equals(obj[3]+"")){
								Float f = Float.parseFloat(obj[7]+"")
										* Float.parseFloat(obj[3]+"");
								goodsVo.setEventPrice(new DecimalFormat("0.00").format(f));
							}
						}
					}
				}
				if (obj[4] != null) {
					goodsVo.setQuantity(obj[4]+"");
				}
			}
		}
		return ObjectToResult.getResult(goodsVo);
	}
	

	@Override
	public Result getGoodsListPageByCross(Parameter param) throws Exception {
		String hql  ="from Goods where state ='1' and crossborder='1' "+param.getOrderBy();
		Page page =this.hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}

	private String getRemoteHost(){
	    String ip = req.getHeader("x-forwarded-for");
	    System.out.println("x-forwarded-for:"+ip);
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = req.getHeader("Proxy-Client-IP");
	        System.out.println("Proxy-Client-IP:"+ip);
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = req.getHeader("WL-Proxy-Client-IP");
	        System.out.println("WL-Proxy-Client-IP:"+ip);
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = req.getRemoteAddr();
	        System.out.println("req.getRemoteAddr():"+ip);
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	/**
	 * pc首页“每日上新”接口
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Result getPcNewGoods(Parameter param) throws Exception{
		String startTime = new DateStr().getStartTime();
		startTime = startTime.replace("/","-");
		String endTime = new DateStr().getEndTime();
		endTime = endTime.replace("/","-");
		String sql = "select g.id,g.title,g.subtitle,gp.param_name,g.price,gp.market_price,g.pic_src from Goods g,Goods_param gp where g.id = gp.goods_id and  g.state = '1' and '"+startTime+"' <= g.create_time and g.create_time <= '"+endTime+"' and g.shop_id = '1' order by rand() asc limit 0,4";
		List<Object[]> list =	hibernateUtil.sql(sql);
		List<PcNewGoods> newGoodsList = new ArrayList<PcNewGoods>();
		for (Object[] goods : list) {
			PcNewGoods newGoods = new PcNewGoods();
			newGoods.setGoodsId(goods[0].toString());
			newGoods.setTitle(goods[1].toString());
			if(goods[2]!=null){
				newGoods.setSubTitle(goods[2].toString());
			}
			newGoods.setModel(goods[3].toString());
			newGoods.setPrice((double)goods[4]);
			newGoods.setMarketPrice(Double.parseDouble(goods[5].toString()));
			newGoods.setPicSrc(goods[6].toString());
			NumberFormat nf=new  DecimalFormat( "0.0 "); 
			newGoods.setNum(nf.format((double)goods[4]*1.0/Double.parseDouble(goods[5].toString())*10));
			newGoodsList.add(newGoods);
		}
		return ObjectToResult.getResult(newGoodsList);
	}
	/**
	 * pc“每日上新”详情页面接口
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Result getPcNewGoodsList(Parameter param) throws Exception{
		//获取全部一级分类
		List<GoodsType> typeList = (List<GoodsType>)goodsTypeServ.getGoodsTypeLevel1List(param).getObj();
		List<PcNewGoodsVo> pcNewGoodsList = new ArrayList<PcNewGoodsVo>();
		//查询一级分类下每日上新商品
		for (GoodsType type : typeList) {
			String typeId = type.getId();
			PcNewGoodsVo vo = new PcNewGoodsVo();
			String startTime = new DateStr().getStartTime();
			startTime = startTime.replace("/","-");
			String endTime = new DateStr().getEndTime();
			endTime = endTime.replace("/","-");
			String sql = "select g.id,g.title,g.subtitle,gp.param_name,g.price,gp.market_price,g.pic_src from Goods g,Goods_param gp where g.id = gp.goods_id and  g.state = '1' and '"+startTime+"' <= g.create_time and g.create_time <= '"+endTime+"' and g.shop_id = '1' and g.goods_type1 ='"+typeId+"'  order by rand() asc";
			List<Object[]> goodsList = hibernateUtil.sql(sql);
			List<PcNewGoods> newGoodsList = new ArrayList<PcNewGoods>();
			for (Object[] goods : goodsList) {
				PcNewGoods newGoods = new PcNewGoods();
				newGoods.setGoodsId(goods[0].toString());
				newGoods.setTitle(goods[1].toString());
				newGoods.setSubTitle(goods[2].toString());
				newGoods.setModel(goods[3].toString());
				newGoods.setPrice((double)goods[4]);
				newGoods.setMarketPrice(Double.parseDouble(goods[5].toString()));
				newGoods.setPicSrc(goods[6].toString());
				NumberFormat nf=new  DecimalFormat( "0.0 "); 
				newGoods.setNum(nf.format((double)goods[4]*1.0/Double.parseDouble(goods[5].toString())*10));
				newGoodsList.add(newGoods);  
			}
			vo.setTotal(goodsList.size());
			vo.setGoodsType1Name(type.getName());
			vo.setPcNewGoodsList(newGoodsList);
			pcNewGoodsList.add(vo);
		}
		return ObjectToResult.getResult(pcNewGoodsList);
	}
	
	/**
	 * pc首页“查询分类商品”接口
	 * @param param id:分类id，key：分类级别，sidx：排序字段
	 * @return
	 * @throws Exception
	 */
	public Result getGoodsByType(Parameter param) throws Exception{
		String id = param.getId().toString();//分类id
		String level = param.getKey();//分类级别，1：一级分类，2：二级分类
		String sidx = param.getSidx();//排序字段，1：随机，2：销量
		String sql = "select g.id,g.title,g.subtitle,gp.param_name,g.price,gp.market_price,g.pic_src from Goods g,Goods_param gp where g.id = gp.goods_id and  g.state = '1' and g.shop_id = '1'";
		if ("1".equals(level)) {
			sql = sql + " and g.goods_type1 ='" + id ;
			if ("1".equals(sidx)) {
				 sql = sql + "' order by rand() desc limit 0,6";			
			}else{
				 sql = sql + "' order by g.sale_sum desc limit 0,4";		
			}
		}
		
		else if ("2".equals(level)) {
			sql = sql + " and g.goods_type2 ='" + id ;
			if ("1".equals(sidx)) {
				 sql = sql + "' order by rand() desc limit 0,6";			
			}else{
				 sql = sql + "' order by g.sale_sum desc limit 0,5";		
			}
		}
		List<Object[]> list = hibernateUtil.sql(sql);
		List<PcTypeGoods> typeGoodsList = new ArrayList<PcTypeGoods>();
		for (Object[] goods : list) {
			PcTypeGoods typeGoods = new PcTypeGoods();
			typeGoods.setGoodsId(goods[0].toString());
			typeGoods.setTitle(goods[1].toString());
			if (goods[2]==null) {
				typeGoods.setSubTitle("");
			}else{
				typeGoods.setSubTitle(goods[2].toString());
			}
			typeGoods.setGoodsParam(goods[3].toString());
			if(goods[4]!=null){
				typeGoods.setPrice(Double.parseDouble(goods[4].toString()));
			}
			if(goods[5]!=null){
				typeGoods.setMarketPrice(Double.parseDouble(goods[5].toString()));
			}
			if(goods[6]!=null){
				typeGoods.setPicSrc(goods[6].toString());
			}
			typeGoodsList.add(typeGoods);
		}
		return ObjectToResult.getResult(typeGoodsList);
	}
}
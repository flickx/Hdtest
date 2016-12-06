package com.ftoul.web.goods.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.DateStr;
import com.ftoul.common.DateUtil;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goods.vo.GoodsVo;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsEvent;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.GoodsProp;
import com.ftoul.po.GoodsType;
import com.ftoul.po.GoodsUploadpic;
import com.ftoul.po.User;
import com.ftoul.po.UserBrowse;
import com.ftoul.util.hibernate.HibernateUtil;
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
			Page page = hibernateUtil.hqlPage(hql1, param.getPageNum(), param.getPageSize());
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
		UserBrowse userBrowse = new UserBrowse();
		if(param.getUserToken()!=null){
			User user = param.getUserToken().getUser();
			List userList = hibernateUtil.hql(" from User where state = '1' and id = '" + user.getId() +"'");
			if(userList != null && userList.size() > 0)
				userBrowse.setUser(user);
		}
		userBrowse.setIpAddress(getRemoteHost());
		userBrowse.setBrowseTime(new DateStr().toString());
		userBrowse.setGoods(goods);
		userBrowse.setState("1");
		hibernateUtil.save(userBrowse);
		
		GoodsVo goodsVo = new GoodsVo();
		
		String hql ="from GoodsProp where state = 1 and goods.id ='"+param.getId()+"'" ;
		List<Object> gp = hibernateUtil.hql(hql);
		List<GoodsProp> goodsPropList = new ArrayList<GoodsProp>();
		if(gp!=null){
			for(Object obj :gp){
				goodsPropList.add((GoodsProp)obj);
			}
		}
		
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
		for (int i = 0; i < stockTotal.size(); i++){
			Double d = Double.parseDouble(String.valueOf(stockTotal.get(0)));
			String sumStock = new Double(d).intValue()+"";
			goodsVo.setSumStock(sumStock);
		}
		//获取商品价格 1.优先取商品活动价格2.取活动促销价格.3 折扣价
		String hql2 = "select ge.eventName,gej.eventPrice,ge.eventPrice,ge.discount,gej.quantity,ge.eventBegen,ge.eventEnd,gs.picSrc,ge.state,gej.state,ge.typeName,ge.homeChannel,gs.subtitle from Goods gs,"
				+ "GoodsEventJoin gej,GoodsEvent ge where gs.id = gej.goods.id and ge.id = gej.goodsEvent.id and gej.state = '1' and ge.state = '1' and gs.id ='"+param.getId()+"'";
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
							}
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
		if(goods.getGoodsType3()!=null){
			goodsVo.setGoodsType3(goods.getGoodsType3().getId());
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
		return ObjectToResult.getResult(goodsVo);
		
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
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
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
		Page page =this.hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
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


	
}
package com.ftoul.pc.search.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.DateUtil;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.goods.vo.GoodsSearchMainVo;
import com.ftoul.pc.goods.vo.GoodsSearchVo;
import com.ftoul.pc.goods.vo.SearchVo;
import com.ftoul.pc.search.service.SearchKeyNameServ;
import com.ftoul.po.SearchKeyName;
import com.ftoul.util.hibernate.HibernateUtil;
@Service("SearchKeyNameServImpl")
public class SearchKeyNameServImpl implements SearchKeyNameServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * 获取搜索关键字
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getSearchKeyName(Parameter param) throws Exception {
		String hql="from SearchKeyName where state ="+param.getKey();
		List<Object> list=hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}
	/**
	 * 根据关键字查询商品（分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsBykeyName(Parameter param) throws Exception {

		String order = "";
		String whereStr = "";
		if(param.getOrderBy()==null || param.getOrderBy().trim().equals("order by id desc")){
			order = " order by sum(uco.id) desc";
		}else{
			order = param.getOrderBy();
		}
		if(null!=param.getWhereStr()){
			whereStr = param.getWhereStr();
		}
		String title = param.getKey();
		if(title.contains("'")){
			title = title.replace("'", "\\'");
		}
		String goodsSql ="select gs.id,gs.title,gs.price,gs.pic_src,gs.shop_id,sum(uco.id),gs.sale_sum,bs.store_name,gp.market_price "
						+ "from Goods gs join business_store bs on gs.shop_id = bs.id and gs.state = 1 and gs.grounding  = 1 "
						+ "join goods_param gp on gs.id = gp.goods_id and gp.state = 1 and gp.defalut = 1 and gs.state = 1 and gs.grounding  = 1 "
						+ "join goods_brand gb on gs.goods_brand_id = gb.id and gs.state = 1 and gs.grounding  = 1 and gb.state = 1 "
						+ "join goods_type gt on gs.goods_type1 = gt.id and gs.state =1 and gs.grounding =1 and gt.state = 1 "
						+ "left join goods_type gt2 on gs.goods_type2 = gt2.id and gs.state =1 and gs.grounding =1 and gt2.state = 1 "
						+ "left join goods_type gt3 on gs.goods_type3 = gt3.id and gs.state =1 and gs.grounding =1 and gt3.state = 1 "
						+ "left join cross_border_museum cbm on gs.country_id = cbm.id and gs.state =1 and gs.grounding =1 and cbm.state = 1 "
						+ "left join user_comment uco on gs.id = uco.good_id and uco.state = 1 "
						+ "where (gs.title like '%"+title+"%'"+ " or gb.name like '%"+title+"%'"+ " or cbm.name like '%"+title+"%' "
						+ "or gt.name like '%"+title+"%'"+ " or gt2.name like '%"+title+"%'" + " or gt3.name like '%"+title+"%') "
						+  whereStr+" group by gs.id "+order ;
		
		String goodsCount ="select count(gs.id) "
				+ "from Goods gs join business_store bs on gs.shop_id = bs.id and gs.state = 1 and gs.grounding  = 1 "
				+ "join goods_param gp on gs.id = gp.goods_id and gp.state = 1 and gp.defalut = 1 and gs.state = 1 and gs.grounding  = 1 "
				+ "join goods_brand gb on gs.goods_brand_id = gb.id and gs.state = 1 and gs.grounding  = 1 and gb.state = 1 "
				+ "join goods_type gt on gs.goods_type1 = gt.id and gs.state =1 and gs.grounding =1 and gt.state = 1 "
				+ "left join goods_type gt2 on gs.goods_type2 = gt2.id and gs.state =1 and gs.grounding =1 and gt2.state = 1 "
				+ "left join goods_type gt3 on gs.goods_type3 = gt3.id and gs.state =1 and gs.grounding =1 and gt3.state = 1 "
				+ "left join cross_border_museum cbm on gs.country_id = cbm.id and gs.state =1 and gs.grounding =1 and cbm.state = 1 "
				+ "left join user_comment uco on gs.id = uco.good_id and uco.state = 1 "
				+ "where (gs.title like '%"+title+"%'"+ " or gb.name like '%"+title+"%'"+ " or cbm.name like '%"+title+"%' "
				+ "or gt.name like '%"+title+"%'"+ " or gt2.name like '%"+title+"%'" + " or gt3.name like '%"+title+"%') "
				+  whereStr;
		
		Page goodsPage = hibernateUtil.sqlPage(goodsCount, goodsSql, param.getPageNum(), param.getPageSize());
		
		String brandSql ="select DISTINCT gb.id,gb.name from Goods gs join goods_brand gb "
					+"on gs.goods_brand_id = gb.id and gs.state =1 and gs.grounding =1 and gb.state = 1 "
					+"and gs.title like '%"+param.getKey()+"%'"+whereStr;
		List<Object[]> brandSqlList = hibernateUtil.sql(brandSql);
		
		String countrySql ="select DISTINCT cbm.id,cbm.name from Goods gs join cross_border_museum cbm "
				+"on gs.country_id = cbm.id and gs.state =1 and gs.grounding =1 and cbm.state = 1 "
				+"and gs.title like '%"+param.getKey()+"%'"+whereStr;
		List<Object[]> countrySqlList = hibernateUtil.sql(countrySql);
		
		String goodsTypeSql ="select DISTINCT gt.id,gt.name from Goods gs join goods_type gt "
				+"on gs.goods_type1 = gt.id and gs.state =1 and gs.grounding =1 and gt.state = 1 "
				+"and gs.title like '%"+param.getKey()+"%'"+whereStr;
		List<Object[]> goodsTypeSqlList = hibernateUtil.sql(goodsTypeSql);
		
		List<GoodsSearchMainVo> goodsList = new ArrayList<GoodsSearchMainVo>();
		List<GoodsSearchVo> goodsSearchVoList = new ArrayList<GoodsSearchVo>();
		GoodsSearchVo goodsSearchVo = new GoodsSearchVo();
		for (int i = 0; i < goodsPage.getObjList().size(); i++) {
			GoodsSearchMainVo goodsSearchMainVo = new GoodsSearchMainVo();
			Object[] obj = (Object[])goodsPage.getObjList().get(i);
			if(obj[0]!=null){
				goodsSearchMainVo.setId(obj[0].toString());
			}
			if(obj[1]!=null){
				goodsSearchMainVo.setTitle(obj[1].toString());
			}
			String hql2 = "select ge.eventName,gej.eventPrice,ge.eventPrice,ge.discount,gej.quantity,ge.eventBegen,ge.eventEnd,gs.picSrc,ge.state,gej.state,ge.typeName,ge.homeChannel,gs.subtitle from Goods gs,"
					+ "GoodsEventJoin gej,GoodsEvent ge where gs.id = gej.goods.id and ge.id = gej.goodsEvent.id and gej.state = '1' and ge.state = '1' and ge.typeName!='满减' and gs.id ='"+obj[0].toString()+"'";
			List<Object> eventList = this.hibernateUtil.hql(hql2);
			Date currentTime = DateUtil.stringFormatToDate(
					DateUtil.dateFormatToString(new Date(), "yyyy/MM/dd HH:mm:ss"),
					"yyyy/MM/dd HH:mm:ss");
			if(eventList.size()>0){
				Object[] object = (Object[])eventList.get(0);
				Date begin = DateUtil.stringFormatToDate(object[5]+"",
						"yyyy/MM/dd HH:mm:ss");
				Date end = DateUtil.stringFormatToDate(object[6]+"",
						"yyyy/MM/dd HH:mm:ss");
				if (currentTime.after(begin) && currentTime.before(end)){
					if(null!=object[1]){
						goodsSearchMainVo.setPrice(object[1]+"");
					}else{
						if(null!=object[2]){
							goodsSearchMainVo.setPrice(object[2]+"");
						}else{
							if(null!=object[3]&&!"".equals(object[3]+"")){
								if(!"1".equals(object[3]+"")){
									float f = Float.parseFloat(String.valueOf(obj[2].toString()))*Float.parseFloat(object[3]+"");
									goodsSearchMainVo.setPrice(new DecimalFormat("0.00").format(f));	
								}else{
									float f = Float.parseFloat(String.valueOf(obj[2].toString()));
									goodsSearchMainVo.setPrice(new DecimalFormat("0.00").format(f));	
								}
							}else{
								float f = Float.parseFloat(String.valueOf(obj[2].toString()));
								goodsSearchMainVo.setPrice(new DecimalFormat("0.00").format(f));	
							}
						}
					}
				}else{
					if(obj[2]!=null){
						goodsSearchMainVo.setPrice(obj[2].toString());
					}
				}
			}else{
				if(obj[2]!=null){
					goodsSearchMainVo.setPrice(obj[2].toString());
				}
			}
			
			if(obj[3]!=null){
				goodsSearchMainVo.setPicSrc(obj[3].toString());
			}
			if(obj[4]!=null){
				goodsSearchMainVo.setShopId(obj[4].toString());
			}
			if(obj[5]!=null){
				Integer com = new Double((Double)obj[5]).intValue();
				goodsSearchMainVo.setComment(com.toString());
			}else{
				goodsSearchMainVo.setComment("0");
			}
			if(obj[6]!=null){
				goodsSearchMainVo.setSaleSum(obj[6].toString());
			}
			if(obj[7]!=null){
				goodsSearchMainVo.setShopName(obj[7].toString());
			}
			if(obj[8]!=null){
				goodsSearchMainVo.setMarketPrice(obj[8].toString());
			}
			goodsList.add(goodsSearchMainVo);
		}
		goodsSearchVo.setGoodsList(goodsList);
		List<SearchVo> brandList = new ArrayList<SearchVo>();
		for (int i = 0; i < brandSqlList.size(); i++){
			SearchVo searchVo = new SearchVo();
			Object[] brandObj = (Object[])brandSqlList.get(i);
			if(brandObj[0]!=null){
				searchVo.setId(brandObj[0].toString());
			}
			if(brandObj[1]!=null){
				searchVo.setName(brandObj[1].toString());
			}
			brandList.add(searchVo);
		}
		goodsSearchVo.setGoodsBrandList(brandList);
			
		List<SearchVo> countryList = new ArrayList<SearchVo>();
		for (int i = 0; i < countrySqlList.size(); i++){
			SearchVo searchVo = new SearchVo();
			Object[] countryObj = (Object[])countrySqlList.get(i);
			if(countryObj[0]!=null){
				searchVo.setId(countryObj[0].toString());
			}
			if(countryObj[1]!=null){
				searchVo.setName(countryObj[1].toString());
			}
			countryList.add(searchVo);
		}
		goodsSearchVo.setCountryList(countryList);
			
		List<SearchVo> goodsTypeList = new ArrayList<SearchVo>();
		for (int i = 0; i < goodsTypeSqlList.size(); i++){
			SearchVo searchVo = new SearchVo();
			Object[] goodsTypeObj = (Object[])goodsTypeSqlList.get(i);
			if(goodsTypeObj[0]!=null){
				searchVo.setId(goodsTypeObj[0].toString());
			}
			if(goodsTypeObj[1]!=null){
				searchVo.setName(goodsTypeObj[1].toString());
			}
			goodsTypeList.add(searchVo);
		}
		goodsSearchVo.setGoodsTypeList(goodsTypeList);
		
		goodsSearchVoList.add(goodsSearchVo);
		goodsPage.setVoList(goodsSearchVoList);
		return ObjectToResult.getVoResult(goodsPage);
	
	}
	/**
	 * 猜猜你喜欢
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result getLikeGoodsList(Parameter param) throws Exception {
		String hql = "from Goods where state = '1' order by createTime desc ";
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 猜猜你喜欢
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result getSaleNumGoodsList(Parameter param) throws Exception {
		String hql = "from Goods where state = '1' and grounding = '1' and id in (select gp.goods.id from GoodsParam gp where gp.state='1') "+param.getOrderBy();
		Page page = hibernateUtil.hqlPage(null,hql, param.getPageNum(), 5);
		return ObjectToResult.getResult(page);
	}
	@Override
	public Result saveSearchKeyName(Parameter param) throws Exception {
		String sql = "delete from search_key_name where state = "+param.getAction();
		hibernateUtil.execSql(sql);
		String[] key = param.getKey().split(":");
		Object res = null;
		for (int i = 0; i < key.length; i++) {
			SearchKeyName keyName = new SearchKeyName();
			keyName.setKeyName(key[i].split(",")[0]);
			keyName.setFontColor(key[i].split(",")[1]);
			keyName.setState(param.getAction());
			res = hibernateUtil.save(keyName);
		}
		return ObjectToResult.getResult(res);
	}
}

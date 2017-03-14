package com.ftoul.pc.search.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.goods.vo.GoodsSearchMainVo;
import com.ftoul.pc.goods.vo.GoodsSearchVo;
import com.ftoul.pc.goods.vo.SearchVo;
import com.ftoul.pc.search.service.SearchKeyNameServ;
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

		String order;
		if(param.getOrderBy()==null || param.getOrderBy().trim().equals("order by id desc")){
			order = " order by gs.sale_sum desc";
		}else{
			order = param.getOrderBy();
		}
		String goodsSql ="select gs.id,gs.title,gs.price,gs.pic_src,gs.shop_id,sum(uco.id),gs.sale_sum,bs.store_name,gp.market_price "
						+ "from Goods gs join business_store bs on gs.shop_id = bs.id "
						+ "join goods_param gp on gs.id = gp.goods_id and gp.state = 1 and gp.defalut = 1 "
						+ "left join user_comment uco on gs.id = uco.good_id "
						+ "where gs.state = 1 and gs.grounding  = 1 and gs.title like '%"+param.getKey()+"%' group by gs.id"+order ;
		
		String goodsCount ="select count(*) "
				+ "from Goods gs left join user_comment uco on gs.id = uco.good_id "
				+ "where gs.state = 1 and gs.grounding  = 1 and gs.title like '%"+param.getKey()+"%' group by gs.id "+order ;
		
		Page goodsPage = hibernateUtil.sqlPage(goodsCount, goodsSql, param.getPageNum(), param.getPageSize());
		
		String brandSql ="select DISTINCT gb.id,gb.name from Goods gs join goods_brand gb "
					+"on gs.goods_brand_id = gb.id and gs.state =1 and gs.grounding =1 and gb.state = 1 "
					+"and gs.title like '%"+param.getKey()+"%'" +order;
		
		String brandCount ="select count(*) from Goods gs join goods_brand gb "
				+"on gs.goods_brand_id = gb.id and gs.state =1 and gs.grounding =1 and gb.state = 1 "
				+"and gs.title like '%"+param.getKey()+"%'" +order;
		
		Page brandPage = hibernateUtil.sqlPage(brandCount, brandSql, param.getPageNum(), param.getPageSize());
		
		String countrySql ="select DISTINCT cbm.id,cbm.name from Goods gs join cross_border_museum cbm "
				+"on gs.country_id = cbm.id and gs.state =1 and gs.grounding =1 and cbm.state = 1 "
				+"and gs.title like '%"+param.getKey()+"%'" +order;
		
		String countryCount ="select count(*) from Goods gs join cross_border_museum cbm "
				+"on gs.country_id = cbm.id and gs.state =1 and gs.grounding =1 and cbm.state = 1 "
				+"and gs.title like '%"+param.getKey()+"%'" +order;
		
		Page countryPage = hibernateUtil.sqlPage(countryCount, countrySql, param.getPageNum(), param.getPageSize());
		
		String goodsTypeSql ="select DISTINCT gt.id,gt.name from Goods gs join goods_type gt "
				+"on gs.goods_type1 = gt.id and gs.state =1 and gs.grounding =1 and gt.state = 1 "
				+"and gs.title like '%"+param.getKey()+"%'"+" group by gt.id" +order;
		
		String goodsTypeCount ="select count(*) from Goods gs join goods_type gt "
				+"on gs.goods_type1 = gt.id and gs.state =1 and gs.grounding =1 and gt.state = 1 "
				+"and gs.title like '%"+param.getKey()+"%'" +order;
		Page goodsTypePage = hibernateUtil.sqlPage(goodsTypeCount, goodsTypeSql, param.getPageNum(), param.getPageSize());
		
		
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
			if(obj[2]!=null){
				goodsSearchMainVo.setPrice(obj[2].toString());
			}
			if(obj[3]!=null){
				goodsSearchMainVo.setPicSrc(obj[3].toString());
			}
			if(obj[4]!=null){
				goodsSearchMainVo.setShopId(obj[4].toString());
			}
			if(obj[5]!=null){
				goodsSearchMainVo.setComment(obj[5].toString());
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
		for (int i = 0; i < brandPage.getObjList().size(); i++){
			SearchVo searchVo = new SearchVo();
			Object[] brandObj = (Object[])brandPage.getObjList().get(i);
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
		for (int i = 0; i < countryPage.getObjList().size(); i++){
			SearchVo searchVo = new SearchVo();
			Object[] countryObj = (Object[])countryPage.getObjList().get(i);
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
		for (int i = 0; i < goodsTypePage.getObjList().size(); i++){
			SearchVo searchVo = new SearchVo();
			Object[] goodsTypeObj = (Object[])goodsTypePage.getObjList().get(i);
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
	
}

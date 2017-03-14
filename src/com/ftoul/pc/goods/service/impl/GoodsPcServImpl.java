package com.ftoul.pc.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.app.vo.GoodsParamAppVo;
import com.ftoul.app.vo.GoodsPicAppVo;
import com.ftoul.app.vo.GoodsPropAppVo;
import com.ftoul.app.vo.ShopVo;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goods.vo.GoodsVo;
import com.ftoul.pc.goods.service.GoodsPcServ;
import com.ftoul.pc.goods.vo.GoodsPcVo;
import com.ftoul.pc.goods.vo.GoodsSearchVo;
import com.ftoul.pc.goods.vo.SearchVo;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.GoodsProp;
import com.ftoul.po.GoodsUploadpic;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.logistics.LogisticsUtil;
import com.ftoul.web.business.service.BusinessWebServ;
import com.ftoul.web.business.vo.BusinessVo;
import com.ftoul.web.goods.service.GoodsServ;

@Service("GoodsPcServImpl")
public class GoodsPcServImpl implements GoodsPcServ {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private BusinessWebServ businessWebServ;
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private LogisticsUtil logisticsUtil;

	@Autowired
	private GoodsServ goodsServ;
	/**
	 * 
	 * 得到商品详情
	 * @param param
	 * Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsDetail(Parameter param) throws Exception {
		Result ret = goodsServ.getGoodsDetail(param);
		Result result = businessWebServ.getBusinessStorePageByGoodsId(param);
		GoodsVo goodsVo = (GoodsVo)ret.getObj();
		BusinessVo businessVo = (BusinessVo)result.getObj();
		GoodsPcVo goodsPcVo = new GoodsPcVo();
		goodsPcVo.setId(goodsVo.getId());
		goodsPcVo.setTitle(goodsVo.getTitle());
		goodsPcVo.setPrice(goodsVo.getPrice());
		goodsPcVo.setPicSrc(goodsVo.getPicSrc());
		if(null!=goodsVo.getQuantity()){
			goodsPcVo.setQuantity(goodsVo.getQuantity());
		}
		goodsPcVo.setGoodsBrandId(goodsVo.getGoodsBrandId());
		goodsPcVo.setStock(goodsVo.getStock());
		if(null!=goodsVo.getTypeName()){
			goodsPcVo.setTypeName(goodsVo.getTypeName());
		}
		goodsPcVo.setShopId(goodsVo.getShopId());
		goodsPcVo.setBusinessClassifyId(goodsVo.getBusinessClassifyId());
		if(null!=goodsVo.getGoodsLabel()){
			goodsPcVo.setGoodsLabel(goodsVo.getGoodsLabel());
		}
		if(null!=goodsVo.getSubtitle()){
			goodsPcVo.setSubTitle(goodsVo.getSubtitle());
		}
		goodsPcVo.setSumStock(goodsVo.getSumStock());
		goodsPcVo.setFreight(goodsVo.getFreight());
		goodsPcVo.setGrounding(goodsVo.getGrounding());
		goodsPcVo.setSaleSum(goodsVo.getSaleSum());
		goodsPcVo.setWeight(goodsVo.getWeight());
		goodsPcVo.setPackingLength(goodsVo.getPackingLength());
		goodsPcVo.setPackingWidth(goodsVo.getPackingWidth());
		goodsPcVo.setPackingHeight(goodsVo.getPackingHeight());
		goodsPcVo.setPackingList(goodsVo.getPackingList());
		goodsPcVo.setAfterService(goodsVo.getAfterService());
		if(null!=goodsVo.getGoodsEventName()){
			goodsPcVo.setGoodsEventName(goodsVo.getGoodsEventName());
		}
		if(null!=goodsVo.getEventPrice()){
			goodsPcVo.setEventPrice(goodsVo.getEventPrice());
		}
		//商品参数
		List<GoodsParamAppVo> paramAppList = new ArrayList<GoodsParamAppVo>(); 
		for (int i = 0; i < goodsVo.getGoodsParamList().size(); i++) {
			GoodsParamAppVo paramAppVo = new GoodsParamAppVo();
			GoodsParam goodsParam = goodsVo.getGoodsParamList().get(i);
			paramAppVo.setId(goodsParam.getId());
			paramAppVo.setGoodsId(goodsParam.getGoods().getId());
			paramAppVo.setParamName(goodsParam.getParamName());
			paramAppVo.setPrice(goodsParam.getPrice());
			paramAppVo.setStock(goodsParam.getStock());
			paramAppVo.setSaleNumber(goodsParam.getSaleNumber());
			paramAppVo.setMarketPrice(goodsParam.getMarketPrice());
			paramAppList.add(paramAppVo);
		}
		goodsPcVo.setGoodsParamList(paramAppList);
		
		//前台商品图
		List<GoodsPicAppVo> picAppList = new ArrayList<GoodsPicAppVo>(); 
		for (int i = 0; i < goodsVo.getGoodsPicList().size(); i++) {
			GoodsPicAppVo goodsPicVo = new GoodsPicAppVo();
			GoodsUploadpic goodsUploadpic = goodsVo.getGoodsPicList().get(i);
			goodsPicVo.setPicSrc(goodsUploadpic.getPicSrc());
			picAppList.add(goodsPicVo);
		}
		goodsPcVo.setGoodsPicList(picAppList);
		
		//前台商品详情图
		List<GoodsPicAppVo> picInfoAppList = new ArrayList<GoodsPicAppVo>(); 
		for (int i = 0; i < goodsVo.getGoodsPicInfoList().size(); i++) {
			GoodsPicAppVo goodsPicVo = new GoodsPicAppVo();
			GoodsUploadpic goodsUploadpic = goodsVo.getGoodsPicInfoList().get(i);
			goodsPicVo.setPicSrc(goodsUploadpic.getPicSrc());
			picInfoAppList.add(goodsPicVo);
		}
		goodsPcVo.setGoodsPicInfoList(picInfoAppList);
		
		//商品规格
		List<GoodsPropAppVo> propAppList = new ArrayList<GoodsPropAppVo>();
		for (int i = 0; i < goodsVo.getGoodsPropList().size(); i++) {
			GoodsPropAppVo propAppVo = new GoodsPropAppVo();
			GoodsProp goodsProp = goodsVo.getGoodsPropList().get(i);
			propAppVo.setContent(goodsProp.getContent());
			if(null!=goodsProp.getGoodsPropertyTypeInfo()){
				propAppVo.setName(goodsProp.getGoodsPropertyTypeInfo().getName());
			}
			propAppList.add(propAppVo);
		}
		goodsPcVo.setGoodsPropList(propAppList);
		//店铺信息
		ShopVo shopVo = new ShopVo();
		shopVo.setBusinessStoreId(businessVo.getBusinessStoreId());
		shopVo.setStoreId(businessVo.getStoreId());
		shopVo.setStoreName(businessVo.getStoreName());
		shopVo.setStorePic(businessVo.getPic());
		shopVo.setGoodsSaleNum(businessVo.getGoodsSaleNum());
		shopVo.setGoodsNum(businessVo.getGoodsNum());
		shopVo.setGoodsMonthNum(businessVo.getGoodsMonthNum());
		goodsPcVo.setShopVo(shopVo);
		if(null!=goodsVo.getGoodsTypeNameOne()){
			goodsPcVo.setGoodsTypeNameOne(goodsVo.getGoodsTypeNameOne());
		}
		if(null!=goodsVo.getGoodsTypeNameTwo()){
			goodsPcVo.setGoodsTypeNameTwo(goodsVo.getGoodsTypeNameTwo());
		}
		if(null!=goodsVo.getGoodsTypeNameThree()){
			goodsPcVo.setGoodsTypeNameThree(goodsVo.getGoodsTypeNameThree());
		}
		//是否参加满减
		goodsPcVo.setFullCutName(goodsVo.getFullCutName());
		return ObjectToResult.getResult(goodsPcVo);
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
	@Override
	public Result getGoodsBySearchName(Parameter param) throws Exception {
		String goodsSql ="select gs.id,gs.title,gs.price,gs.pic_src,gs.shop_id,ifnull(sum(uco.id),0) "
						+ "from Goods gs left join user_comment uco on gs.id = uco.good_id "
						+ "where gs.state = 1 and gs.grounding  = 1 and gs.title like '%"+param.getKey()+"%' group by gs.id" ;
		
		String goodsCount ="select count(*) "
				+ "from Goods gs left join user_comment uco on gs.id = uco.good_id "
				+ "where gs.state = 1 and gs.grounding  = 1 and gs.title like '%"+param.getKey()+"%' group by gs.id" ;
		
		Page goodsPage = hibernateUtil.sqlPage(goodsCount, goodsSql, param.getPageNum(), param.getPageSize());
		
		String brandSql ="select DISTINCT gb.id,gb.name from Goods gs join goods_brand gb "
					+"on gs.goods_brand_id = gb.id and gs.state =1 and gs.grounding =1 and gb.state = 1 "
					+"and gs.title like '%"+param.getKey()+"%'" ;
		
		String brandCount ="select count(*) from Goods gs join goods_brand gb "
				+"on gs.goods_brand_id = gb.id and gs.state =1 and gs.grounding =1 and gb.state = 1 "
				+"and gs.title like '%"+param.getKey()+"%'" ;
		
		Page brandPage = hibernateUtil.sqlPage(brandCount, brandSql, param.getPageNum(), param.getPageSize());
		
		String countrySql ="select DISTINCT cbm.id,cbm.name from Goods gs join cross_border_museum cbm "
				+"on gs.country_id = cbm.id and gs.state =1 and gs.grounding =1 and cbm.state = 1 "
				+"and gs.title like '%"+param.getKey()+"%'" ;
		
		String countryCount ="select count(*) from Goods gs join cross_border_museum cbm "
				+"on gs.country_id = cbm.id and gs.state =1 and gs.grounding =1 and cbm.state = 1 "
				+"and gs.title like '%"+param.getKey()+"%'" ;
		
		Page countryPage = hibernateUtil.sqlPage(countryCount, countrySql, param.getPageNum(), param.getPageSize());
		
		String goodsTypeSql ="select DISTINCT gt.id,gt.name from Goods gs join goods_type gt "
				+"on gs.goods_type1 = gt.id and gs.state =1 and gs.grounding =1 and gt.state = 1 "
				+"and gs.title like '%"+param.getKey()+"%'"+" group by gt.id" ;
		
		String goodsTypeCount ="select count(*) from Goods gs join goods_type gt "
				+"on gs.goods_type1 = gt.id and gs.state =1 and gs.grounding =1 and gt.state = 1 "
				+"and gs.title like '%"+param.getKey()+"%'" ;
		Page goodsTypePage = hibernateUtil.sqlPage(goodsTypeCount, goodsTypeSql, param.getPageNum(), param.getPageSize());
		
		
		List<GoodsSearchVo> goodsList = new ArrayList<GoodsSearchVo>();
		for (int i = 0; i < goodsPage.getObjList().size(); i++) {
			GoodsSearchVo goodsSearchVo = new GoodsSearchVo();
			Object[] obj = (Object[])goodsPage.getObjList().get(i);
			if(obj[0]!=null){
				goodsSearchVo.setId(obj[0].toString());
			}
			if(obj[1]!=null){
				goodsSearchVo.setTitle(obj[1].toString());
			}
			if(obj[2]!=null){
				goodsSearchVo.setPrice(obj[2].toString());
			}
			if(obj[3]!=null){
				goodsSearchVo.setPicSrc(obj[3].toString());
			}
			if(obj[4]!=null){
				goodsSearchVo.setShopId(obj[4].toString());
			}
			if(obj[5]!=null){
				goodsSearchVo.setComment(obj[5].toString());
			}
			if(i==0){
				List<SearchVo> brandList = new ArrayList<SearchVo>();
				for (int j = 0; j < brandPage.getObjList().size(); j++){
					SearchVo searchVo = new SearchVo();
					Object[] brandObj = (Object[])goodsPage.getObjList().get(j);
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
				for (int k = 0; k < countryPage.getObjList().size(); k++){
					SearchVo searchVo = new SearchVo();
					Object[] countryObj = (Object[])countryPage.getObjList().get(k);
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
				for (int l = 0; l < goodsTypePage.getObjList().size(); l++){
					SearchVo searchVo = new SearchVo();
					Object[] goodsTypeObj = (Object[])goodsTypePage.getObjList().get(l);
					if(goodsTypeObj[0]!=null){
						searchVo.setId(goodsTypeObj[0].toString());
					}
					if(goodsTypeObj[1]!=null){
						searchVo.setName(goodsTypeObj[1].toString());
					}
					goodsTypeList.add(searchVo);
				}
				goodsSearchVo.setGoodsTypeList(goodsTypeList);
			}
			goodsList.add(goodsSearchVo);
		}
		goodsPage.setVoList(goodsList);
		return ObjectToResult.getVoResult(goodsPage);
	}
	
}

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
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.coupon.vo.GoodsTypeVo;
import com.ftoul.manage.goods.vo.GoodsVo;
import com.ftoul.pc.goods.service.GoodsPcServ;
import com.ftoul.pc.goods.vo.BrandVo;
import com.ftoul.pc.goods.vo.GoodsBrandVo;
import com.ftoul.pc.goods.vo.GoodsPcVo;
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
	public Result getGoodsBrand(Parameter parameter) throws Exception {
		String sql ="select id,name,logo from Goods_Brand where state = 1 group by id ORDER BY RAND()  LIMIT 18" ;
		List<Object[]> goodsBrandList = hibernateUtil.sql(sql);
		String adventSql ="select id,pic_address from index_carousel_pic where carousel_type = 5" ;
		List<Object[]> adventList = hibernateUtil.sql(adventSql);
		List<BrandVo> goodsBrandVoList = new ArrayList<BrandVo>();
		GoodsBrandVo goodsBrandVo = new GoodsBrandVo();
		for (int i = 0; i < goodsBrandList.size(); i++) {
			BrandVo brandVo = new BrandVo();
			Object[] obj = (Object[])goodsBrandList.get(i);
			if(obj[0]!=null){
				brandVo.setId(obj[0].toString());
			}
			if(obj[1]!=null){
				brandVo.setName(obj[1].toString());
			}
			if(obj[2]!=null){
				brandVo.setLogo(obj[2].toString());
			}
			goodsBrandVoList.add(brandVo);
		}
		goodsBrandVo.setBrandList(goodsBrandVoList);
		if(adventList.size()>0){
			Object[] obj =  (Object[])adventList.get(0);
			if(obj[1]!=null){
				goodsBrandVo.setAdventLogo(obj[1].toString());
			}
		}
		return ObjectToResult.getResult(goodsBrandVo);
	}
	@Override
	public Result getGoodsBrandByTypeId1(Parameter parameter) throws Exception {
		String sql = "select gb.id,gb.name,gb.logo from goods gs join goods_brand gb on gs.goods_brand_id = gb.id "
					 +"and gs.goods_type1 = '"+ parameter.getKey()+"' "
					 +"group by gb.id ORDER BY RAND() LIMIT 8";
		List<Object[]> goodsBrandList = hibernateUtil.sql(sql);
		List<BrandVo> goodsBrandVoList = new ArrayList<BrandVo>();
		for (int i = 0; i < goodsBrandList.size(); i++) {
			BrandVo goodsBrandVo = new BrandVo();
			Object[] obj = (Object[])goodsBrandList.get(i);
			if(obj[0]!=null){
				goodsBrandVo.setId(obj[0].toString());
			}
			if(obj[1]!=null){
				goodsBrandVo.setName(obj[1].toString());
			}
			if(obj[2]!=null){
				goodsBrandVo.setLogo(obj[2].toString());
			}
			goodsBrandVoList.add(goodsBrandVo);
		}
		return ObjectToResult.getResult(goodsBrandVoList);
	}
	@Override
	public Result getGoodsBrandByTypeId2(Parameter parameter) throws Exception {
		String sql = "select gb.id,gb.name,gb.logo from goods gs join goods_brand gb on gs.goods_brand_id = gb.id "
					 +"and gs.goods_type2 = '"+ parameter.getKey()+"' "
					 +"group by gb.id ORDER BY RAND() LIMIT 4";
		List<Object[]> goodsBrandList = hibernateUtil.sql(sql);
		List<BrandVo> goodsBrandVoList = new ArrayList<BrandVo>();
		for (int i = 0; i < goodsBrandList.size(); i++) {
			BrandVo goodsBrandVo = new BrandVo();
			Object[] obj = (Object[])goodsBrandList.get(i);
			if(obj[0]!=null){
				goodsBrandVo.setId(obj[0].toString());
			}
			if(obj[1]!=null){
				goodsBrandVo.setName(obj[1].toString());
			}
			if(obj[2]!=null){
				goodsBrandVo.setLogo(obj[2].toString());
			}
			goodsBrandVoList.add(goodsBrandVo);
		}
		return ObjectToResult.getResult(goodsBrandVoList);
	}
	@Override
	public Result getGoodsType2(Parameter parameter) throws Exception {
		String sql = "select id,name,level from goods_type where state = 1 and level =2 ORDER BY RAND() LIMIT 6";
		List<Object[]> goodsTypeList = hibernateUtil.sql(sql);
		List<GoodsTypeVo> goodsTypeVoList = new ArrayList<GoodsTypeVo>();
		for (int i = 0; i < goodsTypeList.size(); i++) {
			GoodsTypeVo goodsTypeVo = new GoodsTypeVo();
			Object[] obj = (Object[])goodsTypeList.get(i);
			if(obj[0]!=null){
				goodsTypeVo.setId(obj[0].toString());
			}
			if(obj[1]!=null){
				goodsTypeVo.setName(obj[1].toString());
			}
			if(obj[2]!=null){
				goodsTypeVo.setLevel(obj[2].toString());
			}
			goodsTypeVoList.add(goodsTypeVo);
		}
		return ObjectToResult.getResult(goodsTypeVoList);
	}
	
}

package com.ftoul.app.action.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.app.action.goods.service.GoodsAppServ;
import com.ftoul.app.vo.CollectionAppVo;
import com.ftoul.app.vo.GoodsAppVo;
import com.ftoul.app.vo.GoodsParamAppVo;
import com.ftoul.app.vo.GoodsPicAppVo;
import com.ftoul.app.vo.GoodsPropAppVo;
import com.ftoul.app.vo.GoodsWebVo;
import com.ftoul.app.vo.ShopVo;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goods.vo.GoodsVo;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.GoodsProp;
import com.ftoul.po.GoodsUploadpic;
import com.ftoul.po.UserCollection;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.logistics.LogisticsUtil;
import com.ftoul.util.price.PriceUtil;
import com.ftoul.web.business.service.BusinessWebServ;
import com.ftoul.web.business.vo.BusinessVo;
import com.ftoul.web.goods.service.GoodsBrandServ;
import com.ftoul.web.goods.service.GoodsPropTypeServ;
import com.ftoul.web.goods.service.GoodsServ;
import com.ftoul.web.goods.service.GoodsTypeServ;
import com.ftoul.web.goods.service.UserCollectionServ;

/**
* 
*
* 类描述：商品业务实现类
* @author: yw
* @date： 日期：2016年8月8日 时间：上午10:01:10
* @version 1.0
*
*/
@Service("GoodsAppServImpl")
public class GoodsAppServImpl implements GoodsAppServ {

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
	private LogisticsUtil logisticsUtil;
	
	@Autowired
	private GoodsServ goodsServ;
	
	@Autowired
	private BusinessWebServ businessWebServ;
	
	@Autowired
	private UserCollectionServ userCollectionServ;
	/**
	 * 
	 * 得到商品详情
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsDetail(Parameter param) throws Exception {
		Result ret = goodsServ.getGoodsDetail(param);
		Result result = businessWebServ.getBusinessStorePageByGoodsId(param);
		GoodsVo goodsVo = (GoodsVo)ret.getObj();
		BusinessVo businessVo = (BusinessVo)result.getObj();
		GoodsAppVo goodsAppVo = new GoodsAppVo();
		goodsAppVo.setId(goodsVo.getId());
		goodsAppVo.setTitle(goodsVo.getTitle());
		goodsAppVo.setPrice(goodsVo.getPrice());
		goodsAppVo.setPicSrc(goodsVo.getPicSrc());
		if(null!=goodsVo.getQuantity()){
			goodsAppVo.setQuantity(goodsVo.getQuantity());
		}
		goodsAppVo.setGoodsBrandId(goodsVo.getGoodsBrandId());
		goodsAppVo.setStock(goodsVo.getStock());
		if(null!=goodsVo.getTypeName()){
			goodsAppVo.setTypeName(goodsVo.getTypeName());
		}
		goodsAppVo.setShopId(goodsVo.getShopId());
		goodsAppVo.setBusinessClassifyId(goodsVo.getBusinessClassifyId());
		if(null!=goodsVo.getGoodsLabel()){
			goodsAppVo.setGoodsLabel(goodsVo.getGoodsLabel());
		}
		if(null!=goodsVo.getSubtitle()){
			goodsAppVo.setSubTitle(goodsVo.getSubtitle());
		}
		goodsAppVo.setSumStock(goodsVo.getSumStock());
		goodsAppVo.setFreight(goodsVo.getFreight());
		goodsAppVo.setGrounding(goodsVo.getGrounding());
		goodsAppVo.setSaleSum(goodsVo.getSaleSum());
		if(null!=goodsVo.getGoodsEventName()){
			goodsAppVo.setGoodsEventName(goodsVo.getGoodsEventName());
		}
		if(null!=goodsVo.getEventPrice()){
			goodsAppVo.setEventPrice(goodsVo.getEventPrice());
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
		goodsAppVo.setGoodsParamList(paramAppList);
		
		//前台商品图
		List<GoodsPicAppVo> picAppList = new ArrayList<GoodsPicAppVo>(); 
		for (int i = 0; i < goodsVo.getGoodsPicList().size(); i++) {
			GoodsPicAppVo goodsPicVo = new GoodsPicAppVo();
			GoodsUploadpic goodsUploadpic = goodsVo.getGoodsPicList().get(i);
			goodsPicVo.setPicSrc(goodsUploadpic.getPicSrc());
			picAppList.add(goodsPicVo);
		}
		goodsAppVo.setGoodsPicList(picAppList);
		
		//前台商品详情图
		List<GoodsPicAppVo> picInfoAppList = new ArrayList<GoodsPicAppVo>(); 
		for (int i = 0; i < goodsVo.getGoodsPicInfoList().size(); i++) {
			GoodsPicAppVo goodsPicVo = new GoodsPicAppVo();
			GoodsUploadpic goodsUploadpic = goodsVo.getGoodsPicInfoList().get(i);
			goodsPicVo.setPicSrc(goodsUploadpic.getPicSrc());
			picInfoAppList.add(goodsPicVo);
		}
		goodsAppVo.setGoodsPicInfoList(picInfoAppList);
		
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
		goodsAppVo.setGoodsPropList(propAppList);
		//店铺信息
		ShopVo shopVo = new ShopVo();
		shopVo.setStoreId(goodsVo.getShopId());
		shopVo.setStoreName(businessVo.getStoreName());
		shopVo.setStorePic(businessVo.getPic());
		shopVo.setGoodsSaleNum(businessVo.getGoodsSaleNum());
		shopVo.setGoodsNum(businessVo.getGoodsNum());
		shopVo.setGoodsMonthNum(businessVo.getGoodsMonthNum());
		goodsAppVo.setShopVo(shopVo);
		return ObjectToResult.getResult(goodsAppVo);
	}

	@Override
	public Result getUserCollectionList(Parameter param) throws Exception {
		Result result = userCollectionServ.getUserCollectionList(param);
		List<UserCollection> list = (List<UserCollection>)result.getObj();
		List<CollectionAppVo> goodsVoList = new ArrayList<CollectionAppVo>();
		for (int i = 0; i < list.size(); i++) {
			UserCollection userCollection = list.get(i);
			CollectionAppVo collectionAppVo = new CollectionAppVo();
			collectionAppVo.setId(userCollection.getId());
			collectionAppVo.setGoodsId(userCollection.getGoods().getId());
			collectionAppVo.setGoodsPic(userCollection.getGoods().getPicSrc());
			collectionAppVo.setTitle(userCollection.getGoods().getTitle());
			collectionAppVo.setPrice(userCollection.getGoods().getPrice());
			goodsVoList.add(collectionAppVo);
		}
		return ObjectToResult.getResult(goodsVoList);
	}
	
}
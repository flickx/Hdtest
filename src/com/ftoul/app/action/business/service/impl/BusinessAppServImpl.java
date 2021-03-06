package com.ftoul.app.action.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.app.action.business.service.BusinessAppServ;
import com.ftoul.app.vo.GoodsWebVo;
import com.ftoul.app.vo.ShopVo;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.Goods;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.business.service.BusinessWebServ;
import com.ftoul.web.business.vo.BusinessVo;

/**
 * 
 * @author wenyujie
 * 
 */
@Service("BusinessAppServImpl")
public class BusinessAppServImpl implements BusinessAppServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private BusinessWebServ businessWebServ;

	/**
	 * 
	 * 得到店铺商品列表（带分页）
	 * 
	 * @param param
	 *            Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getStoreGoodsPage(Parameter param) throws Exception {
		Result result = businessWebServ.getStoreGoodsPage(param);
		List<Goods> list = (List<Goods>)result.getObj();
		List<GoodsWebVo> goodsVoList = new ArrayList<GoodsWebVo>();
		for (int i = 0; i < list.size(); i++) {
			Goods goods = list.get(i);
			GoodsWebVo goodsVo = new GoodsWebVo();
			goodsVo.setId(goods.getId());
			goodsVo.setGoodsPic(goods.getPicSrc());
			goodsVo.setTitle(goods.getTitle());
			goodsVo.setPrice(goods.getPrice());
			goodsVoList.add(goodsVo);
		}
		result.setObj(goodsVoList);
		return result;

	}

	/**
	 * 
	 * 获取店铺详情以及商品统计
	 * 
	 * @param param
	 *            Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getBusinessStorePage(Parameter param) throws Exception {
		Result result = businessWebServ.getBusinessStorePage(param);
		BusinessVo businessVo = (BusinessVo)result.getObj();
		ShopVo shopVo = new ShopVo();
		shopVo.setStoreId(businessVo.getStoreId());
		shopVo.setBusinessStoreId(businessVo.getBusinessStoreId());
		shopVo.setStoreName(businessVo.getStoreName());
		shopVo.setStorePic(businessVo.getPic());
		shopVo.setGoodsMonthNum(businessVo.getGoodsMonthNum());
		shopVo.setGoodsNum(businessVo.getGoodsNum());
		shopVo.setGoodsSaleNum(businessVo.getGoodsSaleNum());
		shopVo.setVerifyTime(businessVo.getCreateTime());
		shopVo.setSummary(businessVo.getSummary());
		return ObjectToResult.getResult(shopVo);
	}

	/**
	 * 
	 * 根据商品ID获取店铺详情以及商品统计
	 * 
	 * @param param
	 *            Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getBusinessStorePageByGoodsId(Parameter param)
			throws Exception {
		return businessWebServ.getBusinessStorePageByGoodsId(param);
	}

	/**
	 * 
	 * 根据商品分类得到店铺商品列表（带分页）
	 * 
	 * @param param
	 *            Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getStoreGoodsPagebyStoreClassify(Parameter param)
			throws Exception {
		Result result = businessWebServ.getStoreGoodsPagebyStoreClassify(param);
		List<Goods> list = (List<Goods>)result.getObj();
		List<GoodsWebVo> goodsVoList = new ArrayList<GoodsWebVo>();
		for (int i = 0; i < list.size(); i++) {
			Goods goods = list.get(i);
			GoodsWebVo goodsVo = new GoodsWebVo();
			goodsVo.setId(goods.getId());
			goodsVo.setGoodsPic(goods.getPicSrc());
			goodsVo.setTitle(goods.getTitle());
			goodsVo.setPrice(goods.getPrice());
			goodsVoList.add(goodsVo);
		}
		result.setObj(goodsVoList);
		return result;

	}
}

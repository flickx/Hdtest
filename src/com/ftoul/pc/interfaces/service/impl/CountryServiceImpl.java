package com.ftoul.pc.interfaces.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.interfaces.service.CountryService;
import com.ftoul.pc.interfaces.vo.PcCountryTypeVo;
import com.ftoul.pc.interfaces.vo.PcCountryVo;
import com.ftoul.pc.interfaces.vo.PcNewGoods;
import com.ftoul.po.CrossBorderMuseum;
import com.ftoul.po.Goods;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("CountryServiceImpl")
public class CountryServiceImpl implements CountryService{
	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * pc端国家馆二级页面获取所有国家接口
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	public Result getCountryList(Parameter param) throws Exception{
//		//查询所有国家
//		String sql ="select DISTINCT country_id from goods where state=1 and country_id is not null and crossborder = 1";
//		List<Object[]> countryList = hibernateUtil.sql(sql);
//		List<PcCountryVo> countryVoList = new ArrayList<PcCountryVo>();
//		for (Object[] objects : countryList) {
//			PcCountryVo country = new PcCountryVo();
//			if (null!=objects[0]) {
//				country.setId(objects[0].toString());
//			}
//			if (null!=objects[1]) {
//				country.setName(objects[1].toString());
//			}
//			countryVoList.add(country);
//		}
		String hql = "from CrossBorderMuseum where state = '1'";
		List<Object> o  = hibernateUtil.hql(hql);
		List<PcCountryVo> countryVoList = new ArrayList<PcCountryVo>();
		for (Object object : o) {
			CrossBorderMuseum c = (CrossBorderMuseum)object;
			PcCountryVo country = new PcCountryVo();
			country.setId(c.getId());
			country.setName(c.getName());
			countryVoList.add(country);
		}
		return ObjectToResult.getResult(countryVoList);
	}

	/**
	 * pc端国家馆二级页面获取所有有进口商品的商品分类接口
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result getCountryTypeList(Parameter param) throws Exception {
		String hql = "from Goods where state = 1 and crossborder = 1 and crossBorderMuseum.id = '"+param.getId()+"' group by goodsType1.id";
		List<Object> goodsList = (List<Object>)hibernateUtil.hql(hql);
		List<Object> typeList = new ArrayList<Object>();
		for (Object object : goodsList) {
			Goods g = (Goods)object;
			PcCountryTypeVo o = new PcCountryTypeVo();
			o.setId(g.getGoodsType1().getId());
			o.setName(g.getGoodsType1().getName());
			typeList.add(o);
		}
		return ObjectToResult.getResult(typeList);
	}
	/**
	 * pc端国家馆二级页面通过国家ID,一级分类获取商品接口
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result getCountryGoodsList(Parameter param) throws Exception {
		//查询所有国家
		String countSql = "select count(*) from Goods where state = '1' and country_id ='"+param.getId()+"'";
		String sql = "select g.id,g.title,g.subtitle,gp.param_name,g.price,"
				+ "gp.market_price,g.pic_src from Goods g,Goods_param gp "
				+ "where g.id = gp.goods_id and g.state = '1' and g.shop_id = '1' and g.country_id = '"+param.getId()+ "' ";
		if (param.getKey()!=null) {
			sql = sql + " and g.goods_type1 ='"+param.getKey()+"'";
			countSql = countSql + " and goods_type1 ='"+param.getKey()+"'";
		}		
		sql = sql + " group by g.id order by g.create_time asc";
		Page page = hibernateUtil.sqlPage(countSql,sql,param.getPageNum(),param.getPageSize());
		List<PcNewGoods> list = new ArrayList<PcNewGoods>();
		for (int i = 0; i < page.getObjList().size(); i++) {
			PcNewGoods goodsListVo = new PcNewGoods();
			Object[] obj = (Object[])page.getObjList().get(i);
			if(obj[0]!=null)
				goodsListVo.setGoodsId(obj[0].toString());
			if(obj[1]!=null)
				goodsListVo.setTitle(obj[1].toString());
			if(obj[2]!=null)
				goodsListVo.setSubTitle(obj[2].toString());
			if(obj[3]!=null)
				goodsListVo.setModel(obj[3].toString());
			if(obj[4]!=null)
				goodsListVo.setPrice(Double.parseDouble(obj[4].toString()));
			if(obj[5]!=null)
				goodsListVo.setMarketPrice(Double.parseDouble(obj[5].toString()));
			if(obj[6]!=null)
				goodsListVo.setPicSrc(obj[6].toString());
			if(obj[4]!=null && obj[5]!=null)
				goodsListVo.setNum(Double.toString(Math.round((double)obj[4]*1.0/Double.parseDouble(obj[5].toString())*10)));
			list.add(goodsListVo);
		}
		page.setVoList(list);
		return ObjectToResult.getVoResult(page);
	}
}

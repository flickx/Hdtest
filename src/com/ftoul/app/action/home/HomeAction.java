/**
 * 
 */
package com.ftoul.app.action.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.app.vo.IndexCarouselAppVo;
import com.ftoul.app.vo.IndexGoodsAppVo;
import com.ftoul.common.Common;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.Goods;
import com.ftoul.po.IndexCarouselPic;
import com.ftoul.web.home.service.HomeServ;

/**
 * @author 李丁
 * @date:2016年8月15日 下午3:42:16
 * @类说明 : 前台首页
 */
@Controller("AppHomeAction")
@RequestMapping(value = "/app/home")
public class HomeAction {
	@Autowired
	private HomeServ homeServ;
	
	/**
	 * 获取首页轮播图列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getIndexCarouselList") 
	public @ResponseBody Result getIndexCarouselList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result re =  homeServ.getAppIndexCarouselList(parameter);
		List<IndexCarouselPic> index = (List<IndexCarouselPic>)re.getObj();
		List<IndexCarouselAppVo> indexCarouselAppVoList = new ArrayList<IndexCarouselAppVo>();
		for (IndexCarouselPic indexCarouselPic : index) {
			IndexCarouselAppVo i  =new IndexCarouselAppVo();
			i.setPicAddress(indexCarouselPic.getPicAddress());
			i.setUrl(indexCarouselPic.getUrl());
			indexCarouselAppVoList.add(i);
		}
		return ObjectToResult.getResult(indexCarouselAppVoList);
	}
	/**
	 * 获取server time
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getServerTime") 
	public @ResponseBody Result getServerTime(String param) throws Exception{
		Long resTime = System.currentTimeMillis();
		Result result = ObjectToResult.getResult(resTime);
		return result;
	}
	/**
	 * 插入用户短信记录
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "insertSmsInfo") 
	public @ResponseBody Result insertSmsInfo(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return homeServ.insertSmsInfo(parameter);
	}
	/**
	 * app首页一级分类栏目(美肤个护，环球美食，家居生活)
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getAppGoodsByGoodsType") 
	public @ResponseBody Result getAppGoodsByGoodsType(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result re =  homeServ.getAppGoodsByGoodsType(parameter);
		List<Goods> goodsList = (List<Goods>)re.getObj();
		List<IndexGoodsAppVo> goodsAppVoList = new ArrayList<IndexGoodsAppVo>();
		for (Goods goods : goodsList) {
			IndexGoodsAppVo i  =new IndexGoodsAppVo();
			i.setGoodsId(goods.getId());
			i.setPicSrc(goods.getPicSrc());
			i.setPrice(goods.getPrice());
			i.setTitle(goods.getTitle());
			goodsAppVoList.add(i);
		}
		return ObjectToResult.getResult(goodsAppVoList);
	}
	/**
	 * ios获取首页轮播图列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getIosIndexCarouselList") 
	public @ResponseBody Result getIosIndexCarouselList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result re =  homeServ.getIosIndexCarouselList(parameter);
		List<IndexCarouselPic> index = (List<IndexCarouselPic>)re.getObj();
		List<IndexCarouselAppVo> indexCarouselAppVoList = new ArrayList<IndexCarouselAppVo>();
		for (IndexCarouselPic indexCarouselPic : index) {
			IndexCarouselAppVo i  =new IndexCarouselAppVo();
			i.setPicAddress(indexCarouselPic.getPicAddress());
			i.setUrl(indexCarouselPic.getUrl());
			indexCarouselAppVoList.add(i);
		}
		return ObjectToResult.getResult(indexCarouselAppVoList);
	}

}

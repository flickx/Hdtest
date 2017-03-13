package com.ftoul.pc.store.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.businessManage.goods.service.BusinessClassifyServ;
import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.store.service.StoreServ;
import com.ftoul.web.business.service.BusinessWebServ;
@Controller
@RequestMapping(value="/pc/store")
public class StoreAction {
	@Autowired
	private StoreServ storeServ;
	@Autowired
	private BusinessClassifyServ businessClassifyServ;
	
	@Autowired
	private BusinessWebServ businessWebServ;
	/**
	 * 
	 * 得到店铺商铺列表
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value="getStoreGoodsPage")
	public @ResponseBody Result getStoreGoodsPage(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return storeServ.getStoreGoodsPage(parameter);
	}
	/**
	 * 
	 * 根据商品ID获取店铺详情以及商品统计
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */ 
	@RequestMapping(value="getBusinessStorePageByGoodsId")
	public @ResponseBody Result getBusinessStorePageByGoodsId(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessWebServ.getBusinessStorePageByGoodsId(parameter);
	}
	/**
	 * 根据店铺Id查询店铺分类
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getBusinessClassifyByShopId")  
	public @ResponseBody Result getBusinessClassifyByShopId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessClassifyServ.getBusinessClassifyByShopId(parameter);
	}
	/**
	 * 
	 * 根据商铺分类得到店铺商铺列表
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value="getStoreGoodsPageByBusinessClassify")
	public @ResponseBody Result getStoreGoodsPageByBusinessClassify(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return storeServ.getStoreGoodsPageByBusinessClassify(parameter);
	}
	/**
	 * 
	 * 根据店铺id获取店铺简介，营业执照
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value="getStoreSummary")
	public @ResponseBody Result getStoreSummary(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return storeServ.getStoreSummary(parameter);
	}
}

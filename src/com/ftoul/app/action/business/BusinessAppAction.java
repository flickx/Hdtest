package com.ftoul.app.action.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.app.action.business.service.BusinessAppServ;
import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
/**
 * 
 * @author wenyujie
 * 商家
 *
 */
@Controller("BusinessAppAction")
@RequestMapping(value="/app/business")
public class BusinessAppAction {
	@Autowired
	private BusinessAppServ businessAppServ;
	/**
	 * 
	 * 得到店铺商铺列表（带分页）
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value="getStoreGoodsPage")
	public @ResponseBody Result getStoreGoodsPage(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessAppServ.getStoreGoodsPage(parameter);
	}
	/**
	 * 
	 * 获取店铺详情以及商品统计
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */ 
	@RequestMapping(value="getBusinessStorePage")
	public @ResponseBody Result getBusinessStorePage(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessAppServ.getBusinessStorePage(parameter);
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
		return businessAppServ.getBusinessStorePageByGoodsId(parameter);
	}
	/**
	 * 
	 * 根据商品分类得到店铺商铺列表（带分页）
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value="getStoreGoodsPagebyStoreClassify")
	public @ResponseBody Result getStoreGoodsPagebyStoreClassify(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessAppServ.getStoreGoodsPagebyStoreClassify(parameter);
	}
}

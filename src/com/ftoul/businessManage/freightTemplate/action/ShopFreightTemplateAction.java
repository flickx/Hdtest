package com.ftoul.businessManage.freightTemplate.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.businessManage.freightTemplate.service.ShopFreightTemplateServ;
import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 
 * @author liding
 * 商家物流模板
 *
 */
@Controller
@RequestMapping(value="/businessManage/shopFreightTemplate")
public class ShopFreightTemplateAction {
	@Autowired ShopFreightTemplateServ shopFreightTemplateServ;
	/**
	 * 获取商家运费模板详情
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getShopFreightTemplateById")
	public @ResponseBody Result getShopFreightTemplateById(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return shopFreightTemplateServ.getShopFreightTemplateById(parameter);
	}
	/**
	 * 获取商家运费模板数据
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getShopFreightTemplate")
	public @ResponseBody Result getShopFreightTemplatePage(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return shopFreightTemplateServ.getShopFreightTemplatePage(parameter);
	}
	/**
	 * 保存商家运费模板数据
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="saveShopFreightTemplate")
	public @ResponseBody Result saveShopFreightTemplate(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return shopFreightTemplateServ.saveShopFreightTemplate(parameter);
	}
	/**
	 * 删除运费模板列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="delShopFreightTemplate")
	public @ResponseBody Result delShopFreightTemplate(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return shopFreightTemplateServ.delShopFreightTemplate(parameter);
	}
	/**
	 * 通过模板id获取商家区域运费模板数据
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getAreaFreightTemplateById")
	public @ResponseBody Result getAreaFreightTemplateById(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return shopFreightTemplateServ.getAreaFreightTemplateById(parameter);
	}
	/**
	 * 保存商家区域运费模板数据
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="saveShopAreaFreightTemplate")
	public @ResponseBody Result saveShopAreaFreightTemplate(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return shopFreightTemplateServ.saveShopAreaFreightTemplate(parameter);
	}
	/**
	 * 删除商家区域运费模板列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="delAreaFreightTemplateById")
	public @ResponseBody Result delAreaFreightTemplateById(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return shopFreightTemplateServ.delAreaFreightTemplateById(parameter);
	}
}

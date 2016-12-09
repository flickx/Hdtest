package com.ftoul.manage.freight.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.freight.service.FreightTemplateServ;

/**
 * 
 * @author liding
 * 商家物流模板
 *
 */
@Controller
@RequestMapping(value="/manage/freight")
public class FreightAction {
	@Autowired FreightTemplateServ freightTemplateServ;
	/**
	 * 获取商家运费模板详情
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getShopFreightTemplateById")
	public @ResponseBody Result getFreightTemplateById(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return freightTemplateServ.getShopFreightTemplateById(parameter);
	}
	/**
	 * 获取商家运费模板数据
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getShopFreightTemplate")
	public @ResponseBody Result getFreightTemplatePage(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return freightTemplateServ.getShopFreightTemplatePage(parameter);
	}
	/**
	 * 保存商家运费模板数据
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="saveShopFreightTemplate")
	public @ResponseBody Result saveFreightTemplate(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return freightTemplateServ.saveShopFreightTemplate(parameter);
	}
	/**
	 * 删除运费模板列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="delShopFreightTemplate")
	public @ResponseBody Result delFreightTemplate(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return freightTemplateServ.delShopFreightTemplate(parameter);
	}
	/**
	 * 通过模板id获取商家区域运费模板数据
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="getAreaFreightTemplateById")
	public @ResponseBody Result getAreaFreightTemplateById(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return freightTemplateServ.getAreaFreightTemplateById(parameter);
	}
	/**
	 * 保存商家区域运费模板数据
	 * @param param 参数
	 * @throws Exception
	 */
	@RequestMapping(value="saveShopAreaFreightTemplate")
	public @ResponseBody Result saveAreaFreightTemplate(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return freightTemplateServ.saveShopAreaFreightTemplate(parameter);
	}
	/**
	 * 删除商家区域运费模板列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="delShopAreaFreightTemplateById")
	public @ResponseBody Result delAreaFreightTemplateById(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return freightTemplateServ.delAreaFreightTemplateById(parameter);
	}
	/**
	 * 通过商家运费模板和区域名称获取区域运费模板
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@RequestMapping(value="getTemplateByArea")
	public @ResponseBody Result getTemplateByArea(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return freightTemplateServ.getTemplateByArea(parameter);
	}
	/**
	 * 获取省份
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getProvinces")
	public @ResponseBody Result getProvinces(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return freightTemplateServ.getProvinces(parameter);
	}
}

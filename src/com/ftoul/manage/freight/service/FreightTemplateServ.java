package com.ftoul.manage.freight.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 
 * @author liding
 *
 */
public interface FreightTemplateServ {
	/**
	 * 获取商家运费模板详情
	 * @param param 参数
	 * @throws Exception
	 */
	Result getShopFreightTemplateById(Parameter param) throws Exception; 
	/**
	 * 获取运费模板列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getShopFreightTemplatePage(Parameter param) throws Exception; 
	/**
	 * 保存运费模板列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveShopFreightTemplate(Parameter param) throws Exception; 
	/**
	 * 删除运费模板列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result delShopFreightTemplate(Parameter param) throws Exception; 
	/**
	 * 获取商家区域运费模板列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getAreaFreightTemplateById(Parameter param) throws Exception;
	/**
	 * 保存商家区域运费模板列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveShopAreaFreightTemplate(Parameter param) throws Exception; 
	/**
	 * 删除商家区域运费模板列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result delAreaFreightTemplateById(Parameter param) throws Exception; 
	/**
	 * 通过商家运费模板和区域名称获取区域运费模板
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getTemplateByArea(Parameter param)throws Exception;
	/**
	 * 获取省份
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getProvinces(Parameter param)throws Exception;
}

/**
 * 
 */
package com.ftoul.manage.advert.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.advert.service.AdvertServ;
/**
 * @author 李丁
 * @date:2016年9月1日 下午2:47:45
 * @类说明 : 广告管理操作类
 */
@Controller
@RequestMapping(value = "/manage/advert")
public class AdvertAction{
	@Autowired
	private AdvertServ advertServ;
	
	/**
	 * 获取首页轮播图列表（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getIndexCarousePicList")  
	public @ResponseBody Result getIndexCarousePicList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return advertServ.getIndexCarousePicList(parameter);
	}
	/**
	 * 保存/更新首页轮播图对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveIndexCarouselPic")  
	public @ResponseBody Result saveIndexCarouselPic(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return advertServ.saveIndexCarouselPic(parameter);
	}
	/**
	 * 获取首页轮播图对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getIndexCarouselPicById")  
	public @ResponseBody Result getIndexCarouselPicById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return advertServ.getIndexCarouselPicById(parameter);
	}
	
	/**
	 * 删除轮播图片（只删除图片）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "deleteIndexCarouselPicById")  
	public @ResponseBody Result deleteIndexCarouselPicById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return advertServ.deleteIndexCarouselPicById(parameter);
	}
	/**
	 * 删除首页轮播图（全部删除，包括图片）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "deleteIndexPic")  
	public @ResponseBody Result deleteIndexPic(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return advertServ.deleteIndexPic(parameter);
	}
}

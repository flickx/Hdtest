package com.ftoul.manage.advert.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 广告处理的业务接口
 * @author liding
 * 2016-09-01
 */
public interface AdvertServ {
	
	/**
	 * 获取首页轮播图列表（带分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getIndexCarousePicList(Parameter param) throws Exception;
	
	/**
	 * 保存首页轮播图
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveIndexCarouselPic(Parameter param) throws Exception;
	
	/**
	 * 获取首页轮播图对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	Result getIndexCarouselPicById(Parameter param) throws Exception; 
	/**
	 * 删除轮播图,只删除图片
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result deleteIndexCarouselPicById(Parameter parameter) throws Exception ;
	/**
	 * 删除轮播图对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result deleteIndexPic(Parameter parameter) throws Exception ;
}

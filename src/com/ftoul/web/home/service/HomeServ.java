/**
 * 
 */
package com.ftoul.web.home.service;

import org.springframework.web.bind.annotation.RequestMapping;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * @author 李丁
 * @date:2016年8月15日 下午3:42:58
 * @类说明 :
 */

public interface HomeServ {
	/**
	 * 获取首页轮播图列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getIndexCarouselList(Parameter param) throws Exception;
	/**
	 * 插入用户短信记录
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Result insertSmsInfo(Parameter param) throws Exception;
}

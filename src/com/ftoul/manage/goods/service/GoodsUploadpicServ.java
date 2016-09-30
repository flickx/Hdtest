package com.ftoul.manage.goods.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 
* 类描述：
* @author: liding
* @date： 日期：2016年9月6日 时间：上午10:01:27
* @version 1.0
* 
*/


@Service("GoodsUploadpicServ")
public interface GoodsUploadpicServ{
	/**
	 * 
	 * 保存商品图片
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
//	Result saveGoodsUploadpic(Parameter param) throws Exception;
	/**
	 * 
	 * 删除商品图片
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result delGoodsUploadpic(Parameter param, HttpServletRequest request) throws Exception;
	/**
	 * 通过商品id和图片类型得到商品图片集合
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getGoodsUploadpicList(Parameter parameter)throws Exception;
	
}

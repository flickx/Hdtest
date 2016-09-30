package com.ftoul.manage.goods.service;

import org.springframework.stereotype.Service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 
* 类描述：
* @author: yw
* @date： 日期：2016年7月20日 时间：上午10:24:27
* @version 1.0
* 
*/


@Service("GoodsCanalServImpl")
public interface GoodsCanalServ{

	/**
	 * 保存商品渠道
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveGoodsCanal(Parameter param) throws Exception;
	
	/**
	 * 带分页查询
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */

	Result getGoodsCanalPage(Parameter parameter)throws Exception;

	/**
	 * 删除
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result delGoodsCanal(Parameter parameter)throws Exception;

	Result getGoodsCanalList(Parameter parameter)throws Exception;
}

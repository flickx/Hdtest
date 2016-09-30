package com.ftoul.manage.goods.service;

import java.util.List;

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


@Service("GoodsBrandServImpl")
public interface GoodsBrandServ{

	/**
	 * 保存/更新商品品牌对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveGoodsBrand(Parameter param) throws Exception;
	
	
	
	/**
	 * 
	 * 删除商品品牌对象
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	Result delGoodsBrand(Parameter param) throws Exception;


	/**
	 * 
	 * 分页查询GoodsBrand
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */

	Result getGoodsBrandListPage(Parameter parameter)throws Exception;

	
	
	/**
	 * 
	 * 通过id得到GoodsBrand
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */


	Result getGoodsBrandById(Parameter parameter)throws Exception;


	/**
	 * 
	 * 得到所有的商品品牌
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */

	Result getGoodsBrandList(Parameter parameter)throws Exception;
	
	/**
	 * 查询所有品牌列表
	 * @param parameter
	 * @return
	 */
	Result getAllGoodsBrandList(Parameter parameter)throws Exception;



	Result getGoodsBrand(Parameter parameter)throws Exception;
	
}

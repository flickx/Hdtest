/**
 * 
 */
package com.ftoul.web.goods.action;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.goods.service.GoodsBrandServ;


/**
 *
 * 类描述  商品品牌控制类
 * 
 * @author: yw
 * @date： 日期：2016年7月20日 时间：上午11:11:05
 * @version 1.0
 *
 */
@Controller("GoodsBrandsAction")
@RequestMapping(value = "/web/goodsBrands")
public class GoodsBrandAction {
	@Autowired
	private GoodsBrandServ goodsBrandServ;
	
	/**
	 * 
	 * 保存/更新商品品牌
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "saveGoodsBrand")  
	public @ResponseBody Result saveGoodsBrand(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBrandServ.saveGoodsBrand(parameter);
	}
	
	
}

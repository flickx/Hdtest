/**
 * 
 */
package com.ftoul.manage.coin.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.coin.service.CoinSetServ;
import com.ftoul.manage.systemset.service.SystemSetServ;

/**
 *
 * 类描述  蜂币兑换设置
 * 
 * @author: yw
 * @date： 日期：2016年7月20日 时间：上午11:11:05
 * @version 1.0
 *
 */
@Controller
@RequestMapping(value = "/manage/coinSet")
public class CoinSetAction {
	@Autowired
	private CoinSetServ coinSetServ;
	
	/**
	 * 更新蜂币设置
	 * @param param 当前级别参数
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveCoinSet")  
	public @ResponseBody Result saveCoinSet(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return coinSetServ.saveCoinSet(parameter);
	}
	
	/**
	 * 得到当前的系统配置
	 * @param param 当前级别参数
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getCoinSet")  
	public @ResponseBody Result getCoinSet(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return coinSetServ.getCoinSet(parameter);
	}
}

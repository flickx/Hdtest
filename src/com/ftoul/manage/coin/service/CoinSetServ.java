package com.ftoul.manage.coin.service;

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


@Service("SystemSetImpl")
public interface CoinSetServ{

	Result saveCoinSet(Parameter parameter) throws Exception;

	Result getCoinSet(Parameter parameter) throws Exception;
	
	
}

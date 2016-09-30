package com.ftoul.manage.coin.service.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.coin.service.CoinSetServ;
import com.ftoul.po.SystemSet;
import com.ftoul.util.hibernate.HibernateUtil;

/**
*
* 类描述：
* @date： 日期：2016年7月22日 时间：上午10:28:05
* @version 1.0
*
*/
@Service("CoinSetServImpl")
public class CoinSetServImpl implements CoinSetServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 保存/更新系统设置对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveCoinSet(Parameter param) throws Exception {
		SystemSet systemSet = (SystemSet) JSONObject.toBean((JSONObject) param.getObj(),SystemSet.class);
		Object res;
		systemSet.setType("coin");
		systemSet.setState("1");
		if(systemSet.getId()==null){
			systemSet.setCreateTime(new DateStr().toString());
			res =this.hibernateUtil.save(systemSet);
		}else{
			systemSet.setModifyTime(new DateStr().toString());
			res =this.hibernateUtil.update(systemSet);
		} 
			
		return ObjectToResult.getResult(res);
	}
	
	/**
	 * 得到系统设置对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getCoinSet(Parameter param) throws Exception {
		String hql = "from SystemSet where state=1 and type='coin'";
		Object systemSet = this.hibernateUtil.hqlFirst(hql);
		return ObjectToResult.getResult(systemSet);
	}

	
}

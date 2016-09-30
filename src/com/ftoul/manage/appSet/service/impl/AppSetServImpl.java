package com.ftoul.manage.appSet.service.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.appSet.service.AppSetServ;
import com.ftoul.po.AppSet;
import com.ftoul.util.hibernate.HibernateUtil;

/**
*
*
*
* 类描述：
* @author: yw
* @date： 日期：2016年7月22日 时间：上午10:28:05
* @version 1.0
*
*/
@Service("AppSetServImpl")
public class AppSetServImpl implements AppSetServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 保存/更新系统设置对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveAppSet(Parameter param) throws Exception {
		AppSet appSet = (AppSet) JSONObject.toBean((JSONObject) param.getObj(),AppSet.class);
		Object res;
		if(appSet.getId()!=null)
			res = hibernateUtil.update(appSet);
		else 
			res = hibernateUtil.save(appSet);
		return ObjectToResult.getResult(res);
	}
	
	/**
	 * 得到系统设置对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getAppSet(Parameter param) throws Exception {
		String hql = " from AppSet ";
		Object appSet = hibernateUtil.hqlFirst(hql);
		return ObjectToResult.getResult(appSet);
	}
	
}

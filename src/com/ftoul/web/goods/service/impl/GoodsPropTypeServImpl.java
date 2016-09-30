package com.ftoul.web.goods.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.goods.service.GoodsPropTypeServ;
import com.ftoul.po.GoodsPropType;
import com.ftoul.po.GoodsType;
import com.ftoul.util.hibernate.HibernateUtil;

/**
 * 
*
* 类描述：
* @author: yw
* @date： 日期：2016年7月22日 时间：上午10:28:05
* @version 1.0
*
 */
@Service("WebGoodsPropTypeServImpl")
public class GoodsPropTypeServImpl implements GoodsPropTypeServ {

	@Autowired
	private HibernateUtil hibernateUtil;

	
	/**
	 * 保存/更新商品属性分类对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveGoodsPropType(Parameter param) throws Exception {
		GoodsPropType goodsPropType = (GoodsPropType) JSONObject.toBean((JSONObject) param.getObj(),GoodsPropType.class);
		Object res;
		if(Common.isNull(goodsPropType.getId())){
			goodsPropType.setCreateTime(new DateStr().toString());
			goodsPropType.setState("1");
			res = hibernateUtil.save(goodsPropType);
		}else{
			goodsPropType.setModifyTime(new DateStr().toString());
			res = hibernateUtil.update(goodsPropType);
		}
		return ObjectToResult.getResult(res);
	}


	@Override
	public Result delGoodsPropType(Parameter param) throws Exception {
		GoodsPropType goodsPropType = (GoodsPropType) hibernateUtil.find(GoodsPropType.class, param.getId()+"");
		Object res;
		goodsPropType.setState("0");
		res=	hibernateUtil.update(goodsPropType);
		return ObjectToResult.getResult(res);
	}


	@Override
	public Result getById(Parameter param) throws Exception {
		String  hql = "from GoodsPropType where state = '1' and id = '"+ param.getId()+"'";
		List<Object> goodsPropType = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(goodsPropType) ;
	}

	/**
	 * 
	 * 得到所有商品属性大类
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	

	@Override
	public Result getGoodsPropType(Parameter param) {
		String  hql = "from GoodsPropType where state = '1' ";
		List<Object> goodsPropType = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(goodsPropType) ;
	}
}

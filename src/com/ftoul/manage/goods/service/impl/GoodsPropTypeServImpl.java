package com.ftoul.manage.goods.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goods.service.GoodsPropTypeServ;
import com.ftoul.po.GoodsPropType;
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
@Service("GoodsPropTypeServImpl")
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
			List<Object> list = hibernateUtil.hql("from GoodsPropType where state = '1' and name = '"+goodsPropType.getName()+"'");
			if(list.size()>0){
				throw new Exception("类型名称已存在，保存失败");
			}else{
				goodsPropType.setCreateTime(new DateStr().toString());
				goodsPropType.setState("1");
				res = hibernateUtil.save(goodsPropType);
			}
		}else{
			GoodsPropType gpt = (GoodsPropType)hibernateUtil.find(GoodsPropType.class, goodsPropType.getId());
			List<Object> list = hibernateUtil.hql("from GoodsPropType where state = '1' and id != '"+gpt.getId()+"'"+" and name = '"+goodsPropType.getName()+"'");
			if(list.size()>0){
				throw new Exception("类型名称已存在，保存失败");
			}else{
				gpt.setName(goodsPropType.getName());
				gpt.setModifyTime(new DateStr().toString());
				res = hibernateUtil.update(gpt);
			}
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
		GoodsPropType goodsPropType = (GoodsPropType) hibernateUtil.find(GoodsPropType.class, param.getId() + "");
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
		String  hql="";
		if(param.getId()==null)
		  hql = "from GoodsPropType where state = '1' "+ param.getWhereStr() + param.getOrderBy();
		else 
		{
			Object res1 =this.hibernateUtil.find(GoodsPropType.class, param.getId()+"");
			return ObjectToResult.getResult(res1);
		}
		
		List<Object>goodsPropTypeList  = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(goodsPropTypeList);
	}

	@Override
	public Result getGoodsPropTypePageList(Parameter param) throws Exception {
		String  hql = "from GoodsPropType where state = '1' "+ "order by createTime desc";
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);

	}
	
}

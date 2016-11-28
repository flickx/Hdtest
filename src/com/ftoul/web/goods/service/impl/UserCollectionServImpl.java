package com.ftoul.web.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.Goods;
import com.ftoul.po.User;
import com.ftoul.po.UserCollection;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.goods.service.UserCollectionServ;

/**
* 
*
* 类描述：商品业务实现类
* @author: yw
* @date： 日期：2016年8月8日 时间：上午10:01:10
* @version 1.0
*
*/
@Service("WebUserCollectionImpl")
public class UserCollectionServImpl implements UserCollectionServ {

	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public Result saveUserCollection(Parameter param) throws Exception {
		UserCollection userCollection = new UserCollection();
		if(param.getId()!=null){
			Goods goods = (Goods) this.hibernateUtil.find(Goods.class,param.getId()+"");
			userCollection.setGoods(goods);
			userCollection.setUser(param.getUserToken().getUser());
			userCollection.setCreatePerson(param.getUserToken().getUser().getMobil());
			userCollection.setCreateTime(new DateStr().toString());
			userCollection.setState("1");
		}
	  Object res=this.hibernateUtil.save(userCollection);
	  return   ObjectToResult.getResult(res);
	}

	@Override
	public Result findUserCollection(Parameter param) throws Exception {
		User user =param.getUserToken().getUser();
		String hql ="from UserCollection where state ='1' and goods.id='"+param.getId()+"' and user.id= '"+user.getId()+"'" ;
		List<Object> list=this.hibernateUtil.hql(hql);
		Result result = new Result();
		if(list!=null&&list.size()>0){
			result.setResult(1);//说明已经被收藏了
		}
		else{
			result.setResult(0);//没被收藏
		}
		
		return result;
	}
	
	
	
}

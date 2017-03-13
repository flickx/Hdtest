package com.ftoul.pc.website.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.pc.website.service.ArticleClassifyServ;
import com.ftoul.po.ArticleClassify;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("ArticleClassifyServImpl")
public class ArticleClassifyServImpl implements ArticleClassifyServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public Result delArticleClassify(Parameter parameter) throws Exception {
		//如果此分类下有文章 则不允许删除
		String hql = " update ArticleClassify set state = 0  where id in ("+StrUtil.getIds(parameter.getId()+")");
		Integer num = hibernateUtil.execHql(hql);
		return ObjectToResult.getResult(num);
	}
	
	@Override
	public Result getArticleClassify(Parameter parameter) throws Exception {
		String hql = " from ArticleClassify where state = 1 and id = '" + parameter.getId() +"'";
		ArticleClassify articleClassify = (ArticleClassify)hibernateUtil.hqlFirst(hql);
		return ObjectToResult.getResult(articleClassify);
	}
	
	@Override
	public Result getArticleClassifyPage(Parameter param) throws Exception {
		String hql = " from ArticleClassify where state = 1 "+param.getWhereStr();
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	
	@Override
	public Result saveArticleClassify(Parameter param)throws Exception {
		ArticleClassify articleClassify = (ArticleClassify) JSONObject.toBean((JSONObject) param.getObj(),ArticleClassify.class);
		Object res;
		if(Common.isNull(articleClassify.getId())){
			articleClassify.setCreateTime(new DateStr().toString());
			articleClassify.setState("1");
			res = hibernateUtil.save(articleClassify);
		}else{
			articleClassify.setModifyTime(new DateStr().toString());
			res = hibernateUtil.update(articleClassify);
		}
		return ObjectToResult.getResult(res);
	}
	
}

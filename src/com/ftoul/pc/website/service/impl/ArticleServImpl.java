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
import com.ftoul.pc.website.service.ArticleServ;
import com.ftoul.po.Article;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("ArticleServImpl")
public class ArticleServImpl implements ArticleServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public Result delArticle(Parameter parameter) throws Exception {
		String hql = " update Article set state = 0  where id in ("+ StrUtil.getIds(parameter.getId())+")";
		Integer num = hibernateUtil.execHql(hql);
		return ObjectToResult.getResult(num);
	}
	
	@Override
	public Result getArticle(Parameter parameter) throws Exception {
		String hql = " from Article where state = 1 and id = '" + parameter.getId() +"'";
		Article article = (Article)hibernateUtil.hqlFirst(hql);
		return ObjectToResult.getResult(article);
	}
	
	@Override
	public Result getArticlePage(Parameter param) throws Exception {
		String queryStr = param.getWhereStr();
		String hql;
		if(queryStr!=null){
			hql = " from Article where state = 1  "+queryStr+" order by createTime desc";
		}else{
			hql = " from Article where state = 1  order by createTime desc";
		}
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	
	@Override
	public Result getClassifyList(Parameter parameter) throws Exception {
		String hql = "from ArticleClassify where pname is null or pname = '' and state = 1 order by sort asc";
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}
	
	@Override
	public Result getNextClassifyList(Parameter parameter) throws Exception {
		String hql = "from ArticleClassify where pname = '"+parameter.getId() +"' and state = 1 order by sort asc";
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}
	
	@Override
	public Result saveArticle(Parameter param)throws Exception {
		Article article = (Article) JSONObject.toBean((JSONObject) param.getObj(),Article.class);
		Object res;
		if(Common.isNull(article.getId())){
			article.setCreateTime(new DateStr().toString());
			article.setState("1");
			article.setContent(param.getKey());
			res = hibernateUtil.save(article);
		}else{
			article.setModifyTime(new DateStr().toString());
			article.setContent(param.getKey());
			res = hibernateUtil.update(article);
		}
		return ObjectToResult.getResult(res);
	}
	
}

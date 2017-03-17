package com.ftoul.pc.website.service.impl;

import java.util.ArrayList;
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
import com.ftoul.pc.interfaces.vo.PcArticleClassifyVo;
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
		String hql = " update ArticleClassify set state = 0  where id in ("+StrUtil.getIds(parameter.getId())+")";
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

	@Override
	public Result getArticleClassifyList(Parameter parameter) throws Exception {
		String hql ="from ArticleClassify where classify.id is null and state =1 order by createTime asc";
		List<Object> typeLevel1List = hibernateUtil.hql(hql);
		List<PcArticleClassifyVo> typeLevel1VoList = new ArrayList<PcArticleClassifyVo>();
		if(typeLevel1List != null){
			for (Object object : typeLevel1List) {
				ArticleClassify ArticleClassify = (ArticleClassify) object;
				PcArticleClassifyVo ArticleClassifyVoLel1 = new PcArticleClassifyVo();
				hql = "from ArticleClassify where state =1 and classify.id = '" + ArticleClassify.getId() +"'";
				hql+="order by createTime asc";
				List<Object> typeLevel2List = hibernateUtil.hql(hql);
				List<PcArticleClassifyVo> typeLevel2VoList = new ArrayList<PcArticleClassifyVo>();
				if(typeLevel2List != null){
					for (Object object2 : typeLevel2List) {
						ArticleClassify ArticleClassifyLevel2 = (ArticleClassify)object2;
						PcArticleClassifyVo ArticleClassifyVoLel2 = new PcArticleClassifyVo();
						hql = "from ArticleClassify where state =1 and classify.id = '" + ArticleClassifyLevel2.getId() +"'";
						hql+="order by createTime asc";
						List<Object> typeLevel3List = hibernateUtil.hql(hql);
						List<PcArticleClassifyVo> typeLevel3VoList = new ArrayList<PcArticleClassifyVo>();
						if(typeLevel3List != null){
							for (Object object3 : typeLevel3List) {
								ArticleClassify ArticleClassifyLevel3 = (ArticleClassify)object3;
								PcArticleClassifyVo ArticleClassifyVoLel3 = new PcArticleClassifyVo();
								ArticleClassifyVoLel3.setId(ArticleClassifyLevel3.getId());
								ArticleClassifyVoLel3.setName(ArticleClassifyLevel3.getName());
								ArticleClassifyVoLel3.setArticleClassifyList(null);
								typeLevel3VoList.add(ArticleClassifyVoLel3);
							}
						}
						ArticleClassifyVoLel2.setId(ArticleClassifyLevel2.getId());
						ArticleClassifyVoLel2.setName(ArticleClassifyLevel2.getName());
						ArticleClassifyVoLel2.setArticleClassifyList(typeLevel3VoList);
						typeLevel2VoList.add(ArticleClassifyVoLel2);
					}
					ArticleClassifyVoLel1.setId(ArticleClassify.getId());
					ArticleClassifyVoLel1.setName(ArticleClassify.getName());
					ArticleClassifyVoLel1.setArticleClassifyList(typeLevel2VoList);
					typeLevel1VoList.add(ArticleClassifyVoLel1);
				}
			}
		}
		return ObjectToResult.getResult(typeLevel1VoList) ;
		
	
	}
	
}

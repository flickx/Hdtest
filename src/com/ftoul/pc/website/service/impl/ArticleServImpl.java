package com.ftoul.pc.website.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import com.ftoul.pc.interfaces.vo.PcArticleVo;
import com.ftoul.pc.website.service.ArticleServ;
import com.ftoul.po.Article;
import com.ftoul.util.base64.FileBase64Util;
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
	public Result getArticleListByClassifyId(Parameter parameter) throws Exception {
		String hql = " from Article where state = 1 and classify.id = '" + parameter.getId() +"'";
		List<Object> list = (List<Object>)hibernateUtil.hql(hql);
		List<PcArticleVo> articleList = new ArrayList<PcArticleVo>();
		for (Object o : list) {
			Article a = (Article)o;
			PcArticleVo vo = new PcArticleVo();
			vo.setContent(a.getContent());
			vo.setName(a.getClassify().getName());
			articleList.add(vo);
		}
		return ObjectToResult.getResult(articleList);
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
		String hql = "from ArticleClassify where classify.id is null or  classify.id = '' and state = 1 order by sort asc";
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}
	
	@Override
	public Result getNextClassifyList(Parameter parameter) throws Exception {
		String hql = "from ArticleClassify where classify.id = '"+parameter.getId() +"' and state = 1 order by sort asc";
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
	/**
	 * bootstrap-wysiwyg富文本编辑器图片处理
	 */
	@Override
	public Result getFilePath(Parameter parameter, HttpServletRequest request) throws Exception {
		//获取文件后缀
		String fileLastName = parameter.getId().toString().split(",")[0].split(";")[0].split("/")[1];
		//获取编辑器传来的图片base64数据
		String base64String = parameter.getId().toString().split(",")[1];
		//将生成的图片名称，去UUID
		String fileFirstName = UUID.randomUUID().toString();
		//文件夹名称
		String folderName = parameter.getKey();
		//保存至系统的绝对路径
		String path = request.getSession().getServletContext().getRealPath("upload/img/"+folderName+"/");
		//相对路径
		String picPath = "/upload/img/" + folderName + "/"+fileFirstName+"."+fileLastName;
		//绝对路径
		String filePath = path+fileFirstName+"."+fileLastName;
		File targetFile = new File(path, filePath);  
		 if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        } 
		//写入系统
		new FileBase64Util().convertByteToFile(base64String, filePath);
		return ObjectToResult.getResult(picPath);
	}
	
}

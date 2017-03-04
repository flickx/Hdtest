package com.ftoul.pc.website.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.website.service.ArticleServ;

/**
 * 文章分类管理
 * @author LiDing
 * 2017-02-15
 */
@Controller
@RequestMapping(value = "/pc/article")
public class ArticleAction {

	@Autowired
	private ArticleServ articleServ; 
	/**
	 * 获取文章信息详情
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getArticle")  
	public @ResponseBody Result getArticle(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return articleServ.getArticle(parameter);
	}
	/**
	 * 获取文章分类分页列表
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getArticlePage")  
	public @ResponseBody Result getArticlePage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return articleServ.getArticlePage(parameter);
	}
	/**
	 * 保存文章
	 * @author LiDing
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="saveArticle")
	public @ResponseBody Result saveArticle(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return articleServ.saveArticle(parameter);
	}
	/**
	 * 删除文章
	 * @author LiDing
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="delArticle")
	public @ResponseBody Result delArticle(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return articleServ.delArticle(parameter);
	}
	/**
	 * 获取文章一级分类
	 * @author LiDing
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getClassifyList")
	public @ResponseBody Result getClassifyList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return articleServ.getClassifyList(parameter);
	}
	/**
	 * 获取文章子分类
	 * @author LiDing
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getNextClassifyList")
	public @ResponseBody Result getNextClassifyList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return articleServ.getNextClassifyList(parameter);
	}
	
}

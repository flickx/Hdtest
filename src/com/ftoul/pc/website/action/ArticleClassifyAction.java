package com.ftoul.pc.website.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.website.service.ArticleClassifyServ;

/**
 * 物流管理
 * @author LiDing
 * 2017-02-15
 */
@Controller
@RequestMapping(value = "/pc/articleClassify")
public class ArticleClassifyAction {

	@Autowired
	private ArticleClassifyServ articleClassifyServ; 
	
	/**
	 * 获取全部文章分类信息
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getArticleClassifyList")  
	public @ResponseBody Result getLogisticsCompany(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return articleClassifyServ.getArticleClassifyList(parameter);
	}
	/**
	 * 获取全部文章分类信息
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getArticleClassify")  
	public @ResponseBody Result getArticleClassify(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return articleClassifyServ.getArticleClassify(parameter);
	}
	/**
	 * 获取文章分类分页列表
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getArticleClassifyPage")  
	public @ResponseBody Result getLogisticsCompanyPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return articleClassifyServ.getLogisticsCompanyPage(parameter);
	}
	/**
	 * 保存文章分类
	 * @author LiDing
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="saveArticleClassify")
	public @ResponseBody Result saveLogisticsCompanyJoinList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return articleClassifyServ.saveArticleClassify(parameter);
	}
}

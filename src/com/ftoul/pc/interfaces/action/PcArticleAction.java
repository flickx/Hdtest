package com.ftoul.pc.interfaces.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.website.service.ArticleServ;

/**
 * pc端前台“帮助中心”调用接口
 * @author lid
 *
 */
@Controller
@RequestMapping(value = "/pcInterface/article/")
public class PcArticleAction {
	@Autowired
	private ArticleServ articleServ;
	
	/**
	 *获取帮助中心分类文章
	 */
	@RequestMapping(value = "getArticleList")  
	public @ResponseBody Result getArticleList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return  articleServ.getArticleListByClassifyId(parameter);
	} 
}

package com.ftoul.pc.website.service;

import javax.servlet.http.HttpServletRequest;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 文章分类业务接口
 * @author LiDing
 * 2017-02-15
 */
public interface ArticleServ {
	/**
	 * 获取文章分类信息
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	Result getArticle(Parameter parameter) throws Exception;
	/**
	 * 获取文章分类分页列表
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	Result getArticlePage(Parameter parameter) throws Exception;
	/**
	 * 保存文章分类
	 * @author LiDing
	 * @return
	 * @throws Exception
	 */
	Result saveArticle(Parameter parameter) throws Exception;
	/**
	 * 删除文章分类
	 * @author LiDing
	 * @return
	 * @throws Exception
	 */
	Result delArticle(Parameter parameter) throws Exception;
	
	/**
	 * 获取文章一级分类
	 * @author LiDing
	 * @return
	 * @throws Exception
	 */
	Result getClassifyList(Parameter parameter) throws Exception;
	/**
	 * 获取文章子分类
	 * @author LiDing
	 * @return
	 * @throws Exception
	 */
	Result getNextClassifyList(Parameter parameter) throws Exception;
	Result getFilePath(Parameter parameter, HttpServletRequest request) throws Exception;
}

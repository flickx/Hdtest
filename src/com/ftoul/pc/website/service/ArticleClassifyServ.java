package com.ftoul.pc.website.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;


/**
 * 文章分类业务接口
 * @author LiDing
 * 2017-02-15
 */
public interface ArticleClassifyServ {
	/**
	 * 获取文章分类信息
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	Result getArticleClassify(Parameter parameter) throws Exception;
	/**
	 * 获取文章分类分页列表
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	Result getArticleClassifyPage(Parameter parameter) throws Exception;
	/**
	 * 保存文章分类
	 * @author LiDing
	 * @return
	 * @throws Exception
	 */
	Result saveArticleClassify(Parameter parameter) throws Exception;
	/**
	 * 删除文章分类
	 * @author LiDing
	 * @return
	 * @throws Exception
	 */
	Result delArticleClassify(Parameter parameter) throws Exception;
}

package com.ftoul.pc.comment.service;

import javax.servlet.http.HttpServletRequest;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface CommentServ {
	
	Result getCommentPage(Parameter param) throws Exception;
	
	Result getCommentDetail(Parameter param) throws Exception;
	
	Result saveComment(Parameter param) throws Exception;

	Result getCommentBackPage(Parameter parameter) throws Exception;

	Result getOrderCommentStaticSizeByUserId(Parameter parameter) throws Exception;

	Result getGoods(Parameter parameter) throws Exception;

	Result getCommentScore(Parameter parameter) throws Exception;

	Result goodsCommentPicUpload(Parameter parameter, HttpServletRequest request) throws Exception;
}

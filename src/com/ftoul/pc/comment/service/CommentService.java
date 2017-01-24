package com.ftoul.pc.comment.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface CommentService {
	
	Result getCommentPage(Parameter param) throws Exception;
	
	Result getCommentDetail(Parameter param) throws Exception;
	
	Result saveComment(Parameter param) throws Exception;

	Result getCommentBackPage(Parameter parameter) throws Exception;

	Result getOrderCommentStaticSizeByUserId(Parameter parameter) throws Exception;
}

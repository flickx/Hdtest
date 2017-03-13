package com.ftoul.manage.comment.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface CommentServ {
	
	Result getCommentPage(Parameter param) throws Exception;
	
	Result getCommentDetail(Parameter param) throws Exception;
	
	Result deleteComment(Parameter param) throws Exception;
	
	Result auditComment(Parameter param) throws Exception;

	Result hideComment(Parameter param) throws Exception;
}

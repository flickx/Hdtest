package com.ftoul.manage.comment.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.comment.service.CommentServ;

/**
 * 评论管理
 * @author HuDong
 *
 */
@Controller("CommentAction")
@RequestMapping(value = "/manage/comment")
public class CommentAction {
	
	@Autowired
	CommentServ commentService;

	/**
	 * 查询评论
	 * @throws Exception 
	 */
	@RequestMapping(value = "getCommentPage")  
	public @ResponseBody Result getCommentPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return commentService.getCommentPage(parameter);
	}
	
	/**
	 * 查询评论
	 * @throws Exception 
	 */
	@RequestMapping(value = "getCommentDetail")  
	public @ResponseBody Result getCommentDetail(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return commentService.getCommentDetail(parameter);
	}
	
	/**
	 * 删除评论
	 * @throws Exception 
	 */
	@RequestMapping(value = "deleteComment")  
	public @ResponseBody Result deleteComment(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return commentService.deleteComment(parameter);
	}
	
	/**
	 * 隐藏评论
	 * @throws Exception 
	 */
	@RequestMapping(value = "hideComment")  
	public @ResponseBody Result hideComment(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return commentService.hideComment(parameter);
	}
	
	/**
	 * 审核评论
	 * @throws Exception 
	 */
	@RequestMapping(value = "auditComment")  
	public @ResponseBody Result auditComment(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return commentService.auditComment(parameter);
	}
}

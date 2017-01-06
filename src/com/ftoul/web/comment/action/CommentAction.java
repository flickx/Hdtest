package com.ftoul.web.comment.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.comment.service.CommentService;

/**
 * 评论
 * @author HuDong
 *
 */
@Controller("WebCommentAction")
@RequestMapping(value = "/web/comment")
public class CommentAction {
	
	@Autowired
	CommentService commentService;

	/**
	 * 分页查询评论
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
	 * 保存评论
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveComment")  
	public @ResponseBody Result saveComment(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return commentService.saveComment(parameter);
	}
	
}

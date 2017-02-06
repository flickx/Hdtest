package com.ftoul.pc.comment.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.comment.service.CommentService;

/**
 * 评论
 * @author HuDong
 *
 */
@Controller("PcCommentAction")
@RequestMapping(value = "/pc/comment")
public class CommentAction {
	
	@Autowired
	CommentService commentService;

	/**
	 * 分页查询评论pc前端
	 * @throws Exception 
	 */
	@RequestMapping(value = "getCommentPage")  
	public @ResponseBody Result getCommentPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return commentService.getCommentPage(parameter);
	}
	
	/**
	 * 分页查询评论pc用户后台
	 * @throws Exception 
	 */
	@RequestMapping(value = "getCommentBackPage")  
	public @ResponseBody Result getCommentBackPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return commentService.getCommentBackPage(parameter);
	}
	
	/**
	 * 统计评论数目
	 * @throws Exception 
	 */
	@RequestMapping(value = "getOrderCommentStaticSizeByUserId")  
	public @ResponseBody Result getOrderCommentStaticSizeByUserId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return commentService.getOrderCommentStaticSizeByUserId(parameter);
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
	
	/**
	 * 商品评价时展示商品信息
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoods")  
	public @ResponseBody Result getGoods(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return commentService.getGoods(parameter);
	}
	
}

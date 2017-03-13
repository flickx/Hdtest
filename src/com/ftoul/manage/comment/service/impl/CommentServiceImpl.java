package com.ftoul.manage.comment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.comment.service.CommentServ;
import com.ftoul.manage.comment.vo.GoodsCommentVo;
import com.ftoul.po.GoodsComment;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("CommentServiceImpl")
public class CommentServiceImpl implements CommentServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private StrUtil strUtil;
	/**
	 * 分页查询商品评论
	 */
	@Override
	public Result getCommentPage(Parameter param) throws Exception {
		String whereStr = param.getWhereStr();
		String hql;
		if(whereStr!=null){
			hql = "from GoodsComment where state = '1' "+whereStr+" order by commentTime desc";
		}else{
			hql = "from GoodsComment where state = '1' order by commentTime desc";
		}
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		List<Object> objList = page.getObjList();
		List<Object> voList = new ArrayList<>();
		for (Object object : objList) {
			GoodsComment comment = (GoodsComment) object;
			GoodsCommentVo vo = new GoodsCommentVo();
			vo.setCommentCode(comment.getCommentCode());
			vo.setCommentContent(comment.getCommentContent());
			vo.setCommentTime(comment.getCommentTime());
			vo.setCommentType("商品");
			vo.setGoodsTitle(comment.getOrdersDetail().getGoodsTitle());
			vo.setId(comment.getId());
			vo.setIsShow("1".equals(comment.getIsShow())?"显示":"隐藏");
			vo.setUserName(comment.getUserName());
			vo.setAuditState("1".equals(comment.getAuditState())?"未审核":"已审核");
			voList.add(vo);
		}
		page.getObjList().retainAll(objList);
		page.setObjList(voList);
		return ObjectToResult.getResult(page);
	}
	
	/**
	 * 查询商品评论详情
	 */
	@Override
	public Result getCommentDetail(Parameter param) throws Exception {
		GoodsComment comment = (GoodsComment) hibernateUtil.find(GoodsComment.class, param.getId().toString());
		GoodsCommentVo vo = new GoodsCommentVo();
		vo.setCommentCode(comment.getCommentCode());
		vo.setCommentContent(comment.getCommentContent());
		vo.setCommentTime(comment.getCommentTime());
		vo.setGoodsTitle(comment.getOrdersDetail().getGoodsTitle());
		vo.setId(comment.getId());
		vo.setUserName(comment.getUserName());
		vo.setPicSrc(comment.getPicSrc());
		vo.setAuditState(comment.getAuditState());
		vo.setIsShow(comment.getIsShow());
		if(comment.getPicSrc()!=null){
			List<Object> list = new ArrayList<>();
			String[] pic = comment.getPicSrc().split(";");
			for (String string : pic) {
				list.add(string);
			}
			vo.setList(list);
		}
		
		return ObjectToResult.getResult(vo);
	}
	
	/**
	 * 删除商品评论
	 */
	@Override
	public Result deleteComment(Parameter param) throws Exception {
		String ids = strUtil.getStrIds((List)param.getId());
		List<Object> list = hibernateUtil.hql("from GoodsComment where state='1' and id in("+strUtil.getIds(ids)+")");
		for (Object object : list) {
			GoodsComment comment = (GoodsComment) object;
			comment.setState("0");//已删除
			comment.setIsShow("0");//不显示
			comment.setAuditState("2");;//已审核
			comment.setModifyPerson(param.getManageToken().getLoginUser().getLoginName());
			comment.setModifyTime(new DateStr().toString());
			hibernateUtil.update(comment);
		}
		return ObjectToResult.getResult(list);
	}
	
	/**
	 * 隐藏商品评论
	 */
	@Override
	public Result hideComment(Parameter param) throws Exception {
		String ids = "";
		if(param.getId() instanceof String){
			ids = param.getId().toString();
		}else{
			ids = strUtil.getStrIds((List)param.getId());
		}
		List<Object> list = hibernateUtil.hql("from GoodsComment where state='1' and id in("+strUtil.getIds(ids)+")");
		for (Object object : list) {
			GoodsComment comment = (GoodsComment) object;
			comment.setIsShow("0");//不显示
			comment.setAuditState("2");;//已审核
			comment.setModifyPerson(param.getManageToken().getLoginUser().getLoginName());
			comment.setModifyTime(new DateStr().toString());
			hibernateUtil.update(comment);
		}
		return ObjectToResult.getResult(list);
	}
	
	/**
	 * 审核商品评论
	 */
	@Override
	public Result auditComment(Parameter param) throws Exception {
		GoodsComment comment = (GoodsComment) hibernateUtil.find(GoodsComment.class, param.getId().toString());
		comment.setAuditState("2");//已审核
		comment.setIsShow("1");//1显示，0隐藏
		comment.setModifyPerson(param.getManageToken().getLoginUser().getLoginName());
		comment.setModifyTime(new DateStr().toString());
		hibernateUtil.update(comment);
		return ObjectToResult.getResult(comment);
	}

}

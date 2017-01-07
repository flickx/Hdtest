package com.ftoul.pc.comment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.comment.service.CommentService;
import com.ftoul.web.comment.vo.GoodsCommentDetailVo;
import com.ftoul.web.comment.vo.GoodsCommentVo;
import com.ftoul.po.GoodsComment;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("PcCommentServiceImpl")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 分页查询审核通过且显示的商品评论
	 */
	@Override
	public Result getCommentPage(Parameter param) throws Exception {
		String whereStr = param.getWhereStr();
		String key = param.getKey();
		String hql;
		
		if(whereStr!=null){
			if("1".equals(key)){//只查询带图评论的
				whereStr += " and picSrc is not null";
			}
			hql = "from GoodsComment where state = '2' and isShow='1' "+whereStr+" order by commentTime desc";
		}else{
			hql = "from GoodsComment where state = '2' and isShow='1' order by commentTime desc";
		}
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		List<Object> objList = page.getObjList();
		List<Object> voList = new ArrayList<>();
		for (Object object : objList) {
			GoodsComment comment = (GoodsComment) object;
			GoodsCommentVo vo = new GoodsCommentVo();
			vo.setId(comment.getId());
			vo.setCommentContent(comment.getCommentContent());
			vo.setUserName(comment.getUserName());
			vo.setParamName(comment.getOrdersDetail().getParamName());
			vo.setStar(comment.getStar());
			voList.add(vo);
		}
		page.getObjList().retainAll(objList);
		page.setObjList(voList);
		return ObjectToResult.getResult(page);
	}
	
	/**
	 * 保存商品评论
	 */
	@Override
	public Result saveComment(Parameter param) throws Exception {
		GoodsComment comment = (GoodsComment) Common.jsonToBean(param.getObj().toString(), GoodsComment.class);
		comment.setIsShow("0");
		comment.setState("1");
		comment.setCommentTime(new DateStr().toString());
		comment.setCreateTime(new DateStr().toString());
		comment.setCreatePerson(param.getUserToken().getUser().getUsername());
		hibernateUtil.save(comment);
		return ObjectToResult.getResult(comment);
	}
	
	/**
	 * 查询商品评论详情
	 */
	@Override
	public Result getCommentDetail(Parameter param) throws Exception {
		GoodsComment comment = (GoodsComment) hibernateUtil.find(GoodsComment.class, param.getId().toString());
		GoodsCommentDetailVo vo = new GoodsCommentDetailVo();
		vo.setCommentContent(comment.getCommentContent());
		vo.setCommentTime(comment.getCommentTime());
		vo.setGoodsTitle(comment.getOrdersDetail().getGoodsTitle());
		vo.setPrice(comment.getOrdersDetail().getPrice());
		vo.setGoodsPicSrc(comment.getOrdersDetail().getPicSrc());
		vo.setPicSrc(comment.getPicSrc());
		vo.setStar(comment.getStar());
		return ObjectToResult.getResult(vo);
	}
	
}

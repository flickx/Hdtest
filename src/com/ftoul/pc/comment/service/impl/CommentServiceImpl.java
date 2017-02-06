package com.ftoul.pc.comment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.OrdersConstant;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.comment.service.CommentService;
import com.ftoul.pc.orders.vo.PcOrderVo;
import com.ftoul.pc.comment.vo.GoodsCommentDetailVo;
import com.ftoul.pc.comment.vo.GoodsCommentVo;
import com.ftoul.pc.comment.vo.GoodsVo;
import com.ftoul.web.vo.OrderStaticCountVo;
import com.ftoul.po.GoodsComment;
import com.ftoul.po.Orders;
import com.ftoul.po.OrdersDetail;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.orders.OrdersUtil;

@Service("PcCommentServiceImpl")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired  
	OrdersUtil ordersUtil;
	
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
	 * PC用户后台评论分页查询
	 */
	@Override
	public Result getCommentBackPage(Parameter param) throws Exception {
		String key = param.getKey();
		Page page = new Page();
		if(OrdersConstant.NOT_COMMENT.equals(key)){//查询待评价的数据
			page =  hibernateUtil.hqlPage(null,"from Orders where orderStatic = '6' and isHasChild!='1' and state='1' and user.id='"+param.getUserToken().getUser().getId()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		}else{//查询已经全被评论的订单
			page =  hibernateUtil.hqlPage(null,"from Orders where orderStatic ='11' and isHasChild!='1' and state='1' and user.id='"+param.getUserToken().getUser().getId()+"' order by orderTime desc",param.getPageNum(),param.getPageSize());
		}
		PcOrderVo vo = new PcOrderVo();
		List<Object> list = new ArrayList<Object>();
		List<Object> ordersList = page.getObjList();
		for (int i = 0; i < ordersList.size(); i++) {
			Orders order = (Orders) ordersList.get(i);
			List<Object> ordersDetailList = new ArrayList<Object>();
			ordersDetailList = hibernateUtil.hql("from OrdersDetail where orders.id='"+order.getId()+"'");
			vo = ordersUtil.transformOrder(order,ordersDetailList);
			list.add(vo);
		}
		page.setObjList(null);
		page.setObjList(list);
		return ObjectToResult.getResult(page);
	}
	
	/**
	 * 获取订单评论数量
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrderCommentStaticSizeByUserId(Parameter param)
			throws Exception {
		List<Object> ordersList6 =  hibernateUtil.hql("from Orders where orderStatic = '6' and isHasChild !='1' and state= '1' and user.id='"+param.getUserToken().getUser().getId()+"'"); //待评价
		List<Object> ordersList11 =  hibernateUtil.hql("from Orders where orderStatic = '11' and isHasChild !='1' and state= '1' and user.id='"+param.getUserToken().getUser().getId()+"'"); //已评价
		OrderStaticCountVo vo = new OrderStaticCountVo();
		vo.setWaitCommentCount(String.valueOf(ordersList6.size()));
		vo.setCommentCount(String.valueOf(ordersList11.size()));
		return ObjectToResult.getResult(vo);
	}
	
	/**
	 * 保存商品评论
	 */
	@Override
	public Result saveComment(Parameter param) throws Exception {
		GoodsComment comment = (GoodsComment) Common.jsonToBean(param.getObj().toString(), GoodsComment.class);
		OrdersDetail ordersDetail = (OrdersDetail) hibernateUtil.find(OrdersDetail.class, param.getId().toString());
		ordersDetail.setIsComment("1");
		hibernateUtil.update(ordersDetail);
		boolean flag = true;
		Orders orders = ordersDetail.getOrders();
		List<Object> detailList = hibernateUtil.hql("from OrdersDetail where state='1' and orders.id='"+orders.getId()+"'");
		for (Object object : detailList) {
			OrdersDetail detail = (OrdersDetail) object;
			if("0".equals(detail.getIsComment())){
				flag = false;
			}
		}
		if(flag==true){
			orders.setOrderStatic("11");//此订单下所有的订单明细都已经被评论
			hibernateUtil.update(orders);
		}
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

	/**
	 * 商品评价时展示商品信息
	 */
	@Override
	public Result getGoods(Parameter param) throws Exception {
		OrdersDetail ordersDetail = (OrdersDetail) hibernateUtil.find(OrdersDetail.class, param.getId().toString());
		GoodsVo vo = new GoodsVo();
		vo.setOrderNumber(ordersDetail.getOrders().getOrderNumber());
		vo.setOrderTime(ordersDetail.getOrders().getOrderTime());
		vo.setGoodsTitle(ordersDetail.getGoodsTitle());
		vo.setParamName(ordersDetail.getParamName());
		vo.setPicSrc(ordersDetail.getPicSrc());
		vo.setPrice(ordersDetail.getPrice());
		return ObjectToResult.getResult(vo);
	}

	
	
}

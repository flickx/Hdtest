package com.ftoul.pc.comment.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.misc.BASE64Decoder;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.OrdersConstant;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.comment.service.CommentServ;
import com.ftoul.pc.comment.vo.GoodsCommentDetailVo;
import com.ftoul.pc.comment.vo.GoodsCommentScoreVo;
import com.ftoul.pc.comment.vo.GoodsCommentVo;
import com.ftoul.pc.comment.vo.GoodsVo;
import com.ftoul.pc.orders.vo.PcOrderVo;
import com.ftoul.po.AfterSchedule;
import com.ftoul.po.GoodsComment;
import com.ftoul.po.Orders;
import com.ftoul.po.OrdersDetail;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.orders.OrdersUtil;
import com.ftoul.web.vo.OrderStaticCountVo;

@Service("PcCommentServiceImpl")
public class CommentServiceImpl implements CommentServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired  
	OrdersUtil ordersUtil;
	
	/**
	 * 分页查询审核通过且显示的商品评论
	 */
	@Override
	public Result getCommentPage(Parameter param) throws Exception {
		String hql;
		if("1".equals(param.getKey())){//只查询带图评论的
			hql = "from GoodsComment where state = '1' and isShow='1' and auditState='1' and picSrc is not null and ordersDetail.goodsParam.goods.id='"+param.getId().toString()+"' order by commentTime desc";
		}else{
			hql = "from GoodsComment where state = '1' and isShow='1' and auditState='1' and ordersDetail.goodsParam.goods.id='"+param.getId().toString()+"' order by commentTime desc";
		}
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		List<?> objList = page.getObjList();
		List<Object> voList = new ArrayList<>();
		for (Object object : objList) {
			GoodsComment comment = (GoodsComment) object;
			GoodsCommentVo vo = new GoodsCommentVo();
			vo.setId(comment.getId());
			vo.setCommentContent(comment.getCommentContent());
			vo.setUserName(comment.getUserName());
			vo.setParamName(comment.getOrdersDetail().getParamName());
			vo.setStar(comment.getStar());
			vo.setCommentTime(comment.getCommentTime());
			vo.setUserSource(comment.getComeFrom());
			vo.setUserLevel("");
			vo.setUserPic("");
			vo.setPicSrc(comment.getPicSrc());
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
		List<?> ordersList = page.getObjList();
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
		int star = Integer.parseInt(comment.getStar());
		comment.setStar(String.valueOf(star*20));
		OrdersDetail ordersDetail = (OrdersDetail) hibernateUtil.find(OrdersDetail.class, param.getId().toString());
		ordersDetail.setIsComment("1");
		hibernateUtil.update(ordersDetail);
		comment.setOrdersDetail(ordersDetail);
		comment.setAuditState("0");
		comment.setIsShow("0");
		comment.setState("1");
		comment.setUserName(param.getUserToken().getUser().getUsername());
		comment.setCommentTime(new DateStr().toString());
		comment.setCreateTime(new DateStr().toString());
		comment.setCreatePerson(param.getUserToken().getUser().getUsername());
		hibernateUtil.save(comment);
		boolean flag = true;
		Orders orders = ordersDetail.getOrders();
		List<Object> detailList = hibernateUtil.hql("from OrdersDetail where state='1' and orders.id='"+orders.getId()+"'");
		for (Object object : detailList) {
			OrdersDetail detail = (OrdersDetail) object;
			if(detail.getIsComment()==null){
				flag = false;
				break;
			}
		}
		if(flag==true){
			orders.setOrderStatic("11");//此订单下所有的订单明细都已经被评论
			hibernateUtil.update(orders);
		}
		return ObjectToResult.getResult(comment);
	}
	
	/**
	 * 查询商品评论详情
	 */
	@Override
	public Result getCommentDetail(Parameter param) throws Exception {
		GoodsComment comment = (GoodsComment) hibernateUtil.hqlFirst("from GoodsComment where state='1' and ordersDetail.id='"+param.getId().toString()+"'");
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
		vo.setDetailId(ordersDetail.getId());
		return ObjectToResult.getResult(vo);
	}

	/**
	 * 查询商品的好评率
	 */
	@Override
	public Result getCommentScore(Parameter param) throws Exception {
		String hql = "from GoodsComment where state = '1' and ordersDetail.goodsParam.goods.id='"+param.getId().toString()+"' order by commentTime desc";
		List<Object> objList = hibernateUtil.hql(hql);
		GoodsCommentScoreVo vo = new GoodsCommentScoreVo();
		vo.setGoodsId(param.getId().toString());
		vo.setCount(objList.size());
		double totalStar = 0.00;
		for (Object object : objList) {
			GoodsComment comment = (GoodsComment) object;
			totalStar += Double.valueOf(comment.getStar());
		}
		String averageScore = "0";
		if(objList.size()!=0){
			averageScore = new BigDecimal(totalStar).divide(new BigDecimal(objList.size())).toString();
		}
		vo.setScore(averageScore);
		double score = Double.parseDouble(averageScore);
		if(score<=20){
			vo.setStar("20");
			vo.setMsg("差评");
		}else if(score>20&&score<=40){
			vo.setStar("40");
			vo.setMsg("差评");
		}else if(score>40&&score<=60){
			vo.setStar("60");
			vo.setMsg("差评");
		}else if(score>60&&score<=80){
			vo.setStar("80");
			vo.setMsg("一般");
		}else if(score>80){
			vo.setStar("100");
			vo.setMsg("好评");
		}
		return ObjectToResult.getResult(vo);
	}

	/**
	 * 商品评论图片上传
	 */
	@Override
	public Result goodsCommentPicUpload(Parameter param,HttpServletRequest request) throws Exception {
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		System.out.println("contenxtType类型："+request.getContentType());
		BufferedImage image = null;  
        byte[] imageByte = null;
        List<Object> objList = param.getObjList();
        StringBuffer srcs = new StringBuffer();
//        for (Object object : objList) {
        	String strFileName=param.getObj().toString().split(",")[0].split(";")[0].split("/")[1];
        	BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        	imageByte=decoder.decodeBuffer(param.getObj().toString().split(",")[1]);
        	// 处理数据
            for(int i=0;i<imageByte.length;i++){
            	if(imageByte[i]<0){
            		imageByte[i]+=256;
            	}
            }
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);  
            image = ImageIO.read(new ByteArrayInputStream(imageByte));  
            bis.close();  
            String picPath = "/upload/img/goodsComment/";
            String path = request.getSession().getServletContext().getRealPath("upload/img/goodsComment/");
            String picName = UUID.randomUUID()+"."+strFileName;
            String picAddress = picPath+ picName;
            srcs.append(picAddress);
            srcs.append(";");
            File outputfile = new File(path+picName);
            if(!outputfile.exists()){
            	outputfile.mkdirs();
            }
            ImageIO.write(image,strFileName, outputfile);  
            System.out.println(image);
            Map<String ,Object> map = new HashMap<String ,Object>();
            map.put("picAddress", picAddress);
    		map.put("picName", picName );
    		map.put("hasUpload", true );
            ImageIO.write(image,strFileName, outputfile);  
//		}
        
		
//        GoodsComment goodsComment = (GoodsComment) hibernateUtil.find(GoodsComment.class, param.getId()+"");
//		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//		Map<String, MultipartFile> multiValuemap = multipartRequest.getFileMap();
//		Set set = multiValuemap.entrySet();
//		Iterator it = set.iterator();
//		while(it.hasNext()){
//			Entry entry = (Entry) it.next();
//			entry.getValue();
//			fileList.add((MultipartFile) entry.getValue());
//		}
//		String goodsCommentId = request.getParameter("goodsCommentId");
//		GoodsComment goodsComment = (GoodsComment) hibernateUtil.find(GoodsComment.class, goodsCommentId);
//		StringBuffer srcs = new StringBuffer();
//		System.out.println(goodsCommentId);
//		String path = request.getSession().getServletContext().getRealPath("/upload/img/goodsComment/");
//		String picPath = "/upload/img/goodsComment/";
//		int count = 0;
//		if (fileList.size()>0) {
//			for (MultipartFile multipartFile : fileList) {
//				count++;
//				String picName = UUID.randomUUID()+"."+multipartFile.getOriginalFilename().split("\\.")[1];
//			    String picAddress = picPath+ picName;
//			    srcs.append(picAddress);
//			    if(count!=fileList.size()){
//			    	srcs.append(";");
//			    }
//				File targetFile = new File(path, picName);  
//		        if(!targetFile.exists()){  
//		            targetFile.mkdirs();  
//		        } 
//		        multipartFile.transferTo(targetFile);
//			}
//		}
//		goodsComment.setPicSrc(srcs.toString());
//		hibernateUtil.update(goodsComment);
		return ObjectToResult.getResult(map);
	}

	
	
}

/**
 * 
 */
package com.ftoul.manage.util.upload.service.impl;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.util.upload.service.UploadServ;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsUploadpic;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.image.ImageUtil;

/**
 * 
 * 
 * 类描述：upload 工具类
 * 
 * @author: yw
 * @date： 日期：2016年8月3日 时间：上午10:26:11
 * @version 1.0
 * 
 */
@Service("UploadServImpl")
public class UploadServImpl implements UploadServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public Result upload(MultipartFile [] pic, HttpServletRequest request,HttpServletResponse response) throws Exception {

		Result result = new Result();
//		 PrintWriter out = response.getWriter();
		for (MultipartFile multipartFile : pic) {
			if (multipartFile.isEmpty()) {
//				 out.print("1`请选择文件后上传");
//				 out.flush();
//				System.out.println("1`请选择文件后上传");
//				return null;
			} else {
				String realPath = request.getSession().getServletContext()
						.getRealPath("/image");
				String originalFilename = multipartFile.getOriginalFilename();
				System.out.println("文件原名: " + originalFilename);
				System.out.println("文件名称: " + multipartFile.getName());
				System.out.println("文件长度: " + multipartFile.getSize());
				System.out.println("文件类型: " + multipartFile.getContentType());
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),new File(realPath, originalFilename));
				result.setResult(0);
				return result;
			}
		}
		return result;
	}

	@Override
	public Result pictureFileUpload(Parameter parameter, HttpServletRequest request)
			throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		List<MultipartFile> fileList = multipartRequest.getFiles("file_data");
		//图片文件夹名称
		String folderName = request.getParameter("folderName");
		//商品ID
		String goodsId = request.getParameter("goodsId");
		Goods goods = new Goods() ;
		if(goodsId!=null){
			goods = (Goods)hibernateUtil.find(Goods.class, goodsId);
		}
		String path = request.getSession().getServletContext().getRealPath("upload/img/"+folderName+"/");
		String thumbnailPath = request.getSession().getServletContext().getRealPath("upload_src/img/"+folderName+"/");
		String picPath = "/upload/img/" + folderName + "/";
		String thumbnailSrc = "/upload_src/img/" + folderName + "/";
		Map<String ,Object> map = new HashMap<String ,Object>();
		if (fileList.size()>0) {
			for (MultipartFile multipartFile : fileList) {
				String picName = UUID.randomUUID()+"."+multipartFile.getOriginalFilename().split("\\.")[1];
			    String picAddress = picPath+ picName;
			    String thumbnailAddress = thumbnailSrc + picName;
				File targetFile = new File(path, picName);  
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        }  
				multipartFile.transferTo(targetFile);
				ImageUtil.compressImage(path+picName, thumbnailPath+picName, 300);
				map.put("folderName", folderName);
				map.put("thumbnailSrc", thumbnailAddress);
				map.put("picAddress", picAddress );
				map.put("picName", picName );
				map.put("hasUpload", true );
				
				
				GoodsUploadpic goodsUploadpic =  new GoodsUploadpic();
				if(goods.getId()!=null)
				 goodsUploadpic.setGoods(goods);
				goodsUploadpic.setPicSrc(picAddress);
				if ("goodsMain".equals(folderName)) {
					goodsUploadpic.setPicType("0");
					if (!"".equals(goods.getPicSrc())) {
						hibernateUtil.execHql("update GoodsUploadpic set state='0' where goods.id='"+goodsId+"' and picType='0'");
					}
					hibernateUtil.execHql("update Goods set picSrc='"+ thumbnailAddress +"' where id ='"+goodsId+"'");
				}
				if("goods".equals(folderName)){
					goodsUploadpic.setPicType("1");
				}
				if("goodsInfo".equals(folderName)){
					goodsUploadpic.setPicType("2");
				}
				goodsUploadpic.setCreateTime(new DateStr().toString());
				goodsUploadpic.setState("1");
				goodsUploadpic.setCreatePerson(parameter.getManageToken().getLoginUser().getLoginName());
				
				hibernateUtil.save(goodsUploadpic);
			}
		}
		return ObjectToResult.getResult(map);
	}

	@Override
	public Result goodsPicUpload(Parameter parameter, HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		List<MultipartFile> fileList = multipartRequest.getFiles("file_data");
		//图片文件夹名称
		String folderName = request.getParameter("folderName");
		String path = request.getSession().getServletContext().getRealPath("upload/img/"+folderName+"/");
		String thumbnailPath = request.getSession().getServletContext().getRealPath("upload_src/img/"+folderName+"/");
		String picPath = "/upload/img/" + folderName + "/";
		String thumbnailSrc = "/upload_src/img/" + folderName + "/";
		Map<String ,Object> map = new HashMap<String ,Object>();
		if (fileList.size()>0) {
			for (MultipartFile multipartFile : fileList) {
				String picName = UUID.randomUUID()+"."+multipartFile.getOriginalFilename().split("\\.")[1];
			    String picAddress = picPath+ picName;
			    String thumbnailAddress = thumbnailSrc + picName;
				File targetFile = new File(path, picName);  
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        } 
			        multipartFile.transferTo(targetFile);
					ImageUtil.compressImage(path+picName, thumbnailPath+picName, 300);
					map.put("folderName", folderName);
					map.put("thumbnailSrc", thumbnailAddress);
					map.put("picAddress", picAddress );
					map.put("picName", picName );
					map.put("hasUpload", true );
			}
		}
		return ObjectToResult.getResult(map);
	}

	@Override
	public Result advertPicUpload(Parameter parameter, HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		List<MultipartFile> fileList = multipartRequest.getFiles("file_data");
		//图片文件夹名称
		String folderName = request.getParameter("folderName");
		String path = request.getSession().getServletContext().getRealPath("upload/img/"+folderName+"/");
		String picPath = "/upload/img/" + folderName + "/";
		Map<String ,Object> map = new HashMap<String ,Object>();
		if (fileList.size()>0) {
			for (MultipartFile multipartFile : fileList) {
				String picName = UUID.randomUUID()+"."+multipartFile.getOriginalFilename().split("\\.")[1];
			    String picAddress = picPath+ picName;
				File targetFile = new File(path, picName);  
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        } 
			        multipartFile.transferTo(targetFile);
					map.put("folderName", folderName);
					map.put("picAddress", picAddress );
					map.put("picName", picName );
					map.put("hasUpload", true );
			}
		}
		return ObjectToResult.getResult(map);
	}

	@Override
	public Result businessManagePicUpload(Parameter parameter,
			HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		List<MultipartFile> fileList = multipartRequest.getFiles("file_data");
		//图片文件夹名称
		String folderName = request.getParameter("folderName");
		String path = request.getSession().getServletContext().getRealPath("upload/img/"+folderName+"/");
		String picPath = "/upload/img/" + folderName + "/";
		Map<String ,Object> map = new HashMap<String ,Object>();
		if (fileList.size()>0) {
			for (MultipartFile multipartFile : fileList) {
				String picName = UUID.randomUUID()+"."+multipartFile.getOriginalFilename().split("\\.")[1];
			    String picAddress = picPath+ picName;
				File targetFile = new File(path, picName);  
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        } 
			        multipartFile.transferTo(targetFile);
					map.put("folderName", folderName);
					map.put("picAddress", picAddress );
					map.put("picName", picName );
					map.put("hasUpload", true );
			}
		}
		return ObjectToResult.getResult(map);
	}

	@Override
	public Result businessBankPicUpload(Parameter parameter,
			HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		List<MultipartFile> fileList = multipartRequest.getFiles("file_data");
		//图片文件夹名称
		String folderName = request.getParameter("folderName");
		String path = request.getSession().getServletContext().getRealPath("upload/img/"+folderName+"/");
		String picPath = "/upload/img/" + folderName + "/";
		Map<String ,Object> map = new HashMap<String ,Object>();
		if (fileList.size()>0) {
			for (MultipartFile multipartFile : fileList) {
				String picName = UUID.randomUUID()+"."+multipartFile.getOriginalFilename().split("\\.")[1];
			    String picAddress = picPath+ picName;
				File targetFile = new File(path, picName);  
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        } 
			        multipartFile.transferTo(targetFile);
					map.put("folderName", folderName);
					map.put("picAddress", picAddress );
					map.put("picName", picName );
					map.put("hasUpload", true );
			}
		}
		return ObjectToResult.getResult(map);
	}
}

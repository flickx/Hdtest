/**
 * 
 */
package com.ftoul.manage.util.upload.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * @author 李丁
 * @date:2016年7月20日 下午4:44:27
 * @类说明 :
 */

public interface UploadServ {
	Result upload(MultipartFile[] pic, HttpServletRequest request,HttpServletResponse response) throws Exception;
	Result pictureFileUpload(Parameter parameter, HttpServletRequest request) throws Exception;
	Result goodsPicUpload(Parameter parameter, HttpServletRequest request) throws Exception;
	Result advertPicUpload(Parameter parameter, HttpServletRequest request) throws Exception;
}

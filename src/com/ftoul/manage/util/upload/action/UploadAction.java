package com.ftoul.manage.util.upload.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goods.service.GoodsUploadpicServ;
import com.ftoul.manage.util.upload.service.UploadServ;
/**
 * 
 * 
 * 类描述：图片上传的action
 * 
 * @author: liding
 * @date： 日期：2016年8月2日 时间：下午5:58:49
 * @version 1.0
 * 
 */

@Controller
@RequestMapping(value = "/manage/upload")
public class UploadAction {

	@Autowired
	private UploadServ uploadServ;

	@Autowired
	private GoodsUploadpicServ goodsUploadpicServ;
	
	@RequestMapping(value = "upload")
	public @ResponseBody
	Result upload(@RequestParam MultipartFile[] pic,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return uploadServ.upload(pic, request, response);
	}

	/**
	 * 商品修改时图像上传 liding
	 * 
	 * @param param
	 *            页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception
	 */
	@RequestMapping(value = "pictureFileUpload")
	public @ResponseBody
	Result pictureFileUpload(String param, HttpServletRequest request)
			throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return uploadServ.pictureFileUpload(parameter, request);
	}
	/**
	 * 添加商品时图像上传 liding
	 * 
	 * @param param
	 *            页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception
	 */
	@RequestMapping(value = "goodsPicUpload")
	public @ResponseBody
	Result goodsPicUpload(String param, HttpServletRequest request)
			throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return uploadServ.goodsPicUpload(parameter, request);
	}
	/**
	 * 广告图像上传 liding
	 * 
	 * @param param
	 *            页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception
	 */
	@RequestMapping(value = "advertPicUpload")
	public @ResponseBody
	Result advertPicUpload(String param, HttpServletRequest request)
			throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return uploadServ.advertPicUpload(parameter, request);
	}
	/**
	 * 经营信息资料上传
	 * 
	 * @param param
	 *            页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception
	 */
	@RequestMapping(value = "businessManagePicUpload")
	public @ResponseBody
	Result businessManagePicUpload(String param, HttpServletRequest request)
			throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return uploadServ.businessManagePicUpload(parameter, request);
	}
	/**
	 * 银行账户信息上传
	 * 
	 * @param param
	 *            页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception
	 */
	@RequestMapping(value = "businessBankPicUpload")
	public @ResponseBody
	Result businessBankPicUpload(String param, HttpServletRequest request)
			throws Exception {
		Parameter parameter = Common.jsonToParam(param);
		return uploadServ.businessBankPicUpload(parameter, request);
	}

}

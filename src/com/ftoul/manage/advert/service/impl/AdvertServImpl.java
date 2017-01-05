package com.ftoul.manage.advert.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.advert.service.AdvertServ;
import com.ftoul.manage.advert.vo.UploadPicVo;
import com.ftoul.po.IndexCarouselPic;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("AdvertServImpl")
public class AdvertServImpl implements AdvertServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * 获取轮播图列表（带分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getIndexCarousePicList(Parameter param) throws Exception {
		String hql = "from IndexCarouselPic where state = '1'" + param.getWhereStr() + param.getOrderBy();
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 保存轮播图
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveIndexCarouselPic(Parameter param) throws Exception {
		IndexCarouselPic indexCarouselPic = (IndexCarouselPic) JSONObject.toBean((JSONObject) param.getObj(),IndexCarouselPic.class);
		Object res;
		//上传图片
		List<UploadPicVo> picVos = param.getUploadPicVoList();
		if (picVos!=null && picVos.size()>0) {
			UploadPicVo uploadPicVo = picVos.get(0);
			if (uploadPicVo.getHasUpload()) {
				indexCarouselPic.setPicAddress(uploadPicVo.getPicAddress());
				indexCarouselPic.setPicName(uploadPicVo.getPicName());
			}
		}
		else{
			//判断最新的对象里图片是否存在，如果不存在则置空对象的图片信息
				IndexCarouselPic currentIndexPic = (IndexCarouselPic) hibernateUtil.find(IndexCarouselPic.class, indexCarouselPic.getId()+"");
			if (currentIndexPic.getPicAddress()==null ||"".equals(currentIndexPic.getPicAddress())) {
				indexCarouselPic.setPicAddress("");
				indexCarouselPic.setPicName("");
			}
		}
		if(Common.isNull(indexCarouselPic.getId())){
			indexCarouselPic.setCreateTime(new DateStr().toString());
			indexCarouselPic.setState("1");
			res = hibernateUtil.save(indexCarouselPic);
		}else{
			indexCarouselPic.setState("1");
			indexCarouselPic.setModifyTime(new DateStr().toString());
			res = hibernateUtil.update(indexCarouselPic);
		}
		return ObjectToResult.getResult(res);
	}
	/**
	 * 删除轮播图图片
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result deleteIndexCarouselPicById(Parameter parameter)
			throws Exception {
		Integer num = hibernateUtil.execHql("update IndexCarouselPic set picName='' , picAddress='' where id ='"+parameter.getId()+"'");
		return ObjectToResult.getResult(num);
	}
	/**
	 * 删除轮播图（全部删除，包括图片）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@Override
	public Result deleteIndexPic(Parameter parameter) throws Exception {
		Integer num = hibernateUtil.execHql("update IndexCarouselPic set state = '0' where id in ("+StrUtil.getIds(parameter.getId())+")");
		return ObjectToResult.getResult(num);
	}
	/**
	 * 获取轮播图对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	public Result getIndexCarouselPicById(Parameter param) throws Exception{
		IndexCarouselPic indexCarouselPic = (IndexCarouselPic) hibernateUtil.find(IndexCarouselPic.class, param.getId()+"");
		return ObjectToResult.getResult(indexCarouselPic);
	}
}

package com.ftoul.web.goods.service.impl;

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
import com.ftoul.manage.advert.vo.UploadPicVo;
import com.ftoul.po.CrossBorderMuseum;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.goods.service.CrossBorderMuseumServ;

@Service("CrossBorderMuseumServImpl")
public class CrossBorderMuseumServImpl implements CrossBorderMuseumServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * 获取跨境馆列表（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@Override
	public Result getCrossBorderMuseumList(Parameter param)
			throws Exception {
		String hql = "from CrossBorderMuseum where state = '1'" + param.getWhereStr() + param.getOrderBy();
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 保存/更新跨境馆对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@Override
	public Result saveCrossBorderMuseum(Parameter param) throws Exception {
		CrossBorderMuseum crossBorderMuseum = (CrossBorderMuseum) JSONObject.toBean((JSONObject) param.getObj(),CrossBorderMuseum.class);
		Object res;
		//上传图片
		List<UploadPicVo> picVos = param.getUploadPicVoList();
		if (picVos!=null && picVos.size()>0) {
			UploadPicVo uploadPicVo = picVos.get(0);
			if (uploadPicVo.getHasUpload()) {
				crossBorderMuseum.setPicSrc(uploadPicVo.getPicAddress());
			}
		}
		else{
			//判断最新的对象里图片是否存在，如果不存在则置空对象的图片信息
				CrossBorderMuseum current = (CrossBorderMuseum) hibernateUtil.find(CrossBorderMuseum.class, crossBorderMuseum.getId()+"");
			if (null ==current) {
				crossBorderMuseum.setPicSrc("");
			}
		}
		if(Common.isNull(crossBorderMuseum.getId())){
			crossBorderMuseum.setCreateTime(new DateStr().toString());
			crossBorderMuseum.setState("1");
			res = hibernateUtil.save(crossBorderMuseum);
		}else{
			crossBorderMuseum.setState("1");
			crossBorderMuseum.setModifyTime(new DateStr().toString());
			res = hibernateUtil.update(crossBorderMuseum);
		}
		return ObjectToResult.getResult(res);
	}
	/**
	 * 获取跨境馆对象
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@Override
	public Result getCrossBorderMuseumById(Parameter param) throws Exception {
		CrossBorderMuseum crossBorderMuseum = (CrossBorderMuseum) hibernateUtil.find(CrossBorderMuseum.class, param.getId()+"");
		return ObjectToResult.getResult(crossBorderMuseum);
	}
	/**
	 * 删除图片
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@Override
	public Result deletePic(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update CrossBorderMuseum set picSrc=''  where id ='"+param.getId()+"'");
		return ObjectToResult.getResult(num);
	}
	/**
	 * 删除跨境馆
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@Override
	public Result deleteCrossBorderMuseum(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update CrossBorderMuseum set state='0'  where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}
	
}

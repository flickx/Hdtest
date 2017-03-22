package com.ftoul.pc.website.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.advert.vo.UploadPicVo;
import com.ftoul.pc.website.service.SiteInfoServ;
import com.ftoul.po.SiteInfo;
import com.ftoul.util.hibernate.HibernateUtil;
@Service("SiteInfoServImpl")
public class SiteInfoServImpl implements SiteInfoServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public Result getSiteInfo(Parameter parameter) throws Exception {
		String hql = "from SiteInfo where 1 = 1";
		Object o = hibernateUtil.hqlFirst(hql);
		return ObjectToResult.getResult(o);
	}

	@Override
	public Result saveSiteInfo(Parameter param) throws Exception {
		Object res;
		SiteInfo siteInfo = (SiteInfo) JSONObject.toBean((JSONObject) param.getObj(),SiteInfo.class);
		//上传logo图片
		List<UploadPicVo> logoPic = param.getUploadPicVoList();
		if (logoPic!=null && logoPic.size()>0) {
			UploadPicVo uploadPicVo = logoPic.get(0);
			if (uploadPicVo.getHasUpload()) {
				siteInfo.setLogoImg(uploadPicVo.getPicAddress());
			}
		}else{
			siteInfo.setLogoImg(null);
		}
		//上传头部背景图片
		List<UploadPicVo> bgPic = param.getUploadPicInfoVoList();
		if (bgPic!=null && bgPic.size()>0) {
			UploadPicVo uploadPicVo = bgPic.get(0);
			if (uploadPicVo.getHasUpload()) {
				siteInfo.setTopImg(uploadPicVo.getPicAddress());
			}
		}else{
			siteInfo.setTopImg(null);
		}
		String hql = "from SiteInfo where 1 = 1";
		Object o = hibernateUtil.hqlFirst(hql);
		if (o==null) {
			res = hibernateUtil.save(siteInfo);
		}else{
			res = hibernateUtil.update(siteInfo);
		}
		return ObjectToResult.getResult(res);
	}
	
	/**
	 * 删除logo图片
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result deleteLogoPic(Parameter parameter) throws Exception {
		Integer num = hibernateUtil.execHql("update SiteInfo set logoImg= '' where id ='"+parameter.getId()+"'");
		return ObjectToResult.getResult(num);
	}
	/**
	 * 删除头部背景图片
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result deleteTopBgPic(Parameter parameter) throws Exception {
		Integer num = hibernateUtil.execHql("update SiteInfo set topImg= '' where id ='"+parameter.getId()+"'");
		return ObjectToResult.getResult(num);
	}
}

package com.ftoul.manage.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.advert.vo.UploadPicVo;
import com.ftoul.manage.goods.service.GoodsPropServ;
import com.ftoul.manage.goods.vo.GoodsParamVo;
import com.ftoul.manage.goods.vo.GoodsPropVo;
import com.ftoul.manage.goods.vo.GoodsTypePicVo;
import com.ftoul.manage.goods.vo.GoodsTypeVo;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsProp;
import com.ftoul.po.GoodsPropertyTypeInfo;
import com.ftoul.po.GoodsType;
import com.ftoul.util.hibernate.HibernateUtil;

/**
 * 
*
* 类描述：
* @author: yw
* @date： 日期：2016年7月20日 时间：上午10:28:05
* @version 1.0
*
 */
@Service("GoodPropServImpl")
public class GoodsPropServImpl implements GoodsPropServ {

	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public Result getGoodsProp(Parameter param) throws Exception {
		String hql ="from GoodsProp where state ='1' and goods.id= '"+param.getId()+"'";
		List<Object> goodsPropList = this.hibernateUtil.hql(hql);
		List<GoodsPropVo> goodsPropVoList = new ArrayList<GoodsPropVo>();
		if(goodsPropList!=null&&goodsPropList.size()>0){
			for(Object gp:goodsPropList){
				GoodsProp goodsProp = (GoodsProp) gp;
				GoodsPropVo goodsPropVo = new GoodsPropVo();
				if(goodsProp.getId()!=null)
					goodsPropVo.setId(goodsProp.getId());
				if(goodsProp.getGoods()!=null)
					goodsPropVo.setGoods(goodsProp.getGoods());
				if(goodsProp.getGoodsPropertyTypeInfo()!=null&&goodsProp.getGoodsPropertyTypeInfo().getName()!=null)
					goodsPropVo.setName(goodsProp.getGoodsPropertyTypeInfo().getName());
				if(goodsProp.getContent()!=null){
					goodsPropVo.setContent(goodsProp.getContent());
				}
				goodsPropVoList.add(goodsPropVo);
					
			}
		}
		return ObjectToResult.getResult(goodsPropVoList);
	}

	@Override
	public Result saveGoodsProp(Parameter param) throws Exception {
		GoodsPropVo goodsPropVo = (GoodsPropVo) JSONObject.toBean((JSONObject) param.getObj(),GoodsPropVo.class);
//		ObjectMapper objectMapper = new ObjectMapper(); 
//		GoodsPropVo goodsPropVo = (GoodsPropVo) objectMapper.readValue(param.getObj()+"", GoodsPropVo.class);
		GoodsProp goodsProp = new GoodsProp();
		if(goodsPropVo.getGoods()!=null){
			//删除原来的属性
			String hql ="from GoodsProp where goods.id='"+goodsPropVo.getGoods().getId()+"'";
			List<Object> gpList = this.hibernateUtil.hql(hql);
			if(gpList!=null&&gpList.size()>0){
				for(Object gp :gpList){
					GoodsProp gpp =(GoodsProp) gp;
					gpp.setState("0");
					this.hibernateUtil.update(gpp);
				}
			}
		
	    }
		if(goodsPropVo.getId()!=null){
			goodsProp.setId(goodsPropVo.getId());
		}
		
		if(goodsPropVo.getGoodsPropTypeInfoId()!=null){
			GoodsPropertyTypeInfo  gpti = (GoodsPropertyTypeInfo) this.hibernateUtil.find(GoodsPropertyTypeInfo.class, goodsPropVo.getGoodsPropTypeInfoId()+"");
			goodsProp.setGoodsPropertyTypeInfo(gpti);
		}
		
		if(goodsPropVo.getGoods()!=null){
			goodsProp.setGoods(goodsPropVo.getGoods());
		}
		if(goodsPropVo.getContent()!=null){
			goodsProp.setContent(goodsPropVo.getContent());
		}
		Object res;
		goodsProp.setState("1");
		goodsProp.setCreateTime( new DateStr().toString());
		if(goodsProp.getId()!=null){
			res=this.hibernateUtil.update(goodsProp);
		}else {
			res=this.hibernateUtil.save(goodsProp);
		}
	
	   return ObjectToResult.getResult(res);
	}
}

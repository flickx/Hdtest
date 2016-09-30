package com.ftoul.web.goods.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.goods.service.GoodsPropertyTypeInfoServ;
import com.ftoul.po.GoodsPropType;
import com.ftoul.po.GoodsPropertyTypeInfo;
import com.ftoul.util.hibernate.HibernateUtil;

/**
 * 
*
* 类描述：商品属性详情对象
* @author: yw
* @date： 日期：2016年7月26日 时间：下午4:39:39
* @version 1.0
*
 */
@Service("WebGoodsPropertyTypeInfoServImpl")
public class GoodsPropertyTypeInfoServImpl implements GoodsPropertyTypeInfoServ {

	@Autowired
	private HibernateUtil hibernateUtil;

	
	/**
	 * 保存/更新商品品牌对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveGoodsPropertyTypeInfo(Parameter param) throws Exception {
		GoodsPropertyTypeInfo goodsPropertyTypeInfo = (GoodsPropertyTypeInfo) JSONObject.toBean((JSONObject) param.getObj(),GoodsPropertyTypeInfo.class);
		Object res;
		if(Common.isNull(goodsPropertyTypeInfo.getId())){
			goodsPropertyTypeInfo.setCreateTime(new DateStr().toString());
			goodsPropertyTypeInfo.setState("1");
			res = hibernateUtil.save(goodsPropertyTypeInfo);
		}else{
			goodsPropertyTypeInfo.setModifyTime(new DateStr().toString());
			res = hibernateUtil.update(goodsPropertyTypeInfo);
		}
		return ObjectToResult.getResult(res);
	}
	
	
	/**
	 * 
	 * 删除商品属性详情对象
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	public Result delGoodsPropertyTypeInfo(Parameter param) throws Exception{
		GoodsPropertyTypeInfo goodsPropertyTypeInfo = (GoodsPropertyTypeInfo) hibernateUtil.find(GoodsPropertyTypeInfo.class, param.getId()+"");
		Object res;
		goodsPropertyTypeInfo.setState("0");
		res=	hibernateUtil.update(goodsPropertyTypeInfo);
		return ObjectToResult.getResult(res);
	}

	
	
	/**
	 * 
	 * 通过大类id得到商品属性详情
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsPropInfoTypeListByTypeId(Parameter param) {
		if(param.getId()!=null){
			GoodsPropType goodsPropType =(GoodsPropType) hibernateUtil.find(GoodsPropType.class, param.getId()+"");
			if(goodsPropType!=null){
				String hql1 ="from GoodsPropertyTypeInfo where goodsPropType.id = '"+goodsPropType.getId() +"'";
				List<Object> goodsPropertyTypeInfoList = hibernateUtil.hql(hql1);
				return ObjectToResult.getResult(goodsPropertyTypeInfoList);
			}
		}
		return null;
	};
}

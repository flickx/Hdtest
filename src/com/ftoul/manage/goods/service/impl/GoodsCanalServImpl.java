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
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.goods.service.GoodsCanalServ;
import com.ftoul.manage.goods.service.GoodsParamServ;
import com.ftoul.manage.goods.vo.GoodsListVo;
import com.ftoul.manage.goods.vo.GoodsParamVo;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsBrand;
import com.ftoul.po.GoodsCanal;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.GoodsPurchase;
import com.ftoul.po.User;
import com.ftoul.util.hibernate.HibernateUtil;

/**
* 
*
* 类描述：商品渠道业务实现类
* @author: yw
* @date： 日期：2016年8月8日 时间：上午10:01:10
* @version 1.0
*
*/
@Service("GoodsCanalImpl")
public class GoodsCanalServImpl implements GoodsCanalServ {

	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public Result saveGoodsCanal(Parameter param) throws Exception {
		GoodsCanal goodsCanal = (GoodsCanal) JSONObject.toBean((JSONObject) param.getObj(),GoodsCanal.class);
		Object res;
		if(Common.isNull(goodsCanal.getId())){
			goodsCanal.setCreateTime(new DateStr().toString());
			goodsCanal.setState("1");
			res = hibernateUtil.save(goodsCanal);
		}else{
			res =hibernateUtil.update(goodsCanal);
		}
		return ObjectToResult.getResult(res);
	}

	@Override
	public Result getGoodsCanalPage(Parameter param) throws Exception {
		String hql = "from GoodsCanal where state = '1'" + param.getWhereStr() + param.getOrderBy() ;
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}

	@Override
	public Result delGoodsCanal(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update GoodsCanal set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}

	@Override
	public Result getGoodsCanalList(Parameter param) throws Exception {
		String hql ="from GoodsCanal where state=1" ;
		List<Object> goodsCanalList =hibernateUtil.hql(hql);
		return ObjectToResult.getResult(goodsCanalList);
	}

}

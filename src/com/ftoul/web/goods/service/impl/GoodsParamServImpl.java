package com.ftoul.web.goods.service.impl;

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
import com.ftoul.web.goods.service.GoodsParamServ;
import com.ftoul.manage.goods.vo.GoodsListVo;
import com.ftoul.manage.goods.vo.GoodsParamVo;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsBrand;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.GoodsPurchase;
import com.ftoul.util.hibernate.HibernateUtil;

/**
* 
*
* 类描述：商品参数业务实现类
* @author: yw
* @date： 日期：2016年8月8日 时间：上午10:01:10
* @version 1.0
*
*/
@Service("WebGoodsParamImpl")
public class GoodsParamServImpl implements GoodsParamServ {

	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public Result saveStockFromOrder(Parameter param) throws Exception {
		GoodsParam goodsParam = (GoodsParam) this.hibernateUtil.find(GoodsParam.class, param.getId()+"");
		int amount = Integer.parseInt(param.getKey());
		int stock= Integer.parseInt(goodsParam.getStock());
		int ret = stock-amount;
		goodsParam.setStock(ret+"");
		Object res;
		res =this.hibernateUtil.update(goodsParam);
		return ObjectToResult.getResult(res);
	}

}

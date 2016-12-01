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

	/**
	 * 根据goodsId得到总库存
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getSumStockBygoodsId(Parameter param) throws Exception {
		String hql = "from GoodsParam where state ='1' and goods.id ='"+ param.getId()+"'";
		List<Object>goodsParamList = this.hibernateUtil.hql(hql);
		int sumStock =0;
		if(goodsParamList==null||goodsParamList.size()<=0){
			return 0;
		}
		for(Object obj:goodsParamList ){
			int stock =0;
			GoodsParam goodsParam = (GoodsParam) obj;
			if(goodsParam.getStock()==null||goodsParam.getStock()==""){
				stock =0;
			}else{
				stock = Integer.parseInt(goodsParam.getStock());
			}
			sumStock+=stock;
		}
		return sumStock;
	}
	
	

}

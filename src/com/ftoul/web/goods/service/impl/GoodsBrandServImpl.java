package com.ftoul.web.goods.service.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.web.goods.service.GoodsBrandServ;
import com.ftoul.po.GoodsBrand;
import com.ftoul.util.hibernate.HibernateUtil;

/**
*
*
*
* 类描述：
* @author: yw
* @date： 日期：2016年7月22日 时间：上午10:28:05
* @version 1.0
*
*/
@Service("WebGoodBrandServImpl")
public class GoodsBrandServImpl implements GoodsBrandServ {

	@Autowired
	private HibernateUtil hibernateUtil;

	
	/**
	 * 保存/更新商品品牌对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveGoodsBrand(Parameter param) throws Exception {
		GoodsBrand goodsBrand = (GoodsBrand) JSONObject.toBean((JSONObject) param.getObj(),GoodsBrand.class);
		Object res;
		if(Common.isNull(goodsBrand.getId())){
			goodsBrand.setCreateTime(new DateStr().toString());
			goodsBrand.setState("1");
			res = hibernateUtil.save(goodsBrand);
		}else{
			goodsBrand.setModifyTime(new DateStr().toString());
			res = hibernateUtil.update(goodsBrand);
		}
		return ObjectToResult.getResult(res);
	}

	

	/**
	 * 
	 * 删除商品品牌对象
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	@Override
	public Result delGoodsBrand(Parameter param) throws Exception {
		GoodsBrand goodsBrand = (GoodsBrand) hibernateUtil.find(GoodsBrand.class, param.getId()+"");
		Object res;
		goodsBrand.setState("0");
		res=	hibernateUtil.update(goodsBrand); 
		return ObjectToResult.getResult(res);
	}



	@Override
	public Result getGoodsBrandListPage(Parameter param) {
		String hql = "from GoodsBrand where state = '1'" + param.getWhereStr() + param.getOrderBy() ;
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}

	/**
	 * 
	 * 通过id得到商品品牌
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	

	@Override
	public Result getGoodsBrandById(Parameter param) {
		GoodsBrand goodsBrand = (GoodsBrand) hibernateUtil.find(GoodsBrand.class, param.getId() + "");
		return ObjectToResult.getResult(goodsBrand);
	}
	
	
}

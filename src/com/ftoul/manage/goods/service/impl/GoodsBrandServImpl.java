package com.ftoul.manage.goods.service.impl;

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
import com.ftoul.manage.goods.service.GoodsBrandServ;
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
@Service("GoodBrandServImpl")
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
			//重复性判断
			List<Object> list = hibernateUtil.hql("from GoodsBrand where state = '1' and name = '"+goodsBrand.getName()+"'");
			if(list.size()>0){
				throw new Exception("品牌名称已存在，保存失败");
			}else{
				goodsBrand.setCreateTime(new DateStr().toString());
				goodsBrand.setState("1");
				res = hibernateUtil.save(goodsBrand);
			}
		}else{
			GoodsBrand brand = (GoodsBrand) hibernateUtil.find(GoodsBrand.class, goodsBrand.getId());
			//重复性判断
			List<Object> list = hibernateUtil.hql("from GoodsBrand where state = '1' and id != '"+goodsBrand.getId()+"'"+" and name = '"+goodsBrand.getName()+"'");
			if(list.size()>0){
				throw new Exception("品牌名称已存在，保存失败");
			}else{
				brand.setState("1");
				brand.setName(goodsBrand.getName());
				brand.setEnName(goodsBrand.getEnName());
				brand.setStartTime(goodsBrand.getStartTime());
				brand.setEndTime(goodsBrand.getEndTime());
				brand.setLogo(goodsBrand.getLogo());
				brand.setModifyTime(new DateStr().toString());
				res = hibernateUtil.update(brand);
			}
		}
		return ObjectToResult.getResult(res);
	}
	/**
	 * 删除商品品牌对象
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	@Override
	public Result delGoodsBrand(Parameter param) throws Exception {
		String hql = "update GoodsBrand set state = '0' where id in ("+StrUtil.getIds(param.getId())+")";
		Integer num = hibernateUtil.execHql(hql);
		return ObjectToResult.getResult(num);
	}

	@Override
	public Result getGoodsBrandListPage(Parameter param) {
		String hql = "from GoodsBrand where state = '1'" + param.getWhereStr() + param.getOrderBy() ;
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
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

	/**
	 * 
	 * 得到所有的商品品牌
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */

	@Override
	public Result getGoodsBrandList(Parameter param) {
		String hql ="from GoodsBrand where state=1" ;
		List<Object> goodsBrandList =hibernateUtil.hql(hql);
		return ObjectToResult.getResult(goodsBrandList);
	}

	@Override
	public Result getAllGoodsBrandList(Parameter parameter) {
		String hql = "from GoodsBrand where state =1 order by createTime desc";
		Page page = hibernateUtil.hqlPage(null, hql,parameter.getPageNum(),parameter.getPageSize());
		return ObjectToResult.getResult(page);
	}



	@Override
	public Result getGoodsBrand(Parameter parameter) throws Exception {
		String hql ="from GoodsBrand where state =1";
		List<Object> goodsBrandList = this.hibernateUtil.hql(hql);
		return ObjectToResult.getResult(goodsBrandList);
	}
	@Override
	public Result delGoodsBrandLogo(Parameter parameter) throws Exception {
		Integer num = hibernateUtil.execHql("update GoodsBrand set logo='' where id ='"+parameter.getId()+"'");
		return ObjectToResult.getResult(num);
	}

}

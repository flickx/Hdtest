package com.ftoul.pc.search.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.search.service.SearchKeyNameServ;
import com.ftoul.util.hibernate.HibernateUtil;
@Service("SearchKeyNameServImpl")
public class SearchKeyNameServImpl implements SearchKeyNameServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * 获取搜索关键字
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getSearchKeyName(Parameter param) throws Exception {
		String hql="from SearchKeyName where state ="+param.getKey();
		List<Object> list=hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}
	/**
	 * 根据关键字查询商品（分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsBykeyName(Parameter param) throws Exception {
		String hql="";
		if(param.getKey()!=null&&!param.getKey().equals("{}")){
			hql=
				" FROM" +
				"	Goods " +
				"WHERE  state =1  and grounding='1' and (" +
				"	title LIKE '%"+ param.getKey()+ "%' " +
				"OR goodsType3.id IN (" +
				"	SELECT" +
				"		id" +
				"	FROM" +
				"		GoodsType" +
				"	WHERE" +
				"		NAME LIKE '%"+ param.getKey()+ "%' " +
				")" +
				"OR goodsType2.id IN (" +
				"	SELECT" +
				"		id" +
				"	FROM" +
				"		GoodsType" +
				"	WHERE" +
				"		NAME LIKE '%"+ param.getKey()+ "%' " +
				")" +
				"OR goodsType1.id IN (" +
				"	SELECT" +
				"		id" +
				"	FROM" +
				"		GoodsType" +
				"	WHERE" +
				"		NAME LIKE '%"+ param.getKey()+ "%' " +
				")" +
				"OR goodsBrand.id IN ( " +
				"	SELECT " +
				"		id " +
				"	FROM " +
				"		GoodsBrand " +
				"	WHERE " +
				"		NAME LIKE '%"+ param.getKey()+"%' " +
				")" +
				"OR goodsPropType.id IN ( " +
				"	SELECT" +
				"		id " +
				"	FROM" +
				"		GoodsPropType " +
				"	WHERE" +
				"		NAME LIKE '%"+ param.getKey()+"%' )) "+param.getOrderBy();
		}else{
			hql = "from Goods where state = '1' and grounding = '1' and id in (select gp.goods.id from GoodsParam gp where gp.state='1') "+param.getOrderBy();
		}
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 猜猜你喜欢
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result getLikeGoodsList(Parameter param) throws Exception {
		String hql = "from Goods where state = '1' order by createTime desc ";
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 猜猜你喜欢
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result getSaleNumGoodsList(Parameter param) throws Exception {
		String hql = "from Goods where state = '1' and grounding = '1' and id in (select gp.goods.id from GoodsParam gp where gp.state='1') "+param.getOrderBy();
		Page page = hibernateUtil.hqlPage(null,hql, param.getPageNum(), 5);
		return ObjectToResult.getResult(page);
	}
	
}

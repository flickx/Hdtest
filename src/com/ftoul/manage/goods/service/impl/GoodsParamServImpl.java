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
import com.ftoul.manage.goods.service.GoodsParamServ;
import com.ftoul.manage.goods.vo.GoodsListVo;
import com.ftoul.manage.goods.vo.GoodsParamVo;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.GoodsProp;
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
@Service("GoodsParamImpl")
public class GoodsParamServImpl implements GoodsParamServ {

	@Autowired
	private HibernateUtil hibernateUtil;

	/**
	 * 
	 * 保存商品第三步
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result saveGoodsParam(Parameter param) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper(); 
		Object res;
		GoodsParamVo goodsParamVo = (GoodsParamVo) objectMapper.readValue(param.getObj()+"", GoodsParamVo.class);
		GoodsParam goodsParam = new GoodsParam();
		Goods goods = (Goods) this.hibernateUtil.find(Goods.class, goodsParamVo.getGoodsId()+"");
		String hql ="from GoodsParam where state=1 and goods.id = '"+ goods.getId()+"'";
		List<Object> list=  this.hibernateUtil.hql(hql);
		//添加
		if (goodsParamVo.getId()==null){
			//如果添加的是第一个商品参数，则goods表中的price价格为当前的销售价格
			if(list.size()<=0){
				goods.setPrice(Double.valueOf(goodsParamVo.getPrice()));
				//设置添加的第一个为默认
				goodsParam.setDefalut("1");
				goods.setSaleSum(Integer.parseInt(goodsParamVo.getSaleNumber()));
			}
			else{
				goodsParam.setDefalut("0");
				int sum=0;
				for(Object gp :list){
					GoodsParam goodsP = (GoodsParam) gp;
					sum+=goodsP.getSaleNumber();
				}
				sum+=Integer.parseInt(goodsParamVo.getSaleNumber());
				goods.setSaleSum(sum);
			}
			goodsParam.setGoods(goods);
			goodsParam.setBatchprice(goodsParamVo.getBatchprice());
			goodsParam.setCostprice(goodsParamVo.getCostprice());
			goodsParam.setParamName(goodsParamVo.getParamName());
			goodsParam.setPrice(goodsParamVo.getPrice());
			goodsParam.setStock(goodsParamVo.getStock());
			
			goodsParam.setSaleNumber(Integer.parseInt(goodsParamVo.getSaleNumber()));
			goodsParam.setMarketPrice(goodsParamVo.getMarketPrice());
			goodsParam.setCreateTime(new DateStr().toString());
			goodsParam.setState("1");
			goods.setState("1");
			goods.setStep("3");
			goods.setHasstock("1");
			res =this.hibernateUtil.save(goodsParam);
		}
		else{
				int sum=0;
				for(Object gp :list){
						GoodsParam goodsP = (GoodsParam) gp;
						if(!goodsP.getId().equals(goodsParamVo.getId()))
					       sum+=goodsP.getSaleNumber();
				}
				sum+=Integer.parseInt(goodsParamVo.getSaleNumber());
				goods.setSaleSum(sum);
				goodsParam.setGoods(goods);
				goodsParam.setBatchprice(goodsParamVo.getBatchprice());
				goodsParam.setCostprice(goodsParamVo.getCostprice());
				goodsParam.setParamName(goodsParamVo.getParamName());
				goodsParam.setPrice(goodsParamVo.getPrice());
				goodsParam.setStock(goodsParamVo.getStock());
				goodsParam.setSaleNumber(Integer.parseInt(goodsParamVo.getSaleNumber()));
				goodsParam.setMarketPrice(goodsParamVo.getMarketPrice());
				goodsParam.setCreateTime(new DateStr().toString());
				goodsParam.setState("1");
				goodsParam.setId(goodsParamVo.getId());
				//如果是第一个则修改goods中的price
				Object goodsPar = this.hibernateUtil.find(GoodsParam.class, goodsParamVo.getId()+"");
				GoodsParam gpa = (GoodsParam) goodsPar;
				goodsParam.setDefalut(gpa.getDefalut());
				if(gpa.getDefalut().equals("1")){
					goods.setPrice(Double.valueOf(goodsParamVo.getPrice()));
					this.hibernateUtil.update(goods);
				}
				res =this.hibernateUtil.update(goodsParam);
		}
		return ObjectToResult.getResult(res);
	}

	
	/**
	 * 
	 * 删除商品参数
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result delGoodsParam(Parameter param) throws Exception {
		GoodsParam goodParam= (GoodsParam) hibernateUtil.find(GoodsParam.class, param.getId()+"");
		Object res;
		goodParam.setState("0");
		res = hibernateUtil.update(goodParam); 
		return ObjectToResult.getResult(res);
	}
	

	/**
	 * 
	 * 编辑商品参数
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result editGoodsParam(Parameter param) throws Exception {
		GoodsParam goodsParam = (GoodsParam) JSONObject.toBean((JSONObject) param.getObj(),GoodsParam.class);
		Object res;
		if(Common.isNull(goodsParam.getId())){
			goodsParam.setCreateTime(new DateStr().toString());
			res = hibernateUtil.save(goodsParam);
		}else{
			GoodsParam gs = (GoodsParam)this.getGoodsParamByGoodsId(goodsParam.getId());
			gs.setPrice(goodsParam.getPrice());
			gs.setStock(goodsParam.getStock());
			gs.setSaleNumber(goodsParam.getSaleNumber());
			goodsParam.setModifyTime(new DateStr().toString());
			res = hibernateUtil.update(gs);
		}
		return ObjectToResult.getResult(res);
	}


	@Override
	public Result getGoodsParam(Parameter param) throws Exception {
		String hql1 = "from GoodsParam where state=1  and goods.id='"+param.getId()+"'" ;
		List<Object> params =this.hibernateUtil.hql(hql1);
		if(params.size()<=0){
			Result res = new Result();
			return res;
		}
		String hql2 =	"select t1.id,t2.title,t3.name as name1,t4.name as name2,t2.grounding,t1.price,t1.stock,t1.sale_number,t1.param_name,t1.defalut,t1.market_price,t1.batchprice,t1.costprice" + 
						" from goods_param t1 LEFT JOIN goods t2 ON t1.goods_id = t2.id and t1.state = '1' and t2.state = '1' \n" +
						"LEFT JOIN goods_type t3 ON t3.id = t2.goods_type3 and t3.state = '1'  and t2.state = '1' \n" +
						"LEFT JOIN goods_brand  t4 ON t4.id= t2.goods_brand_id and t4.state='1' and t2.state='1'\n" +
						"where t1.state = '1' and t1.goods_id = '"+param.getId()+"' " ;
		String countSql = "select count(*) from ("+hql2+") countS";
		Page page = hibernateUtil.sqlPage(countSql,hql2,param.getPageNum(),param.getPageSize());
		List<Object[]> goodsList =hibernateUtil.sql(hql2);
		List<GoodsListVo> list = new ArrayList<GoodsListVo>();
		for (int i = 0; i < page.getObjList().size(); i++) {
			GoodsListVo goodsListVo = new GoodsListVo();
			Object[] obj = (Object[])goodsList.get(i);
			goodsListVo.setId(obj[0].toString());
			goodsListVo.setTitle(obj[1].toString());
			goodsListVo.setTypeName(obj[2].toString());
			goodsListVo.setBrandName(obj[3].toString());
			goodsListVo.setGrounding(obj[4].toString());
			goodsListVo.setPrice(obj[5].toString());
			goodsListVo.setStock(obj[6].toString());
			goodsListVo.setSaleNumber(obj[7].toString());
			goodsListVo.setParamName(obj[8].toString());
			goodsListVo.setDefalut(obj[9].toString());
			goodsListVo.setMarketPrice(obj[10].toString());
			goodsListVo.setBatchprice(obj[11].toString());
			goodsListVo.setCostprice(obj[12].toString());
			list.add(goodsListVo);
		}
		//更新所有的商品属性
		String hql = "from GoodsProp where goods.id = '"+param.getId()+"'";
		List<Object> goodsPropList = this.hibernateUtil.hql(hql);
		if(goodsPropList!=null&&goodsPropList.size()>0){
			for(Object gp:goodsPropList){
				GoodsProp goodsProp  = (GoodsProp) gp;
				goodsProp.setState("1");
				this.hibernateUtil.save(goodsProp);
			}
		}
		page.setVoList(list);
		return ObjectToResult.getVoResult(page);
	}

	@Override
	public GoodsParam getGoodsParamByGoodsId(String goodsId) throws Exception {
		String hql = "from GoodsParam where goods.id='"+goodsId+"'";
		GoodsParam gp = (GoodsParam)hibernateUtil.hqlFirst(hql);
		return gp;
	}


	@Override
	public Result getGoodsParamList(Parameter param) throws Exception {
		String hql  = "from GoodsParam where state=1 and goods.id ='"+param.getId()+"'";
		Page page = this.hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}


	/**
	 * 保存商品
	 */
	@Override
	public Result saveGoodsParamByGoods(Parameter param) throws Exception {
		GoodsParam goodsParam = (GoodsParam) JSONObject.toBean((JSONObject) param.getObj(),GoodsParam.class);
		Goods goods = (Goods) this.hibernateUtil.find(Goods.class, param.getParentId()+"");
		String hql ="from GoodsParam where state=1 and goods.id = '"+ goods.getId()+"'";
		List<Object> list=  this.hibernateUtil.hql(hql);
		if(Common.isNull(goodsParam.getId())){
			//添加
			if(list.size()<=0){
				goods.setPrice(Double.valueOf(goodsParam.getPrice()));
				//设置添加的第一个为默认
				goodsParam.setDefalut("1");
				goods.setSaleSum(goodsParam.getSaleNumber());
			}
			else{
				goodsParam.setDefalut("0");
				int sum=0;
				for(Object gp :list){
					GoodsParam goodsP = (GoodsParam) gp;
					sum+=goodsP.getSaleNumber();
				}
				sum+=goodsParam.getSaleNumber();
				goods.setSaleSum(sum);
			}
				goodsParam.setStock("0");
				goodsParam.setModifyPerson(param.getUserId());
				goodsParam.setState("1");
				goodsParam.setCreateTime(new DateStr().toString());
				goodsParam.setGoods(goods);
				this.hibernateUtil.save(goodsParam);
				this.hibernateUtil.update(goods);
		}
		else {
			//查找goodsParam
			GoodsParam gparam = (GoodsParam) this.hibernateUtil.find(GoodsParam.class, goodsParam.getId()+"");
			int sum=0;
			for(Object gp :list){
					GoodsParam goodsP = (GoodsParam) gp;
					if(!goodsP.getId().equals(goodsParam.getId()))
				       sum+=goodsP.getSaleNumber();
			}
			
			sum+=goodsParam.getSaleNumber();
			
			goodsParam.setState("1");
			if(gparam.getCreateTime()!=null)
				goodsParam.setCreateTime(gparam.getCreateTime());
			goodsParam.setModifyTime(new DateStr().toString());
			goodsParam.setModifyPerson(param.getUserId());
			goodsParam.setGoods(goods);
			goodsParam.setStock(gparam.getStock());
			goodsParam.setDefalut(gparam.getDefalut());
			goodsParam.setSaleNumber(gparam.getSaleNumber());
			//如果是第一个则修改goods中的price
			if(gparam.getDefalut().equals("1")){
				goods.setPrice(Double.valueOf(goodsParam.getPrice()));
				this.hibernateUtil.update(goods);
			}
			this.hibernateUtil.update(goodsParam);
		}
		return ObjectToResult.getResult(goodsParam);
	}


	@Override
	public Result saveStock(Parameter param) throws Exception {
		GoodsParam goodsParam = (GoodsParam) this.hibernateUtil.find(GoodsParam.class, param.getId()+"");
		String stockStr = goodsParam.getStock();
		if(stockStr==""||stockStr==null){
			stockStr="0";
		}
		int stock = Integer.parseInt(stockStr);
		String amountStr = param.getKey();
		int amount = Integer.parseInt(amountStr);
		int ret  =amount+stock;
		if(ret<0){
			throw new Exception("库存数不能小于0");
		}
		goodsParam.setStock(new Integer (ret).toString());
		Object res;
		res =this.hibernateUtil.update(goodsParam);
		Goods goods = (Goods)this.hibernateUtil.find(Goods.class, goodsParam.getGoods().getId()+"");
		//只要入库了，我们设置商品库存为1
		goods.setHasstock("1");
		this.hibernateUtil.update(goods);
		//加入入库单
		GoodsPurchase goodsPurchase = new GoodsPurchase();
		goodsPurchase.setGoodsParam(goodsParam);
		goodsPurchase.setNum(amountStr);
		this.hibernateUtil.save(goodsPurchase);
		return ObjectToResult.getResult(res);
	}


	/**
	 *	设置商品参数为默认
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result saveDefault(Parameter param) throws Exception {
		GoodsParam  goodsParam =(GoodsParam) this.hibernateUtil.find(GoodsParam.class, param.getId()+"");
		String goodsId = goodsParam.getGoods().getId();
		String hql ="from GoodsParam where state=1 and goods.id='"+goodsId+"'";
		Object res = new Object();
		List<Object> goodsParamList = this.hibernateUtil.hql(hql);
		for(Object obj :goodsParamList){
			GoodsParam gp = (GoodsParam) obj;
			if(gp.getId().equals(goodsParam.getId())){
				goodsParam.setDefalut("1");
				res=this.hibernateUtil.update(goodsParam);
			}
			else{
				gp.setDefalut("0");
				res=this.hibernateUtil.update(gp);
			}
		}
		return ObjectToResult.getResult(res);
	}
}

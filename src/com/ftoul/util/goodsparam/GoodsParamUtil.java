package com.ftoul.util.goodsparam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsParam;
import com.ftoul.po.GoodsType;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.goods.service.GoodsParamServ;
import com.ftoul.web.vo.ShopGoodsParamVo;

@Component
public class GoodsParamUtil {
	
	@Autowired
	GoodsParamServ goodsParamServ;
	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 
	 * 对接订单接口，对应的减少库存 
	 * @param   id 商品参数id，amount购买数量
	 * @return  返回结果（前台用Result对象）
	 */
	
	public Result saveStockFromOrder(String id,String amount) throws Exception {
		Parameter parameter = new Parameter();
		parameter.setId(id);
		parameter.setKey(amount);
		return goodsParamServ.saveStockFromOrder(parameter);
	}
	
	/**
	 * 查询该商品参数的第三级分类
	 */
	public String getGoodsTypeByGoodsParamId(String param){
		GoodsParam goodsParam = (GoodsParam) hibernateUtil.find(GoodsParam.class, param);
		GoodsType type3 = null;
		if(goodsParam!=null){
			Goods goods = goodsParam.getGoods();
			type3 = goods.getGoodsType3();
		}
		return type3.getName();
	}
	
	/**
	 * 通过分类及分类级别查询此级别以下的所有三级分类
	 * @param type
	 * @param level
	 */
	public List<String> getThirdType(String type,String level){
		List<String> types = new ArrayList<String>();
		if("1".equals(level)){
			List<Object> objList = hibernateUtil.hql("from GoodsType where state='1' and pid='"+type+"' and level='2'");
			for (Object object : objList) {
				GoodsType goodsType = (GoodsType) object;
				List<Object> objs = hibernateUtil.hql("from GoodsType where state='1' and pid='"+goodsType.getId()+"' and level='3'");
				for (Object object2 : objs) {
					GoodsType goodsType2 = (GoodsType) object2;
					types.add(goodsType2.getId());
				}
			}
		}else if("2".equals(level)){
			List<Object> objs = hibernateUtil.hql("from GoodsType where state='1' and pid='"+type+"' and level='3'");
			for (Object object : objs) {
				GoodsType goodsType = (GoodsType) object;
				types.add(goodsType.getId());
			}
		}
		return types;
	}
	
	public Map<String, List<ShopGoodsParamVo>> getShopAndGoodsParam(String param){
		Map<String, List<ShopGoodsParamVo>> group = new HashMap<String, List<ShopGoodsParamVo>>();
		List<ShopGoodsParamVo> goodsParamVo = null;
		String[] strList = param.split(":");
		List<ShopGoodsParamVo> voList = new ArrayList<ShopGoodsParamVo>();
		for (int i = 0; i < strList.length; i++) {
			String[] str = strList[i].split(",");
			ShopGoodsParamVo vo = new ShopGoodsParamVo();
			vo.setGoodsParamId(str[0]);
			vo.setNum(str[1]);
			vo.setPrice(str[2]);
			if(str.length==4){
				vo.setShopId("1");
			}else{
				vo.setShopId(str[4]);
			}
			
			voList.add(vo);
		}
		
		for (int j = 0; j < voList.size(); j++) {
			ShopGoodsParamVo vo = voList.get(j);
			String shopId = vo.getShopId();
			if(shopId!=null){
				goodsParamVo = group.get(shopId);
				if(goodsParamVo == null){
					List<ShopGoodsParamVo> newVoList = new ArrayList<ShopGoodsParamVo>();
					newVoList.add(vo);
					group.put(shopId, newVoList);
				}else{
					goodsParamVo.add(vo);
					group.put(shopId, goodsParamVo);
				}
			}
		}
		
		return group;
	}
	
}

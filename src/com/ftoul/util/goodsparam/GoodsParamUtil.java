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
	 * 查询该商品参数属于哪些分类
	 */
	public List<String> getGoodsTypeByGoodsParamId(String param){
		List<String> list = new ArrayList<String>();
		GoodsParam goodsParam = (GoodsParam) hibernateUtil.find(GoodsParam.class, param);
		if(goodsParam!=null){
			Goods goods = goodsParam.getGoods();
			GoodsType type1 = goods.getGoodsType1();
			GoodsType type2 = goods.getGoodsType2();
			GoodsType type3 = goods.getGoodsType3();
			list.add(type1.getName());
			list.add(type2.getName());
			list.add(type3.getName());
		}
		return list;
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

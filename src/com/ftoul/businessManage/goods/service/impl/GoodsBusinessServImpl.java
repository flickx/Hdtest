package com.ftoul.businessManage.goods.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftoul.businessManage.goods.service.GoodsBusinessServ;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.advert.vo.UploadPicVo;
import com.ftoul.manage.goods.service.GoodsBrandServ;
import com.ftoul.manage.goods.service.GoodsPropTypeServ;
import com.ftoul.manage.goods.service.GoodsTypeServ;
import com.ftoul.manage.goods.vo.GoodsListVo;
import com.ftoul.manage.goods.vo.GoodsTypeSetVo;
import com.ftoul.manage.goods.vo.GoodsVo;
import com.ftoul.po.Goods;
import com.ftoul.po.GoodsBrand;
import com.ftoul.po.GoodsCanal;
import com.ftoul.po.GoodsProp;
import com.ftoul.po.GoodsPropType;
import com.ftoul.po.GoodsPropertyTypeInfo;
import com.ftoul.po.GoodsType;
import com.ftoul.po.GoodsUploadpic;
import com.ftoul.util.hibernate.HibernateUtil;

/**
* 
*
* 类描述：商品业务实现类
* @author: yw
* @date： 日期：2016年8月8日 时间：上午10:01:10
* @version 1.0
*
*/
@Service("GoodsBusinessServImpl")
public class GoodsBusinessServImpl implements GoodsBusinessServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private GoodsTypeServ goodsTypeServ;
	@Autowired
	private GoodsBrandServ goodsBrandServ;
	@Autowired
	private GoodsPropTypeServ goodsPropTypeServ;
	
	/**
	 * 保存/更新商品对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveGoods(Parameter param) throws Exception {
		Goods goods = (Goods) JSONObject.toBean((JSONObject) param.getObj(),Goods.class);
		//获取到该商品最新的信息
		Goods newGoods = (Goods)hibernateUtil.find(Goods.class, goods.getId());
		goods.setPicSrc(newGoods.getPicSrc());
		//更新商品主图
//		List<UploadPicVo> picMainVos = param.getUploadPicMainVoList();
//		if (picMainVos!=null && picMainVos.size()>0) {			
//			GoodsUploadpic goodsUploadpic = (GoodsUploadpic)hibernateUtil.hqlFirst("from GoodsUploadpic where state='1' and picType='0' and goods.id ='"+goods.getId()+"'");				
//			if (goodsUploadpic!=null) {
//				goodsUploadpic.setPicSrc(picMainVos.get(0).getPicAddress());
//				goodsUploadpic.setThumbnailSrc(picMainVos.get(0).getThumbnailSrc());
//				hibernateUtil.update(goodsUploadpic);
//			}else{
//				GoodsUploadpic g = new GoodsUploadpic();
//				g.setGoods(goods);
//				g.setPicSrc(picMainVos.get(0).getPicAddress());
//				g.setPicType("0");
//				g.setCreateTime(new DateStr().toString());
//				g.setCreatePerson(param.getManageToken().getLoginUser().getLoginName());
//				g.setState("1");
//				hibernateUtil.save(g);
//			}
//			goods.setPicSrc(picMainVos.get(0).getThumbnailSrc());
//		}
		//上传商品图片
//		List<UploadPicVo> picVos = param.getUploadPicVoList();
//		if (picVos!=null && picVos.size()>0) {
//			for(UploadPicVo uploadPicVo : picVos){
//			if (uploadPicVo.getHasUpload()) {
//				GoodsUploadpic goodsUploadpic =  new GoodsUploadpic();
//				goodsUploadpic.setGoods(goods);
//				goodsUploadpic.setPicSrc(uploadPicVo.getPicAddress());
//				goodsUploadpic.setPicType("1");
//				goodsUploadpic.setCreateTime(new DateStr().toString());
//				goodsUploadpic.setCreatePerson(param.getManageToken().getLoginUser().getLoginName());
//				goodsUploadpic.setState("1");
//				hibernateUtil.save(goodsUploadpic);
//			}
//		}
//		}
		//上传商品图片详情
//		List<UploadPicVo> picInfoVos = param.getUploadPicInfoVoList();
//		if (picInfoVos!=null && picInfoVos.size()>0) {
//			for(UploadPicVo uploadPicVo : picInfoVos){
//			if (uploadPicVo.getHasUpload()) {
//				GoodsUploadpic goodsUploadpic =  new GoodsUploadpic();
//				goodsUploadpic.setGoods(goods);
//				goodsUploadpic.setPicSrc(uploadPicVo.getPicAddress());
//				goodsUploadpic.setPicType("2");
//				goodsUploadpic.setCreateTime(new DateStr().toString());
//				goodsUploadpic.setCreatePerson(param.getManageToken().getLoginUser().getLoginName());
//				goodsUploadpic.setState("1");
//				hibernateUtil.save(goodsUploadpic);
//			}
//		}		
//		}
		Object res;
		if(goods.getId()!=null)
			res =this.hibernateUtil.update(goods);
		else 
			res =this.hibernateUtil.save(goods);
		return ObjectToResult.getResult(res);
	}

	/**
	 * 通过商品分类查找商品
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsList(Parameter param) {
		if(param.getId()!=null){
			GoodsType goodsType =(GoodsType) hibernateUtil.find(GoodsType.class, param.getId()+"");
			if(goodsType!=null){
				String hql1 ="from Goods where goodsType.id = '"+goodsType.getId() +"'";
				List<Object> goodsList = hibernateUtil.hql(hql1);
				return ObjectToResult.getResult(goodsList);
			}
		}
		String hql2 = "from Goods";
		List<Object> allGoodsList = hibernateUtil.hql(hql2);
		return ObjectToResult.getResult(allGoodsList);
	}

	@Override
	public Result saveGoodsFisrtStep(Parameter param) {
		Goods goods = new Goods();
		goods.setStep("1");
		goods.setCreatePerson(new DateStr().toString());
		goods.setState("0");
		Object res ;
		res = this.hibernateUtil.save(goods);
		return ObjectToResult.getResult(res);
	}
	
	/**
	 * 
	 * 保存商品第二步
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */

	@Override
	public Result saveGoodsSecondStep(Parameter param) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper(); 
		GoodsVo goodsVo = (GoodsVo) objectMapper.readValue(param.getObj()+"", GoodsVo.class);
		Goods  goods = new Goods();
		goods.setId(goodsVo.getId());
		goods.setTitle(goodsVo.getTitle());
		goods.setSubtitle(goodsVo.getSubTitle());
		goods.setGoodsLabel(goodsVo.getGoodsLabel());
		if(goodsVo.getGoodsType1()!=null){
			GoodsType goodsType1 =	(GoodsType) this.hibernateUtil.find(GoodsType.class, goodsVo.getGoodsType1());
			goods.setGoodsType1(goodsType1);
		}
		if(goodsVo.getGoodsType2()!=null){
			GoodsType goodsType2 =	(GoodsType) this.hibernateUtil.find(GoodsType.class, goodsVo.getGoodsType2());
			goods.setGoodsType2(goodsType2);
		}
		if(goodsVo.getGoodsType3()!=null){
			GoodsType goodsType3 =	(GoodsType) this.hibernateUtil.find(GoodsType.class, goodsVo.getGoodsType3());
			goods.setGoodsType3(goodsType3);
		}
		if(goodsVo.getGoodsBrandId()!=null){
			GoodsBrand goodsBrand = (GoodsBrand) this.hibernateUtil.find(GoodsBrand.class, goodsVo.getGoodsBrandId());
			goods.setGoodsBrand(goodsBrand);
		}
		if(goodsVo.getBusinessClassifyId()!=null){
			goods.setBusinessClassifyId(goodsVo.getBusinessClassifyId());
		}
		if(goodsVo.getGoodsPropTypeId()!=null){
			GoodsPropType goodsPropType = (GoodsPropType) this.hibernateUtil.find(GoodsPropType.class,goodsVo.getGoodsPropTypeId());
			goods.setGoodsPropType(goodsPropType);
		}
		if(goodsVo.getPrice()!=null){
			goods.setPrice(Double.valueOf(goodsVo.getPrice()));
		}
		if(goodsVo.getGrounding()!=null){
			goods.setGrounding(goodsVo.getGrounding());
		}
		if(goodsVo.getCrossborder()!=null)
			goods.setCrossborder(goodsVo.getCrossborder());
		if(goodsVo.getSkuCode()!=null)
			goods.setSkuCode(goodsVo.getSkuCode());
		if(goodsVo.getDeductionrate()!=null)
			goods.setDeductionrate(goodsVo.getDeductionrate());
		if(goodsVo.getGoodscanalId()!=null){
			GoodsCanal goodsCanal =(GoodsCanal) this.hibernateUtil.find(GoodsCanal.class, goodsVo.getGoodscanalId()+"");
			if(goodsCanal!=null)
				goods.setGoodsCanal(goodsCanal);
		}
//		//加入shop  先加入id为1的shop
//		  BusinessStore businessStore=	(BusinessStore) this.hibernateUtil.find(Shop.class, "1");
//		  if(businessStore!=null)
		
		goods.setShopId(param.getManageToken().getBusinessStoreLogin().getBusinessStore().getId());
		//定时上架
		  if(goodsVo.getGrounding().equals("2")){
			  goods.setGroundingTime(goodsVo.getGroundingTime());
		  }
		
		//物流模板稍后在加
		if(goodsVo.getPcInfo()!=null){
			goods.setPcInfo(goods.getPcInfo());
		}
		if(goodsVo.getMobilInfo()!=null){
			goods.setMobilInfo(goodsVo.getMobilInfo());
		}
		//设置商品主图	
		List<UploadPicVo> uploadPicMainList = goodsVo.getUploadPicMainList();
		if(uploadPicMainList.get(0).getHasUpload()){
			goods.setPicSrc(uploadPicMainList.get(0).getPicAddress());
			GoodsUploadpic goodsUploadpic = new GoodsUploadpic();
			goodsUploadpic.setGoods(goods);
			goodsUploadpic.setPicSrc(uploadPicMainList.get(0).getPicAddress());
			goodsUploadpic.setThumbnailSrc(uploadPicMainList.get(0).getThumbnailSrc());
			goodsUploadpic.setPicType("0");
			goodsUploadpic.setCreateTime(new DateStr().toString());
			goodsUploadpic.setCreatePerson(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
			goodsUploadpic.setState("1");
			hibernateUtil.save(goodsUploadpic);
		}
		//保存商品图片
		List<UploadPicVo> uploadPicList = goodsVo.getUploadPicList();
		if (uploadPicList != null && uploadPicList.size()>0) {			
			for(UploadPicVo uploadPicVo:uploadPicList){
				GoodsUploadpic goodsUploadpic = new GoodsUploadpic();
				if(uploadPicVo.getHasUpload()){
					goodsUploadpic.setGoods(goods);
					goodsUploadpic.setPicSrc(uploadPicVo.getPicAddress());
					goodsUploadpic.setPicType("1");
					goodsUploadpic.setCreateTime(new DateStr().toString());
					goodsUploadpic.setCreatePerson(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
					goodsUploadpic.setState("1");
					hibernateUtil.save(goodsUploadpic);
				}
			}
		
		}
		//保存商品详情图片
		List<UploadPicVo> uploadPicInfoList = goodsVo.getUploadPicInfoList();
			if (uploadPicInfoList != null && uploadPicInfoList.size()>0) {		
			for(UploadPicVo uploadPicVo:uploadPicInfoList){
				GoodsUploadpic goodsUploadpic = new GoodsUploadpic();
				if(uploadPicVo.getHasUpload()){
					goodsUploadpic.setGoods(goods);
					goodsUploadpic.setPicSrc(uploadPicVo.getPicAddress());
					goodsUploadpic.setPicType("2");
					goodsUploadpic.setCreateTime(new DateStr().toString());
					goodsUploadpic.setCreatePerson(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
					goodsUploadpic.setState("1");
					hibernateUtil.save(goodsUploadpic);
				}
			}
		}
		goods.setState("0");
		goods.setStep("2");
		//更新goods
		goods.setCreateTime(new DateStr().toString());
		String goodsLabel="";
		if(null!= goodsVo.getGoodsLabel()){
			String[] label = goodsVo.getGoodsLabel().split(",");
			for (int i = 0; i < label.length; i++) {
				if("1".equals(label[i])){
					goodsLabel+="超值";
				}else if("2".equals(label[i])){
					goodsLabel+="促销";
				}else if("3".equals(label[i])){
					goodsLabel+="特惠";
				}else if("4".equals(label[i])){
					goodsLabel+="清仓";
				}else if("5".equals(label[i])){
					goodsLabel+="热销";
				}else if("6".equals(label[i])){
					goodsLabel+="大促";
				}
				if(label.length!=1 && i!=label.length-1){
					goodsLabel+=",";
				}
			}
		}
		goods.setGoodsLabel(goodsLabel);
		Object obj = hibernateUtil.update(goods);
		
		List<GoodsProp> goodsPropList=	goodsVo.getGoodsPropList();
		for(GoodsProp goodsProp:goodsPropList){
			GoodsProp gp = new GoodsProp();
			if(goodsProp.getId()!=null&&goodsProp.getContent()!=""){
				GoodsPropertyTypeInfo goodsPropertyTypeInfo =	(GoodsPropertyTypeInfo) this.hibernateUtil.find(GoodsPropertyTypeInfo.class, goodsProp.getId()+"");
				gp.setGoodsPropertyTypeInfo(goodsPropertyTypeInfo);
				gp.setGoods(goods);
				gp.setContent(goodsProp.getContent());
				gp.setState("1");
				gp.setCreateTime(new DateStr().toString());
				this.hibernateUtil.save(gp);
			}
		}
		return ObjectToResult.getResult(obj);
	}

	@Override
	public Result getGoodsVoList(Parameter parameter) throws Exception {
		
		String whereStr = parameter.getWhereStr();
		if(whereStr!=null){
			if(whereStr.contains("gs.goodsType.id"))
				whereStr = whereStr.replace("gs.goodsType.id", "gs.goods_Type1");
			if(whereStr.contains("gs.goodsType2Id"))
				whereStr = whereStr.replace("gs.goodsType2Id", "gs.goods_Type2");
			if(whereStr.contains("gs.goodsType3Id"))
				whereStr = whereStr.replace("gs.goodsType3Id", "gs.goods_Type3");
			if(whereStr.contains("brandName"))
				whereStr = whereStr.replace("brandName", "gb.name");
			if(whereStr.contains("typeName"))
				whereStr = whereStr.replace("typeName", "gt.name");
			if(whereStr.contains("title"))
				whereStr = whereStr.replace("title", "gs.title");
			if(whereStr.contains("goodsPropType"))
				whereStr = whereStr.replace("goodsPropType", "gpt.name");
			parameter.setWhereStr(whereStr);
		}else{
			parameter.setWhereStr("");
		}
			

		//String hql = "select gs.id,gs.title,gt.name,gb.name,gs.grounding,gp.price,gp.stock,gp.saleNumber,gpt.name "
		//		+ "from Goods gs,GoodsParam gp,GoodsType gt,GoodsBrand gb, GoodsPropType gpt "
		//		+ "where gs.id = gp.goods.id and gs.state=1 and gs.goodsType3.id = gt.id and gs.goodsBrand.id = gb.id and gs.goodsPropType= gpt.id" +parameter.getWhereStr() + " group by gs.id";
		String sql ="SELECT  " +
				"	gs.id,  " +
				"	gs.title,  " +
				"	gt.NAME AS gtName,  " +
				"	gb.NAME AS gbName,  " +
				"	gs.grounding,  " +
				"	gpt.NAME AS gptName,  " +
				"	gs.subtitle  " +
				"FROM  " +
				"	Goods gs  " +
				"JOIN Goods_Param gp ON gs.id = gp.goods_id  " +
				"AND gs.state = '1'  " +
				"AND gp.state = '1'  " +
				"LEFT JOIN Goods_Type gt ON gs.goods_type3 = gt.id  " +
				"AND gt.state = '1'  " +	
				"AND gs.state = '1'  " +
				"LEFT JOIN Goods_Brand gb ON gs.goods_brand_id = gb.id  " +
				"AND gb.state = '1'  " +
				"AND gs.state = '1'  " +
				"JOIN Goods_Prop_Type gpt ON gs.goods_prop_type_id = gpt.id  " +
				"AND gpt.state = '1'  " +
				"AND gs.state = '1'  " +
				"AND gs.shop_id = '"+parameter.getManageToken().getBusinessStoreLogin().getBusinessStore().getId()+"' " +parameter.getWhereStr()+
				
				"GROUP BY  " +
				"	gs.id  ";
		


		//判断表格每列，并按列进行排序
		if(parameter.getSidx()!=null){
			if(parameter.getSidx().equals("createTime")){
				sql+=" order by gs.create_time "+parameter.getSord();
			}
			if(parameter.getSidx().equals("title")){
				sql+=" order by gs.title "+parameter.getSord();
			}
			if(parameter.getSidx().equals("typeName")){
				sql+=" order by gt.name "+parameter.getSord();
			}
			if(parameter.getSidx().equals("brandName")){
				sql+=" order by gb.name "+parameter.getSord();
			}
			if(parameter.getSidx().equals("goodsPropType")){
				sql+=" order by gpt.name "+parameter.getSord();
			}
			if(parameter.getSidx().equals("grounding")){
				sql+=" order by gs.grounding "+parameter.getSord();
			}
		}
		Page page = hibernateUtil.sqlPage(sql,parameter.getPageNum(),parameter.getPageSize());
		List<GoodsListVo> list = new ArrayList<GoodsListVo>();
		for (int i = 0; i < page.getObjList().size(); i++) {
			GoodsListVo goodsListVo = new GoodsListVo();
			Object[] obj = (Object[])page.getObjList().get(i);
			if(obj[0]!=null)
			  goodsListVo.setId(obj[0].toString());
			if(obj[1]!=null)
			  goodsListVo.setTitle(obj[1].toString());
			if(obj[2]!=null)
			  goodsListVo.setTypeName(obj[2].toString());
			if(obj[3]!=null)
			  goodsListVo.setBrandName(obj[3].toString());
			if(obj[4]!=null)
			   goodsListVo.setGrounding(obj[4].toString());
			if(obj[5]!=null)
				goodsListVo.setGoodsPropType(obj[5].toString());
			if(obj[6]!=null)
				goodsListVo.setSubtitle(obj[6].toString());
			list.add(goodsListVo);
		}
		page.setVoList(list);
		return ObjectToResult.getVoResult(page);
	}

	@Override
	public Result updateGoods(Parameter param,String id,String grounding) throws Exception {
		Goods gs =	(Goods) hibernateUtil.find(Goods.class, id);
		gs.setGrounding(grounding);
		return ObjectToResult.getResult(gs);
	}

	@Override
	public Result getGoodsListByEarlyWarning(Parameter param)throws Exception {
		String hql = "select gs.id,gs.title,gt.name,gb.name,gs.grounding,gp.price,gp.stock,gp.saleNumber "
				+ "from Goods gs,GoodsParam gp,GoodsType gt,GoodsBrand gb "
				+ "where gs.id = gp.goods.id  and gs.state=1 and gs.goodsType3.id = gt.id and gs.goodsBrand.id = gb.id and gp.stock<="+param.getId()+  " group by gs.id";
		//判断表格每列，并按列进行排序
		if(param.getSidx().equals("title")){
			hql+=" order by gs.title "+param.getSord();
		}
		if(param.getSidx().equals("typeName")){
			hql+=" order by gt.name "+param.getSord();
		}
		if(param.getSidx().equals("brandName")){
			hql+=" order by gb.name "+param.getSord();
		}
		if(param.getSidx().equals("grounding")){
			hql+=" order by gs.grounding "+param.getSord();
		}
		if(param.getSidx().equals("price")){
			hql+=" order by gp.price "+param.getSord();
		}
		if(param.getSidx().equals("stock")){
			hql+=" order by gp.stock "+param.getSord();
		}
		if(param.getSidx().equals("saleNumber")){
			hql+=" order by gp.saleNumber "+param.getSord();
		}
		Page page = hibernateUtil.hqlPage(hql,param.getPageNum(),param.getPageSize());
		List<Object> goodsList =hibernateUtil.hql(hql);
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
			list.add(goodsListVo);
		}
		page.setVoList(list);
		return ObjectToResult.getVoResult(page);
	}

	/**
	 * 通过商品id得到商品vo
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result getGoodsVoById(Parameter param) throws Exception {
		String hql = "select gs.id as id,gs.title as title,gt.name  as typeName,gb.name as brandName,gs.grounding as grounding,gp.price as price,gp.stock as stock,gp.saleNumber as saleNumber,gs.pcInfo,gs.mobilInfo "
				+ "from Goods gs,GoodsParam gp,GoodsType gt,GoodsBrand gb "
				+ "where gs.id = gp.goods.id and gs.goodsType3.id = gt.id and gs.state=1 and gs.goodsBrand.id = gb.id and gs.id='"+param.getId()+"'  group by gs.id";
		GoodsListVo goodsListVo = new GoodsListVo();
		Object obj1   =hibernateUtil.hqlFirst(hql);
		Object[] obj = (Object[])obj1;
		goodsListVo.setId(obj[0].toString());
		goodsListVo.setTitle(obj[1].toString());
		goodsListVo.setTypeName(obj[2].toString());
		goodsListVo.setBrandName(obj[3].toString());
		goodsListVo.setGrounding(obj[4].toString());
		goodsListVo.setPrice(obj[5].toString());
		goodsListVo.setStock(obj[6].toString());
		goodsListVo.setSaleNumber(obj[7].toString());
		if(obj[8].toString()!=null)
		goodsListVo.setPcInfo(obj[8].toString());
		if(obj[9].toString()!=null)
		goodsListVo.setMobilInfo(obj[9].toString());
		return ObjectToResult.getResult(goodsListVo);
	}

	@Override
	public Result getGoodsById(Parameter param) throws Exception {
		Goods goods = (Goods) this.hibernateUtil.find(Goods.class, param.getId()+"");
		return ObjectToResult.getResult(goods);
	}
	
	/**
	 * 通过通过商品id查询所对应的goodsType的集合
	 * @param param
	 * @return
	 * @throws Exception
	 */

	@Override
	public Result getGoodsTypeSetByGoodsId(Parameter param)
			throws Exception {
		Goods goods = (Goods) this.hibernateUtil.find(Goods.class, param.getId()+"");
		return ObjectToResult.getResult(goods);
	}

	@Override
	public Result delGoods(Parameter param) {
		Integer num = hibernateUtil.execHql("update Goods set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}
	
	/**
	 * 查找跨境商品（带分页）
	 * @param param
	 * @return
	 * @throws Exception
	 */

	@Override
	public Result getGoodsListPageByCross(Parameter param) throws Exception {
		String hql  ="from Goods where state ='1' and crossborder='1' ";
		Page page =this.hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
}

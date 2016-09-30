package com.ftoul.manage.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.goods.service.GoodsPropertyTypeInfoServ;
import com.ftoul.manage.goods.vo.GoodsBrandInfoVo;
import com.ftoul.po.GoodsPropType;
import com.ftoul.po.GoodsPropertyTypeInfo;
import com.ftoul.util.hibernate.HibernateUtil;

/**
 * 
*
* 类描述：商品属性详情对象
* @author: yw
* @date： 日期：2016年7月26日 时间：下午4:39:39
* @version 1.0
*
 */
@Service("GoodsPropertyTypeInfoServImpl")
public class GoodsPropertyTypeInfoServImpl implements GoodsPropertyTypeInfoServ {

	@Autowired
	private HibernateUtil hibernateUtil;

	
	/**
	 * 保存商品属性
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveGoodsPropertyTypeInfo(Parameter param) throws Exception {
		GoodsPropertyTypeInfo goodsPropertyTypeInfo = (GoodsPropertyTypeInfo) JSONObject.toBean((JSONObject) param.getObj(),GoodsPropertyTypeInfo.class);
		
		Object res = "";
		GoodsPropType goodsPropType = new GoodsPropType();
		goodsPropType.setId((String)param.getParentId());
		goodsPropertyTypeInfo.setGoodsPropType(goodsPropType);
		goodsPropertyTypeInfo.setCreateTime(new DateStr().toString());
		goodsPropertyTypeInfo.setState("1");
		if("0".equals(goodsPropertyTypeInfo.getTextOrSelect())){
			String content = "";
			String cont = goodsPropertyTypeInfo.getContent();
			String[] contentArray = cont.split(",");
			String str = "";
			for (int i = 0; i < contentArray.length; i++) {
				if(i!=contentArray.length-1){
					str +="{\"text\":"+"\""+contentArray[i]+"\","+"\"value\":"+"\""+contentArray[i]+"\"},";
				}else{
					str +="{\"text\":"+"\""+contentArray[i]+"\","+"\"value\":"+"\""+contentArray[i]+"\"}";
				}
			}
			content = "["+str+"]";
			goodsPropertyTypeInfo.setContent(content);
		}
		res = hibernateUtil.save(goodsPropertyTypeInfo);
		return ObjectToResult.getResult(res);
	}
	/**
	 * 更新商品属性
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result updateGoodsPropertyTypeInfo(Parameter param) throws Exception {
		GoodsPropertyTypeInfo goodsPropertyTypeInfo = (GoodsPropertyTypeInfo) JSONObject.toBean((JSONObject) param.getObj(),GoodsPropertyTypeInfo.class);
		//GoodsPropertyTypeInfo info = (GoodsPropertyTypeInfo) hibernateUtil.find(GoodsPropertyTypeInfo.class, goodsPropertyTypeInfo.getId());
		Object res = "";
		/*GoodsPropType goodsPropType = new GoodsPropType();
		GoodsPropertyTypeInfo info = new GoodsPropertyTypeInfo();
		goodsPropType.setId(goodsPropertyTypeInfo.getId());
		goodsPropType.setName(goodsPropertyTypeInfo.getName());
		//goodsPropertyTypeInfo.setGoodsPropType(goodsPropType);
		goodsPropertyTypeInfo.setCreateTime(new DateStr().toString());
		goodsPropertyTypeInfo.setState("1");*/
		String content = "";
		String cont = goodsPropertyTypeInfo.getContent();
		String[] contentArray = cont.split(",");
		String str = "";
		for (int i = 0; i < contentArray.length; i++) {
			if(i!=contentArray.length-1){
				str +="{\"text\":"+"\""+contentArray[i]+"\","+"\"value\":"+"\""+contentArray[i]+"\"},";
			}else{
				str +="{\"text\":"+"\""+contentArray[i]+"\","+"\"value\":"+"\""+contentArray[i]+"\"}";
			}
		}
		content = "["+str+"]";
		goodsPropertyTypeInfo.setContent(content);
		String hql = " update GoodsPropertyTypeInfo set name = '"+goodsPropertyTypeInfo.getName()+"',textOrSelect='"
				+goodsPropertyTypeInfo.getTextOrSelect()+"',content='"+content+"'"+" where id='"+goodsPropertyTypeInfo.getId()+"'";
		System.out.println(hql);
		res = hibernateUtil.execHql(hql);
		return ObjectToResult.getResult(res);
	}
	
	/**
	 * 
	 * 删除商品属性详情对象
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	
	public Result delGoodsPropertyTypeInfo(Parameter param) throws Exception{
		Integer num = hibernateUtil.execHql("update GoodsPropertyTypeInfo set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
		
	}

	
	
	/**
	 * 
	 * 通过大类id得到商品属性详情
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsPropInfoTypeListByTypeId(Parameter param) {
		String hql = "from GoodsPropertyTypeInfo where state = '1' and goodsPropType.id = '" + param.getId()+"'";
		List<Object> goodsPropInfoTypeList = this.hibernateUtil.hql(hql);
		return ObjectToResult.getResult(goodsPropInfoTypeList);
	}


	@Override
	public Result getPropTypeContent(Parameter param) {
		String hql = "from GoodsPropertyTypeInfo where state = '1' and goodsPropType.id = '" + param.getId()+"'";
		Page page = hibernateUtil.hqlPage(hql, param.getPageNum(), param.getPageSize());
		//List<Object> propList = hibernateUtil.hql(hql);
		List<GoodsBrandInfoVo> list = new ArrayList<GoodsBrandInfoVo>();
		for (int i = 0; i < page.getObjList().size(); i++) {
			GoodsPropertyTypeInfo goodsPropertyTypeInfo = new GoodsPropertyTypeInfo();
			goodsPropertyTypeInfo = (GoodsPropertyTypeInfo)page.getObjList().get(i);
			GoodsBrandInfoVo gb = new GoodsBrandInfoVo();
			gb.setId(goodsPropertyTypeInfo.getId());
			gb.setName(goodsPropertyTypeInfo.getName());
			gb.setTextOrSelect(goodsPropertyTypeInfo.getTextOrSelect());
			String content = goodsPropertyTypeInfo.getContent();
			if("0".equals(goodsPropertyTypeInfo.getTextOrSelect())){
				JSONArray json = JSONArray.fromObject(content); 
				String cont = "";
				for(int j=0;j<json.size();j++){
				    JSONObject job = json.getJSONObject(j); 
				    if(null!=job.get("text")){
				    	if(j!=json.size()-1){
					    	cont+=job.get("text")+",";
					    }else{
					    	cont+=job.get("text");
					    }
				    }
				 }
				gb.setContent(cont);
			}else{
				gb.setContent(content);
			}
			list.add(gb);
		}
		page.setVoList(list);
		return ObjectToResult.getVoResult(page);
	};
	
}

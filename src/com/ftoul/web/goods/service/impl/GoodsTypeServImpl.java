package com.ftoul.web.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goods.vo.GoodsTypeVo;
import com.ftoul.po.GoodsType;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.goods.service.GoodsTypeServ;



/**
 * 
*
* 类描述：
* @author: yw
* @date： 日期：2016年7月20日 时间：上午10:28:05
* @version 1.0
*
 */
@Service("WebGoodTypeServImpl")
public class GoodsTypeServImpl implements GoodsTypeServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 保存/更新用户对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveGoodsType(Parameter param) throws Exception {
		GoodsType goodsType = (GoodsType) JSONObject.toBean((JSONObject) param.getObj(),GoodsType.class);
		Object res;
		if(Common.isNull(goodsType.getId())){
			goodsType.setCreateTime(new DateStr().toString());
			goodsType.setState("1");
			res = hibernateUtil.save(goodsType);
		}else{
			goodsType.setModifyTime(new DateStr().toString());
			res = hibernateUtil.update(goodsType);
		}
		return ObjectToResult.getResult(res);
	}

	
	/**
	 * 
	 *  查找下一级产品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getNextGoodsTypes(Parameter param) throws Exception {
		GoodsType goodsType = (GoodsType) hibernateUtil.find(GoodsType.class, param.getId()+"");
		//如果是商品类别为最后一级
		if("3".equals(goodsType.getLevel())){
			throw new Exception("没有下一级商品类别");
		}
		String hql = "from GoodsType where state =1 pid = "+"'"+param.getId().toString()+"'";
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}

	
	/**
	 * 
	 * 删除商品类别对象
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */

	@Override
	public Result delGoodsTypes(Parameter param) throws Exception {
		GoodsType goodsType = (GoodsType) hibernateUtil.find(GoodsType.class, param.getId()+"");
		Object res;
		goodsType.setState("0");
		res=	hibernateUtil.update(goodsType);
		return ObjectToResult.getResult(res);
	}
	
	/**
	 * 
	 *  查找第一级产品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */

	@Override
	public Result getGoodsTypeLevel1List(Parameter param) throws Exception {
		String hql = "from GoodsType where level =1 and state=1 order by typeSort asc,createTime asc";
		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
		
	}

	/**
	 * 通过商品类别一级得到二三级商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getGoodsTypeLevel23from1List(Parameter param)
			throws Exception {
		String hql ="from GoodsType where level =1 and state =1 ";
		if(param.getId()!=null){
			hql+=" and id = '" + param.getId() +"'";
		}
		hql+="order by typeSort asc,createTime asc";
		List<Object> typeLevel1List = hibernateUtil.hql(hql);
		List<GoodsTypeVo> typeLevel1VoList = new ArrayList<GoodsTypeVo>();
		if(typeLevel1List != null){
			for (Object object : typeLevel1List) {
				GoodsType goodsType = (GoodsType) object;
				GoodsTypeVo goodsTypeVoLel1 = new GoodsTypeVo();
				hql = "from GoodsType where state =1 and pid = '" + goodsType.getId() +"'";
				hql+="order by typeSort asc,createTime asc";
				List<Object> typeLevel2List = hibernateUtil.hql(hql);
				List<GoodsTypeVo> typeLevel2VoList = new ArrayList<GoodsTypeVo>();
				if(typeLevel2List != null){
					for (Object object2 : typeLevel2List) {
						GoodsType goodsTypeLevel2 = (GoodsType)object2;
						GoodsTypeVo goodsTypeVoLel2 = new GoodsTypeVo();
						hql = "from GoodsType where state =1 and pid = '" + goodsTypeLevel2.getId() +"'";
						hql+="order by typeSort asc,createTime asc";
						List<Object> typeLevel3List = hibernateUtil.hql(hql);
						List<GoodsTypeVo> typeLevel3VoList = new ArrayList<GoodsTypeVo>();
						if(typeLevel3List != null){
							for (Object object3 : typeLevel3List) {
								GoodsType goodsTypeLevel3 = (GoodsType)object3;
								GoodsTypeVo goodsTypeVoLel3 = new GoodsTypeVo();
								goodsTypeVoLel3.setId(goodsTypeLevel3.getId());
								goodsTypeVoLel3.setPicSrc(goodsTypeLevel3.getPicSrc());
								goodsTypeVoLel3.setName(goodsTypeLevel3.getName());
								goodsTypeVoLel3.setGoodsTypelist(null);
								typeLevel3VoList.add(goodsTypeVoLel3);
							}
						}
						goodsTypeVoLel2.setId(goodsTypeLevel2.getId());
						goodsTypeVoLel2.setName(goodsTypeLevel2.getName());
						goodsTypeVoLel2.setGoodsTypelist(typeLevel3VoList);
						goodsTypeVoLel2.setPicSrc(goodsTypeLevel2.getPicSrc());
						typeLevel2VoList.add(goodsTypeVoLel2);
					}
					goodsTypeVoLel1.setId(goodsType.getId());
					goodsTypeVoLel1.setName(goodsType.getName());
					goodsTypeVoLel1.setGoodsTypelist(typeLevel2VoList);
					typeLevel1VoList.add(goodsTypeVoLel1);
				}
			}
		}
		return ObjectToResult.getResult(typeLevel1VoList) ;
		
	}

	/**
	 * 得到第三级商品类别集合
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getLevel3GoodsType(Parameter parameter) {
		String hql ="from GoodsType where level =3 state=1 order by typeSort asc,createTime asc";
		List<Object> typeLevel1List = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(typeLevel1List) ;
	}
	/**
	 * 得到第一级商品类别集合
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */

	@Override
	public Result getLevel1GoodsType(Parameter parameter) {
		String hql ="from GoodsType where level =1 order by typeSort asc,createTime asc";
		List<Object> typeLevel1List = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(typeLevel1List) ;
	}


	/**
	 * 通过id查找goodsType
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@Override
	public Result getByid(Parameter param) {
		GoodsType goodsType = (GoodsType) hibernateUtil.find(GoodsType.class, param.getId() + "");
		return ObjectToResult.getResult(goodsType) ;
		
	}
}

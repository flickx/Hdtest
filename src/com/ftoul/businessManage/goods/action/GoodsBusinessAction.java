package com.ftoul.businessManage.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.api.sms.util.MessageUtil;
import com.ftoul.businessManage.goods.service.GoodsBusinessServ;
import com.ftoul.businessManage.goods.service.GoodsBusinessTypeServ;
import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goods.service.GoodsBrandServ;
import com.ftoul.manage.goods.service.GoodsCanalServ;
import com.ftoul.manage.goods.service.GoodsParamServ;
import com.ftoul.manage.goods.service.GoodsPropServ;
import com.ftoul.manage.goods.service.GoodsPropTypeServ;
import com.ftoul.manage.goods.service.GoodsPropertyTypeInfoServ;
import com.ftoul.manage.goods.service.GoodsUploadpicServ;
@Controller("GoodsBusinessAction")
@RequestMapping(value = "/businessManage/goods")
public class GoodsBusinessAction {

	@Autowired
	private GoodsBusinessTypeServ goodsBusinessTypeServ;
	
	@Autowired
	private GoodsPropServ goodsPropServ;
	
	@Autowired
	private GoodsPropTypeServ goodsPropTypeServ;
	
	@Autowired
	private GoodsPropertyTypeInfoServ goodsPropertyTypeInfoServ;
	
	@Autowired
	private GoodsCanalServ goodsCanalServ;
	
	
	@Autowired
	private GoodsBusinessServ goodsBusinessServ;
	
	@Autowired
	private GoodsBrandServ goodsBrandServ;
	
	@Autowired
	private GoodsParamServ goodsParamServ;
	
	@Autowired
	private GoodsUploadpicServ goodsUploadpicServ;
	/**
	 * 获取下一级商品类别
	 * @param param 当前级别参数
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getNextGoodsTypes")  
	public @ResponseBody Result getNextGoodsTypes(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessTypeServ.getNextGoodsTypes(parameter);
	}
	
	
	/**
	 * 获取第一级商品类别
	 * @param param 当前级别参数
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsTypeLevel1List")  
	public @ResponseBody Result getGoodsTypeLevel1List(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessTypeServ.getGoodsTypeLevel1List(parameter);
	}
			
	/**
	 * 获取第二级商品类别
	 * @param param 当前级别参数
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsTypeLevel2List")  
	public @ResponseBody Result getGoodsTypeLevel2List(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessTypeServ.getGoodsTypeLevel2List(parameter);
	}
	
	@RequestMapping(value = "sendMessage")  
	public @ResponseBody Result sendMessage(String param) throws Exception{
//		Parameter parameter = Common.jsonToParam(param);
//		return goodsBusinessTypeServ.getNextGoodsTypes(parameter);
		
		String mobile ="18570614771";
		String content="test";
		String ret=MessageUtil.send(mobile, content);
		System.out.println(ret);
		return null;
	}
	
	/**
	 * 保存商品类别
	 * @param param 
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveGoodsType")  
	public @ResponseBody Result saveGoodsType(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessTypeServ.saveGoodsType(parameter);
	}
	
	/**
	 * 保存商品
	 * @param param 
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveGoodsPropType")  
	public @ResponseBody Result saveGoodsPropType(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropTypeServ.saveGoodsPropType(parameter);
	}
	
	
	/**
	 * 保存商品属性分类
	 * @param param 
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveGoods")  
	public @ResponseBody Result saveGoods(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessServ.saveGoods(parameter);
	}
	
	
	/**
	 *	添加商品第一步
	 * @param param 
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveGoodsFisrtStep")  
	public @ResponseBody Result saveGoodsFisrtStep(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessServ.saveGoodsFisrtStep(parameter);
	}
	
	
	/**
	 *	添加商品第二步
	 * @param param 
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveGoodsSecondStep")  
	public @ResponseBody Result saveGoodsSecondStep(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessServ.saveGoodsSecondStep(parameter);
	}
	
	/**
	 * 通过商品类别一级得到二三级商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getGoodsTypeLevel23from1List")  
	public @ResponseBody Result getGoodsTypeLevel23from1List(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessTypeServ.getGoodsTypeLevel23from1List(parameter);
	}
	
	/**
	 * 
	 * 保存/更新商品品牌
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "saveGoodsBrand")  
	public @ResponseBody Result saveGoodsBrand(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBrandServ.saveGoodsBrand(parameter);
	}
	
	
	/**
	 * 
	 * 通过id查找goodsBrand
	 * @param   param Parameter对象
	 */
	@RequestMapping(value = "getGoodsBrandById")  
	public @ResponseBody Result getGoodsBrandById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBrandServ.getGoodsBrandById(parameter);
	}
	
	
	/**
	 *	得到第一级的所有商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getLevel1GoodsType")  
	public @ResponseBody Result getLevel1GoodsType(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessTypeServ.getLevel1GoodsType(parameter);
	}
	
	
	/**
	 *	得到第三级的所有商品类别
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getLevel3GoodsType")  
	public @ResponseBody Result getLevel3GoodsType(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessTypeServ.getLevel3GoodsType(parameter);
	}
	/**
	 *	得到第三级的所有商品类别（根据二级分类查询）
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "getLevel3GoodsTypeByParentId")  
	public @ResponseBody Result getLevel3GoodsTypeByParentId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessTypeServ.getLevel3GoodsTypeByParentId(parameter);
	}
	
	/**
	 * 获取商品品牌（带分页）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsBrandListPage")  
	public @ResponseBody Result getGoodsBrandListPage(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsBrandServ.getGoodsBrandListPage(parameter);
	}
	
	
	/**
	 * 得到商品列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsList")  
	public @ResponseBody Result getGoodsList(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessServ.getGoodsList(parameter);
	}
	
	/**
	 * 得到所有商品属性大类
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsPropType")  
	public @ResponseBody Result getGoodsPropType(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropTypeServ.getGoodsPropType(parameter);
	}
	
	
	
	
	/**
	 * 通过大类id得到商品属性详情
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsPropInfoTypeListByTypeId")  
	public @ResponseBody Result getGoodsPropInfoTypeListByTypeId(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropertyTypeInfoServ.getGoodsPropInfoTypeListByTypeId(parameter);
	}

	
	/**
	 * 通过PID得到商品类别
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getByPid")  
	public @ResponseBody Result getByPid(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessTypeServ.getByPid(parameter);
	}
	
	/**
	 * 得到所有的商品品牌
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsBrandList")  
	public @ResponseBody Result getGoodsBrandList(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsBrandServ.getGoodsBrandList(parameter);
	}
	
	/**
	 * 保存商品参数
	 * @param param 
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveGoodsParam")  
	public @ResponseBody Result saveGoodsParam(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsParamServ.saveGoodsParam(parameter);
	}

	/**
	 * 编辑商品参数
	 * @param param 
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "editGoodsParam")  
	public @ResponseBody Result editGoodsParam(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsParamServ.editGoodsParam(parameter);
	}
	
	/**
	 * 删除商品参数
	 * @param param 
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "delGoodsParam")  
	public @ResponseBody Result delGoodsParam(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsParamServ.delGoodsParam(parameter);
	}
	
	
	/**
	 * 得到商品参数
	 * @param param 
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsParamList")  
	public @ResponseBody Result getGoodsParamList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsParamServ.getGoodsParamList(parameter);
	}
	
	/**
	 * 得到商品参数
	 * @param param 
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsParam")  
	public @ResponseBody Result getGoodsParam(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsParamServ.getGoodsParam(parameter);
	}

	/**
	 * 获取商品列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsVoList")  
	public @ResponseBody Result getGoodsVoList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessServ.getGoodsVoList(parameter);
	}
	/**
	 * 上下架商品
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateGoods")  
	public @ResponseBody Result updateGoods(String param,String id,String grounding) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessServ.updateGoods(parameter,id,grounding);
	}
	/**
	 * 
	 * 删除商品属性分类对象
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "delGoodsPropType")  
	public @ResponseBody Result delGoodsPropType(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropTypeServ.delGoodsPropType(parameter);
	}
	/**
	 * 获取属性内容
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getPropTypeContent")  
	public @ResponseBody Result getPropTypeContent(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropertyTypeInfoServ.getPropTypeContent(parameter);
	}
	
	/**
	 * 添加商品属性内容
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveGoodsPropertyTypeInfo")  
	public @ResponseBody Result saveGoodsPropertyTypeInfo(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropertyTypeInfoServ.saveGoodsPropertyTypeInfo(parameter);
	}
	
	/**
	 * 修改商品属性
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateGoodsPropertyTypeInfo")  
	public @ResponseBody Result updateGoodsPropertyTypeInfo(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropertyTypeInfoServ.updateGoodsPropertyTypeInfo(parameter);
	}
	
	/**
	 * 添加商品参数
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveGoodsParamByGoods")  
	public @ResponseBody Result saveGoodsParamByGoods(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsParamServ.saveGoodsParamByGoods(parameter);
	}
	

	/**
	 * 得到商品预警列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsListByEarlyWarning")  
	public @ResponseBody Result getGoodsListByEarlyWarning(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessServ.getGoodsListByEarlyWarning(parameter);
	}
	

	/**
	 * 保存goodsProp
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveGoodsProp")  
	public @ResponseBody Result saveGoodsProp(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropServ.saveGoodsProp(parameter);
	}
	
	/**
	 * 入库
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveStock")  
	public @ResponseBody Result saveStock(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsParamServ.saveStock(parameter);
	}
	
	

	/**
	 * 通过商品id得到商品vo
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsVoById")  
	public @ResponseBody Result getGoodsVoById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessServ.getGoodsVoById(parameter);
	}
	/**
	 * 通过商品id得到goods
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsById")  
	public @ResponseBody Result getGoodsById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessServ.getGoodsById(parameter);
	}

	/**
	 * 得到商品品牌列表
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "getGoodsBrand")  
	public @ResponseBody Result getGoodsBrand(String param) throws Exception{	
		Parameter parameter = Common.jsonToParam(param);
		return goodsBrandServ.getGoodsBrand(parameter);
	}
	/**
	 * 删除商品属性
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delGoodsPropertyTypeInfo")  
	public @ResponseBody Result delGoodsPropertyTypeInfo(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropertyTypeInfoServ.delGoodsPropertyTypeInfo(parameter);
	}
	/**
	 *	删除商品分类（三级分类）
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "delGoodsTypes3")  
	public @ResponseBody Result delGoodsTypes3(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result rs = goodsBusinessTypeServ.getGoodsByType(parameter,"3");
		List<Object> obj = (List<Object>)rs.getObj();
		if(obj.size()>0){
			rs.setMessage("该分类下有商品，不能删除");
			rs.setResult(0);
			return rs;
		}else{
			return goodsBusinessTypeServ.delGoodsTypes(parameter);
		}
	}
	/**
	 *	删除商品分类（二级分类）
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "delGoodsTypes2")  
	public @ResponseBody Result delGoodsTypes2(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result rs = goodsBusinessTypeServ.getNextGoodsTypes(parameter);
		Result goodsRs = goodsBusinessTypeServ.getGoodsByType(parameter,"2");
		List<Object> obj = (List<Object>)goodsRs.getObj();
		if(obj.size()>0){
			rs.setMessage("该分类下有商品，不能删除");
			rs.setResult(0);
			return rs;
		}
		if("[]" == rs.getObj().toString()){
			rs = goodsBusinessTypeServ.delGoodsTypes(parameter);
		}else{
			rs.setMessage("该分类下有下级分类，不能删除");
			rs.setResult(0);
		}
		return rs;
	}
	/**
	 *	删除商品分类（一级分类）
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "delGoodsTypes1")  
	public @ResponseBody Result delGoodsTypes1(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result rs = goodsBusinessTypeServ.getNextGoodsTypes(parameter);
		Result goodsRs = goodsBusinessTypeServ.getGoodsByType(parameter,"1");
		List<Object> obj = (List<Object>)goodsRs.getObj();
		if(obj.size()>0){
			rs.setMessage("该分类下有商品，不能删除");
			rs.setResult(0);
			return rs;
		}
		if("[]" == rs.getObj().toString()){
			rs = goodsBusinessTypeServ.delGoodsTypes(parameter);
		}else{
			rs.setMessage("该分类下有下级分类，不能删除");
			rs.setResult(0);
		}
		return rs;
	}
	
	/**
	 *	设置商品参数为默认
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "saveDefault")  
	public @ResponseBody Result saveDefault(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsParamServ.saveDefault(parameter);
	}

	
	/**
	 * 通过商品id和图片类型得到商品图片集合
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsUploadpicList")  
	public @ResponseBody Result getGoodsUploadpicList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsUploadpicServ.getGoodsUploadpicList(parameter);
	}
	/**
	 * 通过id删除商品图片
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveGoodsCanal")  
	public @ResponseBody Result saveGoodsCanal(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsCanalServ.saveGoodsCanal(parameter);
	}

	
	/**
	 * 商城图片
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delGoodsUploadpic")  
	public @ResponseBody Result delGoodsUploadpic(String param ,HttpServletRequest request) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsUploadpicServ.delGoodsUploadpic(parameter , request);
	}
	/**
	 * 保存商品渠道
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsCanalPage")  
	public @ResponseBody Result getGoodsCanalPage(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsCanalServ.getGoodsCanalPage(parameter);
	}
	
	
	/**
	 * 删除商品渠道
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delGoodsCanal")  
	public @ResponseBody Result delGoodsCanal(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsCanalServ.delGoodsCanal(parameter);
	}
	
	/**
	 * 查询商品渠道
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsCanalList")  
	public @ResponseBody Result getGoodsCanalList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsCanalServ.getGoodsCanalList(parameter);
	}
	/**
	 * 通过id查找商品类型
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsPropTypeById")  
	public @ResponseBody Result getGoodsPropTypeById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropTypeServ.getById(parameter);
	}
	

	/**
	 * 通过通过商品id查询所对应的goodsType的集合
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsTypeSetByGoodsId")  
	public @ResponseBody Result getGoodsTypeSetByGoodsId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessServ.getGoodsTypeSetByGoodsId(parameter);
	}
	

	/**
	 * 根据ID查找商品分类
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsTypeById")  
	public @ResponseBody Result getGoodsTypeById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessTypeServ.getByid(parameter);
	}
	/**
	 * 根据ID查找商品分类
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsPropTypePageList")  
	public @ResponseBody Result getGoodsPropTypePageList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropTypeServ.getGoodsPropTypePageList(parameter);
	}
	
	/**
	 * 根据ID查找商品分类
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveGoodsTypePic")  
	public @ResponseBody Result saveGoodsTypePic(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropTypeServ.getGoodsPropTypePageList(parameter);
	}
	
	/**
	 * 根据ID查找商品分类
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsProp")  
	public @ResponseBody Result getGoodsProp(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsPropServ.getGoodsProp(parameter);
	}
	
	
	/**
	 * 删除商品
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delGoods")  
	public @ResponseBody Result delGoods(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBusinessServ.delGoods(parameter);
	}
}

/**
 * 
 */
package com.ftoul.manage.goods.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.goods.service.GoodsBrandServ;
import com.ftoul.manage.goods.service.GoodsTypeServ;
import com.ftoul.po.GoodsType;

/**
 *
 * 类描述  商品品牌控制类
 * 
 * @author: yw
 * @date： 日期：2016年7月20日 时间：上午11:11:05
 * @version 1.0
 *
 */
@Controller
@RequestMapping(value = "/manage/goodsBrands")
public class GoodsBrandAction {
	@Autowired
	private GoodsBrandServ goodsBrandServ;
	@Autowired
	private GoodsTypeServ goodsTypeServ;
	
	/**
	 * 查询所有品牌列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getAllGoodsBrandList")  
	public @ResponseBody Result getAllGoodsBrandList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBrandServ.getAllGoodsBrandList(parameter);
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
	 * 删除品牌
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delGoodsBrand")  
	public @ResponseBody Result delGoodsBrand(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBrandServ.delGoodsBrand(parameter);
	}
	/**
	 * 查询品牌类型
	 * @param param
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getLevel3GoodsType")  
	public void getLevel3GoodsType(String param,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		Result result = goodsTypeServ.getLevel3GoodsType(parameter);
		Object object = result.getObj();
		List<GoodsType> list = (List<GoodsType>)object;
		String str = "<select>";
		for (int i = 0; i < list.size(); i++) {
			str+="<option value='"+list.get(i).getId()+"'>"+list.get(i).getName()+"</option>";
		}
		str += "</select>";
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(str);
		out.flush();
	}
	/**
	 * 根据Id查询商品品牌
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsBrandById")  
	public @ResponseBody Result getGoodsBrandById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBrandServ.getGoodsBrandById(parameter);
	}
	/**
	 * 删除品牌logo
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delGoodsBrandLogo")  
	public @ResponseBody Result delGoodsBrandLogo(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return goodsBrandServ.delGoodsBrandLogo(parameter);
	}
}

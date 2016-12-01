package com.ftoul.businessManage.goods.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.businessManage.goods.service.BusinessClassifyServ;
import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 店铺分类.
 * @author yzw
 *
 */
@Controller("BusinessClassifyAction")
@RequestMapping(value = "/businessManage/businessClassify")
public class BusinessClassifyAction {
	@Autowired
	private BusinessClassifyServ businessClassifyServ;
	

	/**
	 * 查询所有分类名称
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getBusinessClassify")  
	public @ResponseBody Result getBusinessClassify(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessClassifyServ.getBusinessClassify(parameter);
	}
	
	/**
	 * 
	 * 保存/更新店铺分类
	 * @param   param Parameter对象
	 * @return  返回结果（前台用Result对象）
	 */
	@RequestMapping(value = "saveBusinessClassify")  
	public @ResponseBody Result saveBusinessClassify(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessClassifyServ.saveBusinessClassify(parameter);
	}
	/**
	 * 删除店铺分类
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delBusinessClassify")  
	public @ResponseBody Result delBusinessClassify(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessClassifyServ.delBusinessClassify(parameter);
	}
	/**
	 * 根据Id查询
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getBusinessClassifyById")  
	public @ResponseBody Result getBusinessClassifyById(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessClassifyServ.getBusinessClassifyById(parameter);
	}
	/**
	 * 根据店铺Id查询
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getBusinessClassifyByShopId")  
	public @ResponseBody Result getBusinessClassifyByShopId(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return businessClassifyServ.getBusinessClassifyByShopId(parameter);
	}
}

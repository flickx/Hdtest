package com.ftoul.pc.search.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.search.service.SearchKeyNameServ;

@Controller
@RequestMapping("pc/search")
public class SearchKeyNameAction {
	@Autowired
	private SearchKeyNameServ searchKeyNameServ;
	/**
	 * 获取关键字
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getSearchKeyName") 
	public @ResponseBody Result getSearchKeyName(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return searchKeyNameServ.getSearchKeyName(parameter);
	}
	/**
	 * 根据关键字查询商品（分页）
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getGoodsBykeyName") 
	public @ResponseBody Result getGoodsBykeyName(String param) throws Exception{
//		param=java.net.URLDecoder.decode(param,"UTF-8");
		Parameter parameter = Common.jsonToParam(param);
		return searchKeyNameServ.getGoodsBykeyName(parameter);
	}
	/**
	 * 猜猜你喜欢
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getLikeGoodsList")  
	public @ResponseBody Result getLikeGoodsList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return searchKeyNameServ.getLikeGoodsList(parameter);
	}
	/**
	 * 大家都在买
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getSaleNumGoodsList")  
	public @ResponseBody Result getSaleNumGoodsList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return searchKeyNameServ.getSaleNumGoodsList(parameter);
	}
	
}

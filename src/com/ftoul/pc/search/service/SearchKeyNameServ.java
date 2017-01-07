package com.ftoul.pc.search.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface SearchKeyNameServ {
	/**
	 * 获取搜索关键字
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getSearchKeyName(Parameter param) throws Exception;
	/**
	 * 根据关键字查询商品（分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result getGoodsBykeyName(Parameter param) throws Exception;
	/**
	 * 猜猜你喜欢
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getLikeGoodsList(Parameter param)throws Exception;
	/**
	 * 大家都在买
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getSaleNumGoodsList(Parameter param)throws Exception;
}

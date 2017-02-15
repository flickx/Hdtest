package com.ftoul.pc.store.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

public interface StoreServ {
	/**
	 * 得到店铺商铺列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getStoreGoodsPage(Parameter param) throws Exception;
	/**
	 * 
	 * 根据商铺分类得到店铺商铺列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getStoreGoodsPageByBusinessClassify(Parameter param) throws Exception;
}

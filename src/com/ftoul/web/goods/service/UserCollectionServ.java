package com.ftoul.web.goods.service;

import org.springframework.stereotype.Service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 
*
* 类描述：
* @author: yw
* @date： 日期：2016年8月8日 时间：上午9:58:52
* @version 1.0
*
*/


@Service("WebUserCollectionImpl")
public interface UserCollectionServ{

	/**
	 * 保存/更新商品对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveUserCollection(Parameter param) throws Exception;
	
	
	/**
	 * 查找是否存在收藏
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result findUserCollection(Parameter param) throws Exception;

	
	/**
	 * 获得所有收藏商品.
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result getUserCollectionList(Parameter param) throws Exception;
	
	
	/**
	 * 删除收藏商品.
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Result deleteUserCollection(Parameter param) throws Exception;
}

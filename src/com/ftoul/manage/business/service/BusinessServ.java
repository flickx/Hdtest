package com.ftoul.manage.business.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 
 * @author wenyujie
 *
 */
public interface BusinessServ {
	/**
	 * 保存/更新商家对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	Result saveBusiness(Parameter param) throws Exception;
	
	/**
	 * 查询商家信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result getBusinessPage(Parameter param) throws Exception;
	
	/**
	 * 查询商家店铺等级信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result getBusinessStoreRank(Parameter param) throws Exception;
	/**
	 * 查询商家店铺分类信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result getBusinessStoreClassify(Parameter param) throws Exception;
	/**
	 * 查询商家信息(分页)
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result getBusinessPageList(Parameter param) throws Exception;
	
	/**
	 * 查询商家店铺等级信息(分页)
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result getBusinessStoreRankList(Parameter param) throws Exception;
	/**
	 * 查询商家店铺分类信息(分页)
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result getBusinessStoreClassifyList(Parameter param) throws Exception;
	/**
	 * 查询商家店铺分类信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result updateBusinessStoreLogin(Parameter parameter) throws Exception;
	/**
	 * 查询商家详情信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result getBusinessLoginById(Parameter parameter) throws Exception;
	/**
	 * 保存店铺分类信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result saveBusinessStoreClassify(Parameter parameter) throws Exception;
	/**
	 * 根绝ID获取店铺分类信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result getBusinessStoreClassifyById(Parameter parameter) throws Exception;
	/**
	 * 更新店铺分类信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result updateBusinessStoreClassify(Parameter parameter) throws Exception;
	/**
	 * 删除店铺分类信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result delBusinessStoreClassifybyId(Parameter parameter) throws Exception;
	/**
	 * 导出店铺分类信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result exportBusinessStoreClassifyExcel(Parameter parameter) throws Exception;
	/**
	 * 导出店铺信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result exportBusinessStoreExcel(Parameter parameter) throws Exception;
	/**
	 * 保存店铺等级信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result saveBusinessStoreRank(Parameter parameter) throws Exception;
	/**
	 * 删除店铺等级信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result delBusinessStoreRankbyId(Parameter parameter) throws Exception;
	/**
	 * 保存店铺经营类目信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result saveBusinessStoreManageCategory(Parameter parameter) throws Exception;
	/**
	 * 获取店铺经营类目信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result getBusinessStoreManageCategoryPage(Parameter parameter) throws Exception;
	/**
	 * 删除店铺经营类目信息
	 * @param param
	 * @return 返回结果(前台用Result对象)
	 * @throws Exception
	 */
	Result delBusinessStoreManageCategoryPage(Parameter parameter) throws Exception;
}


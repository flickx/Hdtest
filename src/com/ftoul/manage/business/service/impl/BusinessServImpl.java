package com.ftoul.manage.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.MD5;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.business.service.BusinessServ;
import com.ftoul.manage.orders.vo.OrdersPayVo;
import com.ftoul.po.Business;
import com.ftoul.po.BusinessStoreClassify;
import com.ftoul.po.BusinessStoreLogin;
import com.ftoul.po.BusinessStoreRank;
import com.ftoul.po.Goods;
import com.ftoul.po.LoginUser;
import com.ftoul.util.hibernate.HibernateUtil;

/**
 * 
 * @author wenyujie
 *
 */
@Service("BusinessServImpl")
public class BusinessServImpl implements BusinessServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 保存/更新商家对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveBusiness(Parameter param) throws Exception {
		BusinessStoreLogin businessStoreLogin=(BusinessStoreLogin) JSONObject.toBean((JSONObject) param.getObj(),BusinessStoreLogin.class);
		Object res;
			//基本信息
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessBase().setCreateId(param.getManageToken().getLoginUser().getLoginName());
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessBase().setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessBase().setOperateId(param.getManageToken().getLoginUser().getLoginName());
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessBase().setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessBase().setState("1");
			res =hibernateUtil.save(businessStoreLogin.getBusinessStore().getBusiness().getBusinessBase());
			//经营信息
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessManage().setCreateId(param.getManageToken().getLoginUser().getLoginName());
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessManage().setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessManage().setOperateId(param.getManageToken().getLoginUser().getLoginName());
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessManage().setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessManage().setState("1");
			res =hibernateUtil.save(businessStoreLogin.getBusinessStore().getBusiness().getBusinessManage());
			//银行信息
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessBank().setCreateId(param.getManageToken().getLoginUser().getLoginName());
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessBank().setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessBank().setOperateId(param.getManageToken().getLoginUser().getLoginName());
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessBank().setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreLogin.getBusinessStore().getBusiness().getBusinessBank().setState("1");
			res =hibernateUtil.save(businessStoreLogin.getBusinessStore().getBusiness().getBusinessBank());
			//商家信息
			businessStoreLogin.getBusinessStore().getBusiness().setCreateId(param.getManageToken().getLoginUser().getLoginName());
			businessStoreLogin.getBusinessStore().getBusiness().setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreLogin.getBusinessStore().getBusiness().setOperateId(param.getManageToken().getLoginUser().getLoginName());
			businessStoreLogin.getBusinessStore().getBusiness().setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreLogin.getBusinessStore().getBusiness().setState("1");
			res =hibernateUtil.save(businessStoreLogin.getBusinessStore().getBusiness());
			//店铺信息
			businessStoreLogin.getBusinessStore().setCreateId(param.getManageToken().getLoginUser().getLoginName());
			businessStoreLogin.getBusinessStore().setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreLogin.getBusinessStore().setOperateId(param.getManageToken().getLoginUser().getLoginName());
			businessStoreLogin.getBusinessStore().setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreLogin.getBusinessStore().setState("1");
			res =hibernateUtil.save(businessStoreLogin.getBusinessStore());
			//店铺登录信息
			businessStoreLogin.setPassword(MD5.getMD5(businessStoreLogin.getPassword()));
			businessStoreLogin.setCreateId(param.getManageToken().getLoginUser().getLoginName());
			businessStoreLogin.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreLogin.setOperateId(param.getManageToken().getLoginUser().getLoginName());
			businessStoreLogin.setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreLogin.setState("1");
			res = hibernateUtil.save(businessStoreLogin);
		return ObjectToResult.getResult(res);
	}

	/**
	 * 获取商家登录信息对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getBusinessPage(Parameter param) throws Exception {
		String queryStr = param.getWhereStr();
		String hql =null;
		if(queryStr!=null){
			hql = "from BusinessStoreLogin"+" where businessStore.business.state =1 "+ queryStr + " order by businessStore.business.createTime desc";
		}else{
			hql = "from BusinessStoreLogin"+" where businessStore.business.state =1  order by businessStore.business.createTime desc";
		}
		
		List<Object> list = hibernateUtil.hql(hql); 
		return ObjectToResult.getResult(list);
	}
	/**
	 * 获取银行信息对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getBusinessStoreRank(Parameter param) throws Exception {
		String hql = "from BusinessStoreRank"+" where state =1 "+ " order by createTime desc";
		List<Object> list = hibernateUtil.hql(hql); 
		return ObjectToResult.getResult(list);
	}
	/**
	 * 获取店铺店铺分类信息对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getBusinessStoreClassify(Parameter param) throws Exception {
		String queryStr = param.getWhereStr();
		String hql = null;
		if(queryStr!=null){
			hql = "from BusinessStoreClassify"+" where state =1 "+  queryStr + " order by createTime desc";
		}else{
			hql = "from BusinessStoreClassify"+" where state =1 "+ " order by createTime desc";
		}
		List<Object> list = hibernateUtil.hql(hql); 
		return ObjectToResult.getResult(list);
	}
	/**
	 * 重置店铺登录对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result updateBusinessStoreLogin(Parameter parameter)
			throws Exception {
		BusinessStoreLogin businessStoreLogin=(BusinessStoreLogin) JSONObject.toBean((JSONObject) parameter.getObj(),BusinessStoreLogin.class);
		BusinessStoreLogin bLogin =	(BusinessStoreLogin) hibernateUtil.find(BusinessStoreLogin.class,businessStoreLogin.getId() );
		bLogin.setPassword(MD5.getMD5(businessStoreLogin.getPassword()));
		bLogin.setOperateId(parameter.getManageToken().getLoginUser().getLoginName());
		bLogin.setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return ObjectToResult.getResult(bLogin);
	}
	/**
	 * 根据ID查询店铺登录信息
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getBusinessLoginById(Parameter parameter)
			throws Exception {
		BusinessStoreLogin businessStoreLogin =	(BusinessStoreLogin) hibernateUtil.find(BusinessStoreLogin.class,parameter.getId().toString());
		return ObjectToResult.getResult(businessStoreLogin);
	}
	/**
	 * 保存店铺分类信息
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveBusinessStoreClassify(Parameter parameter)
			throws Exception {
		BusinessStoreClassify businessStoreClassify=(BusinessStoreClassify) JSONObject.toBean((JSONObject) parameter.getObj(),BusinessStoreClassify.class);
		businessStoreClassify.setCreateId(parameter.getManageToken().getLoginUser().getLoginName());
		businessStoreClassify.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		businessStoreClassify.setOperateId(parameter.getManageToken().getLoginUser().getLoginName());
		businessStoreClassify.setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		businessStoreClassify.setState("1");
		Object res;
		res = hibernateUtil.save(businessStoreClassify);
		return ObjectToResult.getResult(res);
	}
	/**
	 * 通过ID获取店铺分类信息
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getBusinessStoreClassifyById(Parameter parameter)
			throws Exception {
		BusinessStoreClassify businessStoreClassify =(BusinessStoreClassify) hibernateUtil.find(BusinessStoreClassify.class,parameter.getId().toString());
		return ObjectToResult.getResult(businessStoreClassify);
	}
	/**
	 * 修改店铺分类信息
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result updateBusinessStoreClassify(Parameter parameter)
			throws Exception {
		BusinessStoreClassify businessStoreClassify=(BusinessStoreClassify) JSONObject.toBean((JSONObject) parameter.getObj(),BusinessStoreClassify.class);
		businessStoreClassify.setOperateId(parameter.getManageToken().getLoginUser().getLoginName());
		businessStoreClassify.setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		Object res;
		res = hibernateUtil.update(businessStoreClassify);
		return ObjectToResult.getResult(res);
	}
	/**
	 * 删除店铺分类信息
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result delBusinessStoreClassifybyId(Parameter parameter)
			throws Exception {
		String hql="update BusinessStoreClassify set state = '0' where id in ("+StrUtil.getIds(parameter.getId())+")";
		Integer res;
		res = hibernateUtil.execHql(hql);
		return ObjectToResult.getResult(res);
	}
	/**
	 * 导出Excel店铺分类信息
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result exportBusinessStoreClassifyExcel(Parameter parameter)
			throws Exception {
		String queryStr = parameter.getWhereStr();
		String hql = null;
		if(queryStr!=null){
			hql = "from BusinessStoreClassify"+" where state =1 "+  queryStr + " order by createTime desc";
		}else{
			hql = "from BusinessStoreClassify"+" where state =1 "+ " order by createTime desc";
		}
		List<Object> list = hibernateUtil.hql(hql); 
		List<Object> reportDataList = new ArrayList<Object>();
		Object[] vo = null;
		for (int j = 0; j < list.size(); j++) {
			BusinessStoreClassify businessStoreClassify = (BusinessStoreClassify) list.get(j);
			vo = new Object[9];
			vo[0] = businessStoreClassify.getId();
			vo[1] = businessStoreClassify.getStoreType();
			vo[2] = businessStoreClassify.getStoreClassify();
			vo[3] = businessStoreClassify.getClassifyComment();
			vo[4] = businessStoreClassify.getOperateId();
			vo[5] = businessStoreClassify.getOperateTime();
			vo[6] = businessStoreClassify.getCreateId();
			vo[7] = businessStoreClassify.getCreateTime();
			vo[8] = businessStoreClassify.getState();
			reportDataList.add(vo);
		}
		return ObjectToResult.getResult(reportDataList);
	}
	/**
	 * 保存店铺等级信息
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveBusinessStoreRank(Parameter parameter) throws Exception {
		BusinessStoreRank businessStoreRank=(BusinessStoreRank) JSONObject.toBean((JSONObject) parameter.getObj(),BusinessStoreRank.class);
		businessStoreRank.setCreateId(parameter.getManageToken().getLoginUser().getLoginName());
		businessStoreRank.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		businessStoreRank.setOperateId(parameter.getManageToken().getLoginUser().getLoginName());
		businessStoreRank.setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		businessStoreRank.setState("1");
		Object res;
		res = hibernateUtil.save(businessStoreRank);
		return ObjectToResult.getResult(res);
	}
	/**
	 * 删除店铺等级信息
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result delBusinessStoreRankbyId(Parameter parameter)
			throws Exception {
		String hql="update BusinessStoreRank set state = '0' where id in ("+StrUtil.getIds(parameter.getId())+")";
		Integer res;
		res = hibernateUtil.execHql(hql);
		return ObjectToResult.getResult(res);
	}
}

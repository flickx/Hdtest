package com.ftoul.manage.admin.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.MD5;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.admin.service.AdminServ;
import com.ftoul.po.LoginUser;
import com.ftoul.po.OrdersDetail;
import com.ftoul.po.User;
import com.ftoul.po.UserBrowse;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.token.TokenUtil;

@Service("AdminServImpl")
public class AdminServImpl implements AdminServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private TokenUtil tokenUtil;
	
	/**
	 * 获取管理员列表（带分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getAdminList(Parameter param) throws Exception {
		String hql = "from LoginUser where state = '1'" +  param.getWhereStr() + param.getOrderBy() ;
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	
	/**
	 * 根据用户ID获取单个管理员对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getAdminById(Parameter param) throws Exception {
		LoginUser loginUser = (LoginUser) hibernateUtil.find(LoginUser.class, param.getId()+"");
		return ObjectToResult.getResult(loginUser);
	}
	
	/**
	 * 修改密码
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveAdmin(Parameter param) throws Exception {
		LoginUser loginUser = (LoginUser) JSONObject.toBean((JSONObject) param.getObj(),LoginUser.class);
		Object res;
		if(Common.isNull(loginUser.getId())){
			List<Object> userList = hibernateUtil.hql(" from LoginUser where state = '1' and loginName = '" + loginUser.getLoginName() +"'");
			if(userList == null || userList.size() == 0){
				loginUser.setCreateTime(new DateStr().toString());
				loginUser.setPassword(MD5.getMD5(loginUser.getPassword()));
				loginUser.setState("1");
				res = hibernateUtil.save(loginUser);
				tokenUtil.toManageToken(loginUser);
			}else{
				throw new Exception("用户已存在");
			}
		}else{
			LoginUser oldLoginUser = (LoginUser) hibernateUtil.find(LoginUser.class, loginUser.getId());
			oldLoginUser.setPassword(MD5.getMD5(loginUser.getPassword()));
			oldLoginUser.setModifyTime(new DateStr().toString());
			tokenUtil.uploadManageSecretKey(oldLoginUser);
			res = hibernateUtil.update(oldLoginUser);
		}
		return ObjectToResult.getResult(res);
	}
	/**
	 * 删除管理员信息
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result delAdmin(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update LoginUser set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}
	/**
	 * 获取管理员列表（带分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getLogList(Parameter param) throws Exception {
		String hql = "from LoginUserLog where 1 = 1 " +  param.getWhereStr() + param.getOrderBy() ;
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
}

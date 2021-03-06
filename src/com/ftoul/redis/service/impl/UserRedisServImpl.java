package com.ftoul.redis.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.po.OrdersDetail;
import com.ftoul.po.User;
import com.ftoul.po.UserBrowse;
import com.ftoul.redis.service.UserRedisServ;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.redis.RedisUtil;
import com.ftoul.util.token.TokenUtil;
import com.ftoul.util.webservice.WebserviceUtil;
import com.ftoul.web.vo.UsersVO;
import com.ftoul.web.webservice.UserService;

//@Service("UserRedisServImpl")
public class UserRedisServImpl implements UserRedisServ {

//	@Autowired
//	private HibernateUtil hibernateUtil;
//	@Autowired
//	private RedisUtil redisUtil;
//	@Autowired
//	private TokenUtil tokenUtil;
//	/**
//	 * 获取用户列表（带分页）
//	 * @param param Parameter对象
//	 * @return 返回结果（前台用Result对象）
//	 */
//	@Override
//	public Result getUserListPage(Parameter param) throws Exception {
//		String queryStr = "";
//		if(Common.notNull(param.getKey())){
//			queryStr += " and username like '%" + param.getKey() + "%'";
//		}
//		String hql = "from User where state = '1'" + queryStr;
//		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
//        return ObjectToResult.getResult(page);
//		
//	}
//	
//	/**
//	 * 根据用户ID获取单个用户对象
//	 * @param param Parameter对象
//	 * @return 返回结果（前台用Result对象）
//	 */
//	@Override
//	public Result getUserById(Parameter param) throws Exception {
//		User user = (User) hibernateUtil.find(User.class, param.getId()+"");
//		return ObjectToResult.getResult(user);
//	}
//	
//	/**
//	 * 保存/更新用户对象
//	 * @param param Parameter对象
//	 * @return 返回结果（前台用Result对象）
//	 */
//	@Override
//	public Result saveUser(Parameter param) throws Exception {
//		final User user = (User) JSONObject.toBean((JSONObject) param.getObj(),User.class);
//		if(Common.isNull(user.getId())){
//			return ObjectToResult.getResult(redisUtil.save(user.getId(), user));
//		}else{
//			return ObjectToResult.getResult(redisUtil.update(user.getId(), user));
//		}
//	}
//	/**
//	 * 删除用户信息
//	 * @param param Parameter对象
//	 * @return返回结果（前台用Result对象）
//	 */
//	@Override
//	public Result delUser(Parameter param) throws Exception {
//		Integer num = hibernateUtil.execHql("update User set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
//		return ObjectToResult.getResult(num);
//	}
//	/**
//	 * 根据用户ID获取订单详情
//	 * @param param Parameter对象
//	 * @return 返回结果（前台用Result对象）
//	 */
//	@Override
//	public Result getOrdersDetailById(Parameter param) throws Exception {
//		OrdersDetail ordersDetail = (OrdersDetail) hibernateUtil.find(OrdersDetail.class, param.getId()+"");
//		return ObjectToResult.getResult(ordersDetail);
//	}
//	/**
//	 * 获取用户列表
//	 * @param param Parameter对象
//	 * @return 返回结果（前台用Result对象）
//	 */
//	@Override
//	public Result getUserList(Parameter param) throws Exception {
//		String hql = "from User where state = '1'" + param.getWhereStr() + param.getOrderBy() ;
//		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
//		User user = (User) hibernateUtil.hqlFirst(hql);
//		User o = (User) redisUtil.get(user.getId(),User.class);
//		System.out.println("xp="+o.getXp());
//		System.out.println("score="+o.getScore());
//		return ObjectToResult.getResult(page);
//	}
//	/**
//	 * 获取用户浏览记录（带分页）
//	 * @param param Parameter对象
//	 * @return 返回结果（前台用Result对象）
//	 */
//	@Override
//	public Result getUserBrowseList(Parameter param) throws Exception {
//		String hql = "from UserBrowse where state = '1' and user.id = '" + param.getId() +"' " + param.getWhereStr() + param.getOrderBy() ;
//		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
//		return ObjectToResult.getResult(page);
//	}
//	/**
//	 * 保存/更新用户浏览记录
//	 * @param param Parameter对象
//	 * @return 返回结果（前台用Result对象）
//	 */
//	@Override
//	public Result saveUserBrowse(Parameter param) throws Exception {
//		UserBrowse userBrowse = (UserBrowse) JSONObject.toBean((JSONObject) param.getObj(),UserBrowse.class);
//		Object res;
//		if(Common.isNull(userBrowse.getId())){
//			User user = (User)hibernateUtil.find(User.class, param.getParentId()+"");
//			userBrowse.setUser(user);
//			userBrowse.setBrowseTime(new DateStr().toString());
//			userBrowse.setState("1");
//			res = hibernateUtil.save(userBrowse);
//		}else{
//			User user = (User)hibernateUtil.find(User.class, param.getParentId()+"");
//			userBrowse.setUser(user);
//			userBrowse.setBrowseTime(new DateStr().toString());
//			userBrowse.setState("1");
//			res = hibernateUtil.update(userBrowse);
//		}
//		return ObjectToResult.getResult(res);
//	}
//	/**
//	 * 删除用户浏览记录
//	 * @param param Parameter对象
//	 * @return返回结果（前台用Result对象）
//	 */
//	@Override
//	public Result delUserBrowse(Parameter param) throws Exception {
//		
//		Integer num = hibernateUtil.execHql("update UserBrowse set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
//		return ObjectToResult.getResult(num);
//	}
//
//	/**
//	 * 停用
//	 * @param param Parameter对象
//	 * @return返回结果（前台用Result对象）
//	 */
//	@Override
//	public Result stopUser(Parameter param) throws Exception {
//		Integer num = hibernateUtil.execHql("update User set static_ = '0' where id in ("+StrUtil.getIds(param.getId())+")");
//		return ObjectToResult.getResult(num);
//	}
//
//	@Override
//	public Result doResetPassword(Parameter param) throws Exception {
//		UserService userService = WebserviceUtil.getService();
//		Object res=null;
//		UsersVO user = (UsersVO) JSONObject.toBean((JSONObject) param.getObj(),UsersVO.class);
//		String hql = "from User where state = 1 and id = '" + param.getId()+"'";	
//		User userDb = (User) hibernateUtil.hqlFirst(hql);
//		String passwordVersion = userDb.getPasswordVersion();
//		if(passwordVersion == null)
//			passwordVersion = "1";
//		else{
//			passwordVersion = (Integer.parseInt(passwordVersion)+1)+"";
//		}
//		userDb.setPasswordVersion(passwordVersion);
//		//通过webservice修改密码
//		userService.modifyPwd(user.getUsername(),user.getPassword());
//		tokenUtil.uploadSecretKey(userDb);
//		hibernateUtil.update(userDb);
//		return ObjectToResult.getResult(res);
//	}
	
}

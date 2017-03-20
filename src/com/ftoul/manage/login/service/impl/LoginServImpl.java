package com.ftoul.manage.login.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.DateStr;
import com.ftoul.common.MD5;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.login.service.LoginServ;
import com.ftoul.manage.user.vo.ManageTokenVo;
import com.ftoul.mongo.po.LoginUserLog;
import com.ftoul.po.LoginUser;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.mongodb.MongoDbUtil;
import com.ftoul.util.token.TokenUtil;

@Service("LoginServImpl")
public class LoginServImpl implements LoginServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private TokenUtil tokenUtil;
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private HttpServletResponse res;
	@Autowired
	MongoDbUtil mongoDbUtil;
	/**
	 * 管理员登陆
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result login(Parameter param) throws Exception {
		LoginUser loginUser = (LoginUser) JSONObject.toBean((JSONObject) param.getObj(),LoginUser.class);
		String hql = " from LoginUser where state = '1' and loginName = '" 
				+ loginUser.getLoginName() + "' and password = '"
				+ MD5.getMD5(loginUser.getPassword()) + "'";
		List<Object> loginUserList = hibernateUtil.hql(hql);
		ManageTokenVo manageTokenVo = new ManageTokenVo();
		if(loginUserList != null && loginUserList.size() > 0){
			LoginUser loginUserDb = (LoginUser)loginUserList.get(0);
			manageTokenVo = tokenUtil.toManageToken(loginUserDb);
			LoginUserLog loginUserLog = new LoginUserLog(); 
//			loginUserLog.setLoginUser(loginUserDb);
			com.ftoul.mongo.po.LoginUser loginUser2 = null;
			if(loginUser!=null){
				loginUser2 = new com.ftoul.mongo.po.LoginUser();
				BeanUtils.copyProperties(loginUser, loginUser2);
			}
			loginUserLog.setLoginUser(loginUser2);
			loginUserLog.setOperation("login");
			loginUserLog.setMethodName("login");
			loginUserLog.setMethodPackage("/manage/login");
			loginUserLog.setOperationTime(new DateStr().toString());
			loginUserLog.setIpAddress(req.getRemoteAddr());
			loginUserLog.setResStatic(res.getStatus()+"");
			loginUserLog.setResText("success");
			mongoDbUtil.insert(loginUserLog);
//			hibernateUtil.save(loginUserLog);
			
		}else{
			LoginUserLog loginUserLog = new LoginUserLog(); 
//			loginUserLog.setLoginUser(loginUser);
			com.ftoul.mongo.po.LoginUser loginUser2 = null;
			if(loginUser!=null){
				loginUser2 = new com.ftoul.mongo.po.LoginUser();
				BeanUtils.copyProperties(loginUser, loginUser2);
			}
			loginUserLog.setLoginUser(loginUser2);
			loginUserLog.setOperation("login");
			loginUserLog.setMethodName("login");
			loginUserLog.setMethodPackage("/manage/login");
			loginUserLog.setOperationTime(new DateStr().toString());
			loginUserLog.setIpAddress(req.getRemoteAddr());
			loginUserLog.setResStatic(res.getStatus()+"");
			loginUserLog.setResText("使用"+loginUser.getLoginName()+"的用户登陆，用户名/密码错误");
//			hibernateUtil.save(loginUserLog);
			mongoDbUtil.insert(loginUserLog);
			throw new Exception("用户或密码错误");
		}
		return ObjectToResult.getResult(manageTokenVo);
	}
	
	/**
	 * 管理员注销
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result logout(Parameter param) throws Exception {
		LoginUser loginUser = param.getManageToken().getLoginUser();
		com.ftoul.mongo.po.LoginUser loginUser2 = null;
		if(loginUser!=null){
			loginUser2 = new com.ftoul.mongo.po.LoginUser();
			BeanUtils.copyProperties(loginUser, loginUser2);
		}
		LoginUserLog loginUserLog = new LoginUserLog(); 
		loginUserLog.setLoginUser(loginUser2);
		loginUserLog.setOperation("logout");
		loginUserLog.setMethodName("logout");
		loginUserLog.setMethodPackage("/manage/login");
		loginUserLog.setOperationTime(new DateStr().toString());
		loginUserLog.setIpAddress(req.getRemoteAddr());
		loginUserLog.setResStatic(res.getStatus()+"");
		loginUserLog.setResText("success");
//		hibernateUtil.save(loginUserLog);
		mongoDbUtil.insert(loginUserLog);
		tokenUtil.uploadManageSecretKey(loginUser);
		return ObjectToResult.getResult(loginUser);
	}
}

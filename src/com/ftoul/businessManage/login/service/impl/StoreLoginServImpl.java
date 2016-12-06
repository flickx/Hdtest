package com.ftoul.businessManage.login.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.businessManage.login.action.CodeAction;
import com.ftoul.businessManage.login.service.StoreLoginServ;
import com.ftoul.common.DateStr;
import com.ftoul.common.MD5;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.user.vo.ManageTokenVo;
import com.ftoul.po.BusinessStoreLogin;
import com.ftoul.po.LoginUserLog;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.token.TokenUtil;
@Service("StoreLoginServImpl")
public class StoreLoginServImpl implements StoreLoginServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private TokenUtil tokenUtil;
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private HttpServletResponse res;
	/**
	 * 商家登陆
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result login(Parameter param) throws Exception {
		BusinessStoreLogin businessStoreLogin = (BusinessStoreLogin) JSONObject.toBean((JSONObject) param.getObj(),BusinessStoreLogin.class);
		//获取绑定的验证码
		Map<String,String> codeMap = CodeAction.userCodeMap;
		String code = codeMap.get("code");
		//获取用户输入的验证码
		String usercode = (String)param.getId();
		if (!code.equalsIgnoreCase(usercode)) {
			throw new Exception("验证码错误");
		}
		String hql = " from BusinessStoreLogin where state = '1' and storeAccount = '" 
				+ businessStoreLogin.getStoreAccount() + "' and password = '"
				+ MD5.getMD5(businessStoreLogin.getPassword()) + "'";
		List<Object> businessStoreLoginList = hibernateUtil.hql(hql);
		ManageTokenVo manageTokenVo = new ManageTokenVo();
		if(businessStoreLoginList != null && businessStoreLoginList.size() > 0){
			BusinessStoreLogin businessStoreLoginDb = (BusinessStoreLogin)businessStoreLoginList.get(0);
			//更新登录时间
			BusinessStoreLogin bLogin =	(BusinessStoreLogin) hibernateUtil.find(BusinessStoreLogin.class,businessStoreLoginDb.getId() );
			bLogin.setLoginIp(req.getRemoteAddr());
			bLogin.setLoginTIme(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			//存储token
			manageTokenVo = tokenUtil.toManageToken(businessStoreLoginDb);
			LoginUserLog loginUserLog = new LoginUserLog(); 
//			loginUserLog.setLoginUser(businessStoreLoginDb);
			loginUserLog.setOperation("login");
			loginUserLog.setMethodName("login");
			loginUserLog.setMethodPackage("/businessManage/login");
			loginUserLog.setOperationTime(new DateStr().toString());
			loginUserLog.setIpAddress(req.getRemoteAddr());
			loginUserLog.setResStatic(res.getStatus()+"");
			loginUserLog.setResText("success");
			hibernateUtil.save(loginUserLog);
			
		}else{
			LoginUserLog loginUserLog = new LoginUserLog(); 
//			loginUserLog.setLoginUser(loginUser);
			loginUserLog.setOperation("login");
			loginUserLog.setMethodName("login");
			loginUserLog.setMethodPackage("/businessManage/login");
			loginUserLog.setOperationTime(new DateStr().toString());
			loginUserLog.setIpAddress(req.getRemoteAddr());
			loginUserLog.setResStatic(res.getStatus()+"");
			loginUserLog.setResText("使用"+businessStoreLogin.getStoreAccount()+"的用户登陆，用户名/密码错误");
			hibernateUtil.save(loginUserLog);
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
		BusinessStoreLogin businessStoreLogin = param.getManageToken().getBusinessStoreLogin();
		LoginUserLog loginUserLog = new LoginUserLog(); 
//		loginUserLog.setLoginUser(businessStoreLogin);
		loginUserLog.setOperation("logout");
		loginUserLog.setMethodName("logout");
		loginUserLog.setMethodPackage("/manage/login");
		loginUserLog.setOperationTime(new DateStr().toString());
		loginUserLog.setIpAddress(req.getRemoteAddr());
		loginUserLog.setResStatic(res.getStatus()+"");
		loginUserLog.setResText("success");
		hibernateUtil.save(loginUserLog);
		tokenUtil.uploadManageSecretKey(businessStoreLogin);
		return ObjectToResult.getResult(businessStoreLogin);
	}
}

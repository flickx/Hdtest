package com.ftoul.pc.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.api.sms.util.MessageUtil;
import com.ftoul.api.sms.util.SmsCodeUtil;
import com.ftoul.businessManage.login.action.CodeAction;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.exption.MyExption;
import com.ftoul.pc.user.action.ImgCodeAction;
import com.ftoul.pc.user.service.PcUserServ;
import com.ftoul.po.MessageVerification;
import com.ftoul.po.User;
import com.ftoul.po.UserToken;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.token.TokenUtil;
import com.ftoul.util.webservice.WebserviceUtil;
import com.ftoul.web.vo.UsersVO;
import com.ftoul.web.webservice.UserService;
@Service("PcUserServImpl")
public class PcUserServImpl implements PcUserServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private SmsCodeUtil smsCodeUtil;
	@Autowired
	private TokenUtil tokenUtil;
	@Autowired
	private HttpServletRequest req;
	@Override
	public Result login(Parameter param) throws Exception {
		UsersVO user = (UsersVO) JSONObject.toBean((JSONObject) param.getObj(),
				UsersVO.class);
		
		if(Integer.parseInt(param.getKey())==2){
			//获取绑定的验证码
			Map<String,String> codeMap = ImgCodeAction.userCodeMap;
			String code = codeMap.get("code");
			//获取用户输入的验证码
			String usercode = user.getImgCode();
			if (!code.equalsIgnoreCase(usercode)) {
				throw new Exception("验证码错误");
			}
		}
		UserService userService = WebserviceUtil.getService();
		String ip = SmsCodeUtil.getLocalIp(req);
		Object res = null;
		String queryStr = " and username =" + user.getUsername()
				+ " and mobil =" + user.getUsername();
		String hql = "from User where state = 1 " + queryStr;
		List<Object> list = hibernateUtil.hql(hql);
		boolean isExists = userService.checkUserExists(user.getUsername());
		if (!isExists) {
			res = "手机号未注册";
			throw new Exception("手机号未注册");
		}
		// 通过Webservice验证用户
		String p2pID = userService.checkUser(user.getUsername(),
				user.getPassword());
		User u = new User();
		u.setDriveId(param.getKey());
		// 密码错误
		if ("".equals(p2pID)) {
			res = "手机或密码错误";
			throw new Exception("手机或密码错误");
		} else {
			if (list.size() == 0) {
				u.setPasswordVersion("1");
				u.setP2pId(p2pID);
				u.setUsername(user.getUsername());
				u.setMobil(user.getUsername());
				u.setCreateTime(new DateStr().toString());
				u.setCreatePerson(user.getUsername());
				u.setState("1");
				u.setSource("p2p");
				u.setIp(ip);
				u.setStatic_("1");
				res = hibernateUtil.save(u);
			} else if (list.size() > 0) {
				u = (User) list.get(0);
				if ("0".equals(u.getStatic_())) {
					res = "用户已被禁用";
					throw new Exception("用户已被禁用");
				}
			}
			UserToken userToken = tokenUtil.toPcToken(u);
			return ObjectToResult.getResult(userToken);
		}

	}
	/**
	 * 发送短信验证码
	 */
	@Override
	public Result sendSmsCode(Parameter param) throws Exception {
		UsersVO user = (UsersVO) JSONObject.toBean((JSONObject) param.getObj(),
				UsersVO.class);
		Object res = null;
		if (user == null) {
			res = "没有传相应的用户信息";
			throw new MyExption("没有传相应的用户信息");
		}
		if (ImgCodeAction.userCodeMap.get("code")==null) {
			res = "没有图形验证码";
			throw new MyExption("没有图形验证码");
		}
		if (!ImgCodeAction.userCodeMap.get("code").equalsIgnoreCase(
				user.getImgCode())) {
			res = "图形验证码错误";
			throw new MyExption("图形验证码错误");
		}
		String ip = SmsCodeUtil.getLocalIp(req);
		System.out.println("请求接收短信的IP: " + ip);
		
		String smsCodeType = user.getSmscodeType();
		int count = smsCodeUtil.getSmsCount(user.getUsername());
		int countIP = smsCodeUtil.getSmsCountIP();
		if (count >= 5) {
			res = "此手机今天可接收短信已超5条上限";
			throw new MyExption("此手机今天可接收短信已超5条上限");
		}
		if (countIP > 9) {
			res = "IP今日接收短信已超上限";
			throw new MyExption("IP今日接收短信已超上限");
		}
		// 生成验证码
		int sort = smsCodeUtil.makeSmsCode(user.getUsername(), smsCodeType);
		// 获取验证码
		MessageVerification m = smsCodeUtil.getMaxSmsCode(user.getUsername(),
				smsCodeType, sort);

		if (m == null) {
			res = "请先获取短信验证码";
			throw new Exception("请先获取短信验证码");
		} else {
			// 发送短信验证码
			MessageUtil.send(user.getUsername(), m.getContent());
		}
		return ObjectToResult.getResult(res);
	}
	/**
	 * 找回密码
	 */
	@Override
	public Result forgetPassword(Parameter param) throws Exception {
		UsersVO user = (UsersVO) JSONObject.toBean((JSONObject) param.getObj(),
				UsersVO.class);
		Object res = null;
		if (ImgCodeAction.userCodeMap.get("code")==null) {
			res = "没有图形验证码";
			throw new MyExption("没有图形验证码");
		}
		if (!ImgCodeAction.userCodeMap.get("code").equalsIgnoreCase(
				user.getImgCode())) {
			res = "图形验证码错误";
			throw new MyExption("图形验证码错误");
		}
		UserService userService = WebserviceUtil.getService();
		boolean isExists = userService.checkUserExists(user.getUsername());
		if (!isExists) {
			throw new Exception("手机号未注册");
		} else {
			String smsCode = user.getSmsCode();
			int count = smsCodeUtil.getSmsCount(user.getUsername());
			if (count > 5) {
				res = "今日接收短信已超过5条上限";
				throw new Exception("今日接收短信已超过5条上限");
			}
			int maxSort = smsCodeUtil.getMaxSmsSort(user.getUsername(),
					user.getSmscodeType());
			MessageVerification m = smsCodeUtil.getMaxSmsCode(
					user.getUsername(), user.getSmscodeType(), maxSort);
			if (m == null || !smsCode.equals(m.getVerificationCode())) {
				res = "短信验证码错误";
				throw new Exception("短信验证码错误");
			}
			String hql = "from User where state = 1 and username = '"
					+ user.getUsername() + "'";
			User userDb = (User) hibernateUtil.hqlFirst(hql);
			if (userDb != null) {
				String passwordVersion = userDb.getPasswordVersion();
				if (passwordVersion == null)
					passwordVersion = "1";
				else {
					passwordVersion = (Integer.parseInt(passwordVersion) + 1)
							+ "";
				}
				userDb.setPasswordVersion(passwordVersion);
				tokenUtil.uploadSecretKey(userDb);
				hibernateUtil.update(userDb);
			}
			// 通过webservice修改密码
			userService.modifyPwd(user.getUsername(), user.getPassword());
		}
		return ObjectToResult.getResult(res);
	}
}
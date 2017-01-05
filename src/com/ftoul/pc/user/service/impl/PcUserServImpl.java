package com.ftoul.pc.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.api.sms.util.SmsCodeUtil;
import com.ftoul.businessManage.login.action.CodeAction;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.pc.user.action.ImgCodeAction;
import com.ftoul.pc.user.service.PcUserServ;
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
			UserToken userToken = tokenUtil.toMobilToken(u);//暂时用手机端token,需要换pc端Token
			return ObjectToResult.getResult(userToken);
		}

	}
}
package com.ftoul.pc.user.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;

import com.ftoul.api.sms.util.MessageUtil;
import com.ftoul.api.sms.util.SmsCodeUtil;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.exption.MyExption;
import com.ftoul.pc.user.action.ImgCodeAction;
import com.ftoul.pc.user.service.PcUserServ;
import com.ftoul.pc.user.vo.PcUserVo;
import com.ftoul.po.MessageVerification;
import com.ftoul.po.User;
import com.ftoul.po.UserToken;
import com.ftoul.util.email.MD5Util;
import com.ftoul.util.email.SendEmail;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.token.TokenUtil;
import com.ftoul.util.webservice.WebserviceUtil;
import com.ftoul.web.manage.user.vo.ResetPasswordVo;
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
	@Override
	public Result getUserInfo(Parameter param) throws Exception {
		User user = (User) this.hibernateUtil.find(User.class, param.getUserToken().getUser().getId());
		PcUserVo pcUserVo = new PcUserVo();
		pcUserVo.setId(user.getId());
		pcUserVo.setUsername(user.getUsername());
		pcUserVo.setNickname(user.getNickname());
		pcUserVo.setSex(user.getSex());
		pcUserVo.setBirth(user.getBirth());
		pcUserVo.setMobil(user.getMobil());
		pcUserVo.setEmail(user.getEmail());
		pcUserVo.setName(user.getName());
		pcUserVo.setActiveState(user.getActiveState());
		return ObjectToResult.getResult(pcUserVo);
	}
	@Override
	public Result saveUser(Parameter param) throws Exception {
		User user = (User) this.hibernateUtil.find(User.class, param.getUserToken().getUser().getId());
		PcUserVo pcUserVo = (PcUserVo) JSONObject.toBean((JSONObject) param.getObj(), PcUserVo.class);
		if(null!= pcUserVo.getNickname()){
			user.setNickname(pcUserVo.getNickname());
		}
		if(null!=pcUserVo.getSex()){
			user.setSex(pcUserVo.getSex());
		}
		if(null!=pcUserVo.getBirth()){
			user.setBirth(pcUserVo.getBirth());
		}
		if(null!=pcUserVo.getMobil()){
			user.setMobil(pcUserVo.getMobil());
		}
		if(null!=pcUserVo.getEmail()){
			user.setEmail(pcUserVo.getEmail());
		}
		if(null!=pcUserVo.getName()){
			user.setName(pcUserVo.getName());
		}
		if(null!=pcUserVo.getCardFront()){
			user.setCardFront(pcUserVo.getCardFront());
		}
		if(null!=pcUserVo.getCardBack()){
			user.setCardBack(pcUserVo.getCardBack());
		}
		Object res;
		res = hibernateUtil.update(user);
		return ObjectToResult.getResult(res);
	}
	@Override
	public Result picUpload(Parameter param,HttpServletRequest request) throws Exception {
		BufferedImage image = null;  
        byte[] imageByte = null;
        String strFileName=param.getId().toString().split(",")[0].split(";")[0].split("/")[1];
    	BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    	imageByte=decoder.decodeBuffer(param.getId().toString().split(",")[1]);
    	// 处理数据
        for(int i=0;i<imageByte.length;i++){
        	if(imageByte[i]<0){
        		imageByte[i]+=256;
        	}
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);  
        image = ImageIO.read(new ByteArrayInputStream(imageByte));  
        bis.close();  
        String picPath = "/upload/img/header/";
        String path = request.getSession().getServletContext().getRealPath("upload/img/header/");
        String picName = UUID.randomUUID()+"."+strFileName;
        String picAddress = picPath+ picName;
        File outputfile = new File(path+picName);
        if(!outputfile.exists()){
        	outputfile.mkdirs();
        }
        System.out.println(image);
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("picAddress", picAddress);
		map.put("picName", picName );
		map.put("hasUpload", true );
        ImageIO.write(image,strFileName, outputfile);  
		return ObjectToResult.getResult(map);
	}
	@Override
	public Result updatePassword(Parameter param) throws Exception {
		ResetPasswordVo resetPasswordVo = (ResetPasswordVo) JSONObject.toBean(
				(JSONObject) param.getObj(), ResetPasswordVo.class);
		User user = (User) this.hibernateUtil.find(User.class, param.getId()
				+ "");
		Result res = new Result();
		if (user == null) {
			res.setResult(0);
			res.setMessage("没有该用户");
			return res;
		}
		UserService userService = WebserviceUtil.getService();
		// 通过Webservice验证用户
		String p2pID = userService.checkUser(user.getUsername(),
				resetPasswordVo.getOldPassword());
		if ("".equals(p2pID)) {
			res.setResult(0);
			res.setMessage("原密码错误");
			return res;
		}

		// 通过webservice修改密码
		userService.modifyPwd(user.getUsername(),
				resetPasswordVo.getNewPassword());
		String hql = "from User where state = 1 and username = '"
				+ user.getUsername() + "'";
		User userDb = (User) hibernateUtil.hqlFirst(hql);
		String passwordVersion = userDb.getPasswordVersion();
		if (passwordVersion == null)
			passwordVersion = "1";
		else {
			passwordVersion = (Integer.parseInt(passwordVersion) + 1) + "";
		}
		tokenUtil.uploadSecretKey(userDb);
		hibernateUtil.update(userDb);
		res.setResult(1);
		return ObjectToResult.getResult(res);
	}
	
	@Override
	public Result getEmailByName(Parameter param) throws Exception {
		PcUserVo pcUserVo = (PcUserVo) JSONObject.toBean((JSONObject) param.getObj(), PcUserVo.class);
		String hql = "from User where state = 1 and email = '"+ pcUserVo.getEmail()+ "'";
		List<Object> obj = (List<Object>) hibernateUtil.hql(hql);
		if(obj.size()>1){
			return ObjectToResult.getResult(1);
		}else{
			return ObjectToResult.getResult(0);
		}
	}
	
	
	@Override
	public Result sendEmail(Parameter param,HttpServletRequest request) throws Exception {
		String userId = param.getUserToken().getUser().getId();
		String email = param.getKey();
		String code = MD5Util.encode2hex(email);
		String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		///邮件的内容  
        StringBuffer sb=new StringBuffer("点击下面链接激活邮箱，请尽快激活！</br>");  
        sb.append("<a href=\""+url+"/pc/user/activeEmail.action?");
        sb.append("userId="+userId);
        sb.append("&code="+code);
        sb.append("\">"+url+"/pc/user/activeEmail.action?");
        sb.append("userId="+userId);
        sb.append("&code="+code);
        sb.append("</a>");
        //发送邮件  
        SendEmail.send(email, sb.toString());  
        User user = new User();
        user.setId(userId);
        user.setValidateCode(code);
        user.setActiveState("0");
        hibernateUtil.update(user);
        Result ret = new Result();
        ret.setResult(1);
		return ret;
	}
	@Override
	public Result activeEmail(String userId,String code) throws Exception {
		User user = (User) hibernateUtil.find(User.class, userId);
		Result ret = new Result();
		if(code.equals(user.getValidateCode())){
			user.setActiveState("1");
			ret.setResult(1);
			return ret;
		}else{
			ret.setResult(0);
			return ret;
		}
	}
	@Override
	public Result validteSmsCode(Parameter param) throws Exception {
		UsersVO user = (UsersVO) JSONObject.toBean((JSONObject) param.getObj(),
				UsersVO.class);
		int maxSort = smsCodeUtil.getMaxSmsSort(user.getUsername(),
				user.getSmscodeType());
		MessageVerification m = smsCodeUtil.getMaxSmsCode(user.getUsername(),
				user.getSmscodeType(), maxSort);
		String smsCode = user.getSmsCode();
		Result result = new Result();
		String hql = "from User where username = '"+user.getUsername()+"'";
		List<Object> list = hibernateUtil.hql(hql);
		if (m == null || !smsCode.equals(m.getVerificationCode())) {
			result.setResult(0);
			result.setMessage("短信验证码错误");
		}else{
			User u = (User) list.get(0);
			result.setResult(1);
			result.setObj(u.getEmail());
		}
		return result;
	}
	
}
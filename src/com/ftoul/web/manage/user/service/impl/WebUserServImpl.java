/**
 * 
 */
package com.ftoul.web.manage.user.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.api.sms.util.MessageUtil;
import com.ftoul.api.sms.util.SmsCodeUtil;
import com.ftoul.businessManage.login.action.CodeAction;
import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.AddressBook;
import com.ftoul.po.MessageVerification;
import com.ftoul.po.User;
import com.ftoul.po.UserToken;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.token.TokenUtil;
import com.ftoul.util.webservice.WebserviceUtil;
import com.ftoul.web.manage.user.service.WebUserServ;
import com.ftoul.web.manage.user.vo.ResetPasswordVo;
import com.ftoul.web.vo.UsersVO;
import com.ftoul.web.webservice.UserService;

/**
 * @author 李丁
 * @date:2016年7月20日 下午4:44:27
 * @类说明 :
 */
@Service("WebUserServImpl")
public class WebUserServImpl implements WebUserServ{
	
	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private SmsCodeUtil smsCodeUtil;
	@Autowired
	private TokenUtil tokenUtil;
	@Autowired
	private HttpServletRequest req;
	@Override
	/**
	 * 注册 新增用户对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	public Result register(Parameter param) throws Exception {
		UsersVO user = (UsersVO) JSONObject.toBean((JSONObject) param.getObj(),UsersVO.class);
		User u =new User();
		Object res=null;
		UserService userService = WebserviceUtil.getService();
		boolean isExists=userService.checkUserExists(user.getUsername());
		String IP= SmsCodeUtil.getLocalIp(req);
		String p2pId="";
		String smsCode=user.getSmsCode();
		int count = smsCodeUtil.getSmsCount(user.getUsername());
		if (count>5) {
			res="今日接收短信已超过5条上限";
			throw new Exception("今日接收短信已超过5条上限");
		}
		int maxSort = smsCodeUtil.getMaxSmsSort(user.getUsername(), user.getSmscodeType());
		MessageVerification m = smsCodeUtil.getMaxSmsCode(user.getUsername(),  user.getSmscodeType(), maxSort);
		if (isExists) {
			res="手机号已注册";
			throw new Exception("手机号已注册");
		}
		if (m==null||!smsCode.equals(m.getVerificationCode())) {
			res="短信验证码错误";
			throw new Exception("短信验证码错误");
		}
		else{
			//新增用户到p2p，带回新增的用户id
			p2pId=userService.saveUser(IP,user.getUsername(),user.getPassword());
			u.setPasswordVersion("1");
			u.setP2pId(p2pId);
			u.setUsername(user.getUsername());
			u.setCreateTime(new DateStr().toString());
			u.setMobil(user.getUsername());
			u.setSource(user.getSource());
			u.setIp(IP);
			u.setStatic_("1");
			u.setState("1");
			res = hibernateUtil.save(u);
		}
		return ObjectToResult.getResult(res);
	}
	/**
	 * 校验用户名是否存在
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result checkUserExists(Parameter param) throws Exception {
		// TODO Auto-generated method stub
		UsersVO user = (UsersVO) JSONObject.toBean((JSONObject) param.getObj(),UsersVO.class);
		UserService userService = WebserviceUtil.getService();
		boolean isExists=userService.checkUserExists(user.getUsername());
		Object res=null;
		String smsCodeType=user.getSmscodeType();
		if (isExists&&smsCodeType.equals("1")) {
			res="手机号已注册";
			throw new Exception("手机号已注册");
		}
		if (!isExists&&smsCodeType.equals("2")) {
			res="手机号未注册";
			throw new Exception("手机号未注册");
		}
		
		return ObjectToResult.getResult(res);
	}
	
	/**
	 * 根据用户ID获取单个用户对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getUserById(Parameter param) throws Exception {
		User user = (User) hibernateUtil.find(User.class, param.getId()+"");
		return ObjectToResult.getResult(user);
	}
	
	/**
	 * 登录，校验用户名，密码
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result login(Parameter param) throws Exception {
		UsersVO user = (UsersVO) JSONObject.toBean((JSONObject) param.getObj(),UsersVO.class);
		UserService userService = WebserviceUtil.getService();
		String ip = SmsCodeUtil.getLocalIp(req);
		Object res=null;
		String queryStr = " and username =" + user.getUsername()+" and mobil =" + user.getUsername();
		String hql = "from User where state = 1 " + queryStr;			
		List<Object> list =hibernateUtil.hql(hql);
		boolean isExists=userService.checkUserExists(user.getUsername());
		if(!isExists){
			res="手机号未注册";
			throw new Exception("手机号未注册");
		}
		//通过Webservice验证用户
		String p2pID=userService.checkUser(user.getUsername(),user.getPassword());
		User u =  new User();
		u.setDriveId(param.getKey());
			//密码错误
		if ("".equals(p2pID)) {
			res="手机或密码错误";
			throw new Exception("手机或密码错误");
		}else {
			if (list.size()==0) {
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
				res=hibernateUtil.save(u);
			}else if (list.size()>0) {
				u = (User) list.get(0);
				if ("0".equals(u.getStatic_())) {
					res="用户已被禁用";
					throw new Exception("用户已被禁用");
				}
			}
			UserToken userToken = tokenUtil.toMobilToken(u);
			return ObjectToResult.getResult(userToken);
//			LoginUserUtil.getLoginUserPrePage();
		}
	}
	/**
	 * 发送短信验证码
	 */
	@Override
	public Result sendSmsCode(Parameter param){
		UsersVO user = (UsersVO) JSONObject.toBean((JSONObject) param.getObj(),UsersVO.class);
		Object res=null;
		String ip = SmsCodeUtil.getLocalIp(req);
		System.out.println("请求接收短信的IP: "+ip);
		try {
			if (!CodeAction.userCodeMap.get("code").equalsIgnoreCase(user.getImgCode())) {
				res="图形验证码错误";
				throw new Exception("图形验证码错误");
			}
			String smsCodeType=user.getSmscodeType();
			int count = smsCodeUtil.getSmsCount(user.getUsername());
			int countIP = smsCodeUtil.getSmsCountIP();
			if (count>5) {
				res="此手机今天可接收短信已超5条上限";
				throw new Exception("此手机今天可接收短信已超5条上限");
			}if(countIP>9){
				res="IP今日接收短信已超上限";
				throw new Exception("IP今日接收短信已超上限");
			}
			//生成验证码
			int sort=smsCodeUtil.makeSmsCode(user.getUsername(),smsCodeType);
			//获取验证码
			MessageVerification m=smsCodeUtil.getMaxSmsCode(user.getUsername(), smsCodeType,sort);
			
			if (m==null) {			
				res="请先获取短信验证码";
				throw new Exception("请先获取短信验证码");
			}else{
			//发送短信验证码
			MessageUtil.send(user.getUsername(), m.getContent());	
			}
		}catch (Exception e) {
			
		}
		return ObjectToResult.getResult(res);
	}
	
	/**
	 * 找回密码
	 */
	@Override
	public Result doResetPassword(Parameter param) throws Exception {
		UsersVO user = (UsersVO) JSONObject.toBean((JSONObject) param.getObj(),UsersVO.class);
		Object res=null;
		UserService userService = WebserviceUtil.getService();
		String smsCode=user.getSmsCode();
		int count = smsCodeUtil.getSmsCount(user.getUsername());
		if (count>5) {
			res="今日接收短信已超过5条上限";
			throw new Exception("今日接收短信已超过5条上限");
		}
		int maxSort=smsCodeUtil.getMaxSmsSort(user.getUsername(), user.getSmscodeType());
		MessageVerification m=smsCodeUtil.getMaxSmsCode(user.getUsername(),  user.getSmscodeType(), maxSort);
		if (m==null||!smsCode.equals(m.getVerificationCode())) {
			res="短信验证码错误";
			throw new Exception("短信验证码错误");
		}
		String hql = "from User where state = 1 and username = '" + user.getUsername()+"'";	
		User userDb = (User) hibernateUtil.hqlFirst(hql);
		String passwordVersion = userDb.getPasswordVersion();
		if(passwordVersion == null)
			passwordVersion = "1";
		else{
			passwordVersion = (Integer.parseInt(passwordVersion)+1)+"";
		}
		userDb.setPasswordVersion(passwordVersion);
		//通过webservice修改密码
		userService.modifyPwd(user.getUsername(),user.getPassword());
		tokenUtil.uploadSecretKey(userDb);
		hibernateUtil.update(userDb);
		return ObjectToResult.getResult(res);
	}
	
	
	/**
	 * 重置密码
	 */
	@Override
	public Result resetPassword(Parameter param) throws Exception {
		
		ResetPasswordVo resetPasswordVo = (ResetPasswordVo) JSONObject.toBean((JSONObject) param.getObj(),ResetPasswordVo.class);
		User user =(User) this.hibernateUtil.find(User.class, param.getId()+"");
		Result res = new Result();
		if(user==null){
			res.setResult(0);
			res.setMessage("没有该用户");
			return res;
		}
		UserService userService = WebserviceUtil.getService();
		//通过Webservice验证用户
		String p2pID=userService.checkUser(user.getUsername(),resetPasswordVo.getOldPassword());
		if ("".equals(p2pID)) {
			res.setResult(0);
			res.setMessage("原密码错误");
			return res;
		}
		
		//通过webservice修改密码
		userService.modifyPwd(user.getUsername(),resetPasswordVo.getNewPassword());
		String hql = "from User where state = 1 and username = '" + user.getUsername()+"'";	
		User userDb = (User) hibernateUtil.hqlFirst(hql);
		String passwordVersion = userDb.getPasswordVersion();
		if(passwordVersion == null)
			passwordVersion = "1";
		else{
			passwordVersion = (Integer.parseInt(passwordVersion)+1)+"";
		}
		tokenUtil.uploadSecretKey(userDb);
		hibernateUtil.update(userDb);
		res.setResult(1);
		return ObjectToResult.getResult(res);
	}
	
	@Override
	public Result getAddressBook(Parameter param) throws Exception {
		List<Object> bookList = param.getObjList();
		Object res = new Object();
		int version = 1;
		System.out.println(param.getKey()+"此用户的通讯录size为："+bookList.size());
		if(bookList!=null&&bookList.size()>0){
			String userMobile = param.getKey();
			if(!Common.isNull(userMobile)){
				
				String sql = "select max(version) from address_book where user_mobile = '"+userMobile+"'";
				List<Object[]> list = hibernateUtil.sql(sql);
				if(list!=null&&list.size()>0){
					String o = String.valueOf(list.get(0));
					if(o!="null"){
						version = Integer.parseInt(o+"")+1;
					}
				}
				for (int i = 0; i < bookList.size(); i++) {
					AddressBook book = new AddressBook();
					HashMap map = (HashMap) bookList.get(i);
					String name = map.get("name")+"";
					String phoneNumber = map.get("phoneNumbers")+"";
					if(!(Common.isNull(name)||Common.isNull(phoneNumber))){
						book.setSeq(map.get("seq")+"");
						book.setDisplayName((String)map.get("displayName"));
						book.setName((String)map.get("name"));
						book.setNickname((String)map.get("nickname"));
						book.setBirthday((String)map.get("birthday"));
						book.setNote((String)map.get("note"));
						book.setPhoneNumbers((String)map.get("phoneNumbers"));
						book.setEmails((String)map.get("emails"));
						book.setAddresses((String)map.get("addresses"));
						book.setIms((String)map.get("ims"));
						book.setOrganizations((String)map.get("organizations"));
						book.setPhotos((String)map.get("photos"));
						book.setCategories((String)map.get("categories"));
						book.setUrls((String)map.get("urls"));
						book.setUserMobile((String)map.get("userMobile"));
						book.setVersion(String.valueOf(version));
						book.setCreateTime(new DateStr().toString());
						res = hibernateUtil.save(book);
						System.out.println(param.getKey()+"此用户的通讯录的seq为："+map.get("seq")+"");
					}
				}
			}	
		}else{
			res = "通讯录为空";
		}
		
		return ObjectToResult.getResult(res);
	}
	
//	/**
//	 * 找回密码 “下一步”
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	@Override
//	public Result toResetPassword(Parameter param) throws Exception {
//		UsersVO user = (UsersVO) JSONObject.toBean((JSONObject) param.getObj(),UsersVO.class);
//		Object res=null;
//		
//		return ObjectToResult.getResult(res);
//	}
	
	private String getRemoteHost(){
	    String ip = req.getHeader("x-forwarded-for");
	    System.out.println("x-forwarded-for:"+ip);
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = req.getHeader("Proxy-Client-IP");
	        System.out.println("Proxy-Client-IP:"+ip);
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = req.getHeader("WL-Proxy-Client-IP");
	        System.out.println("WL-Proxy-Client-IP:"+ip);
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = req.getRemoteAddr();
	        System.out.println("req.getRemoteAddr():"+ip);
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	/**
	 * 保存/更新用户对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveUser(Parameter param) throws Exception {
		User user = (User) JSONObject.toBean((JSONObject) param.getObj(),User.class);
		Object res;
		if(Common.isNull(user.getId())){
			user.setCreateTime(new DateStr().toString());
			user.setStatic_("1");
			user.setState("1");
			res = hibernateUtil.save(user);
		}else{
			User newUser = (User) hibernateUtil.find(User.class, user.getId());
			newUser.setCardId(user.getCardId());
			newUser.setScore(user.getScore());
			newUser.setXp(user.getXp());
			newUser.setEmail(user.getEmail());
			newUser.setName(user.getName());
			newUser.setSex(user.getSex());
			if(user.getNickname()!=null)
				newUser.setNickname(user.getNickname());
			if(user.getBirth()!=null)
				newUser.setBirth(user.getBirth());
				
//			newUser.setCreatePerson(param.getManageToken().getLoginUser().getLoginName());
//			newUser.setCreateTime(new DateStr().toString());
			newUser.setStatic_("1");
			newUser.setState("1");
			newUser.setStatic_("1");
			newUser.setState("1");
			newUser.setModifyTime(new DateStr().toString());
//			if(1 == 1) throw new Exception("cuowu ");
			res = hibernateUtil.update(newUser);
		}
		return ObjectToResult.getResult(res);
	}
}

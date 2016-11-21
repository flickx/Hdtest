package com.ftoul.util.token;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.DateStr;
import com.ftoul.common.DateUtil;
import com.ftoul.common.MD5;
import com.ftoul.common.Result;
import com.ftoul.manage.user.vo.ManageTokenVo;
import com.ftoul.po.BusinessStoreLogin;
import com.ftoul.po.LoginUser;
import com.ftoul.po.User;
import com.ftoul.po.UserToken;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.vo.UsersVO;

/**
 * Token操作相关类
 * @author flick
 *
 */
@Component
public class TokenUtil {
	private final static String mobilKey = "foul*123";
//	private final static String pcKey = "foul*456";
	private final static String manageKey = "foul*789";
	private final static Integer tokenTime = 30;
	private final static Integer manageTokenTime = 30;
	private static Map<String ,UserToken> userTokenMap = new HashMap<String, UserToken>();
	private static Map<String ,ManageTokenVo> manageTokenMap = new HashMap<String, ManageTokenVo>();
	@Autowired
	HibernateUtil hibernateUtil; 

	/**
	 * 获取移动端token(移动端登陆时调用)
	 * @param userVo
	 * @return
	 */
	public UserToken toMobilToken(User user){
		String userId = user.getId();
		String passwordVersion = user.getPasswordVersion();
		//根据用户ID，密码版本号生成密匙（不修改密码的前提下密匙不会改变）
		String secretKey = generaSecretKey(userId, passwordVersion, mobilKey);
		
		String uuid = user.getDriveId();
		if(uuid == null){
			uuid = "";
		}
		//根据相关信息、时间轴及随机数生成动态的token
		String token = generaToken(userId, secretKey, mobilKey,uuid);
		//更新到数据库
		UserToken userTokenDb = new UserToken();
		Object userTokenObj = hibernateUtil.hqlFirst(" from UserToken where user.state = 1 and user.id = '" + userId +"'");
		if(userTokenObj != null){
			userTokenDb = (UserToken) userTokenObj;
			userTokenDb.setSecretKey(secretKey);
			userTokenDb.setMobilToken(token);
			userTokenDb.setUploadTime(new DateStr().toString());
			hibernateUtil.update(userTokenDb);
		}else{
			userTokenDb = new UserToken();
			userTokenDb.setSecretKey(secretKey);
			userTokenDb.setMobilToken(token);
			userTokenDb.setUploadTime(new DateStr().toString());
			userTokenDb.setUser(user);
			hibernateUtil.save(userTokenDb);
		}
		//更新内存总token的值
		userTokenMap.put(userId, userTokenDb);
		return userTokenDb;
	}
	
	/**
	 * 验证移动端token(移动端AOP验证时调用)
	 * @param userVo
	 * @return
	 */
	public Result checkMobilToken(UserToken userToken){
		Result result = new Result();
		if(userToken == null){
			result.setResult(-1);
			result.setMessage("用户未登陆");
		}else{
			String tokenId = userToken.getId();
			String userId = userToken.getUser().getId();
			String mobilToken = userToken.getMobilToken();
			String secretKey = userToken.getSecretKey();
			String uuid = userToken.getDriveId();
			if(uuid == null){
				uuid = "";
			}
			UserToken userTokenFMap = userTokenMap.get(userId);
			String newToken = generaToken(userId, secretKey, mobilKey,uuid);
			if(userTokenFMap != null){
				if(userTokenFMap.getSecretKey().equals(userToken.getSecretKey())){
					String userMapTokenTime = userTokenFMap.getUploadTime();
					Date userMapTokenTimeToDate = DateUtil.stringFormatToDate(userMapTokenTime, "yyyy-MM-dd HH:mm:ss");
//					if((float)(new Date().getTime()-userMapTokenTimeToDate.getTime())/(1000*60) <= tokenTime){
						if(userTokenFMap.getMobilToken().equals(mobilToken)){
							result.setResult(1);
							result.setMessage("验证通过");
							userTokenFMap.setUploadTime(new DateStr().toString());
							userTokenMap.put(userId, userTokenFMap);
						}else{
							System.out.println("time:"+userMapTokenTimeToDate.getTime());
							System.out.println("mobilToken:"+userTokenFMap.getMobilToken());
							System.out.println("userToken:"+mobilToken);
							result.setResult(3);
							result.setMessage("验证失败，请重新登录(103)");
						}
//					}else{
//						if(userTokenFMap.getMobilToken().equals(mobilToken)){
//							result.setResult(2);
//							result.setMessage("token超时，更新token(102)");
//							UserToken userTokenDb = (UserToken) hibernateUtil.find(UserToken.class, tokenId);
//	//						String hql = "from UserToken where id = '"+tokenId+"'";
//	//						Object o = hibernateUtil.hqlFirst(hql);
//	//						UserToken userTokenDb = (UserToken) o;
//							userTokenDb.setMobilToken(newToken);
//							userTokenDb.setUploadTime(new DateStr().toString());
//							hibernateUtil.update(userTokenDb);
//							result.setObj(userTokenDb);
//							userTokenMap.put(userId, userTokenDb);
//							
//						}else{
//							result.setResult(3);
//							result.setMessage("验证失败，请重新登录(203)");
//						}
//					}
				}else{
					result.setResult(4);
					result.setMessage("密码已修改，请重新登录(304)");
				}
			}else{
				UserToken userTokenDb = (UserToken) hibernateUtil.find(UserToken.class, tokenId);
				if(userTokenDb == null){
					result.setResult(4);
					result.setMessage("token超时,请重新登录");
				}else if(userTokenDb.getSecretKey().equals(userToken.getSecretKey())){
					if(userTokenDb.getMobilToken().equals(mobilToken)){
						userTokenDb.setMobilToken(newToken);
						userTokenDb.setUploadTime(new DateStr().toString());
						hibernateUtil.update(userTokenDb);
						result.setResult(1);
						result.setMessage("token超时,更新token(402)");
					}else{
//						userTokenDb.setMobilToken(newToken);
//						userTokenDb.setUploadTime(new DateStr().toString());
//						hibernateUtil.update(userTokenDb);
						result.setResult(3);
						result.setMessage("token验证失败,请重新登陆(403)");
					}
				}else{
					result.setResult(4);
					result.setMessage("密码已修改，请重新登录(404)");
				}
				result.setObj(userTokenDb);
				userTokenMap.put(userId, userTokenDb);
			}
		}
		return result;
	}
	/**
	 * 更新前台密匙
	 */
	public UserToken uploadSecretKey(User user){
		String userId = user.getId();
		UserToken userTokenFMap = userTokenMap.get(userId);
		String newToken = generaSecretKey(userId, user.getPasswordVersion(), mobilKey);
		String newDate = new DateStr().toString();
		String uuid = user.getDriveId();
		if(uuid == null){
			uuid = "";
		}
		if(userTokenFMap != null){
			userTokenFMap.setSecretKey(newToken);
			userTokenFMap.setUploadTime(newDate);
			userTokenMap.put(userId, userTokenFMap);
		}
		Object userTokenObj = hibernateUtil.hqlFirst(" from UserToken where user.state = 1 and user.id = '" + userId +"'");
		UserToken userToken = new UserToken();
		
		if(userTokenObj == null){
			//根据相关信息、时间轴及随机数生成动态的token
			String token = generaToken(userId, newToken, mobilKey, uuid);
			userToken.setMobilToken(token);
			userToken.setSecretKey(newToken);
			userToken.setUploadTime(newDate);
			userToken.setUser(user);
			hibernateUtil.save(userToken);
		}else{
			userToken = (UserToken) userTokenObj;
			userToken.setSecretKey(newToken);
			userToken.setUploadTime(newDate);
			hibernateUtil.update(userToken);
		}
		return userToken;
	}
	
	
	
	
	public UserToken toPcToken(UsersVO usersVo){
		
		
		return null;
	}
	/**
	 * 生成管理后台token
	 * @param loginUser
	 * @return
	 */
	public ManageTokenVo toManageToken(LoginUser loginUser){
		String userId = loginUser.getId();
		String passwordVersion = loginUser.getPassword();
		//根据用户ID，密码跟版本号生成密匙（不修改密码的前提下密匙不会改变）
		String secretKey = generaSecretKey(userId, passwordVersion, manageKey);
		//根据相关信息、时间轴及随机数生成动态的token
		String token = generaToken(userId, secretKey, manageKey,"pc");
		ManageTokenVo manageTokenVo = new ManageTokenVo();
		manageTokenVo.setLoginUser(loginUser);
		manageTokenVo.setSecretKey(secretKey);
		manageTokenVo.setToken(token);
		manageTokenVo.setUploadTime(new DateStr().toString());
		manageTokenMap.put(userId, manageTokenVo);
		return manageTokenVo;
	}
	
	/**
	 * 生成商家管理后台token
	 * @param BusinessStoreLogin
	 * @return
	 */
	public ManageTokenVo toManageToken(BusinessStoreLogin businessStoreLogin){
		String userId = businessStoreLogin.getId();
		String passwordVersion = businessStoreLogin.getPassword();
		//根据用户ID，密码跟版本号生成密匙（不修改密码的前提下密匙不会改变）
		String secretKey = generaSecretKey(userId, passwordVersion, manageKey);
		//根据相关信息、时间轴及随机数生成动态的token
		String token = generaToken(userId, secretKey, manageKey);
		ManageTokenVo manageTokenVo = new ManageTokenVo();
		manageTokenVo.setBusinessStoreLogin(businessStoreLogin);
		manageTokenVo.setSecretKey(secretKey);
		manageTokenVo.setToken(token);
		manageTokenVo.setUploadTime(new DateStr().toString());
		manageTokenMap.put(userId, manageTokenVo);
		return manageTokenVo;
	}
	/**
	 * 验证后台token(移动端AOP验证时调用)
	 * @param manageTokenVo
	 * @return
	 */
	public Result checkManageToken(ManageTokenVo manageTokenVo){
		Result result = new Result();
		if(manageTokenVo == null){
			result.setResult(-1);
			result.setMessage("用户未登陆");
		}else{
			String userId = null;
			if(manageTokenVo.getLoginUser()!=null){
				userId = manageTokenVo.getLoginUser().getId();
			}else
				userId = manageTokenVo.getBusinessStoreLogin().getId();
			String token = manageTokenVo.getToken();
			ManageTokenVo manageTokenVoFMap = manageTokenMap.get(userId);
			if(manageTokenVoFMap != null){
				if(manageTokenVoFMap.getSecretKey().equals(manageTokenVo.getSecretKey())){
					String userMapTokenTime = manageTokenVoFMap.getUploadTime();
					Date userMapTokenTimeToDate = DateUtil.stringFormatToDate(userMapTokenTime, "yyyy-MM-dd HH:mm:ss");
					if((float)(new Date().getTime()-userMapTokenTimeToDate.getTime())/(1000*60) <= manageTokenTime){
						if(manageTokenVoFMap.getToken().equals(token)){
							result.setResult(1);
							result.setMessage("验证通过");
							manageTokenVoFMap.setUploadTime(new DateStr().toString());
							manageTokenMap.put(userId, manageTokenVoFMap);
						}else{
							result.setResult(3);
							result.setMessage("验证失败，请重新登录(103)");
						}
					}else{
						if(manageTokenVoFMap.getToken().equals(token)){
							result.setResult(2);
							result.setMessage("token超时，请重新登录(102)");
							
						}else{
							result.setResult(3);
							result.setMessage("验证失败，请重新登录(203)");
						}
					}
				}else{
					result.setResult(4);
					result.setMessage("密码已修改，请重新登录(304)");
				}
			}else{
				result.setResult(3);
				result.setMessage("登陆失效，请重新登陆，请重新登录(403)");
			}
		}
		return result;
	}
	
	/**
	 * 更新后台密匙（修改密码时调用）
	 */
	public ManageTokenVo uploadManageSecretKey(LoginUser user){
		String userId = user.getId();
		ManageTokenVo manageTokenVoFMap = manageTokenMap.get(userId);
		String newToken = generaToken(userId, user.getPassword(), manageKey,"pc");
		String newDate = new DateStr().toString();
		String newSecretKey = generaSecretKey(userId, user.getPassword(), manageKey);
		if(manageTokenVoFMap != null){
			manageTokenVoFMap.setSecretKey(newSecretKey);
			manageTokenVoFMap.setUploadTime(newDate);
			manageTokenVoFMap.setToken(newToken);
			manageTokenMap.put(userId, manageTokenVoFMap);
		}
		return manageTokenVoFMap;
	}
	/**
	 * 更新商家后台密匙（修改密码时调用）
	 */
	public ManageTokenVo uploadManageSecretKey(BusinessStoreLogin businessStoreLogin){
		String userId = businessStoreLogin.getId();
		ManageTokenVo manageTokenVoFMap = manageTokenMap.get(userId);
		String newToken = generaToken(userId, businessStoreLogin.getPassword(), manageKey);
		String newDate = new DateStr().toString();
		String newSecretKey = generaSecretKey(userId, businessStoreLogin.getPassword(), manageKey);
		if(manageTokenVoFMap != null){
			manageTokenVoFMap.setSecretKey(newSecretKey);
			manageTokenVoFMap.setUploadTime(newDate);
			manageTokenVoFMap.setToken(newToken);
			manageTokenMap.put(userId, manageTokenVoFMap);
		}
		return manageTokenVoFMap;
	}
	/**
	 * 生成密匙
	 * @return
	 */
	public String generaSecretKey(String userId, String passwordVersion,String key){
		return MD5.getMD5(userId + passwordVersion + key);
	}
	
	/**
	 * 生成Token
	 * @return
	 */
	public String generaToken(String userId, String secretKey,String key,String uuid){
		String timeStr =  new Date().getTime() + "";
		String randomNum = new Random().nextInt(99999) + "";
		return MD5.getMD5(userId + secretKey + timeStr + randomNum + key + uuid);
	}
	
	public static void main(String[] args) {
//		UsersVO userVO = new UsersVO(); 
//		userVO.setPassword("123");
//		userVO.setId("5safas5f4s65adf65s1f6s1f6sda51f");
//		System.out.println(new TokenUtil().getMobilToken(userVO));
		Date d1 = DateUtil.stringFormatToDate("2016-08-04 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date d2 = DateUtil.stringFormatToDate("2016-08-04 00:20:02", "yyyy-MM-dd HH:mm:ss");
		System.out.println((float)(d2.getTime()-d1.getTime())/(1000*60)-tokenTime);
	}
}

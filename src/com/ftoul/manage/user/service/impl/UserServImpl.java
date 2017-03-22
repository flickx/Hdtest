package com.ftoul.manage.user.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.Common;
import com.ftoul.common.DateStr;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Page;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.common.StrUtil;
import com.ftoul.manage.user.service.UserServ;
import com.ftoul.manage.user.vo.CommonVo;
import com.ftoul.manage.user.vo.GoodsSalesVo;
import com.ftoul.manage.user.vo.UserManageVo;
import com.ftoul.po.LoginUser;
import com.ftoul.po.OrdersDetail;
import com.ftoul.po.User;
import com.ftoul.po.UserBrowse;
import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.util.token.TokenUtil;
import com.ftoul.util.webservice.WebserviceUtil;
import com.ftoul.web.vo.UsersVO;
import com.ftoul.web.webservice.UserService;

@Service("UserServImpl")
public class UserServImpl implements UserServ {

	@Autowired
	private HibernateUtil hibernateUtil;
	@Autowired
	private TokenUtil tokenUtil;
	/**
	 * 获取用户列表（带分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getUserListPage(Parameter param) throws Exception {
		String queryStr = "";
		if(Common.notNull(param.getKey())){
			queryStr += " and username like '%" + param.getKey() + "%'";
		}
		String hql = "from User where state = '1'" + queryStr;
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
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
	/**
	 * 删除用户信息
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result delUser(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update User set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}
	/**
	 * 根据用户ID获取订单详情
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getOrdersDetailById(Parameter param) throws Exception {
		OrdersDetail ordersDetail = (OrdersDetail) hibernateUtil.find(OrdersDetail.class, param.getId()+"");
		return ObjectToResult.getResult(ordersDetail);
	}
	/**
	 * 获取用户列表
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getUserList(Parameter param) throws Exception {
		String queryStr = param.getWhereStr();
		String hql = "from User where state = '1'" ;
		if (queryStr!= null) {			
			hql = hql + queryStr + param.getOrderBy() ;
		}else{
			hql = hql + param.getOrderBy() ;
		}
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 获取用户浏览记录（带分页）
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getUserBrowseList(Parameter param) throws Exception {
		String hql = "from UserBrowse where state = '1' and user.id = '" + param.getId() +"' " + param.getWhereStr() + param.getOrderBy() ;
		Page page = hibernateUtil.hqlPage(null, hql, param.getPageNum(), param.getPageSize());
		return ObjectToResult.getResult(page);
	}
	/**
	 * 保存/更新用户浏览记录
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveUserBrowse(Parameter param) throws Exception {
		UserBrowse userBrowse = (UserBrowse) JSONObject.toBean((JSONObject) param.getObj(),UserBrowse.class);
		Object res;
		if(Common.isNull(userBrowse.getId())){
			User user = (User)hibernateUtil.find(User.class, param.getParentId()+"");
			userBrowse.setUser(user);
			userBrowse.setBrowseTime(new DateStr().toString());
			userBrowse.setState("1");
			res = hibernateUtil.save(userBrowse);
		}else{
			User user = (User)hibernateUtil.find(User.class, param.getParentId()+"");
			userBrowse.setUser(user);
			userBrowse.setBrowseTime(new DateStr().toString());
			userBrowse.setState("1");
			res = hibernateUtil.update(userBrowse);
		}
		return ObjectToResult.getResult(res);
	}
	/**
	 * 删除用户浏览记录
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result delUserBrowse(Parameter param) throws Exception {
		
		Integer num = hibernateUtil.execHql("update UserBrowse set state = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}

	/**
	 * 停用
	 * @param param Parameter对象
	 * @return返回结果（前台用Result对象）
	 */
	@Override
	public Result stopUser(Parameter param) throws Exception {
		Integer num = hibernateUtil.execHql("update User set static_ = '0' where id in ("+StrUtil.getIds(param.getId())+")");
		return ObjectToResult.getResult(num);
	}

	@Override
	public Result doResetPassword(Parameter param) throws Exception {
		UserService userService = WebserviceUtil.getService();
		Object res=null;
		UsersVO user = (UsersVO) JSONObject.toBean((JSONObject) param.getObj(),UsersVO.class);
		String hql = "from User where state = 1 and id = '" + param.getId()+"'";	
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

	@Override
	public Result getDeals(Parameter parameter) throws Exception {
		UserManageVo userManageVo =  new UserManageVo();
		String userId = parameter.getManageToken().getLoginUser().getId();
		LoginUser loginUser = (LoginUser)hibernateUtil.find(LoginUser.class, userId);
		userManageVo.setUsername(loginUser.getLoginName());
		String userSql = "SELECT operation_time,ip_address FROM login_user_log"
					+" WHERE login_user_id = '"+loginUser.getId()+"'"
					+" AND operation = 'login' and res_text = 'success'"
					+" order by operation_time desc limit 1";
		List<Object[]> userList = hibernateUtil.sql(userSql);
		for (int i = 0; i < userList.size(); i++) {
			Object[] obj = userList.get(i);
			userManageVo.setLastLoginTime(obj[0].toString());
			userManageVo.setLastLoginIp(obj[1].toString());
		}
		String countUserSql = "select count(id) from user where state  = 1";
		List<Object[]> userCount = hibernateUtil.sql(countUserSql);
		Object userAmount = (Object)userCount.get(0);
		userManageVo.setUserAmount(userAmount.toString());
		
		String goodsSql = "select count(id) from goods where state  = 1 and grounding = 1";
		List<Object[]> goodsCount = hibernateUtil.sql(goodsSql);
		Object goodsAmount = (Object)goodsCount.get(0);
		userManageVo.setGoodsAmount(goodsAmount.toString());
		
		String shopSql = "select count(id) from business_store where state  = 1";
		List<Object[]> shopCount = hibernateUtil.sql(shopSql);
		Object shopAmount = (Object)shopCount.get(0);
		userManageVo.setShopAmount(shopAmount.toString());
		
		String orderSql = "select sum(order_price) from orders where order_static = 6";
		List<Object[]> orderCount = hibernateUtil.sql(orderSql);
		Object orderAmount = (Object)orderCount.get(0);
		DecimalFormat df = new DecimalFormat("#.00"); 
		String order =  df.format(Double.parseDouble(orderAmount.toString()));
		userManageVo.setOrderAmount(order);
		
		
		String deliveryOrderSql = "select count(id) from orders where order_static = '3' and state = 1";
		List<Object[]> deliveryOrderCount = hibernateUtil.sql(deliveryOrderSql);
		Object deliveryOrder = (Object)deliveryOrderCount.get(0);
		userManageVo.setDeliveryOrder(deliveryOrder.toString());
		
		String payOrderSql = "select count(id) from orders where order_static = '1' and state = 1";
		List<Object[]> payOrderCount = hibernateUtil.sql(payOrderSql);
		Object payOrder = (Object)payOrderCount.get(0);
		userManageVo.setPayOrder(payOrder.toString());
		
		String confirmOrderSql = "select count(id) from orders where order_static = '4' and state = 1";
		List<Object[]> confirmOrderCount = hibernateUtil.sql(confirmOrderSql);
		Object confirmOrder = (Object)confirmOrderCount.get(0);
		userManageVo.setConfirmOrder(confirmOrder.toString());
		
		
		String refundOrderSql = "select count(id) from after_schedule where type = 1";
		List<Object[]> refundOrderCount = hibernateUtil.sql(refundOrderSql);
		Object refundOrder = (Object)refundOrderCount.get(0);
		userManageVo.setRefundOrder(refundOrder.toString());
		
		String rexchangeOrderSql = "select count(id) from after_schedule where type = 2";
		List<Object[]> exchangeOrderCount = hibernateUtil.sql(rexchangeOrderSql);
		Object exchangeOrder = (Object)exchangeOrderCount.get(0);
		userManageVo.setExchangeOrder(exchangeOrder.toString());
				
		return ObjectToResult.getResult(userManageVo);
	}

	@Override
	public Result getGoodsSales(Parameter parameter) throws Exception {
		String sql = " select DATE_FORMAT(create_time,'%Y%m')weeks,sum(order_price),count(id) from orders where order_static = 6"
				+" and create_time > DATE_SUB(CURDATE(),INTERVAL dayofyear(now())-1 DAY)"
				+" group by weeks order by weeks";
		List<Object[]> list = hibernateUtil.sql(sql);
		List<GoodsSalesVo> goodsSalesList = new ArrayList<GoodsSalesVo>();
		for (int i = 0; i < list.size(); i++) {
			GoodsSalesVo goodsSalesVo = new GoodsSalesVo();
			Object[] obj = list.get(i);
			if(obj[0]!=null){
				goodsSalesVo.setMonth(obj[0].toString());
			}
			if(obj[1]!=null){
				goodsSalesVo.setPrice(Double.parseDouble(obj[1].toString()));
			}
			if(obj[2]!=null){
				goodsSalesVo.setAmount(Integer.parseInt(obj[2].toString()));
			}
			goodsSalesList.add(goodsSalesVo);
		}
		return ObjectToResult.getResult(goodsSalesList);
	}

	@Override
	public Result getRegisterUser(Parameter parameter) throws Exception {
		String sql = "select DATE_FORMAT(create_time,'%Y%m%d')weeks,count(*) from `user`  where state = 1" 
					+" and create_time > DATE_ADD(curdate(),interval -day(curdate())+1 day) group by weeks order by weeks";
		List<Object[]> list = hibernateUtil.sql(sql);
		List<CommonVo> registerUserList = new ArrayList<CommonVo>();
		for (int i = 0; i < list.size(); i++){
			CommonVo commonVo = new CommonVo();
			Object[] obj = list.get(i);
			if(obj[0]!=null){
				commonVo.setName(obj[0].toString());
			}
			if(obj[1]!=null){
				commonVo.setAmount(Integer.parseInt(obj[1].toString()));
			}
			registerUserList.add(commonVo);
		}
		return ObjectToResult.getResult(registerUserList);
	}

	@Override
	public Result getPopularGoods(Parameter parameter) throws Exception {
		String sql = "select title,sale_sum from goods  where state = 1 order by sale_sum desc limit 20";
		List<Object[]> list = hibernateUtil.sql(sql);
		List<CommonVo> popularGoodsList = new ArrayList<CommonVo>();
		for (int i = 0; i < list.size(); i++){
			CommonVo commonVo = new CommonVo();
			Object[] obj = list.get(i);
			if(obj[0]!=null){
				commonVo.setName(obj[0].toString());
			}
			if(obj[1]!=null){
				commonVo.setAmount(Integer.parseInt(obj[1].toString()));
			}
			popularGoodsList.add(commonVo);
		}
		return ObjectToResult.getResult(popularGoodsList);
	}
	
}

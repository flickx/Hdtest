package com.ftoul.util.score;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.DateStr;
import com.ftoul.po.GoodsComment;
import com.ftoul.po.User;
import com.ftoul.po.UserGrowth;
import com.ftoul.po.UserSroce;
import com.ftoul.util.hibernate.HibernateUtil;

/**
 * 用户积分工具类
 * @author yzw
 *
 */
@Component
public class ScoreUtil {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 注册登陆后赠送成长值
	 * @param username
	 */
	public void giveScore(String username){
		List<Object> list = hibernateUtil.hql("from UserGrowth");
		List<Object> obj =  hibernateUtil.hql("from User where username = '"+username+"'");
		User user = (User) obj.get(0);
		//注册成功后首次登陆
		if(!"1".equals(user.getFirstLogin())){
			UserSroce sroce = new UserSroce();
			for (int i = 0; i < list.size(); i++){
				UserGrowth userGrowth = (UserGrowth)list.get(0);
				if("1".equals(userGrowth.getFirstLoginAble())){
					sroce.setUser(user);
					sroce.setScore(userGrowth.getFirstLogin());
					sroce.setTotalScore(userGrowth.getFirstLogin());
					sroce.setDescription("首次登陆成功赠送");
					sroce.setState("1");
					sroce.setCreateTime(new DateStr().toString());
					hibernateUtil.save(sroce);
					user.setFirstLogin("1");
					user.setScore(Integer.parseInt(userGrowth.getFirstLogin()));
					hibernateUtil.update(user);
				}
			}
		}
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(currentTime);
		//每天手动登陆系统赠送
		if(user.getLastLoginTime()!=null){
			String lastLoginTime = user.getLastLoginTime().substring(0,10);
			if(!date.equals(lastLoginTime)){
				for (int i = 0; i < list.size(); i++){
					UserSroce sroce = new UserSroce();
					UserGrowth userGrowth = (UserGrowth)list.get(0);
					if("1".equals(userGrowth.getManualLoginAble())){
						sroce.setUser(user);
						sroce.setScore(userGrowth.getManualLogin());
						Integer total = user.getScore()+Integer.parseInt(userGrowth.getManualLogin());
						sroce.setTotalScore(total.toString());
						sroce.setDescription("每天手动登陆系统赠送");
						sroce.setState("1");
						sroce.setCreateTime(new DateStr().toString());
						hibernateUtil.save(sroce);
						user.setScore(total);
						hibernateUtil.update(user);
					}
				}
			}
		}else{
			for (int i = 0; i < list.size(); i++){
				UserSroce sroce = new UserSroce();
				UserGrowth userGrowth = (UserGrowth)list.get(0);
				if("1".equals(userGrowth.getManualLoginAble())){
					sroce.setUser(user);
					sroce.setScore(userGrowth.getManualLogin());
					Integer total = user.getScore()+Integer.parseInt(userGrowth.getManualLogin());
					sroce.setTotalScore(total.toString());
					sroce.setDescription("每天手动登陆系统赠送");
					sroce.setState("1");
					sroce.setCreateTime(new DateStr().toString());
					hibernateUtil.save(sroce);
					user.setScore(total);
					hibernateUtil.update(user);
				}
			}
		}
		//是否生日当天登陆
	    if(user.getBirth()!=null && !"".equals(user.getBirth())){
	    	if(date.substring(5).equals(user.getBirth().substring(5)) && (user.getBirthState()==null || "0".equals(user.getBirthState()))){
				for (int i = 0; i < list.size(); i++){
					UserSroce sroce = new UserSroce();
					UserGrowth userGrowth = (UserGrowth)list.get(0);
					if("1".equals(userGrowth.getBirthAble())){
						sroce.setUser(user);
						sroce.setScore(userGrowth.getBirth());
						Integer total = user.getScore()+Integer.parseInt(userGrowth.getBirth());
						sroce.setTotalScore(total.toString());
						sroce.setDescription("生日当天登陆赠送");
						sroce.setState("1");
						sroce.setCreateTime(new DateStr().toString());
						hibernateUtil.save(sroce);
						user.setScore(total);
						user.setBirthState("1");
						hibernateUtil.update(user);
					}
				}
			}else if(!date.substring(5).equals(user.getBirth().substring(5))){
				user.setBirthState("0");
				hibernateUtil.update(user);
			}
	    }
	    user.setLastLoginTime(new DateStr().toString());
	    hibernateUtil.update(user);
	}
	
	/**
	 * 订单完成后赠送成长值
	 * @param price
	 */
	public void giveScoreByOrders(String userId,String price){
		User user = (User)hibernateUtil.find(User.class, userId);
		List<Object> list = hibernateUtil.hql("from UserGrowth");
		for (int i = 0; i < list.size(); i++){
			UserSroce sroce = new UserSroce();
			UserGrowth userGrowth = (UserGrowth)list.get(0);
			if("1".equals(userGrowth.getOrderFinishAble())){
				sroce.setUser(user);
				Double orderFinish = Double.parseDouble(userGrowth.getOrderFinish());
				Double s = orderFinish*Double.parseDouble(price);
				BigDecimal score = new BigDecimal(s).setScale(0, BigDecimal.ROUND_HALF_UP);
				sroce.setScore(score.intValue()+"");
				if(user.getScore()==null){
					user.setScore(0);
				}
				int total = user.getScore()+score.intValue();
				sroce.setTotalScore(total+"");
				sroce.setDescription("订单签收后赠送");
				sroce.setState("1");
				sroce.setCreateTime(new DateStr().toString());
				hibernateUtil.save(sroce);
				user.setScore(total);
				hibernateUtil.update(user);
			}
		}
	}
	
	/**
	 * 评论完成后赠送成长值
	 */
	public void giveScoreByComment(String commentId){
		GoodsComment comment = (GoodsComment)hibernateUtil.find(GoodsComment.class, commentId);
		User user = (User)hibernateUtil.find(User.class, comment.getOrdersDetail().getOrders().getUser().getId());
		List<Object> list = hibernateUtil.hql("from UserGrowth");
		for (int i = 0; i < list.size(); i++){
			UserSroce sroce = new UserSroce();
			UserGrowth userGrowth = (UserGrowth)list.get(0);
			if("1".equals(userGrowth.getCommentAble())){
				sroce.setUser(user);
				sroce.setScore(userGrowth.getComment());
				if(user.getScore()==null){
					user.setScore(0);
				}
				Integer total = user.getScore()+Integer.parseInt(userGrowth.getComment());
				sroce.setTotalScore(total.toString());
				sroce.setDescription("商品评价后赠送");
				sroce.setState("1");
				sroce.setCreateTime(new DateStr().toString());
				hibernateUtil.save(sroce);
				user.setScore(total);
				hibernateUtil.update(user);
			}
		}
	}
	/**
	 * @param 退货减去相应成长值
	 */
	public void loseScore(String userId){
		User user = (User)hibernateUtil.find(User.class, userId);
		List<Object> list = hibernateUtil.hql("from UserGrowth");
		for (int i = 0; i < list.size(); i++){
			UserSroce sroce = new UserSroce();
			UserGrowth userGrowth = (UserGrowth)list.get(0);
			if("1".equals(userGrowth.getOrderFinishAble())){
				sroce.setUser(user);
				Integer amount = -1*Integer.parseInt(userGrowth.getOrderFinish());
				sroce.setScore(amount.toString());
				if(user.getScore()==null){
					user.setScore(0);
				}
				Integer total = user.getScore()-Integer.parseInt(userGrowth.getOrderFinish());
				sroce.setTotalScore(total.toString());
				sroce.setDescription("退货扣去的成长值");
				sroce.setState("1");
				sroce.setCreateTime(new DateStr().toString());
				hibernateUtil.save(sroce);
				user.setScore(total);
				hibernateUtil.update(user);
			}
		}
	}
}

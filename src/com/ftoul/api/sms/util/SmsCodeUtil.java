/**
 * 
 */
package com.ftoul.api.sms.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.DateUtil;
import com.ftoul.po.MessageSend;
import com.ftoul.po.MessageVerification;
import com.ftoul.util.hibernate.HibernateUtil;

/**
 * @author 李丁
 * @date:2016年7月28日 下午6:05:53
 * @类说明 :
 */
@Service("SmsCodeUtil")
public class SmsCodeUtil {
	@Autowired
	HibernateUtil hibernateUtil;
	@Autowired
	private HttpServletRequest req;
	/**
	 * 根据用户手机号码和验证码类型获取验证码最大排序
	 */
	public int getMaxSmsSort(String mobile,String messageType)throws Exception{
		int maxSmsSort=0;
		String queryStr = " and mobile ='" + mobile + "' and messageType='"+messageType+"' order by sort desc";
		String hql = "from MessageVerification where state = '1'" + queryStr;			
		List<Object> list =hibernateUtil.hql(hql);
		List<MessageVerification> messageVerificationList=new ArrayList<MessageVerification>();
		if (list.size()>0) {
		for (Object o : list) {
			messageVerificationList.add((MessageVerification)o);
		}
		maxSmsSort=messageVerificationList.get(0).getSort();
		}
		return maxSmsSort;
	}
	/**
	 * 生成验证码并保存到数据库
	 * @param mobile
	 * @return 1:成功;0：失败
	 */
	public int makeSmsCode(String mobile,String messageType)throws Exception{
		MessageVerification messageVerification = new MessageVerification();
		MessageSend messageSend=new MessageSend();
		String createTime = DateUtil.dateFormatToString(new Date(), "yyyy-MM-dd HH:mm:ss");
		String code = getRandom();
		messageVerification.setVerificationCode(code);
		int maxSmsSort=getMaxSmsSort(mobile, messageType);
		int sort=1;
		if (maxSmsSort!=0) {
			sort =maxSmsSort+1;
		}
		messageVerification.setSort(sort);
		String content=""; 
		if (messageType.equals("1")) {
			content = "【他她乐】亲爱的会员：您的手机注册验证码为"+code+"，如非本人操作，请致电客服0731-82208568";
		}if (messageType.equals("2")) {
			content = "【他她乐】亲爱的会员：您的手机找回密码验证码为"+code+"，如非本人操作，请致电客服0731-82208568";
		}
		messageVerification.setIp(req.getRemoteAddr());
		messageVerification.setMobile(mobile);
		messageVerification.setContent(content);
		messageVerification.setState("1");
		messageVerification.setMessageType(messageType);
		messageVerification.setCreatePerson(mobile);
		messageVerification.setCreateTime(createTime);
		Object o=hibernateUtil.save(messageVerification);
		
		messageSend.setMsg(content);
		messageSend.setSendTime(createTime);
		hibernateUtil.save(messageSend);
		
		if (o==null) {
			throw new Exception("新增失败");
		}
		return sort;
		
	}
	/**
	 * 根据用户手机号码，验证码类型，序号获取最新验证码
	 */
	public MessageVerification getMaxSmsCode(String mobile,String messageType,int sort)throws Exception{
		String queryStr = " and mobile ='" + mobile + "' and messageType='"+messageType+"'and sort='"+sort+"'";
		String hql = "from MessageVerification where state = '1'" + queryStr;			
		MessageVerification m =(MessageVerification)hibernateUtil.hqlFirst(hql);
		return m;
	}
	public String getRandom() {
		//验证码
        String code = "";
        for (int i = 0; i < 6; i++) {
            code = code + (int)(Math.random() * 9);
        }
        return code;
	}
	/**
	 * 获取用户当天收到的短信条数
	 * @param args
	 */
	public int getSmsCount(String mobile){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String currentDay = sf.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, 1);
        String nextDay = sf.format(c.getTime());
		String queryStr = " and mobile ='" + mobile+"' and createTime < '"+ nextDay + "' and createTime > '" + currentDay +"'";
		String hql = "from MessageVerification where state = '1'" + queryStr;	
		List<Object> list = hibernateUtil.hql(hql);
		//System.out.println("手机号"+mobile+"今日已经接收"+list.size()+"条短信");
		return list.size();
	}

	public int getSmsCountIP(){
		String hql = "from MessageVerification where state = '1' and ip='"+req.getRemoteAddr()+"'";	
		List<Object> list = hibernateUtil.hql(hql);
		return list.size();
	}
}

package com.ftoul.util.afterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.DateStr;
import com.ftoul.common.Parameter;
import com.ftoul.po.AfterOpLog;
import com.ftoul.po.AfterSchedule;
import com.ftoul.util.hibernate.HibernateUtil;

@Component
public class AfterServiceUtil {
	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 记录售后申请操作日志
	 * @param param
	 * @param after
	 * @param msg
	 */
	public void saveAfterOpLog(Parameter param,String msg){
		AfterSchedule after = (AfterSchedule) hibernateUtil.find(AfterSchedule.class, param.getId()+"");
		AfterOpLog log = new AfterOpLog();
		log.setAfterSchedule(after);
//		log.setUserId(param.getManageToken().getLoginUser().getLoginName());
		log.setMsg(msg);
		log.setState("1");
		log.setCreatePerson(param.getManageToken().getLoginUser().getLoginName());
		log.setCreateTime(new DateStr().toString());
		hibernateUtil.save(log);
	}
	
	/**
	 * 记录售后申请操作日志
	 * @param param
	 * @param after
	 * @param msg
	 */
	public void saveWebAfterOpLog(Parameter param,String msg){
		AfterSchedule after = (AfterSchedule) hibernateUtil.find(AfterSchedule.class, param.getId()+"");
		AfterOpLog log = new AfterOpLog();
		log.setAfterSchedule(after);
		log.setUserId(param.getUserToken().getUser().getUsername());
		log.setMsg(msg);
		log.setState("1");
		log.setCreatePerson(param.getUserToken().getUser().getUsername());
		log.setCreateTime(new DateStr().toString());
		hibernateUtil.save(log);
	}

}

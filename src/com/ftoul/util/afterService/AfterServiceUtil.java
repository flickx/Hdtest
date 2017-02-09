package com.ftoul.util.afterService;

import java.util.Date;
import java.util.Random;

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
		log.setScheduleStatic(after.getScheduleStatic());
		if(param.getManageToken().getBusinessStoreLogin()==null){
			log.setUserId(param.getManageToken().getLoginUser().getLoginName());
			log.setCreatePerson(param.getManageToken().getLoginUser().getLoginName());
		}else{
			log.setUserId(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
			log.setCreatePerson(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
		}
		log.setMsg(msg);
		log.setState("1");
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
		log.setScheduleStatic(after.getScheduleStatic());
		log.setUserId(param.getUserToken().getUser().getUsername());
		log.setMsg(msg);
		log.setState("1");
		log.setCreatePerson(param.getUserToken().getUser().getUsername());
		log.setCreateTime(new DateStr().toString());
		hibernateUtil.save(log);
	}
	
	/**
	 * 获取售后申请状态
	 * @param orderState
	 * @return
	 */
	public String getAfterState(String afterState){
		String state = null;
		if("1".equals(afterState)){
			state = "申请售后";
		}else if("2".equals(afterState)){
			state = "同意换货";
		}else if("3".equals(afterState)){
			state = "拒绝换货";
		}else if("4".equals(afterState)){
			state = "同意退款";
		}else if("5".equals(afterState)){
			state = "拒绝退款";
		}else if("6".equals(afterState)){
			state = "同意退款/不退货";
		}else if("7".equals(afterState)){
			state = "拒绝退款/不退货";
		}else if("8".equals(afterState)){
			state = "买家已发货";
		}else if("9".equals(afterState)){
			state = "商家已收货";
		}else if("10".equals(afterState)){
			state = "商家已发货";
		}else if("11".equals(afterState)){
			state = "买家已收货";
		}else if("12".equals(afterState)){
			state = "商家已退款并完成售后服务";
		}else if("13".equals(afterState)){
			state = "取消申请";
		}
		return state;
	}
	
	/**
	 * 获取售后申请类型
	 * @param orderState
	 * @return
	 */
	public String getAfterType(String afterState){
		String state = null;
		if("1".equals(afterState)){
			state = "换货";
		}else if("2".equals(afterState)){
			state = "退款";
		}else if("3".equals(afterState)){
			state = "退款/不退货";
		}
		return state;
	}
	/**
	 * 重命名文件
	 * @param fileName
	 * @return
	 */
	public String reName(String fileName){
		int i = fileName.lastIndexOf(".");
        String str = fileName.substring(i);
        return new Date().getTime()+""+ new Random().nextInt(99999999) +str;
	}
}

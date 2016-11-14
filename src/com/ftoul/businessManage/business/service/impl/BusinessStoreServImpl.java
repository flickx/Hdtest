package com.ftoul.businessManage.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.businessManage.business.service.BusinessStoreServ;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.util.hibernate.HibernateUtil;
/**
 * 
 * @author wenyujie
 *
 */
@Service("BusinessStoreServImpl")
public class BusinessStoreServImpl implements BusinessStoreServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	/**
	 * 根据登录token获取商家信息对象
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result getBusinessPage(Parameter param) throws Exception {
		String sql= "select bsl.*,bs.*,b.*,bb.*,bm.*,bk.*,bsmc.*,bss.*  from business_Store_login as bsl "
				+ " left join business_store as bs on bsl.store_id=bs.id left join business as b on bs.business_id=b.id "
				+ " left join business_base as bb on b.base_id=bb.id left join business_manage as bm on b.manage_id=bm.id "
				+ " left join business_bank as bk on b.bank_id = bk.id left join business_store_manage_category as bsmc on bsmc.store_id = bs.id"
				+ " left join business_store_summary as bss on bss.store_id=bs.id"
				+ " where b.state=1 and bsl.id="+param.getManageToken().getBusinessStoreLogin().getId();
		List<Object[]> list = hibernateUtil.sql(sql);
//		String hql="from BusinessStoreLogin where businessStore.business.state=1 and id="+param.getManageToken().getBusinessStoreLogin().getId();
//		List<Object> list = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(list);
	}
}

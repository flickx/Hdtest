package com.ftoul.businessManage.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.businessManage.business.service.BusinessStoreServ;
import com.ftoul.businessManage.business.vo.BusinessStoreVo;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.BusinessStoreLogin;
import com.ftoul.po.BusinessStoreSummary;
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
//		String sql= "select bsl.store_account as storeAccount,bs.pic as pic,bs.store_name as storeName,bs.store_duration as storeDuration,"
//				+ "b.id as id,b.operate_id as operateId,b.operate_time as operateTime,b.create_id as createId,b.create_time as createTime,"
//				+ "b.state as state,bb.company_address as companyAddress,bb.company_name as companyName,bb.registered_capital as registeredCapital,"
//				+ "bb.linkman_name as linkmanName,bb.linkman_number as linkmanNumber,bb.email as email,bm.legal_person as legalPerson,"
//				+ "bm.id_card as idCard,bm.id_card_face_img as idCardFaceImg,bm.id_card_con_img as idCardConImg,bm.business_licence_number as businessLicenceNumber,"
//				+ "bm.establish_time as establishTime,bm.manage_scope as manageScope,bm.business_licence_img as businessLicenceImg,bm.busines_slicence_date as businesSlicenceDate,"
//				+ "bk.bank_account_name as bankAccountName,bk.bank_account as bankAccount,bk.bank_name as bankName,"
//				+ "bk.bank_account_img as bankAccountImg,bsmc.first_category as firstCategory,bsmc.two_category as twoCategory,bsmc.three_category as threeCategory,"
//				+ "bss.summary as summary, bsl.id as businessStoreLoginId,bs.id as businessStoreId,b.id as businessId,"
//				+ "bb.id as businessBaseId,bk.id as businessBankId,bsmc.id as businessStoreManageCategoryId,"
//				+ "bss.id as businessStoreSummaryId from business_Store_login as bsl "
//				+ " left join business_store as bs on bsl.store_id=bs.id left join business as b on bs.business_id=b.id "
//				+ " left join business_base as bb on b.base_id=bb.id left join business_manage as bm on b.manage_id=bm.id "
//				+ " left join business_bank as bk on b.bank_id = bk.id left join business_store_manage_category as bsmc on bsmc.store_id = bs.id"
//				+ " left join business_store_summary as bss on bss.store_id=bs.id"
//				+ " where b.state=1 and bsl.id='"+param.getManageToken().getBusinessStoreLogin().getId()+"'";
//		List<Object[]> list = hibernateUtil.sql(sql);
//		BusinessStoreVo businessStoreVo=new BusinessStoreVo();
//		if(list.size()>0){
//			Object[] obj = (Object[])list.get(0);
//			businessStoreVo.setStoreAccount(obj[0].toString()+"");
//			businessStoreVo.setPic(obj[1].toString()+"");
//			businessStoreVo.setStoreName(obj[2].toString()+"");
//			businessStoreVo.setStoreDuration(obj[3].toString()+"");
//			businessStoreVo.setId(obj[4].toString()+"");
//			businessStoreVo.setOperateId(obj[5].toString()+"");
//			businessStoreVo.setOperateTime(obj[6].toString()+"");
//			businessStoreVo.setCreateId(obj[7].toString()+"");
//			businessStoreVo.setCreateTime(obj[8].toString()+"");
//			businessStoreVo.setState(obj[9].toString()+"");
//			businessStoreVo.setCompanyAddress(obj[10].toString()+"");
//			businessStoreVo.setCompanyName(obj[11].toString()+"");
//			businessStoreVo.setRegisteredCapital(obj[12].toString()+"");
//			businessStoreVo.setLinkmanName(obj[13].toString()+"");
//			businessStoreVo.setLinkmanNumber(obj[14].toString()+"");
//			businessStoreVo.setEmail(obj[15].toString()+"");
//			businessStoreVo.setLegalPerson(obj[16].toString()+"");
//			businessStoreVo.setIdCard(obj[17].toString()+"");
//			businessStoreVo.setIdCardFaceImg(obj[18].toString()+"");
//			businessStoreVo.setIdCardConImg(obj[19].toString()+"");
//			businessStoreVo.setBusinessLicenceNumber(obj[20].toString()+"");
//			businessStoreVo.setEstablishTime(obj[21].toString()+"");
//			businessStoreVo.setManageScope(obj[22].toString()+"");
//			businessStoreVo.setBusinessLicenceImg(obj[23].toString()+"");
//			businessStoreVo.setBusinesSlicenceDate(obj[24].toString()+"");
//			businessStoreVo.setBankAccountName(obj[25].toString()+"");
//			businessStoreVo.setBankAccount(obj[26].toString()+"");
//			businessStoreVo.setBankName(obj[27].toString()+"");
//			businessStoreVo.setBankAccountImg(obj[28].toString()+"");
//			businessStoreVo.setFirstCategory(obj[29].toString()+"");
//			businessStoreVo.setTwoCategory(obj[30].toString()+"");
//			businessStoreVo.setThreeCategory(obj[31].toString()+"");
//			businessStoreVo.setSummary(obj[32].toString()+"");
//			businessStoreVo.setBusinessStoreLoginId(obj[33].toString()+"");
//			businessStoreVo.setBusinessStoreId(obj[34].toString()+"");
//		}
//		return ObjectToResult.getResult(businessStoreVo);
//		String hql="from BusinessStoreLogin where businessStore.business.state=1 and id='"+param.getManageToken().getBusinessStoreLogin().getId()+"'";
//		List<Object> list = hibernateUtil.hql(hql);
//		return ObjectToResult.getResult(list);
		BusinessStoreLogin businessStoreLogin=(BusinessStoreLogin) hibernateUtil.find(BusinessStoreLogin.class, param.getManageToken().getBusinessStoreLogin().getId());
		String hql="from BusinessStoreSummary where state=1 and businessStore.id='"+businessStoreLogin.getBusinessStore().getId()+"'";
		List<Object> list = hibernateUtil.hql(hql);
		list.add(list.size(), businessStoreLogin);
		return ObjectToResult.getResult(list);
	}
}

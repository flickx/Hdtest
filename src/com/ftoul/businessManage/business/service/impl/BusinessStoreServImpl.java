  package com.ftoul.businessManage.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.businessManage.business.service.BusinessStoreServ;
import com.ftoul.businessManage.business.vo.BusinessStoreVo;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.business.vo.BusinessStoreManageCategoryVo;
import com.ftoul.po.BusinessStore;
import com.ftoul.po.BusinessStoreLogin;
import com.ftoul.po.BusinessStoreManageCategory;
import com.ftoul.po.BusinessStoreSummary;
import com.ftoul.po.GoodsType;
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
		BusinessStoreVo businessStoreVo = new BusinessStoreVo();
		businessStoreVo.setBusinessStoreLogin(businessStoreLogin);
		if(list.size()>0){
			businessStoreVo.setBusinessStoreSummary((BusinessStoreSummary) list.get(0));
		}
		String hqla="from BusinessStoreManageCategory where state=1 and storeId='"+businessStoreLogin.getBusinessStore().getStoreId()+"'";
		List<Object> objlist = hibernateUtil.hql(hqla);
		List<Object> businessStoreManageCategoryVoList = new ArrayList<Object>();
		if(objlist!=null){
			BusinessStoreManageCategory businessStoreManageCategory=new BusinessStoreManageCategory();
			GoodsType goodsType=new GoodsType();
			for(int i=0;i<objlist.size();i++){
				BusinessStoreManageCategoryVo businessStoreManageCategoryVo=new BusinessStoreManageCategoryVo();
				businessStoreManageCategory=(BusinessStoreManageCategory) objlist.get(i);
				businessStoreManageCategoryVo.setId(businessStoreManageCategory.getId());
				businessStoreManageCategoryVo.setStoreId(businessStoreManageCategory.getStoreId());
				businessStoreManageCategoryVo.setState(businessStoreManageCategory.getState());
				goodsType=(GoodsType) hibernateUtil.find(GoodsType.class, businessStoreManageCategory.getFirstCategory());
				businessStoreManageCategoryVo.setFirstCategory( businessStoreManageCategory.getFirstCategory());
				businessStoreManageCategoryVo.setFirstCategoryName(goodsType.getName());
				goodsType=(GoodsType) hibernateUtil.find(GoodsType.class, businessStoreManageCategory.getTwoCategory());
				businessStoreManageCategoryVo.setTwoCategory( businessStoreManageCategory.getTwoCategory());
				businessStoreManageCategoryVo.setTwoCategoryName(goodsType.getName());
				goodsType=(GoodsType) hibernateUtil.find(GoodsType.class, businessStoreManageCategory.getThreeCategory());
				businessStoreManageCategoryVo.setThreeCategory( businessStoreManageCategory.getThreeCategory());
				businessStoreManageCategoryVo.setThreeCategoryName(goodsType.getName());
				businessStoreManageCategoryVoList.add(businessStoreManageCategoryVo);
			}
		}
		businessStoreVo.setObjList(businessStoreManageCategoryVoList);
		return ObjectToResult.getResult(businessStoreVo);
	}
//	/**
//	 * 根据登录token获取商家信息对象
//	 * @param param Parameter对象
//	 * @return 返回结果（前台用Result对象）
//	 */
//	@Override
//	public Result getBusinessStorePage(Parameter param) throws Exception {
//		BusinessStoreLogin businessStoreLogin=(BusinessStoreLogin) hibernateUtil.find(BusinessStoreLogin.class, param.getManageToken().getBusinessStoreLogin().getId());
//		return ObjectToResult.getResult(businessStoreLogin);
//	}
	/**
	 * 保存店铺详情
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveBusinessStoreSummary(Parameter param) throws Exception {
		Object res;
		BusinessStoreSummary businessStoreSummary=(BusinessStoreSummary) JSONObject.toBean((JSONObject) param.getObj(),BusinessStoreSummary.class);
		String hql="from BusinessStoreSummary where state=1 and businessStore.id= '"+businessStoreSummary.getBusinessStore().getId()+"'";
		List<Object> list = hibernateUtil.hql(hql); 
		if(list.size()!=0){
			BusinessStoreSummary storeSummary=(BusinessStoreSummary) list.get(0);
			storeSummary.setSummary(businessStoreSummary.getSummary());
			storeSummary.setBusinessStore(businessStoreSummary.getBusinessStore());
			storeSummary.setOperateId(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
			storeSummary.setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			res = hibernateUtil.update(storeSummary);
		}else{
			businessStoreSummary.setCreateId(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
			businessStoreSummary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreSummary.setOperateId(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
			businessStoreSummary.setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			businessStoreSummary.setState("1");
			res = hibernateUtil.save(businessStoreSummary);
		}
		return ObjectToResult.getResult(res);
	}
	/**
	 * 保存店铺logo
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	@Override
	public Result saveBusinessStorePic(Parameter param) throws Exception {
		Object res=1;
		BusinessStore businessStore=(BusinessStore) JSONObject.toBean((JSONObject) param.getObj(),BusinessStore.class);
		BusinessStore businessStoreDb=(BusinessStore) hibernateUtil.find(BusinessStore.class,businessStore.getId());
		businessStoreDb.setPic(businessStore.getPic());
		businessStoreDb.setOperateId(param.getManageToken().getBusinessStoreLogin().getStoreAccount());
		businessStoreDb.setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return ObjectToResult.getResult(res);
	}
}

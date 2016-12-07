package com.ftoul.util.logistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.api.weiXinPay.util.CommonUtil;
import com.ftoul.common.Common;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.AreaFreightTemplate;
import com.ftoul.po.JPositionProvice;
import com.ftoul.po.ShopFreightTemplate;
import com.ftoul.po.UserAddress;
import com.ftoul.util.hibernate.HibernateUtil;

@Component
public class LogisticsUtil {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	/**
	 * 后台查看物流公司
	 * @param param Parameter对象
	 * @return 返回结果（前台用Result对象）
	 */
	public Result getLogisticsCompany(Parameter parameter) throws Exception {
		String hql = " from LogisticsCompany where 1 = 1 ";
		List<Object> companyList = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(companyList);
	}
	
	/**
	 * 计算运费
	 * @param province
	 * @param shopId
	 * @param num
	 */
	public double getFreight(String province,String shopId,int num){
		double defaultFreight;
		double totalFreight = 0.0;
		int passNum;
		boolean flag = false;
		Object obj = hibernateUtil.hqlFirst("from ShopFreightTemplate where state='1' and activety='是' and shopId='"+shopId+"'");
		if(obj!=null){
			ShopFreightTemplate temp = (ShopFreightTemplate) obj;
			List<Object> dataList = hibernateUtil.hql("from AreaFreightTemplate where state='1' and shopFreightTemplate.id='"+temp.getId()+"'");
			if(Common.notNull(province)){
				for (Object object : dataList) {
					AreaFreightTemplate areaFreightTemplate = (AreaFreightTemplate) object;
					String[] areas = areaFreightTemplate.getFreightArea().split(",");
					for (String area : areas) {
						if(area.equals(province)){
							flag = true;
							defaultFreight = areaFreightTemplate.getLessPrice();
							if(num>areaFreightTemplate.getLess()){
								passNum = num-areaFreightTemplate.getLess();
								totalFreight = defaultFreight+(passNum*areaFreightTemplate.getIncreasePrice());
							}else{
								totalFreight = defaultFreight;
							}
							break;
						}
					}
				}
			}
			if(!flag){
				defaultFreight = Double.parseDouble(temp.getDefaultPrice());
				if(num>Integer.parseInt(temp.getDefaultFreight())){
					passNum = num-Integer.parseInt(temp.getDefaultFreight());
					totalFreight = defaultFreight+(passNum*Double.parseDouble(temp.getIncreasePrice()));
				}else{
					totalFreight = defaultFreight;
				}
			}
		}
		return totalFreight;
	}
	
	/**
	 * 获取用户默认省份，中文
	 * @param id
	 * @return
	 */
	public String getDefaultUserAddressProvince(String id){
		String provinceName=null;
		Object obj = hibernateUtil.hqlFirst("from UserAddress where state='1' and defulat='true' and user.id='"+id+"'");
		if(obj!=null){
			UserAddress address = (UserAddress) obj;
			Object object = hibernateUtil.hqlFirst("from JPositionProvice where proviceId='"+address.getProvince()+"'"); 
			JPositionProvice province = (JPositionProvice) object;
			provinceName = province.getProviceName();
		}
		return provinceName;
	}
	
	

}

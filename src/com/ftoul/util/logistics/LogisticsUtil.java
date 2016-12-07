package com.ftoul.util.logistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.po.AreaFreightTemplate;
import com.ftoul.po.ShopFreightTemplate;
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
		Object obj = hibernateUtil.hqlFirst("from ShopFreightTemplate where state='1' and shop.id='"+shopId+"'");
		if(obj!=null){
			ShopFreightTemplate temp = (ShopFreightTemplate) obj;
			List<Object> dataList = hibernateUtil.hql("from AreaFreightTemplate where state='1' and shopFreightTemplate.id-'"+temp.getId()+"'");
			for (Object object : dataList) {
				AreaFreightTemplate areaFreightTemplate = (AreaFreightTemplate) object;
				String[] areas = areaFreightTemplate.getFreightArea().split(",");
				for (String area : areas) {
					if(province.equals(area)){
						defaultFreight = areaFreightTemplate.getLessPrice();
						if(num>areaFreightTemplate.getLess()){
							passNum = num-areaFreightTemplate.getLess();
							totalFreight = defaultFreight+(passNum*areaFreightTemplate.getIncreasePrice());
						}
					}
				}
			}
			if(totalFreight==0.0){
				defaultFreight = Double.parseDouble(temp.getDefaultPrice());
				if(num>Integer.parseInt(temp.getDefaultFreight())){
					passNum = num-Integer.parseInt(temp.getDefaultFreight());
					totalFreight = defaultFreight+(passNum*Double.parseDouble(temp.getIncreasePrice()));
				}
			}
		}
		return totalFreight;
	}
	

}

package com.ftoul.manage.area.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.area.JPositionVo;
import com.ftoul.manage.area.service.AreaServ;
import com.ftoul.po.JPositionProvice;
import com.ftoul.po.UserAddress;
import com.ftoul.util.hibernate.HibernateUtil;

@Service("AreaServImpl")
public class AreaServImpl implements AreaServ {
	
	@Autowired HibernateUtil hibernateUtil;
	
	@Override
	public Result getProvices(Parameter param) throws Exception {
		String hql = "from JPositionProvice";
		List<Object> provices = hibernateUtil.hql(hql);
		return ObjectToResult.getResult(provices);
	}

	@Override
	public Result getCitys(Parameter param) throws Exception {
		String hql = "from JPositionCity where provinceId = '"+param.getId()+"'";
		List<Object> citys = hibernateUtil.hql(hql);
		String hql2 = "from JPositionProvice where proviceId = '"+param.getId()+"'";
		Object provice = hibernateUtil.hql(hql2);
		JPositionVo vo = new JPositionVo();
		vo.setList(citys);
		vo.setObject(provice);
		return ObjectToResult.getResult(vo);
	}

	@Override
	public Result getCountys(Parameter param) throws Exception {
		String hql = "from JPositionCounty where cityId = '"+param.getId()+"'";
		List<Object> countys = hibernateUtil.hql(hql);
		String hql2 = "from JPositionCity where cityId = '"+param.getId()+"'";
		Object city = hibernateUtil.hql(hql2);
		JPositionVo vo = new JPositionVo();
		vo.setList(countys);
		vo.setObject(city);
		return ObjectToResult.getResult(vo);
	}

	@Override
	public Result getTowns(Parameter param) throws Exception {
		String hql = "from JPositionTown where countyId = '"+param.getId()+"'";
		List<Object> towns = hibernateUtil.hql(hql);
		String hql2 = "from JPositionCounty where countyId = '"+param.getId()+"'";
		Object county = hibernateUtil.hql(hql2);
		JPositionVo vo = new JPositionVo();
		vo.setList(towns);
		vo.setObject(county);
		return ObjectToResult.getResult(vo);
	}
	
	@Override
	public Result getVillages(Parameter param) throws Exception {
		String hql = "from JPositionVillage where townId = '"+param.getId()+"'";
		List<Object> villages = hibernateUtil.hql(hql);
		String hql2 = "from JPositionTown where townId = '"+param.getId()+"'";
		Object town = hibernateUtil.hql(hql2);
		JPositionVo vo = new JPositionVo();
		vo.setList(villages);
		vo.setObject(town);
		return ObjectToResult.getResult(vo);
	}

	@Override
	public Result saveProvince(Parameter param) throws Exception {
		String hql = "update JPositionProvice set proviceName= '"+param.getKey()+"' where proviceId = '"+param.getId()+"'";
		Integer n = hibernateUtil.execHql(hql);
		return ObjectToResult.getResult(n);
	}

	@Override
	public Result saveCity(Parameter param) throws Exception {
		String hql = "update JPositionCity set cityName= '"+param.getKey()+"' where cityId = '"+param.getId()+"'";
		Integer n = hibernateUtil.execHql(hql);
		return ObjectToResult.getResult(n);
	}
	
	@Override
	public Result saveCounty(Parameter param) throws Exception {
		String hql = "update JPositionCounty set countyName= '"+param.getKey()+"' where countyId = '"+param.getId()+"'";
		Integer n = hibernateUtil.execHql(hql);
		return ObjectToResult.getResult(n);
	}	
	@Override
	public Result saveTown(Parameter param) throws Exception {
		String hql = "update JPositionTown set townName= '"+param.getKey()+"' where townId = '"+param.getId()+"'";
		
		Integer n = hibernateUtil.execHql(hql);
		return ObjectToResult.getResult(n);
	}
}

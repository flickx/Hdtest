package com.ftoul.web.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftoul.util.hibernate.HibernateUtil;
import com.ftoul.web.business.service.BusinessWebServ;
/**
 * 
 * @author wenyujie
 *
 */
@Service("BusinessWebServImpl")
public class BusinessWebServImpl implements BusinessWebServ {
	@Autowired
	private HibernateUtil hibernateUtil;
	
}

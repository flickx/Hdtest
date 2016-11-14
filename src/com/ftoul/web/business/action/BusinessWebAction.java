package com.ftoul.web.business.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftoul.web.business.service.BusinessWebServ;
/**
 * 
 * @author wenyujie
 * 商家
 *
 */
@Controller
@RequestMapping(value="/web/business")
public class BusinessWebAction {
	@Autowired
	private BusinessWebServ businessWebServ;

}

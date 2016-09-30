package com.ftoul.manage.kdNiaoCode.service;

import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 快递鸟的业务接口
 * @author wenyujie
 * 
 */
public interface KdniaoCodeServ {
	Result getKdniaoCodeList(Parameter parameter) throws Exception;
	Result getkdniaoCodeListPage(Parameter parameter) throws Exception;
	
}

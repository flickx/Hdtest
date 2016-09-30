package com.ftoul.manage.kdNiaoCode.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;
import com.ftoul.manage.kdNiaoCode.service.KdniaoCodeServ;
import com.ftoul.manage.logistics.service.LogisticsServ;

/**
 * 快递鸟
 * @author wenyujie
 *
 */
@Controller
@RequestMapping(value="/manage/kdniaoCode")
public class KdniaoCodeAction {
	@Autowired
	private KdniaoCodeServ kdNiaoCodeService;
	/**
	 * @author wenyujie
	 * 获取快递鸟分页列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getKdniaoCodeList")
	public @ResponseBody Result getKdniaoCodeList(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return kdNiaoCodeService.getKdniaoCodeList(parameter);
	}
	/**
	 * @author wenyujie
	 * 根据快递公司名称去查询
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getkdniaoCodeListPage")
	public @ResponseBody Result getkdniaoCodeListPage(String param)throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return kdNiaoCodeService.getkdniaoCodeListPage(parameter);
	}
}

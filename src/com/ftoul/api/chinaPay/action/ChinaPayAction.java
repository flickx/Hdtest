package com.ftoul.api.chinaPay.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ftoul.api.chinaPay.service.ChinaPayServ;
import com.ftoul.api.chinaPay.vo.PayResult;
import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 银联支付通道的Action
 * @author flick
 *
 */
@Controller
@RequestMapping(value = "/api/chinaPay")
public class ChinaPayAction {

	@Autowired
	private ChinaPayServ chinaPayServ;
	private static final String MOBILURL = "http://m.tatll.com/";
	/**
	 * 支付订单（测试用）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "payOrders")  
	public @ResponseBody Result payOrders(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		System.out.println(request.getParameter("param"));
		return chinaPayServ.payOrders(parameter);
	}
	/**
	 * 支付前台返回处理
	 * @param request 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "payResult")  
	public ModelAndView payResult(HttpServletRequest request) throws Exception{
		PayResult payResult = chinaPayServ.payResult(request);
		ModelMap mmap = new ModelMap();
		mmap.addAttribute("orderNum", payResult.getOrdersNum());
		mmap.addAttribute("result", payResult.getResult());
		if(payResult.getResult()){
			return new ModelAndView("redirect:" + MOBILURL + "payResult.html?orderNum="+payResult.getOrdersNum(), mmap);
		}else{
			return new ModelAndView("redirect:" + MOBILURL + "payFail.html?orderNum="+payResult.getOrdersNum(), mmap); 
		}
	}
	/**
	 * 支付后台返回处理
	 * @param request 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "payReturn")  
	public @ResponseBody void payReturn(String param) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		chinaPayServ.payReturn(request);
	}
}

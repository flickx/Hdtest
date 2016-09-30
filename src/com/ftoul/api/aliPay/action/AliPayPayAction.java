package com.ftoul.api.aliPay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ftoul.api.aliPay.service.AliPayServ;
import com.ftoul.api.chinaPay.vo.PayResult;
import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 微信支付通道的Action
 * @author flick
 *
 */
@Controller
@RequestMapping(value = "/api/aliPay")
public class AliPayPayAction {
	private static final String MOBILURL = "http://m.tatll.com/";
	@Autowired
	private AliPayServ aliPayServ;
	/**
	 * 支付订单（测试用）
	 * @param param 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "payOrders")  
	public @ResponseBody Result payOrders(String param) throws Exception{
		Parameter parameter = Common.jsonToParam(param);
		return aliPayServ.payOrders(parameter);
	}
	/**
	 * 支付前台返回处理
	 * @param request 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "payResult")  
	public ModelAndView payResult(HttpServletRequest request) throws Exception{
		PayResult payResult = aliPayServ.payResult(request);
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
	public void payReturn(HttpServletRequest request,HttpServletResponse response) throws Exception{
		aliPayServ.payReturn(request,response);
	}
	/**
	 * 支付后台返回处理(APP)
	 * @param request 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "payAppReturn")  
	public void payAppReturn(HttpServletRequest request,HttpServletResponse response) throws Exception{
		aliPayServ.payAppReturn(request,response);
	}
}

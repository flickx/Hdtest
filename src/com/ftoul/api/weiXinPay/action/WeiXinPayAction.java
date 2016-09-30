package com.ftoul.api.weiXinPay.action;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

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

import com.ftoul.api.chinaPay.vo.PayResult;
import com.ftoul.api.weiXinPay.service.WeiXinPayServ;
import com.ftoul.api.weiXinPay.util.PayCommonUtil;
import com.ftoul.api.weiXinPay.util.XMLUtil;
import com.ftoul.common.Common;
import com.ftoul.common.Parameter;
import com.ftoul.common.Result;

/**
 * 微信支付通道的Action
 * @author flick
 *
 */
@Controller
@RequestMapping(value = "/api/weiXinPay")
public class WeiXinPayAction {

	@Autowired
	private WeiXinPayServ weiXinPayServ;
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
		return weiXinPayServ.payOrders(parameter);
	}
	/**
	 * 支付前台返回处理
	 * @param request 页面传递参数对象
	 * @return AJAX调用Result的JSON对象
	 * @throws Exception 
	 */
	@RequestMapping(value = "payResult")  
	public ModelAndView payResult(HttpServletRequest request) throws Exception{
		PayResult payResult = weiXinPayServ.payResult(request);
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
	public @ResponseBody void payReturn(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		weiXinPayServ.payReturn(req);
		InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        System.out.println("~~~~~~~~~~~~~~~~付款成功~~~~~~~~~");
        outSteam.close();
        inStream.close();
        String result  = new String(outSteam.toByteArray(),"utf-8");//获取微信调用我们notify_url的返回信息
        Map<Object, Object> map = XMLUtil.doXMLParse(result);
        for(Object keyValue : map.keySet()){
            System.out.println(keyValue+"="+map.get(keyValue));
        }
        if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
            //TODO 对数据库的操作
        	weiXinPayServ.payReturn(map);
            response.getWriter().write(PayCommonUtil.setXML("SUCCESS", ""));   //告诉微信服务器，我收到信息了，不要在调用回调action了
            System.out.println("-------------"+PayCommonUtil.setXML("SUCCESS", ""));
        }
	}
}

package com.ftoul.aop;


import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 作用：每一个访问的页面都会进入这个方法
 * 功能：查询出来，然后过滤
 * 切面
 * @author flick
 *
 */
@Component
@Aspect
public class UploadAop {
	
	@Pointcut("execution(* com.ftoul..action.AliPayPayAction.*(..))")
	private void anyMethod(){}//定义一个切入点
	@Autowired  
	private  HttpServletRequest req;
	@Autowired
	private HttpServletResponse res;
	
	/**
	 * 获取页面传递参数，封装到Parameter对象中
	 * @param param
	 * @throws UnsupportedEncodingException 
	 */
	@Before("anyMethod()")
	public void cos() throws Exception{
		Collection<String> domain = res.getHeaders("Access-Control-Allow-Origin");
//		System.out.println(domain.size());
		if(domain.size() == 0)
			res.addHeader("Access-Control-Allow-Origin", "*");
	}
	
}

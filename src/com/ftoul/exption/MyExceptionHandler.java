package com.ftoul.exption;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftoul.common.ObjectToResult;
import com.ftoul.common.Result;

/**
 * 
 * @author flick
 * 自定义集中管理异常处理类
 */
@Controller("MyExceptionHandler")
public class MyExceptionHandler implements HandlerExceptionResolver {

	private static  final transient Logger log = Logger.getLogger(MyExceptionHandler.class);
	@SuppressWarnings("unchecked")
	/**
	 * 此方法截取所有Exception
	 * 如果需要针对不同的Exception做单独的处理
	 * 可在后面加入其它不同的方法并传入对应的Exption
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mav = new ModelAndView();
		MappingJackson2JsonView view = new MappingJackson2JsonView();
        Result result = ObjectToResult.getResult(ex);
        ObjectMapper mapper = new ObjectMapper();  
        @SuppressWarnings("rawtypes")
        Map<String, ?> attributes = new HashMap();
		try {
			attributes = mapper.readValue(mapper.writeValueAsString(result), Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
        view.setAttributesMap(attributes);
        mav.setView(view);
        ex.printStackTrace();
        log.error(ex);
        return mav;
	}
}


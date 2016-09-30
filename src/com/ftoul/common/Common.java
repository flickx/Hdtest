package com.ftoul.common;

import java.io.StringWriter;

import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;

import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 公共类
 * @author ysz
 *
 */
public class Common {

	/**
	 * 判断字符串不为空
	 * @param str
	 * @return
	 */
	public static Boolean notNull(String str){
		if(str==null||("").equals(str))
			return false;
		else
			return true;
	}
	/**
	 * 判断字符串为空
	 * @param str
	 * @return
	 */
	public static Boolean isNull(String str){
		if(str==null||("").equals(str))
			return true;
		else
			return false;
	}
	/**
	 * 空字符转换
	 */
	public static String toNotNullString(Object object) {
		String rn = "";
		if (object != null) {
			rn = object.toString();
		}
		return rn;
	}
	/**
	 * 对象转JSON字符串
	 * @param obj
	 * @return
	 */
	public static String beanToJson(Object obj){
		String resStr = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			resStr = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return resStr;
    }  
	/**
	 * JSON字符串转对象
	 * @param json
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static Object  jsonToBean(String json, Class<?> cls) throws Exception {  
		ObjectMapper mapper = new ObjectMapper();
	    Object vo = mapper.readValue(json, cls);   
	    return vo;   
	}  
	/**
	 * 根据参数转为Parameter对象
	 * @param param
	 * @return
	 */
	public static Parameter jsonToParam(String param){
		Parameter parameter = new Parameter();
		if(notNull(param)){
			try {
				parameter = (Parameter) jsonToBean(param, Parameter.class);
				if(parameter != null && parameter.getObj() != null){
					Object obj = parameter.getObj();
					JSONObject jsonObject = JSONObject.fromObject(obj);
					parameter.setObj(jsonObject);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return parameter;
	}
}

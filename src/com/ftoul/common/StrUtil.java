package com.ftoul.common;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Component;

/**
 * 字符串查找与替换相关工具类
 * @author flick
 *
 */
@Component
public class StrUtil {

	private static int counter = 0;  
	/**
	 * 查找子字符串的个数(递归调用)
	 * @param str1 源字符串
	 * @param str2 子字符串
	 * @return 个数
	 */
	public static int stringNumbers(String str1,String str2) {
		if (str1.indexOf(str2) == -1) {
			return 0;
		} else if (str1.indexOf(str2) != -1) {
			counter++;
			stringNumbers(str1.substring(str1.indexOf(str2) + str2.length()),str2);
			return counter;
		}
		return 0;
	}
	/**
	 * 字符串替换程序
	 * @param from 要替换的字符
	 * @param to 要替换成的目标字符
	 * @param source 要替换的字符串
	 * @return 替换后的字符串
	 * @return
	 */
	public static String strReplace(String from, String to, String source) {
		StringBuffer bf = new StringBuffer("");
		StringTokenizer st = new StringTokenizer(source, from, true);
		while (st.hasMoreTokens()) {
			String tmp = st.nextToken();
			if (tmp.equals(from)) {
				bf.append(to);
			} else {
				bf.append(tmp);
			}
		}
		return bf.toString();
	}
		/** 
	      * 判断字符串的编码 
	      * 
	      * @param str 
	      * @return 
	      */   
	    public static String getEncoding(String str) {    
	         String encode = "GB2312";    
	        try {    
	            if (str.equals(new String(str.getBytes(encode), encode))) {    
	                 String s = encode;    
	                return s;    
	             }    
	         } catch (Exception exception) {    
	        	 exception.printStackTrace();
	         }    
	         encode = "ISO-8859-1";    
	        try {    
	            if (str.equals(new String(str.getBytes(encode), encode))) {    
	                 String s1 = encode;    
	                return s1;    
	             }    
	         } catch (Exception exception1) { 
	        	 exception1.printStackTrace();
	         }    
	         encode = "UTF-8";    
	        try {    
	            if (str.equals(new String(str.getBytes(encode), encode))) {    
	                 String s2 = encode;    
	                return s2;    
	             }    
	         } catch (Exception exception2) {    
	        	 exception2.printStackTrace();
	         }    
	         encode = "GBK";    
	        try {    
	            if (str.equals(new String(str.getBytes(encode), encode))) {    
	                 String s3 = encode;    
	                return s3;    
	             }    
	        } catch (Exception exception3) {    
	        	exception3.printStackTrace();
	        }    
	       return "";    
     }   
	    
    /** 
      * 根据逗号生成数据库查询字段 
      * 
      * @param ids 
      * @return 
      */   
    public static String getIds(Object id) {
    	String resStr = "";
    	String[] ids = (id+"").split(",");
    	for (int i = 0 ; i<ids.length; i++) {
			if(i>0)
				resStr += ",";
			resStr += "'" + ids[i] + "'"; 
		}
    	return resStr;
    }
    
    /** 
     * 根据逗号生成数据库查询字段 
     * 
     * @param ids 
     * @return 
     */   
   public static String getStrIds(List id) {
		StringBuffer sb = new StringBuffer();
		for (Object object : id) {
			String s = (String) object;
			sb.append(s);
			sb.append(",");
		}
		return sb.toString();
   }
}

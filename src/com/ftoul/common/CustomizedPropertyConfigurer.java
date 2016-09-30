package com.ftoul.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
*
* 类描述：属性文件加载器
* @author: yw
* @date： 日期：2016年7月26日 时间：上午10:05:18
* @version 1.0
*
 */
public class CustomizedPropertyConfigurer extends PropertyPlaceholderConfigurer {
	private static Map<String, Object> propertyMap = new HashMap<String, Object>();

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {

		super.processProperties(beanFactoryToProcess, props);
		for (Object key : props.keySet()) {
			propertyMap.put((String) key, props.get(key));
		}
	}

	/**
	 * 返回指定键的配置。
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return (String) propertyMap.get(key);
	}
}

package com.ftoul.util.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.ftoul.web.webservice.UserService;

public class WebserviceUtil {

    public static String wsdlLocation;
    public static String targetNamespace;
    public static String name;
	public static final int CXF_CLIENT_CONNECT_TIMEOUT = 5 * 1000;//连接超时
	public static final int CXF_CLIENT_RECEIVE_TIMEOUT = 5 * 1000;//响应超时
	private static Properties p = null;
	
	static{
		try {
			InputStream is =  WebserviceUtil.class.getResourceAsStream("/config/webservice.properties");
			p = new Properties();
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//设置cxf客户端超时
	public static void configTimeout(Object service) {
		Client proxy = ClientProxy.getClient(service);
		HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
		HTTPClientPolicy policy = new HTTPClientPolicy();
		policy.setConnectionTimeout(CXF_CLIENT_CONNECT_TIMEOUT);
		policy.setReceiveTimeout(CXF_CLIENT_RECEIVE_TIMEOUT);
		conduit.setClient(policy);
	}
	
	//获取webservice
	public static UserService getService(){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInFaultInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(UserService.class);
		factory.setAddress(getWsdlLocation());
		UserService userService = (UserService)factory.create();
		configTimeout(userService);
		return userService;
	}
	    
    public static String getWsdlLocation(){
        wsdlLocation = p.getProperty("webservice_wsdlLocation");
        return wsdlLocation;
    }
    
    public static String getTargetNamespace(){
        targetNamespace = p.getProperty("webservice_targetNamespace");
        return targetNamespace;
    }
    
    public static String getName(){
        name = p.getProperty("webservice_name");
        return name;
    }
    
    public static void main(String[] args) {

//		String r = w.checkUser("18570614771","111111");
//		System.out.println(r);
	}
}

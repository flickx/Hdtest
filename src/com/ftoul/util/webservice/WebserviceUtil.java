package com.ftoul.util.webservice;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.ftoul.web.webservice.UserService;
import com.ftoul.web.webservice.UserServiceService;

public class WebserviceUtil {

	public static final int CXF_CLIENT_CONNECT_TIMEOUT = 5 * 1000;//连接超时
	public static final int CXF_CLIENT_RECEIVE_TIMEOUT = 5 * 1000;//响应超时
	
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
		UserServiceService server = new UserServiceService();
		UserService userService = server.getUserServicePort();
		configTimeout(userService);
		return userService;
	}
}

package com.ftoul.web.websocket.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.TextMessage;

import com.ftoul.common.Result;
import com.ftoul.web.websocket.WxWebSocketHandler;

public class WxPayServImpl {

	@Bean
	public WxWebSocketHandler wxWebSocketHandler(){
		return new WxWebSocketHandler();
	}
	
	public Result test(){
		wxWebSocketHandler().sendMessageToUser("", new TextMessage("abc"));
		return null;
	}
}

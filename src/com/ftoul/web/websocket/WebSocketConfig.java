package com.ftoul.web.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.ftoul.web.websocket.impl.WxWebSocketHandlerImpl;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements
		WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(wxWebSocketHandler(), "/webSocketServer.action")
				.addInterceptors(new WebSocketHandshakeInterceptor()).setAllowedOrigins("*");

		registry.addHandler(wxWebSocketHandler(), "/sockjs/webSocketServer.action")
				.addInterceptors(new WebSocketHandshakeInterceptor()).setAllowedOrigins("*")
				.withSockJS();
	}

	@Bean
	public WxWebSocketHandlerImpl wxWebSocketHandler() {
		return new WxWebSocketHandlerImpl();
	}

}

package com.ftoul.web.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.ftoul.common.Common;
import com.ftoul.po.UserToken;
import com.ftoul.web.websocket.service.WebSocketServ;

public class WxWebSocketHandler implements WebSocketHandler {


    private static ArrayList<WebSocketSession> users;
    public static final String WebSocketUserName = "webSocketUserName";

    static {
		users = new ArrayList<WebSocketSession>();
	}
    
    @Autowired
    private WebSocketServ webSocketServ;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("connect to the websocket success......");
        users.add(session);
        String userName = (String) session.getAttributes().get(WebSocketUserName);
        WebSocketMessage<?> message = new TextMessage("abc");
        session.sendMessage(message);
        Map map = session.getAttributes();
        System.out.println("id="+session.getId());
        if(userName!= null){
            //查询未读消息
            int count = webSocketServ.getUnReadNews((String) session.getAttributes().get(WebSocketUserName));

            session.sendMessage(new TextMessage(count + ""));
        }
        sendMessageToUsers(new TextMessage("有人上线了"));
//        afterConnectionClosed(session, null);
        String orderNumber = (String) session.getAttributes().get("orderNumber");
        String userToken = (String) session.getAttributes().get("userToken");
        UserToken token = (UserToken) Common.jsonToBean(userToken, UserToken.class);
        sendMessageToUsers(new TextMessage("订单号为："+orderNumber));
        sendMessageToUsers(new TextMessage("token为："+userToken));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        //sendMessageToUsers();
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        System.out.println("websocket connection closed......");
        users.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("websocket connection closed......");
        users.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get(WebSocketUserName).equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

}

package com.example.websocket.wshandler;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleWSHandler extends TextWebSocketHandler {

    public static final String ID = "id";
    private static final List<WebSocketSession> COUNTS = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Status: connected");
        COUNTS.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("Status: getMsg: " + message.getPayload());
    }

    public void sendMessage(String id, String message) {
        for (WebSocketSession socketSession : COUNTS) {
            if (socketSession.getAttributes().get(ID).equals(id)) {
                try {
                    if (socketSession.isOpen()) {
                        socketSession.sendMessage(new TextMessage(message));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void close(String id) {
        for (WebSocketSession socketSession : COUNTS) {
            if (socketSession.getAttributes().get(ID).equals(id)) {
                try {
                    if (socketSession.isOpen()) {
                        socketSession.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                COUNTS.remove(socketSession);
                break;
            }
        }
    }

    public static class CountHandshakeInterceptor implements HandshakeInterceptor {

        @Override
        public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
            String collector = ((ServletServerHttpRequest) request).getServletRequest().getParameter(ID);
            if (StringUtils.isEmpty(collector)) {
                return false;
            } else {
                attributes.put(ID, collector);
                return true;
            }
        }

        @Override
        public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Exception exception) {
        }

    }
}

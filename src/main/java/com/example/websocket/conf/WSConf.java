package com.example.websocket.conf;

import com.example.websocket.wshandler.SimpleWSHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WSConf implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(counterHandler(), "/hello")
                .addInterceptors(new SimpleWSHandler.CountHandshakeInterceptor());
    }

    @Bean
    public SimpleWSHandler counterHandler() {
        return new SimpleWSHandler();
    }

}

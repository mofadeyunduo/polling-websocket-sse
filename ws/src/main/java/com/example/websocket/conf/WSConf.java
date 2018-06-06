package com.example.websocket.conf;

import com.example.websocket.wshandler.SimpleTextWSHandler;
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
                .addInterceptors(new SimpleTextWSHandler.CountHandshakeInterceptor());
    }

    @Bean
    public SimpleTextWSHandler counterHandler() {
        return new SimpleTextWSHandler();
    }

}

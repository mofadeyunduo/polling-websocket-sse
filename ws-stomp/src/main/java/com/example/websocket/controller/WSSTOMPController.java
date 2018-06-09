package com.example.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WSSTOMPController {

    @MessageMapping("/WS/publish/hello")
    @SendTo("/WS/subscribe/hello")
    public String hello(String greeting) {
        System.out.println("Receive Msg: " + greeting);
        return "Hello, time is " + System.currentTimeMillis();
    }

}
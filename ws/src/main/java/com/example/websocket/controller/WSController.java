package com.example.websocket.controller;

import com.example.websocket.wshandler.SimpleTextWSHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/websocket")
public class WSController {

    @Autowired
    private SimpleTextWSHandler simpleWSHandler;

    @RequestMapping("/send")
    public String send(String id) {
        simpleWSHandler.sendMessage(id, "Server time: " + System.currentTimeMillis());
        return "success";
    }

    @RequestMapping("/disconnect")
    public String close(String id) {
        simpleWSHandler.close(id);
        return "success";
    }

}

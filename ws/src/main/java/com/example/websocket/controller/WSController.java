package com.example.websocket.controller;

import com.example.websocket.wshandler.SimpleWSHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/websocket")
public class WSController {

    @Autowired
    private SimpleWSHandler simpleWSHandler;

    @RequestMapping("/send")
    public String send(String id) {
        simpleWSHandler.sendMessage(id, "Server time: " + new Date());
        return "success";
    }

    @RequestMapping("/disconnect")
    public String close(String id) {
        simpleWSHandler.close(id);
        return "success";
    }

}

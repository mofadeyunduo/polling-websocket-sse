package com.example.sse.controller;

import com.example.sse.service.FixedRateNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
public class SseController {

    @Autowired
    private FixedRateNotificationService fixedRateNotificationService;

    @GetMapping(value = "/notification")
    public SseEmitter send(String id) {
        final SseEmitter emitter = new SseEmitter();
        emitter.onCompletion(() -> fixedRateNotificationService.removeSseEmitter(id));
        emitter.onTimeout(() -> fixedRateNotificationService.removeSseEmitter(id));
        fixedRateNotificationService.addSseEmitter(id, emitter);
        return emitter;
    }

}
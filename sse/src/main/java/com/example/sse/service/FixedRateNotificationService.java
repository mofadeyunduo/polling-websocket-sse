package com.example.sse.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@EnableScheduling
public class FixedRateNotificationService {

    private Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public void addSseEmitter(String id, SseEmitter emitter) {
        emitters.put(id, emitter);
    }

    public void removeSseEmitter(String id) {
        emitters.remove(id);
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void doNotify() {
        emitters.forEach((id, emitter) -> {
            try {
                emitter.send(SseEmitter.event().data("Hello, emitter " + id + ", time is " + System.currentTimeMillis()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
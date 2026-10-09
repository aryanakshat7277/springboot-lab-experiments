package com.example.notification.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.notification.model.NotificationPayload;

@Service
public class NotificationService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        try {
            // Send initial welcome notification
            emitter.send(SseEmitter.event()
                    .name("notification")
                    .data(new NotificationPayload("Connected", "Real-time notification stream active!", "SUCCESS")));
        } catch (IOException e) {
            emitters.remove(emitter);
        }

        return emitter;
    }

    public void broadcast(NotificationPayload payload) {
        List<SseEmitter> deadEmitters = new CopyOnWriteArrayList<>();
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("notification")
                        .data(payload));
            } catch (IOException e) {
                deadEmitters.add(emitter);
            }
        }
        emitters.removeAll(deadEmitters);
    }

    public int getSubscriberCount() {
        return emitters.size();
    }

    // Periodic automatic system heartbeat notification
    @Scheduled(fixedRate = 10000)
    public void sendPeriodicSystemHeartbeat() {
        if (!emitters.isEmpty()) {
            broadcast(new NotificationPayload(
                    "System Heartbeat",
                    "Real-time server status healthy. Connected clients: " + emitters.size(),
                    "INFO"
            ));
        }
    }
}

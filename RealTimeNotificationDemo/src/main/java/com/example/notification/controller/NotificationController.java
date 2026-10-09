package com.example.notification.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.notification.model.NotificationPayload;
import com.example.notification.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    // Subscribe to Real-Time SSE Notification Stream
    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe() {
        return service.subscribe();
    }

    // Send a Real-Time Notification Broadcast
    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationPayload payload) {
        service.broadcast(payload);
        return "Notification sent successfully!";
    }

    // Get Active Subscribers Count
    @GetMapping("/subscribers")
    public int getSubscribers() {
        return service.getSubscriberCount();
    }
}

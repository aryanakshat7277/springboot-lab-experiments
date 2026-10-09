package com.example.notification.model;

import java.time.LocalDateTime;

public class NotificationPayload {

    private String title;
    private String message;
    private String timestamp;
    private String type;

    public NotificationPayload() {
    }

    public NotificationPayload(String title, String message, String type) {
        this.title = title;
        this.message = message;
        this.type = type;
        this.timestamp = LocalDateTime.now().toString();
    }

    public NotificationPayload(String title, String message, String timestamp, String type) {
        this.title = title;
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

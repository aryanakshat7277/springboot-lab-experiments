package com.example.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RealTimeNotificationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealTimeNotificationDemoApplication.class, args);
    }
}

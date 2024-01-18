package com.example.notification;

import org.springframework.stereotype.Component;

@Component
public class NotificationFallBack implements NotificationClient{
    @Override
    public void sendNotification(NotificationRequest notificationRequest) {
        System.out.println("in callback notif");
    }
}

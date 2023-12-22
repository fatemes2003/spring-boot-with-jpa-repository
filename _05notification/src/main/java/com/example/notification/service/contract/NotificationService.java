package com.example.notification.service.contract;

import com.example.notification.NotificationRequest;

public interface NotificationService {
    void send(NotificationRequest notificationRequest);
}

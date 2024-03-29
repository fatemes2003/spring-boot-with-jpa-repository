package com.example.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="NOTIFICATION",fallback = NotificationFallBack.class)
public interface NotificationClient {
    @PostMapping("/api/v1/notification")
    public void sendNotification(@RequestBody NotificationRequest notificationRequest);
}

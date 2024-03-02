package com.example.notification.kafka;


import com.example.notification.NotificationRequest;
import com.example.notification.service.contract.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
@AllArgsConstructor
public class KafkaNotificationConsumer {

    private NotificationService notificationService;

    //@KafkaHandler
    //@KafkaListener(topics = "product4",groupId = "1",containerFactory = "factory")
    void listener(NotificationRequest notificationRequest){
        log.warn("====>Consumed  {} from Queue",notificationRequest);
        notificationService.send(notificationRequest);
    }
}

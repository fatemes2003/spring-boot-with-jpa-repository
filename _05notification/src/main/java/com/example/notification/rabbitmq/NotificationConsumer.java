package com.example.notification.rabbitmq;

import com.example.notification.NotificationRequest;
import com.example.notification.service.contract.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    @Autowired
    private NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void consume(NotificationRequest notificationRequest) {
        log.warn("=====> consumed {} from queue ", notificationRequest);
        notificationService.send(notificationRequest);
    }
}

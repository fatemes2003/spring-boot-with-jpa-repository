package com.example.notification.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    /*@Autowired
    private NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void consume(NotificationRequest notificationRequest) {
        log.warn("=====> consumed {} from queue ", notificationRequest);
        notificationService.send(notificationRequest);
    }*/
}

package com.example;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

//@Component
@AllArgsConstructor
@Slf4j
public class RabbitMQMessageProducer {
    private final AmqpTemplate amqpTemplate;

    public void publish(Object data, String exchange, String routingKey) {
        amqpTemplate.convertAndSend(exchange, routingKey, data);
        log.warn("=====> published to {} by routing key {} with data {}",exchange,routingKey, data);
    }
}

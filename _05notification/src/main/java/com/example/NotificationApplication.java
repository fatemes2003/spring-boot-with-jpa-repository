package com.example;

import com.example.notification.NotificationRequest;
import com.example.notification.rabbitmq.AMQPInit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RabbitMQMessageProducer rabbitMQMessageProducer, AMQPInit amqpInit) {
        return args -> {
            rabbitMQMessageProducer.publish(new NotificationRequest(2l,"dsds"), amqpInit.getExchange(), amqpInit.getRoutingKey());
        };
    }
}

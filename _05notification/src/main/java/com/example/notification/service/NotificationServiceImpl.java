package com.example.notification.service;

import com.example.notification.entity.Notification;
import com.example.notification.NotificationRequest;
import com.example.notification.repository.NotificationRepository;
import com.example.notification.service.contract.NotificationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    private final ModelMapper modelMapper;

    @Override
    public void send(NotificationRequest notificationRequest) {
        //notificationRepository.save(new Notification("org.example",notificationRequest.getMessage(), LocalDateTime.now()));
        notificationRepository.save(modelMapper.map(notificationRequest, Notification.class));
    }
}

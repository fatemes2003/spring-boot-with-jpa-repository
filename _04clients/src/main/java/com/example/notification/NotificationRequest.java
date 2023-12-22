package com.example.notification;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    private String sender;
    private String message;
    private LocalDateTime sendAt;
}

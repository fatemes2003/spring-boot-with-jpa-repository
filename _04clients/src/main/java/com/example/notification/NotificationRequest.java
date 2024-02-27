package com.example.notification;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest implements Serializable {
    private Long toProductId;
    private String Message;
}

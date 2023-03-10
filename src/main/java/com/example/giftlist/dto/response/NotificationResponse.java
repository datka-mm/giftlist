package com.example.giftlist.dto.response;

import com.example.giftlist.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class NotificationResponse {

    private Long userId;
    private Long wishId;
    private String wishName;
    private String firstName;
    private String lastName;
    private String photo;
    private LocalDate createdAt;
    private NotificationType notificationType;
    private String message;
}

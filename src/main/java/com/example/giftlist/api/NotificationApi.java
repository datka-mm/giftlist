package com.example.giftlist.api;

import com.example.giftlist.db.services.NotificationService;
import com.example.giftlist.dto.response.AllNotificationsResponse;
import com.example.giftlist.dto.response.SimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("api/notifications")
@Tag(name = "Notification Api", description = "All notifications")
public class NotificationApi {

    private final NotificationService notificationService;

    @Operation(summary = "User notifications", description = "User can see all notifications")
    @GetMapping
    public AllNotificationsResponse getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @Operation(summary = "Mark as read", description = "User can mark as read all notifications")
    @PostMapping
    public SimpleResponse isRead() {
        return notificationService.markAsRead();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Admin notifications", description = "Admin can see all notifications")
    @GetMapping("admin")
    public AllNotificationsResponse getAllNotificationForAdmin(){
        return notificationService.getAllNotificationsForAdmin();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Mark as read", description = "Admin can mark as read all notifications")
    @PutMapping("admin")
    public SimpleResponse MarkIsRead() {
        return notificationService.markAsReadForAdmin();
    }
}
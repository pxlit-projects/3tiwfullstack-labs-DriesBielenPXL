package be.pxl.services.controller;

import be.pxl.services.domain.Notification;
import be.pxl.services.services.INotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final INotificationService service;

    public NotificationController(INotificationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Notification> create(@Validated @RequestBody Notification notification) {
        Notification saved = service.create(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}

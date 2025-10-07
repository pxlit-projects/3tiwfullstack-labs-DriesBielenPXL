package be.pxl.services.services;

import be.pxl.services.domain.Notification;
import be.pxl.services.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Notification create(Notification notification) {
        // basic save, validation can be added later
        return repository.save(notification);
    }
}

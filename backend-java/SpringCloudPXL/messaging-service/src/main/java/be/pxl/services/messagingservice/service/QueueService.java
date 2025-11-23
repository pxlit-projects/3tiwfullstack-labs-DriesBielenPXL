package be.pxl.services.messagingservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    private static final Logger log = LoggerFactory.getLogger(QueueService.class);

    @RabbitListener(queues = "myQueue")
    public void listen(String in) {
        log.info("Message read from myQueue : {}", in);
    }
}

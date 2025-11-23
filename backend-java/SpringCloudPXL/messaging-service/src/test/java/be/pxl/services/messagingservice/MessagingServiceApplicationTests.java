package be.pxl.services.messagingservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"eureka.client.enabled=false", "app.send.on-startup=false"})
class MessagingServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}

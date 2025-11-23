package be.pxl.services.messagingservice;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@SpringBootApplication
@EnableRabbit
public class MessagingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagingServiceApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(name = "app.send.on-startup", havingValue = "true", matchIfMissing = true)
    public CommandLineRunner sendHello(RabbitTemplate rabbitTemplate) {
        return args -> rabbitTemplate.convertAndSend("myQueue", "Hello, world!");
    }
}

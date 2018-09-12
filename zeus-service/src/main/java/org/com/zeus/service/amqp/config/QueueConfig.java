package org.com.zeus.service.amqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public Queue BMessage() {
        return new Queue("clientToCustomerQueue");
    }
}

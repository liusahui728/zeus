package org.com.zeus.service.amqp.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSender {

	@Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msgString) {
        this.rabbitTemplate.convertAndSend("clientToCustomerQueue", msgString);
    }
}

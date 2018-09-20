package org.com.zeus.service.amqp.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSender {

	@Autowired
    private AmqpTemplate rabbitTemplate;

	
    public void send(String bs) {
        this.rabbitTemplate.convertAndSend("myQueue", bs);
    }
    
    public void send() {
        String msgString="fanoutSender :hello i am hzb";
        System.out.println(msgString);
        this.rabbitTemplate.convertAndSend("fanoutExchange","", msgString);
    }
    
    public void send1() {
        String msgString="fanoutSender :hello i am hzb";
        System.out.println(msgString);
        this.rabbitTemplate.convertAndSend("zeusExchange","", msgString);
    }

}

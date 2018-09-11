package org.com.zeus.service.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.A" )
public class RabbitMQReciver {

	    @RabbitHandler
	    public void process(String hello) {
	        System.out.println("Receiver1  : " + hello);
	    }
}

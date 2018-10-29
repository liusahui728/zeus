/*package org.com.zeus.service.amqp.reciver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQExchangeReciver {
	    @RabbitHandler
	    @RabbitListener(queues = "exchange.A" )
	    public void process(String hello) {
	        System.out.println("Receiver A  : " + hello);
	    }
	    
	    @RabbitHandler
	    @RabbitListener(queues = "exchange.C" )
	    public void process1(String hello) {
	        System.out.println("Receiver c  : " + hello);
	    }
}
*/
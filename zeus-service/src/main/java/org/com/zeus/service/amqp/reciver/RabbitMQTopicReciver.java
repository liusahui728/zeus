package org.com.zeus.service.amqp.reciver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQTopicReciver {
	    @RabbitHandler
	    @RabbitListener(queues = "topic.a" )
	    public void process(String hello) {
	        System.out.println("topic.a : " + hello);
	    }
	    
	    @RabbitHandler
	    @RabbitListener(queues = "topic.b" )
	    public void process1(String hello) {
	        System.out.println("topic.b  : " + hello);
	    }
}

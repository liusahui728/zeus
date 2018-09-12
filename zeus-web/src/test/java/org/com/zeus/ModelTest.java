package org.com.zeus;

import org.com.zeus.service.amqp.sender.RabbitMQSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ModelTest extends AppTest {
	@Autowired
	RabbitMQSender rabbitMQSender;
	@Test
	public void test() {
		rabbitMQSender.send("000607b2-b1bc-11e8-9637-0242ac110006");
	}
}

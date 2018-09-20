package org.com.zeus.service.amqp.reciver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Component
public class RabbitMQQueueReciver {

	    @RabbitHandler
	    @RabbitListener(queues = "myQueue")
	    public void process(byte[] val) {
	    	String str=new String(val);
	    	JSONObject jSONObject=JSON.parseObject(str);
	        System.out.println("myQueue  : " + jSONObject.toJSONString());
	    }
}
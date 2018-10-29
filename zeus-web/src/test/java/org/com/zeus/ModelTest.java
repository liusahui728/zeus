/*package org.com.zeus;

import java.util.HashMap;
import java.util.Map;

import org.com.zeus.service.amqp.sender.RabbitMQSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.JedisCluster;

public class ModelTest extends AppTest {
	
	 * @Autowired RedisTemplate<String, String> redisTemplate;
	 
	@Autowired
	JedisCluster jedisCluster;

	@Autowired
	RabbitMQSender rabbitMQSender;

	@Test
	public void test2() {
		Map map = new HashMap<>();
		for(int i=0;i<1;i++) {
			map.put("clientId", "0054ba5ebbd111e8928a0242ac110001");
			//map.put("empId", "0439f4db6d704bb7824f8f4f4218bfb7");
			rabbitMQSender.send(JSON.toJSONString(map));
			
		}
		
	}

	@Test
	public void test() {
		jedisCluster.set("testkey", "test");
		System.out.println(jedisCluster.get("testkey"));

	}

	@Test
	public void test1() {
		
		 * String key = "redisTestKey"; String value = "I am test value";
		 * 
		 * ValueOperations<String, String> opsForValue = redisTemplate.opsForValue(); //
		 * 数据插入测试： opsForValue.set(key, value); String valueFromRedis =
		 * opsForValue.get(key); System.out.println(valueFromRedis);
		 
		// System.out.println(jedisCluster.get("username"));
	}
	
	@Test
	public void testExchange() {
		rabbitMQSender.send1();
	}
}
*/
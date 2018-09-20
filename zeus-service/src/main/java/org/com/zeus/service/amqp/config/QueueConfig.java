package org.com.zeus.service.amqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
	//*表示一个词,#表示零个或多个词
	public static final String ROUTINGKEY1 = "topic.*";
    public static final String ROUTINGKEY2 = "msg-routingKey.#";
	
	//点对点发送
    @Bean
    public Queue QueueMessage() {
        return new Queue("myQueue");
    }
    
    
    //1对多发送需要绑定
    @Bean
    public Queue AMessage() {
        return new Queue("exchange.A");
    }
    
    @Bean
    public Queue CMessage() {
        return new Queue("exchange.C");
    }
    
    @Bean
    FanoutExchange zeusExchange() {
        return new FanoutExchange("zeusExchange");//配置广播路由器
    }
    
    @Bean
    Binding bindingExchangeA() {
        return BindingBuilder.bind(AMessage()).to(zeusExchange());
    }
    
    @Bean
    Binding bindingExchangeB() {
        return BindingBuilder.bind(CMessage()).to(zeusExchange());
    }
    
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");//配置广播路由器
    }
    
    @Bean
    public Queue Topic1() {
        return new Queue("topic.a");
    }
    
    @Bean
    public Queue Topic2() {
        return new Queue("topic.b");
    }
    
    @Bean
    Binding bindingExchangeTopic1() {
        return BindingBuilder.bind(Topic1()).to(topicExchange()).with(ROUTINGKEY1);
    }
    
    @Bean
    Binding bindingExchangeTopic2() {
        return BindingBuilder.bind(Topic2()).to(topicExchange()).with(ROUTINGKEY1);
    }
   
}

package org.com.zeus.rediscluster;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
public class RedisClusterConfig {
	@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.0.44", 7001));
        nodes.add(new HostAndPort("192.168.0.44", 7002));
        nodes.add(new HostAndPort("192.168.0.44", 7003));
        nodes.add(new HostAndPort("192.168.0.44", 7004));
        nodes.add(new HostAndPort("192.168.0.44", 7005));
        nodes.add(new HostAndPort("192.168.0.44", 7006));
        //过期时间等自己配置
        return new JedisCluster(nodes);
	}
}

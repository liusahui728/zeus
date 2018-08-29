package org.com.zeus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
@MapperScan({"org.com.zeus.dao","org.com.zeus.sequence.dao"})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

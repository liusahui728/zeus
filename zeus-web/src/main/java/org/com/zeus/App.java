package org.com.zeus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan({"org.com.zeus.mapper","org.com.zeus.sequence.dao"})
public class App extends SpringBootServletInitializer{
	  @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	    return builder.sources(App.class);
	  }

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

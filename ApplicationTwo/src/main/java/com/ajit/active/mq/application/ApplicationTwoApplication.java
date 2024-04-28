package com.ajit.active.mq.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.ajit.active.mq.application")
@EnableScheduling
public class ApplicationTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationTwoApplication.class, args);
	}

}

package com.etjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConApp {

	public static void main(String[] args) {
		SpringApplication.run(ConApp.class, args);
		// 
//		ApplicationContext ac = SpringApplication.run(ConApp.class, args);
//		ConsumerService consumerService = (ConsumerService)ac.getBean("consumerService");
//		consumerService.receiveMessage();// 每次调用只接收一条消息
	}
}

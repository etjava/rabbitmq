package com.etjava.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.ConFanoutService;

@Service("conFanoutService")
public class ConFanoutServiceImpl implements ConFanoutService {

	@Override
	@RabbitListener(queues = {RabbitMQConfig.SUB_QUEUE1})
	public void receiveSubMessage1(String message) {
		System.out.println("sub1-接收到的订阅消息>:"+message);
	}

	@Override
	@RabbitListener(queues = {RabbitMQConfig.SUB_QUEUE2})
	public void receiveSubMessage2(String message) {
		System.out.println("sub2-接收到的订阅消息>:"+message);
	}

}

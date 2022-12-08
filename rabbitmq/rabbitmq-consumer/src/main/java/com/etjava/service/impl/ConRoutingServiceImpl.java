package com.etjava.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.ConRoutingService;

@Service("conRoutingService")
public class ConRoutingServiceImpl implements ConRoutingService {

	@Override
	@RabbitListener(queues = {RabbitMQConfig.DIRECT_ROUTING_QUEUE1})
	public void receiveMessageWithListener1(String message) {
		System.out.println("消费者队列1 接收到的消息>:"+message);
	}

	@Override
	@RabbitListener(queues = {RabbitMQConfig.DIRECT_ROUTING_QUEUE2})
	public void receiveMessageWithListener2(String message) {
		System.out.println("消费者队列2 接收到的消息>:"+message);
	}

}

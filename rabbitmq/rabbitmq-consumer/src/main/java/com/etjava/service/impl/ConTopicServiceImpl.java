package com.etjava.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.ConTopicService;

@Service("conTopicService")
public class ConTopicServiceImpl implements ConTopicService {

	@Override
	@RabbitListener(queues = {RabbitMQConfig.DIRECT_TOPIC_QUEUE1})
	public void receiveMessage(String message) {
		System.out.println("队列1接收的消息>:"+message);
	}
	
	@Override
	@RabbitListener(queues = {RabbitMQConfig.DIRECT_TOPIC_QUEUE2})
	public void receiveMessage2(String message) {
		System.out.println("队列2接收的消息>:"+message);
	}
}

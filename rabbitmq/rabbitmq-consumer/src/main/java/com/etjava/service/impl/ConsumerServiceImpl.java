package com.etjava.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.ConsumerService;

@Service("consumerService")
public class ConsumerServiceImpl implements ConsumerService{

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Override
	public void receiveMessage() {
//		String msg = (String) amqpTemplate.receiveAndConvert(RabbitMQConfig.DIRECT_QUEUE);
//		System.out.println("0-接收到的消息>:"+msg);
	}

	@Override
	//@RabbitListener(queues = {RabbitMQConfig.DIRECT_QUEUE})
	public void receiveMessageWithListener(String message) {
		System.out.println("1-接收到的消息>:"+message);
	}

	@Override
	//@RabbitListener(queues = {RabbitMQConfig.DIRECT_QUEUE})
	public void receiveMessageWithListener2(String message) {
		System.out.println("2-接收到的消息>:"+message);
	}

}

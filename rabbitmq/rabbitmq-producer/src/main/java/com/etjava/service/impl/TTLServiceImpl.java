package com.etjava.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.TTLService;

@Service("ttlService")
public class TTLServiceImpl implements TTLService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	@Override
	public void sendTTLMessage(String message) {

//		MessageProperties messageProperties = new MessageProperties();
//		messageProperties.setExpiration("10000");// 给消息设置过期时间 单位毫秒
//		Message msg = new Message(message.getBytes(),messageProperties);
		// 给消息单独设置过期时间
//		rabbitTemplate.send(RabbitMQConfig.DIRECT_TTL_EXCHANGE,RabbitMQConfig.DIRECT_TTL_ROUTINGKEY,msg );
		// 消息不设置过期时间 但队列设置了过期时间
		rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_TTL_EXCHANGE,RabbitMQConfig.DIRECT_TTL_ROUTINGKEY,message);
	}


	/**
	 * 发送普通消息到带有过期时间的队列
	 */
	@Override
	public void sendDLXMessage(String message) {
		rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_TTL_EXCHANGE,RabbitMQConfig.DIRECT_TTL_ROUTINGKEY,message);
	}

}

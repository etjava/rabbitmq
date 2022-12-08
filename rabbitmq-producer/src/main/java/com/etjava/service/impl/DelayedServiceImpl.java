package com.etjava.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.DelayedService;

@Service("delayedService")
public class DelayedServiceImpl implements DelayedService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void sendDelayedMessage(String message,Integer delayTime) {
		// 交换机，路由Key，消息体，延时时间
		rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_DELAYED_EXCHANGE,
				RabbitMQConfig.DIRECT_DELAYED_ROUTINGKEY,message,a->{
					a.getMessageProperties().setDelay(delayTime);// 设置延时时间
					return a;
				});
	}

}

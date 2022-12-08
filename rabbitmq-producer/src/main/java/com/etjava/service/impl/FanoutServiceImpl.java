package com.etjava.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.FanoutService;


@Service("fanoutService")
public class FanoutServiceImpl implements FanoutService {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Override
	public void sendFanoutMessage(String message) {
		for(int i=0;i<100;i++) {
			amqpTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE,
					"","广播消息："+message+"--"+i);//routingKey不需要 设为空就可以
		}
	}

}

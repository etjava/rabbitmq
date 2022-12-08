package com.etjava.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.RoutingService;

@Service("routingService")
public class RoutingServiceImpl implements RoutingService {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Override
	public void sendRoutingErrorMessage(String message) {
		for(int i=0;i<10;i++) {
			amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_ROUTING_EXCHANGE,
					RabbitMQConfig.DIRECT_ERROR_ROUTING_ROUTINGKEY,message+"--"+i);
		}
	}


	@Override
	public void sendRoutingInfoMessage(String message) {
		for(int i=0;i<10;i++) {
			amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_ROUTING_EXCHANGE,
					RabbitMQConfig.DIRECT_INFO_ROUTING_ROUTINGKEY,message+"--"+i);
		}
	}

	@Override
	public void sendRoutingWarningMessage(String message) {
		for(int i=0;i<10;i++) {
			amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_ROUTING_EXCHANGE,
					RabbitMQConfig.DIRECT_WARNING_ROUTING_ROUTINGKEY,message+"--"+i);
		}
	}

}

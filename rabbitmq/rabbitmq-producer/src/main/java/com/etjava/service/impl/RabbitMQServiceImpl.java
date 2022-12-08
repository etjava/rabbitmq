package com.etjava.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.RabbitMQService;

@Service("rabbitMQService")
public class RabbitMQServiceImpl implements RabbitMQService {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	
	/**
	 * 发送消息到rabbitmq
	 * String exchange 交换机名称 - 消息要发送到哪个交换机上去
	 * String routingKey 路由Key - 一个交换机可以绑定多个队列 消息需要发送到具体的队列
	 * Object message 需要发送的消息
	 * 
	 * 测试发送消息 直接在启动类中测试 没有单独做测试 而是直接集成到springboot中
	 * 获取ApplicationContext 上下文 然后就可以通过service的名称获取到对应的service 然后调用就可以了
	 * 
	 */
	@Override
	public void sendMessage(String message) {
		for(int i=0;i<100;i++) {
			amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE,
					RabbitMQConfig.DIRECT_ROUTINGKEY,message+"--"+i);
		}
	}
}

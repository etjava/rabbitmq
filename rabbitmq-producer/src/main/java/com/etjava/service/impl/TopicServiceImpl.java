package com.etjava.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.TopicService;

@Service("topicService")
public class TopicServiceImpl implements TopicService {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Override
	public void sendTopicMessage() {
		amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_TOPIC_EXCHANGE,
				"quick.orange.rabbit","跑的快的橘色兔子");// 可以匹配到*.orange.* 和 *.*.rabbit
		
		amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_TOPIC_EXCHANGE,
				"a.quick.orange.rabbit","跑的快的橘色兔子2");// 无法匹配 消息会丢失
		
		amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_TOPIC_EXCHANGE,
				"lazy.rabbit","慢的橘色兔子");// 可以匹配到lazy开头的路由key 同时可以匹配到*.rabbit开头的路由Key
		
		amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_TOPIC_EXCHANGE,
				"dog.lazy","dog.lazy");// 该路由Key无法匹配 原因 定义的路由Key 是lazy.#  消息会丢失
		
		amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_TOPIC_EXCHANGE,
				"lazy.cat","lazy.cat");// 可以匹配到lazy开头的路由key
		
	}

}

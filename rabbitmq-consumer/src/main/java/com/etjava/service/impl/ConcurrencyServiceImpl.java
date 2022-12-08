package com.etjava.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.ConcurrencyService;
import com.rabbitmq.client.Channel;

@Service("concurrencyService")
public class ConcurrencyServiceImpl implements ConcurrencyService {
	
	@Autowired
	CachingConnectionFactory cachingConnectionFactory;
	
	@Bean(name="limitContainerFactory")
	public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(cachingConnectionFactory);
		factory.setPrefetchCount(3);// 每次获取3条
		return factory;
	}

	/**
	 * concurrency 配置并发数 5-10 表示最小5个并发 最大10个并发
	 * 注意 测试该方法时需要注释掉ACKServiceImpl中的RabbitMQConfig.DIRECT_QUEUE队列的监听
	 */
	@Override
	//@RabbitListener(queues = RabbitMQConfig.DIRECT_QUEUE,concurrency = "5-10")
	public void reviceConcurrencyMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		System.out.println("测试提高并发量");
		try {
			System.out.println("deliveryTag:"+deliveryTag);
			/*	
			 * 手动确认
			 * long deliveryTag  消息的TAG 类似消息的ID 用来进行批量处理操作
			 * boolean multiple	是否批量处理 true 批量处理
			 * 这里不使用累计批处理 每接收一条消息就确认一次  如果使用批处理则需要自定义批处理条件
			 * 例如 if(deliveryTag%5==0)每隔五条进行处理一次
			 */
			
			channel.basicAck(deliveryTag, true);
			
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * 发生异常时可拒签
			 * deliveryTag,  消息的TAG
			 * multiple, 是否批量操作
			 * requeue 是否将消息送回队列 false丢弃消息 true送回队列
			 */
			try {
				channel.basicNack(deliveryTag, false, true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	
	
	@Override
	@RabbitListener(queues = RabbitMQConfig.DIRECT_QUEUE,concurrency = "5-10",containerFactory = "limitContainerFactory")
	public void reviceConcurrencyMessage2(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG)long deliveryTag) {
		System.out.println("测试限流");
		try {
			System.out.println("deliveryTag:"+deliveryTag);
			channel.basicAck(deliveryTag, true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				channel.basicNack(deliveryTag, false, true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	
}

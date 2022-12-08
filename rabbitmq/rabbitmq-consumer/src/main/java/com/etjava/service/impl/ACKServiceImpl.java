package com.etjava.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.ACKService;
import com.rabbitmq.client.Channel;

@Service("ackService")
public class ACKServiceImpl implements ACKService {

	
	@Override
	//@RabbitListener(queues = {RabbitMQConfig.DIRECT_QUEUE},concurrency = "5-10")
	public void reviceMessage(String message,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		System.out.println("direct队列接收到的消息>:"+message);
		// 模拟处理业务逻辑
		System.out.println("接收到消息后进行业务处理");
		
		try {
			/*	
			 * 手动确认
			 * long deliveryTag  消息的TAG 类似消息的ID 用来进行批量处理操作
			 * boolean multiple	是否批量处理 true 批量处理
			 */
			System.out.println("deliveryTag:"+deliveryTag);
			// int i = 1/0;// 测试发生异常时 消息是否送回原有队列中 
			if(deliveryTag % 100==0) {// 每隔100条消息进行批量确认一次  最大批处理250条
				System.out.println("========> 批量确认消息");
				channel.basicAck(deliveryTag, true);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * 发生异常时可拒签
			 * deliveryTag,  消息的TAG
			 * multiple, 是否批量操作
			 * requeue 是否将消息送回队列 false丢弃消息 true送回队列
			 */
			try {
				System.out.println("发生异常时将消息返回到队列中");
				channel.basicNack(deliveryTag, false, true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}

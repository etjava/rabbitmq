package com.etjava.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.etjava.config.BaseConfig;
import com.etjava.service.ConDLXService;
import com.rabbitmq.client.Channel;

@Service("conDLXService")
public class ConDLXServiceImpl implements ConDLXService {

	@Override
	@RabbitListener(queues = {BaseConfig.DIRECT_TTL_QUEUE1})
	public void receiveMessage(String message,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG)long deliverTag) {
		try {
			System.out.println(System.currentTimeMillis()+": 接收到的消息>"+message);
			// 模拟异常
			int i=1/0;
			// 手动确认已收到消息 可批手动量确认
			channel.basicAck(deliverTag, true);
		} catch (Exception e) {
			try {
				System.out.println(System.currentTimeMillis()+": 发生异常丢弃的消息>"+message);
				// nck为拒签消息 
				// 参数消息的TAG,是否批量操作  是否丢弃消息
				// 这里不能将消息放回原来的队列中 因此需要设置为false 直接丢弃
				// 设置了死信队列后 丢弃的消息会进入到死信队列中
				channel.basicNack(deliverTag, false, false);
			}catch(Exception ex) {
				
			}
		}
	}
}

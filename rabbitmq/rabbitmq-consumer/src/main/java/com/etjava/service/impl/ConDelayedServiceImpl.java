package com.etjava.service.impl;

import java.io.IOException;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.etjava.config.BaseConfig;
import com.etjava.service.ConDelayedService;
import com.rabbitmq.client.Channel;

@Service("conDelayedService")
public class ConDelayedServiceImpl implements ConDelayedService {

	
	@Override
	@RabbitListener(queues = {BaseConfig.DIRECT_DELAYED_QUEUE})
	public void reveiceMessage(String message,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG)long deliverTag) {
		System.out.println("接收到的延时消息："+message+" -"+new Date().toString());
		// 手动确认签收 如果改为自动确认签收 需要将配置文件中的acknowledge-mode 改为none
		try {
			channel.basicAck(deliverTag, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

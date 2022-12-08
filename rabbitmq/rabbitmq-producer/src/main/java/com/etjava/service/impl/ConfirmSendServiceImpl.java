package com.etjava.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.ConfirmSendService;

//@Service("confirmSendService")
public class ConfirmSendServiceImpl implements ConfirmSendService,RabbitTemplate.ConfirmCallback {

	// RabbitTemplate 是AmqpTemplate实现类,功能更为强大
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/*
	 * 发送消息之前需要开启确认回调
	 * @PostConstruct 是在spring初始化完成带有@Autowired注入后执行@PostConstruct注解的方法
	 */
	@PostConstruct
	public void init() {
		rabbitTemplate.setConfirmCallback(this);
	}
	
	/**
	 * 发送消息
	 */
	@Override
	public void sendMessage(String message) {
		// 发送消息时需要先将消息的ID传递到Broker 那么Borker才可以在回调时将ID带回来
		CorrelationData data = new CorrelationData("orderId-123");
		rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE,
				RabbitMQConfig.DIRECT_ROUTINGKEY,message,data);
	}

	/**
	 * 消息发送后的回调
	 * correlationData 消息的唯一标识 (发送时需要传递过去才会在回调时带回来)
	 * ack Borker是否收到消息 true收到消息 false 没有收到消息
	 * cause 失败的原因 消息发送成功时该参数为null
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if(ack) {
			System.out.println("Borker接收消息成功"+correlationData);
		}else {
			System.out.println("Broker接收消息失败"+correlationData+"失败原因："+cause);
			// 这里需要做消息补发
		}
	}

}

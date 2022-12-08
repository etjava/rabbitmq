package com.etjava.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etjava.config.RabbitMQConfig;
import com.etjava.service.ReturnSendService;

@Service("returnSendService123213")
public class ReturnSendServiceImpl implements ReturnSendService,
			RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@PostConstruct
	public void init() {
		rabbitTemplate.setConfirmCallback(this);
		rabbitTemplate.setReturnCallback(this);
	}
	
	/**
	 * 消息回退
	 * 从交换机(Broker)到队列如果发生异常则回退
	 * message 消息主体
	 * replyCode 状态码
	 * replyText 描述信息
	 * exchange 交换机名称
	 * routingKey 路由key名称
	 */
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("message>:"+new String(message.getBody()));
		System.out.println("replyCode>:"+replyCode);
		System.out.println("replyText>:"+replyText);
		System.out.println("exchange>:"+exchange);
		System.out.println("routingKey>:"+routingKey);
	}

	@Override
	public void sendMessage(String message,String msgId) {
		// 发送消息时需要先将消息的ID传递到Broker 那么Borker才可以在回调时将ID带回来
		CorrelationData data = new CorrelationData(msgId);
		rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE,
				RabbitMQConfig.DIRECT_ROUTINGKEY,message,data);// RabbitMQConfig.DIRECT_ROUTINGKEY
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
			System.out.println("消息发送成功>："+correlationData);
		}else {
			System.out.println("消息发送失败>："+correlationData+" 失败原因>："+cause);
			// 这里需要做消息补发
		}
	}

}

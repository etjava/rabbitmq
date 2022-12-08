package com.etjava.service;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

import com.rabbitmq.client.Channel;

/**
 * 	接收延时消息
 * @author etjav
 *
 */
public interface ConDelayedService {

	public void reveiceMessage(String message,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG)long deliverTag);
}

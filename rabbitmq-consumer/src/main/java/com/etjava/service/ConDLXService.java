package com.etjava.service;

import com.rabbitmq.client.Channel;

/**
 * 	接收消息 测试拒签后消息是否进入死信队列
 * @author etjav
 *
 */
public interface ConDLXService {

	/**
	 * 	接收消息 测试拒签
	 * */ 
	public void receiveMessage(String message,Channel channel,long deliverTag);
}

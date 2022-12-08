package com.etjava.service;

import com.rabbitmq.client.Channel;

/**
 * 手动确认接收到的消息
 * @author etjav
 *
 */
public interface ACKService {

	/**
	 * 接收消息并手动确认
	 * @param message 接收到的消息
	 * @param channel 需要通过channel进行确认
	 * @param deliveryTag 接收到消息的Tag 类似消息的ID 用于批量处理
	 * 			例如接收到五条消息 那么这个tag就是1，2，3，4，5
	 */
	public void reviceMessage(String message,Channel channel,long deliveryTag);
}

package com.etjava.service;

import com.rabbitmq.client.Channel;

/**
 * 	并发处理消息和限流处理消息接口
 * @author etjav
 *
 */
public interface ConcurrencyService {

	/**
	 * 	接收消息并手动确认 - 提高并发量
	 * @param message 接收到的消息
	 * @param channel 需要通过channel进行确认
	 * @param deliveryTag 接收到消息的Tag 类似消息的ID 用于批量处理
	 * 			例如接收到五条消息 那么这个tag就是1，2，3，4，5
	 */
	public void reviceConcurrencyMessage(String message,Channel channel,long deliveryTag);
	
	/**
	 * 	接收消息并手动确认 - 限流设置
	 * @param message
	 * @param channel
	 * @param deliveryTag
	 */
	public void reviceConcurrencyMessage2(String message,Channel channel,long deliveryTag);
}

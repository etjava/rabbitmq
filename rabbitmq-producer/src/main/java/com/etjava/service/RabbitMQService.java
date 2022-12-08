package com.etjava.service;

/**
 * 发送消息Service
 * @author etjav
 *
 */
public interface RabbitMQService {

	/**
	 * 发送消息
	 * @param message
	 */
	public void sendMessage(String message);
	
}

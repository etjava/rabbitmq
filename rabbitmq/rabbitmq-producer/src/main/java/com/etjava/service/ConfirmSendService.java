package com.etjava.service;

/**
 * 消息的可靠性投递 - confirm模式
 * @author etjav
 *
 */
public interface ConfirmSendService {

	/**
	 * 发送消息
	 * @param message
	 */
	public void sendMessage(String message);
}

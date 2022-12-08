package com.etjava.service;

/**
 * 普通一对一模式
 * @author etjav
 *
 */
public interface ConsumerService {

	/**
	 * 接收队列消息 - 每次调用只接收一条
	 */
	public void receiveMessage();
	
	/**
	 * 接收队列消息1 - 监听方式 只要队列有消息就自动接收
	 * @param message - 接收到的消息
	 */
	public void receiveMessageWithListener(String message);
	
	/**
	 * 接收队列消息2 - 监听方式 只要队列有消息就自动接收
	 * @param message - 接收到的消息
	 */
	public void receiveMessageWithListener2(String message);
	
}

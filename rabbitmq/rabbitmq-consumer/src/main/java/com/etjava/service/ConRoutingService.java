package com.etjava.service;

/**
 * 路由模式 消费者service接口
 * @author etjav
 *
 */
public interface ConRoutingService {

	/**
	 * 监听接收队列1的数据
	 * @param message
	 */
	public void receiveMessageWithListener1(String message);
	
	/**
	 * 监听接收队列2的数据
	 * @param message
	 */
	public void receiveMessageWithListener2(String message);
}

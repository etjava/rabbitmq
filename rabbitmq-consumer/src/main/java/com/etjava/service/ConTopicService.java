package com.etjava.service;

/**
 * 主题模式 消费者接口
 * @author etjav
 *
 */
public interface ConTopicService {

	/**
	 * 监听队列1
	 * @param message
	 */
	public void receiveMessage(String message);
	/**
	 * 监听队列2
	 * @param message
	 */
	public void receiveMessage2(String message);
}

package com.etjava.service;

/**
 * 消费者 发布订阅模式Service接口
 * @author etjav
 *
 */
public interface ConFanoutService {

	/**
	 * 接收订阅的消息 - 发布订阅模式1
	 */
	public void receiveSubMessage1(String message);
	
	/**
	 * 接收订阅的消息 - 发布订阅模式2
	 */
	public void receiveSubMessage2(String message);
}

package com.etjava.service;

/**
 * 测试发布订阅模式service接口
 * @author etjav
 *
 */
public interface FanoutService {

	/**
	 * 发送广播类型消息
	 * Fanout类型的交换机会将所有消息发送到所有绑定到该交换机的队列中
	 * @param message
	 */
	public void sendFanoutMessage(String message);
}

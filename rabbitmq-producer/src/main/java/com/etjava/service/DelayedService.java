package com.etjava.service;

/**
 * 	测试延时队列
 * 	插件方式
 * @author etjav
 *
 */
public interface DelayedService {

	/**
	 * 	发送消息 带延迟时间
	 * @param message
	 * @param delayTime 延时时间 单位毫秒
	 */
	public void sendDelayedMessage(String message,Integer delayTime);
}

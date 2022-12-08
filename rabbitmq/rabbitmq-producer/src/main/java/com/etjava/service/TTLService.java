package com.etjava.service;

/**
 *	 带有过期时间的消息生产者接口
 * @author etjav
 *
 */
public interface TTLService {

	/**
	 * 	发送带有过期时间的消息
	 * */ 
	public void sendTTLMessage(String message);
	/**
	 * 	发送普通消息到带有过期时间队列中
	 * */ 
	public void sendDLXMessage(String message);
}

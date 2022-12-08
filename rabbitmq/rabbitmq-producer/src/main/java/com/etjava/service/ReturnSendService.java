package com.etjava.service;

/**
 * return 回退模式发送消息接口
 * 该模式可以配合confirm确认模式使用
 * confirm是到Broker 无法监听到从Broker到队列
 * return模式是从Broker到队列
 * @author etjav
 *
 */
public interface ReturnSendService{

	public void sendMessage(String message,String msgId);
}

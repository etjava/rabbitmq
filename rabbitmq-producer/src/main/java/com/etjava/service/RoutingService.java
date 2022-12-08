package com.etjava.service;

/**
 * 路由模式Service接口
 * @author etjav
 *
 */
public interface RoutingService {

	/**
	 * 发送路由模式消息 - error级别
	 * @param message
	 */
	public void sendRoutingErrorMessage(String message);
	
	/**
	 * 发送路由模式消息 - info级别
	 * @param message
	 */
	public void sendRoutingInfoMessage(String message);
	
	/**
	 * 发送路由模式消息 - warning级别
	 * @param message
	 */
	public void sendRoutingWarningMessage(String message);
	
}

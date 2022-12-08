package com.etjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.etjava.service.DelayedService;

@SpringBootApplication
public class ProApp {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(ProApp.class, args);
//		RabbitMQService rabbitMQService = (RabbitMQService)ac.getBean("rabbitMQService");
//		rabbitMQService.sendMessage("RabbitMQ helloworld！");
		// 发布订阅模式
//		FanoutService fanoutService = (FanoutService)ac.getBean("fanoutService");
//		fanoutService.sendFanoutMessage("测试发布订阅模式消息");
		
		// 路由模式 (精确匹配)- 发送指定级别的消息到对应的队列中
//		RoutingService routingService = (RoutingService)ac.getBean("routingService");
//		routingService.sendRoutingErrorMessage("测试发送error级别数据");
//		routingService.sendRoutingInfoMessage("测试发送Info级别数据");
//		routingService.sendRoutingWarningMessage("测试发送Warning级别数据");
		
		// 主题模式 - 模糊匹配
//		TopicService topicService = (TopicService)ac.getBean("topicService");
//		topicService.sendTopicMessage();
		
		// confirm确认模式
//		ConfirmSendService confirmSendService = (ConfirmSendService)ac.getBean("confirmSendService");
//		confirmSendService.sendMessage("测试confirm确认模式发送消息");
		
		/*
		 * return回退模式
		 * 测试return回退模式时需要将confirm确认模式的service注入备注掉 
		 * 否则会抛出Only one ConfirmCallback is supported by each RabbitTemplate
		 */
//		ReturnSendService returnSendService = (ReturnSendService) ac.getBean("returnSendService123213");
//		for(int i = 0;i<10;i++) {
//			returnSendService.sendMessage("测试return模式发送消息",""+i);
//		}
		// 测试带有过期时间的消息和队列
//		TTLService ttlService = (TTLService)ac.getBean("ttlService");
//		for(int i = 0;i<20;i++) {
//			// ttlService.sendTTLMessage("测试带有过期时间的消息-"+i);
//			ttlService.sendDLXMessage("测试带有过期时间的消息-"+i);
//		}
		
		DelayedService delayedService = (DelayedService)ac.getBean("delayedService");
		delayedService.sendDelayedMessage("测试延时消息1", 2000);// 单位是毫秒
		delayedService.sendDelayedMessage("测试延时消息2", 10000);
		delayedService.sendDelayedMessage("测试延时消息3", 20000);
	}
}

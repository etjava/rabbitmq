package com.etjava.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Rabbit MQ 配置
 * @author etjav
 *
 */
@Configuration
public class RabbitMQConfig extends BaseConfig{

	/**
	 * 定义Direct交换机并指定名称
	 * 注意：DirectExchange是rabbitmq.core包下的
	 * @return
	 */
	@Bean
	public DirectExchange directExchange() {
		// 创建交换机 并指定交换机名称directExchange
		return new DirectExchange(DIRECT_EXCHANGE);
	}
	
	/**
	 * 定义Fanout交换机并指定名称
	 * 注意：DirectExchange是rabbitmq.core包下的
	 * @return
	 */
	@Bean
	public FanoutExchange fanoutExchange() {
		// 创建交换机 并指定交换机名称directExchange
		return new FanoutExchange(FANOUT_EXCHANGE);
	}
	
	/**
	 * 定义队列并指定名称
	 * 注意：Queue是rabbitmq.core包下的
	 * @return
	 */
	@Bean
	public Queue directQueue() {
		return new Queue(DIRECT_QUEUE);
	}
	
	/**
	 * 定义 订阅队列并指定名称
	 * 注意：Queue是rabbitmq.core包下的
	 * @return
	 */
	@Bean
	public Queue subQueue1() {
		return new Queue(SUB_QUEUE1);
	}
	
	/**
	 * 定义 订阅队列并指定名称
	 * 注意：Queue是rabbitmq.core包下的
	 * @return
	 */
	@Bean
	public Queue subQueue2() {
		return new Queue(SUB_QUEUE2);
	}
	
	/**
	 * 定义路由和交换机的绑定(路由规则) - 将队列和路由绑定 并指定路由规则
	 * 从队列到路由
	 * @return
	 */
	@Bean
	public Binding directBinding() {
		
		return BindingBuilder.bind(directQueue())
				.to(directExchange())
				.with(DIRECT_ROUTINGKEY);
	}
	
	/**
	 * 定义路由和交换机的绑定(路由规则) - 将队列和路由绑定
	 * 从队列到路由
	 * @return
	 */
	@Bean
	public Binding fanoutBinding1() {
		// 发布订阅模式 将队列1绑定到广播模式的路由中 因为广播模式会将消息发送到所有绑定的队列上 因此不需要在设置RoutingKey
		return BindingBuilder.bind(subQueue1())
				.to(fanoutExchange());
	}
	
	
	/**
	 * 定义路由和交换机的绑定(路由规则) - 将队列和路由绑定
	 * 从队列到路由
	 * @return
	 */
	@Bean
	public Binding fanoutBinding2() {
		// 发布订阅模式 将队列1绑定到广播模式的路由中 因为广播模式会将消息发送到所有绑定的队列上 因此不需要在设置RoutingKey
		return BindingBuilder.bind(subQueue2())
				.to(fanoutExchange());
	}
	
	
	/////////[路由模式]////////////////////////////////////////////////////////////////////////////////
	/**
	 * 路由模式 创建direct类型交换机 
	 * @return
	 */
	@Bean
	public DirectExchange directRoutingExchange() {
		return new DirectExchange(DIRECT_ROUTING_EXCHANGE);
	}
	
	/**
	 * 路由模式 创建direct类型的队列1
	 * @return
	 */
	@Bean
	public Queue directRoutingQueue1() {
		return new Queue(DIRECT_ROUTING_QUEUE1);
	}
	
	/**
	 * 路由模式 创建direct类型的队列2
	 * @return
	 */
	@Bean
	public Queue directRoutingQueue2() {
		return new Queue(DIRECT_ROUTING_QUEUE2);
	}
	
	/**
	 * 路由模式绑定队列1 - 队列1只指定一个级别 error
	 * @return
	 */
	@Bean
	public Binding directRoutingBinding() {
		return BindingBuilder.bind(directRoutingQueue1())
				.to(directRoutingExchange())
				.with(DIRECT_ERROR_ROUTING_ROUTINGKEY);
	}
	
	/**
	 * 路由模式绑定队列2 - 队列2指定error级别
	 * @return
	 */
	@Bean
	public Binding directErrorRoutingBinding() {
		return BindingBuilder.bind(directRoutingQueue2())
				.to(directRoutingExchange())
				.with(DIRECT_ERROR_ROUTING_ROUTINGKEY);
	}
	
	/**
	 * 路由模式绑定队列2 - 队列2指定info级别
	 * @return
	 */
	@Bean
	public Binding directInfoRoutingBinding() {
		return BindingBuilder.bind(directRoutingQueue2())
				.to(directRoutingExchange())
				.with(DIRECT_INFO_ROUTING_ROUTINGKEY);
	}
	
	/**
	 * 路由模式绑定队列2 - 队列2指定warning级别
	 * @return
	 */
	@Bean
	public Binding directWarningRoutingBinding() {
		return BindingBuilder.bind(directRoutingQueue2())
				.to(directRoutingExchange())
				.with(DIRECT_WARNING_ROUTING_ROUTINGKEY);
	}
	
	////////////[Topic模式]////////////////////////////////////////////////////////////////////
	/**
	 * 主题模式 创建direct类型交换机 
	 * @return TopicExchange
	 */
	@Bean
	public TopicExchange directTopicExchange() {
		return new TopicExchange(DIRECT_TOPIC_EXCHANGE);
	}
	
	/**
	 * 主题模式 创建direct类型的队列1
	 * @return
	 */
	@Bean
	public Queue directTopicQueue1() {
		return new Queue(DIRECT_TOPIC_QUEUE1);
	}
	
	/**
	 * 主题模式 创建direct类型的队列1
	 * @return
	 */
	@Bean
	public Queue directTopicQueue2() {
		return new Queue(DIRECT_TOPIC_QUEUE2);
	}
	
	// 三个路由key 绑定两个队列
	/**
	 * 主题模式 - 绑定路由与队列
	 * *.rabbit.*一个*号表示匹配一个单词
	 * @return
	 */
	@Bean
	public Binding directTopicBinding1() {
		return BindingBuilder.bind(directTopicQueue1())
				.to(directTopicExchange())
				.with("*.orange.*");// DIRECT_TOPICSTART_ROUTINGKEY1
	}
	
	/**
	 * 主题模式 - 绑定路由与队列
	 * *.*.rabbit  一个*号表示匹配一个单词
	 * @return
	 */
	@Bean
	public Binding directTopicBinding2() {
		return BindingBuilder.bind(directTopicQueue2())
				.to(directTopicExchange())
				.with("*.*.rabbit");//DIRECT_TOPICSTART_ROUTINGKEY2 
	}
	
	/**
	 * 主题模式 - 绑定路由与队列
	 * lazy.#  #号表示匹配零个或多个单词
	 * @return
	 */
	@Bean
	public Binding directTopicBinding3() {
		return BindingBuilder.bind(directTopicQueue2())
				.to(directTopicExchange())
				.with("lazy.#");//DIRECT_TOPICHASH_ROUTINGKEY  
	}
	///////[TTL]///////////////////////////////////////////////////////////////////////////
	
	/**
	 * TTL 交换机
	 */
	@Bean
	public TopicExchange directTTLExchange() {
		return new TopicExchange(DIRECT_TTL_EXCHANGE);
	}
	
	/**
	 * 	TTL队列
	 * 
	 * 	参数:
	 * 	name 队列名称
	 * 	acrualName 队列真实名称 默认使用name参数 如果name为空 则自动生产一个
	 * 	durable 是否持久化
	 * 	exclusive 是否独占 排他的
	 * 	autoDelete 是否自动删除
	 * 	队列其它参数
	 * 	x-message-ttl 队列中消息的过期时间 单位毫秒
	 * 	x-expires 队列的过期时间 单位毫秒
	 * 	x-max-length 队列最大长度 超过该长度则从队列头部开始删除消息
	 * 	x-max-length-bytes 队列中消息内容占用最大空间，受内存限制，超过该阙值则从队列头部开始删除消息
	 * 	x-overflow 设置队列溢出行为 这决定了当到达队列最大长度时消息会发生什么
	 * 				有效值是drop-head,reject-publish,reject-publish-dlx 仲裁队列类型仅支持drop-head
	 * x-dead-letter-exchange 死信交换器名称 过期或被删除的队列会被发送到该队列中
	 * x-dead-letter-routing-key 死信消息路由，在消息发送到死信交换器时使用该路由key,如果不设置则使用消息原来的路由Key
	 * x-single-active-consumer 队列是否只有一个存活的消费者，true时 注册的消费组内只有一个消费者消费消息 其它消费者被忽略
	 * 							默认false 队列中的消息会轮询发送给所有注册的消费者
	 * x-max-priority 队列的最大优先级 未设置则不支持消息的优先级
	 * x-queue-mode(lazy-mode) 将队列设置未延迟模式 在磁盘上保留尽可能多的消息，以减少RAM的使用
	 * 						默认不延迟，队列中的消息保存在内存中 以尽可能快的传递消息
	 * 
	 * 		
	 * @return
	 * 
	 */
	@Bean
	public Queue directTTLQueue1() {
		Map<String, Object> map = new HashMap<>();
		map.put("x-message-ttl", 10000); // 队列的过期时间 单位是毫秒
		map.put("x-dead-letter-exchange", DIRECT_DLX_EXCHANGE);// 指定死信交换机
		map.put("x-dead-letter-routing-key", DIRECT_DLX_ROUTINGKEY); // 指定死信路由Key
		
		map.put("x-max-length", 10);// 设置消息最大长度 超出的会从头部开始删除
//		return new Queue(DIRECT_TTL_QUEUE1);
		// 参数 队列名称，持久化，不独占，不自动删除，
		return new Queue(DIRECT_TTL_QUEUE1,true,false,false,map );
	}
	
	/**
	 *	TTL  绑定队列到路由
	 */
	@Bean
	public Binding directTTLBinding1() {
		return BindingBuilder.bind(directTTLQueue1())
				.to(directTTLExchange())
				.with(DIRECT_TTL_ROUTINGKEY);
	}
	
	// DLX(死信) 	 *******************************************************
	/**
	 * DLX 交换机
	 */
	@Bean
	public TopicExchange directDLXExchange() {
		return new TopicExchange(DIRECT_DLX_EXCHANGE);
	}
	
	/**
	 * DLX 队列
	 * @return
	 */
	@Bean
	public Queue directDLXQueue1() {
		return new Queue(DIRECT_DLX_QUEUE1);
	}
	
	/**
	 *	DLX  绑定队列到路由
	 */
	@Bean
	public Binding directDLXBinding1() {
		return BindingBuilder.bind(directDLXQueue1())
				.to(directDLXExchange())
				.with(DIRECT_DLX_ROUTINGKEY);
	}
	
	///[延时队列]	////////////////////////////////////////////////////////////////////
	/**
	 * 	延时队列 交换机
	 */
	@Bean
	public CustomExchange delayedExchange() {
		Map<String, Object> args = new HashMap<>();
	    args.put("x-delayed-type", "direct");
	    return new CustomExchange(DIRECT_DELAYED_EXCHANGE,"x-delayed-message", true, false, args);
	}
	
	/**
	 * 	延时队列 队列
	 * @return
	 */
	@Bean
	public Queue delayedQueue() {
		return new Queue(DIRECT_DELAYED_QUEUE);
	}
	
	/**
	 *	延时队列  绑定队列到路由
	 */
	@Bean
	public Binding delayedDLXBinding() {
		return BindingBuilder.bind(delayedQueue())
				.to(delayedExchange())
				.with(DIRECT_DELAYED_ROUTINGKEY)
				.noargs();// 绑定使用插件的消息路由时 需要使用noargs
	}
	
}

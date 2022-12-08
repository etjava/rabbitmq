package com.etjava.config;

/**
 * 定义路由与队列名称
 * @author etjav
 *
 */
public class BaseConfig {

	/**
	 * direct交换机名称
	 */
	public static final String DIRECT_EXCHANGE="directExchange";
	
	/**
	 * fanout交换机名称
	 */
	public static final String FANOUT_EXCHANGE="fanoutExchange";
	/**
	 * 工作模式队列名称
	 */
	public static final String DIRECT_QUEUE="directQueue";
	
	/**
	 * fanout(发布订阅模式)队列名称1
	 */
	public static final String SUB_QUEUE1="subQueue1";
	
	/**
	 * fanout(发布订阅模式) 队列名称2
	 */
	public static final String SUB_QUEUE2="subQueue2";
	
	/**
	 * direct路由Key
	 */
	public static final String DIRECT_ROUTINGKEY="directRoutingKey";
	
	
	/////////////[路由模式]////////////////////////////////////////////////////
	/**
	* direct交换机名称 - 路由模式
	*/
	public static final String DIRECT_ROUTING_EXCHANGE="direct_routinge_exchange";
	
	/**
	* routing(路由模式) 队列名称1
	*/
	public static final String DIRECT_ROUTING_QUEUE1 = "direct_routing_queue1";
	
	/**
	* routing(路由模式) 队列名称2
	*/
	public static final String DIRECT_ROUTING_QUEUE2 = "direct_routing_queue2";
	
	/**
	 * 路由模式 direct路由Key - error级别路由key
	 */
	public static final String DIRECT_ERROR_ROUTING_ROUTINGKEY="direct_error_routing_routingKey";
	
	/**
	 * 路由模式 direct路由Key - info级别路由key
	 */
	public static final String DIRECT_INFO_ROUTING_ROUTINGKEY="direct_info_routing_routingKey";
	
	/**
	 * 路由模式 direct路由Key - warning级别路由key
	 */
	public static final String DIRECT_WARNING_ROUTING_ROUTINGKEY="direct_warning_routing_routingKey";
	
	///////////[Topic 主题模式]////////////////////////////////////////////////////////////////
	
	/**
	 * topic队列1名称
	 */
	public static final String DIRECT_TOPIC_QUEUE1="topicQueue1";
	/**
	 * topic队列2名称
	 */
	public static final String DIRECT_TOPIC_QUEUE2="topicQueue2";
	
	/**
	 * topic交换机名称
	 */
	public static final String DIRECT_TOPIC_EXCHANGE="directTopicExchange";
	
	/**
	 * topic路由Key - 匹配一个单词
	 * *.orange.* 表示前后各匹配一个单词
	 */
	public static final String DIRECT_TOPICSTART_ROUTINGKEY1="*.orange.*";
	public static final String DIRECT_TOPICSTART_ROUTINGKEY2="*.*.rabbit";
	
	/**
	 * topic路由Key - 匹配一个单词
	 * lazy.# 表示前后各匹配一个单词
	 */
	public static final String DIRECT_TOPICHASH_ROUTINGKEY="lazy.#";
	
	//////////////[TTL 过期时间] //////////////////////////////////////////////////////////////////////////////////
	/**
	 * 	交换机 ttlDirectExchange
	 */
	public static final String DIRECT_TTL_EXCHANGE="ttlDirectExchange";
	
	/**
	 * topic队列1名称 ttlQueue1
	 */
	public static final String DIRECT_TTL_QUEUE1="ttlQueue1";
	
	/**
	 * 	路由Key	ttlRoutingKey
	 */
	public static final String DIRECT_TTL_ROUTINGKEY="ttlRoutingKey";
	
	////[DLX 死信]	/////////////////////////////////////////////////////////////////////
	/**
	 * 	交换机 dlxDirectExchange
	 */
	public static final String DIRECT_DLX_EXCHANGE="dlxDirectExchange";
	
	/**
	 * topic队列1名称 dlxQueue1
	 */
	public static final String DIRECT_DLX_QUEUE1="dlxQueue1";
	
	/**
	 * 	路由Key	dlxRoutingKey
	 */
	public static final String DIRECT_DLX_ROUTINGKEY="dlxRoutingKey";
	
	///[延时队列]	////////////////////////////////////////////////////////////////////
	/**
	 * 	延时队列 交换机 delayedDirectExchange
	 */
	public static final String DIRECT_DELAYED_EXCHANGE="delayedDirectExchange";
	
	/**
	 * 	延时队列	delayedQueue
 	 */
	public static final String DIRECT_DELAYED_QUEUE="delayedQueue";
	
	/**
	 * 	路由Key	delayedRoutingKey
	 */
	public static final String DIRECT_DELAYED_ROUTINGKEY="delayedRoutingKey";
}

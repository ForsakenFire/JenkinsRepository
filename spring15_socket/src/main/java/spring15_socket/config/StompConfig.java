package spring15_socket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @EnableWebSocketMessageBroker包含@EnableWebSocket
 * @author TD
 *
 */
@Configuration
@EnableWebSocketMessageBroker
@PropertySource("classpath:activemq.properties")
public class StompConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Autowired
	private Environment env;
	/**
	 * 注册代理，暴露节点用于连接。
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// TODO Auto-generated method stub
		registry.addEndpoint("/stompEndPoint.do").withSockJS();
	}
	
	/**
	 * 修改消息代理的配置，默认处理以/topic为前缀的消息
	 * setApplicationDestinationPrefixes：配置请求的根路径，表示通过MessageMapping处理/td/*请求，不会发送到代理
	 * enableStompBrokerRelay:配置代理，匹配路径的请求会进入代理：mq等
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		/*基于内存实现的stomp代理，单机适用
		registry.enableSimpleBroker("/queue","/topic");
		*/
		//以/td为目的地的消息使用MessageMapping控制器处理，不走代理
		registry.setApplicationDestinationPrefixes("/td","/app");
		/**
		 * 设置单独发送到某个user需要添加的前缀，用户订阅地址/user/topic/td1地址后会去掉/user，并加上用户名（需要springsecurity支持）等唯一标识组成新的目的地发送回去，
		 * 对于这个url来说 加上后缀之后走代理。发送时需要制定用户名:convertAndSendToUser或者sendtouser注解.
		registry.setUserDestinationPrefix("/user")
		 */
		/*基于mq实现stomp代理，适用于集群。
		 * 以/topic和/queue开头的消息会发送到stomp代理中:mq等。
		 * 每个mq适用的前缀不一样且有限制
		 */
		registry.enableStompBrokerRelay("/topic","/queue")
		.setRelayHost(env.getProperty("mq.brokenHost"))
		.setRelayPort(Integer.parseInt(env.getProperty("mq.brokenPort")))
		.setSystemLogin(env.getProperty("mq.username"))
		.setSystemPasscode(env.getProperty("mq.password"))
		.setClientLogin(env.getProperty("mq.username"))
		.setClientPasscode(env.getProperty("mq.password"));
		/*
		 * systemLogin:设置代理所需的密码
		 * client:设置客户端连接代理所需的密码，默认为guest
		*/
	}
	
	
}

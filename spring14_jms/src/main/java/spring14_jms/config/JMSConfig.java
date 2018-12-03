package spring14_jms.config;

import java.util.HashMap;
import java.util.Map;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import spring14_jms.bean.User;
import spring14_jms.mdb.UserAlertHandler;

@Configuration
@PropertySource("classpath:activemq.properties")
public class JMSConfig {
	@Autowired
	private Environment env;
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		//设置mq所监听的ip和端口
		connectionFactory.setBrokerURL(env.getProperty("mq.brokenUrl"));
		connectionFactory.setUserName(env.getProperty("mq.username"));
		connectionFactory.setPassword(env.getProperty("mq.password"));
		return connectionFactory;
	}
	
	/**
	 * jmstemplate，省去重复代码，增加自动的异常捕获和抛出
	 * @return
	 */
	@Bean
	public JmsTemplate template(ConnectionFactory connectionFactory,MessageConverter messageConverter) {
		JmsTemplate template = new JmsTemplate(connectionFactory);
		//设置默认的目的管道（主题或队列）
		template.setDefaultDestinationName("user.queue1");
		//设置消息转换器，默认的转换器需要序列化
		template.setMessageConverter(messageConverter);
		return template;
	} 
	
	/**
	 * 消息转换器，这里转换为json。需要进行额外的映射
	 */
	@Bean
	public MessageConverter messageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		//定义了typeid到class的map映射
		Map<String, Class<?>> typeIdMap = new HashMap<>();
		typeIdMap.put("User", User.class);
		converter.setTypeIdMappings(typeIdMap);
		//设置转换后发送到队列中的typeid名称，取数时通过typeidmap完成映射
		converter.setTypeIdPropertyName("User");
		return converter;
	}
	
	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,MessageConverter messageConverter,MessageListenerAdapter adapter){
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setDestinationName("user.queue1");
		container.setMessageConverter(messageConverter);
		container.setMessageListener(adapter);
		return container;
	}
	
	@Bean
	public MessageListenerAdapter listenerAdapter(UserAlertHandler alertHandler,MessageConverter converter) {
		MessageListenerAdapter adapter = new MessageListenerAdapter();
		adapter.setDefaultResponseQueueName("user.queue1");
		adapter.setDelegate(alertHandler);
		adapter.setMessageConverter(converter);
		adapter.setDefaultListenerMethod("alertUser");
		return adapter;
	}
	
	/**
	 * 声明一个队列：点对点模式<br>
	 * 指定队列的物理名称
	 * @return
	 */
	@Bean
	public ActiveMQQueue queue() {
		ActiveMQQueue queue = new ActiveMQQueue();
		queue.setPhysicalName("user.queue1");
		return queue;
	}
	
	
	/**
	 * 声明一个主题：订阅发布模式<br>
	 * 指定主题的物理名称
	 * @return
	 */
	@Bean
	public ActiveMQTopic topic() {
		ActiveMQTopic topic = new ActiveMQTopic();
		topic.setPhysicalName("user.topic1");
		return topic;
	}
}

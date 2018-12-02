package spring14_jms.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

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
		//设置消息转换器
		//template.setMessageConverter(messageConverter);
		return template;
	} 
	
	/**
	 * 消息转换器，这里转换为json
	 */
	@Bean
	public MessageConverter messageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		return converter;
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

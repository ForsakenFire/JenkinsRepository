package spring14_jms.service.impl;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;

import spring14_jms.bean.User;
import spring14_jms.service.AlterService;

@Service
public class AlterServiceImpl implements AlterService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JmsOperations jmsOperations;
	
	@Override
	public void sendUser(User user) {
		logger.info("开始发送messge:"+user.toString());
		/*
		 * 不带数据转换功能
		jmsOperations.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(user);
			}
		});
		*/
		jmsOperations.convertAndSend(user);
		logger.info("发送完成...");
	}

	/**
	 * 数据接收的转换有问题
	 */
	@Override
	public User receiveUsr() {
		logger.info("开始接受消息");
		User user1 = (User) jmsOperations.receiveAndConvert();
		/*User user1 = null;
		ObjectMessage receiveMessage =  (ObjectMessage) jmsOperations.receive();
		try {
			user1 = (User) receiveMessage.getObject();
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}*/
		System.out.println("接受到的User"+user1.toString());
		return user1;
	}

}

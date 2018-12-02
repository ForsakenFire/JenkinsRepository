package spring14_jms.main;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class TestMain {

	/**
	 *测试原生的jms功能 :发送到队列
	 * @param args
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws JMSException {
		//sendMessage();
		acceptMessage();
		
	}
	
	private static void sendMessage() throws JMSException {
		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection conn = null;
		Session session = null;
		try {
			conn = cf.createConnection("td","123456");
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = new ActiveMQQueue("user.queue1");
			MessageProducer producer = session.createProducer(destination);
			TextMessage msg = session.createTextMessage();
			msg.setText("hello2");
			producer.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * 取消息
	 * @throws JMSException
	 */
	private static void acceptMessage() throws JMSException {
		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection conn = null;
		Session session = null;
		try {
			conn = cf.createConnection("td","123456");
			conn.start();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = new ActiveMQQueue("user.queue1");
			MessageConsumer consumer = session.createConsumer(destination);
			Message message = consumer.receive();
			TextMessage msg = (TextMessage) message;
			System.out.println("mq接收："+msg.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
}

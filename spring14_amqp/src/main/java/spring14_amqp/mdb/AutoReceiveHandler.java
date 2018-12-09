package spring14_amqp.mdb;

import org.springframework.stereotype.Component;

import spring14_amqp.bean.User;

@Component("handler")
public class AutoReceiveHandler {
	private int count = 0 ;
	public void receiveMessage(User user) {
		count++;
		System.out.println("count:"+count+",消息驱动bean自动接收："+user);
	}
}

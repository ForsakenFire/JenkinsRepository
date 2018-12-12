package spring15_socket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import spring15_socket.bean.User;


/**
 * 基于mdb（消息驱动bean）完成自动监听队列或主题，实现异步消息。
 * 消费者不用阻塞。
 * 将其配置为一个bean之后，还需要声明为消息监听器
 * @author TD
 *
 */
@Component
public class UserAlertHandler {
	public void alertUser(User user) {
		System.out.println("异步接受请求："+user);
	} 
}

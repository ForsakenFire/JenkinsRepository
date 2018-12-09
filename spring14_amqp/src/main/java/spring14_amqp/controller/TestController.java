package spring14_amqp.controller;




import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring14_amqp.bean.User;




@Controller
public class TestController {
	
	@Autowired
	private RabbitTemplate rabbit1;
	
	@RequestMapping(value="/rabbitMQ.do")
	public String test0(Model model){
		return "rabbitMQ";
	}
	
	@RequestMapping(value="/send.do")
	public String test1(Model model) throws InterruptedException{
		User user1 = new User();
		user1.setName("resttest1");
		user1.setAge(321);
			rabbit1.convertAndSend(user1);
		return "rabbitMQ";
	}
	
	@RequestMapping(value="/receive.do")
	public String test2(Model model) throws InterruptedException{
		User user1 = (User) rabbit1.receiveAndConvert("user.queue1");
		System.out.println(user1);
		return "rabbitMQ";
	}
	
}

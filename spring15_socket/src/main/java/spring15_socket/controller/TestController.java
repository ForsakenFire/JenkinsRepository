package spring15_socket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring15_socket.bean.User;

@Controller
public class TestController {
	
	
	@RequestMapping(value="/sockjs.do")
	public String test0(Model model){
		return "sockjs";
	}	
	
	/**
	 * 表示该方法处理客户端发来的/td/stomp1.do或者/app/stomp1.do。
	 * 
	 */
	@MessageMapping("/stomp1.do")
	public void handleStomp(User user) {
		System.out.println("stomp接收到客户端的请求："+user);
		
		
	}
	
	
}

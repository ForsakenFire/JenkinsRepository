package spring14_amqp.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring14_amqp.bean.User;




@Controller
public class TestController {
	
	
	@RequestMapping(value="/activeMQ.do")
	public String test0(Model model){
		User user1 = new User();
		user1.setName("resttest1");
		user1.setAge(321);
		return "activeMQ";
	}
	
	
}

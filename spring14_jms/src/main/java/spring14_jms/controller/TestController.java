package spring14_jms.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring14_jms.bean.User;
import spring14_jms.service.AlterService;




@Controller
public class TestController {
	
	@Autowired
	private AlterService service;
	
	@RequestMapping(value="/activeMQ.do")
	public String test0(Model model){
		User user1 = new User();
		user1.setName("resttest1");
		user1.setAge(321);
		return "activeMQ";
	}
	
	/**
	 * @param model
	 * @return
	 * @throws MyException 
	 */
	@RequestMapping(value="/send.do")
	public String test(Model model){
		User user1 = new User();
		user1.setName("jmsSend");
		user1.setAge(321);
		user1.setPhone("sendphone1");
		service.sendUser(user1);
		return "activeMQ";
	}
	
	@RequestMapping(value="/receive.do")
	public String test2(Model model){
		System.out.println(service.receiveUsr());
		return "activeMQ";
	}
	
}

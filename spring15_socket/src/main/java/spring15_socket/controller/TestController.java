package spring15_socket.controller;





import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;





@Controller
public class TestController {
	
	
	@RequestMapping(value="/sockjs.do")
	public String test0(Model model){
		return "sockjs";
	}
	
	
}

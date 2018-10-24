package spring8_jdbc.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring8_jdbc.dao.JNDIDao;

@Controller
public class TestController {

	@Autowired
	private JNDIDao jndiDao;
	
	@RequestMapping("/hello.do")
	public String jndiTest(Model model) throws SQLException{
		String name = jndiDao.getName();
		model.addAttribute("name", name);
		return "hello";
	}
}

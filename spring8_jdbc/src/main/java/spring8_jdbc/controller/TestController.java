package spring8_jdbc.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring8_jdbc.dao.JDBCTemplateDao;
import spring8_jdbc.dao.JNDIDao;

@Controller
public class TestController {

	@Autowired
	private JNDIDao jndiDao;
	@Autowired
	private JDBCTemplateDao jdbcTemplateDao;
	
	@RequestMapping("/hello.do")
	public String jndiTest(Model model) throws SQLException{
		String name = jndiDao.getName();
		model.addAttribute("name", name);
		return "hello";
	}
	
	@RequestMapping("/templateTest")
	public String getUser(Model model){
		String name = jdbcTemplateDao.getName();
		long num = jdbcTemplateDao.insertUser(); 
		System.out.println(num);
		model.addAttribute("userName",name);
		return "templateTest";
	}
}

package spring10_nosql.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring10_nosql.bean.User;


@Controller
public class TestController {
	
	@Autowired
	private MongoOperations mongo;

	
	@RequestMapping("/mongoTest")
	public String getUser(Model model){
		User user = new User();
		user.setAge(22);
		user.setName("11月4日/小黄");
		user.setPhone("111");
		user.setSex(2);
		//第二个参数为要保存的文档存储的名称（Mongo使用文档来存储数据）。可以通过mongo.remove来移除某个文档
		mongo.save(user,"user");
		//查询
		List<User> userList =  mongo.find(Query.query(Criteria.where("name").is("11月4日/小黄")),User.class);
		System.out.println("查询结果："+userList.get(0).getName());;
		model.addAttribute("userName",userList.get(0).getName());
		return "mongoTest";
	}
	
	@RequestMapping("/neo4jTest")
	public String getUser2(Model model){
		User user = new User();
		user.setAge(23);
		user.setName("11月4日/小黄");
		user.setPhone("111222");
		user.setSex(2);
		//查询
		model.addAttribute("userName",null);
		return "neo4jTest";
	}
	
	
	
}

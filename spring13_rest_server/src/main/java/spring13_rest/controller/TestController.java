package spring13_rest.controller;




import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import spring13_rest.bean.Error;
import spring13_rest.bean.User;
import spring13_rest.exception.MyException;



@RestController
public class TestController {
	
	
	/**
	 * 先保存后取值
	 * @param model
	 * @return
	 * @throws MyException 
	 */
	@RequestMapping(value="/restTest.do")
	public ResponseEntity<?> test(Model model,UriComponentsBuilder ucb) throws MyException{
		User user1 = new User();
		user1.setName("resttest1");
		user1.setAge(321);
		if(user1 != null) {
			//throw new MyException("user不为空");
		}
		HttpHeaders headers = new HttpHeaders();
		URI url = ucb.path("/spring13_rest_server/").path("test.do").build().toUri();
		headers.setLocation(url);
		ResponseEntity<User> entity = new ResponseEntity<User>(user1, headers, HttpStatus.NOT_FOUND);
		
		return entity;
	}
	
	/**
	 * 全局异常处理1，手动设置状态码，返回ResponseEntity.
	 * 通过ResponseEntity可以设置responsHeader.
	 * UCB参数自动注入，可以根据运行环境动态的生成url
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MyException.class)
	public ResponseEntity<spring13_rest.bean.Error> dealError(MyException e){
		ResponseEntity<spring13_rest.bean.Error> errorEntity = null;
		Error error = new Error(e.getMessage());
		errorEntity = new ResponseEntity<>(error,HttpStatus.CREATED);
		return errorEntity;
	} 
	
	/**
	 * 异常处理2，通过注解设置状态码。同时可以设置Reason。
	 * 无需返回ResponseEntity，通过@reponsebody注解直接返回Error实体。
	 * @param e
	 * @return
	 */
	//@ExceptionHandler(MyException.class)
	//@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public Error dealError2(MyException e) {
		return new Error(e.getMessage()); 
	}
	
}

package spring5;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

import spring5.entity.MyException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MyException.class)
	public String myExceptionHandler(){
		System.out.println("handler捕获到异常");
		return "error";
	}
	
	@ModelAttribute
	public String modelAttributeTest(){
		System.out.println("modelAttributeTest被调用");
		return "Hello";
	}
	
	public void initBinderTest(){
		System.out.println("initBinderTest被调用");
	}
}

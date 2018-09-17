package spring4.aopclass;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectClass {
	
	@Pointcut("execution(* *.eat())")
	public void eatPointCut1(){}
	
	@Pointcut("execution(String *.eat(String))")
	public void eatPointCut2(){}
	
	@Before("eatPointCut1()")
	public void beforeEat(){
		System.out.println("无参before切面执行");
	}
	
	@Before("eatPointCut2()")
	public void beforeEat2(){
		System.out.println("有参before切面");
	}	
	
}
